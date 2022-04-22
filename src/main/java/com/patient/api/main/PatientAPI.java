package com.patient.api.main;

/*
 * This class is responsible to invoke patientOperationMethod
 * We create the object of PatintOperation class to call the
 * patientOperationMethod that contains all four method insert, retrieve, update and delete
 */
public class PatientAPI 
{
	public static void main(String[] args) 
	{
		try 
		{ 
			PatientOperation patientOperation = new PatientOperation();
			patientOperation.patientOperationMethod();
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("There is an error in retrieving the patient covid data PatientAPI : main", e);
		}
	}
}
