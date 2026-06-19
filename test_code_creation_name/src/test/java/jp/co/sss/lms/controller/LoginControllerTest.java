package jp.co.sss.lms.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

 
//ログイン画面への遷移
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

	@LocalServerPort
	private int port;
	
	private WebDriver driver;
	
	
	@BeforeEach
	void setUp() {
		driver = new ChromeDriver();
		
	}
	@AfterEach
	void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
	@Test
	void testLogin() {
		driver.get("http://localhost:" + port + "/lms");
		
	WebElement loginidElement = driver.findElement(By.id("loginId"));
	WebElement passwordElement = driver.findElement(By.id("password"));
	
	assertTrue(loginidElement.isDisplayed(), "ログインID入力欄が表示されていること");
	assertTrue(passwordElement.isDisplayed(), "パスワード入力欄が表示されていること");
	
	}

}
