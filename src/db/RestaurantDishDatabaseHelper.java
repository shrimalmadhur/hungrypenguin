/**
 * @author cvandera
 *
 */

package db;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RestaurantDish object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(RestaurantDish object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RestaurantDish> selectAll(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTable() {
		create(CREATE_RESTAURANT_DISH_TABLE);
	}

	@Override
	public RestaurantDish select(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
