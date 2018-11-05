/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkerbs.Netigate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Ryan
 */
public class ArchiveFiles {

    DateFormat dateFormat;
    String date;

    public ArchiveFiles() {
        FileWatcher.calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm");
        date = dateFormat.format(FileWatcher.calendar.getTime());
    }

    public void ArchiveLogFile() {
        try {
            ErrorLogger.closeWriter();
            Files.move(Paths.get(ConfigFile.getFileLocation() + "\\Logs\\" + date + ".txt"),
                    Paths.get(ConfigFile.getFileLocation() + "\\Logs\\Archive\\" + date + ".txt"));
            ErrorLogger.writeError("File " + ConfigFile.getFileLocation() + "\\Logs\\" + date + ".txt archived",
                    ErrorLogger.MESSAGE);
        } catch (IOException ex) {
            ErrorLogger.writeError(ex, ErrorLogger.ERROR);
        }
    }

    public void ArchiveJSONFile() {
        try {
            Files.move(Paths.get(ConfigFile.getFileLocation() + "\\JSON\\Customer " + date + ".json"),
                    Paths.get(ConfigFile.getFileLocation() + "\\JSON\\Archive\\Customer " + date + ".json"));
            ErrorLogger.writeError(
                    "File " + ConfigFile.getFileLocation() + "\\JSON\\Customer " + date + ".json archived",
                    ErrorLogger.MESSAGE);
        } catch (IOException ex) {
            ErrorLogger.writeError(ex, ErrorLogger.ERROR);
        }
    }

    public void ArchiveCSVFile() {
        try {
            Files.move(Paths.get(ConfigFile.getFileLocation() + "\\CSV\\Customer " + date + ".csv"),
                    Paths.get(ConfigFile.getFileLocation() + "\\CSV\\Archive\\Customer " + date + ".csv"));
            ErrorLogger.writeError("File " + ConfigFile.getFileLocation() + "\\CSV\\Customer " + date + ".csv archived",
                    ErrorLogger.MESSAGE);
        } catch (IOException ex) {
            ErrorLogger.writeError(ex, ErrorLogger.ERROR);
        }
    }

    public void ArchiveIncomingFile(String FileName) {
        try {
            Files.move(Paths.get(ConfigFile.getFileLocation() + "\\Incoming\\" + FileName),
                    Paths.get(ConfigFile.getFileLocation() + "\\Incoming\\Archive\\" + FileName));
            ErrorLogger.writeError("File " + ConfigFile.getFileLocation() + "\\Incoming\\" + FileName + " archived",
                    ErrorLogger.MESSAGE);
        } catch (IOException ex) {
            ErrorLogger.writeError(ex, ErrorLogger.ERROR);
        }
    }
}
