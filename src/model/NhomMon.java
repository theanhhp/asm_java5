/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hao
 */
public class NhomMon {
    private String MaLoai,TenLoai,MauSac;

    public NhomMon() {
    }

    public NhomMon(String MaLoai, String TenLoai, String MauSac) {
        this.MaLoai = MaLoai;
        this.TenLoai = TenLoai;
        this.MauSac = MauSac;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }
    
}
