package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        StudentsDatabaseService studentsDatabaseService = new StudentsDatabaseService();

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8080);
                Socket socket = serverSocket.accept();
                ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());

                while (true) {
                    Student student = null;
                    Boolean authenticationStatus = false;
                    while (authenticationStatus == false) {
                        student = (Student) inputFromClient.readObject();
                        authenticationStatus = studentsDatabaseService.authenticateStudent(student);
                        outputToClient.writeBoolean(authenticationStatus);
                        outputToClient.flush();
                    }

                    System.out.println("Authenticated Student");

                    int choice = -1;
                    while (choice != 0) {
                        choice = inputFromClient.readInt();
                        switch (choice) {
                            case 1:
                                outputToClient.writeObject(studentsDatabaseService.getAllMarks(student.getId()));
//                                outputToClient.flush();
                                break;
                            case 2:
                                outputToClient.writeObject(studentsDatabaseService.getAllCourses());
//                                outputToClient.flush();
                                break;
                            case 3:
                                String courseName = (String) inputFromClient.readObject();
                                if (studentsDatabaseService.checkCourseForId(courseName, student.getId())) {
                                    outputToClient.writeObject(studentsDatabaseService.getCourseInfo(courseName));
                                } else {
                                    outputToClient.writeObject(null);
                                }
                                break;
                            case 0:
                                System.out.println("exit");
                                break;
                            default:
                                System.out.println("Invalid Input:(");
                        }
                        outputToClient.flush();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();
    }
}
