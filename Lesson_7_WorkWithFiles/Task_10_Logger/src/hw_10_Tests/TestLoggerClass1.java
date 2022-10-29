package hw_10_Tests;

import hw_10_LoggerExceptions.FileMaxSizeReachedException;
import hw_10_LoggerToFile.FileLogger;
import hw_10_LoggerToFile.FileLoggerConfiguration;
import hw_10_LoggerToFile.LoggingLevel;

public class TestLoggerClass1 {
    FileLogger logger;

    public TestLoggerClass1() {
        logger = new FileLogger(new FileLoggerConfiguration("Logs.txt", LoggingLevel.DEBUG, 400)
                , TestLoggerClass1.class.getName());
    }

    public TestLoggerClass1(FileLogger logger) {
        this.logger = logger;
        logger.setInstanceName(TestLoggerClass1.class.getName());
    }

    public void testFoo() {
        int sum = 0;
        try {
            logger.info("Start function testFoo");
            for (int i = 0; i < 7; i++) {
                sum += i * 5;
                logger.debug("Current add in testFoo is: " + (i * 5));
            }
            logger.info("Finally sum in testFoo is: " + sum);
        } catch (FileMaxSizeReachedException e) {
            throw new RuntimeException(e);
        }
    }
}
