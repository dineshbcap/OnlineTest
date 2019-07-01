package com.aspiresys.onlinetest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspiresys.onlinetest.hardcode.HardCodeValues;
import com.aspiresys.onlinetest.logfiles.StoreUserDetail;

/**
 * Servlet implementation class CommonServlet
 * 
 * This program is a controller for this("online test") project.
 * 
 * below we use, doGet() and doPost() doGet is used for when the user click
 * button or dropdown in the UI, JavaScript will called then servlet (doGet()
 * will called) with some needed parameter
 * 
 * doPost is used when submit the form.
 */

public class CommonServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession session;
	UsersManager usersObject = new UsersManager();
	UserSideManager userAreaObject = new UserSideManager();
	SubjectManager subjectObject = new SubjectManager();
	OrganizationManager organizationObject = new OrganizationManager();
	QuestionManager questionObject = new QuestionManager();
	DoGetManager doGetObject = new DoGetManager();
	FinalResult finalResultObj = new FinalResult();

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public CommonServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) set the session values where needed in this doGet()
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			session = request.getSession();
			if (session.getAttribute("firstName") == null) {
				throw new NullPointerException("NULL");
			}
			String commonName = request
					.getParameter(HardCodeValues.COMMON_NAME);
			String check = request.getParameter(HardCodeValues.CHECK);
			if (commonName.equals("nextSlot") && (check.equals("writeTest"))) {
				String test = (String) session.getAttribute("orgName");
				int beginNumber = Integer.parseInt(request
						.getParameter("beginNumber"));
				int questionNumber = beginNumber;
				int oldPossition = (Integer) session
						.getAttribute(HardCodeValues.QUESTION_POSSITION);
				session.setAttribute(HardCodeValues.OLD_POSSITION, oldPossition);
				session.setAttribute(HardCodeValues.QUESTION_POSSITION,
						questionNumber);

				int endNumber = Integer.parseInt(request
						.getParameter("endNumber"));
				session.setAttribute("beginNumberSes", beginNumber);
				session.setAttribute("endNumberSes", endNumber);
				String subjectName = (String) session
						.getAttribute("subjectNameSelected");
				String go = HardCodeValues.GO_TO;
				try {
					questionObject.getQuestionAndAnswer(request, response,
							session, subjectName, check, questionNumber, test,
							go, beginNumber, endNumber);
				} catch (ClassNotFoundException e) {
					StoreUserDetail
							.storeUserInfo("Error Occured In This User..."
									+ (String) session.getAttribute("name")
									+ "---> Error Detail..." + e);
				}

			} else {
				try {
					doGetObject.getCommonName(commonName, request, response,
							session);
				} catch (ClassNotFoundException e) {
					StoreUserDetail
							.storeUserInfo("Error Occured In This User..."
									+ (String) session.getAttribute("name")
									+ "---> Error Detail..." + e);
				}
			}
		} catch (NullPointerException e) {
			StoreUserDetail.storeUserInfo("Error Occured In This User..."

			+ "---> Error Detail..." + e);
			response.sendRedirect("ErrorDisplayPage.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) set the session values where needed in this doPost()
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String submitButton = request.getParameter(HardCodeValues.SUBMIT);
			String goQuestionNumber = request
					.getParameter(HardCodeValues.GO_QUESTION);
			session = request.getSession();
			/**
			 * This if and else block is used for omit the checking of null
			 * pointer exception at the login time and the GoTO question
			 * time(because, submit button name differ for go to question
			 * button)
			 */
			if (!((goQuestionNumber == null))) {
				if (session.getAttribute("firstName") == null) {
					throw new NullPointerException("NULL");
				}
			} else {
				if (!(submitButton.equals(HardCodeValues.SIGNIN))) {
					if (session.getAttribute("firstName") == null) {
						throw new NullPointerException("NULL");
					}
				}
			}
			QuestionManager questionObject = new QuestionManager();
			// get the submit button value
			String test;
			if (goQuestionNumber != null) {
				test = (String) session.getAttribute("orgName");
				int questionNumber = Integer.parseInt(goQuestionNumber);
				int oldPossition = (Integer) session
						.getAttribute(HardCodeValues.QUESTION_POSSITION);
				session.setAttribute(HardCodeValues.OLD_POSSITION, oldPossition);
				session.setAttribute(HardCodeValues.QUESTION_POSSITION,
						questionNumber);
				int beginNumber = (Integer) session
						.getAttribute("beginNumberSes");
				int endNumber = (Integer) session.getAttribute("endNumberSes");
				String subjectName = (String) session
						.getAttribute("subjectNameSelected");
				String check = HardCodeValues.WRITE_TEST;
				String go = HardCodeValues.GO_TO;
				questionObject.getQuestionAndAnswer(request, response, session,
						subjectName, check, questionNumber, test, go,
						beginNumber, endNumber);
			}
			/**
			 * if submit button value equal signin mean enter to this
			 * function(Login.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.SIGNIN)) {
				userAreaObject.signin(request, response, session);
			} else if (submitButton.equals(HardCodeValues.USER_SUBMIT)) {

				usersObject.addUser(request, response, session);
			} else if (submitButton.equals(HardCodeValues.NEXT)) {
				int beginNumber = (Integer) session
						.getAttribute("beginNumberSes");
				int endNumber = (Integer) session.getAttribute("endNumberSes");
				int questionNumber = 0;
				int numberOfQuestions = (Integer) session
						.getAttribute(HardCodeValues.NUMBER_OF_QUESTIONS);
				questionNumber = (Integer) session
						.getAttribute(HardCodeValues.QUESTION_POSSITION) + 1;
				test = (String) session.getAttribute("orgName");
				if ((questionNumber >= 1)
						&& (questionNumber <= numberOfQuestions)) {
					String subjectName = (String) session
							.getAttribute(HardCodeValues.SUBJECT_NAME_SELECTED);
					session.setAttribute(HardCodeValues.QUESTION_POSSITION,
							questionNumber);
					String check = HardCodeValues.WRITE_TEST;
					String go = HardCodeValues.NEXT;
					questionObject.getQuestionAndAnswer(request, response,
							session, subjectName, check, questionNumber, test,
							go, beginNumber, endNumber);
				} else {
					questionNumber = (Integer) session
							.getAttribute(HardCodeValues.NUMBER_OF_QUESTIONS);
					questionNumber = questionNumber + 1;
					String subjectName = (String) session
							.getAttribute(HardCodeValues.SUBJECT_NAME_SELECTED);
					String check = HardCodeValues.WRITE_TEST;
					String go = HardCodeValues.NEXT;
					questionObject.getQuestionAndAnswer(request, response,
							session, subjectName, check, questionNumber, test,
							go, beginNumber, endNumber);
				}
			} else if (submitButton.equals(HardCodeValues.PREVIOUS)) {
				int beginNumber = (Integer) session
						.getAttribute("beginNumberSes");
				int endNumber = (Integer) session.getAttribute("endNumberSes");
				int numberOfQuestions = (Integer) session
						.getAttribute(HardCodeValues.NUMBER_OF_QUESTIONS);
				int questionNumber = (Integer) session
						.getAttribute(HardCodeValues.QUESTION_POSSITION) - 1;
				test = (String) session.getAttribute("orgName");
				if ((questionNumber >= 1)
						&& (questionNumber <= numberOfQuestions)) {
					String subjectName = (String) session
							.getAttribute(HardCodeValues.SUBJECT_NAME_SELECTED);
					session.setAttribute(HardCodeValues.QUESTION_POSSITION,
							questionNumber);
					String check = HardCodeValues.WRITE_TEST;
					String go = HardCodeValues.PREVIOUS;
					questionObject.getQuestionAndAnswer(request, response,
							session, subjectName, check, questionNumber, test,
							go, beginNumber, endNumber);
				} else {
					questionNumber = 0;
					String subjectName = (String) session
							.getAttribute(HardCodeValues.SUBJECT_NAME_SELECTED);
					String check = HardCodeValues.WRITE_TEST;
					String go = HardCodeValues.PREVIOUS;
					questionObject.getQuestionAndAnswer(request, response,
							session, subjectName, check, questionNumber, test,
							go, beginNumber, endNumber);
				}

			} else if (submitButton.equals("Complete Test")) {
				int beginNumber = (Integer) session
						.getAttribute("beginNumberSes");
				int endNumber = (Integer) session.getAttribute("endNumberSes");

				test = (String) session.getAttribute("orgName");
				int questionNumber = (Integer) session
						.getAttribute(HardCodeValues.QUESTION_POSSITION);
				String subjectName = (String) session
						.getAttribute(HardCodeValues.SUBJECT_NAME_SELECTED);
				String check = HardCodeValues.WRITE_TEST;
				String go = HardCodeValues.FINISH;
				questionObject.getQuestionAndAnswer(request, response, session,
						subjectName, check, questionNumber, test, go,
						beginNumber, endNumber);
			}
			/**
			 * if submit button value equal userSubmit mean enter to this
			 * function(AddUser.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.ADD_USER)) {
				usersObject.addUser(request, response, session);
			}
			/**
			 * if submit button value equal Modify mean enter to this
			 * function(ModifyUser.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.MODIFY_USER)) {
				usersObject.modifyUser(request, response, session);
			} else if (submitButton.equals(HardCodeValues.DELETE_USER)) {
				usersObject.deleteUser(request, response, session);
			}
			/**
			 * if submit button value equal Add mean enter to this
			 * function(AddSubject.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.ADD_SUBJET)) {

				subjectObject.add(request, response, session);
			}
			/**
			 * if submit button value equal modifyName mean enter to this
			 * function(ModifySubject.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.MODIFY_SUBJECT)) {
				subjectObject.modifySubject(request, response, session);
			}
			/**
			 * if submit button value equal deleteSubject mean enter to this
			 * function(DeleteSubject.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.DELETE_SUBJECT)) {
				subjectObject.deleteSubject(request, response, session);
			}
			/**
			 * if submit button value equal AddOrganization mean enter to this
			 * function(AddOrganization.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.ADD_ORGANIZATION)) {
				organizationObject.addOrganization(request, response, session);
			}

			/**
			 * if submit button value equal modifyOrganization mean enter to
			 * this function(ModifyOrganization.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.MODIFY_ORGANIZATION)) {
				organizationObject.modifyOrganization(request, response,
						session);
			}
			/**
			 * if submit button value equal deleteOrganization mean enter to
			 * this function(DeleteOrganization.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.DELETE_ORGANIZATION)) {
				organizationObject.deleteOrganization(request, response,
						session);
			}

			/**
			 * if submit button value equal addQuestion mean enter to this
			 * function(AddQuestions.jsp->submit Button)
			 */

			else if (submitButton.equals(HardCodeValues.ADD_QUESTION)) {
				test = HardCodeValues.ADD;
				questionObject.addModifyQuestion(request, response, session,
						test);
			}
			/**
			 * if submit button value equal modifyQA mean enter to this
			 * function(ModifyQuestions.jsp->submit Button)
			 */
			else if (submitButton.equals(HardCodeValues.MODIFY_QUESTION)) {
				test = HardCodeValues.MODIFY;
				// session.setAttribute(HardCodeValues.NUMBER_OF_QUESTIONS,0);
				questionObject.addModifyQuestion(request, response, session,
						test);
			} else if (submitButton.equals(HardCodeValues.DELETE_QUESTION)) {

				questionObject.deleteQuestion(request, response, session);
			}

			else if (submitButton.equals(HardCodeValues.GENERATE_REPORT)) {
				finalResultObj.filterBy(request, response, session);
			}
		} catch (NullPointerException e) {
			StoreUserDetail.storeUserInfo("Error Occured In This User..."

			+ "---> Error Detail..." + e);
			response.sendRedirect("ErrorDisplayPage.jsp");
		} catch (ClassNotFoundException e) {
			StoreUserDetail.storeUserInfo("Error Occured In This User..."
					+ (String) session.getAttribute("name")
					+ "---> Error Detail..." + e);
		}
	}
}
