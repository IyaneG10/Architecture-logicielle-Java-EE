package com.al.project.sysco.Service;


public interface SecurityService {
    String findLoggedInUsername();

    int autoLogin(String username, String password);
}