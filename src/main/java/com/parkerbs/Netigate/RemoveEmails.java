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
        try (Stream<String> stream = Files.lines(Paths.get("C:\\Users\\ryan.clark\\OneDrive - Parker Building Supplies Limited\\Documents\\IT\\Java\\email_lookup.csv"))){
            stream.forEach(email -> emails.add(email));
            
            //ErrorLogger.writeError("Emails to remove loaded", ErrorLogger.MESSAGE);
        } catch (FileNotFoundException ex) {
            ErrorLogger.writeError(ex, ErrorLogger.ERROR);
        } catch (IOException ex) {
            ErrorLogger.writeError(ex, ErrorLogger.ERROR);
        }
    }

    public static Boolean checkEmail(String email) {

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
