package employee;

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
 * Servlet implementation class searchShainServlet
 */
@WebServlet("/searchShainServlet")
public class searchShainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public searchShainServlet() {
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
		String shainDepartmentName = request.getParameter("shainDepartmentName");
		String shainId = request.getParameter("shainId");
		String shainName = request.getParameter("shainName");
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
		String sql = "select * from SHAIN where 1=1 ";

			if(shainId!=""){
			sql+="and ID='"+shainId+"' ";}
			if(shainName!=""){
			sql+="and NAME like '%"+shainName+"%' ";}
			if(shainDepartmentName!=null){
			sql+="and DEPARTMENT_ID='"+shainDepartmentName+"' ";}

		// 商品リスト（Item型のリスト）
		List<Shain> ShainList = new ArrayList<>();

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
				Shain s = new Shain();
				s.setShain_id(rs1.getString("ID"));
				s.setShain_name(rs1.getString("NAME"));
				s.setAge(rs1.getString("AGE"));
				s.setSex(rs1.getString("SEX"));
				s.setHome(rs1.getString("ADDRESS"));
				s.setShainDepartmentName(rs1.getString("DEPARTMENT_ID"));
				ShainList.add(s);
			}
		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

		// 画面へレスポンスを返却する処理
		System.out.println(ShainList);
		PrintWriter pw = response.getWriter();

		pw.append(new ObjectMapper().writeValueAsString(ShainList));
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
