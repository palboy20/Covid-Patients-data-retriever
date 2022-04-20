package com.patient.api.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import com.patient.api.connection.PatientDatabaseConnection;
import com.patient.api.model.PatientResponse;

/*
 * This class gives the definition of all the methods of PatientDao interface
 */
public class PatientDaoImpl implements PatientDao 
{
	static Logger logger = Logger.getLogger(PatientDaoImpl.class.getName());
	static Connection connection = PatientDatabaseConnection.getConnection();
	PatientResponse patientResponse = new PatientResponse(); 
	private PreparedStatement preparedStatement;

	/*
	 * This function is responsible for adding new patient to the database from the given {@link PatientResponse}.
	 * @param The {@link PateintResponse}. Cannot be null.
	 * @return integer number 0 if add patient was unsuccessful otherwise 1.
	 */
	@Override
	public int addPatient(PatientResponse patientResponse) 
	{
		if (patientResponse == null)
		{
			throw new RuntimeException("An unexpected error occured, patientResponse object can not be null PatientDaoImpl:addPatient.");
		}
		
		int result = 0;
		try  
		{
			String query = "INSERT INTO `covid_patient_data`.`patient_detail`"
					+ " (`patientid`,`certificatenumber`, `hasvaccinated`, `istestedpositive`, `patientaadharnumber`, `patientname`, `phoneNumber`)"
					+ " VALUES (?,?,?,?,?,?,?)";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, patientResponse.getPatientId());
			preparedStatement.setString(2, patientResponse.getCertificateNumber());
			preparedStatement.setString(3, patientResponse.getHasVaccinated());
			preparedStatement.setString(4, patientResponse.getIsTestedPositive());
			preparedStatement.setString(5, patientResponse.getPatientAadharNumber());
			preparedStatement.setString(6, patientResponse.getPatientName());
			preparedStatement.setString(7, patientResponse.getPhoneNumber());
			result = preparedStatement.executeUpdate();
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in add patient SQL query " 
		            + "PatientDaoImpl:addPatient",e);
		}
		return result;
	}
	
	/*
	 * This function is responsible for retrieving patient details from the database for the given {@link patientId}.
	 * @param The {@link patientId}. Cannot be zero or negative value.
	 * @return patient details.
	 */
	@Override
	public PatientResponse getPatient(String patientId) 
	{
		if (Integer.parseInt(patientId) > 0)
		{
			try  
			{
				String query = "SELECT * FROM covid_patient_data.patient_detail where patientid=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, patientId);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) 
				{
					patientResponse.setPatientId(resultSet.getString("patientid"));
					patientResponse.setPatientName(resultSet.getString("patientname"));
					patientResponse.setPatientAadharNumber(resultSet.getString("patientaadharnumber"));
					patientResponse.setCertificateNumber(resultSet.getString("certificatenumber"));
					patientResponse.setPhoneNumber(resultSet.getString("phonenumber"));
					patientResponse.setHasVaccinated(resultSet.getString("hasvaccinated"));
					patientResponse.setIsTestedPositive(resultSet.getString("istestedpositive"));
				}
			} 
			catch (Exception e)   
			{
				throw new RuntimeException("An unexpected error in get patient SQL query " + "PatientDaoImpl:getPatient", e);
			}
		} 
		else 
		{
			throw new RuntimeException("An unexpected error occured while entring the patientId. patientId can not be zero or negative PatientDaoImpl:getPatient");
		}
		return patientResponse;
	}

	/*
	 * This function is responsible for updating patient name to the database from the given {@link PatientResponse}.
	 * @param The {@link PateintResponse}. Cannot be null.
	 * @return This is void type, so it will not return anything.
	 */
	@Override
	public void updatePatient(PatientResponse patientResponse) 
	{
		if (patientResponse == null) 
		{
			throw new RuntimeException("An unexpected error occured, patientResponse object can not be null PatientDaoImpl:updatePatient.");
		}
		
		try 
		{
			String query = "update covid_patient_data.patient_detail set patientname=? where patientid =?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, patientResponse.getPatientName());
			preparedStatement.setString(2, patientResponse.getPatientId());
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in update patient SQL query " + "PatientDaoImpl:updatePatient", e);
		}
	}

	/* 
	 * This function is responsible for deleting patient from the database for the given {@link patientId}.
	 * @param The {@link patientId}. Cannot be zero or negative value.
	 * @return The function type is void, so it will no return anything.
	 */
	@Override
	public void deletePatient(String patientId) 
	{
		if (Integer.parseInt(patientId) <= 0) 
		{
			throw new RuntimeException("An unexpected error occured, patientId can not be zero or negative PatientDaoImpl:deletePatient.");
		}
		try 
		{
			String query = "delete from covid_patient_data.patient_detail where patientId =?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, patientId);
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in delete patient SQL query "
		            + "PatientDaoImpl:deletePatient", e);
		}
	}
}
