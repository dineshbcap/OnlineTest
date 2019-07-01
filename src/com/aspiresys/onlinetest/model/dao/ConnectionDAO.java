package com.aspiresys.onlinetest.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionDAO {
	private static Connection connection;
	

	/**
	 * This method is used for establish the Database connection
	 * 
	 * @return connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static Connection DBConnectionMethod() throws SQLException,
			ClassNotFoundException{
		
		//create object for the properties class 
		Properties prop = new Properties();
		
	    try {
	    	//load the properties file
			prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/com/aspiresys/onlinetest/model/dao/DBCredentialsAndTables.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//get the values from the properties file 
		String connectionString = prop.getProperty("connectionString");
		String dataBaseName = prop.getProperty("dataBaseName");
		String driverName = prop.getProperty("driverName");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		
		Class.forName(driverName);
		connection = DriverManager.getConnection(connectionString
				+ dataBaseName, user, password);
		return (connection);

	}

	/**
	 * This method is used for close the Database connection(resultset,
	 * statement and connection)
	 * 
	 * @param resultSet
	 * @param statement
	 * @param connectionObject
	 * @throws SQLException
	 */
	public static void DBClose(ResultSet resultSet, Statement statement,
			Connection connectionObject) throws SQLException {
		resultSet.close();
		statement.close();
		connectionObject.close();
	}

	/**
	 * This method is used for close the Database connection(statement and
	 * connection)
	 * 
	 * @param statement
	 * @param connectionObject
	 * @throws SQLException
	 */
	public static void DBClose(Statement statement, Connection connectionObject)
			throws SQLException {
		statement.close();
		connectionObject.close();
	}
}
