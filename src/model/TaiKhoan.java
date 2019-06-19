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
public class TaiKhoan {
    private String ID;
    private String Username;
    private String Password;
    private boolean lv;

    public TaiKhoan() {
    }

    public TaiKhoan(String ID, String Username, String Password, boolean lv) {
        this.ID = ID;
        this.Username = Username;
        this.Password = Password;
        this.lv = lv;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public boolean isLv() {
        return lv;
    }

    public void setLv(boolean lv) {
        this.lv = lv;
    }
    
    
}
