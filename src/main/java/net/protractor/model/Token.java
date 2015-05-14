package net.protractor.model;

import org.joda.time.DateTime;

public class Token {

	private String token;
	private DateTime creationDate;

	public Token(String token) {
		this.token = token;
		this.creationDate = new DateTime();
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
