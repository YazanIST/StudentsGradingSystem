package com.atypon.system;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class StudentsDatabaseService {
    final private DatabaseConnector databaseConnector;
    final private String gradesTableName, infoTableName;

    // to configure by database details
    public StudentsDatabaseService() {
        databaseConnector = DatabaseConnector.getInstance();
        infoTableName = "students_login_info";
        gradesTableName = "students_grades";
    }

    public boolean isIdExist(int id) throws SQLException {
        return databaseConnector.hasRecord("Select 1 from " + infoTableName + " where id = " + id);
    }

    public boolean authenticateStudent(Student student) throws SQLException {
        return databaseConnector.hasRecord(
                "Select 1 from " + infoTableName +
                        " where id = " + student.getId() + " and password = " + student.getPassword()
        );
    }

    public LinkedList<String> getAllCourses() throws SQLException {
        LinkedList<String> ret = databaseConnector.getColumnsNames("Select * from " + gradesTableName);
        ret.remove("id");
        return ret;
    }

    public LinkedHashMap<String, Integer> getAllMarks(int id) throws SQLException {
        LinkedHashMap<String, Integer> ret = databaseConnector.getRow(
                "Select * from " + gradesTableName + " where id = " + id
        );
        ret.remove("id");
        return ret;
    }

    public LinkedHashMap<String, Integer> getCourseInfo(String courseName) throws SQLException {
        LinkedHashMap<String, Integer> ret = new LinkedHashMap<>();
        ret.put(
                "min_mark",
                databaseConnector.getSingleValue("Select min(" + courseName + ") from " + gradesTableName)
        );
        ret.put(
                "avg_mark",
                databaseConnector.getSingleValue("Select avg(" + courseName + ") from " + gradesTableName)
        );
        ret.put(
                "max_mark",
                databaseConnector.getSingleValue("Select max(" + courseName + ") from " + gradesTableName)
        );

        LinkedList<Integer> allMarks = databaseConnector.getColumnValues(
                "Select " + courseName + " from " + gradesTableName
        );
        Collections.sort(allMarks);
        int middle = allMarks.size() / 2;
        ret.put(
                "median_mark",
                (allMarks.size() % 2 == 0 ?
                        (allMarks.get(middle) + allMarks.get(middle + 1)) / 2 : allMarks.get(middle))
        );

        return ret;
    }

    public boolean checkCourseForId(String courseName, int id) throws SQLException {
        if (getAllCourses().contains(courseName)) {
            return databaseConnector.hasRecord(
                    "Select 1 from " + gradesTableName +
                            " where id = " + id + " and " + courseName + " is not null"
            );
        }
        return false;
    }
}
