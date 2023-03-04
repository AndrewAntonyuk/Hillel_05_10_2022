package javapro.basemath;

import javapro.logger.FileLogger;
import javapro.logger.FileLoggerConfiguration;
import javapro.logger.FileMaxSizeReachedException;
import javapro.logger.LoggingLevel;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MathMulDivTest {
    private MathMulDiv mathMulDiv;
    private FileLogger fileLogger;

    private static Stream<Arguments> provideMullTest() {
        return Stream.of(
                Arguments.of(0.0, 0.0, 0.0),
                Arguments.of(1.0, 1.0, 1.0),
                Arguments.of(2.0, 2.0, 4.0),
                Arguments.of(-2.0, 52.5, -105.0),
                Arguments.of(1.0, 125.36, 125.36),
                Arguments.of(125.36, 1.0, 125.36)
        );
    }

    private static Stream<Arguments> provideDivTest() {
        return Stream.of(
                Arguments.of(5.0, 1.0, 5.0),
                Arguments.of(0.0, 5.0, 0.0),
                Arguments.of(1024.0, 4.0, 256.0),
                Arguments.of(-12.5, 2.0, -6.25),
                Arguments.of(12.5, -2.0, -6.25)
        );
    }

    private static Stream<Arguments> provideDivByZeroTest() {
        return Stream.of(
                Arguments.of(5.0, 0.0),
                Arguments.of(0.0, 0.0),
                Arguments.of(-1024.0, 0.0),
                Arguments.of(-12.5, 0.0),
                Arguments.of(102.75, 0.0)
        );
    }

    @BeforeEach
    public void init() {
        mathMulDiv = new MathMulDiv();
        fileLogger = new FileLogger(new FileLoggerConfiguration("LogMulDiv.txt", LoggingLevel.INFO, 200000), MathMulDiv.class.getName());
    }

    @ParameterizedTest()
    @MethodSource("provideMullTest")
    public void mull(Double mul1, Double mul2, Double product) {
        assertEquals(product, mathMulDiv.mul(mul1, mul2));

        try {
            fileLogger.info("Test complete for: " + product + " = " + mul1 + " * " + mul2);
        } catch (FileMaxSizeReachedException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest()
    @MethodSource("provideDivTest")
    public void div(Double dividend, Double divisor, Double quotient) {
        assertEquals(quotient, mathMulDiv.div(dividend, divisor));

        try {
            fileLogger.info("Test complete for: " + quotient + " = " + dividend + " / " + divisor);
        } catch (FileMaxSizeReachedException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest()
    @MethodSource("provideDivByZeroTest")
    public void divByZero(Double dividend, Double divisor) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mathMulDiv.div(dividend, divisor));
        assertEquals("Divisor can't be 0", exception.getMessage());

        try {
            fileLogger.info("Test complete for divide by zero: "
                    + "exception \"" + exception.getMessage() + "\" was result for "
                    + dividend + " / " + divisor);
        } catch (FileMaxSizeReachedException e) {
            throw new RuntimeException(e);
        }
    }
}