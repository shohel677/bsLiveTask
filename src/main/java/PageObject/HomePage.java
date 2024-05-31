package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {



    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void homepageElement(){
        //add product to cart
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/../../following-sibling::div/button")).click();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']/../../following-sibling::div/button")).click();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Onesie']/../../following-sibling::div/button")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }
}
