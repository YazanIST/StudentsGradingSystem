package org.example.server;

import org.example.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

public class LoginServer {
    private ObjectOutputStream outputToClient;
    private ObjectInputStream inputFromClient;
    private StudentsDatabaseService studentsDatabaseService;

    public LoginServer(ObjectOutputStream outputToClient, ObjectInputStream inputFromClient,
                       StudentsDatabaseService studentsDatabaseService) {
        this.outputToClient = outputToClient;
        this.inputFromClient = inputFromClient;
        this.studentsDatabaseService = studentsDatabaseService;
    }

    public Student getStudent() throws IOException, ClassNotFoundException, SQLException {
        Boolean authenticationStatus = false;
        Student newStudent = null;

        while (authenticationStatus == false) {
            newStudent = (Student) inputFromClient.readObject();
            authenticationStatus = studentsDatabaseService.authenticateStudent(newStudent);
            outputToClient.writeBoolean(authenticationStatus);
            outputToClient.flush();
        }

        return newStudent;
    }
}
