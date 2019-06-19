/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JformQuanLy;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Ban;

/**
 *
 * @author hao
 */
public class QLBan extends javax.swing.JPanel {

    ArrayList<Ban> list = new ArrayList<>();
    DefaultTableModel model;
    int index = 0;
    private Connection conn;

    /**
     * Creates new form QLBan
     */
    public QLBan() {
        initComponents();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe;user=sa;password=123");
        } catch (Exception e) {
            System.out.println(e);
        }
        list = (ArrayList<Ban>) getListBan();
        model = (DefaultTableModel) tblBan.getModel();
        loadDbToTable();
    }

    public void loadDbToTable() {
        try {
            model.setRowCount(0);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ban");
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getInt(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                model.addRow(row);
            }
            tblBan.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void fillTable() {
        model.setRowCount(0);
        for (Ban s : list) {
            Object[] row = new Object[]{s.getMaBan(), s.getTenBan(), s.getTrangThai()};
            model.addRow(row);
        }
    }

    public void showDetail(int index) {
        txtMaBan.setText(list.get(index).getMaBan());
        txtTenBan.setText(list.get(index).getTenBan());
        txtTrangThai.setText(list.get(index).getTrangThai());
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

    public boolean check() {
        if (txtTenBan.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên bàn");
            txtTenBan.setBackground(Color.red);
            return false;
        }
        return true;
    }

    public void save() {
        if (check()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe;user=sa;password=123");
                String sql = "INSERT INTO ban VALUES(?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, txtTenBan.getText());
                ps.setString(2, txtTrangThai.getText());
                ps.executeUpdate();
                fillTable();
                loadDbToTable();
                JOptionPane.showMessageDialog(this, "Lưu thành công");
                clear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void clear() {
        txtMaBan.setText("");
        txtTenBan.setText("");
    }

    public boolean update() {
        try {
            String id = txtMaBan.getText();
            String sql = "UPDATE ban SET tenban = ? WHERE maban = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, txtTenBan.getText());
            ps.setString(2, txtMaBan.getText());
            ps.executeUpdate();
            loadDbToTable();
            JOptionPane.showMessageDialog(this, "Update thành công");
            clear();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
        public boolean delete() {
        try {
            String maban = txtMaBan.getText();
            String sql = "DELETE FROM ban WHERE maban = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maban);
            ps.executeUpdate();
            loadDbToTable();
            JOptionPane.showMessageDialog(this, "Delete thành công");
            clear();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
public boolean matrong(String s) {
        if (s.equals("")) {
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
        jLabel2 = new javax.swing.JLabel();
        txtTenBan = new javax.swing.JTextField();
        txtTrangThai = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaBan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBan = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Tên bàn:");

        jLabel2.setText("Trạng thái:");

        txtTrangThai.setText("Trống");
        txtTrangThai.setEnabled(false);
        txtTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangThaiActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("QUẢN LÝ BÀN");

        btnSave.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\save.png")); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\delete.png")); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\update.png")); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNew.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\new.png")); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã bàn:");

        txtMaBan.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(22, 22, 22)
                                    .addComponent(txtMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addGap(22, 22, 22)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTrangThai)
                                        .addComponent(txtTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnUpdate)
                                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel8)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(77, 77, 77)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(43, 43, 43))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblBan.setBackground(new java.awt.Color(204, 255, 204));
        tblBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã bàn", "Tên bàn", "Trạng thái"
            }
        ));
        tblBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangThaiActionPerformed

    private void tblBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBanMouseClicked
        // TODO add your handling code here:
           try {
            index = tblBan.getSelectedRow();
            showDetail(index);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblBanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBan;
    private javax.swing.JTextField txtMaBan;
    private javax.swing.JTextField txtTenBan;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
