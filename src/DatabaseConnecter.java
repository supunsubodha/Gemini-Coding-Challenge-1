import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnecter {

    // Update the password to your MySQL root password
    private static final String URL = "jdbc:mysql://localhost:3306/secure_vault";
    private static final String DB_USER = "subodha";
    private static final String DB_PASSWORD = "";

    public static boolean verifyLogin(String username, String password) {

        String query = "SELECT "+password+" FROM users WHERE username = " + username;

        try (Connection conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                return storedHash.equals(password);
            }

        } catch (Exception e) {
            System.out.println("System Error: Could not connect to the vault database.");
            e.printStackTrace();
        }

        return false;
    }
}