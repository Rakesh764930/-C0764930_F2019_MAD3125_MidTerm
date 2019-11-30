package com.example.taxcalculation;

import android.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

public class CRACustomer  {

    int sin_number;
    String first_name;
    String  last_name;
    String full_name=last_name.toUpperCase()+","+first_name;
    double grossIncome;
    double rrsp_contri;
    double EI;
    double total_taxable_amount=(grossIncome-cppAmount()+rrspAmount()+eiAmount());

        // calculating CPP amount
    public double cppAmount(){
        double cpp_slab=57_400.00;
        double cpp_rate=5.10;
        double actual_cpp=0.0;
        if(grossIncome>=cpp_slab)
        {
            actual_cpp=(cpp_slab*cpp_rate)/100;
        }
        else {
            actual_cpp=(grossIncome*cpp_rate)/100;
        }
            return actual_cpp;
    }

    // calculating RRSP amount
    public double rrspAmount(){
        double rrsp_perc=18.00;
        double actual_rrsp=(grossIncome*rrsp_perc)/100;
        if(actual_rrsp>rrsp_contri) {
            System.out.println("RRSP amount exceeded ,You may have to face penalty");
        }
       return actual_rrsp;
        }

    // calculating EI amount
    public double eiAmount(){

        double ei_slab=53_100.00;
        double ei_rate=1.62;
        double actual_ei=0.0;
        if(grossIncome>=ei_slab)
        {
            actual_ei=(ei_slab*ei_rate)/100;
        }
        else {
            actual_ei=(grossIncome*ei_rate)/100;
        }
        return actual_ei;
    }
    public void print(){
        System.out.println(total_taxable_amount);
    }

    // Calculating provincial tax
    public double proTax(){
        double pro_tax=0.0;
        total_taxable_amount=total_taxable_amount-10_582;
        if(total_taxable_amount)

    }

}
