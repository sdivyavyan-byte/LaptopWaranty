import org.junit.jupiter.api.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 100 Individual Test Cases for User Registration
 * 
 * Each test is completely independent and can be run individually.
 * Each test registers a unique user and verifies the registration succeeds.
 */
public class HundredIndividualTests {

    @BeforeEach
    void resetUserData() throws Exception {
        Class<?> udClass = UserData.class;

        Field usersField = udClass.getDeclaredField("users");
        usersField.setAccessible(true);
        usersField.set(null, new HashMap<String, UserData>());

        Field deletedField = udClass.getDeclaredField("deletedUsers");
        deletedField.setAccessible(true);
        deletedField.set(null, new HashMap<String, UserData>());

        new File("userdata.ser").delete();
        new File("deleted_users.ser").delete();
    }

    // ========== 100 INDIVIDUAL TESTS ==========

    @Test
    @DisplayName("Test 001: User Registration Test")
    void test_001_user_registration() {
        UserData user = new UserData("User 001", "5001", "user_001", "pass_001", "Dell", "XPS 13", "12 hrs", "12 months", "WAR_001");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_001", "pass_001"));
    }

    @Test
    @DisplayName("Test 002: User Registration Test")
    void test_002_user_registration() {
        UserData user = new UserData("User 002", "5002", "user_002", "pass_002", "HP", "Pavilion 15", "12 hrs", "12 months", "WAR_002");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_002", "pass_002"));
    }

    @Test
    @DisplayName("Test 003: User Registration Test")
    void test_003_user_registration() {
        UserData user = new UserData("User 003", "5003", "user_003", "pass_003", "Lenovo", "ThinkPad", "12 hrs", "12 months", "WAR_003");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_003", "pass_003"));
    }

    @Test
    @DisplayName("Test 004: User Registration Test")
    void test_004_user_registration() {
        UserData user = new UserData("User 004", "5004", "user_004", "pass_004", "Asus", "Vivobook", "12 hrs", "12 months", "WAR_004");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_004", "pass_004"));
    }

    @Test
    @DisplayName("Test 005: User Registration Test")
    void test_005_user_registration() {
        UserData user = new UserData("User 005", "5005", "user_005", "pass_005", "Apple", "MacBook", "12 hrs", "12 months", "WAR_005");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_005", "pass_005"));
    }

    @Test
    @DisplayName("Test 006: User Registration Test")
    void test_006_user_registration() {
        UserData user = new UserData("User 006", "5006", "user_006", "pass_006", "Acer", "Aspire", "12 hrs", "12 months", "WAR_006");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_006", "pass_006"));
    }

    @Test
    @DisplayName("Test 007: User Registration Test")
    void test_007_user_registration() {
        UserData user = new UserData("User 007", "5007", "user_007", "pass_007", "MSI", "GE76", "12 hrs", "12 months", "WAR_007");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_007", "pass_007"));
    }

    @Test
    @DisplayName("Test 008: User Registration Test")
    void test_008_user_registration() {
        UserData user = new UserData("User 008", "5008", "user_008", "pass_008", "Razer", "Blade", "12 hrs", "12 months", "WAR_008");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_008", "pass_008"));
    }

    @Test
    @DisplayName("Test 009: User Registration Test")
    void test_009_user_registration() {
        UserData user = new UserData("User 009", "5009", "user_009", "pass_009", "Samsung", "Galaxy Book", "12 hrs", "12 months", "WAR_009");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_009", "pass_009"));
    }

    @Test
    @DisplayName("Test 010: User Registration Test")
    void test_010_user_registration() {
        UserData user = new UserData("User 010", "5010", "user_010", "pass_010", "Microsoft", "Surface", "12 hrs", "12 months", "WAR_010");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_010", "pass_010"));
    }

    @Test
    @DisplayName("Test 011: User Registration Test")
    void test_011_user_registration() {
        UserData user = new UserData("User 011", "5011", "user_011", "pass_011", "Dell", "Inspiron", "12 hrs", "12 months", "WAR_011");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_011", "pass_011"));
    }

    @Test
    @DisplayName("Test 012: User Registration Test")
    void test_012_user_registration() {
        UserData user = new UserData("User 012", "5012", "user_012", "pass_012", "HP", "Envy", "12 hrs", "12 months", "WAR_012");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_012", "pass_012"));
    }

