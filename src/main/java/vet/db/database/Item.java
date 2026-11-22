package vet.db.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public record Item(int itemId, int clinicId, String itemName, double itemCost, int itemCount) {

    private static final String prompt = "insert into items(clinic_id, item_name, item_cost, item_count) values ('%d', '%s', '%f', '%d');";

    public static Item fromRow(ResultSet rs) throws SQLException {
        return new Item(
                rs.getInt("item_id"),
                rs.getInt("clinic_id"),
                rs.getString("item_name"),
                rs.getDouble("item_cost"),
                rs.getInt("item_count")
        );
    }
    public static void add(int clinic_id, String item_name, double item_cost, int item_count) {
        Database.update(String.format(Locale.ROOT, prompt, clinic_id, item_name, item_cost, item_count));
    }
}
