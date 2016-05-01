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
	
	@Test
	public void testNullFieldUser() {
		User u = new User("test6543@test", "user475", "user2", "pass432", "test6543@test", null);
		db.insert(u);
		
		Map<String, String> criteria = new HashMap<String, String>();
		if (u.getUsername() != null) criteria.put(UserDatabaseHelper.KEY_USER_USERNAME, u.getUsername());
		if (u.getFirstName() != null) criteria.put(UserDatabaseHelper.KEY_USER_FIRST_NAME, u.getFirstName());
		if (u.getLastName() != null) criteria.put(UserDatabaseHelper.KEY_USER_LAST_NAME, u.getLastName());
		if (u.getPassword() != null) criteria.put(UserDatabaseHelper.KEY_USER_PASSWORD, u.getPassword());
		if (u.getFacebookId() != null) criteria.put(UserDatabaseHelper.KEY_USER_FACEBOOK_ID, u.getFacebookId());
		if (u.getEmail() != null) criteria.put(UserDatabaseHelper.KEY_USER_EMAIL, u.getEmail());
		User user = db.select(criteria);
		
		assertEquals(u, user);
		db.delete(u);
	}

}
