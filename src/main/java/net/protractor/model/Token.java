package net.protractor.model;

import org.joda.time.DateTime;

public class Token {

	private String email;
	private String token;
	private Long timestamp;

	public Token(String token, String email) {
		this.token = token;
		this.email = email;
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
		return email;
	}

	public void setUsername(String username) {
		this.email = username;
	}
}
