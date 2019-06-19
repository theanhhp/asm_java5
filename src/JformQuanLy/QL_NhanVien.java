/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JformQuanLy;


import Connect.connect_1;
import java.awt.Color;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.nv;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author win
 */
public class QL_NhanVien extends javax.swing.JPanel {
 String filename = null;
    byte[] preson_image = null;
    String a, b, c;
    connect_1 hd = new connect_1();
    ResultSet rs;
    Statement st;
    private DefaultComboBoxModel cbo = new DefaultComboBoxModel();
    ArrayList<String> arr = new ArrayList<>();
    String ma;
    connect_1 cho = new connect_1();
    String head[] = {"ID",  "hovaten", "email", "ngaysinh", "gioitinh","sdt","cmnd", "diachi", "hinh"};
    DefaultTableModel model = new DefaultTableModel(head, 0);
    /**
     * Creates new form QL_NhanVien1
     */
    public QL_NhanVien() {
        initComponents();
        loaddata();
        ngayhientai();
    }
     private ArrayList<nv> getlist() {
        ArrayList<nv> getlist = new ArrayList<>();
        try {

            Connection conn = hd.getconection();
            String str = "select * from nhanvien";
            st = conn.createStatement();
            rs = st.executeQuery(str);
            nv a;
            while (rs.next()) {
                a = new nv(rs.getInt("id"), 
                      rs.getString("hovaten"),
                        rs.getString("email"), rs.getString("ngaysinh"), rs.getString("gioitinh"),
                        rs.getString("sdt"), rs.getString("diachi"), rs.getDouble("cmnd"), rs.getInt("lv"), rs.getBytes("hinh"));
                getlist.add(a);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return getlist;
    }
     public void ngayhientai(){  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        dcngay.setDate(date);
    
     }
 private void loaddata() {
        try {
            model.setRowCount(0);
            String user = "sa";
            String pass = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=qlcafe";
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "select * from nhanvien";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                row.add(rs.getString(7));
                row.add(rs.getString(8));
                row.add(rs.getString(9));
                model.addRow(row);
            }
            table.setModel(model);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
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

    public boolean LoiSo(String str) {
        Pattern pt = Pattern.compile("[0-9]");
        Matcher mc = pt.matcher(str);
        if (!mc.find()) {
            return false;
        }
        return true;
    }

    public boolean ngaysinh(String s2) {
        Pattern pt = Pattern.compile("((19|20)\\d\\d)/(0?[1-9]|1[012])/(0?[0-9]|[12][09]|3[01])");
        // 02/01/2018
        Matcher mt = pt.matcher(s2);
        if (!mt.find()) {
            return false;
        }
        return true;
    }

    public boolean nhapchu(String s3) {
        Pattern pt = Pattern.compile("a-zA-z");
        Matcher mt = pt.matcher(s3);
        if (!mt.find()) {
            return false;
        }
        return true;
    }

    public boolean email(String s) {
        Pattern pt = Pattern.compile("\\w+@\\w+\\.\\w+");
        // Phamngocdu@gmail.com
        Matcher mt = pt.matcher(s);
        if (!mt.find()) {
            return false;
        }
        return true;
    }
        public boolean maxminma(String s) {
        int i = s.length();
        if (i <9 || i > 9) {
            return false;
        }
        return true;
    }
        public boolean maximun(String a){
            int i = a.length();
            if(i <10 || i > 10){
        return false;
            }
     return true;
        }
public boolean check(){
    String ten = txtten.getText();
    String cmnd = txtcmnd.getText();
    String email = txtemail.getText();
    String sdt =txtsdt1.getText();
    String diachi = txtdiachi.getText();
    try {
        if(!matrong(ten)){
           txtten.requestFocusInWindow();
           JOptionPane.showMessageDialog(this, "Tên Không Được Bỏ Trống");
           return false;
        }
        if(!kitu(ten)){
            txtten.requestFocusInWindow();
            JOptionPane.showMessageDialog(this, "Tên Không Được Chứa Số");
            return false;
        }
        if(!matrong(cmnd)){
           txtcmnd.requestFocusInWindow();
           JOptionPane.showMessageDialog(this, "CMND Không Được Bỏ Trống");
           return false;
        }
        if(!LoiSo(cmnd)){
            txtcmnd.requestFocusInWindow();
            JOptionPane.showMessageDialog(this, "CMND Không Được Chứa Chữ");
            return false;
        }
        if(!maxminma(cmnd)){
            txtcmnd.requestFocusInWindow();
            JOptionPane.showMessageDialog(this, "CMND Không Được Bé Hơn Hoặc Lớn Hơn 9 Số");
            return false;
        }
        if(!matrong(email)){
           txtemail.requestFocusInWindow();
           JOptionPane.showMessageDialog(this, "Email Không Được Bỏ Trống");
           return false;
        }
        if(!email(email)){
            txtemail.requestFocusInWindow();
            JOptionPane.showMessageDialog(this, "Email Không Đúng Định Dạng ' coffechangtrai@gmail.com' ");
            return false;
        }
        if(!matrong(sdt)){
           txtsdt1.requestFocusInWindow();
           JOptionPane.showMessageDialog(this, "Số Điện Thoại Không Được Bỏ Trống");
           return false;
        }
        if(!LoiSo(sdt)){
            txtsdt1.requestFocusInWindow();
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Không Được Chứa Chữ");
        }
        if(!maximun(sdt)){
            txtsdt1.requestFocusInWindow();
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Phải Bằng 10 Số ");
            return false;
        }
        
        if(!matrong(diachi)){
           txtdiachi.requestFocusInWindow();
           JOptionPane.showMessageDialog(this, "Địa Chỉ Không Được Bỏ Trống");
           return false;
        }
    } catch (Exception e) {
        System.out.println(e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel40 = new javax.swing.JPanel();
        txtemail = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        nam = new javax.swing.JRadioButton();
        nu = new javax.swing.JRadioButton();
        hinh = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtsdt1 = new javax.swing.JTextField();
        cbnchon = new javax.swing.JButton();
        txtten = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        txtcmnd = new javax.swing.JTextField();
        jLabel142 = new javax.swing.JLabel();
        dcngay = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdiachi = new javax.swing.JTextArea();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnnew = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setToolTipText("aa");

        jPanel40.setBackground(new java.awt.Color(204, 204, 255));
        jPanel40.setPreferredSize(new java.awt.Dimension(865, 517));

        txtemail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtemailMouseClicked(evt);
            }
        });
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });

        jLabel134.setText("Email");

        jLabel138.setText("Số Điện Thoại");

        jLabel139.setText("Giới Tính");

        buttonGroup1.add(nam);
        nam.setSelected(true);
        nam.setText("Nam");
        nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namActionPerformed(evt);
            }
        });

        buttonGroup1.add(nu);
        nu.setText("Nữ");
        nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuActionPerformed(evt);
            }
        });

        hinh.setBackground(new java.awt.Color(102, 102, 102));
        hinh.setOpaque(true);

        jLabel140.setText("Tên");

        jLabel1.setText("Ngày Sinh");

        txtsdt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsdt1MouseClicked(evt);
            }
        });
        txtsdt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsdt1ActionPerformed(evt);
            }
        });

        cbnchon.setText("ChỌN");
        cbnchon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnchonActionPerformed(evt);
            }
        });

        txtten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttenMouseClicked(evt);
            }
        });
        txtten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenActionPerformed(evt);
            }
        });

        jLabel141.setText("Cmnd");

        txtcmnd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcmndMouseClicked(evt);
            }
        });
        txtcmnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcmndActionPerformed(evt);
            }
        });

        jLabel142.setText("Địa Chỉ");

        txtdiachi.setColumns(20);
        txtdiachi.setRows(5);
        jScrollPane1.setViewportView(txtdiachi);

        btnthem.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\save.png")); // NOI18N
        btnthem.setText("Save");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\update.png")); // NOI18N
        btnsua.setText("Update");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\delete.png")); // NOI18N
        btnxoa.setText("Delete");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnnew.setIcon(new javax.swing.ImageIcon("C:\\Users\\HOME\\Documents\\NetBeansProjects\\DAM1\\src\\Icons\\new.png")); // NOI18N
        btnnew.setText("New");
        btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hinh, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(cbnchon, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dcngay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtten)
                                .addComponent(txtcmnd, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtemail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel40Layout.createSequentialGroup()
                                        .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8))
                                    .addComponent(jLabel138, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel40Layout.createSequentialGroup()
                                        .addComponent(nam)
                                        .addGap(46, 46, 46)
                                        .addComponent(nu, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtsdt1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnthem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnsua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnxoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(204, 204, 204)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(hinh, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbnchon))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(32, 32, 32)
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel141)
                                    .addComponent(txtcmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsdt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nam)
                            .addComponent(nu))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );

        jDesktopPane1.setLayer(jPanel40, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thêm Nhân Viên", jDesktopPane1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 90, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Danh Sách Sinh Viên", jPanel1);

        jTabbedPane1.addTab("Quản Lý Nhân Viên", jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1001, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtemailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtemailMouseClicked
        txtemail.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtemailMouseClicked

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed

    }//GEN-LAST:event_txtemailActionPerformed

    private void namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namActionPerformed

    private void nuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuActionPerformed

    private void txtsdt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsdt1MouseClicked
        txtsdt1.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtsdt1MouseClicked

    private void txtsdt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsdt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsdt1ActionPerformed

    private void cbnchonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnchonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filename = f.getAbsolutePath();
        ImageIcon icon = new ImageIcon("D:\\Java 4\\DuAn1\\src\\icon " + filename);
        icon = new ImageIcon(new ImageIcon(filename).getImage().
            getScaledInstance(hinh.getWidth(), hinh.getHeight(), Image.SCALE_SMOOTH));
        hinh.setIcon(icon);

        try {
            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bs = new byte[1024];
            for (int i; (i = fis.read(bs)) != -1;) {
                baos.write(bs, 0, i);
            }
            preson_image = baos.toByteArray();
        } catch (Exception e) {
            System.out.println("loi he thong");
        }
    }//GEN-LAST:event_cbnchonActionPerformed

    private void txttenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenMouseClicked

    private void txttenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenActionPerformed

    private void txtcmndMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcmndMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcmndMouseClicked

    private void txtcmndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcmndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcmndActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