    @Test
    @DisplayName("Test 013: User Registration Test")
    void test_013_user_registration() {
        UserData user = new UserData("User 013", "5013", "user_013", "pass_013", "Lenovo", "IdeaPad", "12 hrs", "12 months", "WAR_013");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_013", "pass_013"));
    }

    @Test
    @DisplayName("Test 014: User Registration Test")
    void test_014_user_registration() {
        UserData user = new UserData("User 014", "5014", "user_014", "pass_014", "Asus", "ROG", "12 hrs", "12 months", "WAR_014");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_014", "pass_014"));
    }

    @Test
    @DisplayName("Test 015: User Registration Test")
    void test_015_user_registration() {
        UserData user = new UserData("User 015", "5015", "user_015", "pass_015", "Apple", "MacBook Pro", "12 hrs", "12 months", "WAR_015");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_015", "pass_015"));
    }

    @Test
    @DisplayName("Test 016: User Registration Test")
    void test_016_user_registration() {
        UserData user = new UserData("User 016", "5016", "user_016", "pass_016", "Acer", "Swift", "12 hrs", "12 months", "WAR_016");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_016", "pass_016"));
    }

    @Test
    @DisplayName("Test 017: User Registration Test")
    void test_017_user_registration() {
        UserData user = new UserData("User 017", "5017", "user_017", "pass_017", "MSI", "Katana", "12 hrs", "12 months", "WAR_017");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_017", "pass_017"));
    }

    @Test
    @DisplayName("Test 018: User Registration Test")
    void test_018_user_registration() {
        UserData user = new UserData("User 018", "5018", "user_018", "pass_018", "Razer", "Book 13", "12 hrs", "12 months", "WAR_018");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_018", "pass_018"));
    }

    @Test
    @DisplayName("Test 019: User Registration Test")
    void test_019_user_registration() {
        UserData user = new UserData("User 019", "5019", "user_019", "pass_019", "Samsung", "Book S", "12 hrs", "12 months", "WAR_019");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_019", "pass_019"));
    }

    @Test
    @DisplayName("Test 020: User Registration Test")
    void test_020_user_registration() {
        UserData user = new UserData("User 020", "5020", "user_020", "pass_020", "Microsoft", "Laptop 5", "12 hrs", "12 months", "WAR_020");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_020", "pass_020"));
    }

    @Test
    @DisplayName("Test 021: User Registration Test")
    void test_021_user_registration() {
        UserData user = new UserData("User 021", "5021", "user_021", "pass_021", "Dell", "G15", "12 hrs", "12 months", "WAR_021");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_021", "pass_021"));
    }

    @Test
    @DisplayName("Test 022: User Registration Test")
    void test_022_user_registration() {
        UserData user = new UserData("User 022", "5022", "user_022", "pass_022", "HP", "Omen", "12 hrs", "12 months", "WAR_022");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_022", "pass_022"));
    }

    @Test
    @DisplayName("Test 023: User Registration Test")
    void test_023_user_registration() {
        UserData user = new UserData("User 023", "5023", "user_023", "pass_023", "Lenovo", "Legion", "12 hrs", "12 months", "WAR_023");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_023", "pass_023"));
    }

    @Test
    @DisplayName("Test 024: User Registration Test")
    void test_024_user_registration() {
        UserData user = new UserData("User 024", "5024", "user_024", "pass_024", "Asus", "TUF", "12 hrs", "12 months", "WAR_024");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_024", "pass_024"));
    }

    @Test
    @DisplayName("Test 025: User Registration Test")
    void test_025_user_registration() {
        UserData user = new UserData("User 025", "5025", "user_025", "pass_025", "Apple", "MacBook Air", "12 hrs", "12 months", "WAR_025");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_025", "pass_025"));
    }

    @Test
    @DisplayName("Test 026: User Registration Test")
    void test_026_user_registration() {
        UserData user = new UserData("User 026", "5026", "user_026", "pass_026", "Acer", "Predator", "12 hrs", "12 months", "WAR_026");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_026", "pass_026"));
    }

    @Test
    @DisplayName("Test 027: User Registration Test")
    void test_027_user_registration() {
        UserData user = new UserData("User 027", "5027", "user_027", "pass_027", "MSI", "Summit", "12 hrs", "12 months", "WAR_027");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_027", "pass_027"));
    }

