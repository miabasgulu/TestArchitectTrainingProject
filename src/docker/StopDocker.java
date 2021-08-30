package docker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StopDocker {

	@Test
	public void stoptFile() throws IOException, InterruptedException {
		boolean dockerFlag = false;
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("open dockerdown.command");

		String file = "output.txt";

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 45);
		long stopTime = calendar.getTimeInMillis();
		Thread.sleep(7000);

		while (System.currentTimeMillis() < stopTime) {
			if (dockerFlag) {
				break;
			}
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			String currentLine = bReader.readLine();

			while (currentLine != null && !dockerFlag) {
				if (currentLine.contains("exited with code")) {
					System.out.println("Found my text - Process completed");
					dockerFlag = true;
					break;
				}
				currentLine = bReader.readLine();
			}
			bReader.close();
		}
		Assert.assertTrue(dockerFlag);
		Thread.sleep(7000);
	}
}
