package com.aspiresys.onlinetest.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.aspiresys.onlinetest.hardcode.HardCodeValues;
import com.aspiresys.onlinetest.logfiles.StoreUserDetail;
import com.aspiresys.onlinetest.model.beanclass.CommonBeanClass;

public class ManageUserDAO {
	ManageOrganizationDAO organizationObject = new ManageOrganizationDAO();
	private Connection connectionObject;
	private PreparedStatement statementObject;
	private ResultSet resultSetObject;
	private PreparedStatement statementObject1;
	private ResultSet resultSetObject1;
	private PreparedStatement statementObject2;
	public ArrayList<Object> getUserDetails;
	public ArrayList<String> listOfUser;
	private final String ADD_USER_DETAIL = "insert into User(firstName,lastName,emailId,mobileNumber,userName,PassWord,organizationId,isAdmin) values(?,?,?,?,?,?,?,?)";
	private final String GET_LIST_OF_ORGANIZATION_ID = "select organizationId from Organization where organizationName=?";
	private final String GET_ADMIN_OR_USER_DETAILS = "select * from User where userName=?";
	private final String UPDATE_ADMIN_OR_USER_DETAILS = "update User set firstName=?,lastName=?,emailId=?,mobileNumber=?,passWord=?,organizationId=?,isAdmin=? where userName=?";
	private final String QUERY = "select userName from User where isAdmin=?";
	private final String GET_USER_ID = "select * from User where firstName=?";
	// private final String DELETE_USER_DETAILS =
	// "delete User,UserMark,QuestionAnswerRelation from User LEFT JOIN UserMark ON User.userId=UserMark.userId LEFT JOIN QuestionAnswerRelation ON User.userId=QuestionAnswerRelation.userId where User.userId=?";
	private final String DELETE_USER_DETAILS = "delete from user where userId=?";
	private final String INSERT_USER_ANSWER_REL_TABLE = "insert into QuestionAnswerRelation(userId,subjectId,questionId,answerId,rightOrWrong) values(?,?,?,?,?)";
	private final String UPDATE_USER_ANSWER_REL_TABLE = "update QuestionAnswerRelation set rightOrWrong=? where relationId=? ";
	private final String GET_Question_ID = "select questionId from Question where subjectId=?";
	private final String GET_RELATION_ID = "select relationId from QuestionAnswerRelation where questionId=?";
	private final String GET_ORGANIZATION_NAME = "select organizationName from Organization where organizationId=?";
	private final String GET_ADMIN_ANSWER = "select Answer.answerId,Answer.rightOrWrong from Answer,QuestionAnswerRelation where (QuestionAnswerRelation.userId=? and Answer.subjectId=?)  GROUP BY(Answer.answerId)";
	private final String GET_USER_ANSWER = "select QuestionAnswerRelation.answerId,QuestionAnswerRelation.rightOrWrong from Answer,QuestionAnswerRelation where (QuestionAnswerRelation.userId=? and QuestionAnswerRelation.subjectId=?) GROUP BY(QuestionAnswerRelation.answerId)";
	private final String IS_USER_ANSWERED = "select relationId from QuestionAnswerRelation where QuestionAnswerRelation.userId=? and QuestionAnswerRelation.subjectId=?";
	private final String INSERT_USER_MARK = "insert into UserMark(userId,subjectId,mark,dateOfWrite) values(?,?,?,?)";
	private int databasePossition;

