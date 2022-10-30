package hw_10_LoggerToStd;

import hw_10_Logger.LoggerConfiguration;
import hw_10_Logger.LoggerConfigurationLoader;
import hw_10_Logger.LoggingLevel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StdLoggerConfigurationLoader extends LoggerConfigurationLoader {
    @Override
    public LoggerConfiguration load(String fullPathToConfigFile) {
        StdLoggerConfiguration loggerConfiguration = new StdLoggerConfiguration();

        try (InputStream input = new FileInputStream(fullPathToConfigFile)) {
            Properties properties = new Properties();

            properties.load(input);

            if (LoggingLevel.isValidEnum(properties.getProperty("LEVEL"))) {
                loggerConfiguration.setCurrentLoggingLevel(LoggingLevel.valueOf(properties.getProperty("LEVEL")));
            }
            loggerConfiguration.setFormat(properties.getProperty("FORMAT"));

            return loggerConfiguration;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
