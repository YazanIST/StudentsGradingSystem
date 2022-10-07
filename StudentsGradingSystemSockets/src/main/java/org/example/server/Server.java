package org.example.server;

import org.example.Student;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        new Thread(() -> {
            try {
                StudentsDatabaseService studentsDatabaseService = new StudentsDatabaseService();
                ServerSocket serverSocket = new ServerSocket(8080);
                Socket socket = serverSocket.accept();
                ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
                LoginServer loginServer = new LoginServer(outputToClient, inputFromClient, studentsDatabaseService);
                ViewServer viewServer = new ViewServer(outputToClient, inputFromClient, studentsDatabaseService);

                while (true) {
                    Student student = loginServer.getStudent();

                    System.out.println("Authenticated Student");

                    viewServer.view(student);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();
    }
}
