package main.java.com.amazonaws.samples.factory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import main.java.com.amazonaws.samples.helpers.AWSHelper;

public class AWSClientFactory  {
	public static AWSHelper helperClass= new AWSHelper();
	public  AmazonDynamoDB client;
	
	public AmazonS3 getS3Client(){
		AmazonS3 s3 = new AmazonS3Client(helperClass.getBasicAWSCredtials(),helperClass.getClientConfig());
		 Region usWest2 = Region.getRegion(Regions.AP_SOUTH_1);
		    s3.setRegion(usWest2);
		return s3;
	}
	public  AmazonDynamoDB initializeClient(){
		if(this.client == null){
			System.out.println("Creating Dynamo DB client");
		this.client  = AmazonDynamoDBClientBuilder.standard()
			 .withClientConfiguration(helperClass.getClientConfig())
			 .withCredentials(new AWSCredentialsProvider() {
				
				@Override
				public void refresh() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public AWSCredentials getCredentials() {
					
					return helperClass.getBasicAWSCredtials();
				}
			})
			 .withRegion(Regions.AP_SOUTH_1)
			 .build();
	 
	 System.out.println("client created successfully");
		}
    return client;
}
	public void shutDownClientDynamoClient(){
		if(client != null){
			System.out.println("shutting down client");
		client.shutdown();
		}
	}
}
