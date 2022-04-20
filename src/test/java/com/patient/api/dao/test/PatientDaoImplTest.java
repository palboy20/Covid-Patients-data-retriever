package com.patient.api.dao.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockedStatic.Verification;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.unitils.reflectionassert.ReflectionAssert;

import com.patient.api.connection.PatientDatabaseConnection;
import com.patient.api.dao.PatientDaoImpl;
import com.patient.api.model.PatientResponse;

@ExtendWith(value = { MockitoExtension.class })
public class PatientDaoImplTest  
{
	static Logger logger = Logger.getLogger(PatientDaoImplTest.class.getName());
	@Mock
	private Connection mockConnection;
	@Mock
	private PreparedStatement mockPreparedStatement;
	@Mock
	private ResultSet mockResultset;
	private static MockedStatic<PatientDatabaseConnection> mocked;
	PatientResponse patientResponseMock;

	@BeforeEach
	public void setUp()   
	{ 
		patientResponseMock = mock(PatientResponse.class);
		mocked = Mockito.mockStatic(PatientDatabaseConnection.class);
		mocked.when((Verification) PatientDatabaseConnection.getConnection()).thenReturn(mockConnection);
	}

	@AfterEach
	public void close()  
	{ 
		mocked.close();
	} 
    @Disabled 
	@Test
	public void addPatientTest() throws SQLException  
	{
		String sqlString = "INSERT INTO `covid_patient_data`.`patient_detail`"
				+ " (`patientid`,`certificatenumber`, `hasvaccinated`, `istestedpositive`, `patientaadharnumber`, `patientname`, `phoneNumber`)"
				+ " VALUES (?,?,?,?,?,?,?)";

		final String patientId = "101";
		final String patientNameString = "Ranjeet";
		final String hasVaccinatedString = "Yes";
		final String patientAadharNumber = "123021323222";
		final String phoneNumber = "6363636329";
		final String isTestedPositive = "Yes";
		final String certificateNumber = "RS2914612";
		final int resultStatus;

		when(mockConnection.prepareStatement(sqlString)).thenReturn(mockPreparedStatement);
		when(patientResponseMock.getPatientId()).thenReturn(patientId);
		when(patientResponseMock.getPatientName()).thenReturn(patientNameString);
		when(patientResponseMock.getPatientAadharNumber()).thenReturn(patientAadharNumber);
		when(patientResponseMock.getPhoneNumber()).thenReturn(phoneNumber);
		when(patientResponseMock.getHasVaccinated()).thenReturn(hasVaccinatedString);
		when(patientResponseMock.getCertificateNumber()).thenReturn(certificateNumber);
		when(patientResponseMock.getIsTestedPositive()).thenReturn(isTestedPositive);
		when(mockPreparedStatement.executeUpdate()).thenReturn(1);
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		resultStatus = patientDaoImpl.addPatient(patientResponseMock);
		assertEquals(1, resultStatus);
	}

	@Test
	public void addPatientTestRuntimeException() 
    {
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		Assertions.assertThrows(RuntimeException.class, () -> patientDaoImpl.addPatient(patientResponseMock));
	}

	@Test
	public void testNullObjectInAddPatient() 
    {
		patientResponseMock = null;
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		Assertions.assertThrows(RuntimeException.class, () -> patientDaoImpl.addPatient(patientResponseMock));
	}

