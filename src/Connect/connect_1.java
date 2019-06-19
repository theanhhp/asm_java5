/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author win
 */

public class connect_1 {
    public static Connection getconection(){
        Connection con = null;
        try {
              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
               con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe", "sa", "songlong");
              if(con != null){
               
              }
        } catch (Exception e) {
        }
        return con;
      
        
    }
            
}
