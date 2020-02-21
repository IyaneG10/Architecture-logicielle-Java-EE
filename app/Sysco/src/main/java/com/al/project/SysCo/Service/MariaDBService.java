package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Data;

import java.sql.DriverManager;
import java.sql.ResultSet;
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
    static Statement stmt = null;

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
            //Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sysco?user=admin&password=admin");
            stmt = ((java.sql.Connection) conn).createStatement();
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
    }

    public void addData(Data data) throws ErrorSavingInDB, SQLException {


        //STEP 4: Execute a query
        String sql = "INSERT INTO data"
                + "(rpi_id, state, measure_name, measure_value, date) "
                +"VALUES "
                + "("  + data.getRpiId()
                + ", " + data.isState()
                + ", '" + data.getMeasureName()
                + "', " + data.getMeasureValue()
                + ", '" + data.getDate()
                + "')";

        //sql = "SHOW COLUMNS FROM data";

        ResultSet rs = stmt.executeQuery(sql);

        /*String st ="";

        if (rs.next())
            st= rs.getString(1);


        System.out.println(st);

        if (rs.next())
            st = rs.getString(2);

        System.out.println(st);

        if (rs.next())
            st = rs.getString(3);

        System.out.println(st);

        if (rs.next())
            st = rs.getString(4);

        System.out.println(st);

        if (rs.next())
            st = rs.getString(5);

        System.out.println(st);

    */
        System.out.println(" Data saved in database...");
    }

    public static   ResultSet getDataByAll() throws  SQLException {


        //STEP 4: Execute a query
        String sql = "SELECT FROM data";

        ResultSet rs = stmt.executeQuery(sql);

        return rs;

        //return DBManager.GetObjectsFromDB(Query, rs -> FetchUserFromDB(rs));
    }

    public class ErrorSavingInDB extends Exception {
        public ErrorSavingInDB(String errorMessage) {
            super(errorMessage);
        }
    }

}


