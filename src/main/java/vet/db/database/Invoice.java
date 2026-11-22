package vet.db.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public record Invoice(int invoiceId, int appointmentId, int itemId, int itemsUsed, double itemCost) {

    private static final String prompt = "insert into invoices(appointment_id, item_id, items_used, item_cost) values ('%d', '%d', '%d', '%f');";

    public static Invoice fromRow(ResultSet rs) throws SQLException {
        return new Invoice(
                rs.getInt("invoice_id"),
                rs.getInt("appointment_id"),
                rs.getInt("item_id"),
                rs.getInt("items_used"),
                rs.getDouble("item_cost")
        );
    }

    public static void add(int appointment_id, int item_id, int items_used, double item_cost) {
        Database.update(String.format(Locale.ROOT, prompt, appointment_id, item_id, items_used, item_cost));
    }
}