package vet.db.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public record User(int userId, int clinicId, String firstName, String lastName, String username, String passwordHash, String phoneNumber, String email, double currentBill) {

    private static final String prompt = "insert into users(clinic_id, first_name, last_name, username, password_hash, phone_number, email, current_bill) values ('%d', '%s', '%s', '%s', '%s', '%s', '%s', '%f');";

    public static User fromRow(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getInt("clinic_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getString("phone_number"),
                rs.getString("email"),
                rs.getDouble("current_bill")
        );
    }

    public static void add(int clinicId, String firstName, String lastName, String username, String passwordHash, String phoneNumber, String email, double currentBill) {
        Database.update(String.format(Locale.ROOT, prompt, clinicId, firstName, lastName, username, passwordHash, phoneNumber, email, currentBill));
    }
}
