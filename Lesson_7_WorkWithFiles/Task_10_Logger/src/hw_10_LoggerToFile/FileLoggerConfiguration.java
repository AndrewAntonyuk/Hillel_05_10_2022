package hw_10_LoggerToFile;

public class FileLoggerConfiguration {
    private String fileName = "DefaultLog";
    private String filePath = "src/hw_10_logs/";
    private LoggingLevel currentLoggingLevel = LoggingLevel.INFO;
    private long maxSizeOfFile = 400;
    private String format = "[%1$td.%1$tm.%1$tY-%1$tH:%1$tM:%1$tS] [%2$-5s] Message: In the class [%4$s] %3$s %n";

    //region Constructors
    public FileLoggerConfiguration() {
    }

    public FileLoggerConfiguration(String fileName) {
        this.fileName = fileName;
    }

    public FileLoggerConfiguration(String fileName, long maxSizeOfFile) {
        this(fileName);
        this.maxSizeOfFile = maxSizeOfFile;
    }

    public FileLoggerConfiguration(String fileName, LoggingLevel currentLoggingLevel, long maxSizeOfFile) {
        this(fileName, maxSizeOfFile);
        this.currentLoggingLevel = currentLoggingLevel;
    }
    //endregion

    //region Getters/Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LoggingLevel getCurrentLoggingLevel() {
        return currentLoggingLevel;
    }

    public void setCurrentLoggingLevel(LoggingLevel currentLoggingLevel) {
        this.currentLoggingLevel = currentLoggingLevel;
    }

    public long getMaxSizeOfFile() {
        return maxSizeOfFile;
    }

    public void setMaxSizeOfFile(long maxSizeOfFile) {
        this.maxSizeOfFile = maxSizeOfFile;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFullFilePath() {
        return filePath + fileName;
    }
    //endregion
}
