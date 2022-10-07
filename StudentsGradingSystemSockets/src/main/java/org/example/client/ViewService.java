package org.example.client;

import org.example.Printer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class ViewService {
    private Scanner scanner;
    private ObjectOutputStream outputToServer;
    private ObjectInputStream inputFromServer;

    public ViewService(Scanner scanner,
                       ObjectOutputStream outputToServer,
                       ObjectInputStream inputFromServer) {
        this.scanner = scanner;
        this.outputToServer = outputToServer;
        this.inputFromServer = inputFromServer;
    }

    public void view() throws IOException, ClassNotFoundException {
        int choice = -1;

        while (choice != 0) {
            Printer.printCommands();

            System.out.println("Your choice: ");
            choice = scanner.nextInt();

            outputToServer.writeInt(choice);
            outputToServer.flush();

            System.out.println("\n\n");

            switch (choice) {
                case 1:
                    Printer.print((LinkedHashMap<String, Integer>) inputFromServer.readObject());
                    break;
                case 2:
                    System.out.println("Courses: ");
                    System.out.println(inputFromServer.readObject());
                    break;
                case 3:
                    System.out.println("Available courses: ");
                    System.out.println(inputFromServer.readObject());

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
                    System.out.println("Goodbye :)");
                    break;
                default:
                    System.out.println("Invalid Input:(");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }
}
