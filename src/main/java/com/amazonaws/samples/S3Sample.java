/*
 * Copyright 2010-2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.samples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import main.java.com.amazonaws.samples.constants.AWSConstants;
import main.java.com.amazonaws.samples.manager.AWSManager;
import main.java.com.amazonaws.samples.models.Address;
import main.java.com.amazonaws.samples.models.Employee;

/**
 * This sample demonstrates how to make basic requests to Amazon S3 using
 * the AWS SDK for Java.
 * <p>
 * <b>Prerequisites:</b> You must have a valid Amazon Web Services developer
 * account, and be signed up to use Amazon S3. For more information on
 * Amazon S3, see http://aws.amazon.com/s3.
 * <p>
 * <b>Important:</b> Be sure to fill in your AWS access credentials in
 * ~/.aws/credentials (C:\Users\USER_NAME\.aws\credentials for Windows
 * users) before you try to run this sample.
 */
public class S3Sample{

	public static void main(String[] args) throws IOException {
        /*
         * Create your credentials file at ~/.aws/credentials (C:\Users\USER_NAME\.aws\credentials for Windows users) 
         * and save the following lines after replacing the underlined values with your own.
         *
         * [default]
         * aws_access_key_id = YOUR_ACCESS_KEY_ID
         * aws_secret_access_key = YOUR_SECRET_ACCESS_KEY
         */
    	
    	AWSManager awsmanager=new AWSManager();
    	//input params
    	int EMP_ID=488;
    	String tableName="Employee_Test_Table_2";
    	String designation="Developer";
    	String updated_designation="AWS _developer";
    	HashSet<String> locations=new HashSet<String>(Arrays.asList("Chennai","delhi"));
    	boolean ifDelete=false;
    	
    	
    	Employee employee;
    	Employee updatedEmployee;
    	
    	try{
    	//awsmanager.createTable(tableName);
    	
    	//code for insertion
    	awsmanager.initiablizeDynamoDbMapper();
    	employee= new Employee();
    	employee.setEmpID(EMP_ID);
    	employee.setDesignation(designation);
    	employee.setLocations(locations);
    	employee.setEmployeeAddress(new Address("no 12 K.p koil street",12,"Chennai"));
    	awsmanager.AddEmployee(employee);
    	 
	
    	
    	//code for reading
    	employee= awsmanager.readEmployee(EMP_ID);
    	System.out.println("Retrieved employee: "+employee);
   		
    	//code for updation
    	employee.setDesignation(updated_designation);
    	updatedEmployee=awsmanager.UpdateEmployee(employee);
    	System.out.println("updated employee: "+updatedEmployee);
    		
    	//Code for deletion
    	if(ifDelete){
    	awsmanager.DeleteEmployee(updatedEmployee);
    	}
    

    }
	catch (AmazonServiceException ase) {
        System.out.println("Caught an AmazonServiceException, which means your request made it "
                + "to Amazon S3, but was rejected with an error response for some reason.");
        System.out.println("Error Message:    " + ase.getMessage());
        System.out.println("HTTP Status Code: " + ase.getStatusCode());
        System.out.println("AWS Error Code:   " + ase.getErrorCode());
        System.out.println("Error Type:       " + ase.getErrorType());
        System.out.println("Request ID:       " + ase.getRequestId());
    } catch (AmazonClientException ace) {
        System.out.println("Caught an AmazonClientException, which means the client encountered "
                + "a serious internal problem while trying to communicate with S3, "
                + "such as not being able to access the network.");
        System.out.println("Error Message: " + ace.getMessage());
        ace.printStackTrace();
    }
    	finally{
    		awsmanager.cleanUp();  
    	}
    
	}
}
