package de.leuphana.cosa.documentsystem.structure;

public class Document  {
	private static Integer lastId = 0;
	
	private Integer id;
	// TODO delete because of name parameter
	private String title;
	private String text;

	public Document(String titel) {
		this.title = titel;
		id = ++lastId;
	}

	public Integer getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


}
