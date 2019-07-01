package com.aspiresys.onlinetest.model.dao;

public class StoreUsersDetails {
	String name,lastName,mailId,organizationName;
	long mobileNumber;
	public StoreUsersDetails(String name,String lastName,String mailId,String organizationName,long mobileNumber){
		this.name=name;
		this.lastName=lastName;
		this.mailId=mailId;
		this.organizationName=organizationName;
		this.mobileNumber=mobileNumber;
	}
	public String getName() {
		return name;
	}
	public String getLastName() {
		return lastName;
	}
	public String getMailId() {
		return mailId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	
}
