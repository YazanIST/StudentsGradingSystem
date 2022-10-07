package org.example.server;

import org.example.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

public class ViewServer {
    private ObjectOutputStream outputToClient;
    private ObjectInputStream inputFromClient;
    private StudentsDatabaseService studentsDatabaseService;

    public ViewServer(ObjectOutputStream outputToClient,
                      ObjectInputStream inputFromClient,
                      StudentsDatabaseService studentsDatabaseService) {
        this.outputToClient = outputToClient;
        this.inputFromClient = inputFromClient;
        this.studentsDatabaseService = studentsDatabaseService;
    }

    public void view(Student student) throws IOException, SQLException, ClassNotFoundException {
        int choice = -1;

        while (choice != 0) {
            choice = inputFromClient.readInt();

            switch (choice) {
                case 1:
                    outputToClient.writeObject(studentsDatabaseService.getAllMarks(student.getId()));
                    break;

                case 2:
                    outputToClient.writeObject(studentsDatabaseService.getAllCourses());
                    break;

                case 3:
                    outputToClient.writeObject(studentsDatabaseService.getAllCourses());
                    outputToClient.flush();
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
}
