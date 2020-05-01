package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String LoginId = request.getParameter("LoginId");
		String LoginPassword = request.getParameter("LoginPassword");
		response.setContentType("text/html; charset=UTF-8");
		// TODO 必須機能「趣味参照機能」
		// JDBCドライバの準備
		try {
			// JDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// ドライバが設定されていない場合はエラーになります
			throw new RuntimeException(String.format("JDBCドライバのロードに失敗しました。詳細:[%s]", e.getMessage()), e);
		}

		// データベースにアクセスするために、データベースのURLとユーザ名とパスワードを指定
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String dbUser = "webapp";
		String dbPass = "webapp";

		// 実行するSQL文
		String sql ="select \n" +
				"* \n" +
				"from \n" +
				"AUTHENTICATION_INFO \n" +
				"where 1=1 \n" +
				"and SHAIN_ID='EMP0001' \n" +
				"and PASSWORD='2687' \n" ;


		// DBに接続してSQLを実行
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();
				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
				ResultSet rs1 = stmt.executeQuery(sql);) {

			// SQL実行後の処理内容
			// ユーザー情報をセッションに保存
			// セッションを取得
			if(rs1.next()){
			HttpSession session = request.getSession();
			// セッションにユーザーコードを保存する
			session.setAttribute("user", rs1.getString("SHAIN_ID") );
			System.out.print(session.getAttribute("user"));
			System.out.println(rs1);
			}
//			PrintWriter pw = response.getWriter();
//			pw.append(new ObjectMapper().writeValueAsString(rs1));

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

		// 画面へレスポンスを返却する処理
		PrintWriter pw = response.getWriter();
	//	pw.append(new ObjectMapper().writeValueAsString("ok"));


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
