package br.com.auditeste.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.auditeste.bolsavalores.pages.BolsaValoresPage;

/*
 * O JUnit ficarará encarregada pela estruturação dos casos de teste 
 **/
public class AbrirNavegadorTest {

	private BolsaValoresPage page = new BolsaValoresPage();
	/*
	 * Para determinar que um método vai ser executado antes dos demais métodos da
	 * classe de teste utilizamos a anotação:
	 * 
	 * método que é executado antes do teste. Normalmente, é nele que criamos uma
	 * nova instância do navegador com o Selenium WebDriver, abrimos conexões com
	 * bancos de
	 * 
	 * @BeforeClass public static void setUpBeforeClass() {} Para determinar que um
	 * método vai ser executado depois dos demais métodos da classe de teste
	 * utilizamos a anotação:
	 * 
	 * 
	 * método que, ao final de todos os testes, é executado para encerrar uma
	 * instância do navegador, fechar uma conexão com um banco de dados etc. Esse
	 * método deve vir
	 * 
	 * 
	 * @AfterClass public static void tearDownAfterClass() throws Exception {}
	 * 
	 * Para determinar que um método vai ser executado antes de cada caso de teste
	 * utilizamos a anotação:
	 * 
	 * @Before public void setUp() throws Exception {}
	 * 
	 * Para determinar que um método vai ser executado depois de cada caso de teste
	 * utilizamos a anotação:
	 * 
	 * @After public void tearDown() throws Exception { /// driver.close(); // fecha
	 * a aba que ele usou // driver.quit(); // fecha a o navegador }
	 */

	@BeforeClass
	public static void setUp() {
		BolsaValoresPage.initializaPagina();
	}

	@AfterClass
	public static void tearDown() {
		BolsaValoresPage.finalizar();
	}

	@Test
	public void test() {

		try {

			File file = new File("/home/vinicius/Desktop/CotacaoDolar/BolsaValores.xlsx");
			// ler um arquivo

			FileInputStream fos = new FileInputStream(file);

			// pega todas as planilhas
			XSSFWorkbook workbook = new XSSFWorkbook(fos);

			// pega uma em especifico
			XSSFSheet sheetMoedas = workbook.getSheetAt(0);

			PopulaExcel(page.listarCelulas(), sheetMoedas);

			// escreve no arquivo
			FileOutputStream os = new FileOutputStream(file);
			workbook.write(os);
		}

		catch (Exception e) {
			System.out.println("Erro: " + e.getStackTrace());
		}
	}

	private static void PopulaExcel(List<WebElement> celulas, XSSFSheet sheetMoedas) {
		String[] valores = new String[9];

		int j = 0, row = 1;

		for (int i = 0; i < celulas.size(); i++) {

			while (j < valores.length) {

				valores[j] = celulas.get(i).getText();
				j++;
				break;
			}

			if (j == valores.length) {

				Row r = sheetMoedas.createRow(row);

				for (int k = 0; k < valores.length; k++) {

					org.apache.poi.ss.usermodel.Cell c = r.createCell(k);
					c.setCellValue(valores[k]);

					System.out.println(valores[k]);
				}
				valores = new String[9];
				j = 0;
				row++;
			}
		}
	}

}
