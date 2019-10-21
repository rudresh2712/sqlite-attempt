package com.example.folder2021;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.folder2021.data.DatabaseHandler;
import com.example.folder2021.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db=new DatabaseHandler(MainActivity.this);


        //create contact object

        Contact jeremy=new Contact();
        jeremy.setName("jeremy");
        jeremy.setPhone_number("54686513");

        Contact json = new Contact();
        json.setName("json");
        json.setPhone_number("8795546887");


        // to display just the one contact

//        Contact c = db.getContact(2);
//        c.setName("newJeremy");
//        c.setPhone_number("84516843");

//        int update= db.update(c);
//        Log.d("row","onCreate"+update);


        Log.d("MainActivity",c.getName()+","+c.getPhone_number());
      //  db.deleteContact(c);


        // contact list here
        List<Contact> contactList=db.getAllContacts();
        for(Contact contact: contactList){                   //this is a for each loop
            Log.d("MainActivity","onCreate"+contact.getId());
//
//        }



    }
}
