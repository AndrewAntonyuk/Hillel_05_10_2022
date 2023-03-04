package javapro.basemath;

import javapro.logger.FileLogger;
import javapro.logger.FileLoggerConfiguration;
import javapro.logger.FileMaxSizeReachedException;
import javapro.logger.LoggingLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MathAddSubTest {
    private MathAddSub mathAddSub;
    private FileLogger fileLogger;

    private static Stream<Arguments> provideAddTest() {
        return Stream.of(
                Arguments.of(5.0, 2.0, 7.0),
                Arguments.of(2.0, 5.0, 7.0),
                Arguments.of(8.1, 124.5, 132.6),
                Arguments.of(875.25, 1002.0, 1877.25)
        );
    }

    private static Stream<Arguments> provideSubTest() {
        return Stream.of(
                Arguments.of(5.0, 2.0, 3.0),
                Arguments.of(2.0, 5.0, -3.0),
                Arguments.of(-22.36, 5.78, -28.14),
                Arguments.of(10.5, 2.3, 8.2)
        );
    }

    @BeforeEach
    public void init() {
        mathAddSub = new MathAddSub();
        fileLogger = new FileLogger(new FileLoggerConfiguration("LogAddSub.txt", LoggingLevel.INFO, 200000), MathAddSub.class.getName());
    }

    @ParameterizedTest()
    @MethodSource("provideAddTest")
    void add(double add1, double add2, double sum) {
        assertEquals(sum, mathAddSub.add(add1, add2));

        try {
            fileLogger.info("Test complete for: " + sum + " = " + add1 + " + " + add2);
        } catch (FileMaxSizeReachedException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest()
    @MethodSource("provideSubTest")
    void sub(Double minuend, Double subtrahend, Double difference) {
        assertEquals(difference, mathAddSub.sub(minuend, subtrahend));

        try {
            fileLogger.info("Test complete for: " + difference + " = " + minuend + " - " + subtrahend);
        } catch (FileMaxSizeReachedException e) {
            throw new RuntimeException(e);
        }
    }
}