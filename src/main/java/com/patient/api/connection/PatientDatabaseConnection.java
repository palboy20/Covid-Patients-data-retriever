package com.patient.api.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * This is the database connection class in which we are making 
 * connection with the help of JDBC driver Manger
 */

public class PatientDatabaseConnection 
{
	private static Connection connection; 
	
	static 
	{
		String url = "jdbc:mysql:// localhost:3306/covid_patient_data";
		String user = "root";
		String pass = "root"; 
		try  
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			throw new RuntimeException("An unexpected error in making driver connection to database " 
	                + "PatientDatabaseConnection", e);
		}
	}
	
	public static Connection getConnection() 
	{
		
		return connection;
	}
}
