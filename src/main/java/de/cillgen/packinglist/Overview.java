package de.cillgen.packinglist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	private Boolean showErrorMessage;

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
		service.updateItem(itemModel);
		this.init();
	}

	public void removeItem(ItemModel itemModel) {
		service.removeItem(itemModel);
		this.init();
	}

	public void addItem() {
		if (this.newItemName != null && this.quantity != null && this.category != null) {
			ItemModel newItem = new ItemModel();
			newItem.setName(this.newItemName);
			newItem.setDone(true);
			newItem.setQuantity(this.quantity);
			newItem.setCategory(this.category);
			service.addItem(service.toItem(newItem, this.destId));

			this.hideItemModal();
			this.init();
		} else {
			this.showErrorMessage = true;
		}
	}

	public void showItemModal(DestinationModel destination) {
		this.showItemModal = true;
		this.destId = destination.getId();
	}

	public void hideItemModal() {
		this.showItemModal = false;
		this.newItemName = null;
		this.quantity = null;
		this.category = null;
		this.showErrorMessage = false;
	}

	public void removeDestination(DestinationModel destinationModel) {
		service.removeDestination(destinationModel);
		this.init();
	}

	public void addDestination() {
		if (this.newDestinationName != null) {
			service.addDestination(service.toDestination(this.newDestinationName));
			this.hideDestinationModal();
			this.init();
		} else {
			this.showErrorMessage = true;
		}
	}

	public void showDestinationModal() {
		this.showDestinationModal = true;
	}

	public void hideDestinationModal() {
		this.showDestinationModal = false;
		this.newDestinationName = null;
		this.showErrorMessage = false;
	}

	private void initCategories() {
		this.categories = Arrays.stream(Category.values())
				.map(c -> new SelectItem(c, c.getTranslation()))
				.collect(Collectors.toList());
		this.categories.add(0, new SelectItem("", ""));
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
		return showDestinationModal;
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

	public Boolean getShowErrorMessage() {
		return showErrorMessage;
	}

	public void setShowErrorMessage(Boolean showErrorMessage) {
		this.showErrorMessage = showErrorMessage;
	}
}
