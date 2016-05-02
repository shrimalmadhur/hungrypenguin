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

import model.OrderDish;

public class OrderDishDatabaseHelper extends DatabaseHelper<OrderDish> {

	// Table Names
	private static final String TABLE_ORDER_DISH = "order_dish";
	private static final String TABLE_ORDER = "order";
	private static final String TABLE_DISH = "dish";

	// ORDER_DISH Table - column names
	public static final String KEY_ORDER_ID = "order_id";
	public static final String KEY_DISH_ID = "dish_id";

	private final String CREATE_ORDER_DISH_TABLE = "CREATE TABLE IF NOT EXISTS " + 
			TABLE_ORDER_DISH + "(" + 
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
			KEY_ORDER_ID + " INTEGER," + 
			KEY_DISH_ID + " INTEGER," +
			"FOREIGN KEY(" + KEY_ORDER_ID + ") REFERENCES " + TABLE_ORDER + "(" + KEY_ID + ")," + 
			"FOREIGN KEY(" + KEY_DISH_ID + ") REFERENCES " + TABLE_DISH + "(" + KEY_ID + ")" + ");";
	
	public OrderDishDatabaseHelper() {
		this.createTable();
	}
	
	@Override
	public void insert(OrderDish object) {
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ");
			sb.append(TABLE_ORDER_DISH + " (");
			sb.append(KEY_ORDER_ID + ",");
			sb.append(KEY_DISH_ID + ")");
			sb.append(" VALUES (?, ?);");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, String.valueOf(object.getOrderId()));
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

	private void getRecentlyCreatedObject(OrderDish object) {
		Map<String, String> criteria = new HashMap<String, String>();
		if (object.getDishId() > 0) criteria.put(KEY_DISH_ID, String.valueOf(object.getDishId()));
		if (object.getOrderId() > 0) criteria.put(KEY_ORDER_ID, String.valueOf(object.getOrderId()));

		OrderDish orderDish = select(criteria);
		if (orderDish != null) {
			object.setId(orderDish.getId());
		}
	}
	
	@Override
	public void update(OrderDish object) {
		if (object.getId() <= 0)
			return;
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE ");
			sb.append(TABLE_ORDER_DISH + " SET ");
			sb.append(KEY_ORDER_ID + " = ?, ");
			sb.append(KEY_DISH_ID + " = ?");
			sb.append(" WHERE ");
			sb.append("id = ?;");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, String.valueOf(object.getOrderId()));
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
	public void delete(OrderDish object) {
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM ");
			sb.append(TABLE_ORDER_DISH);
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
	public List<OrderDish> selectAll(Map<String, String> criteria) {
		List<OrderDish> res = new ArrayList<OrderDish>();
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ");
			sb.append(TABLE_ORDER_DISH);

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
				int orderId = rs.getInt(KEY_ORDER_ID);
				int dishId = rs.getInt(KEY_DISH_ID);
				OrderDish o = new OrderDish(orderId, dishId);
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
		create(CREATE_ORDER_DISH_TABLE);
	}

	@Override
	public OrderDish select(Map<String, String> criteria) {
		List<OrderDish> orderDishes = this.selectAll(criteria);
		if (orderDishes.size() > 0) return orderDishes.get(0);
		return null;
	}
	
	public List<OrderDish> getByOrder(int orderId) {
		Map<String, String> criteria = new HashMap<>();
		criteria.put(KEY_ORDER_ID, String.valueOf(orderId));
		return this.selectAll(criteria);
	}
	
}
