package org.example.client;

import org.example.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class LoginService {
    private Scanner scanner;
    private ObjectOutputStream outputToServer;
    private ObjectInputStream inputFromServer;

    public LoginService(Scanner scanner,
                        ObjectOutputStream outputToServer,
                        ObjectInputStream inputFromServer) {
        this.scanner = scanner;
        this.outputToServer = outputToServer;
        this.inputFromServer = inputFromServer;
    }

    public Student login() throws IOException {
        Student newStudent = null;
        Boolean authenticationStatus = false;

        while (authenticationStatus == false) {
            if (newStudent != null) {
                // not the first try
                System.out.println("Invalid Credentials :(");
            }

            System.out.println("Enter ID: ");
            int id = scanner.nextInt();
            System.out.println("Enter Password: ");
            String password = scanner.next();

            newStudent = new Student(id, password);
            outputToServer.writeObject(newStudent);
            outputToServer.flush();

            authenticationStatus = inputFromServer.readBoolean();
        }

        return newStudent;
    }
}
