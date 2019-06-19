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
public class Menu {
    private String MaMon;
    private String TenMon;
    private String MaLoai;
    private String DonGia;
    private String DVT;

    public Menu() {
    }

    public Menu(String MaMon, String TenMon, String MaLoai, String DonGia, String DVT) {
        this.MaMon = MaMon;
        this.TenMon = TenMon;
        this.MaLoai = MaLoai;
        this.DonGia = DonGia;
        this.DVT = DVT;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    
    
}
