package hw_10_LoggerToFile;

import hw_10_Logger.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileLoggerConfigurationLoader extends LoggerConfigurationLoader {
    @Override
    public LoggerConfiguration load(String fullPathToConfigFile) {
        FileLoggerConfiguration loggerConfiguration = new FileLoggerConfiguration();

        try (InputStream input = new FileInputStream(fullPathToConfigFile)) {
            Properties properties = new Properties();

            properties.load(input);

            loggerConfiguration.setCurrentLoggingLevel(LoggingLevel.valueOf(properties.getProperty("LEVEL")));
            loggerConfiguration.setFileName(properties.getProperty("FILE"));
            loggerConfiguration.setFormat(properties.getProperty("FORMAT"));
            loggerConfiguration.setMaxSizeOfFile(Long.parseLong(properties.getProperty("MAX-SIZE")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return loggerConfiguration;
        }
    }
}
