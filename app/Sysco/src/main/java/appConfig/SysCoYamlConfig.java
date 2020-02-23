package appConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties

public class SysCoYamlConfig {

    private final Map<String, Server> servers = new HashMap<>();
    private final Map<String, Login> logins = new HashMap<>();

    public Map<String, Server> getServers() {
        return this.servers;
    }
    public Map<String, Login>getLogins() {
        return this.logins;
    }


    public static class Server{
        private String name;
        private String address;
        private Integer port;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIp() {
            return address;
        }

        public Integer getPort() {
            return port;
        }

        public void setIp(String address) {
            this.address = address;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        @Override
        public String toString() {
            return "Server{" +
                    ", ip='" + address + '\'' +
                    ", port='" + port + '\'' +
                    '}';
        }
    }


    public static class Login
    {
        private String name;
        private String username;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }


        public void setPassword(String password) {
              this.password = password;
        }

        @Override
        public String toString() {
            return "Server{" +
                    "name='" + name + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    // standard getters and setters
}