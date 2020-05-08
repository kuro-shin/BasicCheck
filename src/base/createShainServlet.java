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
 * Servlet implementation class createShainServlet
 */
@WebServlet("/createShainServlet")
public class createShainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public createShainServlet() {
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
		String shainId = request.getParameter("shainId");
		String shainName = request.getParameter("shainName");
		String shainAge = request.getParameter("shainAge");
		String sex = request.getParameter("sex");
		String home = request.getParameter("home");
		String shainDepartmentName = request.getParameter("shainDepartmentName");
		// JDBCドライバの準備
		employeeDAO.loadDB();

		// データベースにアクセスするために、データベースのURLとユーザ名とパスワードを指定

		// 実行するSQL文
					//TO DO...IDの設定

		// DBに接続してSQLを実行
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(employeeDAO.dbUrl, employeeDAO.dbUser, employeeDAO.dbPass);

				// SQLの命令文を実行するための準備をおこないます
			//	Statement stmt = con.createStatement();
				PreparedStatement stmt = createPreparedStatement(con,shainId,shainName,shainAge,sex,home,shainDepartmentName);
				) {
			int resultCount = stmt.executeUpdate();

			System.out.println(resultCount+"件作成しました");

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

	}

		private PreparedStatement createPreparedStatement(Connection con,String shainId,String shainName,String shainAge,String sex,String home,String shainDepartmentName) throws SQLException {
			// 実行するSQL文
			String sql ="insert into SHAIN " +
					"(ID,NAME,AGE,SEX,ADDRESS,DEPARTMENT_ID)" +
					"values " +
					"(?,?,?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,shainId);
			stmt.setString(2,shainName);
			int i = Integer.parseInt(shainAge);
			stmt.setInt(3,i);
			stmt.setString(4,sex);
			stmt.setString(5,home);
			stmt.setString(6,shainDepartmentName);

			return stmt;
		}



}
