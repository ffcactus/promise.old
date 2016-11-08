package com.promise.auth.dto;

import java.util.ArrayList;

public class CreateScopeRequestDto {
	
	private String name;
	private String description;

	private ArrayList<AccessPointDto> accessPointList;

	public CreateScopeRequestDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<AccessPointDto> getAccessPointList() {
		return accessPointList;
	}

	public void setAccessPointList(ArrayList<AccessPointDto> accessPointList) {
		this.accessPointList = accessPointList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
