package com.nit.androidstudyproject;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView txt;
    EditText etxt;
    TextView text;
    private Button display;
    final String [] items=new String
            []{"RED","PINK","BLUE","AQUA","WHITE","BLACK"};

    private int year;
    private int month;
    private int day;

    static final int DateId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Variable initialization

        btn = (Button) findViewById(R.id.button1);
        txt = (TextView) findViewById(R.id.textView1);
        text = (TextView) findViewById(R.id.text);
        etxt = (EditText) findViewById(R.id.editText1);
        display=(Button)findViewById(R.id.display);


        final Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

// Listener for button on main.xml
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String str = "" + String.valueOf(etxt.getText());

                /* Obtain a reference to the view that describes the layout of the toast */
                View ToastView = getCustomToastView(R.mipmap.ic_launcher, "Test Application", "Hello! " + str);

// Create a new Toast object

                Toast toast = new Toast(getApplicationContext());
                /* Associate the toast with the view that describes the custom toast */
                toast.setView(ToastView);
// Display the toast
toast.show();

            }


        });


        display.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

// Create a custom dialog
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(MainActivity.this);

// Specify the title for the dialog box
builder.setTitle("This is an Alert Dialog"); builder.setMessage("Do you want to exit?");

// Set the negative button
builder.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        text.setText("You clicked the Cancel button");
                    }
                });


                builder.setPositiveButton("Ok", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                                text.setText("You clicked the OK button");
                            }
                        });

// Display the dialog box.
builder.show();
            }
        });


    }







public void listDialog(View view) {
    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

//Setting the title
builder.setTitle("Select a Color");

// Using the setItems() method

    builder.setItems(items, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    text.setText(items[which]);
                }
            }

    );
    builder.show();


}

public View getCustomToastView (int imageResourceID, String ToastTitle, String Message){
// Use the abstract LayoutInflater class to instantiate
// the XML layout for the toast into corresponding view objects.
// This class cannot be used directly, you have to use
// the getLayoutInflater() method to retrieve a standard
// LayoutInflater instance that is already hooked up to
// the current context and correctly configured for the
// device you are running on.
        LayoutInflater inflater = getLayoutInflater();
        View toastView = inflater.inflate(R.layout.ctoast,(ViewGroup)
                findViewById(R.id.cToast));

// Instantiating the ImageView widget
        ImageView image = (ImageView) toastView.findViewById(R.id.ivToastImage);

// Using drawables to set the image image.setImageResource(R.drawable.ic_launcher);

// Setting the title for the toast
        TextView title = (TextView) toastView.findViewById(R.id.tvToastTitle);
        title.setText(ToastTitle);

// Accepting the name from main.xml file EditView widget
        TextView text = (TextView) toastView.findViewById(R.id.tvToastText);
        text.setText(Message);

        return (toastView);
    }


    public void showToast(View view) {
        Toast.makeText(getApplicationContext(), "Hi. I am a simple toast", Toast.LENGTH_LONG).show();
    }

    public void showToastPosition(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Hi. I have changed my position", Toast.LENGTH_LONG);

        toast.setGravity(Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        toast.show();

    }

    public void showProgress(View view) {
        // Create an object of the ProgressDialog class.
        ProgressDialog pdialog;
// Initialize the ProgressDialog object
        pdialog = new ProgressDialog(this);
// Set the progress style to STYLE_HORIZONTAL
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
// Specify the message to be displayed in the progress dialog
pdialog.setMessage("Loading....");
pdialog.show();

    }

    public void showDatePicker(View view) {
        // To display the dialog
        showDialog(DateId);
    }

    // Display the date selected by the user
    private void display() { Toast.makeText(MainActivity.this,
"The selected date is:" + month + "-" + day + "-" + year, Toast.LENGTH_LONG).show();
}

private DatePickerDialog.OnDateSetListener DateListener = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker datePicker, int yr, int mnth, int mday) {
        year = yr;
        month = mnth + 1; day = mday; display();

    }
};

    //This method is called when the user sets the time
    private TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener()
    {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
//code to set time that is retrieved from the calendar class
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DateId:
                return new DatePickerDialog(this, DateListener, year, month,day);
            case 1:
                return new TimePickerDialog(this,timeListener,12,30,false);
        }
        return null;

    }

    public void showTimePicker(View view) {
        showDialog(1);
    }

    public void showCustomDialog(View view) {

// Create a custom dialog
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.cdialog);

// Specify the title for the dialog box
dialog.setTitle("WELCOME");



// and insert a text into it.
        TextView textview = (TextView) dialog
                .findViewById(R.id.textview);
        textview.setText("I AM A CUSTOM DIALOG BOX");

// Retrieve the reference of the ImageView from the

// and insert an image from the drawable resources.
ImageView img = (ImageView)
        dialog.findViewById(R.id.imageview);
        img.setImageResource(R.mipmap.ic_launcher);
// Display the dialog box.
        dialog.show();


    }


    public void setAlarm(View view) {
        // Obtain a reference to the alarm service by using the getSystemService()

        AlarmManager alarms = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

// Specify the type, trigger time, action
        int Type = AlarmManager.ELAPSED_REALTIME_WAKEUP;
        long timeofWait = 10000;
        String ALARM_ACTION = "ALARM_ACTION";

// Create an intent with the specified action
        Intent intentToFire = new Intent(ALARM_ACTION);

// Retrieve a pending intent that will perform a broadcast
PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intentToFire, 0);

// Set the alarm
        alarms.set(Type, timeofWait, pendingIntent);

    }

    public void openGesture(View view) {
        startActivity(new Intent(this,GestureActivity.class));
    }
}