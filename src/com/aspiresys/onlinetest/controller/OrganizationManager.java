package com.aspiresys.onlinetest.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspiresys.onlinetest.hardcode.HardCodeValues;
import com.aspiresys.onlinetest.logfiles.StoreUserDetail;
import com.aspiresys.onlinetest.model.beanclass.CommonBeanClass;
import com.aspiresys.onlinetest.model.dao.FindUserDAO;
import com.aspiresys.onlinetest.model.dao.ManageOrganizationDAO;
import com.aspiresys.onlinetest.model.dao.ManageSubjectDAO;
import com.aspiresys.onlinetest.model.dao.ManageUserDAO;
import com.aspiresys.onlinetest.model.dao.StoreOrganizationDetails;
import com.aspiresys.onlinetest.model.dao.StoreSubjectDetails;
import com.aspiresys.onlinetest.model.dao.StoreUsersDetails;

public class OrganizationManager {
	ManageOrganizationDAO organizationObject = new ManageOrganizationDAO();
	ArrayList<String> listOfOrganization;
	FindUserDAO userDaoObj = new FindUserDAO();
	ManageSubjectDAO subjectObject = new ManageSubjectDAO();
	ManageUserDAO userObject = new ManageUserDAO();
	/**
	 * this method will be called when, Submit button equal
	 * "addOrganization"(from servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void addOrganization(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException, ClassNotFoundException,NullPointerException {
		if (session.getAttribute("firstName") == null) {
			throw new NullPointerException("");
		}
		// declare the needed variables
		String organizationName = request
				.getParameter(HardCodeValues.ADD_NEW_ORGANIZATION);
		if ((userDaoObj.uniqueIdentifier("organization", "organizationName",
				organizationName, 0))) {
			response.sendRedirect("AddOrganization.jsp?duplicateOrganization=Duplicate Organization!! Try someother name&organizationName="
					+ organizationName);
		} else {
			String description = request.getParameter("description");
			if (description == "") {
				description = "There Is No Description Specified!";
			}
			CommonBeanClass beanObject = new CommonBeanClass();
			beanObject.setOrganizationName(organizationName);
			beanObject.setDescription(description);
			organizationObject.addOrganization(beanObject);
			listOfOrganization = organizationObject.getListOfOrganizationName();
			session.setAttribute(HardCodeValues.LIST_OF_ORGANIZATION,
					listOfOrganization);
			
			ArrayList<StoreUsersDetails> completeUserDetails=new ArrayList<StoreUsersDetails>();
			ArrayList<StoreOrganizationDetails> completeOrganizationDetails=new ArrayList<StoreOrganizationDetails>();
			ArrayList<StoreSubjectDetails> completeSubjectDetails=new ArrayList<StoreSubjectDetails>();
			
			completeUserDetails=userObject.getUserDatas();
			completeOrganizationDetails=organizationObject.getOrganizationDatas();
			completeSubjectDetails=subjectObject.getSubjectDatas();
			session.setAttribute("completeUserDetails",completeUserDetails);
			session.setAttribute("completeOrganizationDetails", completeOrganizationDetails);
			session.setAttribute("completeSubjectDetails", completeSubjectDetails);
			
			//set the initial number and last number( used for display the whole details of user, subject and organisation)
			session.setAttribute("numberOfOrganization",completeOrganizationDetails.size());
			session.setAttribute("numberOfSubject",completeSubjectDetails.size());
			session.setAttribute("numberOfUser",completeUserDetails.size());
			
			session.setAttribute("initialNumberUser",0);
			session.setAttribute("initialNumberSubject",0);
			session.setAttribute("initialNumberOrganization",0);


			if (completeOrganizationDetails.size() > 10) {
				session.setAttribute("lastNumberOrganization", 10);
			} else {
				session.setAttribute("lastNumberOrganization",
						completeOrganizationDetails.size());
			}
			response.sendRedirect("AddOrganization.jsp?organizationSuccess=addOrganization&dummy=dummy");
		}
	}

	/**
	 * this method will be called when, Submit button equal
	 * "modifyOrganization"(from servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void modifyOrganization(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException, ClassNotFoundException {
		// declare the needed variables
		String organizationName = (String) request
				.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME);
		String newOrganizationName = request
				.getParameter(HardCodeValues.MODIFIED_ORGANIZATION_NAME);
		if (!(organizationName.equals(newOrganizationName))) {
			if ((userDaoObj.uniqueIdentifier("organization",
					"organizationName", newOrganizationName, 0))) {
				response.sendRedirect("ModifyOrganization.jsp?duplicateOrganization=Duplicate Organization!! Try someother name&organizationName="
						+ organizationName+"&newOrganizationName="+newOrganizationName);
			}
			else{
				modifyOrganizationHelper(request,response,session,organizationName,newOrganizationName);
			}
		} else {
			modifyOrganizationHelper(request,response,session,organizationName,newOrganizationName);
		}
	}
	/**
	 * this function is helper function for modify organizaion
	 * @param request
	 * @param response
	 * @param session
	 * @param organizationName
	 * @param newOrganizationName
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void modifyOrganizationHelper(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,String organizationName,String newOrganizationName)
			throws ServletException, IOException, ClassNotFoundException {
		String description = request.getParameter("description");
		System.out.println(description + "test");
		if (description == "") {
			description = "There Is No Description Specified!";
		}
		CommonBeanClass beanObject = new CommonBeanClass();
		beanObject.setOrganizationName(organizationName);
		beanObject.setNewOrganizationName(newOrganizationName);
		beanObject.setDescription(description);
		organizationObject.updateOrganization(beanObject);
		listOfOrganization = organizationObject.getListOfOrganizationName();
		session.setAttribute(HardCodeValues.LIST_OF_ORGANIZATION,
				listOfOrganization);
		ArrayList<StoreUsersDetails> completeUserDetails=new ArrayList<StoreUsersDetails>();
		ArrayList<StoreOrganizationDetails> completeOrganizationDetails=new ArrayList<StoreOrganizationDetails>();
		ArrayList<StoreSubjectDetails> completeSubjectDetails=new ArrayList<StoreSubjectDetails>();
		
		completeUserDetails=userObject.getUserDatas();
		completeOrganizationDetails=organizationObject.getOrganizationDatas();
		completeSubjectDetails=subjectObject.getSubjectDatas();
		session.setAttribute("completeUserDetails",completeUserDetails);
		session.setAttribute("completeOrganizationDetails", completeOrganizationDetails);
		session.setAttribute("completeSubjectDetails", completeSubjectDetails);
		
		//set the initial number and last number( used for display the whole details of user, subject and organisation)
		session.setAttribute("numberOfOrganization",completeOrganizationDetails.size());
		session.setAttribute("numberOfSubject",completeSubjectDetails.size());
		session.setAttribute("numberOfUser",completeUserDetails.size());
		
		session.setAttribute("initialNumberUser",0);
		session.setAttribute("initialNumberSubject",0);
		session.setAttribute("initialNumberOrganization",0);

		if (completeOrganizationDetails.size() > 10) {
			session.setAttribute("lastNumberOrganization", 10);
		} else {
			session.setAttribute("lastNumberOrganization",
					completeOrganizationDetails.size());
		}
		System.out.println("ready to transfer");
		response.sendRedirect("ModifyOrganization.jsp?organizationSuccess=modifyOrganization&dummy=dummy");
		
		
		
	}
	/**
	 * this method will be called when, Submit button equal
	 * "deleteOrganization"(from servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void deleteOrganization(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ClassNotFoundException, IOException {

		// declare the needed variables
		String organizationName = request
				.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME);
		int organizationId = organizationObject
				.getOrganizationId(organizationName);
		CommonBeanClass beanObject = new CommonBeanClass();
		beanObject.setOrganizationId(organizationId);
		organizationObject.deleteOrganization(beanObject);
		listOfOrganization = organizationObject.getListOfOrganizationName();
		session.setAttribute(HardCodeValues.LIST_OF_ORGANIZATION,
				listOfOrganization);
		ArrayList<StoreUsersDetails> completeUserDetails=new ArrayList<StoreUsersDetails>();
		ArrayList<StoreOrganizationDetails> completeOrganizationDetails=new ArrayList<StoreOrganizationDetails>();
		ArrayList<StoreSubjectDetails> completeSubjectDetails=new ArrayList<StoreSubjectDetails>();
		
		completeUserDetails=userObject.getUserDatas();
		completeOrganizationDetails=organizationObject.getOrganizationDatas();
		completeSubjectDetails=subjectObject.getSubjectDatas();
		session.setAttribute("completeUserDetails",completeUserDetails);
		session.setAttribute("completeOrganizationDetails", completeOrganizationDetails);
		session.setAttribute("completeSubjectDetails", completeSubjectDetails);
		
		//set the initial number and last number( used for display the whole details of user, subject and organisation)
		session.setAttribute("numberOfOrganization",completeOrganizationDetails.size());
		session.setAttribute("numberOfSubject",completeSubjectDetails.size());
		session.setAttribute("numberOfUser",completeUserDetails.size());
		
		session.setAttribute("initialNumberUser",0);
		session.setAttribute("initialNumberSubject",0);
		session.setAttribute("initialNumberOrganization",0);

		if (completeOrganizationDetails.size() > 10) {
			session.setAttribute("lastNumberOrganization", 10);
		} else {
			session.setAttribute("lastNumberOrganization",
					completeOrganizationDetails.size());
		}
		try {
			response.sendRedirect("DeleteOrganization.jsp?organizationSuccess=deleteOrganization&dummy=dummy");
		} catch (IOException e) {
			StoreUserDetail.storeUserInfo("Error Occured In This User..."
					+ (String) session.getAttribute("name")
					+ "---> Error Detail..." + e);
		}
	}
}
