/**
 * @author cvandera
 *
 */

package hungrypenguin_server;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import db.RestaurantDatabaseHelper;
import model.Restaurant;

public class RestaurantDishDatabaseHelperTest {

	RestaurantDatabaseHelper db;

	@Before
	public void setUp() throws Exception {
		db = new RestaurantDatabaseHelper();
	}

	@Test
	public void test() {

		Restaurant restaurant = new Restaurant("rest1", "lalala", "image", "$");
		db.insert(restaurant);
		
		Map<String, String> criteria = new HashMap<>();
		criteria.put(RestaurantDatabaseHelper.KEY_RESTAURANT_NAME, "rest1");
		Restaurant r = db.select(criteria);
		assertEquals(restaurant, r);

		criteria.clear();
		criteria.put(RestaurantDatabaseHelper.KEY_RESTAURANT_NAME, "rest2");
		Restaurant r2 = db.select(criteria);
		assertEquals(null, r2);
		
		db.delete(restaurant);
	}

}
