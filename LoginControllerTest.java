package univelec.controller.auth;

import static org.junit.Assert.*;

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

	@Test
	public void testLoginExecute() {
		fail("まだ実装されていません");
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
