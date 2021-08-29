package docker;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeTest1 {

	@BeforeTest
	public void startDockerAndScale() throws IOException, InterruptedException {
		File file = new File("output.txt");
		if (file.delete()) {
			System.out.println("output.txt file deleted successfully");
		}

		StartDocker start = new StartDocker();
		start.startFile();
	}

	@AfterTest
	public void stopDockerAndDeleteFile() throws IOException, InterruptedException {
		StopDocker stop = new StopDocker();
		stop.stoptFile();
	}

	@Test
	public void test1() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, cap);

		driver.get("https://google.com");
		System.out.println(driver.getTitle());

	}

}
