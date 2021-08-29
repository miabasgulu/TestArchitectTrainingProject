package jdbcJacksonJsonFile;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

import pojos.CuctomerDetails;

public class NestedJSON {

	public static void main(String args[])
			throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		// Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "12345678");

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE();");

		JSONArray jArray = new JSONArray();
		List<CuctomerDetails> listOfObject = new ArrayList<CuctomerDetails>();

		while (rs.next()) {
			CuctomerDetails cd = new CuctomerDetails();
			cd.setCourseName(rs.getString(1));
			cd.setPurchaseDate(rs.getString(2));
			cd.setAmount(rs.getInt(3));
			cd.setLocation(rs.getString(4));
			listOfObject.add(cd);
		}

		for (int i = 0; i < listOfObject.size(); i++) {
			Gson gson = new Gson();
			String jString = gson.toJson(listOfObject.get(i));
			jArray.add(jString);
		}

		JSONObject jObj = new JSONObject();
		jObj.put("data", jArray);
		System.out.println(jObj.toJSONString());
		String unescapeString = StringEscapeUtils.unescapeJava(jObj.toJSONString());
		unescapeString = unescapeString.replace("\"{", "{").replace("}\"", "}");
		System.out.println(unescapeString);

		try (FileWriter fileWriter = new FileWriter(
				"/Users/mahammadabasguliyev/Documents/workspace-spring-tool-suite-4-4.6.2.RELEASE/TestArchitectTrainingProject/jsonFiles/nestedJson.json")) {
			fileWriter.write(unescapeString);
		}

		connection.close();
	}

}
