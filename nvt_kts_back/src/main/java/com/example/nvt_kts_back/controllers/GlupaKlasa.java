package com.example.nvt_kts_back.controllers;

public class GlupaKlasa {

    public GlupaKlasa(){

    }

    public GlupaKlasa(String a, String n){
        this.aleksa = a;
        this.nevena = n;
    }

    public String aleksa;
    public String nevena;

    public String getAleksa() {
        return aleksa;
    }

    public void setAleksa(String aleksa) {
        this.aleksa = aleksa;
    }

    public String getNevena() {
        return nevena;
    }

    public void setNevena(String nevena) {
        this.nevena = nevena;
    }
}
