//Brittany Dinneen and Keith Hubbard
//Android Final Project
//Address Book
//Due 12/04/2015

package com.example.username.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewContactActivity extends AppCompatActivity {

    private int position;
    private Button backContactButton;
    private TextView nameDescription;
    private TextView phoneDescription;
    private TextView emailDescription;
    private TextView streetDescription;
    private TextView zipDescription;
    private ArrayList<String> contactInfo = new ArrayList<>();
    private ArrayList <ArrayList> contact = new ArrayList<>();
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        db = new Database(Manager.getInstance().getContext());

        position = Manager.getInstance().getPosition();
        /*Log.d("POSITION", position + "");//Test print*/


        contact = db.getAllContacts();//get all stored db contacts

        contactInfo = db.getContactInfo(contact.get(position));

        nameDescription = (TextView) findViewById(R.id.nameView);
        nameDescription.setText(contactInfo.get(0));

        phoneDescription = (TextView) findViewById(R.id.phoneView);
        phoneDescription.setText(contactInfo.get(1));

        emailDescription = (TextView) findViewById(R.id.emailView);
        emailDescription.setText(contactInfo.get(2));

        streetDescription = (TextView) findViewById(R.id.streetView);
        streetDescription.setText(contactInfo.get(3));

        zipDescription = (TextView) findViewById(R.id.zipView);
        zipDescription.setText(contactInfo.get(4));

        backContactButton = (Button)findViewById(R.id.save_contact_button);
        backContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewContactActivity.this, MainActivity.class);
                startActivityForResult(i, 0);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}