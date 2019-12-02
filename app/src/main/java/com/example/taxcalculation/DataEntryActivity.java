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
import java.time.LocalDate;
import java.time.Period;
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
    EditText edtGross;
    EditText edtsin;
    EditText edtRrsp;
    String gender=" ";
    Button btnSubmit;
    TextView txtAge;
    DatePickerDialog datePickerDialog;

     int dDay;
     int dMonth;
     int dYear;
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

                if(i == R.id.rbMale){
                    gender = rbMale.getText().toString();
                }else if(i == R.id.rbFemale){
                    gender = rbFemale.getText().toString();
                }else {
                    gender = rbOthers.getText().toString();
                }
            }

        });
        edtFname=findViewById(R.id.edtFname);
         edtLname=findViewById(R.id.edtLname);
         edtsin=findViewById(R.id.edtSin);
         edtGross=findViewById(R.id.edtGrossIncome);
         edtRrsp=findViewById(R.id.edtRRSP);

        //submit button
        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age=dateFormat();
                if(Integer.parseInt(age)<18){
                    btnSubmit.setAlpha(.5f);
                    btnSubmit.setClickable(false);
                }
                else{
                    Double grossIncome = Double.parseDouble(edtGross.getText().toString());
                    Double rrsp = Double.parseDouble(edtRrsp.getText().toString());
                   CRACustomer customer = new CRACustomer(edtsin.getText().toString(),
                            edtFname.getText().toString(),
                            edtLname.getText().toString(),
                            gender, grossIncome, rrsp);
                    Intent intent = new Intent(DataEntryActivity.this, DataDisplayActivity.class);
                    intent.putExtra("CRACustomer", customer);
                    startActivity(intent);
            }}
        });
    }
    private String dateFormat() {
        String myFormat = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtDate.setText("D.O.B\t:"+sdf.format(calendar.getTime()));

        LocalDate l = LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        // LocalDate l = LocalDate.of(1998, 04, 23); //specify year, month, date directly
        LocalDate now = LocalDate.now(); //gets localDate
        Period diff = Period.between(l, now); //difference between the dates is calculated
        System.out.println(diff.getYears() + "years" + diff.getMonths() + "months" + diff.getDays() + "days");

        String n1=String.valueOf(diff.getYears());
        String n2=String.valueOf(diff.getMonths());
        String n3=String.valueOf(diff.getDays());
        String age="Age: "+n1+"Years"+n2+"Months"+n3+"Days";

        txtAge.setText(age);
return  n1;



    }

}

