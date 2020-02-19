package com.al.project.SysCo.api;


import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/datas")
public class DataAPI {

    private static   DataService dataService;

    @Autowired
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
/*

    public  void sauvegarderData(Data data) {

        //pusblish vers rpi
        //GetRealTimeTopics();
        dataService.saveData(data);
        System.out.println("METHODE BIEN APPELLEE DANS API");

    }
    */


}
