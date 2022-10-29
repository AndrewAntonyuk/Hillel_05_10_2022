package hw_10_LoggerParrents;

public abstract class BaseLogger {
    private BaseLoggerConfiguration loggerConfiguration;
    private String instanceName = "Without name data";

    public abstract void debug(String message);

    public abstract void info(String message);

    //region Getters/Setters
    public BaseLoggerConfiguration getLoggerConfiguration() {
        return loggerConfiguration;
    }

    public void setLoggerConfiguration(BaseLoggerConfiguration loggerConfiguration) {
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
