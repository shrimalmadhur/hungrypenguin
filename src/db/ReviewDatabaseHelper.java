/**
 * @author cvandera
 *
 */

package db;

import java.util.List;
import java.util.Map;

import model.Review;

public class ReviewDatabaseHelper extends DatabaseHelper<Review> {

	// Table Names
	private static final String TABLE_REVIEW = "review";
	private static final String TABLE_USER = "user";
	private static final String TABLE_DISH = "dish";

	// REVIEW Table - column names
	private static final String KEY_USER_ID = "user_id";
	private static final String KEY_DISH_ID = "dish_id";
	private static final String KEY_REVIEW_REVIEW = "review";

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Review object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Review object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Review> selectAll(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTable() {
		create(CREATE_REVIEW_TABLE);
	}

	@Override
	public Review select(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
