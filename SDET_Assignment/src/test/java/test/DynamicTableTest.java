package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

@SuppressWarnings("unused")
public class DynamicTableTest {
	public static void main(String[] args) {
		final String chromeVersion = "116.0.1938.62";
		WebDriverManager.chromedriver().browserVersion(chromeVersion).setup();

		WebDriver driver = new ChromeDriver();
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		WebElement tableDataButton = driver.findElement(By.xpath("//*[text() = 'Table Data']"));
		tableDataButton.click();
		String jsonData = readJsonFromFile("data.json");
		WebElement inputTextBox = driver.findElement(By.id("jsondata"));
		inputTextBox.clear();
		inputTextBox.sendKeys(jsonData);
		WebElement refreshTableButton = driver.findElement(By.id("refreshtable"));
		refreshTableButton.click();
		WebElement table = driver.findElement(By.id("dynamictable"));
		String tableData = table.getText();
		assert tableData.contains(jsonData) : "Data does not match.";
		driver.quit();

	}
	private static String readJsonFromFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\muskan.gupta\\eclipse-workspace\\SDET_Assignment\\src\\test\\java\\test\\Data.json"));
            JsonElement jsonElement = JsonParser.parseReader(reader);
            reader.close();
            return jsonElement.toString();
        } catch (IOException e) {
            System.err.println("Error reading the JSON file: " + e.getMessage());
            return "";
        }
    }
}