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

import model.User;

public class UserDatabaseHelper extends DatabaseHelper<User> {

	// Table Name
	private static final String TABLE_USER = "user";

	// USER Table - column names
	public static final String KEY_USER_USERNAME = "username";
	public static final String KEY_USER_FIRST_NAME = "firstname";
	public static final String KEY_USER_LAST_NAME = "lastname";
	public static final String KEY_USER_PASSWORD = "password";
	public static final String KEY_USER_FACEBOOK_ID = "facebook_id";
	public static final String KEY_USER_EMAIL = "email";

	private final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_USER_USERNAME + " TEXT," + KEY_USER_FIRST_NAME + " TEXT," + KEY_USER_LAST_NAME + " TEXT," + KEY_USER_PASSWORD + " TEXT," + KEY_USER_FACEBOOK_ID + " INTEGER,"
			+ KEY_USER_EMAIL + " TEXT" + ");";

	public UserDatabaseHelper() {
		this.createTable();
	}

	@Override
	public void insert(User object) {
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ");
			sb.append(TABLE_USER + " (");
			sb.append(KEY_USER_USERNAME + ",");
			sb.append(KEY_USER_FIRST_NAME + ",");
			sb.append(KEY_USER_LAST_NAME + ",");
			sb.append(KEY_USER_PASSWORD + ",");
			sb.append(KEY_USER_FACEBOOK_ID + ",");
			sb.append(KEY_USER_EMAIL + ") ");
			sb.append("VALUES (?, ?, ?, ?, ?, ?);");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, object.getUsername());
			stmt.setString(2, object.getFirstName());
			stmt.setString(3, object.getLastName());
			stmt.setString(4, object.getPassword());
			stmt.setString(5, object.getFacebookId());
			stmt.setString(6, object.getEmail());

			stmt.executeUpdate();

			stmt.close();
			this.closeConnection(c);

			getRecentlyCreatedObject(object);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	private void getRecentlyCreatedObject(User object) {
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put(KEY_USER_USERNAME, object.getUsername());
		criteria.put(KEY_USER_FIRST_NAME, object.getFirstName());
		criteria.put(KEY_USER_LAST_NAME, object.getLastName());
		criteria.put(KEY_USER_PASSWORD, object.getPassword());
		criteria.put(KEY_USER_FACEBOOK_ID, object.getFacebookId());
		criteria.put(KEY_USER_EMAIL, object.getEmail());

		List<User> users = selectAll(criteria);
		if (users.size() > 0) {
			User u = users.get(0);
			object.setId(u.getId());
		}
	}

	@Override
	public void update(User object) {
		if (object.getId() <= 0)
			return;
		try {
			Connection c = this.openConncetion();
			PreparedStatement stmt = null;

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE ");
			sb.append(TABLE_USER + " SET ");
			sb.append(KEY_USER_USERNAME + " = ?, ");
			sb.append(KEY_USER_FIRST_NAME + " = ?, ");
			sb.append(KEY_USER_LAST_NAME + " = ?, ");
			sb.append(KEY_USER_PASSWORD + " = ?, ");
			sb.append(KEY_USER_FACEBOOK_ID + " = ?, ");
			sb.append(KEY_USER_EMAIL + " = ? ");
			sb.append(" WHERE ");
			sb.append("id = ?;");

			stmt = c.prepareStatement(sb.toString());
			stmt.setString(1, object.getUsername());
			stmt.setString(2, object.getFirstName());
			stmt.setString(3, object.getLastName());
			stmt.setString(4, object.getPassword());
			stmt.setString(5, object.getFacebookId());
			stmt.setString(6, object.getEmail());
			stmt.setString(7, String.valueOf(object.getId()));

			stmt.executeUpdate();

			stmt.close();
			this.closeConnection(c);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");

	}

	@Override
	public void delete(User object) {
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM ");
			sb.append(TABLE_USER);
			sb.append(" WHERE ");
			sb.append(KEY_ID + " = ");
			sb.append(object.getId());
			sb.append(";");

			Statement stmt = c.createStatement();
			stmt.executeUpdate(sb.toString());

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	@Override
	public List<User> selectAll(Map<String, String> criteria) {
		List<User> res = new ArrayList<User>();
		try {
			Connection c = this.openConncetion();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ");
			sb.append(TABLE_USER);

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
				String username = rs.getString(KEY_USER_USERNAME);
				String firstName = rs.getString(KEY_USER_FIRST_NAME);
				String lastName = rs.getString(KEY_USER_LAST_NAME);
				String password = rs.getString(KEY_USER_PASSWORD);
				String facebookId = rs.getString(KEY_USER_FACEBOOK_ID);
				String email = rs.getString(KEY_USER_EMAIL);
				User u = new User(username, firstName, lastName, password, email, facebookId);
				u.setId(id);
				res.add(u);
			}
			rs.close();
			stmt.close();

			this.closeConnection(c);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");

		return res;
	}
	
	@Override
	public User select(Map<String, String> criteria) {
		User res = null;
		try {
			Connection c = this.openConncetion();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ");
			sb.append(TABLE_USER);
			
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
				String username = rs.getString(KEY_USER_USERNAME);
				String firstName = rs.getString(KEY_USER_FIRST_NAME);
				String lastName = rs.getString(KEY_USER_LAST_NAME);
				String password = rs.getString(KEY_USER_PASSWORD);
				String facebookId = rs.getString(KEY_USER_FACEBOOK_ID);
				String email = rs.getString(KEY_USER_EMAIL);
				User u = new User(username, firstName, lastName, password, email, facebookId);
				u.setId(id);
				res = u;
			}
			rs.close();
			stmt.close();
			
			this.closeConnection(c);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		
		return res;
	}

	@Override
	public void createTable() {
		create(CREATE_USER_TABLE);
	}

}
