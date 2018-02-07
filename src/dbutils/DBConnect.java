package dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DBConnect {

	private static String dbURL = "jdbc:mysql://localhost/";
	private static String dbUser = "root";
	private static String dbPassword = "";
	private static String dbName = "quiz";

	public static Connection createConnection() {
		Connection con = null;
		try {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error ...");
				System.exit(1);
			}
			con = DriverManager.getConnection(dbURL + dbName, dbUser, dbPassword);
		} catch (SQLException sqe) {
			System.out.println("Error ...");
			sqe.printStackTrace();
		}
		return con;
	}

	public static boolean createDB() {
		boolean status = false;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

			stmt = conn.createStatement();

			String sql = "CREATE DATABASE IF NOT EXISTS quiz;";
			stmt.executeUpdate(sql);
			status = true;
		} catch (Exception e) {
			status = false;
			System.out.println("Error ...");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				System.out.println("Error ...");
			}
		}
		return status;
	}

	public static boolean createTable() {

		boolean status = false;
		Connection con = DBConnect.createConnection();

		try {
			Statement st = con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS users"
					+ "(username VARCHAR(100), password VARCHAR(100), email VARCHAR(100), java INT(10),"
					+ " javascript INT(10),  sequel INT(10),  python INT(10),  css INT(10),  php INT(10),  c INT(10),  ruby INT(10));";
			String sql2 = "ALTER TABLE users ADD UNIQUE(username);";

			st.executeUpdate(sql);
			st.executeUpdate(sql2);
			status = true;
		} catch (SQLException sqe) {
			System.out.println("Error... ");
			sqe.printStackTrace();
			status = false;
		}

		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error ...");
		}

		return status;
	}

	public static boolean checkUsername(String username) {

		boolean exists = false;
		Connection con = DBConnect.createConnection();

		try {
			Statement st = con.createStatement();
			String sql = "Select * from  users where username='" + username + "'";
			ResultSet set = st.executeQuery(sql);
			if(set.next())
				exists = true;
			st.close();
		} catch (Exception e) {
			exists = false;
		}

		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error ...");
		}

		return exists;
	}

	public static LinkedList<Integer> retrieveUserData(String username) {

		LinkedList<Integer> tests = new LinkedList<>();
		Connection con = DBConnect.createConnection();

		try {
			Statement st = con.createStatement();
			String sql = "Select * from users where username='" + username + "'";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				tests.add(rs.getInt("java"));
				tests.add(rs.getInt("python"));
				tests.add(rs.getInt("c"));
			}

			st.close();
		} catch (Exception e) {
			System.out.println("Error ...");
			System.out.println(e);
		}

		return tests;
	}
}
