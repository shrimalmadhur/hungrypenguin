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

import model.Restaurant;

public class RestaurantDatabaseHelper extends DatabaseHelper<Restaurant> {

	// Table Name
	private static final String TABLE_RESTAURANT = "restaurant";

	// RESTAURANT Table - column names
	public static final String KEY_RESTAURANT_NAME = "name";
	public static final String KEY_RESTAURANT_ADDRESS = "address";
	public static final String KEY_RESTAURANT_IMAGE = "image";

	private final String CREATE_RESTAURANT_TABLE = "CREATE TABLE IF NOT EXISTS " + 
			TABLE_RESTAURANT + "(" + 
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
			KEY_RESTAURANT_NAME + " TEXT," + 
			KEY_RESTAURANT_ADDRESS + " TEXT," + 
			KEY_RESTAURANT_IMAGE + " TEXT" + ");";
	
	public RestaurantDatabaseHelper() {
		this.createTable();
	}
	
	@Override
	public void insert(Restaurant object) {
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ");
			sb.append(TABLE_RESTAURANT + " (");
			sb.append(KEY_RESTAURANT_NAME + ",");
			sb.append(KEY_RESTAURANT_ADDRESS + ",");
			sb.append(KEY_RESTAURANT_IMAGE + ")");
			sb.append(" VALUES (?, ?, ?);");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, object.getName());
			stmt.setString(2, object.getAddress());
			stmt.setString(3, object.getImage());
			stmt.executeUpdate();

			getRecentlyCreatedObject(object);

			stmt.close();
			this.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Records created successfully");
	}
	
	private void getRecentlyCreatedObject(Restaurant object) {
		Map<String, String> criteria = new HashMap<String, String>();
		if (object.getName() != null) criteria.put(KEY_RESTAURANT_NAME, String.valueOf(object.getName()));
		if (object.getAddress() != null) criteria.put(KEY_RESTAURANT_ADDRESS, String.valueOf(object.getAddress()));
		if (object.getImage() != null) criteria.put(KEY_RESTAURANT_IMAGE, String.valueOf(object.getImage()));

		Restaurant restaurant = select(criteria);
		if (restaurant != null) {
			object.setId(restaurant.getId());
		}
	}

	@Override
	public void update(Restaurant object) {
		if (object.getId() <= 0)
			return;
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE ");
			sb.append(TABLE_RESTAURANT + " SET ");
			sb.append(KEY_RESTAURANT_NAME + " = ?, ");
			sb.append(KEY_RESTAURANT_ADDRESS + " = ?, ");
			sb.append(KEY_RESTAURANT_IMAGE + " = ?");
			sb.append(" WHERE ");
			sb.append("id = ?;");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, object.getName());
			stmt.setString(2, object.getAddress());
			stmt.setString(3, object.getImage());
			stmt.setString(7, String.valueOf(object.getId()));

			stmt.executeUpdate();

			stmt.close();
			this.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Operation done successfully");
	}

	@Override
	public void delete(Restaurant object) {
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM ");
			sb.append(TABLE_RESTAURANT);
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
	public List<Restaurant> selectAll(Map<String, String> criteria) {
		List<Restaurant> res = new ArrayList<Restaurant>();
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ");
			sb.append(TABLE_RESTAURANT);

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
				sb.append(" ORDER BY " + KEY_ID + " DESC");
			} else {
				sb.append(" ORDER BY " + KEY_RESTAURANT_NAME);
			}
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
				String name = rs.getString(KEY_RESTAURANT_NAME);
				String address = rs.getString(KEY_RESTAURANT_ADDRESS);
				String image = rs.getString(KEY_RESTAURANT_IMAGE);
				Restaurant restaurant = new Restaurant(name, address, image);
				restaurant.setId(id);
				res.add(restaurant);
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
		create(CREATE_RESTAURANT_TABLE);
	}

	@Override
	public Restaurant select(Map<String, String> criteria) {
		List<Restaurant> restaurants = this.selectAll(criteria);
		if (restaurants.size() > 0) return restaurants.get(0);
		return null;
	}
	
}
