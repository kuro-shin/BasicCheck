package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class editDisplayShainServlet
 */
@WebServlet("/editDisplayShainServlet")
public class editDisplayShainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public editDisplayShainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		String shain_id = request.getParameter("shain_id");
		// TODO 必須機能「趣味参照機能」
		// JDBCドライバの準備
		connectDB();
		// データベースにアクセスするために、データベースのURLとユーザ名とパスワードを指定
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String dbUser = "webapp";
		String dbPass = "webapp";
		// 実行するSQL文
		String sql = createSql(shain_id);
		Shain s = new Shain();
		// DBに接続してSQLを実行
		getShainInfomation(dbUrl, dbUser, dbPass, sql, s);

		// 画面へレスポンスを返却する処理
		System.out.println(s);
		PrintWriter pw = response.getWriter();
		pw.append(new ObjectMapper().writeValueAsString(s));
	}

	public void getShainInfomation(String dbUrl, String dbUser, String dbPass, String sql, Shain s) {
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();

				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
				ResultSet rs1 = stmt.executeQuery(sql);) {
			// SQL実行後の処理内容
			// 取得結果分だけループを回してitemListに追加していく。
			setShain(s, rs1);
		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}
	}

	public void setShain(Shain s, ResultSet rs1) throws SQLException {
		while (rs1.next()) {
			// 商品ごとに新しいItemインスタンスを作
			s.setShain_id(rs1.getString("ID"));// Item型の変数itemに販売単価をセット
			s.setShain_name(rs1.getString("NAME"));// Item型の変数itemに税区分をセット
			s.setAge(rs1.getString("AGE"));
			s.setSex(rs1.getString("SEX"));
			s.setHome(rs1.getString("ADDRESS"));
			s.setShainDepartmentName(rs1.getString("DEPARTMENT_ID"));

			//System.out.println(s.getHome());
			System.out.println(rs1.getString("DEPARTMENT_ID")+"だよ");

		}
	}

	public String createSql(String shain_id) {
		String sql ="select \n" +
				"* \n" +
				"from \n" +
				"SHAIN \n" +
				"where \n" +
				"ID='"+shain_id+"' \n";
		return sql;
	}

	public void connectDB() {
		try {
			// JDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// ドライバが設定されていない場合はエラーになります
			throw new RuntimeException(String.format("JDBCドライバのロードに失敗しました。詳細:[%s]", e.getMessage()), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
