package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbutils.DBConnect;
import security.SecurityMechanism;

@WebServlet("/checkRegister")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Connection con = DBConnect.createConnection();

		boolean exists = DBConnect.checkUsername(username);

		try {
			Statement st = con.createStatement();
			SecurityMechanism sm = new SecurityMechanism();
			String sql = "INSERT INTO users values ('" + username + "','" + sm.getEncrypted(password) + "','" + email
					+ "'" + ",0,0,0,0,0,0,0,0)";
			st.execute(sql);
		} catch (SQLException sqe) {
			System.out.println("Error...");
			sqe.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error ...");
		}
		request.setAttribute("newUser", username);

		if (!exists) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/regSuccess.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/regFailure.jsp");
			dispatcher.forward(request, response);
		}
	}
}
