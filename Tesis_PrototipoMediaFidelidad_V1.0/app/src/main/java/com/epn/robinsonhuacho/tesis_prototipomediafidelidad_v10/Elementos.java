package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

/**
 * Created by Robinson Huacho on 10/04/2018.
 */

public class Elementos {

    private int imgFoto;
    private String Nombres;
    private String TotalCompra;
    private String Saldo;

    public Elementos(int imgFoto, String nombres, String totalCompra, String saldo) {
        this.imgFoto = imgFoto;
        Nombres = nombres;
        TotalCompra = totalCompra;
        Saldo = saldo;
    }

    public int getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(int imgFoto) {
        this.imgFoto = imgFoto;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getTotalCompra() {
        return TotalCompra;
    }

    public void setTotalCompra(String totalCompra) {
        TotalCompra = totalCompra;
    }

    public String getSaldo() {
        return Saldo;
    }

    public void setSaldo(String saldo) {
        Saldo = saldo;
    }

}
