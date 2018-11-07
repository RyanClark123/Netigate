/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkerbs.Netigate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Ryan
 */
public class ConfigFile {

    static String sender, password, sendTo, apiKey, fileLocation, sqlURL, sqlusername, sqlpassword, hour, minute;
    static BufferedReader configReader;
    static File configFile;
    static int numberOfBranches, surveyNumber, customersPerBranch;
    static Boolean createCSV, createJSON, archiveCSV, archiveJSON, archiveIncoming, archiveLogs, sendEmail,
            sendToNetigate, activateSurvey, debug, alert, message, error, sqlupdate;

    public static void setSQLUpdate(Boolean sqlUpdate){
        ConfigFile.sqlupdate = sqlUpdate;
    }

    public static Boolean getSQLUpdate(){
        return sqlupdate;
    }

    public static void setDebug(Boolean debug) {
        ConfigFile.debug = debug;
    }

    public static Boolean getDebug() {
        return debug;
    }

    public static void setError(Boolean error) {
        ConfigFile.error = error;
    }

    public static Boolean getError() {
        return error;
    }

    public static void setAlert(Boolean alert) {
        ConfigFile.alert = alert;
    }

    public static Boolean getAlert() {
        return alert;
    }

    public static void setMessage(Boolean message) {
        ConfigFile.message = message;
    }

    public static Boolean getMessage() {
        return message;
    }

    public static String getHour() {
        return hour;
    }

    public static void setHour(String hour) {
        ConfigFile.hour = hour;
    }

    public static String getMinute() {
        return minute;
    }

    public static void setMinute(String minute) {
        ConfigFile.minute = minute;
    }

    public static Boolean getActivateSurvey() {
        return activateSurvey;
    }

    public static void setActivateSurvey(Boolean activateSurvey) {
        ConfigFile.activateSurvey = activateSurvey;
    }

    public static Boolean getSendToNetigate() {
        return sendToNetigate;
    }

    public static void setSendToNetigate(Boolean sendToNetigate) {
        ConfigFile.sendToNetigate = sendToNetigate;
    }

    public static int getCustomersPerBranch() {
        return customersPerBranch;
    }

    public static void setCustomersPerBranch(int customersPerBranch) {
        ConfigFile.customersPerBranch = customersPerBranch;
    }

    public static Boolean getSendEmail() {
        return sendEmail;
    }

    public static void setSendEmail(Boolean sendEmail) {
        ConfigFile.sendEmail = sendEmail;
    }

    public static String getSqlURL() {
        return sqlURL;
    }

    public static void setSqlURL(String sqlURL) {
        ConfigFile.sqlURL = sqlURL;
    }

    public static String getSqlusername() {
        return sqlusername;
    }

    public static void setSqlusername(String sqlusername) {
        ConfigFile.sqlusername = sqlusername;
    }

    public static String getSqlpassword() {
        return sqlpassword;
    }

    public static void setSqlpassword(String sqlpassword) {
        ConfigFile.sqlpassword = sqlpassword;
    }

    public static String getFileLocation() {
        return fileLocation;
    }

    public static void setFileLocation(String fileLocation) {
        ConfigFile.fileLocation = fileLocation;
    }

    public static Boolean getArchiveCSV() {
        return archiveCSV;
    }

    public static void setArchiveCSV(Boolean archiveCSV) {
        ConfigFile.archiveCSV = archiveCSV;
    }

    public static Boolean getArchiveJSON() {
        return archiveJSON;
    }

    public static void setArchiveJSON(Boolean archiveJSON) {
        ConfigFile.archiveJSON = archiveJSON;
    }

    public static Boolean getArchiveIncoming() {
        return archiveIncoming;
    }

    public static void setArchiveIncoming(Boolean archiveIncoming) {
        ConfigFile.archiveIncoming = archiveIncoming;
    }

    public static Boolean getArchiveLogs() {
        return archiveLogs;
    }

    public static void setArchiveLogs(Boolean archiveLogs) {
        ConfigFile.archiveLogs = archiveLogs;
    }

    public static Boolean getCreateCSV() {
        return createCSV;
    }

    public static void setCreateCSV(Boolean createCSV) {
        ConfigFile.createCSV = createCSV;
    }

    public static Boolean getCreateJSON() {
        return createJSON;
    }

    public static void setCreateJSON(Boolean createJSON) {
        ConfigFile.createJSON = createJSON;
    }

    public static int getNumberOfBranches() {
        return numberOfBranches;
    }

    public static void setNumberOfBranches(int numberOfBranches) {
        ConfigFile.numberOfBranches = numberOfBranches;
    }

    public static String getSender() {
        return sender;
    }

    public static void setSender(String sender) {
        ConfigFile.sender = sender;
    }

    public static int getSurveyNumber() {
        return surveyNumber;
    }

    public static void setSurveyNumber(int surveyNumber) {
        ConfigFile.surveyNumber = surveyNumber;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ConfigFile.password = password;
    }

    public static String getSendTo() {
        return sendTo;
    }

    public static void setSendTo(String sendTo) {
        ConfigFile.sendTo = sendTo;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        ConfigFile.apiKey = apiKey;
    }

