package hw_10_Tests;

import hw_10_LoggerParrents.*;

public class TestLoggerClass2 {
    BaseLogger logger;

    public TestLoggerClass2() {
    }

    public TestLoggerClass2(BaseLogger logger) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
