/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkerbs.Netigate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Calendar;
import java.util.List;
import static com.parkerbs.Netigate.NetigateGUI.addedCustomers;

/**
 *
 * @author Ryan
 */
public class FileWatcher implements Runnable {

    public static Calendar calendar;
    public static String incomingFile;
    public static ArchiveFiles archiver;
    public static ErrorLogger errorLogger;

    public void run() {

        WatchService watcher = null;
        try {
            Path myDir = Paths.get(ConfigFile.getFileLocation() + "\\Incoming");
            watcher = myDir.getFileSystem().newWatchService();
            myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        }

        while (true) {
            try {
                WatchKey watchKey = watcher.take();
                List<WatchEvent<?>> events = watchKey.pollEvents();
                for (WatchEvent<?> event : events) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        ErrorLogger.CreateNewLogFile();
                        archiver = new ArchiveFiles();
                        calendar = Calendar.getInstance();
                        if (ConfigFile.getMessage()) {
                            ErrorLogger.writeError("New file " + event.context().toString() + " receieved!",
                                    ErrorLogger.MESSAGE);
                        }
                        incomingFile = event.context().toString();
                        Thread.sleep(4000);
                        if (ConfigFile.getSQLUpdate()) {
                            Thread databaseUpdaterThread = new Thread(new DatabaseUpdater(new File(
                                    ConfigFile.getFileLocation() + "\\Incoming\\" + event.context().toString())));
                            databaseUpdaterThread.start();
                            databaseUpdaterThread.join();
                        }

                        Thread customerFileCreator = new Thread(new CreateCustomerFiles());
                        customerFileCreator.start();
                        customerFileCreator.join();

                        if (ConfigFile.getSendToNetigate()) {
                            Thread sendToNetigateThread = new Thread(new SendToNetigate());
                            sendToNetigateThread.start();
                            sendToNetigateThread.join();
                        }

                        if (ConfigFile.getArchiveCSV()) {
                            archiver.ArchiveCSVFile();
                        }
                        if (ConfigFile.getArchiveJSON()) {
                            archiver.ArchiveJSONFile();
                        }
                        if (ConfigFile.getArchiveIncoming()) {
                            archiver.ArchiveIncomingFile(event.context().toString());
                        }
                        if (ConfigFile.getArchiveLogs()) {
                            archiver.ArchiveLogFile();
                        }

                        addedCustomers.clear();
                    }
                }
                watchKey.reset();
            } catch (InterruptedException ex) {
                if (ConfigFile.getError()) {
                    ErrorLogger.writeError(ex, ErrorLogger.ERROR);
                }
            }
        }
    }

}
