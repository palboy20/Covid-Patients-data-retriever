package com.patient.api.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.patient.api.dao.PatientDaoImpl;
import com.patient.api.model.PatientResponse;

/*
 * We are performing insert, get, update and delete operation in this class.
 * This class is responsible to take input of all the parameters of patient in separate method each
 */
public class PatientOperation 
{
	static Logger logger = Logger.getLogger(PatientOperation.class.getName());
	PatientResponse patientResponse = new PatientResponse();
	PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	
	String patientName = "";
	String patientAadhar = "";
	String patientPhone = "";
	String patientVaccinationCompleted = "";
	String patientCertificate = "";
	String patientCovidStatus = "";
	String patientid = "";
	String userResponseYes = "";
	String userResponseNo = "";
	String input = "";
	/*
	 * This function is responsible for taking input Patient Name from the user on console.
	 * Patient Name should be a String type and it can not be exceed 20 character.
	 * Until user will not provide correct patient name, the input function will ask again and again to give
	 * correct input.
	 */
	private void inputPatientName() 
	{
		try 
		{
			boolean loopBreak = true;
			while (loopBreak) 
			{
				System.out.println("Enter Patient Name: ");
				patientName = bufferedReader.readLine();
				int patientNameLength = 20;
				if (patientName.length() > patientNameLength) 
				{
					System.err.println("Patient Name can not exceeds 20 characters.");
				} 
				else if (patientName.matches("[A-Z][a-zA-Z]*" + "[a-zA-z]+([ -,][a-zA-Z]+)*")
						&& patientName.length() <= patientNameLength) 
				{
					loopBreak = false;
				} 
				else 
				{
					System.err.println("Please Enter Correct Name");
				}
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in taking input patient name" 
		            + " PatientOperation:inputPatientName", e);
		}
		patientResponse.setPatientName(patientName);
	}
	
	/*
	 * This function is responsible for taking input Patient Aadhar Number from the user on console.
	 * Aadhar number can not be exceed 12 digits.
	 * Until user will not provide correct 12 digits, the input function will ask again and again to give
	 * correct input.
	 */
	private void inputPatientAadharNumber() 
	{
		try 
		{
			boolean loopBreak = true;
			while (loopBreak) 
			{
				System.out.println("Enter Patient 12 digit Aadhar Number: ");
				patientAadhar = bufferedReader.readLine();
				if (patientAadhar.matches("^[0-9]{12}$") && Long.parseLong(patientAadhar)>0) 
				{
					loopBreak = false;
				} 
				else 
				{
					System.err.println("Please Enter Correct 12 digit Aadhar Number");
				}
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in taking input Aadhar Number "
					+ "PatientOperation:inputPatientAadharNumber", e);
		}
		patientResponse.setPatientAadharNumber(patientAadhar);
	}

	/*
	 * This function is responsible for taking input Patient Phone Number from the user on console.
	 * Phone number can not be exceed 10 digits.
	 * Until user will not provide correct 10 digits, the input function will ask again and again to give
	 * correct input.
	 */
	private void inputPatientPhoneNumber() 
	{
		try 
		{
			boolean loopBreak = true;
			while (loopBreak) 
			{
				System.out.println("Enter Patient 10 digit Phone Number: ");
				patientPhone = bufferedReader.readLine();
				if (patientPhone.matches("^[0-9]{10}$") && Long.parseLong(patientPhone)>0) 
				{
					loopBreak = false;
				} 
				else 
				{
					System.err.println("Please Enter Correct 10 digit Phone Number");
				}
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in taking input patient phone number "
					+ "PatientOperation:inputPatientPhoneNumber", e);
		}
		patientResponse.setPhoneNumber(patientPhone);
	}

