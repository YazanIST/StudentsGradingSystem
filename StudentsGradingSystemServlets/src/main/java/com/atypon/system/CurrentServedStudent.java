package com.atypon.system;

import java.sql.SQLException;

public class CurrentServedStudent {
    private static StudentsDatabaseService studentsDatabaseService = new StudentsDatabaseService();
    private static boolean authenticationStatus;
    private static int studentId;

    public static void reset() {
        authenticationStatus = false;
        studentId = -1;
    }

    public static boolean isAuthenticated() {
        return authenticationStatus;
    }

    public static Integer getStudentId() {
        if (authenticationStatus) {
            return studentId;
        } else return null;
    }

    public static void authenticate(Student student) throws SQLException {
        if (studentsDatabaseService.authenticateStudent(student)) {
            studentId = student.getId();
            authenticationStatus = true;
        } else {
            authenticationStatus = false;
        }
    }
}
