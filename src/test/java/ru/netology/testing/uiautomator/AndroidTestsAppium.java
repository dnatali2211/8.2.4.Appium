package ru.netology.testing.uiautomator;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidTestsAppium {

    private AndroidDriver driver;
    private String emptyString = " ";
    private String textToChange = "Netology";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testNotChangeTextWithEmptyField() {
        MobileElement el1 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        String startText = el1.getText();
        MobileElement el2 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el2.click();
        el2.sendKeys(emptyString);
        MobileElement el3 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        el4.isDisplayed();
        Assertions.assertEquals(el4.getText(), startText);
    }

    @Test
    public void testShouldOpenChangedTextInAnotherActivity() throws InterruptedException {
        MobileElement el1 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.click();
        el1.sendKeys(textToChange);
        MobileElement el2 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        el2.click();
        Thread.sleep(2000);
        MobileElement el3 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/text"));
        el3.isDisplayed();
        Assertions.assertEquals(el3.getText(), textToChange);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
