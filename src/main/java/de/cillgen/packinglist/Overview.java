package de.cillgen.packinglist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ViewScoped
@Named
public class Overview implements Serializable {
	@Inject
	@SuppressWarnings("java:S1948")
	private Service service;
	private List<DestinationModel> destinationModels = new ArrayList<>();
	private boolean showItemModal;
	private boolean showDestinationModal;
	private Long destId;
	private String newItemName;
	private String newDestinationName;
	private Integer quantity;
	private Category category;
	private List<SelectItem> categories = new ArrayList<>();
	private Boolean showMessage;

	public void init() {
		this.destinationModels.clear();
		this.destinationModels.addAll(service.load());
		this.initCategories();
	}

	public String translateCategory(Category category) {
		return category.getTranslation();
	}

	public void updateItem(ItemModel itemModel) {
		itemModel.setDone(itemModel.getDone());
		try {
			service.updateItem(itemModel);
		} catch (ServiceException e) {
			showErrorMessage("Das Objekt konnte nicht als gepackt markiert werden.");
		}
		this.init();
	}

	public void removeItem(ItemModel itemModel) {
		try {
			service.removeItem(itemModel);
		} catch (ServiceException e) {
			showErrorMessage("Das Objekt konnte nicht gelöscht werden.");
		}
		this.init();
	}

	public void addItem() {
		if (!this.newItemName.equals("") && this.quantity != null && this.category != null) {
			ItemModel newItem = new ItemModel();
			newItem.setName(this.newItemName);
			newItem.setDone(true);
			newItem.setQuantity(this.quantity);
			newItem.setCategory(this.category);
			try {
				service.addItem(service.toItem(newItem, this.destId));
			} catch (ServiceException e) {
				showErrorMessage("Das Objekt konnte nicht hinzugefügt werden.");
			}
			this.hideItemModal();
			this.init();
		} else {
			this.showMessage = true;
		}
	}

	public void showItemModal(DestinationModel destination) {
		this.showItemModal = true;
		this.destId = destination.getId();
	}

	public void hideItemModal() {
		this.showItemModal = false;
		this.newItemName = "";
		this.quantity = null;
		this.category = null;
		this.showMessage = false;
	}

	public void removeDestination(DestinationModel destinationModel) {
		try {
			service.removeDestination(destinationModel);
		} catch (ServiceException e) {
			showErrorMessage("Das Reiseziel konnte nicht entfernt werden.");
		}
		this.init();
	}

	public void addDestination() {
		if (!this.newDestinationName.equals("")) {
			try {
				service.addDestination(service.toDestination(this.newDestinationName));
			} catch (ServiceException e) {
				showErrorMessage("Das Reiseziel konnte nicht hinzugefügt werden.");
			}
			this.hideDestinationModal();
			this.init();
		} else {
			this.showMessage = true;
		}
	}

	private void showErrorMessage(String errorMessage) {
		FacesMessage fm = new FacesMessage(errorMessage);
		fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	public void showDestinationModal() {
		this.showDestinationModal = true;
	}

	public void hideDestinationModal() {
		this.showDestinationModal = false;
		this.newDestinationName = "";
		this.showMessage = false;
	}

	private void initCategories() {
		this.categories = Arrays.stream(Category.values())
				.map(c -> new SelectItem(c, c.getTranslation()))
				.collect(Collectors.toList());
		this.categories.add(0, new SelectItem("", ""));
	}

	public List<SelectItem> getNotEmptyCategories(DestinationModel destination) {
		return this.categories.stream().filter(c -> c.getValue() != null
				&& destination.getItemModelsByCategory().getOrDefault(c.getValue(), null) != null)
				.collect(Collectors.toList());
	}

	public List<ItemModel> getItemsForCategory(DestinationModel destination, SelectItem category) {
		return destination.getItemModelsByCategory().getOrDefault(category.getValue(), Collections.emptyList()).stream()
				.sorted(Comparator.comparingLong(ItemModel::getQuantity).reversed()).toList();
	}

	public List<SelectItem> getCategories() {
		return this.categories;
	}

	public String getStyleClass(ItemModel itemModel) {
		return itemModel.getDone() ? "item-done" : "";

	}

	public List<DestinationModel> getDestinationModels() {
		return destinationModels;
	}

	public boolean isShowItemModal() {
		return this.showItemModal;
	}

	public String getNewItemName() {
		return newItemName;
	}

	public void setNewItemName(String newItemName) {
		this.newItemName = newItemName;
	}

	public boolean isShowDestinationModal() {
		return this.showDestinationModal;
	}

	public void setShowDestinationModal(boolean showDestinationModal) {
		this.showDestinationModal = showDestinationModal;
	}

	public String getNewDestinationName() {
		return newDestinationName;
	}

	public void setNewDestinationName(String newDestinationName) {
		this.newDestinationName = newDestinationName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Boolean getShowMessage() {
		return showMessage;
	}

	public void setShowMessage(Boolean showErrorMessage) {
		this.showMessage = showErrorMessage;
	}
}
