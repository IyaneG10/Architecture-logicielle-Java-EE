package com.al.project.SysCo.Service;


public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}