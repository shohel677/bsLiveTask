package Testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class StandAloneTest {

    public WebDriver driver;

    @Test
    public void bsAutomationTest() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        //add product to cart
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/../../following-sibling::div/button")).click();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']/../../following-sibling::div/button")).click();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Onesie']/../../following-sibling::div/button")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        //Vreify product in cart
        List<WebElement> productsInCart = driver.findElements(By.xpath("//div[@class='cart_item_label']/a/div"));
        List <String> addedProduct = new ArrayList<>();

        for (WebElement product : productsInCart){

            String productName = product.getText();

            addedProduct.add(productName);

        }

        Assert.assertTrue(addedProduct.contains("Sauce Labs Backpack"), "Verify product Sauce Labs Backpack");
        Assert.assertTrue(addedProduct.contains("Sauce Labs Bolt T-Shirt"), "Verify product Sauce Labs Bolt T-Shirt");
        Assert.assertTrue(addedProduct.contains("Sauce Labs Onesie"), "Verify product Sauce Labs Onesie");

        List<WebElement> pricesInCart = driver.findElements(By.xpath("//div[@class='cart_item_label']/div/div"));
        List <String> addedProductPrice = new ArrayList<>();

        for (WebElement price : pricesInCart){

            String productPrice = price.getText();

            addedProductPrice.add(productPrice);

        }

        Assert.assertTrue(addedProductPrice.contains("$29.99"), "Verify price of Sauce Labs Backpack");
        Assert.assertTrue(addedProductPrice.contains("$15.99"), "Verify price of Sauce Labs Bolt T-Shirt");
        Assert.assertTrue(addedProductPrice.contains("$7.99"), "Verify price of Sauce Labs Onesie");

        driver.findElement(By.xpath("//button[text()='Checkout']")).click();

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        //Verify input field in checkout page
        WebElement errorMessage = driver.findElement(By.xpath("//h3"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Verify input fields error message");

        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Golzar Ahamed");
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Shohel");
        driver.findElement(By.xpath("//input[@placeholder='Zip/Postal Code']")).sendKeys("1216");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //Verify prouct in overview page
        List<WebElement> productsInOverview = driver.findElements(By.xpath("//div[@class='cart_item_label']/a/div"));
        List <String> overviewProduct = new ArrayList<>();

        for (WebElement product : productsInOverview){

            String productName = product.getText();

            overviewProduct.add(productName);

        }

        Assert.assertTrue(addedProduct.contains("Sauce Labs Backpack"), "Verify product Sauce Labs Backpack");
        Assert.assertTrue(addedProduct.contains("Sauce Labs Bolt T-Shirt"), "Verify product Sauce Labs Bolt T-Shirt");
        Assert.assertTrue(addedProduct.contains("Sauce Labs Onesie"), "Verify product Sauce Labs Onesie");

        List<WebElement> pricesInOverview = driver.findElements(By.xpath("//div[@class='cart_item_label']/div/div"));
        List <String> overviewProductPrice = new ArrayList<>();

        for (WebElement price : pricesInOverview){

            String productPrice = price.getText();

            overviewProductPrice.add(productPrice);

        }

        driver.findElement(By.xpath("//button[text()='Finish']")).click();

        String actualThankMessage = "Thank you for your order!";
        String thankYouMessage = driver.findElement(By.xpath("//h2")).getText();

        Assert.assertEquals(actualThankMessage, thankYouMessage);

        driver.quit();
    }
}
