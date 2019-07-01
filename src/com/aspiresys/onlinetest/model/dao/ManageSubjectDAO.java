package com.aspiresys.onlinetest.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aspiresys.onlinetest.hardcode.HardCodeValues;
import com.aspiresys.onlinetest.logfiles.StoreUserDetail;
import com.aspiresys.onlinetest.model.beanclass.CommonBeanClass;

public class ManageSubjectDAO {
	ManageOrganizationDAO organizationObject=new ManageOrganizationDAO();
	private Connection connectionObject;
	private PreparedStatement statementObject;
	private ResultSet resultSetObject;
	private final String INSERT_SUBJECT = "insert into Subject(subjectName,organizationId) values(?,?)";
	private final String UPDATE_SUBJECT = "update Subject set subjectName=?,organizationId=? where subjectName=? ";
	//private final String DELETE_SUBJECT = "DELETE Subject,Answer,QuestionAnswerRelation,Question,UserMark FROM Subject LEFT JOIN Answer ON Subject.subjectId = "
			//+ " Answer.subjectID LEFT JOIN QuestionAnswerRelation  ON Answer.subjectId = QuestionAnswerRelation.subjectId LEFT JOIN Question  ON Subject.subjectId = Question.subjectId LEFT JOIN UserMark  ON Subject.subjectId = UserMark.subjectId  WHERE Subject.subjectID =?";
	private final String DELETE_SUBJECT = "DELETE from subject  WHERE subjectID =?";
	private final String GET_LIST_OF_SUBJECT = "select subjectName from Subject";
	private final String GET_LIST_OF_SUBJECT_FOR_USER = "select subjectName from Subject where organizationId=?";
	public ArrayList<String> listOfSubject;
	public ArrayList<String> listOfSubjectForSelectedUser;
	private int databasePossition;

	/**
	 * To add new subject
	 * 
	 * @param beanObject
	 *            contains subjectName and organizationId
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void addSubject(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject.prepareStatement(INSERT_SUBJECT);
			statementObject.setString(databasePossition,
					beanObject.getSubjectName());
			statementObject.setInt(++databasePossition,
					beanObject.getOrganizationId());
			System.out.println(beanObject.getOrganizationId());
			statementObject.executeUpdate();

		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
	}

	/**
	 * To update subject
	 * 
	 * @param beanObject
	 *            contains old name and new name of the subject
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void updateSubject(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject.prepareStatement(UPDATE_SUBJECT);
			statementObject.setString(databasePossition,
					beanObject.getNewSubjectName());
			statementObject.setInt(++databasePossition,
					beanObject.getOrganizationId());
			statementObject.setString(++databasePossition,
					beanObject.getSubjectName());
			statementObject.executeUpdate();
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
	}

	/**
	 * To get list of subject name
	 * 
	 * @return ListOfSubjectName
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<String> getListOfSubjectName()
			throws ClassNotFoundException, IOException {
		listOfSubject = new ArrayList<String>();
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_LIST_OF_SUBJECT);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				listOfSubject.add(resultSetObject
						.getString(HardCodeValues.SUBJECTNAME));
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		}
		finally {
			try {
				ConnectionDAO.DBClose(resultSetObject,statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		// return the arraylist to the calling method
		return listOfSubject;
	}

	/**
	 * This method is used for get the organization name based on subject name
	 * 
	 * @param subjectName
	 * @return OrganizationName
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String getOrganizationName(String subjectName)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		String organizationName = "";
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement("select Organization.organizationName from Organization,Subject where Organization.organizationId=(select Subject.organizationId from Subject where Subject.subjectName=?)");
			statementObject.setString(databasePossition, subjectName);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				organizationName = resultSetObject
						.getString("Organization.organizationName");
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
		return organizationName;
	}

	/**
	 * this method is used for get the prganization id based on the organization name selected possiton
	 * @param possition of the selected organization
	 * @return organizationId for selected organization
	 * @throws ClassNotFoundException
	 *//*
	public int getOrganizationId(int possition) throws ClassNotFoundException{
		boolean booleanVariable=true;
		int orgId=0;
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement("select organizationId from organization");
			resultSetObject = statementObject.executeQuery();
			while ((resultSetObject.absolute(possition) && (booleanVariable))){
				booleanVariable=false;
				orgId=resultSetObject.getInt("organizationId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return orgId;
		
	}*/
	/**
	 * This method is used for get the list of subject name based on
	 * organization id
	 * 
	 * @return ListOfSubjectName
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<String> getListOfSubjectName(int organizationId)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		listOfSubjectForSelectedUser = new ArrayList<String>();
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_LIST_OF_SUBJECT_FOR_USER);
			statementObject.setInt(databasePossition, organizationId);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				listOfSubjectForSelectedUser.add(resultSetObject
						.getString(HardCodeValues.SUBJECTNAME));
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		}
		finally {
			try {
				ConnectionDAO.DBClose(resultSetObject,statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		// return the arraylist to the calling method
		return listOfSubjectForSelectedUser;
	}

	/**
	 * To delete subject name
	 * 
	 * @param beanObject
	 *            contains subjectName
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void deleteSubject(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject.prepareStatement(DELETE_SUBJECT);
			statementObject
					.setInt(databasePossition, beanObject.getSubjectId());
			statementObject.executeUpdate();
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
	}
	
	/**
	 * get full list of subjects
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<StoreSubjectDetails> getSubjectDatas() throws ClassNotFoundException, IOException{
		
		int index = 0;
		String name,organizationName;
		ArrayList<StoreSubjectDetails> subjectDetail=new ArrayList<StoreSubjectDetails>();
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement("select subjectName,organizationId from Subject");
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				name=resultSetObject.getString("subjectName") ;
				organizationName=organizationObject.getOrganizationName(resultSetObject.getInt("organizationId"));
				subjectDetail.add(index,new StoreSubjectDetails(name, organizationName));
				index=index+1;
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
		return subjectDetail;
		
	}	
}
