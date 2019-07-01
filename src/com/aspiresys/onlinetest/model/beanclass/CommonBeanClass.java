package com.aspiresys.onlinetest.model.beanclass;

import java.util.ArrayList;

//this class consist of setter and getter methods 
public class CommonBeanClass {
	private String name;
	private String lastName;
	private String emailId;
	private Long mobileNumber;
	private String userName;
	private String passWord;
	private int organizationId;
	private int isAdmin;

	private String subjectName;

	private String organizationName;
	private String description;

	private String newSubjectName;
	private String newOrganizationName;

	private int subjectId;
	private String question;
	private ArrayList<String> option = new ArrayList<String>();
	private ArrayList<Integer> answer = new ArrayList<Integer>();
	private int questionId;
	private int answerId;
	private int numberOfQuestions;
	private int userId;
	private int relationId;
	private ArrayList<Integer> listOfQuestionId = new ArrayList<Integer>();
	private ArrayList<Integer> listOfAnswerId = new ArrayList<Integer>();;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNewSubjectName() {
		return newSubjectName;
	}

	public void setNewSubjectName(String newSubjectName) {
		this.newSubjectName = newSubjectName;
	}

	public String getNewOrganizationName() {
		return newOrganizationName;
	}

	public void setNewOrganizationName(String newOrganizationName) {
		this.newOrganizationName = newOrganizationName;
	}

	public ArrayList<String> getOption() {
		return option;
	}

	public void setOption(ArrayList<String> option) {
		this.option = option;
	}

	public ArrayList<Integer> getAnswer() {
		return answer;
	}

	public void setAnswer(ArrayList<Integer> answer) {
		this.answer = answer;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ArrayList<Integer> getListOfQuestionId() {
		return listOfQuestionId;
	}

	public void setListOfQuestionId(ArrayList<Integer> listOfQuestionId) {
		this.listOfQuestionId = listOfQuestionId;
	}

	public int getRelationId() {
		return relationId;
	}

	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public ArrayList<Integer> getListOfAnswerId() {
		return listOfAnswerId;
	}

	public void setListOfAnswerId(ArrayList<Integer> listOfAnswerId) {
		this.listOfAnswerId = listOfAnswerId;
	}

}