	/*
	 * This function is responsible for taking input Patient Vaccination Status from the user on console.
	 * This should be string type and it should be yes or no. 
	 * It will not accept any other input rather than yes or no.
	 * Until user will not provide correct input, the input function will ask again and again to give
	 * correct input.
	 */
	private void inputPatientVaccinationStatus() 
	{
		try 
		{
			boolean loopBreak = true;
			while (loopBreak) 
			{
				System.out.println("Has Patient taken the Covid Vaccine? (Yes/No): ");
				patientVaccinationCompleted = bufferedReader.readLine();
				userResponseYes = "Yes";
				userResponseNo = "No";
				if (userResponseYes.equalsIgnoreCase(patientVaccinationCompleted)
						|| userResponseNo.equalsIgnoreCase(patientVaccinationCompleted)) 
				{
					loopBreak = false;
				} 
				else 
				{
					System.err.println("Please give correct response in Yes/No format");
				}
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in taking input patient vaccination status"
					+ "PatientOperation:inputPatientVaccinationStatus", e);
		}
		patientResponse.setHasVaccinated(patientVaccinationCompleted);
	}

	/*
	 * This function is responsible for taking input Patient Covid Vaccine Certificate Number from the user on console.
	 * When user press yes in covid vaccine status, then only we need to give certificate 
	 * number in input otherwise it will skip.
	 * This should be the digit, any string or combination of both digits and characters.
	 * Until user will not provide correct input, the input function will ask again and again to give
	 * correct input.
	 */
	private void inputPatientVaccineCertificateNumber() 
	{
		try 
		{
			boolean loopBreak = true;
			while (loopBreak) 
			{
				userResponseYes = "Yes";
				if (userResponseYes.equalsIgnoreCase(patientVaccinationCompleted)) 
				{
					System.out.println("Enter Patient Covid-19 Vaccine Certificate Number: ");
					patientCertificate = bufferedReader.readLine();
					int patientCertificateLength = 20;
					if(patientCertificate.matches("[0]*"))
					{
						System.err.println("Certifice number can not be zero. Please enter correct data");
					}
					else if(patientCertificate.length() > patientCertificateLength)
					{
						System.err.println("Certificate Number can not exceeds 20 character.");
					}
					else if (patientCertificate.matches("[0-9a-zA-Z]*") && patientCertificate.length() <= patientCertificateLength) 
					{
						loopBreak = false;
					} 
					else 
					{
						System.err.println("Please Enter Correct Covid-19 Vaccine Certificate Number");
					}
				} 
				else 
				{
					loopBreak = false;
				}
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in taking input patient certificate number "
					+ "PatientOperation:inputPatientVaccineCertificateNumber", e);
		}
		patientResponse.setCertificateNumber(patientCertificate);
	}

	/*
	 * This function is responsible for taking input Patient Covid Status from the user on console.
	 * This should be string type and it should be yes or no. 
	 * It will not accept any other input rather than yes or no.
	 * Until user will not provide correct input, the input function will ask again and again to give
	 * correct input.
	 */
	private void inputPatientCovidStatus() 
	{
		try 
		{
			boolean loopBreak = true;
			while (loopBreak) 
			{
				System.out.println("Was Patient find covid-19 positive? (Yes/No): ");
				patientCovidStatus = bufferedReader.readLine();
				userResponseYes = "Yes";
				userResponseNo = "No";
				if (userResponseYes.equalsIgnoreCase(patientCovidStatus)
						|| userResponseNo.equalsIgnoreCase(patientCovidStatus)) 
				{
					loopBreak = false;
				} 
				else 
				{
					System.err.println("Please give correct status in Yes/No format");
				}
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in taking input patient covid status "
				    + "PatientOperation:inputPatientCovidStatus", e);
		}
		patientResponse.setIsTestedPositive(patientCovidStatus);
	}

