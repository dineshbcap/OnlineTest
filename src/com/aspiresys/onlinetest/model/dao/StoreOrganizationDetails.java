package com.aspiresys.onlinetest.model.dao;

public class StoreOrganizationDetails {
	String name,description;
	public StoreOrganizationDetails(String name,String descrption){
		this.name=name;
		this.description=descrption;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
}
