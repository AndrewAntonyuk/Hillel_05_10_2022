import hw_10_LoggerToFile.FileLogger;
import hw_10_LoggerToFile.FileLoggerConfiguration;
import hw_10_LoggerToFile.LoggingLevel;
import hw_10_Tests.TestLoggerClass1;
import hw_10_Tests.TestLoggerClass2;

public class Main {
    public static void main(String[] args) {
        FileLoggerConfiguration loggerConfiguration = new FileLoggerConfiguration("TestLogs.txt",
                LoggingLevel.INFO, 1200);
        FileLogger logger = new FileLogger(loggerConfiguration);
        TestLoggerClass1 loggerClass1 = new TestLoggerClass1(logger);
        TestLoggerClass2 loggerClass2 = new TestLoggerClass2(logger);

        loggerClass1.testFoo();

        loggerConfiguration.setCurrentLoggingLevel(LoggingLevel.DEBUG);
        logger.setLoggerConfiguration(loggerConfiguration);
        loggerClass2.testFoo();
    }
}
