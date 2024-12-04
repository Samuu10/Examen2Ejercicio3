package com.example.examen2ejercicio3.GestionFarmacias;

//Clase Farmacia
public class Farmacia {

    //Atributos
    private String name;
    private String phone;
    private double latitude;
    private double longitude;

    //Constructor
    public Farmacia() {}

    //Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}