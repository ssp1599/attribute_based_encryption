/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Db.Dbcon;
import General.Configuration;
import General.Nimbus;
import java.awt.Desktop;
import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.FileOutputStream;

/**
 *
 * @author Jithinpv
 */
public class ViewRequestedFileStatus extends javax.swing.JFrame {

    /**
     * Creates new form ViewRequestedFileStatus
     */
    public ViewRequestedFileStatus() {
        Nimbus.loadLoogAndFeel();
        initComponents();
        this.setLocationRelativeTo(null);
        loadFileRequestDetails();
        download_button.setEnabled(false);
        Configuration.setIconOnLabel("chooseFile.jpg", bg);
    }

    private void loadFileRequestDetails() {
        Dbcon dbcon = new Dbcon();
        DefaultTableModel dt = (DefaultTableModel) requested_files_table.getModel();
        ResultSet rs = dbcon.select("SELECT hp.name , s.request_id,s.requested_date,s.status, th.attr_1,h.name, th.encrypted_file_path , s.encryption_id as eid FROM tbl_file_request s INNER JOIN tbl_data_member hp   on hp.data_member_id = s.file_owner_data_member INNER JOIN tbl_organisation h on hp.organization_id = h.organisation_id INNER JOIN  tbl_file_encryption_logs th on hp.organization_id = h.organisation_id where requested_data_member='" + DataMemberLogin.logged_in_user_id + "' and  th.encryption_id=s.encryption_id");
        try {
            while (rs.next()) {
                String date1 = rs.getString(3);
                long date2 = Long.parseLong(date1);
                Date date3 = new Date(date2);
                String date = date3.toString();
                String status_string = rs.getString(4);
                int status_int = Integer.parseInt(status_string);
                String status;
                if (status_int == 2) {
                    status = "pending";
                } else if (status_int == 1) {
                    status = "approved";
                } else {
                    status = "rejected";
                }
                dt.addRow(new String[]{rs.getString(2), rs.getString(5), rs.getString(1), rs.getString(6), date, status, rs.getString("encrypted_file_path"), rs.getString("eid")});
            }
            requested_files_table.setModel(dt);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        requested_files_table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        download_button = new javax.swing.JButton();
        progress_bar = new javax.swing.JProgressBar();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        requested_files_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FILE", "DATA MEMBER", "ORGANIZATION", "REQUESTED DATE", "STATUS", "FILE PATH", "eid"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        requested_files_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requested_files_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(requested_files_table);
        requested_files_table.getColumnModel().getColumn(0).setMinWidth(50);
        requested_files_table.getColumnModel().getColumn(0).setPreferredWidth(50);
        requested_files_table.getColumnModel().getColumn(0).setMaxWidth(50);
        requested_files_table.getColumnModel().getColumn(6).setMinWidth(0);
        requested_files_table.getColumnModel().getColumn(6).setPreferredWidth(0);
        requested_files_table.getColumnModel().getColumn(6).setMaxWidth(0);
        requested_files_table.getColumnModel().getColumn(7).setMinWidth(0);
        requested_files_table.getColumnModel().getColumn(7).setPreferredWidth(0);
        requested_files_table.getColumnModel().getColumn(7).setMaxWidth(0);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, 700, 271));

