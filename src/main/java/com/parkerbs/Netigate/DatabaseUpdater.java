/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkerbs.Netigate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import static com.parkerbs.Netigate.NetigateGUI.addedCustomers;

/**
 *
 * @author Ryan
 */
public class DatabaseUpdater implements Runnable {

    File inputFile;
    javax.swing.JTextArea textArea;
    HashMap<Integer, ArrayList<ArrayList<String[]>>> branchList;
    int numberOfBranches;
    int customerCount;

    private final int ACCOUNT_NUMBER = 0;
    private final int BRANCH = 1;
    private final int DELIVERYMETHOD = 2;
    private final int EMAIL = 3;

    private final int COLLECTED = 0;
    private final int DELIVERED = 1;

    public DatabaseUpdater(File file) {
        this.inputFile = file;
        numberOfBranches = ConfigFile.getNumberOfBranches();
        customerCount = 0;
        branchList = new HashMap<>();
    }

    public void splitCsv() {

        for (int i = 1; i <= numberOfBranches; i++) {
            branchList.put(i, new ArrayList<>());
            branchList.get(i).add(new ArrayList<>());
            branchList.get(i).add(new ArrayList<>());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));) {
            String line;

            reader.readLine();

            RemoveEmails.init();

            while ((line = reader.readLine()) != null) {
                String[] customer = line.split(",");
                if ((Integer.valueOf(customer[BRANCH]) <= numberOfBranches)) {
                    if (RemoveEmails.checkEmail(customer[EMAIL])) {
                        if (!(customer[DELIVERYMETHOD].equals("C"))) {
                            branchList.get(Integer.valueOf(customer[BRANCH])).get(DELIVERED).add(customer);
                        } else {
                            branchList.get(Integer.valueOf(customer[EMAIL])).get(COLLECTED).add(customer);
                        }
                        customerCount++;
                    }
                } else {
                    break;
                }
            }
            ErrorLogger.writeError(customerCount + " customers loaded from spreadsheet", ErrorLogger.MESSAGE);
        } catch (FileNotFoundException ex) {
            ErrorLogger.writeError(ex, ErrorLogger.ERROR);
        } catch (IOException ex) {
            ErrorLogger.writeError(ex, ErrorLogger.ERROR);
        }
    }

    public void run() {
        String url = ConfigFile.getSqlURL();
        String user = ConfigFile.getSqlusername();
        String password = ConfigFile.getSqlpassword();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        int duplicateCustomerCount = 0;

        splitCsv();

        Connection connectionObject;
        try {
            connectionObject = DriverManager.getConnection(url, user, password);

            ErrorLogger.writeError("Started SQL connection");
            Statement statement = connectionObject.createStatement();
            Random ran = new Random();
            int randomCollectedCustomerNumber, randomDeliveredCustomerNumber;

            for (int branchNumber = 1; branchNumber <= numberOfBranches; branchNumber++) {
                for (int customerNumber = 0; customerNumber < ConfigFile.getCustomersPerBranch(); customerNumber++) {
                    if (branchList.get(branchNumber).get(COLLECTED).size() > 0) {
                        randomCollectedCustomerNumber = ran.nextInt(branchList.get(branchNumber).get(COLLECTED).size());

                        try {
                            statement.execute(
                                    "INSERT INTO customer_data (account_number, email, last_contact, branch) VALUES"
                                            + "('"
                                            + branchList.get(branchNumber).get(COLLECTED)
                                                    .get(randomCollectedCustomerNumber)[ACCOUNT_NUMBER]
                                            + "', '"
                                            + branchList.get(branchNumber).get(COLLECTED)
                                                    .get(randomCollectedCustomerNumber)[EMAIL]
                                            + "', '" + dtf.format(now) + "', '" + branchList.get(branchNumber)
                                                    .get(COLLECTED).get(randomCollectedCustomerNumber)[BRANCH]
                                            + "')");
                            addedCustomers.add(new Customer(
                                    branchList.get(branchNumber).get(COLLECTED)
                                            .get(randomCollectedCustomerNumber)[EMAIL],
                                    branchList.get(branchNumber).get(COLLECTED)
                                            .get(randomCollectedCustomerNumber)[ACCOUNT_NUMBER],
                                    branchList.get(branchNumber).get(COLLECTED)
                                            .get(randomCollectedCustomerNumber)[BRANCH],
                                    branchList.get(branchNumber).get(COLLECTED)
                                            .get(randomCollectedCustomerNumber)[DELIVERYMETHOD]));
                            ErrorLogger.writeError(
                                    "Customer " + branchList.get(branchNumber).get(COLLECTED)
                                            .get(randomCollectedCustomerNumber)[ACCOUNT_NUMBER] + " added",
                                    ErrorLogger.MESSAGE);
                        } catch (SQLException ex) {
                            if (ex.getErrorCode() == 1062) {
                                duplicateCustomerCount++;
                                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
                            } else {
                                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
                            }
                        }

                    }
                    if (branchList.get(branchNumber).get(DELIVERED).size() > 0) {

                        randomDeliveredCustomerNumber = ran.nextInt(branchList.get(branchNumber).get(DELIVERED).size());

                        try {
                            statement.execute(
                                    "INSERT INTO customer_data (account_number, email, last_contact, branch) VALUES"
                                            + "('"
                                            + branchList.get(branchNumber).get(DELIVERED)
                                                    .get(randomDeliveredCustomerNumber)[ACCOUNT_NUMBER]
                                            + "', '"
                                            + branchList.get(branchNumber).get(DELIVERED)
                                                    .get(randomDeliveredCustomerNumber)[EMAIL]
                                            + "', '" + dtf.format(now) + "', '" + branchList.get(branchNumber)
                                                    .get(DELIVERED).get(randomDeliveredCustomerNumber)[BRANCH]
                                            + "')");
                            addedCustomers.add(new Customer(
                                    branchList.get(branchNumber).get(DELIVERED)
                                            .get(randomDeliveredCustomerNumber)[EMAIL],
                                    branchList.get(branchNumber).get(DELIVERED)
                                            .get(randomDeliveredCustomerNumber)[ACCOUNT_NUMBER],
                                    branchList.get(branchNumber).get(DELIVERED)
                                            .get(randomDeliveredCustomerNumber)[BRANCH],
                                    branchList.get(branchNumber).get(DELIVERED)
                                            .get(randomDeliveredCustomerNumber)[DELIVERYMETHOD]));
                            ErrorLogger.writeError(
                                    "Customer " + branchList.get(branchNumber).get(DELIVERED)
                                            .get(randomDeliveredCustomerNumber)[ACCOUNT_NUMBER] + " added",
                                    ErrorLogger.MESSAGE);
                        } catch (SQLException ex) {
                            if (ex.getErrorCode() == 1062) {
                                duplicateCustomerCount++;
                                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
                            } else {
                                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
                            }
                        }

                    }
                }
            }
            connectionObject.close();
        } catch (SQLException ex) {
            ErrorLogger.writeError(ex, ErrorLogger.ERROR);
        }
    }
}
