//Brittany Dinneen
//Android Exam 1 Programming Portion
//Address Book
//Due 9/28/15
package com.example.username.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Brittany Dinneen on 9/21/2015.
 * This Database will serve as the storage for entries placed into this Address Book
 */
public class Database extends SQLiteOpenHelper{

    public Database(Context context){
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase addressBook){

        //SQL statement to create address book database
        String createTable = "CREATE TABLE if not exists addressBook ( " +
                "name TEXT, " +
                "phoneNumber TEXT, " +
                "email TEXT, " +
                "streetAddress TEXT, " +
                "cityStateZip TEXT)";

        addressBook.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase addressBook, int oldVersion, int newVersion){

    }
    //Column names in Address Book
    private static final String DB_TABLE_NAME = "addressBook";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "phoneNumber";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "streetAddress";
    private static final String KEY_CITYSTATEZIP = "cityStateZip";
    private static final String[] tableColumns = {KEY_NAME, KEY_NUMBER, KEY_EMAIL, KEY_ADDRESS, KEY_CITYSTATEZIP};

    public void addContact(ArrayList<String> contactInfo){
        //open datebase
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contact = new ContentValues();
        contact.put(KEY_NAME, contactInfo.get(0));
        contact.put(KEY_NUMBER, contactInfo.get(1));
        contact.put(KEY_EMAIL, contactInfo.get(2));
        contact.put(KEY_ADDRESS, contactInfo.get(3));
        contact.put(KEY_CITYSTATEZIP, contactInfo.get(4));

        //insert content into addressBook
        database.insert(DB_TABLE_NAME, null, contact);

        //close the database
        database.close();

    }

    public void deleteContact(ArrayList<String> contactInfo){

        String contactName = contactInfo.get(0);
        String deleteQuery = "DELETE FROM " + DB_TABLE_NAME + " WHERE "+KEY_NAME + "='"+ contactName + "';";

        SQLiteDatabase database = this.getWritableDatabase();
        //database.rawQuery(deleteQuery, null);
        database.execSQL(deleteQuery);
        database.close();

    }

    public ArrayList<String> getContactInfo(ArrayList<String> contactInfo){
        ArrayList<String> editContact = new ArrayList<String>();
        String contactName = contactInfo.get(0);
        String getQuery = "SELECT * FROM " + DB_TABLE_NAME + " WHERE " +KEY_NAME + "='" + contactName + "';";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(getQuery, null);

        ArrayList<String> row = null;
        if(cursor.moveToFirst()){
            do{
                row = new ArrayList<>();
                row.add(cursor.getString(0));
                row.add(cursor.getString(1));
                row.add(cursor.getString(2));
                row.add(cursor.getString(3));
                row.add(cursor.getString(4));

                //editContact.add(row);
            } while(cursor.moveToNext());
        }


        return row;

    }

    public ArrayList<ArrayList> getAllContacts(){
        ArrayList<ArrayList> allContacts = new ArrayList<>();

        String selectAllQuery = "SELECT * FROM " + DB_TABLE_NAME;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectAllQuery, null);

        ArrayList<String> row;
        if(cursor.moveToFirst()){
            do{
                row = new ArrayList<>();
                row.add(cursor.getString(0));
                row.add(cursor.getString(1));
                row.add(cursor.getString(2));
                row.add(cursor.getString(3));
                row.add(cursor.getString(4));

                allContacts.add(row);
            } while(cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return allContacts;
    }

}