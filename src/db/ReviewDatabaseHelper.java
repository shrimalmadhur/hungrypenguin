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

import model.Review;

public class ReviewDatabaseHelper extends DatabaseHelper<Review> {

	// Table Names
	private static final String TABLE_REVIEW = "review";
	private static final String TABLE_USER = "user";
	private static final String TABLE_DISH = "dish";

	// REVIEW Table - column names
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_DISH_ID = "dish_id";
	public static final String KEY_REVIEW_REVIEW = "review";

	private final String CREATE_REVIEW_TABLE = "CREATE TABLE IF NOT EXISTS " + 
			TABLE_REVIEW + "(" + 
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
			KEY_REVIEW_REVIEW + " TEXT," + 
			KEY_USER_ID + " INTEGER," + 
			KEY_DISH_ID + " INTEGER," +
			"FOREIGN KEY(" + KEY_USER_ID + ") REFERENCES " + TABLE_USER + "(" + KEY_ID + ")," + 
			"FOREIGN KEY(" + KEY_DISH_ID + ") REFERENCES " + TABLE_DISH + "(" + KEY_ID + ")" + ");";
	
	public ReviewDatabaseHelper() {
		this.createTable();
	}
	
	@Override
	public void insert(Review object) {
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ");
			sb.append(TABLE_REVIEW + " (");
			sb.append(KEY_USER_ID + ",");
			sb.append(KEY_DISH_ID + ",");
			sb.append(KEY_REVIEW_REVIEW + ")");
			sb.append(" VALUES (?, ?, ?);");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, String.valueOf(object.getUserId()));
			stmt.setString(2, String.valueOf(object.getDishId()));
			stmt.setString(3, String.valueOf(object.getReview()));

			stmt.executeUpdate();

			stmt.close();
			this.closeConnection(c);
			
			getRecentlyCreatedObject(object);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Records created successfully");
		
	}
	
	private void getRecentlyCreatedObject(Review object) {
		Map<String, String> criteria = new HashMap<String, String>();
		if (object.getUserId() > 0) criteria.put(KEY_USER_ID, String.valueOf(object.getUserId()));
		if (object.getUserId() > 0) criteria.put(KEY_USER_ID, String.valueOf(object.getUserId()));
		if (object.getReview() != null) criteria.put(KEY_REVIEW_REVIEW, String.valueOf(object.getReview()));

		Review review = select(criteria);
		if (review != null) {
			object.setId(review.getId());
		}
	}

	@Override
	public void update(Review object) {
		if (object.getId() <= 0)
			return;
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE ");
			sb.append(TABLE_REVIEW + " SET ");
			sb.append(KEY_USER_ID + " = ?, ");
			sb.append(KEY_DISH_ID + " = ?, ");
			sb.append(KEY_REVIEW_REVIEW + " = ?");
			sb.append(" WHERE ");
			sb.append("id = ?;");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, String.valueOf(object.getUserId()));
			stmt.setString(2, String.valueOf(object.getDishId()));
			stmt.setString(3, String.valueOf(object.getReview()));

			stmt.executeUpdate();

			stmt.close();
			this.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Operation done successfully");
	}

	@Override
	public void delete(Review object) {
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM ");
			sb.append(TABLE_REVIEW);
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
	public List<Review> selectAll(Map<String, String> criteria) {
		List<Review> res = new ArrayList<Review>();
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ");
			sb.append(TABLE_REVIEW);

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
				int dishId = rs.getInt(KEY_DISH_ID);
				String review = rs.getString(KEY_REVIEW_REVIEW);
				Review r = new Review(review, userId, dishId);
				r.setId(id);
				res.add(r);
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
		create(CREATE_REVIEW_TABLE);
	}

	@Override
	public Review select(Map<String, String> criteria) {
		List<Review> reviews = this.selectAll(criteria);
		if (reviews.size() > 0) return reviews.get(0);
		return null;
	}
	
}
