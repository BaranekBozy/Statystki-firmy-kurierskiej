package com.example.dyp1;

class Samochod {
    int id;
    String marka,model,numer_rejestracyjny;
    double koszt;

    public Samochod(int id, String marka, String model, String numer_rejestracyjny, double koszt) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.numer_rejestracyjny = numer_rejestracyjny;
        this.koszt = koszt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumer_rejestracyjny() {
        return numer_rejestracyjny;
    }

    public void setNumer_rejestracyjny(String numer_rejestracyjny) {
        this.numer_rejestracyjny = numer_rejestracyjny;
    }

    public double getKoszt() {
        return koszt;
    }

    public void setKoszt(double koszt) {
        this.koszt = koszt;
    }
}
