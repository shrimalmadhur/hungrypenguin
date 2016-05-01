/**
 * @author cvandera
 *
 */

package hungrypenguin_server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import db.UserDatabaseHelper;
import model.User;

public class SQLiteJDBCTest {

	UserDatabaseHelper db;
	
	@Before
	public void setUp() throws Exception {
		db = new UserDatabaseHelper();
	}

	@Test
	public void testCreateUser() {
		User u = new User("username", "test", "test", "pass", "test@test", "2");
		db.insert(u);
		User u2 = new User("username2", "test2", "test2", "pass2", "test2@test", "3");
		db.insert(u2);
		System.out.println(u);
		
		u.setPassword("lalala");
		db.update(u);
		System.out.println(u);
		
		Map<String, String> criteria = new HashMap<>();
		criteria.put(UserDatabaseHelper.KEY_USER_FIRST_NAME, "test");
		List<User> users = db.selectAll(criteria);
		for (User user : users) {
			assertEquals(u, user);
		}
		
		
		User user = db.select(criteria);
		assertEquals(u, user);
		
		db.delete(user);
		db.delete(u2);
	}
	
	@Test
	public void testDeleteUser() {
		User u = new User("username", "user", "user2", "pass", "test@test", "2");
		db.insert(u);
		
		db.delete(u);
		
		Map<String, String> criteria = new HashMap<>();
		criteria.put(UserDatabaseHelper.KEY_USER_FIRST_NAME, "user");
		User user = db.select(criteria);
		assertEquals(null, user);
	}

}
