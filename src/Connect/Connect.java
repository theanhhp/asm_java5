/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hao
 */
public class Connect {
    public static Connection getConnect(){
       Connection conn=null;
       try{
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe","sa","songlong");
//           if(conn !=null){
//               System.err.println("ket noi thanh cong");
//           }
       }catch(Exception ex){
           System.out.println(ex.toString());
           ex.printStackTrace();
       }
       return conn;
    }
    
}
