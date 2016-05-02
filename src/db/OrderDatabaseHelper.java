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

import model.Order;

public class OrderDatabaseHelper extends DatabaseHelper<Order> {

	// Table Names
	private static final String TABLE_ORDER = "food_order";
	private static final String TABLE_USER = "user";
	private static final String TABLE_RESTAURANT = "restaurant";

	// ORDER Table - column names
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_RESTAURANT_ID = "restaurant_id";
	public static final String KEY_ORDER_COST = "total_cost";

	private final String CREATE_ORDER_TABLE = "CREATE TABLE IF NOT EXISTS " + 
			TABLE_ORDER + "(" + 
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
			KEY_USER_ID + " INTEGER," + 
			KEY_RESTAURANT_ID + " INTEGER," + 
			KEY_ORDER_COST + " DOUBLE," + 
			"FOREIGN KEY(" + KEY_USER_ID + ") REFERENCES " + TABLE_USER + "(" + KEY_ID + ")," + 
			"FOREIGN KEY(" + KEY_RESTAURANT_ID + ") REFERENCES " + TABLE_RESTAURANT + "(" + KEY_ID + ")" + ");";
	
	public OrderDatabaseHelper() {
		this.createTable();
	}
	
	@Override
	public void insert(Order object) {
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ");
			sb.append(TABLE_ORDER + " (");
			sb.append(KEY_USER_ID + ",");
			sb.append(KEY_RESTAURANT_ID + ",");
			sb.append(KEY_ORDER_COST + ")");
			sb.append(" VALUES (?, ?, ?);");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, String.valueOf(object.getUserId()));
			stmt.setString(2, String.valueOf(object.getRestaurantId()));
			stmt.setString(3, String.valueOf(object.getTotalCost()));

			stmt.executeUpdate();

			stmt.close();
			this.closeConnection(c);
			
			getRecentlyCreatedObject(object);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Records created successfully");
		
	}

	private void getRecentlyCreatedObject(Order object) {
		Map<String, String> criteria = new HashMap<String, String>();
		if (object.getRestaurantId() > 0) criteria.put(KEY_RESTAURANT_ID, String.valueOf(object.getRestaurantId()));
		if (object.getUserId() > 0) criteria.put(KEY_USER_ID, String.valueOf(object.getUserId()));
		if (object.getTotalCost() > 0) criteria.put(KEY_ORDER_COST, String.valueOf(object.getTotalCost()));

		Order order = select(criteria);
		if (order != null) {
			object.setId(order.getId());
		}
	}

	@Override
	public void update(Order object) {
		if (object.getId() <= 0)
			return;
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE ");
			sb.append(TABLE_ORDER + " SET ");
			sb.append(KEY_RESTAURANT_ID + " = ?, ");
			sb.append(KEY_USER_ID + " = ?, ");
			sb.append(KEY_ORDER_COST + " = ?");
			sb.append(" WHERE ");
			sb.append("id = ?;");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, String.valueOf(object.getRestaurantId()));
			stmt.setString(2, String.valueOf(object.getUserId()));
			stmt.setString(3, String.valueOf(object.getTotalCost()));
			stmt.setString(4, String.valueOf(object.getId()));

			stmt.executeUpdate();

			stmt.close();
			this.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Operation done successfully");
	}

	@Override
	public void delete(Order object) {
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM ");
			sb.append(TABLE_ORDER);
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
	public List<Order> selectAll(Map<String, String> criteria) {
		List<Order> res = new ArrayList<Order>();
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ");
			sb.append(TABLE_ORDER);

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
				int userId = rs.getInt(KEY_USER_ID);
				int restaurantId = rs.getInt(KEY_RESTAURANT_ID);
				double totalCost = rs.getDouble(KEY_ORDER_COST);
				Order o = new Order(userId, restaurantId, totalCost);
				o.setId(id);
				res.add(o);
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
		create(CREATE_ORDER_TABLE);
	}

	@Override
	public Order select(Map<String, String> criteria) {
		List<Order> orders = this.selectAll(criteria);
		if (orders.size() > 0) return orders.get(0);
		return null;
	}
	
	public List<Order> getOrdersByUser(int userId) {
		Map<String, String> criteria = new HashMap<>();
		criteria.put(KEY_USER_ID, String.valueOf(userId));
		return selectAll(criteria);
	}
}