    @Test
    @DisplayName("Test 028: User Registration Test")
    void test_028_user_registration() {
        UserData user = new UserData("User 028", "5028", "user_028", "pass_028", "Razer", "Pro 17", "12 hrs", "12 months", "WAR_028");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_028", "pass_028"));
    }

    @Test
    @DisplayName("Test 029: User Registration Test")
    void test_029_user_registration() {
        UserData user = new UserData("User 029", "5029", "user_029", "pass_029", "Samsung", "Notebook 9", "12 hrs", "12 months", "WAR_029");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_029", "pass_029"));
    }

    @Test
    @DisplayName("Test 030: User Registration Test")
    void test_030_user_registration() {
        UserData user = new UserData("User 030", "5030", "user_030", "pass_030", "Microsoft", "Surface Laptop", "12 hrs", "12 months", "WAR_030");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_030", "pass_030"));
    }

    @Test
    @DisplayName("Test 031: User Registration Test")
    void test_031_user_registration() {
        UserData user = new UserData("User 031", "5031", "user_031", "pass_031", "Dell", "Vostro", "12 hrs", "12 months", "WAR_031");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_031", "pass_031"));
    }

    @Test
    @DisplayName("Test 032: User Registration Test")
    void test_032_user_registration() {
        UserData user = new UserData("User 032", "5032", "user_032", "pass_032", "HP", "ProBook", "12 hrs", "12 months", "WAR_032");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_032", "pass_032"));
    }

    @Test
    @DisplayName("Test 033: User Registration Test")
    void test_033_user_registration() {
        UserData user = new UserData("User 033", "5033", "user_033", "pass_033", "Lenovo", "Yoga", "12 hrs", "12 months", "WAR_033");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_033", "pass_033"));
    }

    @Test
    @DisplayName("Test 034: User Registration Test")
    void test_034_user_registration() {
        UserData user = new UserData("User 034", "5034", "user_034", "pass_034", "Asus", "Zenbook", "12 hrs", "12 months", "WAR_034");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_034", "pass_034"));
    }

    @Test
    @DisplayName("Test 035: User Registration Test")
    void test_035_user_registration() {
        UserData user = new UserData("User 035", "5035", "user_035", "pass_035", "Apple", "iBook", "12 hrs", "12 months", "WAR_035");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_035", "pass_035"));
    }

    @Test
    @DisplayName("Test 036: User Registration Test")
    void test_036_user_registration() {
        UserData user = new UserData("User 036", "5036", "user_036", "pass_036", "Acer", "TravelMate", "12 hrs", "12 months", "WAR_036");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_036", "pass_036"));
    }

    @Test
    @DisplayName("Test 037: User Registration Test")
    void test_037_user_registration() {
        UserData user = new UserData("User 037", "5037", "user_037", "pass_037", "MSI", "Stealth", "12 hrs", "12 months", "WAR_037");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_037", "pass_037"));
    }

    @Test
    @DisplayName("Test 038: User Registration Test")
    void test_038_user_registration() {
        UserData user = new UserData("User 038", "5038", "user_038", "pass_038", "Razer", "Blade Stealth", "12 hrs", "12 months", "WAR_038");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_038", "pass_038"));
    }

    @Test
    @DisplayName("Test 039: User Registration Test")
    void test_039_user_registration() {
        UserData user = new UserData("User 039", "5039", "user_039", "pass_039", "Samsung", "Elite", "12 hrs", "12 months", "WAR_039");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_039", "pass_039"));
    }

    @Test
    @DisplayName("Test 040: User Registration Test")
    void test_040_user_registration() {
        UserData user = new UserData("User 040", "5040", "user_040", "pass_040", "Microsoft", "Surface Pro", "12 hrs", "12 months", "WAR_040");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_040", "pass_040"));
    }

    @Test
    @DisplayName("Test 041: User Registration Test")
    void test_041_user_registration() {
        UserData user = new UserData("User 041", "5041", "user_041", "pass_041", "Dell", "Alienware", "12 hrs", "12 months", "WAR_041");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_041", "pass_041"));
    }

