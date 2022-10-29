package hw_10_LoggerToFile;

import hw_10_LoggerParrents.*;

public class FileLoggerConfiguration extends BaseLoggerConfiguration {
    //region Constructors
    public FileLoggerConfiguration() {
        super();
    }

    public FileLoggerConfiguration(String fileName) {
        super(fileName);
    }

    public FileLoggerConfiguration(String fileName, long maxSizeOfFile) {
        super(fileName, maxSizeOfFile);
    }

    public FileLoggerConfiguration(String fileName, LoggingLevel currentLoggingLevel, long maxSizeOfFile) {
        super(fileName, currentLoggingLevel, maxSizeOfFile);
    }
    //endregion
}
