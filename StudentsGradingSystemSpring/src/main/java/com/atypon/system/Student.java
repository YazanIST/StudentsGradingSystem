package com.atypon.system;

public class Student implements java.io.Serializable {
    private int id;
    private String password;

    public Student() {
    }

    public Student(int id, String password) {
        this.id = id;
        this.password = password;
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
}