    @Test
    @DisplayName("Test 042: User Registration Test")
    void test_042_user_registration() {
        UserData user = new UserData("User 042", "5042", "user_042", "pass_042", "HP", "Spectre", "12 hrs", "12 months", "WAR_042");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_042", "pass_042"));
    }

    @Test
    @DisplayName("Test 043: User Registration Test")
    void test_043_user_registration() {
        UserData user = new UserData("User 043", "5043", "user_043", "pass_043", "Lenovo", "Flex", "12 hrs", "12 months", "WAR_043");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_043", "pass_043"));
    }

    @Test
    @DisplayName("Test 044: User Registration Test")
    void test_044_user_registration() {
        UserData user = new UserData("User 044", "5044", "user_044", "pass_044", "Asus", "StudioBook", "12 hrs", "12 months", "WAR_044");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_044", "pass_044"));
    }

    @Test
    @DisplayName("Test 045: User Registration Test")
    void test_045_user_registration() {
        UserData user = new UserData("User 045", "5045", "user_045", "pass_045", "Apple", "PowerBook", "12 hrs", "12 months", "WAR_045");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_045", "pass_045"));
    }

    @Test
    @DisplayName("Test 046: User Registration Test")
    void test_046_user_registration() {
        UserData user = new UserData("User 046", "5046", "user_046", "pass_046", "Acer", "Nitro", "12 hrs", "12 months", "WAR_046");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_046", "pass_046"));
    }

    @Test
    @DisplayName("Test 047: User Registration Test")
    void test_047_user_registration() {
        UserData user = new UserData("User 047", "5047", "user_047", "pass_047", "MSI", "Bravo", "12 hrs", "12 months", "WAR_047");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_047", "pass_047"));
    }

    @Test
    @DisplayName("Test 048: User Registration Test")
    void test_048_user_registration() {
        UserData user = new UserData("User 048", "5048", "user_048", "pass_048", "Razer", "Surbook 13", "12 hrs", "12 months", "WAR_048");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_048", "pass_048"));
    }

    @Test
    @DisplayName("Test 049: User Registration Test")
    void test_049_user_registration() {
        UserData user = new UserData("User 049", "5049", "user_049", "pass_049", "Samsung", "Odyssey", "12 hrs", "12 months", "WAR_049");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_049", "pass_049"));
    }

    @Test
    @DisplayName("Test 050: User Registration Test")
    void test_050_user_registration() {
        UserData user = new UserData("User 050", "5050", "user_050", "pass_050", "Microsoft", "Book 3", "12 hrs", "12 months", "WAR_050");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_050", "pass_050"));
    }

    @Test
    @DisplayName("Test 051: User Registration Test")
    void test_051_user_registration() {
        UserData user = new UserData("User 051", "5051", "user_051", "pass_051", "Dell", "Precision", "12 hrs", "12 months", "WAR_051");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_051", "pass_051"));
    }

    @Test
    @DisplayName("Test 052: User Registration Test")
    void test_052_user_registration() {
        UserData user = new UserData("User 052", "5052", "user_052", "pass_052", "HP", "ZBook", "12 hrs", "12 months", "WAR_052");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_052", "pass_052"));
    }

    @Test
    @DisplayName("Test 053: User Registration Test")
    void test_053_user_registration() {
        UserData user = new UserData("User 053", "5053", "user_053", "pass_053", "Lenovo", "ThinkPad P", "12 hrs", "12 months", "WAR_053");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_053", "pass_053"));
    }

    @Test
    @DisplayName("Test 054: User Registration Test")
    void test_054_user_registration() {
        UserData user = new UserData("User 054", "5054", "user_054", "pass_054", "Asus", "ProArt", "12 hrs", "12 months", "WAR_054");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_054", "pass_054"));
    }

    @Test
    @DisplayName("Test 055: User Registration Test")
    void test_055_user_registration() {
        UserData user = new UserData("User 055", "5055", "user_055", "pass_055", "Apple", "MacBook Pro Max", "12 hrs", "12 months", "WAR_055");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_055", "pass_055"));
    }

    @Test
    @DisplayName("Test 056: User Registration Test")
    void test_056_user_registration() {
        UserData user = new UserData("User 056", "5056", "user_056", "pass_056", "Acer", "ConceptD", "12 hrs", "12 months", "WAR_056");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_056", "pass_056"));
    }

