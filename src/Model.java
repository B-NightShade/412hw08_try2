import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    Connection conn;

    ResultSet selectconnection() {
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "SELECT * FROM students;";
            rs = conn.createStatement().executeQuery(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    void createConnection(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            conn.createStatement().executeUpdate(String.format("INSERT INTO students (name, age) VALUES ('%s',%d);", Controller.name, Controller.age));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void updateConnection(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            conn.createStatement().executeUpdate(String.format("UPDATE students SET name = '%s', age = %d WHERE id = %d;", Controller.name, Controller.age, Controller.id));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void createTable(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY," +
                    "name STRING," +
                    "age INTEGER);";
            conn.createStatement().executeUpdate(cmd);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
