package com.example.dyp1;

class Kurier {
    int id;
    String imie,nazwisko;
    int numer_kuriera,id_auto;
    double stawka_za_paczke,stawka_za_odbior;
    int numer_kontaktowy;

    public Kurier(int id, String imie, String nazwisko, int numer_kuriera, int id_auto, double stawka_za_paczke, double stawka_za_odbior, int numer_kontaktowy) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numer_kuriera = numer_kuriera;
        this.id_auto = id_auto;
        this.stawka_za_paczke = stawka_za_paczke;
        this.stawka_za_odbior = stawka_za_odbior;
        this.numer_kontaktowy = numer_kontaktowy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getNumer_kuriera() {
        return numer_kuriera;
    }

    public void setNumer_kuriera(int numer_kuriera) {
        this.numer_kuriera = numer_kuriera;
    }

    public int getId_auto() {
        return id_auto;
    }

    public void setId_auto(int id_auto) {
        this.id_auto = id_auto;
    }

    public double getStawka_za_paczke() {
        return stawka_za_paczke;
    }

    public void setStawka_za_paczke(double stawka_za_paczke) {
        this.stawka_za_paczke = stawka_za_paczke;
    }

    public double getStawka_za_odbior() {
        return stawka_za_odbior;
    }

    public void setStawka_za_odbior(double stawka_za_odbior) {
        this.stawka_za_odbior = stawka_za_odbior;
    }

    public int getNumer_kontaktowy() {
        return numer_kontaktowy;
    }

    public void setNumer_kontaktowy(int numer_kontaktowy) {
        this.numer_kontaktowy = numer_kontaktowy;
    }
}
