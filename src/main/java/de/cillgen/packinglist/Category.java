package de.cillgen.packinglist;

enum Category {
	CLOTHING("Kleidung"),
	TOILETRY("Kulturbeutel"),
	SPORTSGEAR("Ausrüstung"),
	ELECTRONICS("Elektronik"),
	PHARMACY("Reiseapotheke"),
	DOCUMENTS("Dokumente"),
	MISCELLANEOUS("Sonstiges");

	private final String translation;

	private Category(String translation) {
		this.translation = translation;
	}

	public String getTranslation() {
		return translation;
	}
}