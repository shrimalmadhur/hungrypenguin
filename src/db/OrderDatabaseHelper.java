/**
 * @author cvandera
 *
 */

package db;

import java.util.List;
import java.util.Map;

import model.Order;

public class OrderDatabaseHelper extends DatabaseHelper<Order> {

	// Table Names
	private static final String TABLE_ORDER = "food_order";
	private static final String TABLE_USER = "user";
	private static final String TABLE_RESTAURANT = "restaurant";

	// ORDER Table - column names
	private static final String KEY_USER_ID = "user_id";
	private static final String KEY_RESTAURANT_ID = "restaurant_id";
	private static final String KEY_ORDER_COST = "total_cost";

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Order object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Order object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> selectAll(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTable() {
		create(CREATE_ORDER_TABLE);
	}

	@Override
	public Order select(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
