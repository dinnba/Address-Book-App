//Brittany Dinneen and Keith Hubbard
//Android Final Project
//Address Book
//Due 12/04/2015
package com.example.username.addressbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddContactActivity extends AppCompatActivity {

    private ArrayList<String> contactInfo = new ArrayList<>();
    private Button saveContactButton;
    private EditText txtDescription;
    private Database db;
    private String nameString = "";
    private String phoneNumberString = "";
    private String emailString = "";
    private String streetString = "";
    private String cityString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        db = new Database(Manager.getInstance().getContext());


        saveContactButton = (Button)findViewById(R.id.save_contact_button);
        saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Store Name Info
                txtDescription = (EditText)findViewById(R.id.nameText);
                nameString += txtDescription.getText().toString();
                contactInfo.add(0, nameString);
                Log.d("Name: ", nameString);

                //Store Phone number info
                txtDescription = (EditText)findViewById(R.id.phoneText);
                phoneNumberString += txtDescription.getText().toString();
                contactInfo.add(1, phoneNumberString);
                Log.d("Phone: ", phoneNumberString);

                //Store E-Mail
                txtDescription = (EditText)findViewById(R.id.emailText);
                emailString += txtDescription.getText().toString();
                contactInfo.add(2, emailString);
                Log.d("Email: ", emailString);

                //Store Street Address
                txtDescription = (EditText)findViewById(R.id.streetText);
                streetString += txtDescription.getText().toString();
                contactInfo.add(3, streetString);
                Log.d("Street: ", streetString);

                //Store City/State/Zip
                txtDescription = (EditText)findViewById(R.id.cityStateZipText);
                cityString += txtDescription.getText().toString();
                contactInfo.add(4, cityString);
                Log.d("City/State/Zip: ", cityString);

                db.addContact(contactInfo);

                Log.d("CONTACTS: ", db.getAllContacts().size() + "");

                Intent i = new Intent(AddContactActivity.this, MainActivity.class);
                startActivityForResult(i, 0);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
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
