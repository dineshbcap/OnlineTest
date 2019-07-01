package com.aspiresys.onlinetest.model.dao;

public class StoreSubjectDetails {
	String subjectName,organizationName;
	public StoreSubjectDetails(String subjectName,String organizationName){
		this.subjectName=subjectName;
		this.organizationName=organizationName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	
}
