package net.protractor.model;

public class User {

	private String username;
	private String firstName;
	private String lastName;
	private String gender;
	private String password;

	public User(String username, String firstName, String lastName, String gender, String password) {
			this.username = username;
			this.firstName = firstName;
			this.lastName = lastName;
			this.gender = gender;
			this.password = password;
	}

	public User() {

	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
