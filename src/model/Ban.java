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
public class Ban {
    private String MaBan;
    private String TenBan;
    private String TrangThai;

    public Ban() {
    }

    public Ban(String MaBan, String TenBan, String TrangThai) {
        this.MaBan = MaBan;
        this.TenBan = TenBan;
        this.TrangThai = TrangThai;
    }

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
