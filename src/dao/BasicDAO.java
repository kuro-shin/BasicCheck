package dao;

public class BasicDAO {

	public static final String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String dbUser = "webapp";
	public static final String dbPass = "webapp";



	public static void loadDB() {
		try {
			// JDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// ドライバが設定されていない場合はエラーになります
			throw new RuntimeException(String.format("JDBCドライバのロードに失敗しました。詳細:[%s]", e.getMessage()), e);
		}
	}

}
