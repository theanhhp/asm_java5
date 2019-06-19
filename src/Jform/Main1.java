/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jform;

import JformBanHang.BanHang;
import JformQuanLy.QuanLy;
import JformQuanLy.ThietLap;
import JformQuanLy.TrangChu;
import QLKho.QL_kho1;
import ThongKe.thongke;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.TaiKhoan;

/**
 *
 * @author hao
 */
public class Main1 extends javax.swing.JFrame {

    int timerun = 0;

    BanHang bh;
    QuanLy ql;
    thongke tke;
    QLKho.QL_kho1 qlk;
    Login lg = new Login();
    TrangChu tc = new TrangChu();
    ThietLap tl = new ThietLap();
    public static TaiKhoan tk;

    /**
     * Creates new form Main
     */
    public Main1() {
        initComponents();
        setLocationRelativeTo(null);
        time();
        this.lblQTV.setText(Login.manv);
    }

    public void time() {
        new Thread() {
            public void run() {
                while (timerun == 0) {
                    Calendar cal = new GregorianCalendar();
                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int AM_PM = cal.get(Calendar.AM_PM);
                    String day_night = "";

                    if (AM_PM == 1) {
                        day_night = "PM";
                    } else {
                        day_night = "AM";
                    }
                    String str = hour + ":" + minute + ":" + second + " " + day_night;
                    lblTime.setText(str);
                }
            }
        }.start();
    }

    public void reloadPanel(int i) {
        jpBanHang.removeAll();
        switch (i) {
            case 1:
                if (bh == null) {
                    bh = new BanHang();
                }
                jpBanHang.add(bh);
                break;
            case 2:
                if (ql == null) {
                    ql = new QuanLy();
                }
                jpBanHang.add(ql);
                break;
            case 3:
                if (tc == null) {
                    tc = new TrangChu();
                }
                jpBanHang.add(tc);
                break;
            case 4:
                if (tke == null) {
                    tke = new thongke();
                }
                jpBanHang.add(tke);
                break;
            case 5:
                if (qlk == null) {
                    qlk = new QL_kho1();
                }
                jpBanHang.add(qlk);
                break;

            case 6:
                if (tl == null) {
                    tl = new ThietLap();
                }
                jpBanHang.add(tl);
                break;
        }
        jpBanHang.updateUI();
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
        btnTrangChu = new javax.swing.JButton();
        btnBanHang = new javax.swing.JButton();
        btnThietLap = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        lblQTV = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jpMain = new javax.swing.JPanel();
        jpBanHang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        btnTrangChu.setBackground(new java.awt.Color(204, 255, 204));
        btnTrangChu.setIcon(new javax.swing.ImageIcon("C:\\Users\\trant\\eclipse-workspace\\asm\\src\\Icons\\home.png")); // NOI18N
        btnTrangChu.setText("Trang chủ");
        btnTrangChu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTrangChu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrangChu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        btnBanHang.setBackground(new java.awt.Color(204, 255, 204));
        btnBanHang.setIcon(new javax.swing.ImageIcon("C:\\Users\\trant\\eclipse-workspace\\asm\\src\\Icons\\banhang.png")); // NOI18N
        btnBanHang.setText("Bán hàng");
        btnBanHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBanHang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnThietLap.setBackground(new java.awt.Color(204, 255, 204));
        btnThietLap.setIcon(new javax.swing.ImageIcon("C:\\Users\\trant\\eclipse-workspace\\asm\\src\\Icons\\setting.png")); // NOI18N
        btnThietLap.setText("Giới thiệu");
        btnThietLap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThietLap.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThietLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThietLapActionPerformed(evt);
            }
        });

        btnDangXuat.setBackground(new java.awt.Color(204, 0, 0));
        btnDangXuat.setIcon(new javax.swing.ImageIcon("C:\\Users\\trant\\eclipse-workspace\\asm\\src\\Icons\\logout.png")); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        lblQTV.setForeground(new java.awt.Color(255, 204, 204));
        lblQTV.setText("jLabel2");

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(204, 255, 204));
        lblTime.setIcon(new javax.swing.ImageIcon("C:\\Users\\trant\\eclipse-workspace\\asm\\src\\Icons\\time.png")); // NOI18N
        lblTime.setText("Time:");

        jpMain.setBackground(new java.awt.Color(0, 51, 51));
        jpMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jpBanHang.setLayout(new java.awt.BorderLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\trant\\eclipse-workspace\\asm\\src\\Images\\main.jpg")); // NOI18N
        jpBanHang.add(jLabel2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jpMainLayout = new javax.swing.GroupLayout(jpMain);
        jpMain.setLayout(jpMainLayout);
        jpMainLayout.setHorizontalGroup(
            jpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpMainLayout.setVerticalGroup(
            jpMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThietLap, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(494, 494, 494)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTime)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblQTV, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                        .addComponent(btnDangXuat)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblQTV))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnThietLap, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBanHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnDangXuat)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        this.setVisible(false);
        lg.setVisible(true);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnThietLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThietLapActionPerformed
        // TODO add your handling code here:
        reloadPanel(6);
    }//GEN-LAST:event_btnThietLapActionPerformed

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        // TODO add your handling code here:
        reloadPanel(1);
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:
        reloadPanel(3);
    }//GEN-LAST:event_btnTrangChuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnThietLap;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpBanHang;
    private javax.swing.JPanel jpMain;
    private javax.swing.JLabel lblQTV;
    private javax.swing.JLabel lblTime;
    // End of variables declaration//GEN-END:variables
}
