package com.aspiresys.onlinetest.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspiresys.onlinetest.hardcode.HardCodeValues;
import com.aspiresys.onlinetest.logfiles.StoreUserDetail;
import com.aspiresys.onlinetest.model.beanclass.CommonBeanClass;
import com.aspiresys.onlinetest.model.dao.ManageQuestionsDAO;
import com.aspiresys.onlinetest.model.dao.ManageUserDAO;

public class QuestionManager {

	ManageUserDAO userObject = new ManageUserDAO();
	ManageQuestionsDAO questionObject = new ManageQuestionsDAO();
	private static RequestDispatcher dispatcher;
	Boolean booleanVariable=true;
	int minute;
	int second;
	/**
	 * this method will be called when, Submit button equal "addQuestion"(from
	 * servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param test
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void addModifyQuestion(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String test)
			throws ServletException, IOException, ClassNotFoundException {
		CommonBeanClass beanObjectQuestion = new CommonBeanClass();
		CommonBeanClass beanObject = new CommonBeanClass();
		ArrayList<String> option = new ArrayList<String>();
		option.add(0, request.getParameter(HardCodeValues.OPTION1));
		option.add(1, request.getParameter(HardCodeValues.OPTION2));
		option.add(2, request.getParameter(HardCodeValues.OPTION3));
		option.add(3, request.getParameter(HardCodeValues.OPTION4));
		ArrayList<Integer> answer = new ArrayList<Integer>();
		String storeResult;
		storeResult = request.getParameter("rb");
		for (int possition = 0; possition < 4; possition++) {
			if ((storeResult).equals(("rb" + possition).toString())) {
				answer.add(possition, 1);
			} else {
				answer.add(possition, 0);
			}
			beanObject.setOption(option);
			beanObject.setAnswer(answer);
		}
		if (test.equals(HardCodeValues.ADD)) {
			// declare the needed variables
			String question = request
					.getParameter(HardCodeValues.ADMIN_QUESTION);
			String subjectName = request
					.getParameter("selectedSubjectName1");
			
			String organizationName = request
					.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME);
			int subjectId = questionObject.getSubjectId(subjectName);
			beanObjectQuestion.setSubjectId(subjectId);
			beanObjectQuestion.setQuestion(question);
			questionObject.addQuestion(beanObjectQuestion);
			int questionId = questionObject.getQuestionNumber();
			beanObject.setQuestionId(questionId);
			beanObject.setSubjectId(subjectId);
			questionObject.addQuestionAnswer(beanObject);
			int questNumber=(Integer)(session.getAttribute("addQuestNumber"));
			questNumber=questNumber+1;
			session.setAttribute("addQuestNumber", questNumber);
			response.sendRedirect("AddQuestions.jsp?subjectName="
					+ subjectName +"&organizationName="+organizationName+ "&questionSuccess=addQuestion&dummy=dummy+&questionNumber="+questNumber);
		} else if (test.equals(HardCodeValues.MODIFY)) {
			int beginNumber=(Integer) session.getAttribute("beginNumberSes");	
			int endNumber=(Integer) session.getAttribute("endNumberSes");
			int questionId = (Integer) session
					.getAttribute(HardCodeValues.QUESTION_ID);
			String subjectName = request
					.getParameter("selectedSubjectName1");
			String organizationName = request
					.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME);
			int answerId = questionObject.getAnswerId(questionId);
			answerId = answerId - 3;
			String question = request.getParameter(HardCodeValues.QUESTION);
			beanObjectQuestion.setQuestion(question);
			beanObjectQuestion.setQuestionId(questionId);
			questionObject.updateQuestion(beanObjectQuestion);
			beanObject.setAnswerId(answerId);
			questionObject.updateOptionAndAnswer(beanObject);
			response.sendRedirect("ModifyQuestions.jsp?subjectName="
					+ subjectName+"&organizationName="+organizationName
					+ "&questionSuccess=modifyQuestion&beginNumber="+beginNumber+"&endNumber="+endNumber);
		}
	}

	/**
	 * This method is used for get the question number
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param subjectName
	 * @param check
	 * @throws ServletException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void getQuestionNumber(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String subjectName, String check,String organizationName) throws ServletException,
			ClassNotFoundException, IOException {
		String userName = (String) session.getAttribute("name");
		try {
			int selectedSubjectId = questionObject.getSubjectId(subjectName);
			int totalQuestions = questionObject
					.getQuestionNumber(selectedSubjectId);
			
			session.setAttribute(HardCodeValues.NUMBER_OF_QUESTIONS, totalQuestions);
			if(check.equals("AddQuestion")){
				totalQuestions=totalQuestions+1;
				session.setAttribute("addQuestNumber",totalQuestions);
				response.sendRedirect("AddQuestions.jsp?subjectName="
						+ subjectName+"&organizationName="+organizationName+"&questionNumber="+totalQuestions);
			}
			
			if((booleanVariable==true)&&(check.equals(HardCodeValues.WRITE_TEST))){
				ArrayList<Integer> shuffledNumbers = new ArrayList<Integer>();
			     for(int i = 1; i <= totalQuestions; i++)
			     {
			    	 shuffledNumbers.add(i);
			     }
			 
			     Collections.shuffle(shuffledNumbers);
			     session.setAttribute("shuffledNumbers",shuffledNumbers);
			     booleanVariable=false;
			     System.out.println(shuffledNumbers);
			}
			else{
				ArrayList<Integer> shuffledNumbers = new ArrayList<Integer>();
			     for(int i = 1; i <= totalQuestions; i++)
			     {
			    	 shuffledNumbers.add(i);
			     }
			 
			     session.setAttribute("shuffledNumbers",shuffledNumbers);
			     booleanVariable=true;
			}
			
			if (check.equals(HardCodeValues.MODIFY_QUESTION)) {
				int beginQuestionNumber=0;
				int endQuestionNumber=0;
				
				if(totalQuestions>10){
					beginQuestionNumber= 1;
					endQuestionNumber=10;
				}
				else{
					beginQuestionNumber= 1;
					endQuestionNumber=totalQuestions;
				}
				
				if(subjectName.equals("-Subject Name-")){
					beginQuestionNumber=0;
				}
				session.setAttribute("beginNumberSes",beginQuestionNumber);
				session.setAttribute("endNumberSes", endQuestionNumber);
				response.sendRedirect("ModifyQuestions.jsp?verification=modify&subjectName="
						+ subjectName+"&organizationName="+organizationName+"&beginNumber="+beginQuestionNumber+"&endNumber="+endQuestionNumber);
			} else if (check.equals(HardCodeValues.WRITE_TEST)) {
				Properties prop = new Properties();
				
			    try {
			    	//load the properties file
					prop.load(new FileInputStream("C:/Users/acer/Desktop/OnlineTest/OnlineTest/src/com/aspiresys/onlinetest/controller/Configuration.properties"));
				}  catch (FileNotFoundException e) {
					StoreUserDetail.storeUserInfo("Property File Error Occured--->"+e);
				} catch (IOException e) {
					StoreUserDetail.storeUserInfo("Property File Error Occured--->"+e);
				}
				//get the values from the properties file 
			    minute= Integer.parseInt(prop.getProperty("testMinutes"));
				
				second=Integer.parseInt(prop.getProperty("testSeconds"));
				
				int beginQuestionNumber=0;
				int endQuestionNumber=0;
				
				if(totalQuestions>10){
					beginQuestionNumber= 1;
					endQuestionNumber=10;
				}
				else{
					beginQuestionNumber= 1;
					endQuestionNumber=totalQuestions;
				}
				
				session.setAttribute("beginNumberSes", beginQuestionNumber);
				session.setAttribute("endNumberSes",endQuestionNumber);
				
				session.setAttribute(HardCodeValues.SUBJECT_NAME_SELECTED,
						subjectName);
				session.setAttribute(HardCodeValues.QUESTION_POSSITION, 1);
				CommonBeanClass beanObject = new CommonBeanClass();
				ArrayList<Integer> listOfQuestionId = new ArrayList<Integer>();
				ArrayList<Integer> listOfAnswerId = new ArrayList<Integer>();
				int numberOfQuestions = (Integer) session
						.getAttribute(HardCodeValues.NUMBER_OF_QUESTIONS);
				beanObject.setUserName(userName);
				int userId = userObject.getUserId(beanObject);
				int userAnswered = 0;
				beanObject.setNumberOfQuestions(numberOfQuestions);
				beanObject.setSubjectId(selectedSubjectId);
				listOfQuestionId = userObject.getQuestionId(beanObject);
				int answerId = 0;
				for (int i = 0; i < listOfQuestionId.size(); i++) {
					answerId = questionObject.getAnswerId(listOfQuestionId
							.get(i));
					answerId = answerId - 3;
					listOfAnswerId.add(i, answerId);
				}
				session.setAttribute("listOfAnswerId", listOfAnswerId);
				beanObject.setListOfQuestionId(listOfQuestionId);
				beanObject.setUserId(userId);
				beanObject.setListOfAnswerId(listOfAnswerId);
				userAnswered = userObject.insertUserAnswerTable(beanObject);
				try {
					if (userAnswered == 0) {
						int questionNumber = 1;
						subjectName = (String) session
								.getAttribute(HardCodeValues.SUBJECT_NAME_SELECTED);
						check = HardCodeValues.WRITE_TEST;
						getQuestionAndAnswer(request, response, session,
								subjectName, check, questionNumber, organizationName,"",beginQuestionNumber,endQuestionNumber);
					} else {
						response.sendRedirect("ChooseSubject.jsp?userAnswered=answered&subjectName="+subjectName);
					}
				} catch (IOException e) {
					StoreUserDetail.storeUserInfo("Error Occured In This User..." + userName+"---> Error Detail..."+e);
					response.sendRedirect("ErrorDisplayPage.jsp");
				}
			} else if (check.equals(HardCodeValues.DELETE_QUESTION)) {
				int beginQuestionNumber=0;
				int endQuestionNumber=0;
				
				if(totalQuestions>10){
					beginQuestionNumber= 1;
					endQuestionNumber=10;
				}
				else{
					beginQuestionNumber= 1;
					endQuestionNumber=totalQuestions;
				}
				
				if(subjectName.equals("-Subject Name-")){
					beginQuestionNumber=0;
				}
				session.setAttribute("beginNumberSes",beginQuestionNumber);
				session.setAttribute("endNumberSes", endQuestionNumber);
				response.sendRedirect("DeleteQuestions.jsp?verification=delete&subjectName="
						+ subjectName+"&organizationName="+organizationName+"&beginNumber="+beginQuestionNumber+"&endNumber="+endQuestionNumber);
			}
		} catch (IOException e) {
			StoreUserDetail.storeUserInfo("Error Occured In This User..." + userName+"---> Error Detail..."+e);
		}
	}

	/**
	 * this method is used for get the question and answer
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param subjectName
	 * @param check
	 * @param questionNumber
	 * @param test
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void getQuestionAndAnswer(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String subjectName, String check, int questionNumber, String test,String go,int beginNumber,int endNumber)
			throws ServletException, IOException, ClassNotFoundException {
		ArrayList<Object> listOfQuestionAndQuestionId = null;
		ArrayList<Object> listOfOptionAndAnswer;
		int questionId;
		String question;
		String option1;
		String option2;
		String option3;
		String option4;
		int selectedSubjectId = questionObject.getSubjectId(subjectName);
		String tableName = "QuestionAnswerRelation";
		int qNumber=questionNumber;
		/*int qNumber = (Integer) session
				.getAttribute(HardCodeValues.QUESTION_POSSITION);*/
		int numberOfQuestions = (Integer) session
				.getAttribute(HardCodeValues.NUMBER_OF_QUESTIONS);
		@SuppressWarnings("unchecked")
		ArrayList<Integer> shuffledNumber=(ArrayList<Integer>) session
				.getAttribute("shuffledNumbers");
		/*if(questionNumber==0){
			questionNumber=1;
		}*/
		listOfQuestionAndQuestionId = questionObject.getQuestion(shuffledNumber.get(questionNumber-1),
				selectedSubjectId, numberOfQuestions);
		
