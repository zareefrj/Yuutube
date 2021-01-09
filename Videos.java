package YOUTUBEV1;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Videos {
    protected  int views, likes, subs, dislikes;
    protected   String videoTitle, path, channel;
    protected  ArrayList<String> ar = new ArrayList<>();//comment section
    //protected static File f=new File("C:\\Users\\User\\Documents\\YUUTUBE\\videos.txt");
    protected static File f=new File("Videos");
    protected static Videos[] array;

    public Videos(int views,int likes,int subs,int dislikes,String videoTitle,String path,String channel) {
        this.dislikes = dislikes;
        this.likes = likes;
        this.subs = subs;
        this.views = views;
        this.videoTitle = videoTitle;
        this.path = path;
        this.channel=channel;
    }
    public void play() throws IOException {//code to play video
        System.out.println(videoTitle+" is playing....");
        { Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+path);}
    }
    public static void getVideos() throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream(f));
        Random r=new Random();int i = 0;
//TXT FILE STRUCTURE: path name channel
        array=new Videos[setSize()];
        while (in.hasNext()) {
            String findPath = in.next();
            String findName = in.next();
            String findChannel=in.next();
            int findViews = r.nextInt(1_000_000);
            int findLikes = r.nextInt(1_000_000);
            int findDisLikes = r.nextInt(1_000_000);
            int findSubscribe = r.nextInt(1_000_000);
            array[i]=new Trending(findViews, findLikes, findSubscribe, findDisLikes, findName, findPath, findChannel);
            i++;
        }in.close();}

    public static int setSize() throws FileNotFoundException {
        int size=0;Scanner in2 = new Scanner(new FileInputStream(f));
        while (in2.hasNext()){
            in2.nextLine();size++; //count total videos in file to set array size
        }in2.close();
        return size;}

    public static void showVideos() throws FileNotFoundException {
        getVideos();
        for(Videos vids:array)
            System.out.println(vids.toString());
    }

    public static void VideoPrompt() throws IOException {
        Scanner in=new Scanner(System.in);int flag=0,i=0;
        System.out.println("Type the video title to select");
        String s= in.nextLine();getVideos();
        for(Videos a: array){
            i++;
            if (a.getVideoTitle().equalsIgnoreCase(s)) {
                System.out.println("P: Play  L: Like  D: Dislike C: Comment S: Subscribe");
                String op = in.nextLine().toUpperCase();
                switch (op) {
                    case "P" -> {a.play();System.out.println(a.toString());}
                    case "L" -> {a.setLikes(a.likes++);System.out.println("you just liked "+a.getVideoTitle()+"\n"+a.toString());}
                    case "D" -> {a.setDislikes(a.dislikes++);System.out.println("you just disliked "+a.getVideoTitle()+"\n"+a.toString());}
                    case "C" -> {
                        System.out.println("Comment: ");String comment=in.nextLine();a.setAr(comment);
                        System.out.println("you just commented on "+a.getVideoTitle()+"\n"+a.toString());}
                    case "S" -> {a.setSubs(a.subs++);System.out.println("you just subscribed to "+a.getChannel()+"\n"+a.toString());}
                    default -> throw new IllegalStateException("Unexpected value: " + op);
                }
            }else flag++;}
            if(flag==i) System.out.println("No Video Exist");
    }
    public void setAr(String comment) {
        ar.add(comment);
    }

    public ArrayList<String> getAr() {
        return ar;
    }

    public void setDislikes(int dislikes) {
                  this.dislikes = dislikes;
              }

    public void setLikes(int likes) {
                  this.likes = likes;
              }

              public String getPath() {
                  return path;
              }

    public String getVideoTitle() {
                  return videoTitle;
              }

    public void setSubs(int subs) {
                  this.subs = subs;
              }

    public int getViews() {
                  return views;
              }

    public String getChannel() {
                  return channel;
              }

    @Override
              public String toString() {
                  return videoTitle+" :"+channel+"\nViews:"+views+"\t\tLikes:"+likes+"\t\tDislikes:"+dislikes+"\t\tSubscribers:"+subs+
                          "\t\tComments:"+getAr()+"\n";
              }
          }