package unitTests;

import gameModel.User;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 * @author Chaitanya Varma
 * @version May 2017
 */
public class UsersTest {

    /**
     * Test of isUserExists method, of class User.
     */
    @Test
    public void testIsUserExists() {
        boolean isExists = User.isUserExists("testuser");
        assertTrue(isExists);
    }

    /**
     * Test of loginUser method, of class User.
     */
    @Test
    public void testLoginUser() {
        User user = User.loginUser("testuser", "test123");
        assertTrue(user != null);
    }
}
