package com.aspiresys.onlinetest.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aspiresys.onlinetest.logfiles.StoreUserDetail;
import com.aspiresys.onlinetest.model.dao.StoreResult;

public class ManageFilterDAO {
	ManageSubjectDAO subjectDaoObj=new ManageSubjectDAO();
	private Connection connectionObject;
	private PreparedStatement statementObject;
	private ResultSet resultSetObject;
	ArrayList<String> helper=new ArrayList<String>();
	String name,mailId,subjectName,organizationName;
	float mark;
	ArrayList<StoreResult> fullDeatilList=new ArrayList<StoreResult>();
	public ArrayList<StoreResult> getFinalResult(String finalQuery)
			throws ClassNotFoundException, IOException {
		int possition=0;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(finalQuery);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				helper=getUserDetails(resultSetObject.getInt("userId"));
				name=helper.get(0);
				mailId=helper.get(1);
				subjectName=getSubjectName(resultSetObject.getInt("subjectId"));
				organizationName=subjectDaoObj.getOrganizationName(subjectName);
				mark=resultSetObject.getFloat("mark");
				fullDeatilList.add(possition,new StoreResult(name,mailId,subjectName,organizationName,mark));
				possition=possition+1;
				
				
			}

		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject,statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		return fullDeatilList;
	}
	private ArrayList<String> getUserDetails(int userId) throws ClassNotFoundException, IOException{
		
		Connection connectionObject1 = null ;
		PreparedStatement statementObject1 = null;
		ResultSet resultSetObject1 = null;
		int databasePossition = 1;
		ArrayList<String> userDetails=new ArrayList<String>();
		try {
			connectionObject1 = ConnectionDAO.DBConnectionMethod();
			statementObject1 = connectionObject1
					.prepareStatement("select firstName,emailId from user where userId=?");
			statementObject1.setInt(databasePossition,userId);
			resultSetObject1 = statementObject1.executeQuery();
			while (resultSetObject1.next()) {
				userDetails.add(resultSetObject1.getString("firstName"));
				userDetails.add(resultSetObject1.getString("emailId"));
			}

		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject1,statementObject1, connectionObject1);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		return userDetails;
	
	}
	
	
	private String getSubjectName(int subjectId) throws ClassNotFoundException, IOException{
		Connection connectionObject1 = null ;
		PreparedStatement statementObject1 = null;
		ResultSet resultSetObject1 = null;
		int databasePossition = 1;
		String subjectName=null;
		try {
			connectionObject1 = ConnectionDAO.DBConnectionMethod();
			statementObject1 = connectionObject1
					.prepareStatement("select subjectName from Subject where subjectId=?");
			statementObject1.setInt(databasePossition,subjectId);
			resultSetObject1 = statementObject1.executeQuery();
			while (resultSetObject1.next()) {
				subjectName=resultSetObject1.getString("subjectName");
			}

		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject1,statementObject1, connectionObject1);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		return subjectName;
		
	}
}
