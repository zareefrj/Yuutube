package YOUTUBEV1;

import java.io.FileNotFoundException;

public class Trending extends Videos {

    public Trending(int views, int likes, int subs, int dislikes, String videoTitle, String path,String channel) {
        super(views, likes, subs, dislikes, videoTitle, path, channel);
    }

    public static void setTrending() throws FileNotFoundException {

        getVideos();//to read all video list into an array
        for (int pass = 0; pass < array.length - 1; pass++)
            for (int j = 0; j < array.length - pass - 1; j++)//get views to arrange
                if (array[j].getViews() < array[j + 1].getViews()) {
                    Videos hold = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = hold;
                }

        for(int x=1,y=0;x< array.length && y<5;x++,y++)//printing the objects' names and views
            System.out.println(x+": "+array[y].getVideoTitle()+" by "+array[y].getChannel()+" "+array[y].getViews()+" views");
        System.out.println();
    }
}