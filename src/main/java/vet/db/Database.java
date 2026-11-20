package vet.db;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public interface Database {

    String dbUrl = "jdbc:sqlite:my.db";
    String schemaDirectory = "src/main/resources/vet/db/";



    /*
        This is an easier way to make a query and get data from the database
        Takes a string in the form of a query such as "SELECT name FROM example"
        Takes a RowMapper object required to parse through the results of the query
        EXAMPLE: List<Pet> pets = Database.query("SELECT id, name FROM pets",rs-> new Pet(rs.getInt("id"),rs.getString("name")
                 will create a list of all pets inside the Database
     */

     static <T> List<T> query(String query, RowMapper<T> mapper) {

        try(Connection conn = DriverManager.getConnection(dbUrl);
            Statement stmt = conn.createStatement()){

            ResultSet rset = stmt.executeQuery(query);
            List<T> results = new ArrayList<T>();

            while(rset.next()){
                results.add(mapper.map(rset));
            }
            return results;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();

            // I am not sure if I want to crash the software for a query error
            // for now report the error message and send an empty array
            //            throw new RuntimeException(e);

        }
    }


    /*
       Updates table should include deleting and creating a table.
       EXAMPLE: Database.update("UPDATE example SET name = 'bill' WHERE id = 1")
     */
    static void update(String query){
         try(Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
             stmt.executeUpdate(query);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
//             throw new RuntimeException(e);
         }
    }

    static void tryParseSchema(String schemaName) {
        try {
            BufferedReader schema = new BufferedReader(new FileReader(String.format("%s%s", schemaDirectory, schemaName)));
            String readLine;
            String totalLine = "";
            while ((readLine = schema.readLine()) != null) {
                totalLine = totalLine.concat(readLine.strip());
                if(readLine.contains(";")) {
                    Database.update(totalLine);
                    totalLine = "";
                }
            }
            schema.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



}
