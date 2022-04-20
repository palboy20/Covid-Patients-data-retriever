# Covid-Patients-data-retriever
https://jira2.cerner.com/browse/DEVACDMY-38358


# Project Documentation

# Intent of the Project:
## Project:

Problem Statement:
For the given patientId, API should return the patients covid data.  Covid data should contain patientid, patient name, testedpostive, hasvaccinated, phone number, vaccine certification number, aadhar number.

Overview:

The COVID-19 pandemic has led to a dramatic loss of human life worldwide and presents an unprecedented challenge to public health, food systems and the world of work. Mainly healthcare systems have lot of pressure with the high rate of incoming patients. With this project we will be coming up with the API which provides the patients covid data for the given Aadhar/pan number.  This API is time efficient, as the consumers will get the patients covid history data at one short.

JDBC Flow Chart:-


![API](https://user-images.githubusercontent.com/97604594/164281350-c5a71a15-fabf-458a-8010-cbd7a35689a6.jpg)


![JDBC](https://user-images.githubusercontent.com/97604594/164281399-934af7b4-f81b-4916-b331-971ddafd994e.jpg)

## High Level Design

![High-Level-Design](https://user-images.githubusercontent.com/97604594/164281688-9a6dd291-366b-4a8c-ab23-28a88c0678cf.jpg)


## UML Design

![UML](https://user-images.githubusercontent.com/97604594/164281592-0ebf0851-4af7-464f-8722-db5b416e1633.jpg)






## Technical Description

Programming language used : Java

This project needs jdk 1.8 and maven 3.8.4

## PatientAPI implementation workflow

When consumer run PatientAPI in main class then PatientOperation class object invokes and it calls patientOperationMethod. When paientOperationMethod invokes then it will show four statements to user in this way:-

Press 1 for add new Patient

Press 2 for Display details of Patient

Press 3 for Update details of Patient

Press 4 for delete details of Patient

If user press 1, insertNewPatient() api will be called and it will ask to take patient details. When we provide patient details then JDBC connection will be establish between java application and MySQL database with the help of JDBC driver and patient details added to the database table.

If user press 2 retrievePatient() api will be called, then for the given patientId, a JDBC connection will be establish between java application and MySQL database with the help of JDBC driver and we get all the patient details from the database table.

If user press 3 updatePatient() api will be called, then for the given patientId JDBC connection will be establish between java application and MySQL database with the help of JDBC driver and we are able to update the patient details from the database table for the given patientId.

If user press 4 deletePatient() api will be called, then for the given patientId, a JDBC connection will be establish between java application and MySQL database with the help of JDBC driver and we get are able to delete the patient details for the given patientId from the database table.

## Testing Tools used in project

We used JUnit5 and Mockito 4.3.1 versions for unit testing and mocking.

## Tools Used

Eclipse IDE 

Jenkins for build deployement

Github for code review

MySQL Workbench for creating database and executing required queries
