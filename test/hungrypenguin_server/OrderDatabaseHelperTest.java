/**
 * @author cvandera
 *
 */

package hungrypenguin_server;

import org.junit.Before;
import org.junit.Test;

import db.OrderDatabaseHelper;
import db.RestaurantDatabaseHelper;
import db.UserDatabaseHelper;
import model.Order;
import model.Restaurant;
import model.User;

public class OrderDatabaseHelperTest {

	OrderDatabaseHelper db;
	UserDatabaseHelper userdb;
	RestaurantDatabaseHelper restaurantdb;

	@Before
	public void setUp() throws Exception {
		db = new OrderDatabaseHelper();
		userdb = new UserDatabaseHelper();
		restaurantdb = new RestaurantDatabaseHelper();
	}

	@Test
	public void testCreateOrder() {
		User u = new User("username", "test", "test", "pass", "test@test", "2");
		userdb.insert(u);

		Restaurant restaurant = new Restaurant("rest1", "lalala", "image", "$");
		restaurantdb.insert(restaurant);

		Order order = new Order(u.getId(), restaurant.getId(), 100.70);
		db.insert(order);
		System.out.println(order);

		db.delete(order);
		userdb.delete(u);
		restaurantdb.delete(restaurant);
	}

}
