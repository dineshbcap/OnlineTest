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

public class ManageQuestionsDAO {
	private Connection connectionObject;
	private PreparedStatement statementObject;
	private PreparedStatement statementObject1;
	private ResultSet resultSetObject;
	public ArrayList<Object> getQuestionAndQuestionId;
	public ArrayList<Object> getOptionAndAnswer;
	private final String ADD_QUESTION = "insert into Question(subjectId,question) values(?,?)";
	private final String ADD_OPTION_ANSWER = "insert into Answer(subjectId,answerDescription,questionId,rightOrWrong) values(?,?,?,?)";
	private final String UPDATE_QUESTION = "update Question set question=? where questionId=?";
	private final String UPDATE_OPTION_ANSWER = "update Answer set answerDescription=?,rightOrWrong=? where answerId=?";
	private final String GET_ANSWER_OPTION = "select * from Answer where questionId=?";
	private final String GET_QUESTION_QUESTIONID = "select * from Question where subjectId=?";
	private final String GET_MAXIMUM_QUESTIONID = "select max(questionId) from Question";
	private final String GET_NUMBER_OF_QUESTION_IN_SUBJECT = "select count(*) from Question where subjectId=?";
	private final String GET_SUBJECTID_FOR_SELECTED_SUBJECT = "select subjectId from Subject where subjectName=?";
	private final String GET_ANSWER_ID = "select answerId from Answer where questionId=?";
	private final String GET_FIRST_ANSWER_ID = "select answerId from Answer where questionId=?";
	//private final String DELETE_QUESTION = "DELETE Question,Answer,QuestionAnswerRelation FROM Question LEFT JOIN Answer ON Question.questionId = Answer.questionId"
			//+ " LEFT JOIN QuestionAnswerRelation  ON Answer.questionId = QuestionAnswerRelation.questionId WHERE Question.questionId =?";
	private final String DELETE_QUESTION = "DELETE from question WHERE questionId =?";
	private final String USER_ANS = "select rightOrWrong from QuestionAnswerRelation where answerId=?";
	private final String ADMIN_ANS = "select rightOrWrong from Answer where answerId=?";
	private int databasePossition = 1;

