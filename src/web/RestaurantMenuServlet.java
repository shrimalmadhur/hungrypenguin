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
import org.json.JSONObject;

import db.DishDatabaseHelper;
import db.RestaurantDatabaseHelper;
import model.Dish;
import model.Restaurant;

/**
 * Servlet implementation class RestaurantMenuServlet
 */
@WebServlet("/restaurant/menu")
public class RestaurantMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RestaurantDatabaseHelper db;
    private DishDatabaseHelper dishDb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantMenuServlet() {
        super();
        db = new RestaurantDatabaseHelper();
        dishDb = new DishDatabaseHelper();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		int id = Integer.parseInt(request.getParameter("id"));
		Restaurant r = db.getById(id);
		if (r == null) {
			JSONObject json = new JSONObject();
			json.put("error", "No such restaurant");
			out.print(json);
			out.flush();
			return;
		}
		
		List<Dish> menu = dishDb.getDishesByRestaurant(id);
		JSONArray json = new JSONArray();
		for (Dish dish : menu) {
			json.put(dish.toJson());
		}
		
		System.out.println(json);
		out.print(json);
		out.flush();
	}

}