if(check()){

            try {
                Connection con = hd.getconection();
                String b = "insert into nhanvien(hovaten,email,ngaysinh,gioitinh,sdt,cmnd,diachi,hinh)values (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(b);

                pst.setString(1, txtten.getText());
                pst.setString(2, txtemail.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(dcngay.getDate());
                pst.setString(3, date);
                if (nam.isSelected()) {
                    a = "Nam";
                }
                if (nu.isSelected()) {
                    a = "Nữ";
                }
                pst.setString(4, a);
                pst.setString(5, txtsdt1.getText());
                pst.setString(6, txtcmnd.getText());

                pst.setString(7, txtdiachi.getText());
                pst.setBytes(8, preson_image);
                pst.executeUpdate();
                model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                loaddata();
                neww();
                JOptionPane.showMessageDialog(null, "thành công");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
              
            try {
                Connection conn = hd.getconection();
                int row = table.getSelectedRow();
                String value = (table.getModel().getValueAt(row, 0).toString());
                String b = "update nhanvien set hovaten=?, email=?, ngaysinh=?, gioitinh=?, sdt=?,cmnd =?,diachi=?,hinh=? where id=" + value;
                PreparedStatement pst = conn.prepareStatement(b);
              
                pst.setString(1, txtten.getText());
                pst.setString(2, txtemail.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(dcngay.getDate());
                pst.setString(3, date);
                if (nam.isSelected()) {
                    a = "Nam";
                }
                if (nu.isSelected()) {
                    a = "Nữ";
                }
                pst.setString(4, a);
                pst.setString(5, txtsdt1.getText());
                pst.setString(6, txtcmnd.getText());

                pst.setString(7, txtdiachi.getText());
                pst.setBytes(8, preson_image);
                pst.executeUpdate();
                model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                loaddata();
                JOptionPane.showMessageDialog(null, "thành công");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
       try {
            Connection conn = hd.getconection();
            int row = table.getSelectedRow();
            String value = (table.getModel().getValueAt(row, 0).toString());
            String query = "delete from nhanvien where id=" + value;
            PreparedStatement st = conn.prepareStatement(query);
            st.executeUpdate();
            model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            loaddata();
            JOptionPane.showMessageDialog(null, "Xóa thành công !");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnxoaActionPerformed
public void neww(){
             txtdiachi.setText("");
        txtemail.setText("");
        txtsdt1.setText("");
        txtten.setText("");
        hinh.setIcon(null);
        txtcmnd.setText("");
}
    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
        neww();
    }//GEN-LAST:event_btnnewActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
       try {

            Connection conn = hd.getconection();
            int i = table.getSelectedRow();
            TableModel model = table.getModel();
            txtten.setText(model.getValueAt(i, 1).toString());
            txtemail.setText(model.getValueAt(i, 2).toString());
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(i, 3));
                dcngay.setDate(date);

            } catch (Exception e) {

            }
            String gioitinh = (model.getValueAt(i, 4).toString());
            if (gioitinh.equals("Nam")) {
                nam.setSelected(true);

            } else {
                nu.setSelected(true);

            }
            txtsdt1.setText(model.getValueAt(i, 5).toString());

            txtcmnd.setText(model.getValueAt(i, 6).toString());
            txtdiachi.setText(model.getValueAt(i, 7).toString());
            
            try {

                byte[] img = (getlist().get(i).getHinh());
                ImageIcon icon = new ImageIcon(new ImageIcon(img).getImage().
                        getScaledInstance(hinh.getWidth(), hinh.getHeight(), Image.SCALE_SMOOTH));
                hinh.setIcon(icon);
            } catch (Exception e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnnew;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cbnchon;
    private com.toedter.calendar.JDateChooser dcngay;
    private javax.swing.JLabel hinh;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton nam;
    private javax.swing.JRadioButton nu;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtcmnd;
    private javax.swing.JTextArea txtdiachi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtsdt1;
    private javax.swing.JTextField txtten;
    // End of variables declaration//GEN-END:variables
}
