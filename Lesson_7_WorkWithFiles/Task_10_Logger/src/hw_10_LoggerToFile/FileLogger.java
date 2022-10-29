package hw_10_LoggerToFile;

import hw_10_LoggerParrents.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class FileLogger extends BaseLogger {
    //region Constructors
    public FileLogger() {
        super();
        super.setLoggerConfiguration(new FileLoggerConfiguration());
    }

    public FileLogger(FileLoggerConfiguration loggerConfiguration) {
        super();
        super.setLoggerConfiguration(loggerConfiguration);
    }

    public FileLogger(String instanceName) {
        this();
        super.setInstanceName(instanceName);
    }

    public FileLogger(FileLoggerConfiguration loggerConfiguration, String instanceName) {
        super();
        super.setLoggerConfiguration(loggerConfiguration);
        super.setInstanceName(instanceName);
    }
    //endregion

    public void debug(String message) {
        if (super.getLoggerConfiguration().getCurrentLoggingLevel().compareTo(LoggingLevel.DEBUG) == 0) {
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
            file = getLogFile(super.getLoggerConfiguration().getFilePath());
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

    private File getLogFile(String directory) {
        File currentLogFile = getLastLogFile(directory);
        String newFileName = String.format("%2$s_%1$td_%1$tm_%1$tY_%1$tH_%1$tM_%1$tS_%1$tL.txt", LocalDateTime.now(),
                super.getLoggerConfiguration().getFileName());

        if (currentLogFile == null) {
            currentLogFile = createFile(super.getLoggerConfiguration().getFilePath() + newFileName);
        } else {
            if (currentLogFile.length() >= super.getLoggerConfiguration().getMaxSizeOfFile()) {
                currentLogFile = createFile(super.getLoggerConfiguration().getFilePath() + newFileName);
            }
        }

        return currentLogFile;
    }

    private File getLastLogFile(String directory) {
        if (directory == null) {
            return null;
        }

        File folder = new File(directory);
        File[] listOfFiles;
        String fileName = super.getLoggerConfiguration().getFileName();

        if (folder.exists()) {
            listOfFiles = folder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.contains(fileName);
                }
            });

            if (listOfFiles.length == 0) {
                return null;
            }

            return listOfFiles[listOfFiles.length - 1];
        } else {
            return null;
        }
    }

    private File createFile(String fullPathFile) {
        File file = new File(fullPathFile);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }
}