	/**
	 * To add question
	 * 
	 * @param beanObjectQuestion
	 *            contains subjectId and question
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void addQuestion(CommonBeanClass beanObjectQuestion)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject.prepareStatement(ADD_QUESTION);
			statementObject.setInt(databasePossition,
					beanObjectQuestion.getSubjectId());
			statementObject.setString(++databasePossition,
					beanObjectQuestion.getQuestion());
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
	 * To add option and answer
	 * 
	 * @param beanObject
	 *            contains answerDescription,questionId and rightOrWrong
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void addQuestionAnswer(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(ADD_OPTION_ANSWER);
			for (int arrayListPossition = 0; arrayListPossition < 4; arrayListPossition++) {
				statementObject.setInt(databasePossition,
						beanObject.getSubjectId());
				statementObject.setString(++databasePossition, beanObject
						.getOption().get(arrayListPossition));
				statementObject.setInt(++databasePossition,
						beanObject.getQuestionId());
				statementObject.setInt(++databasePossition, beanObject
						.getAnswer().get(arrayListPossition));
				statementObject.executeUpdate();
				databasePossition = 1;
			}

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
	 * To get subject Id for the coming subject name
	 * 
	 * @param subjectName
	 * @return SubjectId
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public int getSubjectId(String subjectName) throws ClassNotFoundException, IOException {
		int subjectId = 0;
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_SUBJECTID_FOR_SELECTED_SUBJECT);
			statementObject.setString(databasePossition, subjectName);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				subjectId = resultSetObject.getInt(databasePossition);
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		// return the arraylist to the calling method
		return subjectId;
	}

	/**
	 * return the question number for the coming subjectid
	 * 
	 * @param subjectId
	 * @return QuestionNumber
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public int getQuestionNumber(int subjectId) throws ClassNotFoundException, IOException {
		int questionNumber = 0;
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_NUMBER_OF_QUESTION_IN_SUBJECT);
			statementObject.setInt(databasePossition, subjectId);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				questionNumber = resultSetObject.getInt(databasePossition);
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		// return the arraylist to the calling method
		return questionNumber;
	}

	/**
	 * return the maximum question number
	 * 
	 * @return QuestionNumber
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public int getQuestionNumber() throws ClassNotFoundException, IOException {
		int questionNumber = 1;
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_MAXIMUM_QUESTIONID);

			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				questionNumber = resultSetObject.getInt(databasePossition);
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		return questionNumber;
	}

	/**
	 * To get the answer id for coming question id
	 * 
	 * @param questionId
	 * @return AnswerId
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public int getAnswerId(int questionId) throws ClassNotFoundException, IOException {
		int answerId = 0;
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject.prepareStatement(GET_ANSWER_ID);
			statementObject.setInt(databasePossition, questionId);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				answerId = resultSetObject.getInt(databasePossition);
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		// return the arraylist to the calling method
		return answerId;
	}

	/**
	 * To get the question
	 * 
	 * @param questionNumber
	 * @param subjectId
	 * @return getQuestions
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Object> getQuestion(int questionNumber, int subjectId,
			int numberOfQuestion) throws ClassNotFoundException, IOException {
		if (questionNumber == 0) {
			questionNumber = 1;
		}
		if (questionNumber >= numberOfQuestion) {
			questionNumber = numberOfQuestion;
		}
		getQuestionAndQuestionId = new ArrayList<Object>();
		databasePossition = 1;
		int arrayListPossition = 0;
		String question = "";
		int questionId;
		Boolean booleanVariable = true;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_QUESTION_QUESTIONID);
			statementObject.setInt(databasePossition, subjectId);
			resultSetObject = statementObject.executeQuery();
			while ((resultSetObject.absolute(questionNumber))
					&& (booleanVariable)) {
				questionId = resultSetObject.getInt(HardCodeValues.QUESTION_ID);
				question = resultSetObject.getString(HardCodeValues.QUESTION);
				getQuestionAndQuestionId.add(arrayListPossition, questionId);
				getQuestionAndQuestionId.add(++arrayListPossition, question);
				booleanVariable = false;
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		// return the arraylist to the calling method
		return getQuestionAndQuestionId;
	}

	/**
	 * To get the option and answer
	 * 
	 * @param questionId
	 * @return getAnswer
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<Object> getAnswer(int questionId)
			throws ClassNotFoundException, IOException {
		getOptionAndAnswer = new ArrayList<Object>();
		databasePossition = 1;
		String option;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_ANSWER_OPTION);
			statementObject.setInt(databasePossition, questionId);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				option = resultSetObject.getString("answerDescription");
				getOptionAndAnswer.add(option);
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		// return the arraylist to the calling method
		return getOptionAndAnswer;
	}

	/**
	 * To update question
	 * 
	 * @param beanObjectQuestion
	 *            contains question and questionId
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void updateQuestion(CommonBeanClass beanObjectQuestion)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(UPDATE_QUESTION);
			statementObject.setString(databasePossition,
					beanObjectQuestion.getQuestion());
			statementObject.setInt(++databasePossition,
					beanObjectQuestion.getQuestionId());
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
	 * To update option and answer
	 * 
	 * @param beanObject
	 *            option and answerId
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void updateOptionAndAnswer(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			int answerId = beanObject.getAnswerId();
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(UPDATE_OPTION_ANSWER);
			for (int arrayListPossition = 0; arrayListPossition < 4; arrayListPossition++) {
				databasePossition = 1;
				statementObject.setString(databasePossition, beanObject
						.getOption().get(arrayListPossition));
				statementObject.setInt(++databasePossition, beanObject
						.getAnswer().get(arrayListPossition));
				statementObject.setInt(++databasePossition, answerId);
				statementObject.executeUpdate();
				answerId = answerId + 1;
			}
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
	 * This method is used for delete the questions
	 * 
	 * @param questionNumbers
	 * @param subjectId
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void deleteQuestion(ArrayList<Integer> questionNumbers, int subjectId)
			throws ClassNotFoundException, IOException {
		System.out.println("2");
		databasePossition = 1;
		try {
			int size = questionNumbers.size();
			int questionId;
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_QUESTION_QUESTIONID);
			statementObject.setInt(databasePossition, subjectId);
			resultSetObject = statementObject.executeQuery();
			boolean booleanVariable = true;
			for (int arrayListPossition = 0; arrayListPossition < size; arrayListPossition++) {
				booleanVariable = true;
				while ((resultSetObject.absolute(questionNumbers
						.get(arrayListPossition))) && (booleanVariable)) {
					questionId = resultSetObject
							.getInt(HardCodeValues.QUESTION_ID);
					statementObject1 = connectionObject
							.prepareStatement(DELETE_QUESTION);
					statementObject1.setInt(1, questionId);
					statementObject1.executeUpdate();
					booleanVariable = false;
				}
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				statementObject.close();
				statementObject1.close();
				connectionObject.close();
				// DBConnection.DBClose(resultSetObject,statementObject,connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
	}

	/**
	 * This method is used for get the first answer id based on subject id
	 * 
	 * @param subjectId
	 * @return AnswerId
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public int getFirstAnswerId(int subjectId) throws ClassNotFoundException, IOException {
		int answerId = 0;
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(GET_FIRST_ANSWER_ID);
			statementObject.setInt(databasePossition, subjectId);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				answerId = resultSetObject.getInt(databasePossition);
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(statementObject, connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		// return the arraylist to the calling method
		return answerId;
	}

	/**
	 * This method is used for get the user answer based on answer Id
	 * 
	 * @param answerId
	 * @param tableName
	 * @return user answer
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public int getUserAnswer(int answerId, String tableName)
			throws ClassNotFoundException, IOException {
		int rightOrWrong = 0;
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			if (tableName.equals("QuestionAnswerRelation")) {
				statementObject = connectionObject.prepareStatement(USER_ANS);
				statementObject.setInt(databasePossition, answerId);
				resultSetObject = statementObject.executeQuery();
				while (resultSetObject.next()) {
					rightOrWrong = resultSetObject.getInt(databasePossition);
				}
			} else if (tableName.equals("Answer")) {
				statementObject = connectionObject.prepareStatement(ADMIN_ANS);

				statementObject.setInt(databasePossition, answerId);
				resultSetObject = statementObject.executeQuery();
				while (resultSetObject.next()) {
					rightOrWrong = resultSetObject.getInt(databasePossition);
				}
			}
		} catch (SQLException e) {
			StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
		} finally {
			try {
				ConnectionDAO.DBClose(resultSetObject, statementObject,
						connectionObject);
			} catch (SQLException e) {
				StoreUserDetail.storeUserInfo("Database Error Occured--->"+e);
			}
		}
		// return the arraylist to the calling method
		return rightOrWrong;
	}
}
