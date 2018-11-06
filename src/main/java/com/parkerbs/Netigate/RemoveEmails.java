/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkerbs.Netigate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Ryan
 */
public class RemoveEmails {

    private static ArrayList<String> emails, customerEmails;

    public static void init() {
        emails = new ArrayList<>();
        customerEmails = new ArrayList<>();
        loadEmails();
    }

    public static void loadEmails() {
        try (Stream<String> stream = Files.lines(Paths.get(
                "C:\\Users\\ryan.clark\\OneDrive - Parker Building Supplies Limited\\Documents\\IT\\Java\\email_lookup.csv"))) {
            stream.forEach(email -> emails.add(email));
            if (ConfigFile.getMessage()) {
                ErrorLogger.writeError("Emails to remove loaded", ErrorLogger.MESSAGE);
            }
        } catch (FileNotFoundException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        } catch (IOException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        }
    }

    public static Boolean checkEmail(String email) {

        try {
            InternetAddress emailToCheck = new InternetAddress(email);
            emailToCheck.validate();
        } catch (AddressException ex) {
            if(ConfigFile.getMessage()){
                ErrorLogger.writeError("Invalid email address", ErrorLogger.MESSAGE);
            }
            return false;
        }

        for (String emailToRemove : emails) {
            if ((email.toLowerCase().startsWith(emailToRemove))) {
                return false;
            }
        }

        for (String customerEmail : customerEmails) {
            if (customerEmail.equals(email)) {
                return false;
            }
        }

        customerEmails.add(email);

        return true;
    }
}
