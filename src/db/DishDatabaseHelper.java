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

import model.Dish;
import model.OrderDish;
import model.RestaurantDish;

public class DishDatabaseHelper extends DatabaseHelper<Dish> {

	// Table Names
	private static final String TABLE_DISH = "dish";

	// DISH Table - column names
	public static final String KEY_DISH_NAME = "name";
	public static final String KEY_DISH_PRICE = "price";
	public static final String KEY_DISH_IMAGE = "image";
	public static final String KEY_DISH_RATING = "rating";

	private final String CREATE_DISH_TABLE = "CREATE TABLE IF NOT EXISTS " + 
			TABLE_DISH + "(" + 
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
			KEY_DISH_NAME + " TEXT," + 
			KEY_DISH_PRICE + " DOUBLE," + 
			KEY_DISH_IMAGE + " TEXT," + 
			KEY_DISH_RATING + " TEXT" + ");";
	
	public DishDatabaseHelper() {
		this.createTable();
	}
	
	@Override
	public void insert(Dish object) {
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ");
			sb.append(TABLE_DISH + " (");
			sb.append(KEY_DISH_NAME + ",");
			sb.append(KEY_DISH_PRICE + ",");
			sb.append(KEY_DISH_IMAGE + ",");
			sb.append(KEY_DISH_RATING + ")");
			sb.append(" VALUES (?, ?, ?, ?);");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, String.valueOf(object.getName()));
			stmt.setString(2, String.valueOf(object.getPrice()));
			stmt.setString(3, String.valueOf(object.getImage()));
			stmt.setString(4, String.valueOf(object.getRating()));

			stmt.executeUpdate();

			stmt.close();
			this.closeConnection(c);
			
			getRecentlyCreatedObject(object);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Records created successfully");
	}

	private void getRecentlyCreatedObject(Dish object) {
		Map<String, String> criteria = new HashMap<String, String>();
		if (object.getName() != null) criteria.put(KEY_DISH_NAME, object.getName());
		if (object.getPrice() > 0) criteria.put(KEY_DISH_PRICE, String.valueOf(object.getPrice()));
		if (object.getRating() > 0) criteria.put(KEY_DISH_RATING, String.valueOf(object.getRating()));
		if (object.getImage() != null) criteria.put(KEY_DISH_IMAGE, object.getImage());

		Dish d = select(criteria);
		if (d != null) {
			object.setId(d.getId());
		}
	}
	
	@Override
	public void update(Dish object) {
		if (object.getId() <= 0)
			return;
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE ");
			sb.append(TABLE_DISH + " SET ");
			sb.append(KEY_DISH_NAME + " = ?, ");
			sb.append(KEY_DISH_PRICE + " = ?, ");
			sb.append(KEY_DISH_IMAGE + " = ?, ");
			sb.append(KEY_DISH_RATING + " = ?");
			sb.append(" WHERE ");
			sb.append("id = ?;");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, String.valueOf(object.getName()));
			stmt.setString(2, String.valueOf(object.getPrice()));
			stmt.setString(3, String.valueOf(object.getImage()));
			stmt.setString(3, String.valueOf(object.getRating()));
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
	public void delete(Dish object) {
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM ");
			sb.append(TABLE_DISH);
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
	public List<Dish> selectAll(Map<String, String> criteria) {
		List<Dish> res = new ArrayList<Dish>();
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ");
			sb.append(TABLE_DISH);

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
				String name = rs.getString(KEY_DISH_NAME);
				double price = rs.getDouble(KEY_DISH_PRICE);
				double rating = rs.getDouble(KEY_DISH_RATING);
				String image = rs.getString(KEY_DISH_IMAGE);
				Dish d = new Dish(name, price, image, rating);
				d.setRating(rating);
				d.setId(id);
				res.add(d);
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
		create(CREATE_DISH_TABLE);
	}

	@Override
	public Dish select(Map<String, String> criteria) {
		List<Dish> dishes = this.selectAll(criteria);
		if (dishes.size() > 0) return dishes.get(0);
		return null;
	}
	
	public List<Dish> getDishesByOrder(int orderId) {
		List<Dish> res = new ArrayList<>();
		
		OrderDishDatabaseHelper db = new OrderDishDatabaseHelper();
		List<OrderDish> od = db.getByOrder(orderId);
		
		for (OrderDish orderDish : od) {
			Dish d = this.getById(orderDish.getDishId());
			if (d!= null) res.add(d);
		}
		
		return res;
	}
	
	public List<Dish> getDishesByRestaurant(int restaurantId) {
		List<Dish> res = new ArrayList<>();
		
		RestaurantDishDatabaseHelper db = new RestaurantDishDatabaseHelper();
		List<RestaurantDish> od = db.getByRestaurant(restaurantId);
		
		for (RestaurantDish restaurantDish : od) {
			Dish d = this.getById(restaurantDish.getDishId());
			if (d!= null) res.add(d);
		}
		
		return res;
	}
	
}
