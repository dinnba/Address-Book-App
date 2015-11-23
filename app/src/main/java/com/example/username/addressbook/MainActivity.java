//Brittany Dinneen and Keith Hubbard
//Android Final Project
//Address Book
//Due 12/04/2015
/*
* This is the Main  Activity/Start Screen of the Address Book App
* It contacts a ListView of all the Names of the contacts stored
* To add a contact, click the plus/add icon at the top of the screen
* To view a contact information click the contact name
* To Edit/Delete a contact long-click the contact name
* */

package com.example.username.addressbook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Database db;
    private ArrayList<ArrayList> contacts;
    private ArrayAdapter<String> aa;
    private ArrayList<String> contactNames = new ArrayList<String>();
    ArrayList<String> listNames = new ArrayList<>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the context of the application for the database
        Context context = this.getApplicationContext();
        Manager.getInstance().setContext(context);
        db = new Database(Manager.getInstance().getContext());

        reloadContactList();//load contact data from DB to list
        mListView = (ListView) findViewById(R.id.list_view);
        //onItemClickListener when user touches a name on list
        mListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Manager.getInstance().setPosition(position);
                Intent viewIntent = new Intent(MainActivity.this, ViewContactActivity.class);
                startActivityForResult(viewIntent, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            //this will send the user to the AddContactActivity when clicked
            Intent i = new Intent(MainActivity.this, AddContactActivity.class);
            startActivityForResult(i, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.list_item_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = info.position;


        switch(item.getItemId()) {
            case R.id.menu_item_delete:
                //this is where you can delete a contact
                AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                      .setTitle("Are you sure?")
                        .setMessage("This will permanently delete the contact")
                            .setNegativeButton("Cancel", null)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ArrayList < ArrayList > allContacts = new ArrayList<>();
                                        allContacts = db.getAllContacts();//get all stored db contacts
                                        db.deleteContact(allContacts.get(position));
                                        contacts.clear();
                                        contactNames.clear();
                                        listNames.clear();
                                        reloadContactList();
                                    }
                                });
                dialog.show();

                return true;
            case R.id.menu_item_edit:
                //this is where you can edit a contact
                Manager.getInstance().setPosition(position);
                Intent editIntent = new Intent(MainActivity.this, EditContactActivity.class);
                startActivityForResult(editIntent, 0);

                return true;
        }
        return super.onContextItemSelected(item);
    }

    public void reloadContactList(){

        //grab the database instance so this activity can access
        contacts = db.getAllContacts();//get all stored db contacts

        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNames);
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(aa);
        //register th context menu for the ListView
        registerForContextMenu(mListView);

        for(int i=0; i<contacts.size(); i++){

            contactNames = contacts.get(i);
            listNames.add(contactNames.get(0));//add Names to be viewed by user in list
            aa.notifyDataSetChanged();//notify ArrayAdapter the change has been made to update the list
        }
        //Log.d("Names: ", listNames.toString());
    }
}