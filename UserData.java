import java.io.*;
import java.util.HashMap;

public class UserData implements Serializable {
    private String ownerName;
    private String ownerContact;
    private String ownerUserid;
    private String ownerPassword;
    private String laptopBrand;
    private String laptopModel;
    private String laptopBatteryLife;
    private String warrantyDuration;
    private String warrantyId;

    private static final String DATA_FILE = "userdata.ser";
    private static final String DELETED_DATA_FILE = "deleted_users.ser";
    private static HashMap<String, UserData> users = new HashMap<>();
    private static HashMap<String, UserData> deletedUsers = new HashMap<>();

    public static final int LOGIN_SUCCESS = 0;
    public static final int LOGIN_INVALID = 1;
    public static final int LOGIN_WARRANTY_EXPIRED = 2;

    static {
        loadUsers();
        loadDeletedUsers();
    }

    public UserData(String ownerName, String ownerContact, String ownerUserid, String ownerPassword,
                   String laptopBrand, String laptopModel, String laptopBatteryLife,
                   String warrantyDuration, String warrantyId) {
        this.ownerName = ownerName;
        this.ownerContact = ownerContact;
        this.ownerUserid = ownerUserid;
        this.ownerPassword = ownerPassword;
        this.laptopBrand = laptopBrand;
        this.laptopModel = laptopModel;
        this.laptopBatteryLife = laptopBatteryLife;
        this.warrantyDuration = warrantyDuration;
        this.warrantyId = warrantyId;
    }

    public static void saveUser(UserData userData) {
        users.put(userData.ownerUserid, userData);
        saveUsers();
    }

    public static int validateLogin(String userid, String password) {
        UserData userData = users.get(userid);
        if (userData == null) {
            // Check if user was deleted
            if (deletedUsers.containsKey(userid)) {
                return LOGIN_WARRANTY_EXPIRED;
            }
            return LOGIN_INVALID;
        }
        if (userData.ownerPassword.equals(password)) {
            return LOGIN_SUCCESS;
        }
        return LOGIN_INVALID;
    }

    public static UserData getUser(String userid, String password) {
        UserData userData = users.get(userid);
        if (userData != null && userData.ownerPassword.equals(password)) {
            return userData;
        }
        return null;
    }

    public static UserData getDeletedUserData(String userid) {
        return deletedUsers.get(userid);
    }

    public static boolean deleteUser(String userId) {
        UserData userData = users.get(userId);
        if (userData != null) {
            users.remove(userId);
            deletedUsers.put(userId, userData);
            saveUsers();
            saveDeletedUsers();
            return true;
        }
        return false;
    }

    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveDeletedUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DELETED_DATA_FILE))) {
            oos.writeObject(deletedUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadUsers() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                users = (HashMap<String, UserData>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                users = new HashMap<>();
            }
        } else {
            users = new HashMap<>();
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadDeletedUsers() {
        File file = new File(DELETED_DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                deletedUsers = (HashMap<String, UserData>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                deletedUsers = new HashMap<>();
            }
        } else {
            deletedUsers = new HashMap<>();
        }
    }

    // Getters
    public String getOwnerName() { return ownerName; }
    public String getOwnerContact() { return ownerContact; }
    public String getOwnerUserid() { return ownerUserid; }
    public String getLaptopBrand() { return laptopBrand; }
    public String getLaptopModel() { return laptopModel; }
    public String getLaptopBatteryLife() { return laptopBatteryLife; }
    public String getWarrantyDuration() { return warrantyDuration; }
    public String getWarrantyId() { return warrantyId; }

    public static HashMap<String, UserData> getAllUsers() {
        return users;
    }
}
