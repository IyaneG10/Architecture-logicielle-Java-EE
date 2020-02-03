package com.al.project.SysCo.Service;


import com.al.project.SysCo.Model.User;
import com.al.project.SysCo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User create(User u) {
        if(Objects.nonNull(u))
        {
            return repository.save(u);
        }
        return null;
    }

}