package hw_10_LoggerToFile;

import hw_10_Logger.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class FileLogger extends Logger {
    private String lastFullLogFileName = "";

    //region Constructors
    public FileLogger() {
        super();
        setLoggerConfiguration(new FileLoggerConfiguration());
    }

    public FileLogger(FileLoggerConfiguration loggerConfiguration) {
        super();
        setLoggerConfiguration(loggerConfiguration);
    }

    public FileLogger(String instanceName) {
        this();
        this.setInstanceName(instanceName);
    }

    public FileLogger(FileLoggerConfiguration loggerConfiguration, String instanceName) {
        super();
        setLoggerConfiguration(loggerConfiguration);
        this.setInstanceName(instanceName);
    }
    //endregion

    public void debug(String message) {
        if (super.getLoggerConfiguration().getCurrentLoggingLevel().equals(LoggingLevel.DEBUG)) {
            String formattedMessage = String.format(super.getLoggerConfiguration().getFormat(), LocalDateTime.now(),
                    LoggingLevel.DEBUG, message, super.getInstanceName());

            putLogToFile(formattedMessage);
        }
    }

    public void info(String message) {
        if (super.getLoggerConfiguration().getCurrentLoggingLevel().compareTo(LoggingLevel.INFO) <= 0) {
            String formattedMessage = String.format(super.getLoggerConfiguration().getFormat(), LocalDateTime.now(),
                    LoggingLevel.INFO, message, super.getInstanceName());

            putLogToFile(formattedMessage);
        }
    }

    private void putLogToFile(String logMessage) {
        Writer writer = null;
        OutputStreamWriter outputStreamWriter = null;
        FileOutputStream fileOutputStream = null;
        File file;

        try {
            file = getLogFile();
            fileOutputStream = new FileOutputStream(file, true);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            writer = new BufferedWriter(outputStreamWriter);

            writer.write(logMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private File getLogFile() {
        FileLoggerConfiguration loggerConfiguration = (FileLoggerConfiguration) this.getLoggerConfiguration();
        File currentLogFile;

        String newFileName = String.format("%4$s/%2$s_%1$td_%1$tm_%1$tY_%1$tH_%1$tM_%1$tS_%1$tL.%3$s", LocalDateTime.now(),
                loggerConfiguration.getFileName(), loggerConfiguration.getFileExtension(), loggerConfiguration.getDirectoryPath());

        if (lastFullLogFileName.isEmpty() || !lastFullLogFileName.contains(loggerConfiguration.getDirectoryPath()
                + "/" + loggerConfiguration.getFileName() + "_")) {
            currentLogFile = createFile(newFileName);
        } else {
            currentLogFile = new File(lastFullLogFileName);
            if (currentLogFile.length() >= loggerConfiguration.getMaxSizeOfFile()) {
                currentLogFile = createFile(newFileName);
            }
        }

        return currentLogFile;
    }

    private File createFile(String fullPathFile) {
        File file = new File(fullPathFile);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lastFullLogFileName = fullPathFile;

        return file;
    }

    private String getLastFullLogFileName() {
        String directoryPath = ((FileLoggerConfiguration) this.getLoggerConfiguration()).getDirectoryPath();
        String fileName = ((FileLoggerConfiguration) this.getLoggerConfiguration()).getFileName();

        if (directoryPath.isEmpty()) {
            return "";
        }

        File folder = new File(directoryPath);
        File[] listOfFiles;

        if (folder.exists()) {
            listOfFiles = folder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File directory, String name) {
                    return name.contains(fileName);
                }
            });

            if (listOfFiles.length == 0) {
                return "";
            }

            return directoryPath + "/" + listOfFiles[listOfFiles.length - 1].getName();
        } else {
            return "";
        }
    }

    //region Setters/Getters
    @Override
    public void setLoggerConfiguration(LoggerConfiguration loggerConfiguration) {
        super.setLoggerConfiguration(loggerConfiguration);
        lastFullLogFileName = getLastFullLogFileName();
    }
    //endregion
}
