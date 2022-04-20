package com.patient.api.model;

/*
 * This class is the Bean class that contains all the patient parameters
 * We mentioned getter and setter method in this class
 */

public class PatientResponse 
{
	private String patientId;
	private String patientAadharNumber;
	private String patientName;
	private String phoneNumber;
	private String isTestedPositive;
	private String certificateNumber;
	private String hasVaccinated;

	public PatientResponse(String patientId, String patientAadharNumber, String patientName, String phoneNumber,
			String isTestedPositive, String certificateNumber, String hasVaccinated) 
	{
		this.patientId = patientId;
		this.patientAadharNumber = patientAadharNumber;  
		this.patientName = patientName;
		this.phoneNumber = phoneNumber;
		this.isTestedPositive = isTestedPositive;
		this.certificateNumber = certificateNumber;
		this.hasVaccinated = hasVaccinated;
	}

	public PatientResponse() 
	{
		super();
	}

	public String getPatientId() 
	{
		return patientId;
	}

	public void setPatientId(String patientId) 
	{
		this.patientId = patientId;
	}

	public String getPatientName() 
	{
		return patientName;
	}

	public void setPatientName(String patientName) 
	{
		this.patientName = patientName;
	}

	public String getPatientAadharNumber() 
	{
		return patientAadharNumber;
	}

	public void setPatientAadharNumber(String patientAadharNumber) 
	{
		this.patientAadharNumber = patientAadharNumber;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	public String getIsTestedPositive() 
	{
		return isTestedPositive;
	}

	public void setIsTestedPositive(String isTestedPositive) 
	{
		this.isTestedPositive = isTestedPositive;
	}

	public String getHasVaccinated() 
	{
		return hasVaccinated;
	}

	public void setHasVaccinated(String hasVaccinated) 
	{
		this.hasVaccinated = hasVaccinated;
	}

	public String getCertificateNumber() 
	{
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) 
	{
		this.certificateNumber = certificateNumber;
	}

	@Override
	public String toString() 
	{
		return "PatientResponse [patientId=" + patientId + ", patientAadharNumber=" + patientAadharNumber
				+ ", patientName=" + patientName + ", phoneNumber=" + phoneNumber + ", isTestedPositive="
				+ isTestedPositive + ", certificateNumber=" + certificateNumber + ", hasVaccinated=" + hasVaccinated
				+ "]";
	}
}