    @Test
    @DisplayName("Test 057: User Registration Test")
    void test_057_user_registration() {
        UserData user = new UserData("User 057", "5057", "user_057", "pass_057", "MSI", "Creator", "12 hrs", "12 months", "WAR_057");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_057", "pass_057"));
    }

    @Test
    @DisplayName("Test 058: User Registration Test")
    void test_058_user_registration() {
        UserData user = new UserData("User 058", "5058", "user_058", "pass_058", "Razer", "Book 15", "12 hrs", "12 months", "WAR_058");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_058", "pass_058"));
    }

    @Test
    @DisplayName("Test 059: User Registration Test")
    void test_059_user_registration() {
        UserData user = new UserData("User 059", "5059", "user_059", "pass_059", "Samsung", "Pro", "12 hrs", "12 months", "WAR_059");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_059", "pass_059"));
    }

    @Test
    @DisplayName("Test 060: User Registration Test")
    void test_060_user_registration() {
        UserData user = new UserData("User 060", "5060", "user_060", "pass_060", "Microsoft", "Surface Studio", "12 hrs", "12 months", "WAR_060");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_060", "pass_060"));
    }

    @Test
    @DisplayName("Test 061: User Registration Test")
    void test_061_user_registration() {
        UserData user = new UserData("User 061", "5061", "user_061", "pass_061", "Dell", "Studio", "12 hrs", "12 months", "WAR_061");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_061", "pass_061"));
    }

    @Test
    @DisplayName("Test 062: User Registration Test")
    void test_062_user_registration() {
        UserData user = new UserData("User 062", "5062", "user_062", "pass_062", "HP", "Elite", "12 hrs", "12 months", "WAR_062");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_062", "pass_062"));
    }

    @Test
    @DisplayName("Test 063: User Registration Test")
    void test_063_user_registration() {
        UserData user = new UserData("User 063", "5063", "user_063", "pass_063", "Lenovo", "Tab P", "12 hrs", "12 months", "WAR_063");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_063", "pass_063"));
    }

    @Test
    @DisplayName("Test 064: User Registration Test")
    void test_064_user_registration() {
        UserData user = new UserData("User 064", "5064", "user_064", "pass_064", "Asus", "Chromebook", "12 hrs", "12 months", "WAR_064");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_064", "pass_064"));
    }

    @Test
    @DisplayName("Test 065: User Registration Test")
    void test_065_user_registration() {
        UserData user = new UserData("User 065", "5065", "user_065", "pass_065", "Apple", "iPad Pro", "12 hrs", "12 months", "WAR_065");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_065", "pass_065"));
    }

    @Test
    @DisplayName("Test 066: User Registration Test")
    void test_066_user_registration() {
        UserData user = new UserData("User 066", "5066", "user_066", "pass_066", "Acer", "Spin", "12 hrs", "12 months", "WAR_066");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_066", "pass_066"));
    }

    @Test
    @DisplayName("Test 067: User Registration Test")
    void test_067_user_registration() {
        UserData user = new UserData("User 067", "5067", "user_067", "pass_067", "MSI", "Modern", "12 hrs", "12 months", "WAR_067");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_067", "pass_067"));
    }

    @Test
    @DisplayName("Test 068: User Registration Test")
    void test_068_user_registration() {
        UserData user = new UserData("User 068", "5068", "user_068", "pass_068", "Razer", "Blade 15", "12 hrs", "12 months", "WAR_068");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_068", "pass_068"));
    }

    @Test
    @DisplayName("Test 069: User Registration Test")
    void test_069_user_registration() {
        UserData user = new UserData("User 069", "5069", "user_069", "pass_069", "Samsung", "Notebook", "12 hrs", "12 months", "WAR_069");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_069", "pass_069"));
    }

    @Test
    @DisplayName("Test 070: User Registration Test")
    void test_070_user_registration() {
        UserData user = new UserData("User 070", "5070", "user_070", "pass_070", "Microsoft", "Duo", "12 hrs", "12 months", "WAR_070");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_070", "pass_070"));
    }

    @Test
    @DisplayName("Test 071: User Registration Test")
    void test_071_user_registration() {
        UserData user = new UserData("User 071", "5071", "user_071", "pass_071", "Dell", "XPS", "12 hrs", "12 months", "WAR_071");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_071", "pass_071"));
    }

