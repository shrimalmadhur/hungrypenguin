/**
 * @author cvandera
 *
 */

package db;

import java.util.List;
import java.util.Map;

import model.OrderDish;

public class OrderDishDatabaseHelper extends DatabaseHelper<OrderDish> {

	// Table Names
	private static final String TABLE_ORDER_DISH = "order_dish";
	private static final String TABLE_ORDER = "order";
	private static final String TABLE_DISH = "dish";

	// ORDER_DISH Table - column names
	private static final String KEY_ORDER_ID = "order_id";
	private static final String KEY_DISH_ID = "dish_id";

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(OrderDish object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderDish object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderDish> selectAll(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTable() {
		create(CREATE_ORDER_DISH_TABLE);
	}

	@Override
	public OrderDish select(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
