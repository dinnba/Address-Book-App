//Brittany Dinneen
//Android Exam 1 Programming Portion
//Address Book
//Due 9/28/15
/*
* This is the Application Manager
* The Manager.java class allows the sharing of data between activities/pages easily
* I used the manger class to save the context of the app, required when initiating the db instance
* I also stored the position of the contact from the listView here for easy db updates.
* */
package com.example.username.addressbook;

import android.content.Context;

/**
 * Created by Brittany Dinneen on 9/21/2015.
 */
public class Manager {

    private static Manager manager;
    private Context context;
    private int dbPosition;

    private Manager(){}

    public static Manager getInstance(){

        if(manager == null){
            manager = new Manager();
        }
        return manager;
    }

    public Context getContext(){
        return context;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void setPosition(int position){
        this.dbPosition = position;
    }

    public int getPosition() {
        return dbPosition;
    }

}