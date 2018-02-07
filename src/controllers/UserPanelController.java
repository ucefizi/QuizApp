package controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbutils.DBConnect;
import models.Exam;

@WebServlet("/userPanel")
public class UserPanelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserPanelController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		session.setAttribute("user", username);

		LinkedList<Integer> tests = DBConnect.retrieveUserData(username);

		ListIterator<Integer> listIterator = tests.listIterator();
		Integer[] array = new Integer[50];
		int i = 0;

		while (listIterator.hasNext()) {
			array[i] = listIterator.next();
			i++;
		}

		float multiplier = 100 / (Exam.numberOfQuestions);
		request.setAttribute("java", multiplier * array[0]);
		request.setAttribute("python", multiplier * array[1]);
		request.setAttribute("c", multiplier * array[2]);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/userPanel.jsp");
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
