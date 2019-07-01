package com.aspiresys.onlinetest.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.aspiresys.onlinetest.hardcode.HardCodeValues;
import com.aspiresys.onlinetest.logfiles.StoreUserDetail;
import com.aspiresys.onlinetest.model.dao.ManageOrganizationDAO;
import com.aspiresys.onlinetest.model.dao.ManageSubjectDAO;
import com.aspiresys.onlinetest.model.dao.ManageUserDAO;

public class DoGetManager {
	ManageUserDAO userObject = new ManageUserDAO();
	ManageSubjectDAO subjectObject = new ManageSubjectDAO();
	QuestionManager questionObject = new QuestionManager();
	UserSideManager userAreaObject = new UserSideManager();
	/**
	 * This method is used for get the input name and based on that it will do
	 * some operation and transfer the page
	 * @param commonName
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 * @throws ServletException
	 * @throws ClassNotFoundException
	 */
	public void getCommonName(String commonName, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException, ServletException, ClassNotFoundException {
		UsersManager userManagerObject = new UsersManager();
		ManageOrganizationDAO organizationDaoObj = new ManageOrganizationDAO();
		ManageSubjectDAO subjectDaoObj = new ManageSubjectDAO();
		try {
			session = request.getSession();
			if (session.getAttribute("firstName") == null) {
			}
			// declare the needed variables
			ArrayList<String> adminList;
			ArrayList<String> userList;
			/**
			 * this function used in DeleteUser.jsp .(while click the select
			 * button this method called)
			 */
			if (commonName.equals(HardCodeValues.SELECT_USER)) {
				String nameOfUser = request
						.getParameter(HardCodeValues.USER_NAME);

				if (nameOfUser.equals(HardCodeValues.ADMIN)) {

					// get the admin list
					adminList = userObject.getListOfUser(1);

					// set that list in session
					session.setAttribute(HardCodeValues.LIST_OF_USER_NAME,
							adminList);
					response.sendRedirect("DeleteUser.jsp?userName=admin");

				} else if (nameOfUser.equals(HardCodeValues.USER)) {
					// get the user list
					userList = userObject.getListOfUser(0);
					// set that list in session
					session.setAttribute(HardCodeValues.LIST_OF_USER_NAME,
							userList);
					response.sendRedirect("DeleteUser.jsp?userName=user");
				}
			}
			/**
			 * this function used in ModifyUser.jsp .(while click the select
			 * button this method called)
			 */
			else if (commonName.equals(HardCodeValues.GET_DETAIL_OF_USER)) {
				// declare the needed variables
				String check = request.getParameter(HardCodeValues.CHECK);
				String userName = request
						.getParameter(HardCodeValues.USER_NAME);
				userManagerObject.getDetailOfUser(request, response, session,
						userName, check);
			}
			/**
			 * this function used in ModifyQuestions.jsp .(while click the
			 * question number Button this method called)
			 */
			else if (commonName.equals(HardCodeValues.GET_QUESTION_ANSWER)) {
				// declare the needed variables
				int beginNumber = Integer.parseInt(request
						.getParameter("beginNumber"));
				int endNumber = Integer.parseInt(request
						.getParameter("endNumber"));
				int questionNumber = Integer.parseInt(request
						.getParameter(HardCodeValues.QUESTION_NUMBER));
				String subjectName = request
						.getParameter(HardCodeValues.SUBJECTNAME);
				String organizationName = request
						.getParameter("organizationName");
				String check = request.getParameter(HardCodeValues.CHECK);
				session.setAttribute("beginNumberSes", beginNumber);
				session.setAttribute("endNumberSes", endNumber);
				questionObject.getQuestionAndAnswer(request, response, session,
						subjectName, check, questionNumber, organizationName,
						"", beginNumber, endNumber);
			}
			/**
			 * this function used in ModifyQuestions.jsp .(while click the
			 * modifyOk Button this method called)
			 */
			else if (commonName.equals(HardCodeValues.GET_QUESTION_NUMBER)) {
				// declare the needed variables
				String subjectName = request
						.getParameter(HardCodeValues.SUBJECTNAME);
				String check = request.getParameter(HardCodeValues.CHECK);
				String organizationName;
				if (check.equals("writeTest")) {
					organizationName = (String) session.getAttribute("orgName");
				} else {
					organizationName = request.getParameter("organizationName");
				}
				session.setAttribute(HardCodeValues.QUESTION_POSSITION, 1);
				try {
					questionObject.getQuestionNumber(request, response,
							session, subjectName, check, organizationName);
				} catch (Exception e) {

					response.sendRedirect("ChooseSubject.jsp?userAnswered=noQuestion&subjectName="
							+ subjectName);
				}
			} else if (commonName.equals("getOrganizationName")) {
				String subjectName = request
						.getParameter(HardCodeValues.SUBJECTNAME);
				String test = request.getParameter("test");
				String organizationName = subjectObject
						.getOrganizationName(subjectName);
				if (test.equals("modifySubject")) {
					response.sendRedirect("ModifySubject.jsp?subjectName="
							+ subjectName + "&organizationName="
							+ organizationName);
				} else {
					response.sendRedirect("DeleteSubject.jsp?subjectName="
							+ subjectName + "&organizationName="
							+ organizationName);
				}
			} else if (commonName.equals("ModifyQuestions")) {
				session.setAttribute(HardCodeValues.NUMBER_OF_QUESTIONS, 0);
				response.sendRedirect("ModifyQuestions.jsp");
			} else if (commonName.equals("DeleteQuestions")) {
				session.setAttribute(HardCodeValues.NUMBER_OF_QUESTIONS, 0);
				response.sendRedirect("DeleteQuestions.jsp");
			}
			/**
			 * if submit button value equal new User mean enter to this
			 * function(NewUser.jsp->submit Button)
			 */
			else if (commonName.equals(HardCodeValues.NEW_USER)) {
				userAreaObject.newUser(request, response, session);

			}

			else if (commonName.equals("getSubjectList")) {
				String organizationName = request
						.getParameter("organizationName");
				ArrayList<String> listOfSubjects = new ArrayList<String>();
				listOfSubjects = subjectDaoObj
						.getListOfSubjectName(organizationDaoObj
								.getOrganizationId(organizationName));
				session.setAttribute("numberOfQuestions", 0);

				session.setAttribute("listOfSubject", listOfSubjects);

				String check = request.getParameter("check");
				if (check.equals("FilterResult")) {
					response.sendRedirect("FilterResult.jsp?organizationName="
							+ organizationName);
				} else if (check.equals("ModifySubject")) {
					response.sendRedirect("ModifySubject.jsp?organizationName="
							+ organizationName);
				} else if (check.equals("AddQuestion")) {
					response.sendRedirect("AddQuestions.jsp?organizationName="
							+ organizationName);
				} else if (check.equals("ModifyQuestion")) {
					response.sendRedirect("ModifyQuestions.jsp?organizationName="
							+ organizationName + "&beginNumber=0");
				} else if (check.equals("DeleteQuestion")) {
					response.sendRedirect("DeleteQuestions.jsp?organizationName="
							+ organizationName);
				} else {
					response.sendRedirect("DeleteSubject.jsp?organizationName="
							+ organizationName);
				}

			}

			else if (commonName.equals("nextSlot")) {
				int beginNumber = Integer.parseInt(request
						.getParameter("beginNumber"));
				int endNumber = Integer.parseInt(request
						.getParameter("endNumber"));
				String check = request.getParameter("check");
				String organizationName = request
						.getParameter("organizationName");
				session.setAttribute("beginNumberSes", beginNumber);
				session.setAttribute("endNumberSes", endNumber);
				String subjectName = request.getParameter("subjectName");
				if (check.equals("modify")) {
					System.out.println(beginNumber + "" + endNumber);
					response.sendRedirect("ModifyQuestions.jsp?verification=modify&subjectName="
							+ subjectName
							+ "&organizationName="
							+ organizationName
							+ "&beginNumber="
							+ beginNumber
							+ "&endNumber=" + endNumber);
				} else if (check.equals("delete")) {
					response.sendRedirect("DeleteQuestions.jsp?verification=modify&subjectName="
							+ subjectName
							+ "&organizationName="
							+ organizationName
							+ "&beginNumber="
							+ beginNumber
							+ "&endNumber=" + endNumber);
				}

			} else if (commonName.equals("resultSlider")) {
				int initialNumber = Integer.parseInt(request
						.getParameter("initialNumber"));
				int lastNumber = Integer.parseInt(request
						.getParameter("lastNumber"));
				String test = request.getParameter("test");
				if (test.equals("getResult")) {
					session.setAttribute("initialNumber", initialNumber);
					session.setAttribute("lastNumber", lastNumber);
					response.sendRedirect("Result.jsp?initialNumber="
							+ initialNumber + "&lastNumber=" + lastNumber);
				} else if (test.equals("userSlider")) {
					session.setAttribute("initialNumberUser", initialNumber);
					session.setAttribute("lastNumberUser", lastNumber);
					response.sendRedirect("ManageUser.jsp");
				} else if (test.equals("organizationSlider")) {
					session.setAttribute("initialNumberOrganization",
							initialNumber);
					session.setAttribute("lastNumberOrganization", lastNumber);
					response.sendRedirect("ManageOrganization.jsp");
				} else if (test.equals("subjectSlider")) {
					session.setAttribute("initialNumberSubject", initialNumber);
					session.setAttribute("lastNumberSubject", lastNumber);
					response.sendRedirect("ManageSubject.jsp");
				}
			}
			/**
			 * this function is called when we click the logout button .
			 */
			else if (commonName.equals(HardCodeValues.LOGOUT)) {
				StoreUserDetail.storeUserInfo("Logout..."
						+ session.getAttribute(HardCodeValues.NAME));
				session.invalidate();

				response.sendRedirect("LoginPage.jsp");
			}
		} catch (NullPointerException e) {
			StoreUserDetail.storeUserInfo("Error Occured In This User..."
			+ "---> Error Detail..." + e);
			response.sendRedirect("ErrorDisplayPage.jsp");
		}
	}
}
