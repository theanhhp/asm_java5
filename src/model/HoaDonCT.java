/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HOME
 */
public class HoaDonCT {
    private int MaHDCT;
    private int MaHD;
    private String TenMon;
    private String DVT;
    private int SoLuong;
    private int Gia;

    public HoaDonCT() {
    }

    public HoaDonCT(int MaHDCT ,int MaHD, String TenMon, String DVT, int SoLuong, int Gia) {
        this.MaHDCT = MaHDCT;
        this.MaHD = MaHD;
        this.TenMon = TenMon;
        this.DVT = DVT;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
    }

    public int getMaHDCT() {
        return MaHDCT;
    }

    public void setMaHDCT(int MaHDCT) {
        this.MaHDCT = MaHDCT;
    }
    

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int Gia) {
        this.Gia = Gia;
    }
    
}
