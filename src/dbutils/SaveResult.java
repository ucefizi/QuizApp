package dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveResult {

	public SaveResult() {
	}

	public static void save(String user, int result, String selectedExam) {

		Connection con = DBConnect.createConnection();

		try {
			Statement st = con.createStatement();
			String sql = "UPDATE users SET " + selectedExam + " = " + result + " WHERE username = '" + user + "'";

			st.executeUpdate(sql);
		} catch (SQLException sqe) {
			System.out.println("Error ...");
		}

		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error ...");
		}
	}
}
