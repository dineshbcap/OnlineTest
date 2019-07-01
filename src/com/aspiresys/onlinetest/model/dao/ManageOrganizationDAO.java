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

public class ManageOrganizationDAO {
	private Connection connectionObject;
	private PreparedStatement statementObject;
	private ResultSet resultSetObject;
	private final String ADD_ORGANIZATION = "insert into Organization(organizationName,description) values(?,?)";
	private final String UPDATE_ORGANIZATION = "update Organization set organizationName=?,description=? where organizationName=? ";
	private final String LIST_OF_ORGANIZATION = "select * from Organization";
	//private final String DELETE_ORGANIZATION = "delete Organization,Subject,User from Organization LEFT JOIN Subject ON Organization.OrganizationId=Subject.organizationId LEFT JOIN User ON Organization.OrganizationId=User.OrganizationId where Organization.OrganizationId=?";
	private final String DELETE_ORGANIZATION = "delete from organization where OrganizationId=?";
	public ArrayList<String> listOfOrganization;
	private int databasePossition = 1;

	/**
	 * To add new organization
	 * 
	 * @param beanObject
	 *            contain organization name
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void addOrganization(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(ADD_ORGANIZATION);
			statementObject.setString(databasePossition,
					beanObject.getOrganizationName());
			statementObject.setString(++databasePossition,
					beanObject.getDescription());
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
	 * To update organization name
	 * 
	 * @param beanObject
	 *            contain oldName and new name of organization
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void updateOrganization(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(UPDATE_ORGANIZATION);
			statementObject.setString(databasePossition,
					beanObject.getNewOrganizationName());
			statementObject.setString(++databasePossition,
					beanObject.getDescription());
			statementObject.setString(++databasePossition,
					beanObject.getOrganizationName());
			
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
	 * get list of organization name
	 * 
	 * @return listOfOrganization name
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public ArrayList<String> getListOfOrganizationName()
			throws ClassNotFoundException, IOException {
		listOfOrganization = new ArrayList<String>();
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(LIST_OF_ORGANIZATION);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				listOfOrganization.add(resultSetObject
						.getString(HardCodeValues.ORGANIZATION_NAME));
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
		return listOfOrganization;
	}

	/**
	 * 
	 * @param organizationName
	 * @return organizationId based on organization name
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public int getOrganizationId(String organizationName)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		int organizationId = 0;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement("select organizationId from Organization where organizationName=?");
			statementObject.setString(databasePossition, organizationName);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				organizationId = resultSetObject.getInt("organizationId");
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
		return organizationId;
	}
	/**
	 * 
	 * @param organizationId
	 * @return organizationId based on organization name
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public String getOrganizationName(int organizationId)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		String organizationName = "";
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement("select organizationName from Organization where organizationId=?");
			statementObject.setInt(databasePossition, organizationId);
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				organizationName = resultSetObject.getString("organizationName");
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
	 * To delete organization name
	 * 
	 * @param beanObject
	 *            contain organization name
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 */
	public void deleteOrganization(CommonBeanClass beanObject)
			throws ClassNotFoundException, IOException {
		databasePossition = 1;
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement(DELETE_ORGANIZATION);
			statementObject.setInt(databasePossition,
					beanObject.getOrganizationId());
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
	 * get full list of Organization details
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<StoreOrganizationDetails> getOrganizationDatas() throws ClassNotFoundException, IOException{
		
		int index = 0;
		String organizationName,description;
		ArrayList<StoreOrganizationDetails> organizationDetail=new ArrayList<StoreOrganizationDetails>();
		try {
			connectionObject = ConnectionDAO.DBConnectionMethod();
			statementObject = connectionObject
					.prepareStatement("select organizationName,description from Organization");
			resultSetObject = statementObject.executeQuery();
			while (resultSetObject.next()) {
				organizationName=resultSetObject.getString("organizationName") ;
				description=resultSetObject.getString("description");
				organizationDetail.add(index,new StoreOrganizationDetails(organizationName,description));
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
		return organizationDetail;
		
	}	
}
