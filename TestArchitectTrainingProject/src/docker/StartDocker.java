package docker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StartDocker {

	@Test
	public void startFile() throws IOException, InterruptedException {
		boolean dockerFlag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("open dockerup.command");

		String file = "output.txt";

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 45);
		long stopTime = calendar.getTimeInMillis();
		Thread.sleep(3000);

		while (System.currentTimeMillis() < stopTime) {
			if (dockerFlag) {
				break;
			}
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			String currentLine = bReader.readLine();

			while (currentLine != null && !dockerFlag) {
				if (currentLine.contains("registered to the hub and ready to use")) {
					System.out.println("Found my text - Ready to use");
					dockerFlag = true;
					break;
				}
				currentLine = bReader.readLine();
			}
			bReader.close();
		}
		Assert.assertTrue(dockerFlag);
		runtime.exec("open scale.command");
		Thread.sleep(10000);
	}
}
