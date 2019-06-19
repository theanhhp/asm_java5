/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLKho;

import Connect.connect_1;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author win
 */
public class QL_SanPham2 extends javax.swing.JPanel {
    NumberFormat chuyentien = new DecimalFormat("#,###");
    connect_1 cho = new connect_1();
    String head[] = {"Số Thứ Tự", "Mã Lọai", "Tên Sản Phẩm", "Nhà Sản Xuất", "Giá Sản Phẩm (VND)", "Ngày Nhập Kho", "Số Lượng","Thành Tiền (VND) ","Đơn Vị Tính", "Địa Chỉ Nới Mua"};
    DefaultTableModel model = new DefaultTableModel(head, 0);

    /**
     * Creates new form QL_SanPham2
     */
    public QL_SanPham2() {
        initComponents();
        //combobox();
        load();
        Loadngaygio();
        //ong chay thu di
    }

//    public void loadcombobox() {
//        try {
//            Connection con = cho.getconection();
//            Statement st = con.createStatement();
//            String sql = "select * from sanphamkho";
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                String s = rs.getString(3);
//                cbxmamon.addItem(s);
//                String ss = rs.getString(4);
//                cbxtensanpham.addItem(ss);
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

 
    
    public void load() {
        try {
            model.setRowCount(0);
            String user = "sa";
            String pass = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe";
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "select * from sanphamkho";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                row.add(chuyentien.format(rs.getFloat(7)));
                row.add(chuyentien.format(rs.getFloat(8)));
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                model.addRow(row);
            }
            table.setModel(model);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public void combobox() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Polypro", "sa", "123");
//            String sql = "select mamon from thucdon";
//            PreparedStatement pst = con.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                String ten = rs.getString("mamon");
//                cbxmamon.addItem(ten);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }
    //mahoadon trong sql của ông làm j có cái này
    // cái ma hoa đơn á hả
    // trong sql của ông không có sao nó lưu đc
    // dị sai sql rồi 
    // cái ngày hiện tại làm vậy đc k
    //ông tụ gửi code rồi ông làm chauw đc hả
    // ngay hiện tại 
    // để tụ làm dùm ông lun cho
    // còn cái ngày giờ mình phải bỏ ra ngoài 
    // để tụ làm ông cái ngày trước
    //ok
    // nhưng mình lưu mình vẫn làm như bt nha
    public void Loadngaygio() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        txtngaynhap.setDate(date);
    }

    public void save() {
       if(check()){
        try {
            Connection con = cho.getconection();
            String sql = "insert into sanphamkho(maloai,tensanpham,nhasanxuat,giasanpham,ngaynhapkho,soluong,donvitinh,diachinoimua) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cbomaloai.getSelectedItem().toString());
            ps.setString(2, txttensanpham.getText());
            ps.setString(3, txanhasanxuat.getText());
            ps.setString(4, txtgiasanpham.getText());
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(5, df.format(date));
            ps.setString(6, txtsoluong.getText());
            ps.setString(7, txtdonvitinh.getText());
            ps.setString(8, txadiachinoimua.getText());
            ps.executeUpdate();
            con.close();
            load();
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
        }
       }
    }

public boolean matrong(String s) {
        if (s.equals("")) {
            return false;
        }
        return true;
    }

    public boolean nhapchu(String ss) {
        Pattern pt = Pattern.compile("a-zA-z");
        Matcher mt = pt.matcher(ss);
        if (!mt.find()) {
            return false;
        }
        return true;
    }

