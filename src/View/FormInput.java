/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Service.SVinput;
import java.io.File;
import javax.swing.JFileChooser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mode.Diem;
import mode.Lop;
import mode.Sinhvien;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Service.SVinputdd;

/**
 *
 * @author dell
 */
public class FormInput extends javax.swing.JFrame {

    DefaultTableModel tbmModel = new DefaultTableModel();
    DefaultTableModel tbmModeldd = new DefaultTableModel();
    Workbook _wb = new HSSFWorkbook();
    static String _path = null;
    static List<Diem> _lstdiem = new ArrayList<>();
    static List<Sinhvien> _lstSV = new ArrayList<>();
    static Lop _lop = new Lop();

    /**
     * Creates new form mmm
     */
    public FormInput() {
        initComponents();
        this.setTitle("Nhập dữ liệu");
        setLocationRelativeTo(this);
        setResizable(false);
        setformtabquiz();
    }

    private void setformtabquiz() {
        tbmModel = (DefaultTableModel) tbinput.getModel();
        tbmModeldd = (DefaultTableModel) tbinput1.getModel();
        tbmModeldd.setRowCount(0);
        tbmModeldd.setColumnCount(0);
        tbmModel.setColumnCount(0);
        tbmModel.setRowCount(0);
        groupradio();
        lblbgr.setIcon(new ImageIcon("img/bgr.jpg"));
        btnquiz.setIcon(new ImageIcon("img/file.png"));
        btndd1.setIcon(new ImageIcon("img/file.png"));
        btnnextform1.setIcon(new ImageIcon("img/next.png"));
    }

    private void groupradio() {
        ButtonGroup group = new ButtonGroup();
        group.add(rd75);
        group.add(rd10);
        rd10.setSelected(true);
    }

    private void loadtbquiz(String path) {
        tbmModel.setRowCount(0);
        tbmModel.setColumnCount(0);
        Workbook wb = Service.SVinput.getwb(path);
        _lstSV = Service.SVinput.getListSV(wb);
        _lstdiem = Service.SVinput.getlstDiems(wb);
        _lop = Service.SVinput.getlop(wb);
        String colums[] = {"STT", "Mã SV", "Tên SV", "Quiz 1", "Quiz 2", "Quiz 3", "Quiz 4", "Quiz 5", "Quiz 6", "Quiz 7", "Quiz 8", "Quiz 9", "Quiz 10"};
        if (_lop.getCheck() == 1) {
            rd10.setSelected(true);
            for (int j = 0; j < colums.length; j++) {
                tbmModel.addColumn(colums[j]);
                if (j == 10 && _lop.getSodiemquiz() < 10) {
                    break;
                }
            }
            for (int j = 0; j < _lstSV.size(); j++) {
                tbmModel.addRow(new Object[]{j + 1, _lstSV.get(j).getMasv(), _lstSV.get(j).getTensv(),
                    _lstdiem.get(j).getDiem1(), _lstdiem.get(j).getDiem2(), _lstdiem.get(j).getDiem3(),
                    _lstdiem.get(j).getDiem4(), _lstdiem.get(j).getDiem5(), _lstdiem.get(j).getDiem6(),
                    _lstdiem.get(j).getDiem7(), _lstdiem.get(j).getDiem8(), _lstdiem.get(j).getDiem9(), _lstdiem.get(j).getDiem10()});
            }
        }
        if (_lop.getCheck() == 2) {
            rd75.setSelected(true);
            for (int i = 0; i < 3; i++) {
                tbmModel.addColumn(colums[i]);
            }
            tbmModel.addColumn("Điểm online");
            for (int i = 0; i < _lstSV.size(); i++) {
                tbmModel.addRow(new Object[]{i + 1, _lstSV.get(i).getMasv(), _lstSV.get(i).getTensv(), _lstdiem.get(i).getDiemonl()});
            }
        }
        if (_lop.getCheck() == 0) {
            for (int i = 0; i < 3; i++) {
                tbmModel.addColumn(colums[i]);
            }
            for (int i = 0; i < _lstSV.size(); i++) {
                tbmModel.addRow(new Object[]{i + 1, _lstSV.get(i).getMasv(), _lstSV.get(i).getTensv()});
            }
        }
    }

