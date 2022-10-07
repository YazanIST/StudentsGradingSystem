package org.example;

import java.sql.*;
import java.util.*;

public class DatabaseConnector {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;

    DatabaseConnector(int port, String databaseName, String username, String password) {
        connectToDatabase(port, databaseName, username, password);
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

//    public Integer[][] displayTable() throws SQLException, ClassNotFoundException {
//        while (resultSet.next()) {
//            System.out.println("ID " + resultSet.getInt(1) + "/ Name: " + resultSet.getString(2));
//        }
//    }
}