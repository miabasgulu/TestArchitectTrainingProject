package jdbcJacksonJsonFile;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojos.CuctomerDetails;

public class JsonToJava {

	public static void main(String args[])
			throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "12345678");

		Statement st = connection.createStatement();
		ResultSet rs = st
				.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");

		List<CuctomerDetails> listOfObject = new ArrayList<CuctomerDetails>();

		while (rs.next()) {
			CuctomerDetails cd = new CuctomerDetails();
			cd.setCourseName(rs.getString(1));
			cd.setPurchaseDate(rs.getString(2));
			cd.setAmount(rs.getInt(3));
			cd.setLocation(rs.getString(4));
			listOfObject.add(cd);

			System.out.println(cd.getCourseName());
			System.out.println(cd.getPurchaseDate());
			System.out.println(cd.getAmount());
			System.out.println(cd.getLocation());
		}
		for (int i = 0; i < listOfObject.size(); i++) {
			ObjectMapper om = new ObjectMapper();
			om.writeValue(new File(
					"/Users/mahammadabasguliyev/Documents/workspace-spring-tool-suite-4-4.6.2.RELEASE/TestArchitectTrainingProject/jsonFiles/customer_info"
							+ i + ".json"),
					listOfObject.get(i));
		}

		connection.close();
	}

}
