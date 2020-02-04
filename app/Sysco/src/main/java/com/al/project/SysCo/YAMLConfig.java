package com.al.project.SysCo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties

public class YAMLConfig {

    private String name;
    private String environment;
    private List<String> servers = new ArrayList<>();

    // standard getters and setters

}