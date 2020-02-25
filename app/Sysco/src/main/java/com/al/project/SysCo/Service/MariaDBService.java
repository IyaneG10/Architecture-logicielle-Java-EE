package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

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
    }


    public static JSONArray getDataByAll() throws SQLException, JSONException {

        //STEP 4: Execute a query
        String sql = "SELECT * FROM data";

        ResultSet rs = stmt.executeQuery(sql);

        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            int numColumns = rsmd.getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i=1; i<=numColumns; i++) {
                String column_name = rsmd.getColumnName(i);
                obj.put(column_name, rs.getObject(column_name));
            }
            json.put(obj);
        }
        //System.out.println(json);
        return json;

        //return DBManager.GetObjectsFromDB(Query, rs -> FetchUserFromDB(rs));
    }

    public  JSONArray getDataByRoom(int room) throws SQLException, JSONException {

        String sql = "SELECT * FROM data WHERE rpi_id = "+ room;

        ResultSet rs = stmt.executeQuery(sql);

        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            int numColumns = rsmd.getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i=1; i<=numColumns; i++) {
                String column_name = rsmd.getColumnName(i);
                obj.put(column_name, rs.getObject(column_name));

            }
            json.put(obj);
        }
        return json;
    }

    public  JSONArray getDataByRoomAndSensor(int room, String measureName) throws SQLException, JSONException {

       String sql = "SELECT * FROM data WHERE rpi_id = "+ room +" AND measure_name =" +"\"" +measureName+ "\"";
        ResultSet rs = stmt.executeQuery(sql);

        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            int numColumns = rsmd.getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i=1; i<=numColumns; i++) {
                String column_name = rsmd.getColumnName(i);
                obj.put(column_name, rs.getObject(column_name));

            }
            json.put(obj);
        }
        return json;
    }


    public  JSONArray getRoom() throws SQLException, JSONException {

        String sql = "SELECT DISTINCT rpi_id FROM data ORDER BY rpi_id ASC;";
        ResultSet rs = stmt.executeQuery(sql);

        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            int numColumns = rsmd.getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i=1; i<=numColumns; i++) {
                String column_name = rsmd.getColumnName(i);
                obj.put(column_name, rs.getObject(column_name));

            }
            json.put(obj);
        }
        return json;
    }


    public class ErrorSavingInDB extends Exception {
        public ErrorSavingInDB(String errorMessage) {
            super(errorMessage);
        }
    }

}


