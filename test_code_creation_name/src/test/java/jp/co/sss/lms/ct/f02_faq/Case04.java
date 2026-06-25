package jp.co.sss.lms.ct.f02_faq;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {
	
	@LocalServerPort
	private int port;
	
	private static WebDriver driver;
	@SuppressWarnings("unused")
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
		
		//コース詳細画面に変わるまで待つ
		wait.until(ExpectedConditions.titleIs("コース詳細 | LMS"));
		
		//コース詳細画面に遷移
		assertEquals("コース詳細 | LMS", driver.getTitle());
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		driver.findElement(By.linkText("機能")).click();;
		
		driver.findElement(By.linkText("ヘルプ")).click();;
		
		assertEquals("ヘルプ | LMS", driver.getTitle());
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		@SuppressWarnings("unused")
		String Handle = driver.getWindowHandle();
		
		driver.findElement(By.linkText("よくある質問")).click();
		
		@SuppressWarnings("unused")
		String newHandle = null;
		for(String id : driver.getWindowHandles()) {
			if(!id.equals(Handle)) {
				newHandle = id;
			}
		}
		driver.switchTo().window(newHandle);
		
		assertEquals("よくある質問 | LMS", driver.getTitle());
		
	}

}
