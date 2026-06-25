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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

/**
 * 結合テスト ログイン機能①
 * ケース03
 * @author holy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース03 受講生 ログイン 正常系")
public class Case03 {

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
		
		//ログイン画面の表示を確認
		assertEquals("ログイン | LMS", driver.getTitle());
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		//正しいログインID、ログインパスワードを入力(初回ログイン済みのユーザー)
		driver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		driver.findElement(By.id("password")).sendKeys("StudentAA02");
		
		//「ログイン」ボタンをクリック
		driver.findElement(By.cssSelector("input[value='ログイン']")).click();
	
		//コース詳細画面に遷移
		assertEquals("コース詳細 | LMS", driver.getTitle());
	
	}

}
