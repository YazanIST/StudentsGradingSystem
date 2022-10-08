package com.atypon.system;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class DatabaseConnector {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;

    //configure database details here
    private static DatabaseConnector soloInstance = new DatabaseConnector(
            3306,
            "student_grading_database",
            "root",
            "root"
    );

    private DatabaseConnector(int port, String databaseName, String username, String password) {
        connectToDatabase(port, databaseName, username, password);
    }

    public static DatabaseConnector getInstance() {
        return soloInstance;
    }

    private void connectToDatabase(int port, String databaseName, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + databaseName, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean hasRecord(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        return resultSet.next();
    }

    public int getSingleValue(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        resultSet.next();
        return resultSet.getInt(1);
    }

    public LinkedHashMap<String, Integer> getRow(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        resultSet.next();
        resultSetMetaData = resultSet.getMetaData();
        LinkedHashMap<String, Integer> ret = new LinkedHashMap<>();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            ret.put(resultSetMetaData.getColumnName(i), resultSet.getInt(i));
        }
        return ret;
    }

    public LinkedList<String> getColumnsNames(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        resultSetMetaData = resultSet.getMetaData();
        LinkedList<String> ret = new LinkedList<>();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            ret.add(resultSetMetaData.getColumnName(i));
        }
        return ret;
    }

    public LinkedList<Integer> getColumnValues(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        LinkedList<Integer> ret = new LinkedList<>();
        while (resultSet.next()) {
            ret.add(resultSet.getInt(1));
        }
        return ret;
    }
}