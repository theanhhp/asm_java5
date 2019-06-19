/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author trant
 */
public class nv {

    private int MaNv;
    private String  hovaten, email, ngaysinh, gioitinh, sdt ,diachi;
    private double cmnd;
    private int trangthai;
    private byte[] hinh;

    public nv() {
    }

    public nv(int MaNv, String hovaten, String email, String ngaysinh, String gioitinh, String sdt, String diachi, double cmnd, int trangthai, byte[] hinh) {
        this.MaNv = MaNv;
       
        this.hovaten = hovaten;
        this.email = email;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.cmnd = cmnd;
        this.trangthai = trangthai;
        this.hinh = hinh;
    }

    public int getMaNv() {
        return MaNv;
    }

    public void setMaNv(int MaNv) {
        this.MaNv = MaNv;
    }

   

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public double getCmnd() {
        return cmnd;
    }

    public void setCmnd(double cmnd) {
        this.cmnd = cmnd;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    

}
