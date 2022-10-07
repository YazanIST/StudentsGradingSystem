package org.example.client;

import org.example.Student;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        ObjectOutputStream outputToServer = null;
        ObjectInputStream inputFromServer = null;
        LoginService loginService = null;
        ViewService viewService = null;

        try {
            socket = new Socket("localhost", 8080);
            outputToServer = new ObjectOutputStream(socket.getOutputStream());
            inputFromServer = new ObjectInputStream(socket.getInputStream());
            loginService = new LoginService(scanner, outputToServer, inputFromServer);
            viewService = new ViewService(scanner, outputToServer, inputFromServer);
        } catch (Exception e) {
            System.out.println(e);
        }

        while (true) {
            Student newStudent = loginService.login();

            System.out.println("\nWelcome " + newStudent.getId() + "!!\n");

            viewService.view();
        }
    }
}
