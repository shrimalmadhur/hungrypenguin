package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

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
		JSONObject json = new JSONObject();
		
		if (user == null) {
			json.put("error", "No such user");
		} else {
			json.put("user", user.toJson());
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		out.print(json);
		out.flush();
	}

}
