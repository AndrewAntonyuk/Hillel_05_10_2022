package hw_10_Logger;

public abstract class LoggerConfiguration {
    private LoggingLevel currentLoggingLevel = LoggingLevel.INFO;
    private String format = "[%1$td.%1$tm.%1$tY-%1$tH:%1$tM:%1$tS.%1$tL] [%2$-5s] Message: In the class [%4$s] %3$s %n";

    //region Constructors
    public LoggerConfiguration() {
    }

    public LoggerConfiguration(LoggingLevel loggingLevel) {
        this.currentLoggingLevel = loggingLevel;
    }
    //endregion

    //region Getters/Setters
    public LoggingLevel getCurrentLoggingLevel() {
        return currentLoggingLevel;
    }

    public void setCurrentLoggingLevel(LoggingLevel currentLoggingLevel) {
        this.currentLoggingLevel = currentLoggingLevel;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    //endregion
}
