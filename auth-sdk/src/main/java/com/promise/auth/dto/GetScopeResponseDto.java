package com.promise.auth.dto;

import java.util.ArrayList;

public class GetScopeResponseDto {
	
	private String id;
	private String name;
	private String description;
	private ArrayList<AccessPointDto> accessPointList;
	
	public GetScopeResponseDto() {
		
	}

	
	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}


	public ArrayList<AccessPointDto> getAccessPointList() {
		return accessPointList;
	}


	public void setAccessPointList(ArrayList<AccessPointDto> accessPointList) {
		this.accessPointList = accessPointList;
	}

}
