package com.example.irishbirdapp;

public class Bird {

    //public Bird commonName;
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


}
