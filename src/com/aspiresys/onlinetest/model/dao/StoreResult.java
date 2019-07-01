package com.aspiresys.onlinetest.model.dao;

public class StoreResult {
	String name,mailId,subjectName,organizationName;
	float mark;
	public StoreResult(String name,String mailId,String subjectName,String organizationName,float mark) {
		this.name=name;
		this.mailId=mailId;
		this.subjectName=subjectName;
		this.organizationName=organizationName;
		this.mark=mark;
	}
	public String getName() {
		return name;
	}
	public String getMailId() {
		return mailId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public float getMark() {
		return mark;
	}
	
}
