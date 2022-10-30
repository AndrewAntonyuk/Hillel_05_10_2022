import hw_10_Logger.*;
import hw_10_LoggerToFile.*;
import hw_10_LoggerToStd.*;

public class Main {
    public static void main(String[] args) {
        FileLoggerConfigurationLoader configurationLoader = new FileLoggerConfigurationLoader();
        FileLoggerConfiguration loggerConfiguration = new FileLoggerConfiguration("src/hw_10_logs/TestLogs.txt",
                LoggingLevel.INFO, 500);
        FileLogger logger = new FileLogger(loggerConfiguration, "TestLoggerFromObject");

        logger.debug("Some debug text");
        logger.debug("Some debug text1");
        logger.debug("Some debug text2");
        logger.info("Some info text");

        loggerConfiguration = (FileLoggerConfiguration) configurationLoader.load("src/hw_10_Configs/FilLoggerConfig.properties");
        logger.setLoggerConfiguration(loggerConfiguration);
        logger.setInstanceName("TestLoggerFromPropFile");

        logger.debug("Some debug text");
        logger.debug("Some debug text1");
        logger.debug("Some debug text2");
        logger.info("Some info text");

        StdLoggerConfigurationLoader stdLoggerConfigurationLoader = new StdLoggerConfigurationLoader();
        StdLoggerConfiguration stdLoggerConfiguration = (StdLoggerConfiguration) stdLoggerConfigurationLoader.load("src/hw_10_Configs/StdLoggerConfig.properties");
        StdLogger stdLogger = new StdLogger(stdLoggerConfiguration);
        stdLogger.setInstanceName("TestLoggerStdFromPropFile");

        stdLogger.debug("Some debug text to std");
        stdLogger.debug("Some debug text to std");
        stdLogger.info("Some info text to std");
        stdLogger.debug("Some debug text to std");

        stdLoggerConfiguration.setCurrentLoggingLevel(LoggingLevel.DEBUG);

        stdLogger.debug("Some debug text to std");
        stdLogger.debug("Some debug text to std");
        stdLogger.info("Some info text to std");
        stdLogger.debug("Some debug text to std");
    }
}