        jLabel1.setText("Requested File Status");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 22, 177, -1));

        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(386, 336, -1, -1));

        download_button.setText("Download file");
        download_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                download_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(download_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 336, -1, -1));
        getContentPane().add(progress_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 336, 251, 23));

        bg.setText("jLabel2");
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 730, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        DataMemberHome dataMemberHome = new DataMemberHome();
        dataMemberHome.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    class ProgressBarThread extends Thread {

        String requested_file_status;

        private ProgressBarThread(String requested_file_status) {
            this.requested_file_status = requested_file_status;
        }

        public void run() {
            int value = 0;
            try {
                while (value <= 100) {
                    progress_bar.setValue(value += 5);
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (requested_file_status.trim().toLowerCase().equals("approved")) {
                downloadSelectedFileWithDecryption();
            } else {
                downloadSelectedFileWithoutDecryption();
            }

        }
    }

    private void downloadSelectedFileWithoutDecryption() {
        String requestedFilePath = requested_files_table.getValueAt(requested_files_table.getSelectedRow(), 6).toString();
        String requestedFileName = requested_files_table.getValueAt(requested_files_table.getSelectedRow(), 1).toString();
        System.out.println(requestedFilePath);
        File fromFile = new File(Configuration.dataCloud + requestedFilePath);
        File temporaryFileDirectory = new File(Configuration.temporaryFilePath + System.currentTimeMillis());
        if (temporaryFileDirectory.mkdir()) {
            File toFile = new File(temporaryFileDirectory.getPath() + "/" + requestedFileName + "." + FilenameUtils.getExtension(requestedFilePath));

            try {
                FileUtils.copyFile(fromFile, toFile);
                Desktop desktop = Desktop.getDesktop();
                desktop.open(temporaryFileDirectory);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Could not open the file. Please check directory " + temporaryFileDirectory);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Could not get file permission in computer to make new folder at " + temporaryFileDirectory);
        }
        download_button.setEnabled(false);
    }

    private void downloadSelectedFileWithDecryption() {
        String requestedFilePath = requested_files_table.getValueAt(requested_files_table.getSelectedRow(), 6).toString();
        String requestedFileName = requested_files_table.getValueAt(requested_files_table.getSelectedRow(), 1).toString();
        String eid = requested_files_table.getValueAt(requested_files_table.getSelectedRow(), 7).toString();

        System.out.println(requestedFilePath);
        File fromFile = new File(Configuration.dataCloud + requestedFilePath);
        try {
            String readFileToString = FileUtils.readFileToString(fromFile);

            // kakes
            String qery = "select private_key,master_key,secret_key from tbl_file_encryption_logs where encryption_id=" + eid;
            ResultSet select = new Dbcon().select(qery);
            if (select.next()) {
                String private_key = select.getString("private_key");
                String master_key = select.getString("master_key");
                String secret_key = select.getString("secret_key");

                System.out.println("private_key " + private_key);
                System.out.println("master_key " + master_key);
                System.out.println("secret_key " + secret_key);

                File privateKey = new File(Configuration.allKeys + private_key);
                File masterKey = new File(Configuration.allKeys + master_key);
                File secretKey = new File(Configuration.allKeys + secret_key);

                if (privateKey.exists() && secretKey.exists() && privateKey.exists()) {
                    String p_key = "0";
                    String m_key = "0";
                    String s_key = "0";
                    try {
                        p_key = FileUtils.readFileToString(privateKey);
                        m_key = FileUtils.readFileToString(masterKey);
                        s_key = FileUtils.readFileToString(secretKey);
                    } catch (Exception e) {
                    }

                    try {
                        ENC.setPrivateKey(Integer.parseInt(p_key));
                        ENC.setMasterKey(Integer.parseInt(m_key));
                        ENC.setSecretKey(Integer.parseInt(s_key));
                    } catch (Exception e) {
                    }
                    byte[] dataByteArray = ENC.decodeData(readFileToString);

                    File temporaryFileDirectory = new File(Configuration.temporaryFilePath + System.currentTimeMillis());
                    if (temporaryFileDirectory.mkdir()) {
                        FileOutputStream dataOutFile = new FileOutputStream(temporaryFileDirectory.getPath() + "/" + requestedFileName + "." + FilenameUtils.getExtension(requestedFilePath));
                        dataOutFile.write(dataByteArray);
                        dataOutFile.close();
                        try {
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(temporaryFileDirectory);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(rootPane, "Could not open the file. Please check directory " + temporaryFileDirectory);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Could not get file permission in computer to make new folder at " + temporaryFileDirectory);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "All the keys are not found. Could not decrypt the file");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Sone could not decrypt the file, please try after some time");
                return;
            }
            download_button.setEnabled(false);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    private void getKeys() {
    }

    static class ENC {

        static int privateKey;
        static int secretKey;
        static int masterKey;

        public static void setMasterKey(int masterKey) {
            ENC.masterKey = masterKey;
        }

        public static void setSecretKey(int secretKey) {
            ENC.secretKey = secretKey;
        }

        public static void setPrivateKey(int privateKey) {
            ENC.privateKey = privateKey;
        }

        public static byte[] decodeData(String idatastring) {
            return Base64.decode(idatastring);
        }
    }

private void download_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_download_buttonActionPerformed
    // TODO add your handling code here:
    String requested_file_status = requested_files_table.getValueAt(requested_files_table.getSelectedRow(), 5).toString();
    new ProgressBarThread(requested_file_status).start();
}//GEN-LAST:event_download_buttonActionPerformed

private void requested_files_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requested_files_tableMouseClicked

    download_button.setEnabled(true);
    String requested_file_id = requested_files_table.getValueAt(requested_files_table.getSelectedRow(), 0).toString();
    String requested_file_status = requested_files_table.getValueAt(requested_files_table.getSelectedRow(), 5).toString();
    if (requested_file_status.trim().toLowerCase().equals("approved")) {
        // file is approved, decrypt and retreive
    } else {
        // not authorised for file
    }

    // TODO add your handling code here:
}//GEN-LAST:event_requested_files_tableMouseClicked

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
            java.util.logging.Logger.getLogger(ViewRequestedFileStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRequestedFileStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRequestedFileStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRequestedFileStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ViewRequestedFileStatus().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton download_button;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar progress_bar;
    private javax.swing.JTable requested_files_table;
    // End of variables declaration//GEN-END:variables
}
