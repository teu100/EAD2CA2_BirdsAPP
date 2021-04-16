package com.example.irishbirdapp;

import java.io.Serializable;

public class Bird implements Serializable {

    //public Bird commonName;
    private int id;
    private String commonName;
    private String binomial;
    private String irishName;
    private String orderName;
    private String familyName;
    private String infoLink;
    private byte liked;
    private String imageLink;

    public String toString() {
        String Bird = "common Name :"+this.commonName+"\n" +
                "Binomial: "+this.binomial+"\n" +
                "Irish Name : "+this.irishName+"\n" +
                "Order Name: "+this.orderName+"\n" +
                "Family Name : "+this.familyName+"\n" +
                "Info Link: "+this.infoLink+"\n"
                ;
        return Bird;
    }

    public int getID() {return id;}

    public String getCommonName(){
        return commonName;
    }

    public String getBinomial(){
        return binomial;
    }

    public String getIrishName(){
        return irishName;
    }

    public String getOrderName(){
        return orderName;
    }

    public String getFamilyName(){
        return familyName;
    }

    public String getLiked()
    {
        String rtrnString ="";
        if(liked != 0)
        {
            rtrnString = "Liked";
        }
        else
        {
            rtrnString = "..........";
        }
        return rtrnString;
    };

    public String getInfoLink(){
        return infoLink;
    }

    public String getImageLink(){
        return imageLink;
    }

    public void setImageLink(String imgLink){
        this.imageLink = imgLink;
    }


}
