package YOUTUBEV1;

import java.io.IOException;
import java.util.Scanner;

public class Search extends Videos{
    public Search(int views, int likes, int subs, int dislikes, String videoTitle, String path,String channel) {
        super(views, likes, subs, dislikes, videoTitle, path, channel);
    }

    public static void search() throws IOException {
        int flag=0;getVideos();
        System.out.println("Type your query:");
        Scanner in=new Scanner(System.in); String q=in.nextLine();
        for(Videos arr:array)
            if(arr.getVideoTitle().toLowerCase().contains(q.toLowerCase())||arr.getChannel().toLowerCase().contains(q.toLowerCase())) {
                //User can search with video title or channel
                System.out.println(arr.toString());
            }else
                flag++;
        if(flag==array.length)
            System.out.println("No match found");
    }
}
