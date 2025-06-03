package univelec.controller.auth;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fw.attribute.Attributes;
import fw.request.WebRequest;
import univelec.util.DBHelper;

public class LogoutControllerTest {
	DBHelper dbHelper;

	LogoutController controller;

	@Before
	public void setUp() throws Exception {
		dbHelper = new DBHelper();
		controller = new LogoutController();
	}

	@After
	public void tearDown() throws Exception {
		dbHelper.close();
	}

	@Test
	public void testLogoutExecute() {
		WebRequest webRequest = new WebRequest(); // ダミー生成（必要に応じてモック）
		Attributes attributes = new Attributes(); // ダミー生成（必要に応じてモック）
		// メソッド呼び出し
		String actual = controller.logoutExecute(webRequest, attributes);
		// 結果検証
		Assertions.assertThat(actual).isEqualTo("auth/login");
	}

}
