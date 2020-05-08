package dao;

public class employeeDAO {

	public static final String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String dbUser = "webapp";
	public static final String dbPass = "webapp";

	public static void loadDB() {
		try {
			// JDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// ドライバが設定されていない場合はエラーになります
			throw new RuntimeException(String.format("JDBCドライバのロードに失敗しました。詳細:[%s]", e.getMessage()), e);
		}
	}

//	public List<Shain> addAll(){
//		String sql = "select ID,NAME,AGE,SEX,ADDRESS,DEPARTMENT_ID from  SHAIN";
//		List<Shain> EmployeeList = new ArrayList<>();
//;		try (
//
//				Connection con = DriverManager.getConnection(dbUrl, dbUser,dbPass);
//				PreparedStatement stmt = con.prepareStatement(sql);
//				ResultSet rs1 = stmt.executeQuery();) {
//
//			// SQL実行後の処理内容
//			while (rs1.next()) {
//				Shain s = new Shain();
//				s.setShain_id(rs1.getString("ID"));
//				s.setShain_name(rs1.getString("NAME"));
//				s.setAge(rs1.getString("AGE"));
//				s.setSex(rs1.getString("SEX"));
//				s.setHome(rs1.getString("ADDRESS"));
//				s.setShainDepartmentName(rs1.getString("DEPARTMENT_ID"));
//
//				if (sessionUserCd.equals(rs1.getString("ID"))) {
//					s.setLoginuser(true);
//				} else {
//					s.setLoginuser(false);
//				}
//				if ("manager".equals(sessionRoll)) {
//					s.setManager(true);
//				} else {
//					s.setManager(false);
//				}
//
//				EmployeeList.add(s);
//			}
//
//		} catch (Exception e) {
//			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
//		}
//		return EmployeeList;
//	}




}
