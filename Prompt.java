package YOUTUBEV1;

import java.io.IOException;
import java.util.Scanner;

import static YOUTUBEV1.Videos.VideoPrompt;

public class Prompt {

    public static void prompt() throws IOException {
          while (true){
              Scanner in=new Scanner(System.in);
                System.out.println("""
                        What do you want to do (type the number of your choice):
                        1-Search
                        2-Show videos
                        3-Video Ops
                        4- Your Channel
                        5- Upload Videos
                        6- Delete Videos
                        7- Change password
                        8- Change Channel name
                        9- Close Account
                        10- Logout""");
                int choice = in.nextInt();
                switch (choice) {
                    case 1 -> Search.search();
                    case 2 -> Videos.showVideos();
                    case 3 -> VideoPrompt();
                    case 4 -> User.ShowUserVideos();
                    case 5 -> User.upload();
                    case 6 -> User.delete();
                   /* case 7 -> UserProfile.Change();
                    case 8 -> UserProfile.Change();
                    case 9 -> UserProfile.Change();*/
                    case 10 -> { System.out.println("Thank you for using Yuutube!");System.exit(0);}
                    default -> throw new IllegalStateException("Unexpected value: " + choice);}
                }
        }
    }