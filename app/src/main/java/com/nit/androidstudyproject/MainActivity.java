package com.nit.androidstudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {
    public static final String MYPREFS = "mySharedPreferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveDataInFile(View v){
        String FILENAME = "MyData.txt";
        String myData = "My name is Sam";

            FileOutputStream fos = null;
            try {
                fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                // Write data to the file
                fos.write(myData.getBytes());

            // close the stream
                fos.close();
                Toast.makeText(this, " saved", Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }




    }

    public void readFileData(View v){
        String FILENAME = "MyData.txt";
        try{
            FileInputStream fis = openFileInput(FILENAME);

            byte[] reader = new byte[fis.available()];

            if (fis.read(reader)!=-1)
            {
                String myData = new String(reader);
                Toast.makeText(this, myData, Toast.LENGTH_SHORT).show();
            }

            fis.close();
        }
        catch(Exception ex)
        {
            Log.e("Exception", ex.toString());
        }

    }

    public void checkMedia(View V){
        boolean media_available = false;
        boolean media_writeable = false;

// Retrieve the state of the SD card
        String State = Environment.getExternalStorageState();

// Check if the SD card is mounted
if(Environment.MEDIA_MOUNTED.equals(State)) {

// We can read and write data on the media
media_available = media_writeable = true;
    }

    // Check if the SD card is mounted but in read-only mode
        else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(State))
    {



    }
else
    {

// We can only read data from the media
media_available = true; media_writeable = false;

        /* Something else is wrong. It may be one of the other states, you can neither read nor write on the media.*/

        media_available = media_writeable = false;
    }

}

public void writeExternal(View v){

    String MYFILE = "MyData.txt"; String myData = "My name is Sam from external";
// Open the file
    try {
        FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "//" + MYFILE);

// Write data to the file
        fos.write(myData.getBytes());
        fos.close();
    }catch (Exception e){
        e.printStackTrace();
    }

}

public void readExternal(View v){
    String MYFILE = "MyData.txt";
    try {
        FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory() + "//" + MYFILE);
        byte[] reader = new byte[fis.available()];
        while (fis.read(reader) != -1) {
        }
        String mData = (new String(reader));
        Log.i("Data", mData);
        Toast.makeText(this, mData, Toast.LENGTH_SHORT).show();

        fis.close();
    }catch (Exception e){
        e.printStackTrace();
    }

}
// Method to save preferences
public void savePref(View V){

// Create or retrieve the shared preference object.
int mode = Activity.MODE_PRIVATE;


    SharedPreferences mySharedPreferences =   getSharedPreferences(MYPREFS, mode);

// Use an editor to modify the shared preferences.
SharedPreferences.Editor edit = mySharedPreferences.edit();
// Store primitive types in the shared preferences object. edit.putBoolean("Flag", true);
edit.putFloat("Float", 3f); edit.putInt("wholeNum", 2); edit.putLong("aNum", 29); edit.putString("Value", "Hello");

// Commits the changes.
edit.commit();
}

    public void loadPref(View v)
    {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mySharedPreferences = getSharedPreferences(MYPREFS, mode);

// To retrieve the saved values
        boolean mFlag = mySharedPreferences.getBoolean("Flag", false);
        float mFloat = mySharedPreferences.getFloat("Float", 0f);
        int wholeNum = mySharedPreferences.getInt("wholeNum", 1);
        long mNum = mySharedPreferences.getLong("aNum", 0);
        String mPreference;
        mPreference = mySharedPreferences.getString("Value","def");
        Toast.makeText(this, "Pref Data "+mFlag + mFloat+ mPreference, Toast.LENGTH_SHORT).show();
    }

    public void checkremote(View v){
        String s = server_data("eur","usd","1");
        Toast.makeText(this,"Data Check "+ s,  Toast.LENGTH_SHORT).show();
    }

    // Define a method for accessing the Web component.
    public String server_data(String base, String target, String cur_value)
    {
        String value = null;
        String myString = "";

        try {


// Defines the URL for the component.
// These parameters vary depending upon the Web component/service being used.

            URL myURL = new
                    URL("http://xurrency.com/api/"+base+"/"+target+"/"+cur_value);

// Open a connection to that URL for reading data.
            URLConnection ucon = myURL.openConnection();

// Defines input streams to read from the URLConnection.
InputStream is = ucon.getInputStream();
BufferedInputStream bis = new BufferedInputStream(is);


           ByteArrayOutputStream baf = new ByteArrayOutputStream(50);

            int current = 0;
// Read bytes to the buffer until there is nothing more
// to read (-1).
            while((current = bis.read()) != -1)
            {
                baf.write((byte)current);
            }

// Convert the bytes read to aString
myString = new String(baf.toByteArray());

        } catch (Exception e) {
            // Display if an error occurs.
            myString = e.getMessage();
            Log.e("Error","Check"+ myString);
            myString ="";
        }

        /* Check if the component returned a positive value, which is specified by "status = ok" */

        if(myString.contains("\"status\":\"ok\""))
        {

// Extract the converted value from the returned string.
value=myString.substring(myString.indexOf("value\":")+7, myString.indexOf(",\"target"));
        }

        else if(myString.contains("\"status\":\"fail\""))
        {
            value="Error retrieving data from server";
        }

/* Send the converted value to the main (calling)
activity */

        return value;
    }


}