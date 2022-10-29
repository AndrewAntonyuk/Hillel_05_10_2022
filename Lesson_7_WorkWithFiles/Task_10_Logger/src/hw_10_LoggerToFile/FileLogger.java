package hw_10_LoggerToFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class FileLogger {
    private FileLoggerConfiguration loggerConfiguration;
    private String instanceName = "Without name data";

    //region Constructors
    public FileLogger() {
        loggerConfiguration = new FileLoggerConfiguration();
    }

    public FileLogger(FileLoggerConfiguration loggerConfiguration) {
        this.loggerConfiguration = loggerConfiguration;
    }

    public FileLogger(String instanceName) {
        this();
        this.instanceName = instanceName;
    }

    public FileLogger(FileLoggerConfiguration loggerConfiguration, String instanceName) {
        this.loggerConfiguration = loggerConfiguration;
        this.instanceName = instanceName;
    }
    //endregion

    public void debug(String message) throws FileMaxSizeReachedException {
        if (loggerConfiguration.getCurrentLoggingLevel().compareTo(LoggingLevel.DEBUG) == 0) {
            String formattedMessage = String.format(loggerConfiguration.getFormat(), LocalDateTime.now(),
                    LoggingLevel.DEBUG, message, instanceName);

            putLogToFile(formattedMessage);
        }
    }

    public void info(String message) throws FileMaxSizeReachedException {
        if (loggerConfiguration.getCurrentLoggingLevel().compareTo(LoggingLevel.INFO) <= 0) {
            String formattedMessage = String.format(loggerConfiguration.getFormat(), LocalDateTime.now(),
                    LoggingLevel.INFO, message, instanceName);

            putLogToFile(formattedMessage);
        }
    }

    private void putLogToFile(String logMessage) throws FileMaxSizeReachedException {
        Writer writer = null;
        OutputStreamWriter outputStreamWriter = null;
        FileOutputStream fileOutputStream = null;
        File file;

        try {
            file = new File(loggerConfiguration.getFullFilePath());
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                if (file.length() > loggerConfiguration.getMaxSizeOfFile()) {
                    throw new FileMaxSizeReachedException("Current size of log file (" + file.length()
                            + ") is greater than maximum available (" + loggerConfiguration.getMaxSizeOfFile() + ")\n");
                }
            }

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

    //region Getters/Setters
    public FileLoggerConfiguration getLoggerConfiguration() {
        return loggerConfiguration;
    }

    public void setLoggerConfiguration(FileLoggerConfiguration loggerConfiguration) {
        this.loggerConfiguration = loggerConfiguration;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
    //endregion
}
