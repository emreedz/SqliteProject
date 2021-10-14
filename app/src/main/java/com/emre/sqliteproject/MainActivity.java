package com.emre.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            SQLiteDatabase db = openOrCreateDatabase("Footballers", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS footballers(id INTEGER PRIMARY KEY,name VARCHAR,age INT)");

//            db.execSQL("INSERT INTO footballers(name,age) VALUES ('Cristiano',37)");
//            db.execSQL("INSERT INTO footballers(name,age) VALUES ('Messi',34)");
//            db.execSQL("INSERT INTO footballers(name,age) VALUES ('Neymar',29)");

            //Update islemleri
            db.execSQL("UPDATE footballers SET age=31 WHERE name='Neymar'");
            db.execSQL("UPDATE footballers SET name='Cristiano Ronaldo' WHERE name='Cristiano'");
            db.execSQL("UPDATE footballers SET name='Leo Messi' WHERE name='Messi'");
            db.execSQL("UPDATE footballers SET name='Neymar JR' WHERE id=3");

            //Delete islem
            //db.execSQL("DELETE FROM footballers WHERE id=2");

            //Cursor cursor = db.rawQuery("SELECT * FROM footballers WHERE age > 30", null); //Filtreleme için
           // Cursor cursor = db.rawQuery("SELECT * FROM footballers WHERE name LIKE '%o'", null); // Sonu o ile bitenleri getirir
            //Cursor cursor = db.rawQuery("SELECT * FROM footballers WHERE name LIKE 'N%'", null); //N ile başlayanları getirir.
            Cursor cursor = db.rawQuery("SELECT * FROM footballers", null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            int idIndex = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {
                System.out.println("ID: " + cursor.getString(idIndex));
                System.out.println("Name: " + cursor.getString(nameIndex));
                System.out.println("Age: " + cursor.getString(ageIndex));


            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}