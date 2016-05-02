package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import db.DishDatabaseHelper;
import db.OrderDatabaseHelper;
import db.UserDatabaseHelper;
import model.Dish;
import model.Order;
import model.Restaurant;
import model.User;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDatabaseHelper db;
	private OrderDatabaseHelper orderDb;
	private DishDatabaseHelper dishDb;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        db = new UserDatabaseHelper();
        orderDb = new OrderDatabaseHelper();
        dishDb = new DishDatabaseHelper();
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
		
		List<Order> orders = orderDb.getOrdersByUser(u.getId());
		JSONArray json = new JSONArray();
		for (Order order : orders) {
			JSONObject o = order.toJson();
			// get restaurant for this order
			Restaurant r = order.getRestaurant();
			o.put("restaurant", r);
			
			// get all dishes for this order
			List<Dish> dishes = dishDb.getDishesByOrder(order.getId());
			JSONArray dishesJson = new JSONArray();
			for (Dish dish : dishes) {
				dishesJson.put(dish.toJson());
			}
			o.put("dishes", dishesJson);
			
			json.put(o);
		}
		
		out.print(json);
		out.flush();
	}

}
