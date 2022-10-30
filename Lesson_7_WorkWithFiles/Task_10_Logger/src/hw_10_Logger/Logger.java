package hw_10_Logger;

public abstract class Logger {
    private LoggerConfiguration loggerConfiguration;
    private String instanceName = "Without name data";

    public abstract void debug(String message);

    public abstract void info(String message);

    //region Getters/Setters
    public LoggerConfiguration getLoggerConfiguration() {
        return loggerConfiguration;
    }

    public void setLoggerConfiguration(LoggerConfiguration loggerConfiguration) {
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
