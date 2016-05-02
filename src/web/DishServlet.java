package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import db.DishDatabaseHelper;
import model.Dish;

/**
 * Servlet implementation class DishServlet
 */
@WebServlet("/dish")
public class DishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DishDatabaseHelper db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishServlet() {
        super();
        db = new DishDatabaseHelper();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		Dish d = db.getById(id);
		if (d == null) {
			JSONObject json = new JSONObject();
			json.put("error", "No such dish");
			out.print(json);
			out.flush();
			return;
		}
		
		out.print(d.toJson());
		out.flush();
	}

}
