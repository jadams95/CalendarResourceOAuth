package com.jaddy.calendarresourceoauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//

@Component
@Configuration
@PropertySource("file:..\\..\\.aws\\lavishConfProd.properties")
public class DBConfig {

    @Value("${user.dir}")
    String directory;

    @Value("${prod-env-url}")
    String url;

    @Value("${prod-env-user}")
    String user;

    @Value("${prod-env-psswrd}")
    String password;

    @Value("${prod-env-driver}")
    String driverClassName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    DataSource dataSource(){
        return new DriverManagerDataSource(url, user, password);
    }

}
