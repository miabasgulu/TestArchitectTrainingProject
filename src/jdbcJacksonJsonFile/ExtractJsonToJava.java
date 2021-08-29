package jdbcJacksonJsonFile;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojos.CuctomerDetails;

public class ExtractJsonToJava {

	public static void main(String args[]) throws IOException {

		ObjectMapper omapper = new ObjectMapper();
		CuctomerDetails customDetails = omapper.readValue(new File(
				"/Users/mahammadabasguliyev/Documents/workspace-spring-tool-suite-4-4.6.2.RELEASE/TestArchitectTrainingProject/jsonFiles/customerInfo.json"),
				CuctomerDetails.class);
		System.out.println(customDetails.getStudentName());

	}

}