		questionId = (Integer) listOfQuestionAndQuestionId.get(0);
		int answerId = questionObject.getAnswerId(questionId);
		answerId = answerId - 3;
		int checkBox1[] = new int[4];
		for (int possition = 0; possition < 4; possition++) {
			checkBox1[possition] = questionObject.getUserAnswer(answerId,
					tableName);
			answerId = answerId + 1;
		}
		question = (String) listOfQuestionAndQuestionId.get(1);
		listOfOptionAndAnswer = questionObject.getAnswer(questionId);
		option1 = (String) listOfOptionAndAnswer.get(0);
		option2 = (String) listOfOptionAndAnswer.get(1);
		option3 = (String) listOfOptionAndAnswer.get(2);
		option4 = (String) listOfOptionAndAnswer.get(3);
		session.setAttribute(HardCodeValues.QUESTION_ID, questionId);
		request.setAttribute(HardCodeValues.QUESTION, question);
		request.setAttribute(HardCodeValues.OPTION1, option1);
		request.setAttribute(HardCodeValues.OPTION2, option2);
		request.setAttribute(HardCodeValues.OPTION3, option3);
		request.setAttribute(HardCodeValues.OPTION4, option4);
		if (check.equals(HardCodeValues.MODIFY)) {
			tableName = "Answer";
			answerId = questionObject.getAnswerId(questionId);
			answerId = answerId - 3;
			for (int possition = 0; possition < 4; possition++) {
				checkBox1[possition] = questionObject.getUserAnswer(answerId,
						tableName);
				answerId = answerId + 1;
			}
			dispatcher = request
					.getRequestDispatcher("ModifyQuestions.jsp?subjectName="
							+ subjectName+"&organizationName="+test
							+ "&check1="
							+ checkBox1[0]
							+ "&check2="
							+ checkBox1[1]
							+ "&check3="
							+ checkBox1[2]
							+ "&check4="
							+ checkBox1[3]
							+ "&questionNum=" + shuffledNumber.get(questionNumber-1)+"&beginNumber="+beginNumber+"&endNumber="+endNumber);
			if (dispatcher != null)
				dispatcher.forward(request, response);
		} else if (check.equals(HardCodeValues.WRITE_TEST)) {
			if(!(go.equals(""))){
				minute=Integer.parseInt(request.getParameter("minute"));
				second=Integer.parseInt(request.getParameter("second"));
			}
			
			if (go.equals("next")) {
				questionNumber = questionNumber - 1;
			} else if (go.equals("previous")) {
				questionNumber = questionNumber + 1;
			} else if (go.equals("goTo")) {
				questionNumber = (Integer) session
						.getAttribute(HardCodeValues.OLD_POSSITION);
			}
			listOfQuestionAndQuestionId = questionObject.getQuestion(
					shuffledNumber.get(questionNumber-1), selectedSubjectId, numberOfQuestions);
			questionId = (Integer) listOfQuestionAndQuestionId.get(0);
			int relationId = userObject.getRelationId(questionId) - 3;
			int count = 0;
			String storeResult1;
			int checkBox[] = new int[4];
			int arrayPossition = 0;
			ArrayList<Integer> answer = new ArrayList<Integer>();
			storeResult1 = request.getParameter("rb");
			for (int possition = 0; possition < 4; possition++) {
				
				if(storeResult1==null){
					answer.add(possition, 0);
					checkBox[arrayPossition] = 0;
					arrayPossition++;
				} 
				else if(storeResult1.equals(("cb"+possition).toString())){
					answer.add(possition, 1);
					checkBox[arrayPossition] = 1;
					arrayPossition++;
					count++;
				} 
				else {
					answer.add(possition, 0);

					
					checkBox[arrayPossition] = 0;
					arrayPossition++;
				}

			}

			if (count == 0) {
				if (go.equals(HardCodeValues.FINISH)) {
					UserSideManager userAreaObject = new UserSideManager();
					userAreaObject.calculateMark(request, response, session);
				}
				else{
				dispatcher = request
						.getRequestDispatcher("QuestionRetrival.jsp?check1="
								+ checkBox1[0] + "&check2=" + checkBox1[1]
								+ "&check3=" + checkBox1[2] + "&check4="
								+ checkBox1[3]+"&questionNumber="+qNumber+"&totQuestion="+numberOfQuestions+"&beginNumber="+beginNumber+"&endNumber="+endNumber+"&min="+minute+"&sec="+second);
					if (dispatcher != null)
						dispatcher.forward(request, response);
				}	
			} else {
				CommonBeanClass beanObject = new CommonBeanClass();
				beanObject.setAnswer(answer);
				beanObject.setRelationId(relationId);
				userObject.updateQuewstionAnswerRelation(beanObject);
				if (go.equals(HardCodeValues.FINISH)) {
					UserSideManager userAreaObject = new UserSideManager();
					userAreaObject.calculateMark(request, response, session);
				} else {
					dispatcher = request
							.getRequestDispatcher("QuestionRetrival.jsp?check1="
									+ checkBox1[0]
									+ "&check2="
									+ checkBox1[1]
									+ "&check3="
									+ checkBox1[2]
									+ "&check4="
									+ checkBox1[3]+"&questionNumber="+qNumber+"&totQuestion="+numberOfQuestions+"&beginNumber="+beginNumber+"&endNumber="+endNumber+"&min="+minute+"&sec="+second);
					if (dispatcher != null)
						dispatcher.forward(request, response);
				}
			}
			
		} else if (check.equals("delete")) {
			dispatcher = request
					.getRequestDispatcher("DeleteQuestions.jsp?subjectName="
							+ subjectName +"&organizationName="+test+ "&questionNum=" + shuffledNumber.get(questionNumber-1));
			if (dispatcher != null)
				dispatcher.forward(request, response);
		}
	}

	/**
	 * this method will be called when, Submit button equal
	 * "deleteQuestion"(from servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void deleteQuestion(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException, ClassNotFoundException {
		session = request.getSession();
		String subjectName = request
				.getParameter("selectedSubjectName1");
		String organizationName = request
				.getParameter("selectedOrganizationName");
		int beginNumber=(Integer) session.getAttribute("beginNumberSes");	
		int endNumber=(Integer) session.getAttribute("endNumberSes");
		
		int numberOfQuestion = (Integer) session
				.getAttribute(HardCodeValues.NUMBER_OF_QUESTIONS);
		
		
		ArrayList<Integer> questionNumbers = new ArrayList<Integer>();
		int possition = 0;
		int selectedSubjectId = questionObject.getSubjectId(subjectName);
		
		for (int check = 1; check <= numberOfQuestion; check++) {
			String test = request.getParameter("goTo[" + check + "]");
			if (!(test == null)) {
				questionNumbers.add(possition, check);
				possition = possition + 1;
			}
		}
		numberOfQuestion = numberOfQuestion - possition;
		session.setAttribute(HardCodeValues.NUMBER_OF_QUESTIONS,
				numberOfQuestion);
		session.setAttribute("beginNumberSes",beginNumber);
		if(endNumber>10){
			endNumber=10;
		
		}
		else{
			endNumber=endNumber-possition;
		}
		session.setAttribute("endNumberSes", endNumber);
		questionObject.deleteQuestion(questionNumbers, selectedSubjectId);
		response.sendRedirect("DeleteQuestions.jsp?subjectName="
				+ subjectName +"&organizationName="+organizationName+ "&questionSuccess=deleteQuestion&beginNumber="+beginNumber+"&endNumber="+endNumber);
	}
}

