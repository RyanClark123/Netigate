
package com.parkerbs.Netigate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextArea;

/**
 * The <code>ErrorLogger</code> class is a class that will log errors to a file
 * or to a JTextArea from a GUI, will logs message as 4 different {@link #DEBUG
 * types}:
 * <ol>
 * <li><code>DEBUG</code></li>
 * <li><code>MESSAGE</code></li>
 * <li><code>ERROR</code></li>
 * <li><code>ALERT</code></li>
 * </ol>
 * 
 * <h3>Initialising <code>ErrorLogger</code></h3>
 * <p>
 * Before using the <code>ErrorLogger</code> you must itialise it there are
 * several {@link #init() init methods} such as <code>init()</code> and
 * <code>init(SimpleDateFormat dateFormat)</code>, if you do not specify a
 * <code>SimpleDateFormat</code> the default one of <blockquote>
 * 
 * <pre>
 * ErrorLogger.dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm");
 * </pre>
 * 
 * </blockquote> will be used, you can specify a simple date format at a later
 * date using {@link #setNewDateFormat(SimpleDateFormat)
 * setNewSimpleDateFormat(SimpleDateFormat)} all of the methods are
 * <code>static</code> so you canot create an instance of
 * <code>ErrorLogger</code>
 * <p>
 * when the <code>ErrorLogger init()</code> method is called a new error file is
 * created at a default location or a specified location.
 * 
 * <h4>Writing an error</h4>
 * <p>
 * Writing an error is simple once the <code>ErrorLogger</code> has been
 * initialised, to log an error simply call one of the logging
 * {@link #writeError(Exception) methods} such as
 * <code>writeError(String ex)</code> this will log an error ex to a file and
 * JTextArea, with message type <code>MESSAGE</code>
 * 
 * 
 * @author Ryan
 * @version 1.0
 * @since 05/11/2018
 * @param init              initialises the errorlogger, can pass in nothing, a
 *                          file location, a <code>JTextArea</code> or
 *                          <code>SimpleDateFormat</code>
 * @param writeError        writes an error the log file, can pass in a String
 *                          or Exception and a message type
 * @param writeLogTextError writes an error to the <code>JTextArea</code> if one
 *                          was specified, can pass in a String and message type
 * @param setNewDateFormat  can specify a new date format to use on the error
 *                          messages
 * @param setLogToTextArea  sets the value of <code>logToTextArea</code> to the
 *                          passed in value
 * @param closeWriter       closes the file write on the error logger
 */
public class ErrorLogger {

    private static BufferedWriter errorFileWriter;
    public static final String DEBUG = "DEBUG";
    public static final String MESSAGE = "MESSAGE";
    public static final String ALERT = "ALERT";
    public static final String ERROR = "ERROR";
    private static JTextArea textArea = null;
    private static Boolean logToTextArea = false;
    private static SimpleDateFormat dateFormat;

