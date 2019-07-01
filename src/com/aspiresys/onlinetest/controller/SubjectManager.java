package com.aspiresys.onlinetest.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspiresys.onlinetest.hardcode.HardCodeValues;
import com.aspiresys.onlinetest.model.beanclass.CommonBeanClass;

import com.aspiresys.onlinetest.model.dao.FindUserDAO;
import com.aspiresys.onlinetest.model.dao.ManageOrganizationDAO;
import com.aspiresys.onlinetest.model.dao.ManageQuestionsDAO;
import com.aspiresys.onlinetest.model.dao.ManageSubjectDAO;
import com.aspiresys.onlinetest.model.dao.ManageUserDAO;
import com.aspiresys.onlinetest.model.dao.StoreSubjectDetails;

public class SubjectManager {
	ManageUserDAO userObject = new ManageUserDAO();
	ManageQuestionsDAO questionObject = new ManageQuestionsDAO();
	ManageSubjectDAO subjectObject = new ManageSubjectDAO();
	ManageOrganizationDAO organizationObj=new ManageOrganizationDAO();
	FindUserDAO userDaoObj = new FindUserDAO();

	/**
	 * this method will be called when, Submit button equal "add"(from servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws ServletException, IOException,
			ClassNotFoundException {
		ArrayList<String> arrayListObjectForSubject;
		// declare the needed variables
		String subjectName = request.getParameter(HardCodeValues.ADD_NEW_SUBJECT);
		String organizationName=request.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME);
		if ((userDaoObj.uniqueIdentifier("subject", "subjectName",
				subjectName, 0))) {
			response.sendRedirect("AddSubject.jsp?duplicateSubject=Duplicate Subject!! Try someother name&subjectName="
					+ subjectName+"&organizationName="+organizationName);
		} else {
		int organizationId=organizationObj.getOrganizationId(request.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME));
		/*int organizationId = subjectObject.getOrganizationId(Integer.parseInt(request
				.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME)));*/
		CommonBeanClass beanObject = new CommonBeanClass();
		beanObject.setSubjectName(subjectName);
				//+"-"+request.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME));
		beanObject.setOrganizationId(organizationId);
		subjectObject.addSubject(beanObject);
		arrayListObjectForSubject = subjectObject.getListOfSubjectName();
		session.setAttribute(HardCodeValues.LIST_OF_SUBJECT,
				arrayListObjectForSubject);

		ArrayList<StoreSubjectDetails> completeSubjectDetails=new ArrayList<StoreSubjectDetails>();
		
		completeSubjectDetails=subjectObject.getSubjectDatas();
		session.setAttribute("completeSubjectDetails", completeSubjectDetails);
		
		//set the initial number and last number( used for display the whole details of user, subject and organisation)
		session.setAttribute("numberOfSubject",completeSubjectDetails.size());
		
		session.setAttribute("initialNumberSubject",0);
		if(completeSubjectDetails.size()>10){
			session.setAttribute("lastNumberSubject",10);
		}
		else{
			session.setAttribute("lastNumberSubject",completeSubjectDetails.size());
		}
		response.sendRedirect("AddSubject.jsp?subjectSuccess=Subject Added Successfully");
		}
	}

	/**
	 * this method will be called when, Submit button equal "modifySubject"(from
	 * servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void modifySubject(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException, ClassNotFoundException {
		ArrayList<String> arrayListObjectForSubject;
		// declare the needed variables
		String subjectName = request
				.getParameter("SubjectName");
		String modifiedName = request
				.getParameter(HardCodeValues.MODIFIED_NAME);
		String organizationName = request
				.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME);
		if ((userDaoObj.uniqueIdentifier("subject", "subjectName",
				modifiedName, 0))) {
			response.sendRedirect("ModifySubject.jsp?duplicateSubject=Duplicate Subject!! Try someother name&subjectName="
					+ subjectName+"&organizationName="+organizationName+"&modifiedName="+modifiedName);
		}
		else if (subjectName.equals(HardCodeValues.SELECT)) {
			response.sendRedirect("ModifySubject.jsp?subjectSuccess=Please Select The Subject");
		} else {
			
			int organizationId = userObject.getOrganizationId(organizationName);
			CommonBeanClass beanObject = new CommonBeanClass();
			beanObject.setSubjectName(subjectName);
			beanObject.setOrganizationId(organizationId);
			beanObject.setNewSubjectName(modifiedName);
					//+"-"+organizationName);
			subjectObject.updateSubject(beanObject);
			arrayListObjectForSubject = subjectObject.getListOfSubjectName();
			session.setAttribute(HardCodeValues.LIST_OF_SUBJECT,
					arrayListObjectForSubject);
			ArrayList<StoreSubjectDetails> completeSubjectDetails=new ArrayList<StoreSubjectDetails>();
			
			completeSubjectDetails=subjectObject.getSubjectDatas();
			session.setAttribute("completeSubjectDetails", completeSubjectDetails);
			
			//set the initial number and last number( used for display the whole details of user, subject and organisation)
			session.setAttribute("numberOfSubject",completeSubjectDetails.size());
			
			session.setAttribute("initialNumberSubject",0);
			if(completeSubjectDetails.size()>10){
				session.setAttribute("lastNumberSubject",10);
			}
			else{
				session.setAttribute("lastNumberSubject",completeSubjectDetails.size());
			}
			response.sendRedirect("ModifySubject.jsp?subjectSuccess=Subject Modified Successfully");
		}
	}

	/**
	 * this method will be called when, Submit button equal "deleteSubject"(from
	 * servlet)
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void deleteSubject(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException, ClassNotFoundException {
		ArrayList<String> arrayListObjectForSubject;
		// declare the needed variables
		String subjectName = request
				.getParameter("SubjectName");
		
		if (subjectName.equals(HardCodeValues.SELECT)) {
			response.sendRedirect("DeleteSubject.jsp?subjectSuccess=Please Select The Subject");
		} else {
			/*String organizationName = request
					.getParameter(HardCodeValues.SELECTED_ORGANIZATION_NAME);*/
			int subjectId = questionObject.getSubjectId(subjectName);
			CommonBeanClass beanObject = new CommonBeanClass();
			beanObject.setSubjectId(subjectId);
			subjectObject.deleteSubject(beanObject);
			arrayListObjectForSubject = subjectObject.getListOfSubjectName();
			session.setAttribute(HardCodeValues.LIST_OF_SUBJECT,
					arrayListObjectForSubject);
			ArrayList<StoreSubjectDetails> completeSubjectDetails=new ArrayList<StoreSubjectDetails>();
			
			completeSubjectDetails=subjectObject.getSubjectDatas();
			session.setAttribute("completeSubjectDetails", completeSubjectDetails);
			
			//set the initial number and last number( used for display the whole details of user, subject and organisation)
			session.setAttribute("numberOfSubject",completeSubjectDetails.size());
			
			session.setAttribute("initialNumberSubject",0);
			if(completeSubjectDetails.size()>10){
				session.setAttribute("lastNumberSubject",10);
			}
			else{
				session.setAttribute("lastNumberSubject",completeSubjectDetails.size());
			}
			response.sendRedirect("DeleteSubject.jsp?subjectSuccess=Subject Deleted Successfully");
		}
	}
}