    private void loadtbdd(List<Sinhvien> lst) {
        try {
            tbmModeldd.setRowCount(0);
            tbmModeldd.setColumnCount(0);
            String comlums[] = {"STT", "Mã sinh viên", "Tên sinh viên", "Tỉ lệ nghỉ"};
            for (String comlum : comlums) {
                tbmModeldd.addColumn(comlum);
            }
            int i = 0;
            for (Sinhvien x : lst) {
                tbmModeldd.addRow(new Object[]{i++, x.getMasv(), x.getTensv(), x.getTilenghi() + "%"});
            }
        } catch (Exception e) {
            System.out.println("loi" + e);
        }
    }

    private String chooser() {
        try {
            File srcFile = null;
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                srcFile = chooser.getSelectedFile();
            }
            _path = srcFile.getAbsolutePath();
            return _path;
        } catch (Exception e) {
            return null;
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

        jToolBar1 = new javax.swing.JToolBar();
        btndd1 = new javax.swing.JButton();
        btnquiz = new javax.swing.JButton();
        btnnextform1 = new javax.swing.JButton();
        tabtb = new javax.swing.JTabbedPane();
        pndiemquiz = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbinput = new javax.swing.JTable();
        lbl_chek = new javax.swing.JLabel();
        rd10 = new javax.swing.JRadioButton();
        rd75 = new javax.swing.JRadioButton();
        lbllop = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbinput1 = new javax.swing.JTable();
        lbl_chek1 = new javax.swing.JLabel();
        txttilenghi = new javax.swing.JTextField();
        lblbgr = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar1.setRollover(true);

        btndd1.setBackground(new java.awt.Color(255, 255, 255));
        btndd1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btndd1.setForeground(new java.awt.Color(255, 0, 51));
        btndd1.setText("Nhập File điểm Quiz");
        btndd1.setFocusable(false);
        btndd1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btndd1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btndd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndd1ActionPerformed(evt);
            }
        });
        jToolBar1.add(btndd1);

        btnquiz.setBackground(new java.awt.Color(255, 255, 255));
        btnquiz.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnquiz.setForeground(new java.awt.Color(255, 0, 51));
        btnquiz.setText("Nhập File điểm danh");
        btnquiz.setFocusable(false);
        btnquiz.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnquiz.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnquiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquizActionPerformed(evt);
            }
        });
        jToolBar1.add(btnquiz);

        btnnextform1.setBackground(new java.awt.Color(255, 255, 255));
        btnnextform1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnnextform1.setForeground(new java.awt.Color(255, 0, 51));
        btnnextform1.setText("Danh sách thi");
        btnnextform1.setFocusable(false);
        btnnextform1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnextform1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnnextform1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextform1ActionPerformed(evt);
            }
        });
        jToolBar1.add(btnnextform1);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 60));

        tabtb.setBackground(new java.awt.Color(204, 255, 255));
        tabtb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        pndiemquiz.setBackground(new java.awt.Color(255, 255, 255));

        tbinput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbinput);

        lbl_chek.setText("Check diem lab: ");

        rd10.setText("Diem = 10");

        rd75.setText("Diem >= 7.5");

        lbllop.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbllop.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout pndiemquizLayout = new javax.swing.GroupLayout(pndiemquiz);
        pndiemquiz.setLayout(pndiemquizLayout);
        pndiemquizLayout.setHorizontalGroup(
            pndiemquizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndiemquizLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_chek)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rd10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rd75)
                .addGap(36, 36, 36)
                .addComponent(lbllop, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1225, Short.MAX_VALUE)
        );
        pndiemquizLayout.setVerticalGroup(
            pndiemquizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pndiemquizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pndiemquizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rd75)
                    .addComponent(rd10)
                    .addComponent(lbl_chek)
                    .addComponent(lbllop, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
        );

        tabtb.addTab("Danh sách điểm Quiz", pndiemquiz);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbinput1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbinput1);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1205, 362));

        lbl_chek1.setText("Tỉ lệ được phép nghỉ (%):");
        jPanel2.add(lbl_chek1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel2.add(txttilenghi, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 70, -1));

        tabtb.addTab("Danh sách điểm danh", jPanel2);

        getContentPane().add(tabtb, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 1230, 450));
        getContentPane().add(lblbgr, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 670));

        jMenu1.setText("File");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Import file Excel");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        jMenuItem4.setText("Danh sách thi");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        jMenuItem5.setText("Kế hoạch thi");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem6.setText("Đăng xuất");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void btnquizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquizActionPerformed
