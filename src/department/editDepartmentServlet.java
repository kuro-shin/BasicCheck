package department;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editDepartmentServlet
 */
@WebServlet("/editDepartmentServlet")
public class editDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public editDepartmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		// リクエストパラメータの取得
				String renew_department_id = request.getParameter("renew_department_id");
				String renew_department_name = request.getParameter("renew_department_name");


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
				String sql = "UPDATE DEPARTMENT \n" +
							"set NAME='"+renew_department_name+"'\n" +
								"where \n" +
								"ID='"+renew_department_id+"' \n";



				// 受注リスト（Order型のリスト）

				// DBに接続してSQLを実行
				try (
						// データベースへ接続します
						Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

						// SQLの命令文を実行するための準備をおこないます
						Statement stmt = con.createStatement();	) {
					int resultCount = stmt.executeUpdate(sql);

					System.out.println(resultCount+"件departmentを編集したよ");

				} catch (Exception e) {
					throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
				}

	}

}
