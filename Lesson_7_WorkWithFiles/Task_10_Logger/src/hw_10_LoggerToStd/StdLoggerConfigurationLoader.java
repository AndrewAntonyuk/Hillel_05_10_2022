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

            loggerConfiguration.setCurrentLoggingLevel(LoggingLevel.valueOf(properties.getProperty("LEVEL")));
            loggerConfiguration.setFormat(properties.getProperty("FORMAT"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return loggerConfiguration;
        }
    }
}
