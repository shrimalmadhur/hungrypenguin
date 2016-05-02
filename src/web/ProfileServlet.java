package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import db.UserDatabaseHelper;
import model.User;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDatabaseHelper db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        db = new UserDatabaseHelper();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		Map<String, String> criteria = new HashMap<>();
		criteria.put(UserDatabaseHelper.KEY_USER_USERNAME, username);
		User u = db.select(criteria);
		if (u == null) {
			JSONObject json = new JSONObject();
			json.put("error", "No such user");
			out.print(json);
			out.flush();
			return;
		}
		
		out.print(u.toJson());
		out.flush();
	}


}