    /**
     * Initialises the error logger with no parameters, sets the default
     * <code>SimpleDateFormat</code> and file location
     * 
     */
    public static void init() {
        try {
            ErrorLogger.dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm");
            Date date = new Date();
            String file = ConfigFile.getFileLocation() + "\\Logs\\" + dateFormat.format(date) + ".txt";
            errorFileWriter = new BufferedWriter(new FileWriter(file));
            errorFileWriter
                    .write(dateFormat.format(new Timestamp(System.currentTimeMillis())) + " MESSAGE: New log file "
                            + ConfigFile.getFileLocation() + "\\Logs\\" + dateFormat.format(date) + ".txt created");
            errorFileWriter.newLine();
            errorFileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initialises the error logger with a <code>SimpleDtaeFormat</code> that is
     * passed in, sets the default file location
     * 
     * @param dateFormat The date format to use
     */
    public static void init(SimpleDateFormat dateFormat) {
        try {
            ErrorLogger.dateFormat = dateFormat;
            Date date = new Date();
            String file = ConfigFile.getFileLocation() + "\\Logs\\" + ErrorLogger.dateFormat.format(date) + ".txt";
            errorFileWriter = new BufferedWriter(new FileWriter(file));
            errorFileWriter.write(dateFormat.format(new Timestamp(System.currentTimeMillis()))
                    + " MESSAGE: New log file " + ConfigFile.getFileLocation() + "\\Logs\\"
                    + ErrorLogger.dateFormat.format(date) + ".txt created");
            errorFileWriter.newLine();
            errorFileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initialises the error logger with a JTextArea, sets the default
     * SimpleDateFormat and file location. Sets the logging on the
     * {@link #logToTextArea text area} to true
     * 
     * @param textArea Is the JTextArea to use when logging errors
     */
    public static void init(JTextArea textArea) {
        try {
            ErrorLogger.dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm");
            Date date = new Date();
            String file = ConfigFile.getFileLocation() + "\\Logs\\" + dateFormat.format(date) + ".txt";
            errorFileWriter = new BufferedWriter(new FileWriter(file));
            errorFileWriter
                    .write(dateFormat.format(new Timestamp(System.currentTimeMillis())) + " MESSAGE: New log file "
                            + ConfigFile.getFileLocation() + "\\Logs\\" + dateFormat.format(date) + ".txt created");
            errorFileWriter.newLine();
            errorFileWriter.flush();
            ErrorLogger.textArea = textArea;
            logToTextArea = true;
            textArea.append(dateFormat.format(new Timestamp(System.currentTimeMillis())) + " MESSAGE: New log file "
                    + ConfigFile.getFileLocation() + "\\Logs\\" + dateFormat.format(date) + ".txt created\n");
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes an error to the file, takes in an exception and converts it to a
     * string and also takes in a message type
     * 
     * @param e           Exception object that is passed in
     * @param messageType Message type to use when logging errors
     */
    public static void writeError(Exception e, String messageType) {
        try {
            errorFileWriter
                    .write(dateFormat.format(new Timestamp(System.currentTimeMillis())) + " " + messageType + ": ");
            errorFileWriter.write(e.toString());
            errorFileWriter.newLine();
            errorFileWriter.flush();

            if (logToTextArea) {
                textArea.append(
                        dateFormat.format(new Timestamp(System.currentTimeMillis())) + " " + messageType + ": ");
                textArea.append(e.toString());
                textArea.append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes an error to the file, takes in an exception as a string and also takes
     * in a message type
     * 
     * @param e           Exception string that is passed in as a string
     * @param messageType Message type to use when logging errors
     */
    public static void writeError(String e, String messageType) {
        try {
            errorFileWriter
                    .write(dateFormat.format(new Timestamp(System.currentTimeMillis())) + " " + messageType + ": ");
            errorFileWriter.write(e);
            errorFileWriter.flush();
            errorFileWriter.newLine();

            if (logToTextArea) {
                textArea.append(
                        dateFormat.format(new Timestamp(System.currentTimeMillis())) + " " + messageType + ": ");
                textArea.append(e);
                textArea.append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes an error to the file, takes in an exception and converts it to a
     * string uses the default message type which is MESSAGE
     * 
     * @param e Exception object that is passed in
     */
    public static void writeError(Exception e) {
        try {
            errorFileWriter.write(dateFormat.format(new Timestamp(System.currentTimeMillis())) + " MESSAGE: ");
            errorFileWriter.write(e.toString());
            errorFileWriter.newLine();
            errorFileWriter.flush();

            if (logToTextArea) {
                textArea.append(dateFormat.format(new Timestamp(System.currentTimeMillis())) + " MESSAGE: ");
                textArea.append(e.toString());
                textArea.append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes an error to the file, takes in an exception as a string uses the
     * default message type which is MESSAGE
     * 
     * @param e Exception string that is passed in
     */
    public static void writeError(String e) {
        try {
            errorFileWriter.write(dateFormat.format(new Timestamp(System.currentTimeMillis())) + " MESSAGE: ");
            errorFileWriter.write(e);
            errorFileWriter.flush();
            errorFileWriter.newLine();

            if (logToTextArea) {
                textArea.append(dateFormat.format(new Timestamp(System.currentTimeMillis())) + " MESSAGE: ");
                textArea.append(e);
                textArea.append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the date format that should be used for all messages
     * 
     * @param dateFormat the date format to be used when logging errors
     */
    public static void setNewDateFormat(SimpleDateFormat dateFormat) {
        ErrorLogger.dateFormat = dateFormat;
    }

    /**
     * Specify wether or not to log to text Area specified, by default this is set
     * to false
     *
     * @param logToTextArea Boolean value that specifies wether to log to text area
     *                      or not, <code>False</code> doesn't log,
     *                      <code>True</code> does log.
     */
    public static void setLogToTextArea(Boolean logToTextArea) {
        ErrorLogger.logToTextArea = logToTextArea;
    }

    /**
     * Closes the log file writer, this must be called to avoid memory leaks or
     * holding the file open
     */
    public static void closeWriter() {
        try {
            errorFileWriter.flush();
            errorFileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(ErrorLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
