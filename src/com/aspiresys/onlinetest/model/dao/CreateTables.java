package com.aspiresys.onlinetest.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.aspiresys.onlinetest.logfiles.StoreUserDetail;



public class CreateTables {
	
	public static void main(String ar[])throws IOException, ClassNotFoundException{
		Connection connectionObject = null;
		Statement statementObject = null;
		//create object for the properties class 
		Properties prop = new Properties();
			
				
		try {
			
			//load the properties file
			prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/com/aspiresys/onlinetest/model/dao/DBCredentialsAndTables.properties"));
			
			
			//get the values from the properties file 
			String connectionString = prop.getProperty("connectionString");
			String driverName = prop.getProperty("driverName");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			
			Class.forName(driverName);
			connectionObject = DriverManager.getConnection(connectionString, user, password);
			
			
			//get the values from the properties file
			/*String createDataBase=prop.getProperty("createDataBase");
			statementObject = connectionObject.createStatement();
			statementObject.executeUpdate(createDataBase);
			ConnectionDAO.DBClose(statementObject,
					connectionObject);
			*/
			
			connectionObject = ConnectionDAO.DBConnectionMethod();
			String userTable= prop.getProperty("userTable");
			String organizationTable= prop.getProperty("organizationTable");
			String subjectTable= prop.getProperty("subjectTable");
			String questionTable= prop.getProperty("questionTable");
			String answerTable= prop.getProperty("answerTable");
			String questionAnswerRelationTable= prop.getProperty("questionAnswerRelationTable");
			String usersMarkTable= prop.getProperty("usersMarkTable");
			String adminInsert=prop.getProperty("adminInsert");
			
			
			
			
			statementObject = connectionObject.createStatement();
			statementObject.executeUpdate(organizationTable);
			statementObject.executeUpdate(subjectTable);
			statementObject.executeUpdate(userTable);
			statementObject.executeUpdate(usersMarkTable);
			statementObject.executeUpdate(questionTable);
			statementObject.executeUpdate(answerTable);
			statementObject.executeUpdate(questionAnswerRelationTable);
			statementObject.executeUpdate(adminInsert);
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} catch (FileNotFoundException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} catch (IOException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		}finally {
			try {
				ConnectionDAO.DBClose(statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
	}

}
