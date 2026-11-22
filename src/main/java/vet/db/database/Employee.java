package vet.db.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public record Employee(int employeeId, int clinicId, String firstName, String lastName, String phoneNumber, String schedule) {

    private static final String prompt = "insert into employees(clinic_id, first_name, last_name, phone_number, schedule) values ('%d', '%s', '%s', '%s', '%s');";

    public static Employee fromRow(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("employee_id"),
                rs.getInt("clinic_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("phone_number"),
                rs.getString("schedule")
        );
    }
    public static void add(int clinicId, String firstName, String lastName, String phoneNumber, String schedule) {
        Database.update(String.format(Locale.ROOT, prompt, clinicId, firstName, lastName, phoneNumber, schedule));
    }
}
