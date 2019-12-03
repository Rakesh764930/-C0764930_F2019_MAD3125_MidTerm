package com.example.taxcalculation;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class CRACustomer implements Parcelable {
    public static void main(String[] args) {

    }
    String sin_number;
    String first_name;
    String  last_name;
    String full_name;
    String gender;
    Date dob,filingDate;
    String aGe;

    double fed_Tax, prov_Tax;
    double rrsp_CarryForward;
    public Date getDob() {
        return dob;
    }

    public Date getFilingDate() {
        return filingDate;
    }

    double grossIncome;
    double rrsp_contri;
    double EI;
//    double total_taxable_amount=grossIncome-(cppAmount()+rrspAmount()+eiAmount());
//    double total_tax_paid=provincialTax()+federalTax();


    public CRACustomer(String sinNumber, String firstName,
                       String lastName, String gender, double grossIncome, double rrspContri,String aGe)
    {
        this.sin_number = sinNumber;
        this.first_name = firstName;
        this.last_name = lastName;
        this.gender = gender;
        this.aGe=aGe;
        this.grossIncome = grossIncome;
        this.rrsp_contri = rrspContri;
    }
    //setters and getters
    public String getSin_number() {
        return sin_number;
    }

    public void setSin_number(String sin_number) {
        this.sin_number = sin_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFull_name() {
        return last_name.toUpperCase() + ", " +
                first_name.substring(0,1).toUpperCase() + first_name.substring(1);
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getRrsp_contri() {
        return rrsp_contri;
    }

    public void setRrsp_contri(double rrsp_contri) {
        this.rrsp_contri = rrsp_contri;
    }


    protected CRACustomer(Parcel in) {
        sin_number = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        gender = in.readString();
        aGe=in.readString();
        full_name = in.readString();
        grossIncome = in.readDouble();
        rrsp_contri = in.readDouble();

    }

    public static final Parcelable.Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel in) {
            return new CRACustomer(in);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sin_number);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(full_name);
        dest.writeString(aGe);
        dest.writeString(gender);
        dest.writeDouble(grossIncome);
        dest.writeDouble(rrsp_contri);

    }

}
