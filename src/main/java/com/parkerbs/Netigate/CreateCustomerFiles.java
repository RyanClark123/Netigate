/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkerbs.Netigate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Ryan
 */
public class CreateCustomerFiles implements Runnable {

    JSONObject json = new JSONObject();
    DateFormat dateFormat;

    public void run() {

        dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm");

        if (ConfigFile.getCreateCSV()) {
            createCSV();
            if (ConfigFile.getSendEmail()) {
                EmailSender.sendEmail(ConfigFile.getFileLocation() + "\\CSV\\Customer "
                        + dateFormat.format(FileWatcher.calendar.getTime()) + ".csv");
                if (ConfigFile.getMessage()) {
                    ErrorLogger.writeError("Email sent", ErrorLogger.MESSAGE);
                }
            }
        }

        if (ConfigFile.getCreateJSON()) {
            createJSON();
        }
    }

    public void createCSV() {
        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(ConfigFile.getFileLocation()
                + "\\CSV\\Customer " + dateFormat.format(FileWatcher.calendar.getTime()) + ".csv"))) {

            csvWriter.write("Email\n");

            for (Customer customer : NetigateGUI.addedCustomers) {
                csvWriter.write(customer.getEmail() + ",");
                csvWriter.write(customer.getAccountNumber() + ",");
                csvWriter.write(customer.getBranch() + ",");
                csvWriter.write(customer.getCategory() + "\n");
            }

            if (ConfigFile.getMessage()) {
                ErrorLogger.writeError("New CSV file created", ErrorLogger.MESSAGE);
            }
            csvWriter.flush();
        } catch (IOException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void createJSON() {
        try (FileWriter JSONfile = new FileWriter(ConfigFile.getFileLocation() + "\\JSON\\Customer "
                + dateFormat.format(FileWatcher.calendar.getTime()) + ".json")) {
            for (Customer customer : NetigateGUI.addedCustomers) {
                json.put("backgroundData", new JSONArray());
                json.put("sendMail", true);
                json.put("contactDetails", customer.getEmail());
                JSONfile.write(json.toJSONString());
            }
            if (ConfigFile.getMessage()) {
                ErrorLogger.writeError("New JSON file created", ErrorLogger.MESSAGE);
            }
            JSONfile.flush();
        } catch (IOException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        }
    }

}
