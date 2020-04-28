package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class displayDepartmentServlet
 */
@WebServlet("/displayDepartmentServlet")
public class displayDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public displayDepartmentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		String sql = "select ID,NAME from DEPARTMENT";

		// 商品リスト（Item型のリスト）
		List<Department> DepartmentList = new ArrayList<>();

		// DBに接続してSQLを実行
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();

				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
				ResultSet rs1 = stmt.executeQuery(sql);) {

			// SQL実行後の処理内容
			// 取得結果分だけループを回してitemListに追加していく。
			while (rs1.next()) {
				// 商品ごとに新しいItemインスタンスを作
				Department d = new Department();
				d.setDepartment_id(rs1.getString("ID"));// Item型の変数itemに販売単価をセット
				d.setDepartment_name(rs1.getString("NAME"));// Item型の変数itemに税区分をセット
				// 作成した一つ分のItem型をリストに追加
				DepartmentList.add(d);
			}
		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

		// 画面へレスポンスを返却する処理
		System.out.println(DepartmentList);
		PrintWriter pw = response.getWriter();

		pw.append(new ObjectMapper().writeValueAsString(DepartmentList));

		// -- ここまで --
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
