/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JformBanHang;

import Connect.Connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import model.Ban;

/**
 *
 * @author hao
 */
public class BanHang extends javax.swing.JPanel {

    ArrayList<Ban> list = new ArrayList<>();
    DefaultTableModel model;
    int index = 0;

    private Connection conn;
    Connect con = new Connect();

    /**
     * Creates new form BanHang
     */
    public BanHang() {
        initComponents();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe;user=sa;password=songlong");
        } catch (Exception e) {
            System.out.println(e);
        }
        list = (ArrayList<Ban>) getListBan();
//        Fillcombo();
//        fillTrangThai();
        LoadCbo();
        LoadTrangthai();
        Loadngaygio();
    }

    public ArrayList<Ban> getListBan() {

        String sql = "SELECT * FROM ban";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ban b = new Ban();
                b.setMaBan(rs.getString(1));
                b.setTenBan(rs.getString(2));
                b.setTrangThai(rs.getString(3));
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void LoadCbo() {
        try {
            Connection con1 = con.getConnect();
            String sql = "select maban from ban";
            PreparedStatement pst = con1.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maban = rs.getString("maban");
                cboBan.addItem(maban);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void LoadTrangthai() {
        try {
            Connection con1 = con.getConnect();
            String sql = "select maban,trangthai from ban where maban='" + cboBan.getSelectedItem().toString() + "'";
            PreparedStatement pst = con1.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String tt = rs.getString("trangthai");
                txtTrangThai.setText(tt);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    ThucDon td;

    public void reloadPanel(int i) {
        jpThucDon.removeAll();
//        jpHoaDon.removeAll();
        switch (i) {
            case 1:
                if (td == null) {
                    td = new ThucDon();
                }
                jpThucDon.add(td);
                break;
        }
        jpThucDon.updateUI();
    }

    public boolean UpdateTrangThaiBan() {
        try {
            Connection con1 = con.getConnect();
            String id = (String) cboBan.getSelectedItem();
            txtTrangThai.setText("Dang phuc vu");
            String sql = "UPDATE ban SET TrangThai = ? WHERE maban = ?";
            PreparedStatement ps = con1.prepareStatement(sql);

            ps.setString(1, txtTrangThai.getText());
            ps.setString(2, (String) cboBan.getSelectedItem());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
    public boolean UpdateTrangThaiBanKhiThanhToan() {
        try {
            Connection con1 = con.getConnect();
            String id = (String) cboBan.getSelectedItem();
            txtTrangThai.setText("Trong");
            String sql = "UPDATE ban SET TrangThai = ? WHERE maban = ?";
            PreparedStatement ps = con1.prepareStatement(sql);

            ps.setString(1, txtTrangThai.getText());
            ps.setString(2, (String) cboBan.getSelectedItem());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
    public void Loadngaygio() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
      txtngay.setDate(date);
    }

    public void TaoHoaDon() {
        try {
            Connection con1 = con.getConnect();
            String sql = "INSERT INTO hoadon(MaBan,GioDen,TrangThai) VALUES(?,?,?)";
            PreparedStatement ps = con1.prepareStatement(sql);

            ps.setString(1, (String) cboBan.getSelectedItem());
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(2, df.format(date));
            ps.setString(3, "0");

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboBan = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        btnMuaHang = new javax.swing.JButton();
        txtngay = new com.toedter.calendar.JDateChooser();
        jpThucDon = new javax.swing.JPanel();

        setBackground(new java.awt.Color(42, 7, 7));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Algerian", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BÀN");

        cboBan.setBackground(new java.awt.Color(255, 255, 102));
        cboBan.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        cboBan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboBanItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("TRẠNG THÁI");

        txtTrangThai.setBackground(new java.awt.Color(255, 255, 204));
        txtTrangThai.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTrangThai.setEnabled(false);

        btnMuaHang.setBackground(new java.awt.Color(0, 102, 102));
        btnMuaHang.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\GoiMon.png")); // NOI18N
        btnMuaHang.setBorderPainted(false);
        btnMuaHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMuaHang.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btnMuaHang.setDefaultCapable(false);
        btnMuaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuaHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMuaHang)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cboBan, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)))
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(cboBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnMuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jpThucDon.setBackground(new java.awt.Color(42, 7, 7));
        jpThucDon.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboBanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboBanItemStateChanged
        // TODO add your handling code here:
        LoadTrangthai();
    }//GEN-LAST:event_cboBanItemStateChanged

    private void btnMuaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuaHangActionPerformed
        // TODO add your handling code here:
        reloadPanel(1);
        UpdateTrangThaiBan();
        TaoHoaDon();
    }//GEN-LAST:event_btnMuaHangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMuaHang;
    private javax.swing.JComboBox cboBan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpThucDon;
    private javax.swing.JTextField txtTrangThai;
    private com.toedter.calendar.JDateChooser txtngay;
    // End of variables declaration//GEN-END:variables
}
