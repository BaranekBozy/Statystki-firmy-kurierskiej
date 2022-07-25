package com.example.dyp1;

class Dodatkowe_koszta {
    private String data,opis;
    private int id,numer_kuriera;
    private double koszta;

    public Dodatkowe_koszta( int id, int numer_kuriera, String opis, double koszta,String data) {
        this.data = data;
        this.opis = opis;
        this.id = id;
        this.numer_kuriera = numer_kuriera;
        this.koszta = koszta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumer_kuriera() {
        return numer_kuriera;
    }

    public void setNumer_kuriera(int numer_kuriera) {
        this.numer_kuriera = numer_kuriera;
    }

    public double getKoszta() {
        return koszta;
    }

    public void setKoszta(double koszta) {
        this.koszta = koszta;
    }
}
