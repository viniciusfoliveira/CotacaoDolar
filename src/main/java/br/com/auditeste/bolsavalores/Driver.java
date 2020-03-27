package br.com.auditeste.bolsavalores;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

	private static WebDriver driver = null;

	public static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "/home/vinicius/Documentos/chromedriver");

		if (driver == null) {
			switch (Propriedades.BROWSER) {
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case CHROME:
				driver = new ChromeDriver();
				break;
			}
		}

		return driver;
	}
}
