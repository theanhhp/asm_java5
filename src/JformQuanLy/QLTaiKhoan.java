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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.TaiKhoan;

/**
 *
 * @author hao
 */
public class QLTaiKhoan extends javax.swing.JPanel {

    ArrayList<TaiKhoan> list = new ArrayList<>();
    DefaultTableModel model;
    int index = 0;
    private Connection conn;

    /**
     * Creates new form QLTaiKhoan
     */
    public QLTaiKhoan() {
        initComponents();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe;user=sa;password=123");
        } catch (Exception e) {
            System.out.println(e);
        }
        list = (ArrayList<TaiKhoan>) getListTaiKhoan();
        model = (DefaultTableModel) tblTaiKhoan.getModel();
        loadDbToTable();

    }

    public void loadDbToTable() {
        try {
            model.setRowCount(0);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM taikhoan");
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getInt(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getBoolean(4));
                model.addRow(row);
            }
            tblTaiKhoan.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void fillTable() {
        model.setRowCount(0);
        for (TaiKhoan s : list) {
            Object[] row = new Object[]{s.getID(), s.getUsername(), s.getPassword(), s.isLv()};
            model.addRow(row);
        }
    }

    public void showDetail(int index) {
        txtID.setText(list.get(index).getID());
        txtUsername.setText(list.get(index).getUsername());
        txtPassword.setText(list.get(index).getPassword());
        if (list.get(index).isLv() == true) {
            rdoNhanVien.setSelected(true);
        } else {
            rdoAdmin.setSelected(true);
        }

    }

    public ArrayList<TaiKhoan> getListTaiKhoan() {

        String sql = "SELECT * FROM taikhoan";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setID(rs.getString(1));
                tk.setUsername(rs.getString(2));
                tk.setPassword(rs.getString(3));
                tk.setLv(rs.getBoolean(4));
                list.add(tk);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean check() {
        if (txtID.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống ID");
            txtID.setBackground(Color.red);
            return false;
        } else if (txtUsername.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống Username");
            txtUsername.setBackground(Color.red);
            return false;
        } else if (txtPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống Password");
            txtPassword.setBackground(Color.red);
            return false;
        }
        return true;
    }

    public void save() {
        if (check()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe;user=sa;password=123");
                String sql = "INSERT INTO taikhoan VALUES(?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, txtID.getText());
                ps.setString(2, txtUsername.getText());
                ps.setString(3, txtPassword.getText());
                boolean lv;
                if (rdoAdmin.isSelected()) {
                    lv = true;
                } else {
                    lv = false;
                }
                ps.setBoolean(4, lv);
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
        txtID.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }

    public boolean update() {
        try {
            String id = txtID.getText();
            String sql = "UPDATE taikhoan SET username = ?, passwords = ?, lv = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, txtUsername.getText());
            ps.setString(2, txtPassword.getText());
            boolean lv;
            if (rdoAdmin.isSelected()) {
                lv = true;
            } else {
                lv = false;
            }
            ps.setBoolean(3, lv);
            ps.setString(4, txtID.getText());
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
            String id = txtID.getText();
            String sql = "DELETE FROM taikhoan WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            loadDbToTable();
            JOptionPane.showMessageDialog(this, "Delete thành công");
            clear();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnSave1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnNew1 = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        rdoNhanVien = new javax.swing.JRadioButton();
        rdoAdmin = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();

        jPanel8.setBackground(new java.awt.Color(255, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("QUẢN LÝ TÀI KHOẢN");

        jLabel9.setText("Username:");

        jLabel10.setText("Password:");

        jLabel11.setText("Level:");

        btnSave1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\save.png")); // NOI18N
        btnSave1.setText("Save");
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        btnDelete1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\delete.png")); // NOI18N
        btnDelete1.setText("Delete");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        btnUpdate1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\update.png")); // NOI18N
        btnUpdate1.setText("Update");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        btnNew1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\new.png")); // NOI18N
        btnNew1.setText("New");
        btnNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setSelected(true);
        rdoNhanVien.setText("Nhan vien");

        buttonGroup1.add(rdoAdmin);
        rdoAdmin.setText("Admin");

        jLabel12.setText("ID:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(403, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 58, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDelete1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNew1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtID)
                                .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                    .addComponent(rdoNhanVien)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rdoAdmin))
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(51, 51, 51))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(79, 79, 79)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(rdoNhanVien)
                    .addComponent(rdoAdmin))
                .addGap(63, 63, 63)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave1)
                    .addComponent(btnNew1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUpdate1)
                    .addComponent(btnDelete1))
                .addGap(46, 46, 46))
        );

        tblTaiKhoan.setBackground(new java.awt.Color(255, 204, 255));
        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Username", "Password", "Level"
            }
        ));
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblTaiKhoan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnNew1ActionPerformed

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        // TODO add your handling code here:
        try {
            index = tblTaiKhoan.getSelectedRow();
            showDetail(index);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDelete1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnNew1;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rdoAdmin;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JTable tblTaiKhoan;
    private javax.swing.JTextField txtID;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
