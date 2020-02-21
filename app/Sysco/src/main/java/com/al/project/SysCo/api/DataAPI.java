package com.al.project.SysCo.api;


import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Service.DataService;
import com.al.project.SysCo.Service.MariaDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/api/datas")
public class DataAPI {

    private static   DataService dataService;

    @Autowired

/*
    public DataAPI(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public static ResponseEntity<Data> saveData(Data data) {
        //dataService.save(data);
        System.out.println("METHODE BIEN APPELLEE DANS API");
        dataService.saveData(data);
        return null;
    }
      */




    @GetMapping
    public ResponseEntity<ResultSet> findAll() throws SQLException {
        return ResponseEntity.ok(MariaDBService.getDataByAll());
    }

/*

    public  void sauvegarderData(Data data) {

        //pusblish vers rpi
        //GetRealTimeTopics();
        dataService.saveData(data);
        System.out.println("METHODE BIEN APPELLEE DANS API");

    }
    */


}
