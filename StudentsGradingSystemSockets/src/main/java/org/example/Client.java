package org.example;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        ObjectOutputStream outputToServer = null;
        ObjectInputStream inputFromServer = null;

        try {
            socket = new Socket("localhost", 8080);
            outputToServer = new ObjectOutputStream(socket.getOutputStream());
            inputFromServer = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println(e);
        }

        while (true) {
            Student newStudent = null;
            Boolean authenticationStatus = false;

            while (authenticationStatus == false) {
                if (newStudent != null) {
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

            System.out.println("Welcome " + newStudent.getId() + "!!");

            int choice = -1;
            while (choice != 0) {
                Printer.printCommands();
                System.out.println("Your choice: ");
                choice = scanner.nextInt();
                outputToServer.writeInt(choice);
                outputToServer.flush();
                switch (choice) {
                    case 1:
                        Printer.print((LinkedHashMap<String, Integer>) inputFromServer.readObject());
                        break;
                    case 2:
                        System.out.println(inputFromServer.readObject());
                        break;
                    case 3:
                        System.out.println("a course info");
                        System.out.println("Enter the course name");
                        String courseName = scanner.next();
                        outputToServer.writeObject(courseName);
                        outputToServer.flush();
                        Object info = inputFromServer.readObject();
                        if (info == null) {
                            System.out.println("Course name does not exist :(");
                        } else {
                            Printer.print((LinkedHashMap<String, Integer>) info);
                        }
                        break;
                    case 0:
                        System.out.println("exit");
                        break;
                    default:
                        System.out.println("Invalid Input:(");
                }
            }
        }
    }
}
