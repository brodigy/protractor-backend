package net.protractor.model;

public final class Fault {

	private String code;
	private String message;

	public Fault() {
	}

	public Fault(String code, String message) {
		this.code = code;
		this.message = message;
	}

}