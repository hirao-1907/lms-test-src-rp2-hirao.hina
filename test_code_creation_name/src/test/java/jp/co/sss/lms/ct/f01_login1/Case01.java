package jp.co.sss.lms.ct.f01_login1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

/**
 * 結合テスト ログイン機能①
 * ケース01
 * @author holy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース01 ログイン画面への遷移")
public class Case01 {

	@LocalServerPort
	private int port;
	
	private static WebDriver driver;
	
	/** 前処理 */
	@BeforeAll
	static void before() {
		driver = new ChromeDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		if(driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		driver.get("http://localhost:" + port + "/lms");
		
		WebElement loginidElement = driver.findElement(By.id("loginId"));
		WebElement passwordElement = driver.findElement(By.id("password"));
		
		assertTrue(loginidElement.isDisplayed(), "ログインID入力欄が表示されていること");
		assertTrue(passwordElement.isDisplayed(), "パスワード入力欄が表示されていること");
		
	}

}
