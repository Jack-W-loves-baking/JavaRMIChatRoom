package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
This class includes all the operations from Java Derby database.
Therefore, opimize the code if need to create the connection and send queries.
*/

public class DBOperations {

    private static final String url = "jdbc:derby://localhost:1527/UserDB;create=true";
    String tableName = "UserData";
    private static final String username = "dms";
    private static final String password = "dms";
    Connection conn;
    final String quote = "'";

    public void createConnection() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println(url + " connected....");

        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean checkTableExist() throws SQLException {

        boolean tableExist = false;

        //schema for new db is the login username in uppercases, in this case, DMS
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, "DMS", tableName.toUpperCase(), null);

        //if table exists
        if (rs.next()) {
            tableExist = true;
        }

        return tableExist;

    }

    public void createTable() {

        try {

            //if table does not exist, need to create a new table
            if (!checkTableExist()) {
                Statement statement = conn.createStatement();

                String sqlCreate = "CREATE TABLE " + tableName + " "
                        + "(Username varchar(255), Password varchar(255), "
                        + "Email varchar(255))";

                statement.executeUpdate(sqlCreate);

                System.out.println("Table created");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    //return resultset for sql select queries
    public ResultSet selectQuery(String sql) {

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void insertIntoDatabase(String username, String password, String email) {

        String sql = "INSERT INTO " + tableName.toUpperCase() + "(Username, Password, Email)" + " VALUES ("+
                quote + username + quote + ", " + quote + password + quote + ", "+quote + email + quote+")";
        try {
            conn.createStatement().executeUpdate(sql);
            System.out.println("Successfully added!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
