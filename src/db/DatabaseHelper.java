package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public abstract class DatabaseHelper<T> {

	private final String SQLITE = "org.sqlite.JDBC";

	private final String DB_NAME = "test.db";
	private final String DRIVER = "jdbc:sqlite:" + DB_NAME;
	
	// Common column names
	protected static final String KEY_ID = "id";
	
	protected Connection openConncetion() throws SQLException, ClassNotFoundException {
		Connection c = null;
		Class.forName(SQLITE);
		c = DriverManager.getConnection(DRIVER);
		return c;
	}
	
	protected void closeConnection(Connection c) throws SQLException {
		c.close();
	}
	
	protected void create(String sql) {
		try {
			Connection c = openConncetion();
			
			Statement stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			
			closeConnection(c);
			System.out.println("Table created successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public abstract void createTable();

	public abstract void insert(T object);
	public abstract void update(T object);
	public abstract void delete(T object);
	
	public abstract T select(Map<String, String> criteria);
	public abstract List<T> selectAll(Map<String, String> criteria);
}
