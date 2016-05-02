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
import db.ReviewDatabaseHelper;
import db.UserDatabaseHelper;
import model.Dish;
import model.Review;
import model.User;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/review")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewDatabaseHelper db;
	private DishDatabaseHelper dishDb;
	private UserDatabaseHelper userDb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        db = new ReviewDatabaseHelper();
        dishDb = new DishDatabaseHelper();
        userDb = new UserDatabaseHelper();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		int id = Integer.parseInt(request.getParameter("id"));
		Dish d = dishDb.getById(id);
		if (d == null) {
			JSONObject json = new JSONObject();
			json.put("error", "No such dish");
			out.print(json);
			out.flush();
			return;
		}
		
		Map<String, String> criteria = new HashMap<>();
		criteria.put(ReviewDatabaseHelper.KEY_DISH_ID, String.valueOf(d.getId()));
		List<Review> reviews = db.selectAll(criteria);
		JSONArray json = new JSONArray();
		for (Review review : reviews) {
			json.put(review.toJson());
		}
		
		out.print(json);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		int dishId = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String review = request.getParameter("review");
		
		Dish d = dishDb.getById(dishId);
		if (d == null) {
			JSONObject json = new JSONObject();
			json.put("error", "No such dish");
			out.print(json);
			out.flush();
			return;
		}
		
		User u = userDb.getByUsername(username);
		if (u == null) {
			JSONObject json = new JSONObject();
			json.put("error", "No such user");
			out.print(json);
			out.flush();
			return;
		}
		
		Review r = new Review(review, u.getId(), d.getId());
		db.insert(r);
		
		System.out.println(r);
		out.print(r.toJson());
		out.flush();
	}

}
