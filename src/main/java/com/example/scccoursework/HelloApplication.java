package com.example.scccoursework;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Scanner;

@ApplicationPath("/api")
public class HelloApplication extends Application {
     class Main {
        public void main(String[] args) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Enter userID: ");

            String userID = userInput.nextLine();
            System.out.println("Username is: " + userID);
        }
    }
}