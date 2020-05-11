package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.employeeDAO;

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

		HttpSession session = request.getSession();
			session.removeAttribute("user");//なぜこれでうまくいく？新たに宣言したのに消すって何？


			employeeDAO.loadDB();

		// 実行するSQL文
		String sql ="select * " +
				"from " +
				"AUTHENTICATION_INFO " +
				"where 1=1 " +
				"and SHAIN_ID='"+LoginId+"' " +
				"and PASSWORD='"+LoginPassword+"' " ;

		// TO DO 返却用のMap作る
		Map<String, String> status = new HashMap<>();

		try (

				//Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				Connection con = DriverManager.getConnection(employeeDAO.dbUrl, employeeDAO.dbUser, employeeDAO.dbPass);

				Statement stmt = con.createStatement();

				ResultSet rs1 = stmt.executeQuery(sql);) {
			if(rs1.next()){
			// セッションにユーザーコードを保存する
			session.setAttribute("user", rs1.getString("SHAIN_ID") );
			status.put("user", rs1.getString("SHAIN_ID") );

			session.setAttribute("roll", rs1.getString("ROLL") );
			status.put("roll", rs1.getString("ROLL") );


			}


		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

		// 画面へレスポンスを返却する処理
		PrintWriter pw = response.getWriter();
		pw.append(new ObjectMapper().writeValueAsString(status));


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
