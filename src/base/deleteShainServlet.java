package base;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.employeeDAO;
/**
 * Servlet implementation class deleteShainServlet
 */
@WebServlet("/deleteShainServlet")
public class deleteShainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteShainServlet() {
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
		String id = request.getParameter("q");

		// JDBCドライバの準備
		employeeDAO.loadDB();

		// データベースにアクセスするために、データベースのURLとユーザ名とパスワードを指定

		// DBに接続してSQLを実行
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(employeeDAO.dbUrl, employeeDAO.dbUser, employeeDAO.dbPass);

				// SQLの命令文を実行するための準備をおこないます
				//Statement stmt = con.createStatement();
				PreparedStatement stmt = createPreparedStatement(con,id);
				) {
			int resultCount = stmt.executeUpdate();//1つのSQL文しか実行できない

			System.out.println(resultCount+"件削除");

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}
	}

	private PreparedStatement createPreparedStatement(Connection con, String id) throws SQLException {
		// 実行するSQL文
		String sql = "delete " +
				"from " +
				"SHAIN " +
				"where " +
				"ID=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, id);
		return stmt;
	}

}
