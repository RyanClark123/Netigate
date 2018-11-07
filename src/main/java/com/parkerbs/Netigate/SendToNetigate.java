/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkerbs.Netigate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static com.parkerbs.Netigate.FileWatcher.calendar;
import static com.parkerbs.Netigate.NetigateGUI.addedCustomers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ryan
 */
public class SendToNetigate implements Runnable {

    URL url;
    String input, date, time;
    DateFormat dateFormat;
    JSONObject response;
    JSONParser parser = new JSONParser();
    long sendoutID;

    public void run() {
        createSendout();
        sendRequest();
        getSendoutId();
        addRespondents();
        if (ConfigFile.getActivateSurvey()) {
            activateSurvey();
            sendRequest();
        }
    }

    public void createSendout() {

        try (BufferedReader messageReader = new BufferedReader(new FileReader(
                "C:\\Working\\HTML.txt"))) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.format(calendar.getTime());
            time = "T" + ConfigFile.getHour() + ":" + ConfigFile.getMinute() + ":00Z";

            String message;

            while ((message = messageReader.readLine()) != null) {
                url = new URL("https://www.netigate.se/api/v1.1/surveys/" + ConfigFile.getSurveyNumber() + "/sendouts");
                input = "{\"name\":\"" + date + "\",\"type\":\"EMAIL\",\"sendDate\":\"" + date + time
                        + "\",\"sender\":\"feedback@parkerbs.com\",\"subject\":\"We'd love your feedback!\",\"message\":"
                        + message + "}";
            }

        } catch (MalformedURLException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
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

    public void addRespondents() {
        try {
            for (Customer customer : addedCustomers) {
                url = new URL("https://www.netigate.se/api/v1.1/surveys/" + ConfigFile.getSurveyNumber() + "/sendouts/"
                        + sendoutID + "/respondents");
                input = "{\"contactDetails\": \"" + customer.getEmail() + "\",\"sendMail\":true,\"backgroundData\":[]}";
                sendRequest();
            }

        } catch (MalformedURLException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        }
    }

    public void activateSurvey() {
        try {
            url = new URL("https://www.netigate.se/api/v1.1/surveys/" + ConfigFile.getSurveyNumber() + "/sendouts/"
                    + sendoutID + "/activate");
        } catch (MalformedURLException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        }
    }

    public void sendRequest() {
        try {
            HttpURLConnection connectionObject = (HttpURLConnection) url.openConnection();
            connectionObject.setDoOutput(true);
            connectionObject.setRequestMethod("POST");
            connectionObject.setRequestProperty("Content-Type", "application/json");
            connectionObject.setRequestProperty("Content-Length", "");
            connectionObject.setRequestProperty("X-API-KEY", ConfigFile.getApiKey());

            OutputStream os = connectionObject.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(connectionObject.getInputStream()));

            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                response = (JSONObject) parser.parse(output);
            }

            connectionObject.disconnect();
        } catch (IOException | ParseException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        }
    }

    public void getSendoutId() {
        response = (JSONObject) response.get("CreateSendoutResult");
        sendoutID = (long) response.get("SendoutId");
    }
}
