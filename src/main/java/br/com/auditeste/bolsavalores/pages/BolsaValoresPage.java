package br.com.auditeste.bolsavalores.pages;

import static br.com.auditeste.bolsavalores.Driver.getDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BolsaValoresPage {

	public static void initializaPagina() {
		getDriver().get("https://br.investing.com/currencies/exchange-rates-table");
	}

	public static void finalizar() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}

	public List<WebElement> listarCelulas() {

		WebElement tabela = getDriver().findElement(By.cssSelector("#exchange_rates_1"));

		List<WebElement> celulas = tabela.findElements(By.tagName("td"));

		return celulas;
	}

}