	 /*
	 * This function is responsible for calling all the required methods for input the patient details.
	 * Then adding all the input data to patientResponse object and passing that object to addPatient function.
	 * addPatient function passing data to patientDaoImpl object.
	 * We are storing the successful response to addPatientSuccessful variable.
	 * If addPatientSuccessful is 1 the we are printing the patient details otherwise we are logging an error message
	 */
	private void insertNewPatient() 
	{
		try 
		{
			inputPatientName();
			inputPatientAadharNumber();
			inputPatientPhoneNumber();
			inputPatientVaccinationStatus();
			inputPatientVaccineCertificateNumber();
			inputPatientCovidStatus();
			int addPatientSuccessful = patientDaoImpl.addPatient(patientResponse);
			
			if(addPatientSuccessful != 1)
			{
				logger.warning("There is some error occured in adding the patient details");
			}
			else
			{
				System.out.println(addPatientSuccessful + " Patient's data inserted\n");
				System.out.println("Patient Name : " + patientName + "\n" + "Patient Aadhar Number : " + patientAadhar
						+ "\n" + "Patient vaccination Certificate Number : " + patientCertificate + "\n"
						+ "Patient Phone Number : " + patientPhone + "\n" + "Patient Vaccination Status : "
						+ patientVaccinationCompleted + "\n" + "Patient Covid Status : " + patientCovidStatus + "\n"
				);
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in adding new patient data " 
		            + "PatientOperation:insertNewPatient", e);
		}
	}

	/* 
	 * This method is used to take input patient id from user. 
	 * Until user will not provide correct patientId, the input function will ask again and again to give
	 * correct input.
	 */
	private void inputPatientId()
	{
		try 
		{
			boolean loopBreak = true;
			while (loopBreak) 
			{
				System.out.println("Enter Your patientId: ");
				patientid = bufferedReader.readLine();
				int maxPatientId = Integer.MAX_VALUE;
				if (patientid.matches("[0]*")) 
				{
					System.err.println("Patient ID can not be zero. Please Enter correct Patient ID");
				} 
				else if (patientid.matches("[0-9]*") && Integer.parseInt(patientid) < maxPatientId) 
				{
					loopBreak = false;
				}
				else 
				{
					System.err.println("Please Enter correct Patient ID");
				}
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in taking input patient id " 
		            + "PatientOperation:inputPatientId", e);
		}
		patientResponse = patientDaoImpl.getPatient(patientid);
	}

	/*
	 * In this method we are calling inputPatientId() function to take input patient id from user and passing to
	 * getPatient method of PatientDaoImpl class
	 */
	private void retrievePaient() 
	{
		try 
		{
			inputPatientId();
			if (patientResponse.getPatientId() == null)
			{
				System.err.println("Patient with the given patientId doesn't exist in the database");
			} 
			else 
			{
				System.out.println("Patient ID : " + patientResponse.getPatientId() + "\n" + "Patient Name : "
						+ patientResponse.getPatientName() + "\n" + "Patient Addhar Number : "
						+ patientResponse.getPatientAadharNumber() + "\n" + "Patient Vaccination Status : "
						+ patientResponse.getHasVaccinated() + "\n" + "Patient vaccination Certificate Number : "
						+ patientResponse.getCertificateNumber() + "\n" + "Patient Phone Number : "
						+ patientResponse.getPhoneNumber() + "\n" + "Patient Covid Status : "
						+ patientResponse.getIsTestedPositive() + "\n");
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in retrieving Patient covid data " 
		            + "PatientOperation:retrievePatient", e);
		}
	}

	/*
	 * In this method we are calling inputPatientId() and inputPatientName() methods for taking input of 
	 * patient id from user and passing to updatePatient method of PatientDaoImpl class.
	 */
	private void updatePatientDetails() 
	{
		try 
		{
			inputPatientId();
			inputPatientName();
			patientDaoImpl.updatePatient(patientResponse);
			patientResponse = patientDaoImpl.getPatient(patientid);
			if (patientResponse.getPatientId() == null)
			{
				System.err.println("Patient with the given patientId doesn't exist in the database");
			} 
			else 
			{
				System.out.println("Patient ID : " + patientResponse.getPatientId() + "\n" + "Patient Name : "
						+ patientResponse.getPatientName() + "\n" + "Patient Addhar Number : "
						+ patientResponse.getPatientAadharNumber() + "\n" + "Patient Vaccination Status : "
						+ patientResponse.getHasVaccinated() + "\n" + "Patient vaccination Certificate Number : "
						+ patientResponse.getCertificateNumber() + "\n" + "Patient Phone Number : "
						+ patientResponse.getPhoneNumber() + "\n" + "Patient Covid Status : "
						+ patientResponse.getIsTestedPositive() + "\n");
				System.out.println("Patient details with patientId has been updated Successfully.....");
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in updating Patient covid data " 
		            + "PatientOperation:updatePatientDetails",e);
		}
	}

