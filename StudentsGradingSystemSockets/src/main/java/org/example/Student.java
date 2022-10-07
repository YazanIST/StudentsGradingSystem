package org.example;

import java.util.HashMap;

public class Student implements java.io.Serializable {
    private int id;
    private String password;
    private boolean authenticationStatus;

    public Student() {

    }

    public Student(int id, String password) {
        this.id = id;
        this.password = password;
        authenticationStatus = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(boolean authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }
}
