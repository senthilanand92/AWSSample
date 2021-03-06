package main.java.com.amazonaws.samples.models;

import java.util.HashSet; 

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Employee_Table")
public class Employee {
	Integer empID;
	String Designation;
	HashSet<String> locations;
	Address employeeAddress;
	
	@DynamoDBAttribute(attributeName="ADDRESS")
	public Address getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(Address employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	@DynamoDBHashKey(attributeName="EMP_ID")
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	
	@DynamoDBAttribute(attributeName="DESIGNATION")
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	
	@DynamoDBAttribute(attributeName="Locations")
	public HashSet<String> getLocations() {
		return locations;
	}
	public void setLocations(HashSet<String> locations) {
		this.locations = locations;
	}
	
	@Override
	public String toString(){
		return "Employee :ID"+this.getEmpID()+" Designation"+this.getDesignation()+" locationsofWork:"+this.getLocations();
	}
	
	
	

}