	/**
	 * add admin/user details
	 * 
	 * @param beanObject
	 *            contains name,emailId,mobileNumber,userUserName,userPassWord,
	 *            organizationId and isAdmin
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void addUserDetails(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(ADD_USER_DETAIL);
			statementObject.setString(databasePossition, beanObject.getName());
			statementObject.setString(++databasePossition,
					beanObject.getLastName());
			statementObject.setString(++databasePossition,
					beanObject.getEmailId());
			statementObject.setLong(++databasePossition,
					beanObject.getMobileNumber());
			statementObject.setString(++databasePossition,
					beanObject.getUserName());
			statementObject.setString(++databasePossition,
					beanObject.getPassWord());
			statementObject.setInt(++databasePossition,
					beanObject.getOrganizationId());
			statementObject
					.setInt(++databasePossition, beanObject.getIsAdmin());
			statementObject.executeUpdate();
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
	}

	/**
	 * to get organizationId
	 * 
	 * @param name
	 * @return organizationId
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public int getOrganizationId(String name) throws ClassNotFoundException,
			IOException {
		databasePossition = 1;
		// decalre local variables
		int organizationId = 0;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_LIST_OF_ORGANIZATION_ID);
			statementObject.setString(databasePossition, name);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				organizationId = resultSetObject.getInt("organizationId");
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
		// return the arraylist to the calling method
		return organizationId;
	}

	/**
	 * to get organization name based on organization id
	 * 
	 * @param organizationId
	 * 
	 * @return organization name
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String getOrganizationName(int organizationId)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		// decalre local variables
		String organizationName = "";
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_ORGANIZATION_NAME);
			statementObject.setInt(databasePossition, organizationId);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				organizationName = resultSetObject
						.getString("organizationName");
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
		// return the arraylist to the calling method
		return organizationName;
	}

	/**
	 * get the list of admin or user name
	 * 
	 * @param testValue
	 * @return listOfUser
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getListOfUser(int testValue)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		listOfUser = new ArrayList<String>();
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			// String
			// query="select * from "+tableName+" where "+testString+"="+testValue;
			statementObject = connectionObject.prepareStatement(QUERY);

			statementObject.setInt(databasePossition, testValue);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				listOfUser.add(resultSetObject.getString("userName"));
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
		// return the arraylist to the calling method
		return listOfUser;
	}

	/**
	 * get the detail for admin or user
	 * 
	 * @param name2
	 * @return getUserDetails
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Object> getListOfUserDetails(String name2)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		// decalre local variables
		getUserDetails = new ArrayList<Object>();
		String name1,lastName, emailId, userName, passWord;
		int organizationId;
		Long mobileNumber;
		Boolean isAdmin;
		int arrayListPossition = 0;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_ADMIN_OR_USER_DETAILS);
			statementObject.setString(databasePossition, name2);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				name1 = resultSetObject.getString("firstName");
				lastName=resultSetObject.getString("lastName");
				emailId = resultSetObject.getString(HardCodeValues.EMAILID);
				mobileNumber = resultSetObject
						.getLong(HardCodeValues.MOBILE_NUMBER);
				userName = resultSetObject.getString(HardCodeValues.USER_NAME);
				passWord = resultSetObject.getString(HardCodeValues.PASSWORD);
				organizationId = resultSetObject
						.getInt(HardCodeValues.ORGANIZATION_ID);
				isAdmin = resultSetObject.getBoolean(HardCodeValues.IS_ADMIN);
				getUserDetails.add(arrayListPossition, name1);
				getUserDetails.add(++arrayListPossition, lastName);
				getUserDetails.add(++arrayListPossition, emailId);
				getUserDetails.add(++arrayListPossition, (Long) mobileNumber);
				getUserDetails.add(++arrayListPossition, userName);
				getUserDetails.add(++arrayListPossition, passWord);
				getUserDetails.add(++arrayListPossition, organizationId);
				getUserDetails.add(++arrayListPossition, (Boolean) isAdmin);
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
		// return the arraylist to the calling method
		return getUserDetails;
	}

	/**
	 * update admin or user details
	 * 
	 * @param beanObject
	 *            contains name,emailId,mobileNumber,userUserName,userPassWord,
	 *            organizationId and isAdmin
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void updateUserDetails(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(UPDATE_ADMIN_OR_USER_DETAILS);
			statementObject.setString(databasePossition,
					beanObject.getUserName());
			statementObject.setString(++databasePossition,
					beanObject.getLastName());
			statementObject.setString(++databasePossition,
					beanObject.getEmailId());
			statementObject.setLong(++databasePossition,
					beanObject.getMobileNumber());
			
			statementObject.setString(++databasePossition,
					beanObject.getPassWord());
			statementObject.setInt(++databasePossition,
					beanObject.getOrganizationId());
			statementObject
					.setInt(++databasePossition, beanObject.getIsAdmin());
			statementObject
					.setString(++databasePossition, beanObject.getName());
			statementObject.executeUpdate();
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
	}

	/**
	 * This method is used for delete user basesd on user ID
	 * 
	 * @param userId
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void deleteUser(int userId) throws ClassNotFoundException,
			IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(DELETE_USER_DETAILS);
			statementObject.setInt(databasePossition, userId);
			statementObject.executeUpdate();
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
	}

	/**
	 * This method is used for get user Id user name
	 * 
	 * @param beanObject
	 * @return UserId
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public int getUserId(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		int userId = 0;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject.prepareStatement(GET_USER_ID);
			statementObject.setString(databasePossition,
					beanObject.getUserName());
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				userId = resultSetObject.getInt("userId");
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
		return userId;
	}

	/**
	 * This method is used for insert answer table for the user
	 * 
	 * @param beanObject
	 * @return booleanValue for confirmation
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public int insertUserAnswerTable(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		int arrayListPossition = 0;
		int booleanValue = 0;
		// int numberOfQuestions=beanObject.getNumberOfQuestions();
		// int answerId=beanObject.getAnswerId();
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(IS_USER_ANSWERED);
			statementObject.setInt(databasePossition, beanObject.getUserId());
			statementObject.setInt(++databasePossition,
					beanObject.getSubjectId());
			resultSetObject = statementObject.executeQuery();
			if (resultSetObject.next()) {
				booleanValue = 1;
			}
			statementObject.close();
			resultSetObject.close();
			if (booleanValue == 0) {
				databasePossition = 1;

				statementObject = connectionObject
						.prepareStatement(INSERT_USER_ANSWER_REL_TABLE);
				int numberOfQuestionId = beanObject.getListOfQuestionId()
						.size();
				for (int questionNumber = 1; questionNumber <= numberOfQuestionId; questionNumber++) {
					int answerId = beanObject.getListOfAnswerId().get(
							arrayListPossition);
					for (int i = 0; i < 4; i++) {
						statementObject.setInt(databasePossition,
								beanObject.getUserId());
						statementObject.setInt(++databasePossition,
								beanObject.getSubjectId());
						statementObject.setInt(++databasePossition, beanObject
								.getListOfQuestionId().get(arrayListPossition));
						statementObject.setInt(++databasePossition, answerId
								+ i);
						statementObject.setInt(++databasePossition, 0);
						statementObject.executeUpdate();
						databasePossition = 1;
					}
					arrayListPossition = arrayListPossition + 1;
				}
			}

		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
		return booleanValue;
	}

	/**
	 * This method is used for update the user answer relation
	 * 
	 * @param beanObject
	 *            contains user answers and relationId
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void updateQuewstionAnswerRelation(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		int relationId = beanObject.getRelationId();
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(UPDATE_USER_ANSWER_REL_TABLE);
			for (int answer = 0; answer < 4; answer++) {
				statementObject.setInt(databasePossition, beanObject
						.getAnswer().get(answer));
				statementObject.setInt(++databasePossition, relationId);
				statementObject.executeUpdate();
				databasePossition = 1;
				relationId = relationId + 1;
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
	}

	/**
	 * This method return list of question id based on subject id
	 * 
	 * @param beanObject
	 * @return listOfQuestionId
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Integer> getQuestionId(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		ArrayList<Integer> listOfQuestionId = new ArrayList<Integer>();
		int questionId = 0;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_Question_ID);
			statementObject
					.setInt(databasePossition, beanObject.getSubjectId());
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				listOfQuestionId.add(questionId++,
						resultSetObject.getInt("questionId"));
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
		return listOfQuestionId;
	}

	/**
	 * This method is used for get the relation Id based on question id
	 * 
	 * @param questionId
	 * @return RelationId
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public int getRelationId(int questionId) throws ClassNotFoundException,
			IOException {
		databasePossition = 1;
		int relationId = 0;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_RELATION_ID);
			statementObject.setInt(databasePossition, questionId);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				relationId = resultSetObject.getInt("relationId");
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
		return relationId;
	}

	/**
	 * This method is used for calculate the mark of the user
	 * 
	 * @param beanObject
	 * @return mark
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public double calculateMark(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		Properties prop = new Properties();

		try {
			// load the properties file
			prop.load(new FileInputStream(
					"C:/Users/acer/Desktop/OnlineTest/OnlineTest/src/com/aspiresys/onlinetest/controller/Configuration.properties"));
		} catch (FileNotFoundException e) {
			StoreUserDetail
					.storeUserInfo("Property File Error Occured--->" + e);
		} catch (IOException e) {
			StoreUserDetail
					.storeUserInfo("Property File Error Occured--->" + e);
		}
		// get the values from the properties file
		float possitveMark = Float
				.parseFloat(prop.getProperty("possitiveMark"));
		float negativeMark = Float.parseFloat(prop.getProperty("negativeMark"));
		databasePossition = 1;
		float count = 0;
		HashMap<Integer, Integer> adminAnswer = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> userAnswer = new HashMap<Integer, Integer>();
		ArrayList<Integer> listAnswerID = new ArrayList<Integer>();
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_ADMIN_ANSWER);
			statementObject.setInt(databasePossition, beanObject.getUserId());
			statementObject.setInt(++databasePossition,
					beanObject.getSubjectId());
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				adminAnswer.put(resultSetObject.getInt("Answer.answerId"),
						resultSetObject.getInt("Answer.rightOrWrong"));
			}
			resultSetObject.close();
			statementObject.close();

			databasePossition = 1;
			statementObject1 = connectionObject
					.prepareStatement(GET_USER_ANSWER);
			statementObject1.setInt(databasePossition, beanObject.getUserId());
			statementObject1.setInt(++databasePossition,
					beanObject.getSubjectId());
			resultSetObject1 = statementObject1.executeQuery();
			while (resultSetObject1.next()) {
				userAnswer.put(resultSetObject1
						.getInt("QuestionAnswerRelation.answerId"),
						resultSetObject1
								.getInt("QuestionAnswerRelation.rightOrWrong"));
				listAnswerID.add(resultSetObject1
						.getInt("QuestionAnswerRelation.answerId"));
			}
			resultSetObject1.close();
			statementObject1.close();
			for (int i = 0; i < adminAnswer.size(); i++) {
				if (userAnswer.get(listAnswerID.get(i)) == 1) {
					if ((adminAnswer.get(listAnswerID.get(i))) == (userAnswer
							.get(listAnswerID.get(i)))) {
						count = count + possitveMark;
					} else {
						count = count - negativeMark;
					}
				}
			}

			Date currentDatetime = new Date(System.currentTimeMillis());
			java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
			databasePossition = 1;
			statementObject2 = connectionObject
					.prepareStatement(INSERT_USER_MARK);
			statementObject2.setInt(databasePossition, beanObject.getUserId());
			statementObject2.setInt(++databasePossition,
					beanObject.getSubjectId());
			statementObject2.setFloat(++databasePossition, count);
			statementObject2.setDate(++databasePossition, sqlDate);
			statementObject2.executeUpdate();
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(statementObject2, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
		return count;
	}

	public ArrayList<StoreUsersDetails> getUserDatas()
			throws ClassNotFoundException, IOException {

		int index = 0;
		String name,lastName, mailId, organizationName;
		long mobileNumber;
		ArrayList<StoreUsersDetails> userDetail = new ArrayList<StoreUsersDetails>();
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement("select firstName,lastName,emailId,mobileNumber,organizationId from user");
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				name = resultSetObject.getString("firstName");
				lastName = resultSetObject.getString("lastName");
				mailId = resultSetObject.getString("emailId");
				mobileNumber = Long.parseLong(resultSetObject
						.getString("mobileNumber"));
				organizationName = organizationObject
						.getOrganizationName(resultSetObject
								.getInt("organizationId"));
				if (organizationName != "") {
				} else {
					organizationName = "Admin Rights!";
				}
				userDetail.add(index, new StoreUsersDetails(name,lastName, mailId,
						organizationName, mobileNumber));
				index = index + 1;
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->" + e);
			}
		}
		return userDetail;

	}
}
