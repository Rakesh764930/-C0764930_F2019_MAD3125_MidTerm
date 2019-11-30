package com.example.taxcalculation;

public class CRACustomer  {

    int sin_number;
    String first_name;
    String  last_name;
    String full_name=last_name.toUpperCase()+","+first_name;



    public double cppAmount(double gross_income){
        double gi=gross_income;
        double cpp_slab=57400.00;
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
    public double cppAmount(double gross_income){
        double gi=gross_income;
        double cpp_slab=57400.00;
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



}
