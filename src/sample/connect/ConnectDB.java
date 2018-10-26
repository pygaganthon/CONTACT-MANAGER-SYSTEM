package sample.connect;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
*/
import java.sql.*;
//import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;
// INITIALLY SAVE THE PHONE NUMBER WHEN RETRIEVED
// WHEN UPDATING,
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Created by ONUR BASKIRT on 22.02.2016.
 */
public class ConnectDB {
    @FXML
    private static TextArea result;
    //Declare JDBC Driver
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    //Connection
    private static Connection conn = null;

    //Connection String
    //String connStr = "jdbc:oracle:thin:Username/Password@IP:Port/SID";
    //Username=HR, Password=HR, IP=localhost, IP=1521, SID=xe
    private static final String connStr = "jdbc:sqlserver://localhost:1433;instanceName=DESKTOP-FFBL893;databaseName=CONTACTMANAGER;integratedSecurity=true";


    //Connect to DB
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        //Setting Oracle JDBC Driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            throw e;
        }

        System.out.println("Oracle JDBC Driver Registered!");

        //Establish the Oracle Connection using Connection String
        try {
            conn = DriverManager.getConnection(connStr);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
    }


    //DB Execute Query Operation
    public static ResultSet dbExecuteSQuery(String queryStmt,String Firstname,String Midname,String Lastname,String sex,String Bdate,String PhoneNo,String Email,String City,String State, String Zip ,String AddressLine) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        //Statement stmtSQL = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;


            //Connect to DB (Establish Oracle Connection)

        PreparedStatement ps = null;
        try {
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            ps = conn.prepareStatement(queryStmt);

            ps.setObject(1, Firstname);

            ps.setObject(2, Midname);

            ps.setObject(3, Lastname);

            ps.setObject(4, sex);

            ps.setObject(5, Bdate);

            ps.setObject(6, PhoneNo);

            ps.setObject(7, Email);

            ps.setObject(8, City);

            ps.setObject(9, State);

            ps.setObject(10, Zip);

            ps.setObject(11, AddressLine);
            /*
            ps.setString(1, Firstname);

            ps.setString(2, Midname);

            ps.setString(3, Lastname);

            ps.setString(4, Bdate);

            ps.setString(5, sex);

            ps.setString(6, PhoneNo);

            ps.setString(7, Email);

            ps.setString(8, City);

            ps.setString(9, State);

            ps.setString(10, Zip);

            ps.setString(11, AddressLine);
*/
            resultSet = ps.executeQuery();


            //Create statement
            //stmtSQL = conn.createStatement();
            //Execute select (query) operation
            //resultSet = stmtSQL.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = new CachedRowSetImpl();

            crs.populate(resultSet);
        }
        catch (SQLException e) {
            System.out.println("Problem occurred at executeSQuery operation : " + e);
            throw e;
        } finally {
            ps.close();
            conn.close();
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }


    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmtSQL = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");
            //Create statement
            stmtSQL = conn.createStatement();
            //Execute select (query) operation
            resultSet = stmtSQL.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = new CachedRowSetImpl();

            crs.populate(resultSet);

        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmtSQL != null) {
                //Close Statement
                stmtSQL.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static int dbExecuteUpdate(String sqlStmt,String Firstname,String Midname,String Lastname,String Bdate,String sex,String PhoneNo,String Email,String City,String State, String Zip ,String AddressLine) throws SQLException, ClassNotFoundException {
        //Declare statement as null

        //Statement stmt = null;
        PreparedStatement ps = null;
        try {
            dbConnect();
            ps = conn.prepareCall(sqlStmt);
            ps.setString(1, Firstname);
            ps.setString(2, Midname);
            ps.setString(3, Lastname);
            ps.setString(4, Bdate);
            ps.setString(5, sex);
            ps.setString(6, PhoneNo);
            ps.setString(7, Email);
            ps.setString(8, City);
            ps.setString(9, State);
            ps.setString(10, Zip);
            ps.setString(11, AddressLine);
            int n = ps.executeUpdate();
            return n;

            //ps.close();

            //Connect to DB (Establish Oracle Connection)

            //Create Statement
            //stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement



        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            ps.close();
            conn.close();
            //Close connection
            dbDisconnect();

        }
    }


    //DB Execute Update (For Insert/Delete) Operation
    public static int dbExecuteDI(String sqlStmt,String PersonID) throws SQLException, ClassNotFoundException {
        //Declare statement as null

        //Statement stmt = null;
        PreparedStatement ps = null;
        try {
            dbConnect();
            ps = conn.prepareCall(sqlStmt);
            ps.setString(1, PersonID);
            //Create Statement
          //  stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement

            int n = ps.executeUpdate();
            return n;
            /*ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){

                int pk = rs.getInt(1);
                return pk;}*/



         } catch (SQLException e) {
            System.out.println("Problem occurred at executeDelete operation : " + e);
            throw e;

        } finally {
            ps.close();
            conn.close();
            //Close connection
            dbDisconnect();
        }
    }

}