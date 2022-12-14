package util;

import android.app.Application;

public class JournalUser extends Application {
    private  String username;
    private  String userID;

    private static  JournalUser instance;
    public static JournalUser getInstance(){
        if(instance == null){
            instance=new JournalUser();
        }
        return  instance;
    }
    public JournalUser(){

    }
    public String getUsername(){
        return username;
    }
    public String getUserID(){
        return userID;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setUserID(String userID){this.userID= userID;}

}
