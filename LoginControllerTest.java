package univelec.controller.auth;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fw.attribute.Attributes;
import fw.request.WebRequest;
import univelec.util.DBHelper;

public class LoginControllerTest {
	DBHelper dbHelper;

	LoginControllerTest controller;

	@Before
	public void setUp() throws Exception {
		dbHelper = new DBHelper();
		controller = new LoginControllerTest();
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

		// テスト対象のインスタンスを生成
		LoginController controller = new LoginController();

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
		fail("まだ実装されていません");
	}

}
