package com.aspiresys.onlinetest.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspiresys.onlinetest.hardcode.HardCodeValues;
import com.aspiresys.onlinetest.model.dao.ManageFilterDAO;
import com.aspiresys.onlinetest.model.dao.StoreResult;


public class FinalResult {
	public void filterBy(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException, ClassNotFoundException {
		// declare the needed variables
		String organizationName=request.getParameter("selectedOrganizationName1");
		String subjectName=request.getParameter("SubjectName");
		String fromDate = null;
		String toDate = null;
		String mark=request.getParameter("MarkRange");
		String finalQuery;
		
		if((!(request.getParameter("FromDate").equals("-DD-")))&&(!(request.getParameter("FromMonth").equals("-MM-")))&&(!(request.getParameter("FromYear").equals("-YYYY-")))){
			fromDate=request.getParameter("FromYear")+"-"+request.getParameter("FromMonth")+"-"+request.getParameter("FromDate");
		}
		else{
			fromDate="All";
		}
		if((!(request.getParameter("ToDate").equals("-DD-")))&&(!(request.getParameter("ToMonth").equals("-MM-")))&&(!(request.getParameter("ToYear").equals("-YYYY-")))){
			toDate=request.getParameter("ToYear")+"-"+request.getParameter("ToMonth")+"-"+request.getParameter("ToDate");
		}
		else{
			toDate="All";
		}
		ManageFilterDAO filterObj=new ManageFilterDAO();
		if(!(organizationName.equals("-select-"))){
			organizationName="subjectid in (select subjectid from subject where organizationid=(select organizationId from organization where organizationName='"+organizationName+"'))";
		}
		else{
			organizationName="subjectid in (select subjectid from subject where organizationid in (select organizationId from organization))";
		}
		if(!(subjectName.equals("-select-"))){
			subjectName="subjectId = (select subjectid from subject where subjectName='"+subjectName+"')";
		}
		else{
			subjectName="subjectId in (select subjectid from subject)";
		}
		if(!(fromDate.equals("All"))){
			fromDate="'"+fromDate+"'";
		}
		else{
			fromDate="(select min(dateOfWrite) from userMark)";
		}
		
		if(!(toDate.equals("All"))){
			toDate="'"+toDate+"'";
		}
		else{
			toDate="(select max(dateOfWrite) from userMark)";
		}
		if(!(mark.equals(""))){
			mark="(mark>="+mark+")";
		}
		else{
			mark="(mark>=(select min(mark) from usermark))";
		}
		finalQuery="select * from usermark where "+mark+" && "+subjectName+" && dateofwrite between "+fromDate+" AND "+toDate+" && "+organizationName;
		System.out.println(finalQuery);
		ArrayList<StoreResult> finalResult=filterObj.getFinalResult(finalQuery);
		session.setAttribute(HardCodeValues.FINAL_REPORT,finalResult);
		session.setAttribute("numberOfResults",finalResult.size());
		if((finalResult.size())>10){
			session.setAttribute("initialNumber",0);
			session.setAttribute("lastNumber",10);
			response.sendRedirect("Result.jsp?initialNumber=0&lastNumber=10");
		}
		else{
			session.setAttribute("initialNumber",0);
			session.setAttribute("lastNumber",finalResult.size());
			response.sendRedirect("Result.jsp?initialNumber=0&lastNumber="+finalResult.size());
		}
		
	}
}