    @Test
    @DisplayName("Test 072: User Registration Test")
    void test_072_user_registration() {
        UserData user = new UserData("User 072", "5072", "user_072", "pass_072", "HP", "Pavilion", "12 hrs", "12 months", "WAR_072");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_072", "pass_072"));
    }

    @Test
    @DisplayName("Test 073: User Registration Test")
    void test_073_user_registration() {
        UserData user = new UserData("User 073", "5073", "user_073", "pass_073", "Lenovo", "ThinkBook", "12 hrs", "12 months", "WAR_073");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_073", "pass_073"));
    }

    @Test
    @DisplayName("Test 074: User Registration Test")
    void test_074_user_registration() {
        UserData user = new UserData("User 074", "5074", "user_074", "pass_074", "Asus", "VivoBook X", "12 hrs", "12 months", "WAR_074");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_074", "pass_074"));
    }

    @Test
    @DisplayName("Test 075: User Registration Test")
    void test_075_user_registration() {
        UserData user = new UserData("User 075", "5075", "user_075", "pass_075", "Apple", "Mac mini", "12 hrs", "12 months", "WAR_075");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_075", "pass_075"));
    }

    @Test
    @DisplayName("Test 076: User Registration Test")
    void test_076_user_registration() {
        UserData user = new UserData("User 076", "5076", "user_076", "pass_076", "Acer", "Extensa", "12 hrs", "12 months", "WAR_076");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_076", "pass_076"));
    }

    @Test
    @DisplayName("Test 077: User Registration Test")
    void test_077_user_registration() {
        UserData user = new UserData("User 077", "5077", "user_077", "pass_077", "MSI", "Crosshair", "12 hrs", "12 months", "WAR_077");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_077", "pass_077"));
    }

    @Test
    @DisplayName("Test 078: User Registration Test")
    void test_078_user_registration() {
        UserData user = new UserData("User 078", "5078", "user_078", "pass_078", "Razer", "Blade Studio", "12 hrs", "12 months", "WAR_078");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_078", "pass_078"));
    }

    @Test
    @DisplayName("Test 079: User Registration Test")
    void test_079_user_registration() {
        UserData user = new UserData("User 079", "5079", "user_079", "pass_079", "Samsung", "ChromeBook", "12 hrs", "12 months", "WAR_079");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_079", "pass_079"));
    }

    @Test
    @DisplayName("Test 080: User Registration Test")
    void test_080_user_registration() {
        UserData user = new UserData("User 080", "5080", "user_080", "pass_080", "Microsoft", "Book Go", "12 hrs", "12 months", "WAR_080");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_080", "pass_080"));
    }

    @Test
    @DisplayName("Test 081: User Registration Test")
    void test_081_user_registration() {
        UserData user = new UserData("User 081", "5081", "user_081", "pass_081", "Dell", "Latitude", "12 hrs", "12 months", "WAR_081");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_081", "pass_081"));
    }

    @Test
    @DisplayName("Test 082: User Registration Test")
    void test_082_user_registration() {
        UserData user = new UserData("User 082", "5082", "user_082", "pass_082", "HP", "Stream", "12 hrs", "12 months", "WAR_082");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_082", "pass_082"));
    }

    @Test
    @DisplayName("Test 083: User Registration Test")
    void test_083_user_registration() {
        UserData user = new UserData("User 083", "5083", "user_083", "pass_083", "Lenovo", "Chromebook", "12 hrs", "12 months", "WAR_083");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_083", "pass_083"));
    }

    @Test
    @DisplayName("Test 084: User Registration Test")
    void test_084_user_registration() {
        UserData user = new UserData("User 084", "5084", "user_084", "pass_084", "Asus", "ExpertBook", "12 hrs", "12 months", "WAR_084");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_084", "pass_084"));
    }

    @Test
    @DisplayName("Test 085: User Registration Test")
    void test_085_user_registration() {
        UserData user = new UserData("User 085", "5085", "user_085", "pass_085", "Apple", "Studio", "12 hrs", "12 months", "WAR_085");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_085", "pass_085"));
    }

    @Test
    @DisplayName("Test 086: User Registration Test")
    void test_086_user_registration() {
        UserData user = new UserData("User 086", "5086", "user_086", "pass_086", "Acer", "Chromebook 11", "12 hrs", "12 months", "WAR_086");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_086", "pass_086"));
    }

