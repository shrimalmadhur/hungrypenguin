package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserDatabaseHelper;
import model.User;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDatabaseHelper db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		db = new UserDatabaseHelper();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = db.authenticate(username, password);
		String res = "";
		if (user == null) {
			res = "{error: true, user: null}";
		} else {
			res = "{error: false, user: " + user.toString() + "}";
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		out.print(res);
		out.flush();
	}

}
