import hw_10_LoggerParrents.*;
import hw_10_LoggerToFile.*;
import hw_10_LoggerToStd.StdLogger;
import hw_10_LoggerToStd.StdLoggerConfiguration;
import hw_10_LoggerToStd.StdLoggerConfigurationLoader;
import hw_10_Tests.*;

public class Main {
    public static void main(String[] args) {
        FileLoggerConfigurationLoader configurationLoader = new FileLoggerConfigurationLoader();
        FileLoggerConfiguration loggerConfiguration = new FileLoggerConfiguration("TestLogs",
                LoggingLevel.INFO, 500);
        FileLogger logger = new FileLogger(loggerConfiguration);
        TestLoggerClass1 loggerClass1 = new TestLoggerClass1(logger);

        loggerClass1.testFoo();

        loggerConfiguration = (FileLoggerConfiguration) configurationLoader.load("src/hw_10_Configs/FilLoggerConfig.properties");
        logger.setLoggerConfiguration(loggerConfiguration);
        TestLoggerClass2 loggerClass2 = new TestLoggerClass2(logger);
        loggerClass2.testFoo();

        StdLoggerConfigurationLoader stdLoggerConfigurationLoader = new StdLoggerConfigurationLoader();
        StdLoggerConfiguration stdLoggerConfiguration = (StdLoggerConfiguration) stdLoggerConfigurationLoader.load("src/hw_10_Configs/StdLoggerConfig.properties");
        StdLogger stdLogger = new StdLogger(stdLoggerConfiguration);

        loggerClass1 = new TestLoggerClass1(stdLogger);
        loggerClass1.testFoo();

        loggerClass2 = new TestLoggerClass2(stdLogger);
        loggerClass2.testFoo();
    }
}
