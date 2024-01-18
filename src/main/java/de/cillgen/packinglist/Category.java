package de.cillgen.packinglist;

enum Category {
	CLOTHING("Kleidung"),
	TOILETRY("Kulturbeutel"),
	PHARMACY("Reiseapotheke"),
	DOCUMENTS("Dokumente"),
	ELECTRONICS("Elektronik"),
	SPORTSGEAR("Ausrüstung"),
	MISCELLANEOUS("Sonstiges");

	private final String translation;

	private Category(String translation) {
		this.translation = translation;
	}

	public String getTranslation() {
		return translation;
	}
}