package net.protractor.model;

public class Post {

	private String title;
	private String message;
	private String author;

	public Post(String title, String message, String author) {
		this.title = title;
		this.message = message;
		this.author = author;
	}

	public Post() {}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
