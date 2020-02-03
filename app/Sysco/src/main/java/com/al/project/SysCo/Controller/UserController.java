package com.al.project.SysCo.Controller;

import com.al.project.SysCo.Model.User;
import com.al.project.SysCo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/add")
    public ResponseEntity add (@RequestBody User u){
        if (Objects.isNull(u))
        {
            return (ResponseEntity) ResponseEntity.badRequest();
        }
        u = service.create(u);
        return ResponseEntity.ok("Hello World!");
    }

}
