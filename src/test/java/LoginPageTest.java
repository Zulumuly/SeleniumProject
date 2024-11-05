import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new SafariDriver();
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void testSingIn() {

        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

        WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div"));
        assertTrue(successMessage.isDisplayed());
    }


    @Test
    public void testLoginWithInvalidPassword() {
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
        assertEquals("Epic sadface: Username and password do not match any user in this service", errorMessage.getText());
    }

    @Test
    public void testLoginWithEmptyFields() {
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
        assertEquals("Epic sadface: Username is required", errorMessage.getText());
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
