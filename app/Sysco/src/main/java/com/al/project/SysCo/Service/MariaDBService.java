package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MariaDBService {


    // JDBC driver name and database URL

    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static String DB_URL;

    //  Database credentials
    private static String USER;
    private static String PASSWORD;


    java.sql.Connection conn = null;
    Statement stmt = null;

    public MariaDBService(String DB_URL, String USER, String PASSWORD) {
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        configure();
    }


    private void  configure(){

        try {
            //STEP 2: Register JDBC driver
            //Class.forName("org.mariadb.jdbc.Driver");
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected database successfully...");

        }
        catch (SQLException se) {

            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception e) {

            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally {

            //finally block used to close resources
            try {

                if (stmt != null) {
                    conn.close();
                }
            }
            catch (SQLException se) {

            }
            try {

                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException se) {

                se.printStackTrace();
            }
        }
    }

    public void addData(Data data) throws ErrorSavingInDB, SQLException {


        //STEP 4: Execute a query
        stmt = ((java.sql.Connection) conn).createStatement();
        String sql = "INSERT INTO Data"
                + "(roomId, state, measureName, measureValue, date), "
                +"VALUES "
                + "("  + data.getRpiId()
                + ", " + data.isState()
                + ", '" + data.getMeasureName()
                + "', " + data.getMeasureValue()
                + ", '" + data.getDate()
                + "')";

        stmt.executeUpdate(sql);
        System.out.println("Created table in given database...");
    }


    // JDBC driver name and database URL

    /*static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://192.168.100.174/db";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    */

    /* Config MariaDB */
        /*

        java.sql.Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/db", "root", "glopglop");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = ((java.sql.Connection) conn).createStatement();

             String sql = "CREATE TABLE REGISTRATION "
                    + "(id INTEGER not NULL, "
                    + " first VARCHAR(255), "
                    + " last VARCHAR(255), "
                    + " age INTEGER, "
                    + " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
       */

    public class ErrorSavingInDB extends Exception {
        public ErrorSavingInDB(String errorMessage) {
            super(errorMessage);
        }
    }

}
