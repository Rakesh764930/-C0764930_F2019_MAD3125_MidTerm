package com.example.taxcalculation;

import android.app.AlertDialog;
import android.widget.Toast;

public class CRACustomer  {

    int sin_number;
    String first_name;
    String  last_name;
    String full_name=last_name.toUpperCase()+","+first_name;
    double grossIncome;
    double rrsp_contri;
double EI;

    public double cppAmount(double gross_income){
        double gi=gross_income;
        double cpp_slab=57_400.00;
        double cpp_rate=5.10;
        double actual_cpp=0.0;
        if(gi>=cpp_slab)
        {
            actual_cpp=(cpp_slab*cpp_rate)/100;
        }
        else {
            actual_cpp=(gi*cpp_rate)/100;
        }
            return actual_cpp;
    }
    public double rrspAmount(double gross_income){
        double gi=gross_income;
        double rrsp_perc=18.00;
        double actual_rrsp=(gi*rrsp_perc)/100;
        if(actual_rrsp>rrsp_contri) {
            System.out.println("RRSP amount exceede ,You may have to face penalty");
        }
       return actual_rrsp;
        }


    public double eiAmount(double gross_income){
        double gi=gross_income;
        double ei_slab=53_100.00;
       




}
