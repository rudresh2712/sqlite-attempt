package com.example.folder2021.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.folder2021.R;
import com.example.folder2021.Util.Util;
import com.example.folder2021.model.Contact;

import java.util.ArrayList;
import java.util.List;

import static com.example.folder2021.R.*;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME,null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {




        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"                //this line shows error runtime
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACT_TABLE);   //executec sequel

        //creating our table

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        String DROP_TABLE=String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE,new String[]{Util.DATABASE_NAME});

        //the table is gone after the above command
        // so we need to create new table aso we call the earlier again

        onCreate(db);
    }

    /*CRUD operations = CREATE<READ <*/

    public void  addContact(Contact contact)      // pass the contact to be added
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhone_number());

        db.insert(Util.TABLE_NAME,null,values);
        Log.d("DBHandler","adding"+"item added");

        //since the table is open after this command so to prevent memory leak close it
        db.close();

    }

    //get a contact not "add" a contact

    //create a function that returns a contact
    // this is the reading part of the table

    public Contact getContact(int id){
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor=db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_PHONE_NUMBER},                //probably the table name and the column names are being passed in which the contact is to be searched
                Util.KEY_ID+"=?",new String[]{String.valueOf(id)},
                null,null,null);
                                                                                      //the second util.key id is to be received asking the user

        if(cursor!=null)
             cursor.moveToFirst();

        Contact contact = new Contact("jojojojo","4568781");
  //          assert cursor != null;             //this line helped in removing null point exception
//     contact.setId((Integer.parseInt(cursor.getString(0)));
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhone_number(cursor.getString(2));

        return  contact;

    }



    //get all contacts
    //for this we ar going to need an array list

    public List<Contact> getAllContacts(){

        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();


        //copied from
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact("James", "213986");
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone_number(cursor.getString(2));

                //add contact objects to our list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }

        return contactList;
    }

    //update
    public int update(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhone_number());


        // here we are passing the table name and the content values to be updated
        return db.update(Util.TABLE_NAME,values,Util.KEY_ID+"=?",
               new String[]{String.valueOf(contact.getId())} );

        // "=?" this implies it has to be given as an input

    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();

        db.delete(Util.TABLE_NAME,Util.KEY_ID+"=?",
                new String[]{String.valueOf(contact.getId())});

        db.close();

    }
}
