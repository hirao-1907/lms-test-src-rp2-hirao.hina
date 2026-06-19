package jp.co.sss.lms.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginFailureTest {

	@LocalServerPort
	private int port;
	
	private WebDriver driver;
	
	@BeforeEach
	void setUp() {
		driver = new ChromeDriver();
	}
	@AfterEach
	void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	@Test
	@DisplayName("ログイン失敗：パスワードを空文字のまま入力")
	void testLoginFailurePass(){
		//ログイン画面を開く
		driver.get("http://localhost:" + port + "/lms");

		//誤った情報を入力
		driver.findElement(By.id("loginId")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("");

		//ログインボタンをクリック
		driver.findElement(By.cssSelector("input[value='ログイン']")).click();

		//エラーメッセージが表示されているか
		WebElement errorMsg = driver.findElement(By.className("error"));

		// メッセージが画面に表示されているか（CSS等で隠れていないか）
		assertTrue(errorMsg.isDisplayed(), "エラーメッセージが表示されている必要があります");

		//メッセージの内容は正しいか
		assertEquals("パスワードは必須です。", errorMsg.getText());

		// 検証：画面が遷移していない（タイトルが変わっていない）ことの確認
		assertEquals("ログイン | LMS", driver.getTitle());
	}

	@Test
	@DisplayName("ログイン失敗：ログインIDを空文字のまま入力")
	void testLoginFailureId(){
		//ログイン画面を開く
		driver.get("http://localhost:" + port + "/lms");

		//誤った情報を入力
		driver.findElement(By.id("loginId")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("admin");

		//ログインボタンをクリック
		driver.findElement(By.cssSelector("input[value='ログイン']")).click();

		//エラーメッセージが表示されているか
		WebElement errorMsg = driver.findElement(By.className("error"));

		// メッセージが画面に表示されているか（CSS等で隠れていないか）
		assertTrue(errorMsg.isDisplayed(), "エラーメッセージが表示されている必要があります");

		//メッセージの内容は正しいか
		assertEquals("ログインIDは必須です。", errorMsg.getText());

		// 検証：画面が遷移していない（タイトルが変わっていない）ことの確認
		assertEquals("ログイン | LMS", driver.getTitle());
	}
	@Test
	@DisplayName("ログイン失敗：誤ったパスワードを入力")
	void testLoginErrorPass()throws InterruptedException{
		//ログイン画面を開く
		driver.get("http://localhost:" + port + "/lms");

		//誤った情報を入力
		driver.findElement(By.id("loginId")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("wrong-password");

		//ログインボタンをクリック
		driver.findElement(By.cssSelector("input[value='ログイン']")).click();

		Thread.sleep(3000);
		
		//エラーメッセージが表示されているか
		WebElement errorMsg = driver.findElement(By.className("error"));

		// メッセージが画面に表示されているか（CSS等で隠れていないか）
		assertTrue(errorMsg.isDisplayed(), "エラーメッセージが表示されている必要があります");

		//メッセージの内容は正しいか
		assertEquals("* ログインに失敗しました。", errorMsg.getText());

		// 検証：画面が遷移していない（タイトルが変わっていない）ことの確認
		assertEquals("ログイン | LMS", driver.getTitle());

	}
	@Test
	@DisplayName("ログイン失敗：誤ったログインIDを入力")
	void testLoginErrorId()throws InterruptedException{
		//ログイン画面を開く
		driver.get("http://localhost:" + port + "/lms");

		//誤った情報を入力
		driver.findElement(By.id("loginId")).sendKeys("wrong-loginId");
		driver.findElement(By.id("password")).sendKeys("admin");

		//ログインボタンをクリック
		driver.findElement(By.cssSelector("input[value='ログイン']")).click();

		Thread.sleep(3000);
		
		//エラーメッセージが表示されているか
		WebElement errorMsg = driver.findElement(By.className("error"));

		// メッセージが画面に表示されているか（CSS等で隠れていないか）
		assertTrue(errorMsg.isDisplayed(), "エラーメッセージが表示されている必要があります");

		//メッセージの内容は正しいか
		assertEquals("* ログインに失敗しました。", errorMsg.getText());

		// 検証：画面が遷移していない（タイトルが変わっていない）ことの確認
		assertEquals("ログイン | LMS", driver.getTitle());

	}
}
