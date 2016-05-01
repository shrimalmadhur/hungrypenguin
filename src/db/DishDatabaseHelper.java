/**
 * @author cvandera
 *
 */

package db;

import java.util.List;
import java.util.Map;

import model.Dish;

public class DishDatabaseHelper extends DatabaseHelper<Dish> {

	// Table Names
	private static final String TABLE_DISH = "dish";

	// DISH Table - column names
	private static final String KEY_DISH_NAME = "name";
	private static final String KEY_DISH_PRICE = "price";
	private static final String KEY_DISH_IMAGE = "image";
	private static final String KEY_DISH_RATING = "rating";

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Dish object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Dish object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Dish> selectAll(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTable() {
		create(CREATE_DISH_TABLE);
	}

	@Override
	public Dish select(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
