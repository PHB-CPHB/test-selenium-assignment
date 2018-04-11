import  org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class seleniumTest {
    WebDriver driver;

    @BeforeAll
    public void beforeAll(){
        System.setProperty("webdriver.chrome.driver","/usr/local/Cellar/chromedriver/2.37/bin/chromedriver");
    }

    @BeforeEach
    public void beforeEach(){
        this.driver = new ChromeDriver();
        this.driver.get("http://localhost:3000");
    }

    @AfterEach
    public void afterEach(){
        driver.get("http:localhost:3000/reset");
        driver.quit();
    }

    /** 1.
     *  Verify that data is loaded, and
     *  the DOM is constructed (Five rows in the table)
     */
    @Test
    @DisplayName("Data Loaded")
    public void dataLoaded(){
        WebElement tbodycars = driver.findElement(By.id("tbodycars"));
        List<WebElement> rows = tbodycars.findElements(By.tagName("tr"));
        assertAll("Data Loaded", () -> {
            assertNotNull(rows.size());
            assertEquals(5, rows.size());
        });

    }

    /** 2.
     * Write 2002 in the filter text and
     * verify that we only see two rows
     */
    @Test
    @DisplayName("Write in Filter")
    public void testFilterWithText(){
        WebElement filter = driver.findElement(By.id("filter"));
        filter.sendKeys("2002");

        List<WebElement> rows = getCarTable();
        assertAll("Filter Search", () -> {
            assertNotNull(rows.size());
            assertEquals(2, rows.size());
        } );


    }

    /** 3.
     * Clear the text in the filter text and
     * verify that we have the original five rows
     */
    @Test
    @DisplayName("Clear Filter")
    public void testClearFilter(){
        // Inputting data in filter
        WebElement filter = driver.findElement(By.id("filter"));
        filter.sendKeys("2002");

        // Testing that filter has text
        List<WebElement> rows = getCarTable();
        assertAll("Before Clear Filter", () -> {
            assertNotNull(filter.getAttribute("value"));
            assertEquals("2002", filter.getAttribute("value"));
            assertNotNull(rows);
            assertEquals(2, rows.size());
        });

        // Testing that filter is clear
        filter.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
        List<WebElement> updatedRows = getCarTable();
        assertAll("Filter Search Cleared", () -> {
            assertEquals("", filter.getAttribute("value"));
            assertNotNull(updatedRows);
            assertEquals(5, updatedRows.size());
        } );
    }

    /** 4.
     * Click the sort “button” for Year, and
     * verify that the top row contains the car with id 938 and
     * the last row the car with id = 940.
     */
    @Test
    @DisplayName("Sort Button")
    public void testSortButton(){
        WebElement sort = driver.findElement(By.id("h_year"));
        sort.click();
        List<WebElement> rows = getCarTable();
        assertAll("Before Clear Filter", () -> {
            assertNotNull(rows);
            assertEquals("938", rows.get(0).findElements(By.tagName("td")).get(0).getText());
            assertEquals("940", rows.get(rows.size() - 1).findElements(By.tagName("td")).get(0).getText());

        });
    }

    /** 5.
     * Press the edit button for the car with the id 938.
     * Change the Description to "Cool car", and
     * save changes.
     * Verify that the row for car with id 938 now contains "Cool car" in the Description column
     */
    @Test
    @DisplayName("Edit Car")
    public void testEditCar(){
        List<WebElement> rows = getCarTable();

        assertAll("Before Edit of 938", () -> {
                assertEquals("938", rows.get(1).findElements(By.tagName("td")).get(0).getText());
        } );

        List<WebElement> id938tds = rows.get(1).findElements(By.tagName("td"));
        WebElement id938a = id938tds.get(7).findElements(By.tagName("a")).get(0);
        System.out.println(id938a.getText());
        id938a.click();

        WebElement descriptionField = driver.findElement(By.id("description"));
        descriptionField.click();
        descriptionField.clear();
        descriptionField.sendKeys("Cool car");

        WebElement saveButton = driver.findElement(By.id("save"));
        saveButton.click();

        List<WebElement> updatedRows = getCarTable();

        List<WebElement> id938updatedTds = updatedRows.get(1).findElements(By.tagName("td"));
        String id938Description = id938updatedTds.get(5).getText();

        assertAll("After Edit og 938", () -> {
            assertEquals("Cool car", id938Description);
        } );
    }

    /** 6.
     * Click the new “Car Button”, and
     * click the “Save Car” button.
     * Verify that we have an error message with the text “All fields are required” and
     * we still only have five rows in the all cars table.
     */
    @Test
    @DisplayName("Error Message on Save")
    public void testErrorMessageOnSave(){
        WebElement saveButton = driver.findElement(By.id("save"));
        saveButton.click();

        assertEquals("All fields are required", driver.findElement(By.id("submiterr")).getText());
    }

    /** 7.
     * Click the new Car Button, and
     * add the following values for a new car
     a. Year: 2008
     b. Registered: 2002-5-5
     c. Make: Kia
     d. Model: Rio
     e. Description: As new
     f. Price: 31000
     */
    @Test
    @DisplayName("Add new car")
    public void testAddCar(){

        WebElement newCarButton = driver.findElement(By.id("new"));
        newCarButton.click();

        WebElement yearField = driver.findElement(By.id("year"));
        yearField.click();
        yearField.clear();
        yearField.sendKeys("2008");

        WebElement registeredField = driver.findElement(By.id("registered"));
        registeredField.click();
        registeredField.clear();
        registeredField.sendKeys("2002-5-5");

        WebElement makeField = driver.findElement(By.id("make"));
        makeField.click();
        makeField.clear();
        makeField.sendKeys("Kia");

        WebElement modelField = driver.findElement(By.id("model"));
        modelField.click();
        modelField.clear();
        modelField.sendKeys("Rio");

        WebElement descriptionField = driver.findElement(By.id("description"));
        descriptionField.click();
        descriptionField.clear();
        descriptionField.sendKeys("As new");

        WebElement priceField = driver.findElement(By.id("price"));
        priceField.click();
        priceField.clear();
        priceField.sendKeys("31000");

        WebElement saveButton = driver.findElement(By.id("save"));
        saveButton.click();

        List<WebElement> rows = getCarTable();

        assertAll("Before Edit of 938", () -> {
            assertNotNull(rows);
            assertEquals(6, rows.size());
            assertEquals("942", rows.get(rows.size() - 1).findElements(By.tagName("td")).get(0).getText());
            assertEquals("2008", rows.get(rows.size() - 1).findElements(By.tagName("td")).get(1).getText());
            assertEquals("5/5/2002", rows.get(rows.size() - 1).findElements(By.tagName("td")).get(2).getText());
            assertEquals("Kia", rows.get(rows.size() - 1).findElements(By.tagName("td")).get(3).getText());
            assertEquals("Rio", rows.get(rows.size() - 1).findElements(By.tagName("td")).get(4).getText());
            assertEquals("As new", rows.get(rows.size() - 1).findElements(By.tagName("td")).get(5).getText());
            assertEquals("31.000,00 kr.", rows.get(rows.size() - 1).findElements(By.tagName("td")).get(6).getText());
        } );


    }

    private List<WebElement> getCarTable(){
        WebElement TBodyCars = driver.findElement(By.id("tbodycars"));
        return TBodyCars.findElements(By.tagName("tr"));
    }

}
