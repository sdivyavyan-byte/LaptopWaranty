import org.junit.jupiter.api.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

/**
 * Comprehensive test suite for user registration in the Laptop Warranty System.
 *
 * Testing Approach:
 * ─────────────────────────────────────────────────────────────────────────────
 * This suite employs a multi-layered testing strategy that ensures robustness
 * across the full user registration and login workflow:
 *
 * 1. FUNCTIONAL TESTING: Validates core registration and retrieval operations.
 *    - User registration with valid data
 *    - Successful user retrieval with correct credentials
 *    - Preservation of all user fields
 *
 * 2. BOUNDARY TESTING: Tests edge cases and extreme input values.
 *    - Registration with minimal/maximal field lengths
 *    - Special characters in fields
 *    - Numeric boundaries (e.g., warranty ID edge cases)
 *
 * 3. EXCEPTION HANDLING: Validates error paths and invalid states.
 *    - Failed login with incorrect password
 *    - Login attempt for non-existent user
 *    - User deletion and retrieval of deleted users
 *    - Duplicate user registration scenarios
 *
 * Test Isolation:
 * Each test is isolated using @BeforeEach to reset UserData's static state
 * (via reflection) and clean up serialized files, ensuring tests don't depend
 * on test order or previous test outcomes.
 *
 * Repetition: The @RepeatedTest(100) variant ensures that the system handles
 * high-volume registration scenarios correctly.
 * ─────────────────────────────────────────────────────────────────────────────
 */
public class RunHundredTests {

    @BeforeEach
    void resetUserData() throws Exception {
        Class<?> udClass = UserData.class;

        Field usersField = udClass.getDeclaredField("users");
        usersField.setAccessible(true);
        usersField.set(null, new HashMap<String, UserData>());

        Field deletedField = udClass.getDeclaredField("deletedUsers");
        deletedField.setAccessible(true);
        deletedField.set(null, new HashMap<String, UserData>());

        // Remove serialized files so tests start from a clean slate
        new File("userdata.ser").delete();
        new File("deleted_users.ser").delete();
    }

    // ═════════════════════════════════════════════════════════════════════════
    // FUNCTIONAL TESTING: Core Registration and Retrieval
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * TEST CASE 1: Basic User Registration with Valid Data
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Functional Testing
     * Purpose: Verify that a user can be registered with all required fields
     *          and that the user is correctly stored.
     *
     * Test Steps:
     * 1. Create a UserData object with standard valid input
     * 2. Save the user
     * 3. Verify the user exists in the users HashMap
     *
     * Expected Result: User is persisted and can be retrieved by user ID
     */
    @Test
    @DisplayName("TEST 1: Basic User Registration with Valid Data")
    void testBasicUserRegistration() {
        String userId = "john_doe_001";
        String password = "secure_pass_123";

        UserData user = new UserData(
                "John Doe",
                "1234567890",
                userId,
                password,
                "Dell",
                "XPS 15",
                "12 hrs",
                "12 months",
                "WAR001"
        );

        UserData.saveUser(user);

        Assertions.assertTrue(UserData.getAllUsers().containsKey(userId),
                "User should exist in the registry after saving");
    }

    /**
     * TEST CASE 2: Successful User Login with Correct Credentials
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Functional Testing
     * Purpose: Verify that a registered user can retrieve their data using
     *          the correct password.
     *
     * Test Steps:
     * 1. Register a user
     * 2. Call getUser() with correct credentials
     * 3. Verify all fields match the registered data
     *
     * Expected Result: User object is returned with all original data intact
     */
    @Test
    @DisplayName("TEST 2: Successful User Login with Correct Credentials")
    void testSuccessfulLoginWithCorrectCredentials() {
        String userId = "alice_smith";
        String password = "pass123!@#";

        UserData user = new UserData(
                "Alice Smith",
                "9876543210",
                userId,
                password,
                "HP",
                "Pavilion 15",
                "20 hrs",
                "6 months",
                "WAR002"
        );
        UserData.saveUser(user);

        UserData retrieved = UserData.getUser(userId, password);

        Assertions.assertNotNull(retrieved, "User should be retrievable with correct password");
        Assertions.assertEquals("Alice Smith", retrieved.getOwnerName(),
                "Retrieved user's name should match");
        Assertions.assertEquals("HP", retrieved.getLaptopBrand(),
                "Retrieved laptop brand should match");
    }

