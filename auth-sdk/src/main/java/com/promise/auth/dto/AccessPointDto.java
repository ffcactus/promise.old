package com.promise.auth.dto;

/**
 * AccessPoint aggregates the scope object. <br>
 * Examples:<br>
 * { type: "uri", value: "/rest/user" }<br>
 * { type: "scope", value: "/rest/scope/xxxx" }
 *
 */
public class AccessPointDto {

	private String type;
	private String value;

	public AccessPointDto() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
