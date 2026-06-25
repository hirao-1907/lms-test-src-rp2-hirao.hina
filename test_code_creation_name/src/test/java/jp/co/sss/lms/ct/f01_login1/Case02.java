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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

/**
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

	@LocalServerPort
	private int port;
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	
	/** 前処理 */
	@BeforeAll
	static void before() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
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
		
		//間違ったIDを入力
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginId"))).sendKeys("aaa");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.cssSelector("input[value='ログイン']")).click();

		//ログイン画面の保持を確認
		assertEquals("ログイン | LMS", driver.getTitle());
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	void test02() {
		// TODO ここに追加

		//エラーメッセージが表示されているか
		WebElement errorMsg = driver.findElement(By.className("error"));

		// メッセージが画面に表示されているか（CSS等で隠れていないか）
		assertTrue(errorMsg.isDisplayed(), "エラーメッセージが表示されている必要があります");

		//メッセージの内容は正しいか
		assertEquals("* ログインに失敗しました。", errorMsg.getText());
	}

}
