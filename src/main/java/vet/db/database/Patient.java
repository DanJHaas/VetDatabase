package vet.db.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public record Patient(int patientId, int userId, String firstName, String lastName) {

    private static final String prompt = "insert into patients(user_id, first_name, last_name) values ('%d', '%s', '%s');";

    public static Patient fromRow(ResultSet rs) throws SQLException {
        return new Patient(
                rs.getInt("patient_id"),
                rs.getInt("user_id"),
                rs.getString("first_name"),
                rs.getString("last_name")
        );
    }

    public static void add(int user_id, String first_name, String last_name) {
        Database.update(String.format(Locale.ROOT, prompt, user_id, first_name, last_name));
    }
}