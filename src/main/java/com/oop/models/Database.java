package com.oop.models;

/**
 *
 * @author orestis
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {

    private static final String FULL_DB_URL = "jdbc:mysql://172.17.0.2:3306/bootdb?createDatabaseIfNotExist=true&autoReconnect=true&serverTimezone=UTC&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "root";
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement pst = null;
    

    
        public Database(){
        getConnection();
    }

       public static Connection getConnection() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            return connection;
        } catch (SQLException ex) {
            System.out.println("");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static ResultSet getResults(String query) { // query = "SELECT * FROM Customers"
        try {
            setStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Statement getStatement() {
        return statement;
    }

    public Statement getStatementNonStatic() {
        return statement;
    }

    public static void setStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStatementNonStatic() {
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static PreparedStatement getPreparedStatement() {
        return pst;
    }

    public static void setPreparedStatement(String query) {
        try {
            pst = connection.prepareStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setPreparedStatementWithKeys(String query) {
        try {
            pst = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}