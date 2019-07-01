package com.aspiresys.onlinetest.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.aspiresys.onlinetest.hardcode.HardCodeValues;
import com.aspiresys.onlinetest.model.beanclass.CommonBeanClass;
import com.aspiresys.onlinetest.model.dao.FindUserDAO;
import com.aspiresys.onlinetest.model.dao.ManageUserDAO;
import com.aspiresys.onlinetest.model.dao.StoreUsersDetails;

public class UsersManager {
	ManageUserDAO userObject = new ManageUserDAO();
	FindUserDAO userDaoObj = new FindUserDAO();
	ArrayList<String> listOfAdmin;
	ArrayList<String> listOfUser;
	private static RequestDispatcher dispatcher;

	/**
	 * this method will be called when, Submit button equal "addUser"(from
	 * servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void addUser(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException, ClassNotFoundException {
		// declare the needed variables
		int isAdminValue = 0;
		String name = request.getParameter("selectedName");
		String lastName = request.getParameter("lastName");
		System.out.println(lastName+"LAST");
		String emailId = request.getParameter(HardCodeValues.EMAILID);
		Long mobileNumber = Long.parseLong(request
				.getParameter(HardCodeValues.MOBILE_NUMBER));
		String userName = request.getParameter(HardCodeValues.USER_NAME);
		String isAdmin = request.getParameter(HardCodeValues.IS_ADMIN);
		String passWord = request.getParameter(HardCodeValues.PASSWORD);
		String organizationName = request
				.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME);

		if ((userDaoObj.uniqueIdentifier("user", "emailId", emailId, 0))) {
			if ((isAdmin == null) || (isAdmin.equals("on"))) {
				response.sendRedirect("AddUser.jsp?duplicateMailId=Duplicate Mail Id!! Try with someother mailId&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&mailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&userName="
						+ userName + "&organizationName=" + organizationName);
			}
			/*
			 * else if (isAdmin.equals("on")) { System.out.println("2");
			 * response.sendRedirect(
			 * "ManageUser.jsp?page=AddUser&duplicateMailId=Duplicate Mail Id!! Try with someother mailId&name="
			 * + name + "&mailId=" + emailId + "&mobileNumber=" + mobileNumber +
			 * "&userName=" + userName); }
			 */
			else if (isAdmin.equals("0")) {
				response.sendRedirect("NewUser.jsp?duplicateMailId=Duplicate Mail Id!! Try with someother mailId&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&mailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&userName="
						+ userName + "&organizationName=" + organizationName);
			}

		}

		else if (userDaoObj.uniqueIdentifier("user", "mobileNumber", "null",
				mobileNumber)) {
			if ((isAdmin == null) || (isAdmin.equals("on"))) {
				response.sendRedirect("AddUser.jsp?duplicateMobNum=Duplicate Mobile Numer!! Try with someother Mobile Number&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&mailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&userName="
						+ userName + "&organizationName=" + organizationName);
			} else if (isAdmin.equals("0")) {
				response.sendRedirect("NewUser.jsp?duplicateMobNum=Duplicate Mobile Numer!! Try with someother Mobile Number&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&mailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&userName="
						+ userName + "&organizationName=" + organizationName);
			}
		}

		else if (userDaoObj.uniqueIdentifier("user", "userName", userName, 0)) {
			if ((isAdmin == null) || (isAdmin.equals("on"))) {
				response.sendRedirect("AddUser.jsp?duplicateUserName=Duplicate User Name!! Try with someother Name&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&mailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&userName="
						+ userName + "&organizationName=" + organizationName);
			} else if (isAdmin.equals("0")) {
				response.sendRedirect("NewUser.jsp?duplicateUserName=Duplicate User Name!! Try with someother Name&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&mailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&userName="
						+ userName + "&organizationName=" + organizationName);
			}
		} else {

			int organizationId = userObject.getOrganizationId(organizationName);

			ArrayList<StoreUsersDetails> completeUserDetails = new ArrayList<StoreUsersDetails>();

			completeUserDetails = userObject.getUserDatas();
			session.setAttribute("completeUserDetails", completeUserDetails);

			// set the initial number and last number( used for display the
			// whole details of user, subject and organisation)
			session.setAttribute("numberOfUser", completeUserDetails.size());

			session.setAttribute("initialNumberUser", 0);

			if (completeUserDetails.size() > 10) {
				session.setAttribute("lastNumberUser", 10);
			} else {
				session.setAttribute("lastNumberUser",
						completeUserDetails.size());
			}

			if (isAdmin == null) {
				isAdminValue = 0;
				CommonBeanClass beanObject = new CommonBeanClass();
				beanObject.setName(name);
				beanObject.setLastName(lastName);
				beanObject.setEmailId(emailId);
				beanObject.setMobileNumber(mobileNumber);
				beanObject.setUserName(userName);
				beanObject.setPassWord(passWord);
				beanObject.setOrganizationId(organizationId);
				beanObject.setIsAdmin(isAdminValue);
				userObject.addUserDetails(beanObject);
				listOfUser = userObject.getListOfUser(0);
				session.setAttribute(HardCodeValues.LIST_OF_USER, listOfUser);
				completeUserDetails = userObject.getUserDatas();
				session.setAttribute("completeUserDetails", completeUserDetails);
				response.sendRedirect("AddUser.jsp?addUserSuccess=User Added Successfully");
			} else if (isAdmin.equals("on")) {
				isAdminValue = 1;
				CommonBeanClass beanObject = new CommonBeanClass();
				beanObject.setName(name);
				beanObject.setLastName(lastName);
				beanObject.setEmailId(emailId);
				beanObject.setMobileNumber(mobileNumber);
				beanObject.setUserName(userName);
				beanObject.setPassWord(passWord);
				beanObject.setOrganizationId(organizationId);
				beanObject.setIsAdmin(isAdminValue);
				userObject.addUserDetails(beanObject);
				listOfAdmin = userObject.getListOfUser(1);
				session.setAttribute(HardCodeValues.LIST_OF_ADMIN, listOfAdmin);
				completeUserDetails = userObject.getUserDatas();
				session.setAttribute("completeUserDetails", completeUserDetails);
				response.sendRedirect("AddUser.jsp?addUserSuccess=Admin AddedSuccessfully&dummy=dummy");
			} else if (isAdmin.equals("0")) {
				isAdminValue = 0;
				CommonBeanClass beanObject = new CommonBeanClass();
				beanObject.setName(name);
				beanObject.setLastName(lastName);
				beanObject.setEmailId(emailId);
				beanObject.setMobileNumber(mobileNumber);
				beanObject.setUserName(userName);
				beanObject.setPassWord(passWord);
				beanObject.setOrganizationId(organizationId);
				beanObject.setIsAdmin(isAdminValue);
				userObject.addUserDetails(beanObject);
				listOfUser = userObject.getListOfUser(0);
				session.setAttribute(HardCodeValues.LIST_OF_USER, listOfUser);
				completeUserDetails = userObject.getUserDatas();
				session.setAttribute("completeUserDetails", completeUserDetails);
				response.sendRedirect("LoginPage.jsp?userAdded=User Added Successfully!");
			}
		}
	}

	/**
	 * this method will be called when, Submit button equal "modifyUser"(from
	 * servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void modifyUser(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException, ClassNotFoundException {
		// declare the needed variables
		int isAdmin = 1;
		String check = "";
		check = request.getParameter(HardCodeValues.IS_ADMIN);
		if (check == null) {
			isAdmin = 0;
		} else if (check.equals("on")) {
			isAdmin = 1;
		}
		String name = request.getParameter(HardCodeValues.SELECTED_NAME);
		String firstName = request.getParameter("userName");
		String lastName = request.getParameter("lastName");
		String emailId = request.getParameter(HardCodeValues.EMAILID);
		Long mobileNumber = Long.parseLong(request
				.getParameter(HardCodeValues.MOBILE_NUMBER));
		
		String passWord = request.getParameter(HardCodeValues.PASSWORD);
		String organizationName = request
				.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME);

		if ((userDaoObj.uniqueIdentifier("user", "emailId", emailId, 0))
				&& ((userDaoObj.uniqueIdentifier1("userName", name, 0))) != ((userDaoObj
						.uniqueIdentifier1("emailId", emailId, 0)))) {
			if (isAdmin == 0) {
				response.sendRedirect("ModifyUser.jsp?duplicateMailId=Duplicate Mail Id!! Try with someother mailId&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&emailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&firstName="
						+ firstName
						+ "&organizationName="
						+ organizationName);
			} else if (isAdmin == 1) {
				response.sendRedirect("ModifyAdmin.jsp?duplicateMailId=Duplicate Mail Id!! Try with someother mailId&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&emailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&firstName="
						+ firstName
						+ "&organizationName="
						+ organizationName);
			}

		} else if (userDaoObj.uniqueIdentifier("user", "mobileNumber", "null",
				mobileNumber)
				&& ((userDaoObj.uniqueIdentifier1("userName", name, 0))) != ((userDaoObj
						.uniqueIdentifier1("mobileNumber", "null", mobileNumber)))) {
			if (isAdmin == 0) {
				response.sendRedirect("ModifyUser.jsp?duplicateMobNum=Duplicate Mobile Numer!! Try with someother Mobile Number&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&emailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&firstName="
						+ firstName
						+ "&organizationName="
						+ organizationName);
			} else if (isAdmin == 1) {
				response.sendRedirect("ModifyAdmin.jsp?duplicateMobNum=Duplicate Mobile Numer!! Try with someother Mobile Number&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&emailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&firstName="
						+ firstName
						+ "&organizationName="
						+ organizationName);
			}

		} /*else if (userDaoObj.uniqueIdentifier("user", "userName", userName, 0)
				&& ((userDaoObj.uniqueIdentifier1("userName", name, 0))) != ((userDaoObj
						.uniqueIdentifier1("userName", userName, 0)))) {
			if (isAdmin == 0) {
				response.sendRedirect("ModifyUser.jsp?duplicateUserName=Duplicate User Name!! Try with someother Name&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&emailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&userName="
						+ userName
						+ "&organizationName="
						+ organizationName);
			} else if (isAdmin == 1) {
				response.sendRedirect("ModifyAdmin.jsp?duplicateUserName=Duplicate User Name!! Try with someother Name&name="
						+ name
						+ "&lastName="
						+ lastName
						+ "&emailId="
						+ emailId
						+ "&mobileNumber="
						+ mobileNumber
						+ "&userName="
						+ userName
						+ "&organizationName="
						+ organizationName);
			}

		}*/ else {
			int organizationId = 0;

			if (check == null) {
				isAdmin = 0;

				organizationId = userObject.getOrganizationId(organizationName);
				CommonBeanClass beanObject = new CommonBeanClass();
				beanObject.setName(name);
				beanObject.setLastName(lastName);
				beanObject.setEmailId(emailId);
				beanObject.setMobileNumber(mobileNumber);
				beanObject.setUserName(firstName);
				beanObject.setPassWord(passWord);
				beanObject.setOrganizationId(organizationId);
				beanObject.setIsAdmin(isAdmin);
				// ManageUserDAO.addAdminOrUserDetails(beanObject);
				userObject.updateUserDetails(beanObject);
			} else if (check.equals("on")) {
				isAdmin = 1;
				organizationId = 0;
				CommonBeanClass beanObject = new CommonBeanClass();
				beanObject.setName(name);
				beanObject.setLastName(lastName);
				beanObject.setEmailId(emailId);
				beanObject.setMobileNumber(mobileNumber);
				beanObject.setUserName(firstName);
				beanObject.setPassWord(passWord);
				beanObject.setOrganizationId(organizationId);
				beanObject.setIsAdmin(isAdmin);
				// ManageUserDAO.addAdminOrUserDetails(beanObject);
				userObject.updateUserDetails(beanObject);
			}
			listOfUser = userObject.getListOfUser(0);
			session.setAttribute(HardCodeValues.LIST_OF_USER, listOfUser);
			listOfAdmin = userObject.getListOfUser(1);
			session.setAttribute(HardCodeValues.LIST_OF_ADMIN, listOfAdmin);

			ArrayList<StoreUsersDetails> completeUserDetails = new ArrayList<StoreUsersDetails>();

			completeUserDetails = userObject.getUserDatas();
			session.setAttribute("completeUserDetails", completeUserDetails);

			// set the initial number and last number( used for display the
			// whole
			// details of user, subject and organisation)
			session.setAttribute("numberOfUser", completeUserDetails.size());

			session.setAttribute("initialNumberUser", 0);

			if (completeUserDetails.size() > 10) {
				session.setAttribute("lastNumberUser", 10);
			} else {
				session.setAttribute("lastNumberUser",
						completeUserDetails.size());
			}

			if (isAdmin == 1) {
				response.sendRedirect("ModifyAdmin.jsp?addUserSuccess=modifyUser&modifyUserSuccess=Details Modified Successfully!");
			} else {
				response.sendRedirect("ModifyUser.jsp?addUserSuccess=modifyUser&modifyUserSuccess=Details Modified Successfully!");
			}
		}
	}

	/**
	 * this method will be called when, Submit button equal "deleteUser"(from
	 * servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void deleteUser(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException, ClassNotFoundException {
		CommonBeanClass beanObject = new CommonBeanClass();
		String userName = request.getParameter("deleteName");
		if (userName.equals(session.getAttribute("name"))) {
			response.sendRedirect("DeleteUser.jsp?deleteUserSuccess=Curren User Not Possible To Delete");
		} else if (userName.equals("-name-")) {
			response.sendRedirect("DeleteUser.jsp?deleteUserSuccess=Please Select The Name");
		} else {
			beanObject.setUserName(userName);
			int userId = userObject.getUserId(beanObject);
			userObject.deleteUser(userId);
			listOfUser = userObject.getListOfUser(0);
			listOfAdmin = userObject.getListOfUser(1);
			session.setAttribute(HardCodeValues.LIST_OF_USER, listOfUser);
			session.setAttribute(HardCodeValues.LIST_OF_ADMIN, listOfAdmin);

			ArrayList<StoreUsersDetails> completeUserDetails = new ArrayList<StoreUsersDetails>();

			completeUserDetails = userObject.getUserDatas();
			session.setAttribute("completeUserDetails", completeUserDetails);

			// set the initial number and last number( used for display the
			// whole details of user, subject and organisation)
			session.setAttribute("numberOfUser", completeUserDetails.size());

			session.setAttribute("initialNumberUser", 0);

			if (completeUserDetails.size() > 10) {
				session.setAttribute("lastNumberUser", 10);
			} else {
				session.setAttribute("lastNumberUser",
						completeUserDetails.size());
			}

			response.sendRedirect("DeleteUser.jsp?deleteUserSuccess=User Deleted Successfully");
		}
	}

	/**
	 * this method is used for get the detail of user
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param userName
	 * @param checkUser
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void getDetailOfUser(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String userName,
			String checkUser) throws ServletException, IOException,
			ClassNotFoundException {
		session = request.getSession();
		ArrayList<Object> arrayListObjectForManageAdminOrUser;
		arrayListObjectForManageAdminOrUser = userObject
				.getListOfUserDetails(userName);
		String lastName=(String) arrayListObjectForManageAdminOrUser.get(1);
		String emailId = (String) arrayListObjectForManageAdminOrUser.get(2);
		Long mobileNumber = (Long) arrayListObjectForManageAdminOrUser.get(3);
		String userNameForAdminOrUser = (String) arrayListObjectForManageAdminOrUser
				.get(0);
		String passWordForAdminOrUser = (String) arrayListObjectForManageAdminOrUser
				.get(5);
		int organizationId = (Integer) arrayListObjectForManageAdminOrUser
				.get(6);
		String organizationName = userObject
				.getOrganizationName(organizationId);
		request.setAttribute("lastName", lastName);
		request.setAttribute(HardCodeValues.EMAILID, emailId);
		request.setAttribute(HardCodeValues.MOBILE_NUMBER, mobileNumber);
		request.setAttribute("firstNamee", userNameForAdminOrUser);
		request.setAttribute(HardCodeValues.PASSWORD, passWordForAdminOrUser);
		request.setAttribute(HardCodeValues.ORGANIZATION_ID, organizationId);
		if (checkUser.equals(HardCodeValues.USER)) {
			dispatcher = request.getRequestDispatcher("ModifyUser.jsp?user="
					+ userName + "&type=releaseHideUser&orgId="
					+ organizationName);
			if (dispatcher != null)
				dispatcher.forward(request, response);
		} else if (checkUser.equals(HardCodeValues.ADMIN)) {
			dispatcher = request.getRequestDispatcher("ModifyAdmin.jsp?user="
					+ userName + "&type=releaseHideAdmin&orgId="
					+ organizationId);
			if (dispatcher != null)
				dispatcher.forward(request, response);
		}
	}
}
