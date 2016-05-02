/**
 * @author cvandera
 *
 */

package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.RestaurantDish;

public class RestaurantDishDatabaseHelper extends DatabaseHelper<RestaurantDish> {

	// Table Names
	private static final String TABLE_RESTAURANT_DISH = "restaurant_dish";
	private static final String TABLE_RESTAURANT = "restaurant";
	private static final String TABLE_DISH = "dish";

	// ORDER_DISH Table - column names
	private static final String KEY_DISH_ID = "dish_id";
	private static final String KEY_RESTAURANT_ID = "restaurant_id";

	private final String CREATE_RESTAURANT_DISH_TABLE = "CREATE TABLE IF NOT EXISTS " + 
			TABLE_RESTAURANT_DISH + "(" + 
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
			KEY_RESTAURANT_ID + " INTEGER," + 
			KEY_DISH_ID + " INTEGER," +
			"FOREIGN KEY(" + KEY_RESTAURANT_ID + ") REFERENCES " + TABLE_RESTAURANT + "(" + KEY_ID + ")," + 
			"FOREIGN KEY(" + KEY_DISH_ID + ") REFERENCES " + TABLE_DISH + "(" + KEY_ID + ")" + ");";
	
	public RestaurantDishDatabaseHelper() {
		this.createTable();
	}
	
	@Override
	public void insert(RestaurantDish object) {
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ");
			sb.append(TABLE_RESTAURANT_DISH + " (");
			sb.append(KEY_RESTAURANT_ID + ",");
			sb.append(KEY_DISH_ID + ")");
			sb.append(" VALUES (?, ?);");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, String.valueOf(object.getRestaurantId()));
			stmt.setString(2, String.valueOf(object.getDishId()));

			stmt.executeUpdate();

			stmt.close();
			this.closeConnection(c);
			
			getRecentlyCreatedObject(object);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Records created successfully");
	}
	
	private void getRecentlyCreatedObject(RestaurantDish object) {
		Map<String, String> criteria = new HashMap<String, String>();
		if (object.getRestaurantId() > 0) criteria.put(KEY_RESTAURANT_ID, String.valueOf(object.getRestaurantId()));
		if (object.getDishId() > 0) criteria.put(KEY_DISH_ID, String.valueOf(object.getDishId()));

		RestaurantDish restaurantDish = select(criteria);
		if (restaurantDish != null) {
			object.setId(restaurantDish.getId());
		}
	}

	@Override
	public void update(RestaurantDish object) {
		if (object.getId() <= 0)
			return;
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE ");
			sb.append(TABLE_RESTAURANT_DISH + " SET ");
			sb.append(KEY_RESTAURANT_ID + " = ?, ");
			sb.append(KEY_DISH_ID + " = ?");
			sb.append(" WHERE ");
			sb.append("id = ?;");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, String.valueOf(object.getRestaurantId()));
			stmt.setString(2, String.valueOf(object.getDishId()));

			stmt.executeUpdate();

			stmt.close();
			this.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Operation done successfully");
	}

	@Override
	public void delete(RestaurantDish object) {
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM ");
			sb.append(TABLE_RESTAURANT_DISH);
			sb.append(" WHERE ");
			sb.append(KEY_ID + " = ");
			sb.append(object.getId());
			sb.append(";");

			Statement stmt = c.createStatement();
			stmt.executeUpdate(sb.toString());

			stmt.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Operation done successfully");
	}

	@Override
	public List<RestaurantDish> selectAll(Map<String, String> criteria) {
		List<RestaurantDish> res = new ArrayList<RestaurantDish>();
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ");
			sb.append(TABLE_RESTAURANT_DISH);

			String[] keys = null;
			if (criteria != null && criteria.size() > 0) {
				sb.append(" WHERE ");
				keys = new String[criteria.size()];

				int i = 0;
				Iterator<Map.Entry<String, String>> it = criteria.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
					sb.append(pair.getKey() + " = ?");
					keys[i] = pair.getKey();
					i++;
					if (it.hasNext()) {
						sb.append(" AND ");
					}
				}
			}
			sb.append(" ORDER BY " + KEY_ID + " DESC");
			sb.append(";");

			PreparedStatement stmt = c.prepareStatement(sb.toString());
			if (keys != null) {
				for (int i = 0; i < keys.length; i++) {
					stmt.setString(i + 1, criteria.get(keys[i]));
				}
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(KEY_ID);
				int dishId = rs.getInt(KEY_DISH_ID);
				int restaurantId = rs.getInt(KEY_RESTAURANT_ID);
				RestaurantDish rd = new RestaurantDish(restaurantId, dishId);
				rd.setId(id);
				res.add(rd);
			}
			rs.close();
			stmt.close();

			this.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Operation done successfully");

		return res;
	}

	@Override
	public void createTable() {
		create(CREATE_RESTAURANT_DISH_TABLE);
	}

	@Override
	public RestaurantDish select(Map<String, String> criteria) {
		List<RestaurantDish> restaurantDishes = this.selectAll(criteria);
		if (restaurantDishes.size() > 0) return restaurantDishes.get(0);
		return null;
	}
	
	public List<RestaurantDish> getByRestaurant(int restaurantId) {
		Map<String, String> criteria = new HashMap<>();
		criteria.put(KEY_RESTAURANT_ID, String.valueOf(restaurantId));
		return this.selectAll(criteria);
	}
	
}
