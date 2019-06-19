/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JformBanHang;

import Connect.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.HoaDonCT;

/**
 *
 * @author hao
 */
public class ThucDon extends javax.swing.JPanel {

    ArrayList<HoaDonCT> list = new ArrayList<>();
    DefaultTableModel model;
    int index = 0;
    Connect con = new Connect();
    BanHang banhang = new BanHang();
    private Connection conn;
    String head[] = {"ID", "Tên món", "ĐVT", "Số lượng", "Giá"};
    DefaultTableModel model1 = new DefaultTableModel(head, 0);

    /**
     * Creates new form NhomMon
     */
    public ThucDon() {
        initComponents();
        try {
            Connection con1 = con.getConnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        list = (ArrayList<HoaDonCT>) getListHoaDonCT();
        model = (DefaultTableModel) tblHDCT.getModel();
        LoadCboLoai();
        LoadLblTenMon();
//        loadDbToTable();
        LoadMaHoaDon();
    }

    public ArrayList<HoaDonCT> getListHoaDonCT() {

        String sql = "SELECT * FROM chitiethd";
        try {
            Connection con1 = con.getConnect();
            PreparedStatement ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonCT hdct = new HoaDonCT();
                hdct.setMaHDCT(rs.getInt(1));
                hdct.setMaHD(rs.getInt(2));
                hdct.setTenMon(rs.getString(3));
                hdct.setDVT(rs.getString(4));
                hdct.setSoLuong(rs.getInt(5));
                hdct.setGia(rs.getInt(4));
                list.add(hdct);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void fillTable() {
        model.setRowCount(0);
        for (HoaDonCT s : list) {
            Object[] row = new Object[]{s.getMaHDCT(), s.getTenMon(), s.getDVT(), s.getSoLuong(), s.getGia()};
            model.addRow(row);
        }
    }

    public void loadDbToTable() {
        try {
            model.setRowCount(0);
            Connection con1 = con.getConnect();
            Statement st = con1.createStatement();
            ResultSet rs = st.executeQuery("SELECT MaChiTietHD,TenMon,DVT,SoLuong,Gia "
                    + "FROM chitiethd where MaHoaDon='" + lblMaHoaDon.getText() + "'");
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));

                model.addRow(row);
            }
            tblHDCT.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
  public boolean kitu(String s2) {
        Pattern pt = Pattern.compile("[a-zA-Z]");
        Matcher mc = pt.matcher(s2);
        if (!mc.find()) {
            return false;
        }
        return true;
    }
public boolean check(){
    try {
    String soluong = txtSoLuong.getText();
    if(kitu(soluong)){
        txtSoLuong.requestFocusInWindow();
        JOptionPane.showMessageDialog(this, "Số Lượng Không Được Nhập Chữ");
        return false;
    }
     } catch (Exception e) {
         System.out.println(e);
    }
    return true;  
}
    public void saveHoaDonCT() {
         int gia = Integer.parseInt(lblGia.getText());
        int sl = Integer.parseInt(txtSoLuong.getText());
        int tongGia = gia * sl;
      
        
            try {
            Connection con1 = con.getConnect();
            String sql = "INSERT INTO chitiethd(MaHoaDon,TenMon,DVT,SoLuong,Gia) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con1.prepareStatement(sql);

            ps.setString(1, lblMaHoaDon.getText());
            ps.setString(2, lblTenMon.getText());
            ps.setString(3, lblDVT.getText());
            ps.setString(4, txtSoLuong.getText());
            ps.setString(5, String.valueOf(tongGia));

            ps.executeUpdate();
            fillTable();
            loadDbToTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void LoadCboLoai() {
        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe;user=sa;password=123");
            Connection con1 = con.getConnect();
            String sql = "select MaLoai from nhommon";
            PreparedStatement pst = con1.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String ml = rs.getString("MaLoai");
                cboLoai.addItem(ml);
            }
            con1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void LoadCboMon() {
        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe;user=sa;password=123");
            Connection con1 = con.getConnect();
            String sql = "select TenMon from thucdon where MaLoai='" + cboLoai.getSelectedItem().toString() + "'";
            PreparedStatement pst = con1.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            cboMon.removeAllItems();
            while (rs.next()) {
                String tm = rs.getString("TenMon");
                cboMon.addItem(tm);
            }
            con1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void LoadLblTenMon() {
        try {
            Connection con1 = con.getConnect();
            String sql = "select TenMon,DonGia,DVT from thucdon where TenMon='" + cboMon.getSelectedItem().toString() + "'";
            PreparedStatement pst = con1.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String tm = rs.getString("TenMon");
                lblTenMon.setText(tm);
                String dg = rs.getString("DonGia");
                lblGia.setText(dg);
                String dvt = rs.getString("DVT");
                lblDVT.setText(dvt);
            }
            con1.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
//
//    public void TinhTongTien() {
//        int gia = Integer.parseInt(lblGia.getText());
//        int sl = Integer.parseInt(txtSoLuong.getText());
//        lblTongTien.setText(String.valueOf(sl * gia));
//    }

    public void TinhTienTraLai() {
        int tong = Integer.parseInt(lblTongTien.getText());
        int nhan = Integer.parseInt(txtNhan.getText());
        lblTraLai.setText(String.valueOf(nhan - tong));
    }

    public boolean UpdateTrangThaiBan() {
        BanHang banhang = new BanHang();
        banhang.UpdateTrangThaiBanKhiThanhToan();
        return false;
    }

    public void LoadMaHoaDon() {
        try {
            Connection con1 = con.getConnect();
            String sql = "SELECT Max(MaHoaDon) as LastID FROM hoadon";
            PreparedStatement pst = con1.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("LastID");
                lblMaHoaDon.setText(ma);

            }
            con1.close();
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }
    public void LoadTongGia() {
        try {
            Connection con1 = con.getConnect();
            String sql = "SELECT sum(Gia) as tonggia FROM chitiethd WHERE MaHoaDon='" + lblMaHoaDon.getText() + "'";
            PreparedStatement pst = con1.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String tg = rs.getString("tonggia");
                lblTongTien.setText(tg);

            }
            con1.close();
        } catch (Exception ex) {
            System.out.println(ex);

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboLoai = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        btnChon = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cboMon = new javax.swing.JComboBox();
        lblTenMon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        lblDVT = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        lblGia1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblTraLai = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtNhan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(51, 0, 0));

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Algerian", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU");

        cboLoai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 255));
        jLabel2.setText("LOẠI");

        btnChon.setBackground(new java.awt.Color(153, 0, 0));
        btnChon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChon.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\mua.png")); // NOI18N
        btnChon.setText("CHỌN");
        btnChon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 255, 255));
        jLabel3.setText("MÓN");

        cboMon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMonItemStateChanged(evt);
            }
        });

        lblTenMon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTenMon.setForeground(new java.awt.Color(255, 255, 255));
        lblTenMon.setText(".....");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ĐVT:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Đơn giá:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tên món:");

        lblGia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGia.setForeground(new java.awt.Color(255, 255, 255));
        lblGia.setText(".....");

        lblDVT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDVT.setForeground(new java.awt.Color(255, 255, 255));
        lblDVT.setText(".....");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Số lượng:");

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSoLuong.setText("1");
        txtSoLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        lblGia1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGia1.setForeground(new java.awt.Color(255, 255, 255));
        lblGia1.setText("VNĐ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2))
                    .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTenMon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblDVT))
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(lblGia1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel3))
                                    .addComponent(cboMon, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenMon)
                    .addComponent(jLabel7))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblGia)
                    .addComponent(lblGia1))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblDVT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnChon)
                .addGap(28, 28, 28))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("HÓA ĐƠN");

        tblHDCT.setBackground(new java.awt.Color(0, 102, 102));
        tblHDCT.setBorder(new javax.swing.border.MatteBorder(null));
        tblHDCT.setForeground(new java.awt.Color(255, 255, 255));
        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên món", "ĐVT", "Số lượng", "Giá"
            }
        ));
        jScrollPane2.setViewportView(tblHDCT);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tổng tiền:");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(204, 255, 51));
        lblTongTien.setText(".....");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nhận:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Trả lại:");

        lblTraLai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTraLai.setForeground(new java.awt.Color(102, 255, 102));
        lblTraLai.setText(".....");

        jButton1.setBackground(new java.awt.Color(0, 0, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\print.png")); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\thanhtoan.png")); // NOI18N
        jButton2.setText("Thanh toán");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtNhan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNhanActionPerformed(evt);
            }
        });
        txtNhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNhanKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 255, 102));
        jLabel10.setText("VNĐ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 255, 102));
        jLabel12.setText("VNĐ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Mã hóa đơn:");

        lblMaHoaDon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMaHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblMaHoaDon.setText(".....");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 255, 102));
        jLabel15.setText("VNĐ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMaHoaDon)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(166, 166, 166))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txtNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(23, 23, 23)
                                .addComponent(lblTraLai)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(44, 44, 44))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(34, 34, 34)
                                        .addComponent(lblTongTien)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10))
                                    .addComponent(jButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(24, 24, 24)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblMaHoaDon))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblTongTien)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(lblTraLai)
                    .addComponent(txtNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(13, 13, 13))
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

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
if(check()){
    // TODO add your handling code here:
        saveHoaDonCT();
        loadDbToTable();
        LoadTongGia();
}
    }//GEN-LAST:event_btnChonActionPerformed

    private void cboLoaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiItemStateChanged
        // TODO add your handling code here:
        LoadCboMon();
    }//GEN-LAST:event_cboLoaiItemStateChanged

    private void cboMonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMonItemStateChanged
        // TODO add your handling code here:
        LoadLblTenMon();
    }//GEN-LAST:event_cboMonItemStateChanged

    private void txtNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhanActionPerformed

    private void txtNhanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhanKeyReleased
        // TODO add your handling code here:
 
            TinhTienTraLai();
        
    }//GEN-LAST:event_txtNhanKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        lblTongTien.setText(".....");
        txtNhan.setText(null);
        lblTraLai.setText(".....");

        banhang.UpdateTrangThaiBanKhiThanhToan();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
                GregorianCalendar gg = new GregorianCalendar();
        SimpleDateFormat dd = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat ddd = new SimpleDateFormat("HH:mm");
        MessageFormat header = new MessageFormat("***4 chàng trai Coffee*** -- Hóa đơn");
        MessageFormat lead = new MessageFormat("Date :" + dd.format(gg.getTime()) + "Time" + ddd.format(gg.getTime()));
        MessageFormat footer = new MessageFormat("Hẹn gặp lại quý khách -- "+"Page{0,number,integer}");
        try {
            tblHDCT.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot", head);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.JComboBox cboLoai;
    private javax.swing.JComboBox cboMon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDVT;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblGia1;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblTenMon;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTraLai;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTextField txtNhan;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
