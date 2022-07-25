package com.example.dyp1;

class Wprowadz_statystyki {
    private String kary_opis,data;
    private double paliwo_objetosc,paliwo_koszt,kary_koszt,wynagrodzenie_z_trasy_DOR,wynagrodzenie_z_trasy_ODB,wynagrodzenie_kuriera,zysk_z_trasy;
    private int id,id_kurier,ilosc_DOR,przejechane_km,ilosc_odb,ilosc_dni_odb;

    public Wprowadz_statystyki(String kary_opis,String data,double paliwo_objetosc,double paliwo_koszt,double kary_koszt,double wynagrodzenie_z_trasy_DOR,
                               double wynagrodzenie_z_trasy_ODB,double wynagrodzenie_kuriera,double zysk_z_trasy,int id,int id_kurier,int ilosc_DOR,int przejechane_km,
                               int ilosc_odb,int ilosc_dni_odb){
        this.kary_opis=kary_opis;
        this.data=data;
        this.paliwo_objetosc=paliwo_objetosc;
        this.paliwo_koszt=paliwo_koszt;
        this.kary_koszt=kary_koszt;
        this.wynagrodzenie_z_trasy_DOR=wynagrodzenie_z_trasy_DOR;
        this.wynagrodzenie_z_trasy_ODB=wynagrodzenie_z_trasy_ODB;
        this.wynagrodzenie_kuriera=wynagrodzenie_kuriera;
        this.zysk_z_trasy=zysk_z_trasy;
        this.id=id;
        this.id_kurier=id_kurier;
        this.ilosc_DOR=ilosc_DOR;
        this.przejechane_km=przejechane_km;
        this.ilosc_odb=ilosc_odb;
        this.ilosc_dni_odb=ilosc_dni_odb;

    }
    public String getKary_opis(){return kary_opis;}
    public void setKary_opis(){this.kary_opis = kary_opis;}
    public String getData(){return data;}
    public void setData(){this.data = data;}
    public double getPaliwo_objetosc(){return paliwo_objetosc;}
    public void setPaliwo_objetosc(){this.paliwo_objetosc = paliwo_objetosc;}
    public double getPaliwo_koszt(){return paliwo_koszt;}
    public void setPaliwo_koszt(){this.paliwo_koszt = paliwo_koszt;}
    public double getKary_koszt(){return kary_koszt;}
    public void setKary_koszt(){this.kary_koszt = kary_koszt;}
    public double getWynagrodzenie_z_trasy_DOR(){return wynagrodzenie_z_trasy_DOR;}
    public void setWynagrodzenie_z_trasy_DOR(){this.wynagrodzenie_z_trasy_DOR = wynagrodzenie_z_trasy_DOR;}
    public double getWynagrodzenie_z_trasy_ODB(){return wynagrodzenie_z_trasy_ODB;}
    public void setWynagrodzenie_z_trasy_ODB(){this.wynagrodzenie_z_trasy_ODB = wynagrodzenie_z_trasy_ODB;}
    public double getWynagrodzenie_kuriera(){return wynagrodzenie_kuriera;}
    public void setWynagrodzenie_kuriera(){this.wynagrodzenie_kuriera = wynagrodzenie_kuriera;}
    public double getZysk_z_trasy(){return zysk_z_trasy;}
    public void setZysk_z_trasy(){this.zysk_z_trasy = zysk_z_trasy;}
    public int getId(){return id;}
    public void setId(){this.id = id;}
    public int getId_kurier(){return id_kurier;}
    public void setId_kurier(){this.id_kurier = id_kurier;}
    public int getIlosc_DOR(){return ilosc_DOR;}
    public void setIlosc_DOR(){this.ilosc_DOR = ilosc_DOR;}
    public int getPrzejechane_km(){return przejechane_km;}
    public void setPrzejechane_km(){this.przejechane_km = przejechane_km;}
    public int getIlosc_odb(){return ilosc_odb;}
    public void setIlosc_odb(){this.ilosc_odb = ilosc_odb;}
    public int getIlosc_dni_odb(){return ilosc_dni_odb;}
    public void setIlosc_dni_odb(){this.ilosc_dni_odb = ilosc_dni_odb;}





}
