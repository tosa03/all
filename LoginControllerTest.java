package univelec.controller.auth;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fw.attribute.Attributes;
import fw.attribute.Messages;
import fw.request.WebRequest;
import univelec.util.DBHelper;

public class LoginControllerTest {
	DBHelper dbHelper;

	LoginController controller;

	@Before
	public void setUp() throws Exception {
		dbHelper = new DBHelper();
		controller = new LoginController();
	}

	@After
	public void tearDown() throws Exception {
		dbHelper.close();
	}

	@Test
	public void testLoginEntry() {
		// 前処理：ダミーの引数を作る（使わないのでnullでもOK）
		WebRequest webRequest = new WebRequest(); // ダミー生成（必要に応じてモック）
		Attributes attributes = new Attributes(); // ダミー生成（必要に応じてモック）

		// テスト対象のインスタンスを生成→メンバ変数で定義済み
		//LoginController controller = new LoginController();

		// メソッド呼び出し
		String actual = controller.loginEntry(webRequest, attributes);

		// 結果検証
		Assertions.assertThat(actual).isEqualTo("auth/login");
	}

	@Test //IDとパスワードが正しい（成功ケース）
	public void testLoginExecute01() {
		// 前処理：DBにテストユーザーをセット
		dbHelper.doSQLFile("test/univelec/controller.auth/LoginControllerTest_testLoginController01.sql");
		int id = 10001;
		String password = "testpass1"; // ✅ セミコロン追加

		WebRequest webRequest = new WebRequest();
		webRequest.setParameter("id", new String[] { String.valueOf(id) }); // ✅ 配列で渡す
		webRequest.setParameter("password", new String[] { password }); // ✅ 配列で渡す

		Attributes attributes = new Attributes();

		String actual = controller.loginExecute(webRequest, attributes);

		Assertions.assertThat(actual).isEqualTo("/menu/showMenuEntry");
		Assertions.assertThat(attributes.getPrincipal()).isNotNull();
	}

	@Test //ID未入力＆エラー
	public void testLoginExecute02() {
		// 引数の準備
		WebRequest webRequest = new WebRequest();
		webRequest.setParameter("id", ""); // ID未入力
		webRequest.setParameter("password", "password123");

		Attributes attributes = new Attributes();

		// 実行
		String actual = controller.loginExecute(webRequest, attributes);

		// 戻り値検証
		Assertions.assertThat(actual).isEqualTo("auth/login");

		// メッセージ検証
		Messages messages = attributes.getMessages();
		Assertions.assertThat(messages.getErrorMessages())
				.contains("IDが未入力です。");
	}

	@Test //IDが数字でないエラー
	public void testLoginExecute03() {
		WebRequest webRequest = new WebRequest();
		webRequest.setParameter("id", "abc"); // 数字じゃない
		webRequest.setParameter("password", "password123");

		Attributes attributes = new Attributes();

		String actual = controller.loginExecute(webRequest, attributes);

		Assertions.assertThat(actual).isEqualTo("auth/login");

		Messages messages = attributes.getMessages();
		Assertions.assertThat(messages.getErrorMessages())
				.contains("IDには数字を入れてください。");
	}

	@Test
	public void testLogoutExecute() {
		// 前処理：ダミーの引数を作る（使わないのでnullでもOK）
		WebRequest webRequest = new WebRequest(); // ダミー生成（必要に応じてモック）
		Attributes attributes = new Attributes(); // ダミー生成（必要に応じてモック）
		// メソッド呼び出し
		String actual = controller.logoutExecute(webRequest, attributes);

		// 結果検証
		Assertions.assertThat(actual).isEqualTo("auth/login");
		// メッセージの検証（"ログアウトしました。" が含まれているか）
		Messages messages = attributes.getMessages();
		Assertions.assertThat(messages.getInfoMessages())
				.contains("ログアウトしました。");
	}

}
