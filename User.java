package YOUTUBEV1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public abstract class User {
        //protected static File f=new File("C:\\Users\\User\\Documents\\YUUTUBE\\database.txt");
        protected static File f=new File("database");
        protected static final File f1=new File("Videos");
        protected  static String email,password, channelname;
        protected static int subs, views, vidCount;
        protected static ArrayList<Videos>uservids=new ArrayList<>();
        protected static User[] user;

        protected static final Scanner s=new Scanner(System.in);

    public User(String email, String password,String channelname) {
        this.email =email;
        this.channelname =channelname;
        subs=0;
        views=0;
        vidCount=0;
        this.password =password;
    }
    public static void getUser() throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream(f));
        user=new UserProfile[setSize()];int i=0;
        while (in.hasNext()) {
            String findmail = in.next();
            String findpw = in.next();
            String findChannel=in.next();
            user[i]=new UserProfile(findmail,findpw,findChannel);
            i++;
        }in.close();}


    public static int setSize() throws FileNotFoundException {
        int size=0;Scanner in2 = new Scanner(new FileInputStream(f));
        while (in2.hasNext()){
            in2.nextLine();size++; //count total videos in file to set array size
        }in2.close();
        return size;}

    public static void ShowUserVideos() throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream(f1));
        getUser();
        while (in.hasNext()){
            String path=in.next();
            String name=in.next();
            if(in.next().equals(channelname)){
                uservids.add(new Trending(views,0,subs,0,name,path,channelname));
                vidCount++;}
        }in.close();
        for(User user1:user)
            if(user1.getEmail().equals(email)){
                System.out.println(user1.toString());break;}
        for(Videos v:uservids){
            System.out.println(v.toString());break;}
    }
    //database structure email pw channel
    public static void Login() throws IOException {   //LOGIN SECTION WORKING
//File f=new File("database");
            int flag=0, i=0;
            System.out.println("Email: ");
            email=s.nextLine();
            System.out.println("Password: ");
            password=s.nextLine();

                Scanner in = new Scanner(new FileInputStream(f));
                while(in.hasNextLine()){
                    i++;
                    if(in.next().equals(email))
                        if(in.next().equals(password)){
                            channelname=in.next();
                            System.out.println("Login Successful!");
                            System.out.println("TRENDING NOW:");Trending.setTrending();
                            Prompt.prompt();break;
                        }
                        else{
                            System.out.println("Wrong password");break;}
                    else{
                        flag++;in.nextLine();}
                }
                in.close();
                if(flag==i) System.out.println("User doesnt exist");
        }

        public static void Register() throws IOException {  //REGISTRATION WORKS!
            Scanner s=new Scanner(System.in);
           // File f=new File("database");
                do{ System.out.println("Enter email: ");
                    email=s.nextLine();} while(validate(email));
                System.out.println("Password: ");
                password=s.nextLine();
                do{System.out.println("Channel Name: ");
                channelname = s.nextLine();}while(validate(channelname));
                FileWriter fw = new FileWriter(f, true);
                fw.write(email+" "+password+" "+ channelname+"\n");
                fw.close();
            System.out.println("REGISTRATION COMPLETE!");
            System.out.println("TRENDING NOW:");Trending.setTrending();
            Prompt.prompt();
        }

        public static boolean validate(String email) throws FileNotFoundException { //VALIDATION WORKS!!
           // File f=new File("database");
                Scanner in = new Scanner(new FileInputStream(f));
                while(in.hasNextLine()){
                    if(in.next().equals(email)){
                        System.out.println("email exist!");return true;}
                    else{
                    in.next();
                    if(in.next().equals(email)){
                        System.out.println("channel name taken!");return true;}
                    in.nextLine();
                }} in.close();
            return false;}

    //////////////////UPLOAD/////////////////////////////////////////////////////////////
    public static void upload() throws IOException {
        System.out.println("Write the file name");
        String filename=s.nextLine();
        String videoTitle=s.nextLine();
        FileWriter out=new FileWriter(f1,true);
        out.write("C:\\\\Users\\\\User\\\\Documents\\\\YUUTUBE\\\\videos\\\\"+filename+".mp4 "+videoTitle+" "+channelname+"\n");
        out.close();
        System.out.println("Video uploaded\n");}


    ///////////////////////DELETE/////////////////////////////////////////////////////////////
    public static void delete() throws IOException {
        System.out.print("Can you please write the name of the video to delete it: ");
        String name = s.nextLine();
        PrintWriter temp = new PrintWriter(new FileOutputStream(f1));
        for(Videos arr:Videos.array)
            if(!arr.getVideoTitle().equalsIgnoreCase(name))
                temp.println(arr.getPath()+" "+arr.getVideoTitle()+" "+arr.getChannel());
        System.out.println("Video deleted\n");
        temp.close();
    }


    public  String getEmail() {
        return email;
    }

    public String getChannelname() {
        return channelname;
    }

    public String getPassword() {
        return password;
    }


    public String toString() {
            return "Your Channel : "+getChannelname()+"\nEmail: "+getEmail()+"\tSubscribers: "+subs+"\tTotal Views: "+views+"\nYour Videos: ("+vidCount+")\n";


    }
}
