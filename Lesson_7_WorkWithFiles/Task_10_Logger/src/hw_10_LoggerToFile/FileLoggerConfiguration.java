package hw_10_LoggerToFile;

import hw_10_Logger.*;

import java.io.File;

public class FileLoggerConfiguration extends LoggerConfiguration {
    private long maxSizeOfFile = 400;
    private String fullFileName = "src/hw_10_logs/DefaultLog.txt";
    private String directoryPath;
    private String fileName;
    private String fileExtension;

    //region Constructors
    public FileLoggerConfiguration() {
        super();
        parseFullFileName();
    }

    public FileLoggerConfiguration(LoggingLevel loggingLevel) {
        super(loggingLevel);
        parseFullFileName();
    }

    public FileLoggerConfiguration(String fullFileName) {
        this();
        this.fullFileName = fullFileName;
        parseFullFileName();
    }

    public FileLoggerConfiguration(String fullFileName, long maxSizeOfFile) {
        this(fullFileName);
        this.maxSizeOfFile = maxSizeOfFile;
        parseFullFileName();
    }

    public FileLoggerConfiguration(String fullFileName, LoggingLevel currentLoggingLevel, long maxSizeOfFile) {
        this(fullFileName, maxSizeOfFile);
        super.setCurrentLoggingLevel(currentLoggingLevel);
        parseFullFileName();
    }
    //endregion

    private void parseFullFileName() {
        File file = new File(fullFileName);
        fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        directoryPath = file.getParent();
        fileExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }

    //region Getters/Setters
    public String getFullFileName() {
        return fullFileName;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
        parseFullFileName();
    }

    public long getMaxSizeOfFile() {
        return maxSizeOfFile;
    }

    public void setMaxSizeOfFile(long maxSizeOfFile) {
        this.maxSizeOfFile = maxSizeOfFile;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }
    //endregion
}
