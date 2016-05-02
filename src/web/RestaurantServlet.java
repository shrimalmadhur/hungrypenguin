package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import db.RestaurantDatabaseHelper;
import model.Restaurant;

/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet("/restaurants")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantDatabaseHelper db;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestaurantServlet() {
		super();
		db = new RestaurantDatabaseHelper();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Restaurant> restaurants = db.selectAll(null);
		JSONArray json = new JSONArray();
		for (Restaurant r : restaurants) {
			json.put(r.toJson());
		}
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		System.out.println(json);
		out.print(json);
		out.flush();
	}

}
