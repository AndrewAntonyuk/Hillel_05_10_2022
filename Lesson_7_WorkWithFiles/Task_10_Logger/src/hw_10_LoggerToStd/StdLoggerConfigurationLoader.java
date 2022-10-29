package hw_10_LoggerToStd;

import hw_10_LoggerParrents.BaseLoggerConfiguration;
import hw_10_LoggerParrents.BaseLoggerConfigurationLoader;
import hw_10_LoggerParrents.LoggingLevel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StdLoggerConfigurationLoader extends BaseLoggerConfigurationLoader {
    @Override
    public BaseLoggerConfiguration load(String fullPathToConfigFile) {
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
