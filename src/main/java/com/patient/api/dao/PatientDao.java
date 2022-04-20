package com.patient.api.dao;
import com.patient.api.model.PatientResponse;

/*
 * This interface contains all the APIs to add, update, delete and get patient data
 */
public interface PatientDao 
{
	// With the help of this addPatient function we can add patient details to database

	public int addPatient(PatientResponse patientResponse);
	
	// getPatient method gives us the patient details by patient id

	public PatientResponse getPatient(String patientId);

	// Update method is used to update the patient details

	public void updatePatient(PatientResponse patientResponse);

	// Delete patient details with patient Id

	public void deletePatient(String patientId);
}
