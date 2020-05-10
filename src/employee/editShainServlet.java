package employee;

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
 * Servlet implementation class editShainServlet
 */
@WebServlet("/editShainServlet")
public class editShainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public editShainServlet() {
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
		String shain_id = request.getParameter("shain_id");
		String renew_shain_id = request.getParameter("renew_shain_id");
		String renew_shain_name = request.getParameter("renew_shain_name");
		String renew_age = request.getParameter("renew_age");
		String renew_sex = request.getParameter("renew_sex");
		String renew_home = request.getParameter("renew_home");
		String renew_Shain_DepartmentName = request.getParameter("renew_Shain_DepartmentName");

		employeeDAO.loadDB();

		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(employeeDAO.dbUrl, employeeDAO.dbUser, employeeDAO.dbPass);

				// SQLの命令文を実行するための準備をおこないます
				//Statement stmt = con.createStatement();
				PreparedStatement stmt = createPreparedStatement(con,shain_id,renew_shain_id,renew_shain_name,renew_age,renew_sex,renew_home,renew_Shain_DepartmentName);
				) {
			int resultCount = stmt.executeUpdate();

			System.out.println(resultCount+"件社員編集したよ");

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

}

	private PreparedStatement createPreparedStatement(Connection con, String shain_id,String renew_shain_id,String renew_shain_name,String renew_age,String renew_sex,String renew_home,String renew_Shain_DepartmentName) throws SQLException {
		// 実行するSQL文
		String sql = "UPDATE SHAIN \n" +
				"SET ID =?, NAME =?, AGE=?,SEX=?,ADDRESS=?,DEPARTMENT_ID=? " +
				"WHERE id = ?" ;

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, renew_shain_id);
		stmt.setString(2, renew_shain_name);
		int i = Integer.parseInt(renew_age);
		stmt.setInt(3,i);
		stmt.setString(4, renew_sex);
		stmt.setString(5, renew_home);
		stmt.setString(6, renew_Shain_DepartmentName);
		stmt.setString(7, shain_id);
		return stmt;
	}

}
