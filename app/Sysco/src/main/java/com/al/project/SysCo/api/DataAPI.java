package com.al.project.SysCo.api;


import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Service.DataService;
import com.al.project.SysCo.Service.MariaDBService;
import com.al.project.SysCo.Service.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api-datas")
public class DataAPI {
    MariaDBService mariaDBService = new MariaDBService("jdbc:mariadb://localhost:3306/sysco", "admin", "admin");


    @GetMapping("/all")
    public ResponseEntity<String> findAllDatas() throws SQLException, JSONException {
        return new ResponseEntity<String>(mariaDBService.getDataByAll()+ "", HttpStatus.OK);
    }

    @GetMapping("/room{room_id}")
    public ResponseEntity<String> findDataByRoom(@PathVariable("room_id") int id)  throws SQLException, JSONException {
        return new ResponseEntity<String>(mariaDBService.getDataByRoom(id)+ "", HttpStatus.OK);
    }
  //  private static   DataService dataService;

    //@Autowired
    //public DataAPI(DataService dataService) {
      //  this.dataService = dataService;
    //}
/*
    @GetMapping
    public static ResponseEntity<Data> saveData(Data data) {
        //dataService.save(data);
        System.out.println("METHODE BIEN APPELLEE DANS API");
        dataService.saveData(data);
        return null;
    }
*/


/*

    @GetMapping
    public ResponseEntity<JSONArray> findData() throws SQLException, JSONException {
        return ResponseEntity.ok(MariaDBService.getDataByAll());
    }
*/

}
