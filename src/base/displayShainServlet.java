package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.employeeDAO;

/**
 * Servlet implementation class displayShainServlet
 */
@WebServlet("/displayShainServlet")
public class displayShainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public displayShainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		// TODO 必須機能「趣味参照機能」
		// JDBCドライバの準備

		HttpSession session = request.getSession();
		String sessionUserCd = (String) session.getAttribute("user");
		String sessionRoll = (String) session.getAttribute("roll");

		PrintWriter pw = response.getWriter();//こいつ重要、こいつを返すのがこのサーブレットの役目



		if (sessionUserCd == null) {
			pw.append(new ObjectMapper().writeValueAsString("NOT LOGIN"));
		} else {

		//--------------------------------------
			employeeDAO.loadDB();
			List<Shain> ShainList = new ArrayList<>();
			try (

					Connection con = DriverManager.getConnection(employeeDAO.dbUrl, employeeDAO.dbUser,
							employeeDAO.dbPass);

					PreparedStatement stmt = createPreparedStatement(con);

					ResultSet rs1 = stmt.executeQuery();) {

				// SQL実行後の処理内容
				setEmployees(sessionUserCd, sessionRoll, ShainList, rs1);

			} catch (Exception e) {
				throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
			}
		//--------------------------------------


			// 画面へレスポンスを返却する処理
			pw.append(new ObjectMapper().writeValueAsString(ShainList));
		}

	}

	public void setEmployees(String sessionUserCd, String sessionRoll, List<Shain> ShainList, ResultSet rs1)
			throws SQLException {
		while (rs1.next()) {
			Shain s = new Shain();
			s.setShain_id(rs1.getString("ID"));
			s.setShain_name(rs1.getString("NAME"));
			s.setAge(rs1.getString("AGE"));
			s.setSex(rs1.getString("SEX"));
			s.setHome(rs1.getString("ADDRESS"));
			s.setShainDepartmentName(rs1.getString("DEPARTMENT_ID"));

			if (sessionUserCd.equals(rs1.getString("ID"))) {
				s.setLoginuser(true);
			} else {
				s.setLoginuser(false);
			}
			if ("manager".equals(sessionRoll)) {
				s.setManager(true);
			} else {
				s.setManager(false);
			}

			// 作成した一つ分のItem型をリストに追加
			ShainList.add(s);
		}
	}

	private PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		// 実行するSQL文
		String sql = "select ID,NAME,AGE,SEX,ADDRESS,DEPARTMENT_ID from  SHAIN";
		PreparedStatement stmt = con.prepareStatement(sql);
		return stmt;
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