	/*
	 * In this method we calling inputPatientId() method for taking input patientId that we are passing to 
	 * deletePatient method of PatientDaoImpl class
	 */
	private void deletePatientData() 
	{
		inputPatientId();
		patientResponse = patientDaoImpl.getPatient(patientid);
		if(patientResponse.getPatientId() == null)
		{
			System.err.println("Patient with the entered patientId doesn't exist in the database.");
		}
		else 
		{
			patientDaoImpl.deletePatient(patientid);
			System.out.println("Patient details with patientId has been deleted successfuly..\n");
		}
//		patientDaoImpl.deletePatient(patientid);
//		System.out.println("Patient details with patientId " + patientid + " has been deleted successfuly..\n");
	}

	/*
	 * In this method we are making a switch case to make application user friendly
	 * In case 1, we are calling add patient api
	 * In case 2, we are calling get Patient api
	 * In case 3, we are calling update Patient api
	 * In case 4, we are calling delete Patient api
	 */
	public void patientOperationMethod() 
	{
		System.out.println("Press 1 for add new Patient details");
		System.out.println("Press 2 for Display details of Patient");
		System.out.println("Press 3 for Update details of Patient");
		System.out.println("Press 4 for delete details of Patient");
		try 
		{
			input = bufferedReader.readLine(); 
			switch (input) 
			{
			case "1": 
				boolean loopBreak = true; 
				while (loopBreak) 
				{
					System.out.println("Do you want to add new patient?(Yes/No): ");
					String decision = bufferedReader.readLine();
					userResponseYes = "Yes";
					userResponseNo = "No";
					if (userResponseYes.equalsIgnoreCase(decision)) 
					{
						insertNewPatient();
					} 
					else if(userResponseNo.equalsIgnoreCase(decision))
					{
						patientOperationMethod();
						loopBreak = false;
					}
					else 
					{
						System.err.println("Please enter correct data in Yes/No.");
					}
				}
				break;

			case "2":
				boolean temp1 = true;
				while (temp1) 
				{
					System.out.println("Do you want to display patient details's?(Yes/No): ");
					String decision = bufferedReader.readLine();
					userResponseYes = "Yes";
					userResponseNo = "No";
					if (userResponseYes.equalsIgnoreCase(decision)) 
					{
						retrievePaient();
					} 
					else if(userResponseNo.equalsIgnoreCase(decision))
					{
						patientOperationMethod();
						temp1 = false;
					}
					else 
					{
						System.err.println("Please enter correct data in Yes/No.");
					}
				}
				break;

			case "3":
				boolean temp2 = true;
				while (temp2) 
				{
					System.out.println("Do you want to update patient details's?(Yes/No): ");
					String decision = bufferedReader.readLine();
					userResponseYes = "Yes";
					userResponseNo = "No";
					if (userResponseYes.equalsIgnoreCase(decision)) 
					{
						updatePatientDetails();
					} 
					else if(userResponseNo.equalsIgnoreCase(decision)) 
					{
						patientOperationMethod();
						temp2 = false;
					}
					else 
					{
						System.err.println("Please enter correct data in Yes/No.");
					}
				}
				break;

			case "4":
				boolean temp3 = true;
				while (temp3) 
				{
					System.out.println("Do you want to delete patient details's?(Yes/No): ");
					String decision = bufferedReader.readLine();
					userResponseYes = "Yes";
					userResponseNo = "No";
					if (userResponseYes.equalsIgnoreCase(decision)) 
					{
						deletePatientData();
					} 
					else if(userResponseNo.equalsIgnoreCase(decision))
					{
						patientOperationMethod();
						temp3 = false;
					}
					else 
					{
						System.err.println("Please enter correct data in Yes/No.");
					}
				}
				break;
			default:
				System.err.println("Please Choose the corect option ");
				patientOperationMethod();
			}
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("An unexpected error in performing operation on patient "
				    + "PatientOperation:patientOpertationMethod", e);
		}
	}
}
