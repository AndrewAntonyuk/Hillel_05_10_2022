package hw_10_LoggerToStd;

import hw_10_LoggerParrents.BaseLogger;
import hw_10_LoggerParrents.LoggingLevel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class StdLogger extends BaseLogger {
    //region Constructors
    public StdLogger() {
        super();
        super.setLoggerConfiguration(new StdLoggerConfiguration());
    }

    public StdLogger(StdLoggerConfiguration loggerConfiguration) {
        super();
        super.setLoggerConfiguration(loggerConfiguration);
    }

    public StdLogger(String instanceName) {
        this();
        super.setInstanceName(instanceName);
    }

    public StdLogger(StdLoggerConfiguration loggerConfiguration, String instanceName) {
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
        System.out.print(logMessage);
    }
}
