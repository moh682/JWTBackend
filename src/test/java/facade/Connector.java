/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author andre
 */
class Connector {
    private static final String HOST = "jdbc:mysql://46.101.104.53:3306/seedtest";
    private static final String DBNAME = "seedtest";
    private static final String URL = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
    private static final String USERNAME = "seed_user";
    private static final String PASSWORD = "ostemad";
    private static Connection singleton;

    public static void setConnection(Connection con) {
        singleton = con;
    }

    public static Connection connection() throws ClassNotFoundException, SQLException {
        if (singleton == null) {
            Class.forName("com.mysql.jdbc.Driver");
            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return singleton;
    }

}
    

