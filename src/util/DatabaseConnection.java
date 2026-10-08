package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/BankingApplication";
            String uname = "root";
            String pwd = "root";

            Connection con = DriverManager.getConnection(url, uname, pwd);

            return con;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}