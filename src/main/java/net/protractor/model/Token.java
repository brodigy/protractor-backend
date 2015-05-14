package net.protractor.model;

import org.joda.time.DateTime;

public class Token {

	private String username;
	private String token;
	private Long timestamp;

	public Token(String token, String username) {
		this.token = token;
		this.username = username;
		this.timestamp = new DateTime().getMillis();
	}

	public Long getCreationDate() {
		return timestamp;
	}

	public void setCreationDate(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
