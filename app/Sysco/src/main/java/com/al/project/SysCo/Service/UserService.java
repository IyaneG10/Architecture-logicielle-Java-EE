package com.al.project.SysCo.Service;


import com.al.project.SysCo.Model.User;


public interface UserService {
    void save(User user);

    User findByUsername(String username);
}