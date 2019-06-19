/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HOME
 */
public class checklogin {

    public static Connection conn = null;
    public static ResultSet rs = null;
    public static PreparedStatement pst = null;

    public static String testConnect() {
        try {
            conn = Connect.getConnect();

            return "Kết nối dữ liệu thành công";

        } catch (Exception e) {
            return "Kết nối dữ liệu thất bại";
        }

    }

    public static ResultSet cLog(String user, String pass) {
        String sql = "select * from taikhoan where Username=? and Passwords=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            return rs = pst.executeQuery();
        } catch (Exception e) {
            return rs = null;
        }

    }

}
