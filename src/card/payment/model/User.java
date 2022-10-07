package card.payment.model;

public class User {
    private int id;
    private String phone;
    private String password;
    private boolean isAdmin;
    private boolean isBanned;
    private boolean isActive;
    public User(String phone, String password, boolean isAdmin, boolean isBanned) {
        this.phone = phone;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
    }
    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
    public User(String phone, String password, boolean isAdmin, boolean isBanned, boolean isActive) {
        this.phone = phone;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
        this.isActive = isActive;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public boolean isBanned() {
        return isBanned;
    }
    public void setBanned(boolean banned) {
        isBanned = banned;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", isBanned=" + isBanned +
                '}';
    }
}