//        String l = "C:\\Users\\dell\\Desktop\\ppp.xlsx";
        if (_lstSV == null) {
            JOptionPane.showMessageDialog(rootPane, "Moi ban nhap file diem quiz da");
            return;
        }
        if (SVinputdd.isfilediemdanh(_lstSV, SVinputdd.getListdd(SVinput.getwb(chooser())))) {
            JOptionPane.showMessageDialog(rootPane, "nhap dung");
            loadtbdd(_lstSV);
        } else {
            JOptionPane.showMessageDialog(rootPane, "nhap sai");
        }
    }//GEN-LAST:event_btnquizActionPerformed

    private void btndd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndd1ActionPerformed
        String path = chooser();
        Workbook wb = Service.SVinput.getwb(path);
        Lop lop = Service.SVinput.getlop(wb);
        loadtbquiz(path);
        lbllop.setText("Lớp: " + lop.getMalop() + "\t Môn: " + lop.getMamon() + "\t Kì học: " + lop.getKihoc() + "\t Thời gian: " + lop.getThoigian());
//        if (lop.getCheck() == 1) {
//            loadtbquiz(path);
//            lbllop.setText("Lớp: " + lop.getMalop() + "\t Môn: " + lop.getMamon() + "\t Kì học: " + lop.getKihoc() + "\t Thời gian: " + lop.getThoigian());
//        } else if (lop.getCheck() == 2) {
//            loadtbquiz(path);
//            lbllop.setText("Lớp: " + lop.getMalop() + "\t Môn: " + lop.getMamon() + "\t Kì học: " + lop.getKihoc() + "\t Thời gian: " + lop.getThoigian());
//        } else {
//            lbllop.setText("");
//            tbmModel.setColumnCount(0);
//            tbmModel.setRowCount(0);
//            JOptionPane.showMessageDialog(rootPane, "Môn " + lop.getMamon() + " không cần check điểm");
//        }
    }//GEN-LAST:event_btndd1ActionPerformed

    private void btnnextform1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextform1ActionPerformed
        double a = 10;
        if (rd75.isSelected()) {
            a = 7.5;
        }
        int b = 0;
        try {
            b = Integer.parseInt(txttilenghi.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Moi nhap thi le duco phep nghi");
            return;
        }
        for (int i = 0; i < _lstSV.size(); i++) {
            if (Service.SVcheckdk.checksv(_lstdiem.get(i), _lop, a) && _lstSV.get(i).getTilenghi() <= b) {
                _lstSV.get(i).setCheckfalse(true);
            } else {
                _lstSV.get(i).setCheckfalse(false);
            }
        }
        Formkehoach.getlop(_lop);
        Formkehoach.getlstsv(_lstSV);
        Formkehoach.getlstdiem(_lstdiem);
        Formkehoach x = new Formkehoach();
        x.setVisible(true);
    }//GEN-LAST:event_btnnextform1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormInput().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndd1;
    private javax.swing.JButton btnnextform1;
    private javax.swing.JButton btnquiz;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbl_chek;
    private javax.swing.JLabel lbl_chek1;
    private javax.swing.JLabel lblbgr;
    private javax.swing.JLabel lbllop;
    private javax.swing.JPanel pndiemquiz;
    private javax.swing.JRadioButton rd10;
    private javax.swing.JRadioButton rd75;
    private javax.swing.JTabbedPane tabtb;
    private javax.swing.JTable tbinput;
    private javax.swing.JTable tbinput1;
    private javax.swing.JTextField txttilenghi;
    // End of variables declaration//GEN-END:variables
}