    @Test
    @DisplayName("Test 087: User Registration Test")
    void test_087_user_registration() {
        UserData user = new UserData("User 087", "5087", "user_087", "pass_087", "MSI", "Alpha", "12 hrs", "12 months", "WAR_087");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_087", "pass_087"));
    }

    @Test
    @DisplayName("Test 088: User Registration Test")
    void test_088_user_registration() {
        UserData user = new UserData("User 088", "5088", "user_088", "pass_088", "Razer", "Chromebook", "12 hrs", "12 months", "WAR_088");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_088", "pass_088"));
    }

    @Test
    @DisplayName("Test 089: User Registration Test")
    void test_089_user_registration() {
        UserData user = new UserData("User 089", "5089", "user_089", "pass_089", "Samsung", "Galaxy Tab", "12 hrs", "12 months", "WAR_089");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_089", "pass_089"));
    }

    @Test
    @DisplayName("Test 090: User Registration Test")
    void test_090_user_registration() {
        UserData user = new UserData("User 090", "5090", "user_090", "pass_090", "Microsoft", "Surface Go", "12 hrs", "12 months", "WAR_090");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_090", "pass_090"));
    }

    @Test
    @DisplayName("Test 091: User Registration Test")
    void test_091_user_registration() {
        UserData user = new UserData("User 091", "5091", "user_091", "pass_091", "Dell", "Inspiron 15", "12 hrs", "12 months", "WAR_091");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_091", "pass_091"));
    }

    @Test
    @DisplayName("Test 092: User Registration Test")
    void test_092_user_registration() {
        UserData user = new UserData("User 092", "5092", "user_092", "pass_092", "HP", "Chromebook 14", "12 hrs", "12 months", "WAR_092");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_092", "pass_092"));
    }

    @Test
    @DisplayName("Test 093: User Registration Test")
    void test_093_user_registration() {
        UserData user = new UserData("User 093", "5093", "user_093", "pass_093", "Lenovo", "10e", "12 hrs", "12 months", "WAR_093");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_093", "pass_093"));
    }

    @Test
    @DisplayName("Test 094: User Registration Test")
    void test_094_user_registration() {
        UserData user = new UserData("User 094", "5094", "user_094", "pass_094", "Asus", "Flip", "12 hrs", "12 months", "WAR_094");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_094", "pass_094"));
    }

    @Test
    @DisplayName("Test 095: User Registration Test")
    void test_095_user_registration() {
        UserData user = new UserData("User 095", "5095", "user_095", "pass_095", "Apple", "Mac Studio", "12 hrs", "12 months", "WAR_095");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_095", "pass_095"));
    }

    @Test
    @DisplayName("Test 096: User Registration Test")
    void test_096_user_registration() {
        UserData user = new UserData("User 096", "5096", "user_096", "pass_096", "Acer", "AspireE15", "12 hrs", "12 months", "WAR_096");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_096", "pass_096"));
    }

    @Test
    @DisplayName("Test 097: User Registration Test")
    void test_097_user_registration() {
        UserData user = new UserData("User 097", "5097", "user_097", "pass_097", "MSI", "GF63", "12 hrs", "12 months", "WAR_097");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_097", "pass_097"));
    }

    @Test
    @DisplayName("Test 098: User Registration Test")
    void test_098_user_registration() {
        UserData user = new UserData("User 098", "5098", "user_098", "pass_098", "Razer", "Pro 14", "12 hrs", "12 months", "WAR_098");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_098", "pass_098"));
    }

    @Test
    @DisplayName("Test 099: User Registration Test")
    void test_099_user_registration() {
        UserData user = new UserData("User 099", "5099", "user_099", "pass_099", "Samsung", "Galaxy Tab A", "12 hrs", "12 months", "WAR_099");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_099", "pass_099"));
    }

    @Test
    @DisplayName("Test 100: User Registration Test")
    void test_100_user_registration() {
        UserData user = new UserData("User 100", "5100", "user_100", "pass_100", "Microsoft", "Book 2", "12 hrs", "12 months", "WAR_100");
        UserData.saveUser(user);
        Assertions.assertNotNull(UserData.getUser("user_100", "pass_100"));
    }
}
