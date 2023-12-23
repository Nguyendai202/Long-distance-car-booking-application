
package cab.model;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.*;

public class ConnectDatabase {
    public Statement s;
    public Connection c;
    public ConnectDatabase() {
        SQLServerDataSource con = new SQLServerDataSource();
        con.setUser("sa");
        con.setPassword("113202");
        con.setServerName("DESKTOP-0EF4K9Q\\SQLEXPRESS");
        con.setPortNumber(1433);
        con.setDatabaseName("BookCoach");
        con.setEncrypt("true");
        con.setTrustServerCertificate(true);
        try {
            c = con.getConnection();
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}  

