/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkerbs.Netigate;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import static com.parkerbs.Netigate.FileWatcher.archiver;
import static com.parkerbs.Netigate.FileWatcher.incomingFile;

/**
 *
 * @author Ryan
 */
public class NetigateGUI extends javax.swing.JFrame {

    public static List<Customer> addedCustomers = new ArrayList<>();
    public static ConfigFile configFile;

    /**
     * Creates new form NetigateGUI
     */
    public NetigateGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        logsButton = new javax.swing.JButton();
        clearLogButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        settingsMenuItem = new javax.swing.JMenuItem();
        exitButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Netigate Processor");
        setIconImage(new ImageIcon(
                "C:\\Users\\ryan.clark\\OneDrive - Parker Building Supplies Limited\\Documents\\IT\\Java\\Parker BS.jpg")
                        .getImage());
        setPreferredSize(new java.awt.Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1814, 200));

        logTextArea.setEditable(false);
        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        jScrollPane1.setViewportView(logTextArea);

        logsButton.setText("View Logs");
        logsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logsButtonActionPerformed(evt);
            }
        });

        clearLogButton.setText("Clear Log");
        clearLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(mainPanelLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup().addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                .addGroup(mainPanelLayout.createSequentialGroup().addGap(39, 39, 39)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(logsButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clearLogButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(45, 45, 45).addComponent(logsButton).addGap(18, 18, 18).addComponent(clearLogButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)));

        jMenu1.setText("File");

        settingsMenuItem.setText("Settings");
        settingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(settingsMenuItem);

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        jMenu1.add(exitButton);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(mainPanel,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(mainPanel,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logsButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logsButtonActionPerformed
        try {
            Runtime.getRuntime().exec("explorer.exe " + ConfigFile.getFileLocation() + "\\Logs\\");
        } catch (IOException ex) {
            ErrorLogger.writeError(ex);
        }
    }// GEN-LAST:event_logsButtonActionPerformed

    private void settingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_settingsMenuItemActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SettingsGUI settingsGUI = new SettingsGUI();
                settingsGUI.setVisible(true);
                ConfigFile.getConfig();

                settingsGUI.setNumberOfBranches(ConfigFile.getNumberOfBranches());
                settingsGUI.setCustomersPerBranch(ConfigFile.getCustomersPerBranch());
                settingsGUI.setFileLocation(ConfigFile.getFileLocation());
                settingsGUI.setSendEmail(ConfigFile.getSendEmail());
                settingsGUI.setEmailSenderTextField(ConfigFile.getSender());
                settingsGUI.setPassword(ConfigFile.getPassword());
                settingsGUI.setEmailSendToTextField(ConfigFile.getSendTo());
                settingsGUI.setApiKey(ConfigFile.getApiKey());
                settingsGUI.setSurveyNumber(ConfigFile.getSurveyNumber());
                settingsGUI.setSendToNetigate(ConfigFile.getSendToNetigate());
                settingsGUI.setActivateSurvey(ConfigFile.getActivateSurvey());
                settingsGUI.setHour(ConfigFile.getHour());
                settingsGUI.setMinute(ConfigFile.getMinute());
                settingsGUI.setCreateCSV(ConfigFile.getCreateCSV());
                settingsGUI.setCreateJSON(ConfigFile.getCreateJSON());
                settingsGUI.setArchiveCSV(ConfigFile.getArchiveCSV());
                settingsGUI.setArchiveJSON(ConfigFile.getArchiveJSON());
                settingsGUI.setArchiveIncoming(ConfigFile.getArchiveIncoming());
                settingsGUI.setArchiveLogs(ConfigFile.getArchiveLogs());
                settingsGUI.setSQLURL(ConfigFile.getSqlURL());
                settingsGUI.setSQLUsername(ConfigFile.getSqlusername());
                settingsGUI.setSQLPassword(ConfigFile.getSqlpassword());

            }
        });
    }// GEN-LAST:event_settingsMenuItemActionPerformed

    private void clearLogButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clearLogButtonActionPerformed
        logTextArea.setText("");
    }// GEN-LAST:event_clearLogButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitButtonActionPerformed
        try {
            if (ConfigFile.getArchiveCSV()) {
                archiver.ArchiveCSVFile();
            }
            if (ConfigFile.getArchiveJSON()) {
                archiver.ArchiveJSONFile();
            }
            if (ConfigFile.getArchiveIncoming()) {
                archiver.ArchiveIncomingFile(incomingFile);
            }
            if (ConfigFile.getArchiveLogs()) {
                archiver.ArchiveLogFile();
            }

            System.exit(0);

        } catch (java.lang.NullPointerException ex) {
            System.exit(0);
        }
    }// GEN-LAST:event_exitButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NetigateGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NetigateGUI().setVisible(true);
            }
        });

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(NetigateGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConfigFile.getConfig();
        checkFolders();

        Thread thread = new Thread(new FileWatcher());
        thread.start();

    }

    public static void checkFolders() {
        String CSVLocation = ConfigFile.getFileLocation() + "\\CSV";
        String IncomingLocation = ConfigFile.getFileLocation() + "\\Incoming";
        String JSONLocation = ConfigFile.getFileLocation() + "\\JSON";
        String LogsLocation = ConfigFile.getFileLocation() + "\\Logs";

        File CSVFile = new File(CSVLocation);
        File IncomingFile = new File(IncomingLocation);
        File JSONFile = new File(JSONLocation);
        File LogsFile = new File(LogsLocation);

        if (!CSVFile.exists()) {
            CSVFile.mkdir();
            new File(CSVLocation + "\\Archive").mkdir();
        }
        if (!IncomingFile.exists()) {
            IncomingFile.mkdir();
            new File(IncomingLocation + "\\Archive").mkdir();
        }
        if (!JSONFile.exists()) {
            JSONFile.mkdir();
            new File(JSONLocation + "\\Archive").mkdir();
        }
        if (!LogsFile.exists()) {
            LogsFile.mkdir();
            new File(LogsLocation + "\\Archive").mkdir();
        }
        if (!(new File(CSVLocation + "\\Archive").exists())) {
            new File(CSVLocation + "\\Archive").mkdir();
        }
        if (!(new File(IncomingLocation + "\\Archive").exists())) {
            new File(IncomingLocation + "\\Archive").mkdir();
        }
        if (!(new File(JSONLocation + "\\Archive").exists())) {
            new File(JSONLocation + "\\Archive").mkdir();
        }
        if (!(new File(LogsLocation + "\\Archive").exists())) {
            new File(LogsLocation + "\\Archive").mkdir();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearLogButton;
    private javax.swing.JMenuItem exitButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea logTextArea;
    private javax.swing.JButton logsButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem settingsMenuItem;
    // End of variables declaration//GEN-END:variables
}