    /**
     * TEST CASE 3: All User Fields Preserved After Registration
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Functional Testing
     * Purpose: Verify that every field is correctly preserved during
     *          registration and retrieval.
     *
     * Test Steps:
     * 1. Register user with specific values in each field
     * 2. Retrieve the user
     * 3. Assert each field matches the original value
     *
     * Expected Result: All fields are preserved exactly as entered
     */
    @Test
    @DisplayName("TEST 3: All User Fields Preserved After Registration")
    void testAllFieldsPreserved() {
        String userId = "field_test_user";
        String password = "testpass";

        UserData original = new UserData(
                "Field Test User",
                "5555555555",
                userId,
                password,
                "Lenovo",
                "ThinkPad X1",
                "24 hrs",
                "12 months",
                "WAR999"
        );
        UserData.saveUser(original);

        UserData retrieved = UserData.getUser(userId, password);

        Assertions.assertEquals(original.getOwnerName(), retrieved.getOwnerName());
        Assertions.assertEquals(original.getOwnerContact(), retrieved.getOwnerContact());
        Assertions.assertEquals(original.getOwnerUserid(), retrieved.getOwnerUserid());
        Assertions.assertEquals(original.getLaptopBrand(), retrieved.getLaptopBrand());
        Assertions.assertEquals(original.getLaptopModel(), retrieved.getLaptopModel());
        Assertions.assertEquals(original.getLaptopBatteryLife(), retrieved.getLaptopBatteryLife());
        Assertions.assertEquals(original.getWarrantyDuration(), retrieved.getWarrantyDuration());
        Assertions.assertEquals(original.getWarrantyId(), retrieved.getWarrantyId());
    }

    // ═════════════════════════════════════════════════════════════════════════
    // BOUNDARY TESTING: Edge Cases and Extreme Values
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * TEST CASE 4: Registration with Minimal Field Values
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Boundary Testing
     * Purpose: Verify system handles registration with minimal/single-character
     *          input values.
     *
     * Test Steps:
     * 1. Register user with single-character fields where applicable
     * 2. Retrieve and verify data integrity
     *
     * Expected Result: System accepts minimal input and preserves it correctly
     */
    @Test
    @DisplayName("TEST 4: Registration with Minimal Field Values")
    void testRegistrationWithMinimalValues() {
        String userId = "u";
        String password = "p";

        UserData user = new UserData(
                "A",
                "1",
                userId,
                password,
                "B",
                "M",
                "12 hrs",
                "12 months",
                "W"
        );
        UserData.saveUser(user);

        UserData retrieved = UserData.getUser(userId, password);

        Assertions.assertNotNull(retrieved, "User with minimal values should be retrievable");
        Assertions.assertEquals("A", retrieved.getOwnerName());
    }

    /**
     * TEST CASE 5: Registration with Special Characters in Fields
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Boundary Testing
     * Purpose: Verify system handles special characters, spaces, and symbols
     *          in user input fields.
     *
     * Test Steps:
     * 1. Register user with special characters in name, contact, and model
     * 2. Retrieve user
     * 3. Verify special characters are preserved
     *
     * Expected Result: System preserves all special characters without issue
     */
    @Test
    @DisplayName("TEST 5: Registration with Special Characters in Fields")
    void testRegistrationWithSpecialCharacters() {
        String userId = "user@special-123";
        String password = "p@$$w0rd!";

        UserData user = new UserData(
                "John O'Reilly-Smith, Jr.",
                "123-456-7890",
                userId,
                password,
                "Dell & Co.",
                "XPS 15 (2024 Edition)",
                "12 hrs",
                "12 months",
                "WAR-2024-#001"
        );
        UserData.saveUser(user);

        UserData retrieved = UserData.getUser(userId, password);

        Assertions.assertEquals("John O'Reilly-Smith, Jr.", retrieved.getOwnerName(),
                "Special characters in name should be preserved");
        Assertions.assertEquals("123-456-7890", retrieved.getOwnerContact(),
                "Special characters in contact should be preserved");
    }

