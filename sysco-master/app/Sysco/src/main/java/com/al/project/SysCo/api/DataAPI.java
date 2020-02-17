package com.al.project.SysCo.api;


import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Model.Product;
import com.al.project.SysCo.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/datas")
public class DataAPI {

    private final DataService dataService;

    @Autowired
    public DataAPI(DataService dataService) {
        this.dataService = dataService;
    }
/*
    @GetMapping
    public ResponseEntity<Data> save(Data data);

    {
       return ResponseEntity.status(HttpStatus.CREATED).body(dataService.save(data));
    }


 */

    @GetMapping
    public ResponseEntity<Data> create(Data data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dataService.save(data));
    }


}
