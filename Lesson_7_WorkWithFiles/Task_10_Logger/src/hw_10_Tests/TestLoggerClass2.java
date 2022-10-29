package hw_10_Tests;

import hw_10_LoggerToFile.FileMaxSizeReachedException;
import hw_10_LoggerToFile.FileLogger;
import hw_10_LoggerToFile.FileLoggerConfiguration;
import hw_10_LoggerToFile.LoggingLevel;

public class TestLoggerClass2 {
    FileLogger logger;

    public TestLoggerClass2() {
        logger = new FileLogger(new FileLoggerConfiguration("Logs.txt", LoggingLevel.DEBUG, 400)
                , TestLoggerClass2.class.getName());
    }

    public TestLoggerClass2(FileLogger logger) {
        this.logger = logger;
        logger.setInstanceName(TestLoggerClass2.class.getName());
    }

    public void testFoo() {
        int sum = 0;
        try {
            logger.info("Start function testFoo");
            for (int i = 0; i < 20; i++) {
                sum += i * 5;
                logger.debug("Current add in testFoo is: " + (i * 5));
            }
            logger.info("Finally sum in testFoo is: " + sum);
        } catch (FileMaxSizeReachedException e) {
            throw new RuntimeException(e);
        }
    }
}
