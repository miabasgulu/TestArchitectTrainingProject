package docker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class ChromeTest4 {

	@Test
	public void test4() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, cap);

		driver.get("https://twitter.com");
		System.out.println(driver.getTitle());

	}

}