    public boolean loiso(String s1) {
        Pattern pt = Pattern.compile("[0-9]");
        Matcher rc = pt.matcher(s1);
        if (!rc.find()) {
            return false;
        }
        return true;
    }
      public boolean kitu(String s2) {
        Pattern pt = Pattern.compile("[a-zA-Z]");
        Matcher mc = pt.matcher(s2);
        if (!mc.find()) {
            return false;
        }
        return true;
    }
    public boolean check() {
        String tensanpham = txttensanpham.getText();
        String nhasanxuat = txanhasanxuat.getText();
        String gia = txtgiasanpham.getText();
        String soluong = txtsoluong.getText();
        String diachi = txadiachinoimua.getText();
        try {
          
            if (!matrong(tensanpham)) {
                JOptionPane.showMessageDialog(this, "Tên Sản Phẩm Không Được Bỏ Trống");
                txttensanpham.requestFocusInWindow();
                return false;
            }
          
            if (!kitu(tensanpham)) {
                JOptionPane.showMessageDialog(this, "Tên Sản Phẩm Không Được Ghi Số");
                txttensanpham.requestFocusInWindow();
                return false;
            }
              if (!matrong(nhasanxuat)) {
                JOptionPane.showMessageDialog(this, "Nhà Sản Xuất Không Được Bỏ Trống");
                txanhasanxuat.requestFocusInWindow();
                return false;
            }
            if(!matrong(gia)){
                JOptionPane.showMessageDialog(this, "Giá Không Được Bỏ Trống");
                txtgiasanpham.requestFocusInWindow();
                return false;
            }
            if(!loiso(gia)){
                JOptionPane.showMessageDialog(this, "Giá Không Được Nhập Chữ");
                txtgiasanpham.requestFocusInWindow();
                return false;
            }
            if(!matrong(soluong)){
                JOptionPane.showMessageDialog(this, "Số Lượng  Không Được Bỏ Trống");
                txtsoluong.requestFocusInWindow();
                return false;
            }
            if(!loiso(soluong)){
                JOptionPane.showMessageDialog(this, "Số Lương Không Được Nhập Chữ");
                txtsoluong.requestFocusInWindow();
                return false;
            }
            if(!matrong(diachi)){
                JOptionPane.showMessageDialog(this, "Địa Chỉ Không Được Bỏ Trống");
                txadiachinoimua.requestFocusInWindow();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    public void update() {
        try {
            Connection con = cho.getconection();
            String sql = "update sanphamkho set nhasanxuat=?,giasanpham=?,ngaynhapkho=?,soluong=?,diachinoimua=? where madonhang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txanhasanxuat.getText());
            ps.setString(2, txtgiasanpham.getText());
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(3, df.format(date));
            ps.setString(4, txtsoluong.getText());
            ps.setString(5, txadiachinoimua.getText());
            ps.setString(6, txtdonvitinh.getText());
            ps.executeUpdate();
            con.close();
            load();
            JOptionPane.showMessageDialog(this, "Update thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            Connection con = cho.getconection();
            String sql = "delete from sanphamkho where maloai=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cbomaloai.getSelectedItem().toString());
            st.executeUpdate();
            con.close();
            load();
            JOptionPane.showMessageDialog(this, "Xóa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnlammoi = new javax.swing.JButton();
        txtsoluong = new javax.swing.JTextField();
        btnxoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();
        txtgiasanpham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txadiachinoimua = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txanhasanxuat = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtdonvitinh = new javax.swing.JTextField();
        txttensanpham = new javax.swing.JTextField();
        txtngaynhap = new com.toedter.calendar.JDateChooser();
        cbomaloai = new javax.swing.JComboBox<>();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1411, 566));

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên Sản Phẩm :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Địa Chỉ Nơi Mua :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Số Lượng :");

        btnlammoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnlammoi.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\new.png")); // NOI18N
        btnlammoi.setText("New");

        btnxoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnxoa.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\delete.png")); // NOI18N
        btnxoa.setText("Delete");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnsua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsua.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\update.png")); // NOI18N
        btnsua.setText("Update");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnthem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnthem.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\save.png")); // NOI18N
        btnthem.setText("Save");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Giá Sản Phẩm :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ngày Nhập Kho :");

        txadiachinoimua.setColumns(20);
        txadiachinoimua.setRows(5);
        jScrollPane1.setViewportView(txadiachinoimua);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nhà Sản Xuất :");

        txanhasanxuat.setColumns(20);
        txanhasanxuat.setRows(5);
        jScrollPane3.setViewportView(txanhasanxuat);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã loại:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Đơn Vị Tính :");

        txtdonvitinh.setText("VND");
        txtdonvitinh.setEnabled(false);

        cbomaloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "food", "drink" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsua)
                        .addGap(18, 18, 18)
                        .addComponent(btnxoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnlammoi)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8))))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txttensanpham)
                                    .addComponent(txtngaynhap, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                    .addComponent(cbomaloai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtgiasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(jLabel7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtdonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtgiasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(cbomaloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txttensanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtngaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("QL_Mua Hàng", jDesktopPane1);

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Danh Sách", jPanel2);

        jTabbedPane1.addTab("Danh Sách Hàng", jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed

        save();
    }//GEN-LAST:event_btnthemActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int i = table.getSelectedRow();
        model = (DefaultTableModel) table.getModel();
//        txtmadonhang.setText(model.getValueAt(i, 0).toString());
    String combo = model.getValueAt(i, 1).toString();
switch(combo){
    case "food" :
        cbomaloai.setSelectedIndex(0);
        break;
    case "drink" :
        cbomaloai.setSelectedIndex(1);
        break;
        default:
            break;
}
        txttensanpham.setText(model.getValueAt(i, 2).toString());
        txanhasanxuat.setText(model.getValueAt(i, 3).toString());    
        txtgiasanpham.setText(model.getValueAt(i, 4).toString());
        try {
            int chonngay = table.getSelectedRow();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(chonngay, 5).toString());
            txtngaynhap.setDate(date);
        } catch (Exception e) {
        }
        txtsoluong.setText(model.getValueAt(i, 6).toString());
//                txtthanhtien.setText(model.getValueAt(i, 7).toString());
        txtdonvitinh.setText(model.getValueAt(i, 8).toString());
        txadiachinoimua.setText(model.getValueAt(i, 9).toString());
    }//GEN-LAST:event_tableMouseClicked

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        this.update();
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnxoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cbomaloai;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable table;
    private javax.swing.JTextArea txadiachinoimua;
    private javax.swing.JTextArea txanhasanxuat;
    private javax.swing.JTextField txtdonvitinh;
    private javax.swing.JTextField txtgiasanpham;
    private com.toedter.calendar.JDateChooser txtngaynhap;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttensanpham;
    // End of variables declaration//GEN-END:variables
}
