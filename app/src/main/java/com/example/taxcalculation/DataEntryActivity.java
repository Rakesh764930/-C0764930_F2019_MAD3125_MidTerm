package com.example.taxcalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Locale;

public class DataEntryActivity  extends AppCompatActivity  {

    final Calendar calendar = Calendar.getInstance();
    TextView txtDate;
    RadioGroup rgGender;
    RadioButton rbMale;
    RadioButton rbFemale;
    RadioButton rbOthers;
    TextView txtFullName;
    EditText edtFname;
    EditText edtLname;
    Button btnSubmit;
    TextView txtAge;
    DatePickerDialog datePickerDialog;

    int dDay;
     int dMonth;
     int dYear;
     int sYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        txtDate= findViewById(R.id.txtDate);


       // https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateFormat();

            }

        };


        txtDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(DataEntryActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        txtAge=findViewById(R.id.txtAge);
        rgGender=findViewById(R.id.rgGender);
        rbMale=findViewById(R.id.rbMale);
        rbFemale=findViewById(R.id.rbFemale);
        rbOthers=findViewById(R.id.rbOthers);
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (rbMale.isChecked())
                    Toast.makeText(DataEntryActivity.this, "You Chose Male", Toast.LENGTH_SHORT).show();
                if (rbFemale.isChecked())
                    Toast.makeText(DataEntryActivity.this, "You Chose Female", Toast.LENGTH_SHORT).show();
                if (rbOthers.isChecked())
                    Toast.makeText(DataEntryActivity.this, "You Chose Others", Toast.LENGTH_SHORT).show();
            }
        });

        //submit button
        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DataEntryActivity.this,DataDisplayActivity.class);
                startActivity(intent);
            }
        });

    }
    private void dateFormat() {
        String myFormat = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtDate.setText(sdf.format(calendar.getTime()));
    }




}


