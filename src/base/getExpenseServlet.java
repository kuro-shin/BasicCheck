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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class getExpenseServlet
 */
@WebServlet("/getExpenseServlet")
public class getExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public getExpenseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

		HttpSession session = request.getSession();
		String sessionUserCd = (String) session.getAttribute("user");
		String sessionRoll = (String) session.getAttribute("roll");

		// データベースにアクセスするために、データベースのURLとユーザ名とパスワードを指定
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String dbUser = "webapp";
		String dbPass = "webapp";

		// 実行するSQL文
		String sql = "select \n" +
				"* \n" +
				"from \n" +
				"EXPENSE e, \n" +
				"SHAIN s \n" +
				"where \n" +
				"e.APPLICANT_CODE=s.ID \n" ;

		// 商品リスト（Item型のリスト）
		List<Expense> ExpenseList = new ArrayList<>();

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
				Expense e = new Expense();
				e.setCode(rs1.getString("CD"));
				e.setToday(rs1.getString("TODAY"));
				e.setApplicant_name(rs1.getString("APPLICANT_NAME"));//ここ注意
				e.setApplicant_code(rs1.getString("ID"));
				e.setTitle(rs1.getString("TITLE"));
				e.setPayee(rs1.getString("PAYEE"));
				e.setPrice(rs1.getString("PRICE"));
				e.setUpater_name(rs1.getString("UPDATER_NAME"));
				e.setUpdated_date(rs1.getString("UPDATED_DATE"));
				e.setStatus(rs1.getString("STATUS"));
				e.setReason(rs1.getString("REASON"));
				// 作成した一つ分のItem型をリストに追加
				ExpenseList.add(e);
				if (sessionUserCd.equals(rs1.getString("ID"))) {
					e.setLoginuser(true);
				} else {
					e.setLoginuser(false);
				}
				if ("manager".equals(sessionRoll)) {
					e.setManager(true);
				} else {
					e.setManager(false);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

		// 画面へレスポンスを返却する処理
		//System.out.println(ExpenseList);
		PrintWriter pw = response.getWriter();

		pw.append(new ObjectMapper().writeValueAsString(ExpenseList));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
