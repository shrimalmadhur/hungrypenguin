/**
 * @author cvandera
 *
 */

package db;

import java.util.List;
import java.util.Map;

import model.Restaurant;

public class RestaurantDatabaseHelper extends DatabaseHelper<Restaurant> {

	// Table Name
	private static final String TABLE_RESTAURANT = "restaurant";

	// RESTAURANT Table - column names
	private static final String KEY_RESTAURANT_NAME = "name";
	private static final String KEY_RESTAURANT_ADDRESS = "address";
	private static final String KEY_RESTAURANT_IMAGE = "image";

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Restaurant object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Restaurant object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Restaurant> selectAll(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTable() {
		create(CREATE_RESTAURANT_TABLE);
	}

	@Override
	public Restaurant select(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