	/* Unit Test for getPatient */
	@Disabled
	@Test
	public void getPatientTest() throws SQLException  
	{
		String sqlString = "SELECT * FROM covid_patient_data.patient_detail where patientid=?";

		final String patientId = "101";
		final String patientNameString = "Ranjeet";
		final String hasVaccinatedString = "YES";
		final String patientAadharNumber = "123021323222";
		final String phoneNumber = "6363636329";
		final String isTestedPositive = "No";
		final String certificateNumber = "RS291461";

		PatientResponse expectedResponse = new PatientResponse();
		expectedResponse.setPatientId(patientId);
		expectedResponse.setPatientName(patientNameString);
		expectedResponse.setPatientAadharNumber(patientAadharNumber);
		expectedResponse.setCertificateNumber(certificateNumber);
		expectedResponse.setPhoneNumber(phoneNumber);
		expectedResponse.setHasVaccinated(hasVaccinatedString);
		expectedResponse.setIsTestedPositive(isTestedPositive);

		when(mockConnection.prepareStatement(sqlString)).thenReturn(mockPreparedStatement);
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultset);
		when(mockResultset.next()).thenReturn(true).thenReturn(false);
		when(mockResultset.getString("patientid")).thenReturn(patientId);
		when(mockResultset.getString("patientname")).thenReturn(patientNameString);
		when(mockResultset.getString("patientaadharnumber")).thenReturn(patientAadharNumber);
		when(mockResultset.getString("certificatenumber")).thenReturn(certificateNumber);
		when(mockResultset.getString("phonenumber")).thenReturn(phoneNumber);
		when(mockResultset.getString("hasvaccinated")).thenReturn(hasVaccinatedString);
		when(mockResultset.getString("istestedpositive")).thenReturn(isTestedPositive);

		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		PatientResponse actualResponse = patientDaoImpl.getPatient(patientId);
		ReflectionAssert.assertReflectionEquals(actualResponse, expectedResponse); 
	} 
	
//	@Disabled
	@Test
	public void testRuntimeExceptionGetPatient() 
	{
		final String patientId = "101";
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		Assertions.assertThrows(RuntimeException.class, () -> 
		patientDaoImpl.getPatient(patientId));  
	} 
 
	@Test 
	public void testNegativePatientIdGetPatient() 
	{
		final String patientId = "-1";
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		Assertions.assertThrows(RuntimeException.class, () -> 
		patientDaoImpl.getPatient(patientId));
	} 
	 
	@Test
	public void testZeroPatientIdInGetPatient() 
	{
		final String patientId = "0";
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		Assertions.assertThrows(RuntimeException.class, () -> patientDaoImpl.getPatient(patientId));
	}

	/* Unit Test for Update Methods */
    @Disabled
	@Test
	public void updatePatientTest() throws SQLException 
    {
		final String sql = "update covid_patient_data.patient_detail set patientname=? where patientid =?";

		final String patientId = "101";
		final String patientNameString = "Ranjeet";
		when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
		when(patientResponseMock.getPatientId()).thenReturn(patientId);
		when(patientResponseMock.getPatientName()).thenReturn(patientNameString);
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		patientDaoImpl.updatePatient(patientResponseMock);
	}
 
	@Test
	public void testNullObjectInUpdatePatient() 
    {
		patientResponseMock = null;
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		Assertions.assertThrows(RuntimeException.class, () -> patientDaoImpl.updatePatient(patientResponseMock));
	}

	@Test
	public void updatePatientTestRuntimeException() 
	{
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		Assertions.assertThrows(RuntimeException.class, () -> patientDaoImpl.addPatient(patientResponseMock));
	}

	/* UT for deletePatient */
	
	@Disabled 
	@Test
	public void deletePatientTest() throws RuntimeException, SQLException 
	{
		String sqlString = "delete from covid_patient_data.patient_detail where patientId =?";

		final String patientId = "101";
		when(mockConnection.prepareStatement(sqlString)).thenReturn(mockPreparedStatement);
		when(mockPreparedStatement.executeUpdate()).thenReturn(1);
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		patientDaoImpl.deletePatient(patientId);
	}
	
	@Test 
	public void testRuntimeExceptionInDeletePatient()  
	{
		final String patientId = "101";
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		Assertions.assertThrows(RuntimeException.class, () -> patientDaoImpl.deletePatient(patientId));
	}

	@Test
	public void testNegativePatientIdInDeletePatient() 
	{
		final String patientId = "-1";
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		Assertions.assertThrows(RuntimeException.class, () -> patientDaoImpl.deletePatient(patientId));
	}

	@Test
	public void testZeroPatientIdInDeletePatient()  
    {
		final String patientId = "0";
		PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
		Assertions.assertThrows(RuntimeException.class, () -> patientDaoImpl.deletePatient(patientId));
	}
}
