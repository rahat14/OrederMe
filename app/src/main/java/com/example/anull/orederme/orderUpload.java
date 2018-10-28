package com.example.anull.orederme;

import java.util.List;

public class orderUpload {
    private  String email ;
    private String phnNum  ;
    private String adress ;
private List<Order>foods ;

    public orderUpload() {
    }

    public orderUpload(String email, String phnNum, String adress, List<Order> foods) {
        this.email = email;
        this.phnNum = phnNum;
        this.adress = adress;
        this.foods = foods;
    }

    public String getEmail() {
        return email;
    }

    public String getPhnNum() {
        return phnNum;
    }

    public String getAdress() {
        return adress;
    }

    public List<Order> getFoods() {
        return foods;
    }
}