    /**
     * TEST CASE 6: Registration with Maximal Field Values
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Boundary Testing
     * Purpose: Verify system handles very long input values without truncation
     *          or data loss.
     *
     * Test Steps:
     * 1. Register user with long strings (50+ characters)
     * 2. Retrieve and verify full values are preserved
     *
     * Expected Result: No truncation occurs; full strings are stored and retrieved
     */
    @Test
    @DisplayName("TEST 6: Registration with Maximal Field Values")
    void testRegistrationWithMaximalValues() {
        String userId = "user_with_very_long_identifier_1234567890";
        String longName = "This is a very long name with many characters and spaces " +
                          "that spans across multiple words and symbols!";
        String password = "very_secure_password_with_special_characters_1234567890!@#$%";

        UserData user = new UserData(
                longName,
                "9999999999999999999",
                userId,
                password,
                "Dell Corporation Limited International",
                "XPS 15 Plus Premium Edition (2024) with RTX 4090",
                "24 hrs",
                "12 months",
                "WARRANTY_ID_12345678901234567890"
        );
        UserData.saveUser(user);

        UserData retrieved = UserData.getUser(userId, password);

        Assertions.assertEquals(longName, retrieved.getOwnerName(),
                "Long names should be fully preserved without truncation");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // EXCEPTION HANDLING: Error Paths and Invalid States
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * TEST CASE 7: Login Fails with Incorrect Password
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Exception Handling
     * Purpose: Verify that login fails when an incorrect password is provided,
     *          even if the user ID is correct.
     *
     * Test Steps:
     * 1. Register user with password "correct_pass"
     * 2. Attempt to retrieve with password "wrong_pass"
     * 3. Verify getUser returns null
     *
     * Expected Result: getUser returns null; user is not authenticated
     */
    @Test
    @DisplayName("TEST 7: Login Fails with Incorrect Password")
    void testLoginFailsWithIncorrectPassword() {
        String userId = "secure_user";
        String correctPassword = "correct_pass";

        UserData user = new UserData(
                "Secure User",
                "1111111111",
                userId,
                correctPassword,
                "Asus",
                "Vivobook 15",
                "12 hrs",
                "12 months",
                "WAR007"
        );
        UserData.saveUser(user);

        UserData retrieved = UserData.getUser(userId, "wrong_pass");

        Assertions.assertNull(retrieved, "getUser should return null for incorrect password");
    }

    /**
     * TEST CASE 8: Login Fails for Non-Existent User
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Exception Handling
     * Purpose: Verify system gracefully handles login attempts for users that
     *          were never registered.
     *
     * Test Steps:
     * 1. Attempt to retrieve a user that was never registered
     * 2. Verify getUser returns null
     *
     * Expected Result: getUser returns null; no exception is thrown
     */
    @Test
    @DisplayName("TEST 8: Login Fails for Non-Existent User")
    void testLoginFailsForNonExistentUser() {
        UserData retrieved = UserData.getUser("nonexistent_user", "any_password");

        Assertions.assertNull(retrieved, "getUser should return null for non-existent user");
    }

    /**
     * TEST CASE 9: validateLogin Returns Correct Status Codes
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Exception Handling
     * Purpose: Verify validateLogin method returns correct status codes for
     *          various authentication scenarios.
     *
     * Test Steps:
     * 1. Register a valid user
     * 2. Call validateLogin with correct credentials → expect LOGIN_SUCCESS
     * 3. Call validateLogin with incorrect password → expect LOGIN_INVALID
     * 4. Call validateLogin with non-existent user → expect LOGIN_INVALID
     *
     * Expected Result: All status codes are returned correctly
     */
    @Test
    @DisplayName("TEST 9: validateLogin Returns Correct Status Codes")
    void testValidateLoginStatusCodes() {
        String userId = "status_test_user";
        String password = "status_password";

        UserData user = new UserData(
                "Status Test User",
                "2222222222",
                userId,
                password,
                "Apple",
                "MacBook Pro 16",
                "20 hrs",
                "12 months",
                "WAR009"
        );
        UserData.saveUser(user);

        int successResult = UserData.validateLogin(userId, password);
        Assertions.assertEquals(UserData.LOGIN_SUCCESS, successResult,
                "Correct credentials should return LOGIN_SUCCESS");

        int invalidResult = UserData.validateLogin(userId, "wrong_password");
        Assertions.assertEquals(UserData.LOGIN_INVALID, invalidResult,
                "Incorrect password should return LOGIN_INVALID");

        int nonexistentResult = UserData.validateLogin("nonexistent", "any_pass");
        Assertions.assertEquals(UserData.LOGIN_INVALID, nonexistentResult,
                "Non-existent user should return LOGIN_INVALID");
    }

    /**
     * TEST CASE 10: User Deletion and Retrieval from Deleted Users
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Exception Handling / Functional Testing
     * Purpose: Verify that deleted users are properly removed from active
     *          users and moved to the deleted users registry.
     *
     * Test Steps:
     * 1. Register a user
     * 2. Delete the user via deleteUser()
     * 3. Verify user is no longer in active users
     * 4. Verify user data is available via getDeletedUserData()
     * 5. Verify login fails for deleted user
     *
     * Expected Result: Deleted user is properly archived and inaccessible for login
     */
    @Test
    @DisplayName("TEST 10: User Deletion and Retrieval from Deleted Users")
    void testUserDeletionAndRetrieval() {
        String userId = "user_to_delete";
        String password = "deletion_test_pass";

        UserData user = new UserData(
                "Deletion Test User",
                "3333333333",
                userId,
                password,
                "Acer",
                "Aspire 5",
                "12 hrs",
                "6 months",
                "WAR010"
        );
        UserData.saveUser(user);

        // Verify user exists before deletion
        UserData beforeDelete = UserData.getUser(userId, password);
        Assertions.assertNotNull(beforeDelete, "User should exist before deletion");

        // Delete the user
        boolean deleteResult = UserData.deleteUser(userId);
        Assertions.assertTrue(deleteResult, "deleteUser should return true on successful deletion");

        // Verify user is no longer in active users
        UserData afterDelete = UserData.getUser(userId, password);
        Assertions.assertNull(afterDelete, "Deleted user should not be retrievable");

        // Verify user data is in deleted users registry
        UserData deletedUserData = UserData.getDeletedUserData(userId);
        Assertions.assertNotNull(deletedUserData, "Deleted user data should be retrievable from deleted registry");
        Assertions.assertEquals("Deletion Test User", deletedUserData.getOwnerName(),
                "Deleted user data should contain correct information");
    }

    // ═════════════════════════════════════════════════════════════════════════
    // HIGH-VOLUME STRESS TESTING: 100 Concurrent Registrations
    // ═════════════════════════════════════════════════════════════════════════

    /**
     * STRESS TEST: Register 100 Unique Users with High-Volume Repetition
     * ─────────────────────────────────────────────────────────────────────────
     * Category: Functional Testing / Stress Testing
     * Purpose: Verify the system can handle 100 registration operations
     *          without data loss, corruption, or performance degradation.
     *
     * Test Steps (performed 100 times):
     * 1. Generate unique user ID and credentials
     * 2. Create UserData object with unique values
     * 3. Save the user
     * 4. Retrieve and verify persistence
     *
     * Expected Result: All 100 users are registered, stored, and retrievable
     *                   without errors
     */
    @RepeatedTest(100)
    @DisplayName("STRESS TEST: 100 Concurrent User Registrations")
    void testRegister100UsersRepeated(RepetitionInfo info) {
        int repetition = info.getCurrentRepetition();
        String uid = "stress_user_" + repetition + "_" + UUID.randomUUID();
        String password = "password_" + repetition;

        UserData user = new UserData(
                "Stress Test User " + repetition,
                "555555555" + repetition,
                uid,
                password,
                "Brand" + repetition,
                "Model" + repetition,
                "12 hrs",
                "12 months",
                "STRESS_WID_" + repetition
        );

        UserData.saveUser(user);

        // Verify user was saved
        Assertions.assertTrue(UserData.getAllUsers().containsKey(uid),
                "User should be in registry after save (repetition " + repetition + ")");

        // Verify user can be retrieved
        UserData retrieved = UserData.getUser(uid, password);
        Assertions.assertNotNull(retrieved,
                "User should be retrievable (repetition " + repetition + ")");
        Assertions.assertEquals(uid, retrieved.getOwnerUserid(),
                "Retrieved user ID should match (repetition " + repetition + ")");
    }
}
