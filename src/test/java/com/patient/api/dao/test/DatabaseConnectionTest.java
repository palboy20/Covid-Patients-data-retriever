package com.patient.api.dao.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.patient.api.connection.PatientDatabaseConnection;

@ExtendWith(value = { MockitoExtension.class })
class DatabaseConnectionTest 
{
	@Mock
	Connection mockConnection;
	@Mock
	PreparedStatement mockPreparedStatement;
	@Mock
	ResultSet mockResultset; 
	@Mock
	Logger logger;

	@Test
	public void testConnection() throws Exception 
	{
		try 
		{
			String url = "jdbc:mysql:// localhost:3306/covid_patient_data";
			String user = "root";
			String pass = "root";
			
			Connection conn = mock(Connection.class);
			mockStatic(DriverManager.class);
			when(DriverManager.getConnection(url, user, pass)).thenReturn(conn);
			PatientDatabaseConnection.getConnection();
		} 
		catch (Exception e) 
		{
			Assertions.assertThrows(RuntimeException.class, () -> PatientDatabaseConnection.getConnection());
		}
	}
}
