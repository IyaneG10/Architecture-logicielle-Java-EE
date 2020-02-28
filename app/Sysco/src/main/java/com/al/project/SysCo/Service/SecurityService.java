package com.al.project.SysCo.Service;


public interface SecurityService {
    String findLoggedInUsername();

    int autoLogin(String username, String password);
}