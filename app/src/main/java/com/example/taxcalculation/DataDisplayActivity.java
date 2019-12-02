package com.example.taxcalculation;




import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class DataDisplayActivity extends AppCompatActivity {
    CRACustomer customer;
    TextView txtsin;
    TextView txtfull_Name;
    TextView txtgenDer;
    TextView txtgross_income,
            txttaxDate, txtfederal_Tax, txtprovincial_Tax, lblcpp,
            txtEmpInsurance, txtRRSPcontri, txtCfRRSP,
            txtTaxableIncome, lblTaxPaid;
    double cpp = 0, ei = 0;  double rrsp = 0, rrspCf = 0, taxableIncome, fedTax,
            proTax, totalTaxPaid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);
        txtsin = findViewById(R.id.txtSIN);
        txtfull_Name = findViewById(R.id.txtfull_Name);
        txtgenDer =   findViewById(R.id.txtGender);
        txtgross_income = findViewById(R.id.txtgrossIncome);
        txtRRSPcontri = findViewById(R.id.txt_D_RRSPContri);
        lblcpp = findViewById(R.id.txtCpp);
        txtEmpInsurance = findViewById(R.id.txt_D_empInsurance);
        txtCfRRSP = findViewById(R.id.txt_D_cfRRSP);
        txtTaxableIncome = findViewById(R.id.txt_D_taxableIncome);
        txtfederal_Tax = findViewById(R.id.txt_D_federalTax);
        txtprovincial_Tax = findViewById(R.id.txt_D_provincialTax);
        lblTaxPaid = findViewById(R.id.txt_D_taxPayed);


        //collecting intent
        Intent mIntent = getIntent();
        CRACustomer customer = mIntent.getParcelableExtra("CRACustomer");

        txtsin.setText("\tSIN: \t" + customer.getSin_number());
        txtfull_Name.setText("\tFULL NAME: \t" + customer.getFull_name());
        txtgenDer.setText(" GENDER: \t" + customer.getGender());
        txtgross_income.setText("\tGROSS INCOME: \t" + customer.getGrossIncome());
        txtRRSPcontri.setText("\tRRSP Contributed: \t" + customer.getRrsp_contri());

        // calculate  cpp
        double grossIncome = customer.getGrossIncome();
        if(grossIncome > 57400.00){
            cpp = (57400.00 * 0.051); //perc= 5.10%
        } else {
            cpp = (grossIncome * 0.051);
        }
        lblcpp.setText("\tCPP COntribution in Year:\t" + cpp);
        // calculate employement insurance
        if(grossIncome > 53100){
            ei = (53100 * 0.0162); //perc 1.62%
        }else{
            ei = (grossIncome * (1.62/100));
        }
        txtEmpInsurance.setText("\tEmployeement Insurance: \t" + ei);
        // calculate RRSP
        rrsp = customer.getRrsp_contri();
        double maxRRSP = (grossIncome * 0.18); //perc= 18%
        if(rrsp > maxRRSP ){
            Toast.makeText(this,"you mave have to face a penalty,amount exceeding",Toast.LENGTH_SHORT).show();
            rrspCf = rrsp - maxRRSP;
            rrsp = maxRRSP;
        }else{
            rrsp = rrsp;
        }
        txtCfRRSP.setText("\tRRSP Carried forward: \t"+ rrspCf);
        //taxable income
        taxableIncome = grossIncome - (cpp + ei + rrsp);

        txtTaxableIncome.setText("\tTotal Taxable income:\t" + (double) taxableIncome);
        //federal tax
        double fed_tax = federalTax();
        txtfederal_Tax.setText("\tFederal Tax: \t" + fed_tax);
        // Provincial Tax
        double pro_tax = provincialTax();
        txtprovincial_Tax.setText("\tProvincial Tax:\t" + pro_tax);
        // total tax paid
        double taxpaid = TaxPaid();
        lblTaxPaid.setText("\tTotal tax Paid:\t" + taxpaid);

    }


    public double calcCpp(){
        // calculating  cpp
        if(customer.getGrossIncome() > 57400.00){
            cpp = (57400.00 * 0.051);
        } else {
            cpp = (customer.getGrossIncome() * 0.051);
        }
        return cpp;
    }
    //calculating federal tax
    public double federalTax(){

        double temp = taxableIncome ;
        if(temp <= 12069.00){
            fedTax = 0;//0%
            temp = taxableIncome - 12069.00;
        }
        if(temp >= 12069.01){
            fedTax = (temp * 0.15);//perc= 15%
            temp = temp - 35561;
        }
        if(temp >= 47630.01){
            fedTax = (temp * 0.205); //perc= 20.50%
            temp = temp - 47628.99;
        }
        if(temp >= 95259.01){
            fedTax = (temp * 0.26); //perc= 26%
            temp = temp - 52407.99;
        }
        if (temp >= 147667.01){
            fedTax = (temp * 0.29);//perc= 29%
            temp = temp - 62703.99;
        }
        if(temp >= 210371.01){
            fedTax = (temp * 0.33);//perc= 33%
            //temp = temp - federalTax;
        }
        return fedTax;
    }

    //calculate provincial tax
    public  double provincialTax(){
        double temp = taxableIncome ;

        if(temp <= 10582.00){
            proTax = 0;
            temp = taxableIncome - 10582.00;
        }
        if(temp >= 10582.01){
            proTax = (temp * 0.0505); // perc= 5.05%
            temp = temp - 33323.99;
        }
        if(temp >= 43906.01){
            proTax = (temp * 0.0915); //perc= 9.15%
            temp = temp - 43906.99;
        }
        if(temp >= 87813.01){
            proTax = (temp * 0.1116); //perc= 11.16%
            temp = temp - 62187.99;
        }
        if (temp >= 150000.01){
            proTax = (temp * 0.1216);//perc= 12.16%
            temp = temp - 69999.99;
        }
        if(temp >= 220000.01){
            proTax = (temp * 0.1316);//perc= 13.16%

        }
        return proTax;
    }
    public  double TaxPaid(){
        return totalTaxPaid=fedTax+proTax;
    }

}