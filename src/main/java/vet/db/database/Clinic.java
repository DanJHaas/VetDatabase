package vet.db.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public record Clinic(int clinicId, String name, String address, String phoneNumber) {

    private static final String prompt = "insert into clinics(name, address, phone_number) values ('%s', '%s', '%s');";

    public static Clinic fromRow(ResultSet rs) throws SQLException {
        return new Clinic(
                rs.getInt("clinic_id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("phone_number")
        );
    }

    public static void add(String name, String address, String phone_number) {
        Database.update(String.format(Locale.ROOT, prompt, name, address, phone_number));
    }
}
