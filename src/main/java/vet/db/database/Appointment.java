package vet.db.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public record Appointment(int appointmentId, int employeeId, int patientId, String summary, long dateTime) {

    private static final String prompt = "insert into appointments(employee_id, patient_id, summary, date_time) values ('%d', '%d', '%s', '%d');";

    public static Appointment fromRow(ResultSet rs) throws SQLException {
        return new Appointment(
                rs.getInt("appointment_id"),
                rs.getInt("employee_id"),
                rs.getInt("patient_id"),
                rs.getString("summary"),
                rs.getLong("date_time")
        );
    }

    public static void add(int employee_id, int patient_id, String summary, long date_time) {
        Database.update(String.format(Locale.ROOT, prompt, employee_id, patient_id, summary, date_time));
    }
}