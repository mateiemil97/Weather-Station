package com.example.weatherstationapp;

public class Measurement implements Comparable<Measurement>{

    private int Value;
    private String DateTime;


    public Measurement(int value,String dateTime){
        Value = value;
        DateTime = dateTime;
    }

    public int GetValue(){
        return Value;
    }

    public String GetDateTime(){
        return DateTime;
    }

    @Override
    public int compareTo(Measurement o) {
        return (Integer.parseInt(this.GetDateTime())) - (Integer.parseInt(o.GetDateTime()));
//        int res=0;
//        if (Integer.parseInt(GetDateTime()) < Integer.parseInt(o.GetDateTime())) {res=-1;  }
//        if (Integer.parseInt(GetDateTime()) > Integer.parseInt(o.GetDateTime())){res=1;}
//        return res;
    }

}