    public static void writeConfig() {
        try {
            BufferedWriter configWriter = new BufferedWriter(new FileWriter(
                    "C:\\Users\\ryan.clark\\OneDrive - Parker Building Supplies Limited\\Documents\\IT\\Java\\config.txt"));
            configWriter.write("NumberOfBranches: " + numberOfBranches);
            configWriter.newLine();
            configWriter.write("CustomersPerBranch: " + customersPerBranch);
            configWriter.newLine();
            configWriter.write("FileLocation: " + fileLocation);
            configWriter.newLine();
            configWriter.write("SendEmail: " + sendEmail);
            configWriter.newLine();
            configWriter.write("EmailSender: " + sender);
            configWriter.newLine();
            configWriter.write("Password: " + password);
            configWriter.newLine();
            configWriter.write("EmailTo: " + sendTo);
            configWriter.newLine();
            configWriter.write("ApiKey: " + apiKey);
            configWriter.newLine();
            configWriter.write("SurveyNumber: " + surveyNumber);
            configWriter.newLine();
            configWriter.write("SendToNetigate: " + sendToNetigate);
            configWriter.newLine();
            configWriter.write("ActivateSurvey: " + activateSurvey);
            configWriter.newLine();
            configWriter.write("Hour: " + hour);
            configWriter.newLine();
            configWriter.write("Minute: " + minute);
            configWriter.newLine();
            configWriter.write("CreateCSV: " + createCSV);
            configWriter.newLine();
            configWriter.write("CreateJSON: " + createJSON);
            configWriter.newLine();
            configWriter.write("ArchiveCSV: " + archiveCSV);
            configWriter.newLine();
            configWriter.write("ArchiveJSON: " + archiveJSON);
            configWriter.newLine();
            configWriter.write("ArchiveIncoming: " + archiveIncoming);
            configWriter.newLine();
            configWriter.write("ArchiveLogs: " + archiveLogs);
            configWriter.newLine();
            configWriter.write("SQLURL: " + sqlURL);
            configWriter.newLine();
            configWriter.write("SQLUsername: " + sqlusername);
            configWriter.newLine();
            configWriter.write("SQLPassword: " + sqlpassword);
            configWriter.newLine();
            configWriter.write("SQLUpdate: " + sqlupdate);
            configWriter.newLine();
            configWriter.write("Debug: " + debug);
            configWriter.newLine();
            configWriter.write("Error: " + error);
            configWriter.newLine();
            configWriter.write("Message: " + message);
            configWriter.newLine();
            configWriter.write("Alert: " + alert);

            configWriter.flush();
            configWriter.close();
        } catch (IOException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        }
    }

    public static void getConfig() {
        String configLine;
        try {
            configFile = new File(
                    "C:\\Users\\ryan.clark\\OneDrive - Parker Building Supplies Limited\\Documents\\IT\\Java\\config.txt");
            configReader = new BufferedReader(new FileReader(configFile));
            while ((configLine = configReader.readLine()) != null) {
                String[] configArray = configLine.split(":");
                switch (configArray[0]) {
                case "NumberOfBranches":
                    numberOfBranches = Integer.valueOf(configArray[1].trim());
                    break;
                case "CustomersPerBranch":
                    customersPerBranch = Integer.valueOf(configArray[1].trim());
                    break;
                case "FileLocation":
                    fileLocation = configArray[1].trim() + ":" + configArray[2].trim();
                    break;
                case "SendEmail":
                    sendEmail = Boolean.parseBoolean(configArray[1].trim());
                    break;
                case "EmailSender":
                    sender = configArray[1].trim();
                    break;
                case "Password":
                    password = configArray[1].trim();
                    break;
                case "EmailTo":
                    sendTo = configArray[1].trim();
                    break;
                case "ApiKey":
                    apiKey = configArray[1].trim();
                    break;
                case "SurveyNumber":
                    surveyNumber = Integer.valueOf(configArray[1].trim());
                    break;
                case "SendToNetigate":
                    sendToNetigate = Boolean.parseBoolean(configArray[1].trim());
                    break;
                case "ActivateSurvey":
                    activateSurvey = Boolean.parseBoolean(configArray[1].trim());
                    break;
                case "Hour":
                    hour = configArray[1].trim();
                    break;
                case "Minute":
                    minute = configArray[1].trim();
                    break;
                case "CreateCSV":
                    createCSV = Boolean.parseBoolean(configArray[1].trim());
                    break;
                case "CreateJSON":
                    createJSON = Boolean.parseBoolean(configArray[1].trim());
                    break;
                case "ArchiveCSV":
                    archiveCSV = Boolean.parseBoolean(configArray[1].trim());
                    break;
                case "ArchiveJSON":
                    archiveJSON = Boolean.parseBoolean(configArray[1].trim());
                    break;
                case "ArchiveIncoming":
                    archiveIncoming = Boolean.parseBoolean(configArray[1].trim());
                    break;
                case "ArchiveLogs":
                    archiveLogs = Boolean.parseBoolean(configArray[1].trim());
                    break;
                case "SQLURL":
                    sqlURL = configArray[1].trim() + ":" + configArray[2].trim() + ":" + configArray[3].trim() + ":"
                            + configArray[4].trim();
                    break;
                case "SQLUsername":
                    sqlusername = configArray[1].trim();
                    break;
                case "SQLPassword":
                    sqlpassword = configArray[1].trim();
                    break;
                case "SQLUpdate":
                    sqlupdate = Boolean.parseBoolean(configArray[1].trim());
                case "Debug":
                    debug = Boolean.parseBoolean(configArray[1].trim());
                case "Error":
                    error = Boolean.parseBoolean(configArray[1].trim());
                case "Alert":
                    alert = Boolean.parseBoolean(configArray[1].trim());
                case "Message":
                    message = Boolean.parseBoolean(configArray[1].trim());
                default:
                    break;
                }
            }
        } catch (IOException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        }
    }
}
