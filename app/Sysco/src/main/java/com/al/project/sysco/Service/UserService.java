package com.al.project.sysco.Service;

import com.al.project.sysco.Model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}