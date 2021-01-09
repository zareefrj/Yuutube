package YOUTUBEV1;

import java.io.IOException;
import java.util.Scanner;

public class Main {
//make sure text cursor at new line
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to YuuTube\nL: Login R: Register");
        Scanner in = new Scanner(System.in);
        String input=in.nextLine();
        switch (input){
            case "L" -> User.Login();
            case "R" -> User.Register();
            default -> System.out.println("Invalid answer");
        }

    }



    }