import hw_10_LoggerToFile.FileLogger;
import hw_10_LoggerToFile.FileLoggerConfiguration;
import hw_10_LoggerToFile.FileMaxSizeReachedException;
import hw_10_LoggerToFile.LoggingLevel;
public class Main {
    public static void main(String[] args) {
        FileLoggerConfiguration loggerConfiguration = new FileLoggerConfiguration("TestLogs.txt",
                LoggingLevel.INFO, 1200);
        FileLogger logger = new FileLogger(loggerConfiguration);
        logger.setInstanceName("TestFileLogger");

        try {
            logger.debug("Test debug text 1");
            logger.debug("Test debug text 2");
            logger.debug("Test debug text 3");
            logger.debug("Test debug text 4");
            logger.info("Test info text 1");

            loggerConfiguration.setCurrentLoggingLevel(LoggingLevel.DEBUG);
            logger.setLoggerConfiguration(loggerConfiguration);

            logger.debug("Test debug text 1");
            logger.debug("Test debug text 2");
            logger.debug("Test debug text 3");
            logger.debug("Test debug text 4");
            logger.info("Test info text 1");

        } catch (FileMaxSizeReachedException e) {
            throw new RuntimeException(e);
        }
    }
}
