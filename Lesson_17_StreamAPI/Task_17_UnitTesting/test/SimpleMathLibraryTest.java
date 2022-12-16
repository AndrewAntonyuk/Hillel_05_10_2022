import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Tests for SimpleMathLibrary")
public class SimpleMathLibraryTest {
    private SimpleMathLibrary mathLibrary;

    @BeforeEach
    void init() {
        mathLibrary = new SimpleMathLibrary();
    }

    private static Stream<Arguments> provideAddForTypicalTest() {
        return Stream.of(
                Arguments.of(2.0, 5.0, 7.0),
                Arguments.of(124.5, 8.1, 132.6),
                Arguments.of(1002.0, 875.25, 1877.25)
        );
    }

    private static Stream<Arguments> provideAddForReverseDirectionTest() {
        return Stream.of(
                Arguments.of(5.0, 2.0, 7.0),
                Arguments.of(8.1, 124.5, 132.6),
                Arguments.of(875.25, 1002.0, 1877.25)
        );
    }

    private static Stream<Arguments> provideAddForLessThanZeroTest() {
        return Stream.of(
                Arguments.of(-5.0, 2.0, -3.0),
                Arguments.of(8.1, -124.5, -116.4),
                Arguments.of(-875.25, 1002.0, 126.75)
        );
    }

    private static Stream<Arguments> provideMinusForTypicalTest() {
        return Stream.of(
                Arguments.of(5.0, 2.0, 3.0),
                Arguments.of(124.5, 124.5, 0.0),
                Arguments.of(1002.0, 875.25, 126.75)
        );
    }

    private static Stream<Arguments> provideMinusForMinuendGreatThanSubtrahendTest() {
        return Stream.of(
                Arguments.of(0.0, 5.0, -5.0),
                Arguments.of(0.0, 12.9, -12.9),
                Arguments.of(12.5, 25.0, -12.5)
        );
    }

    @ParameterizedTest(name = "Test with parameters {0} and {1} should be {2}")
    @MethodSource("provideAddForTypicalTest")
    public void typical_add_test(double add1, double add2, double sum) {
        assertEquals(sum, mathLibrary.add(add1, add2));
    }

    @ParameterizedTest(name = "Test with parameters {0} and {1} should be {2}")
    @MethodSource("provideAddForReverseDirectionTest")
    public void reverse_add_direction_test(double add1, double add2, double sum) {
        assertEquals(sum, mathLibrary.add(add1, add2));
    }

    @ParameterizedTest(name = "Test with parameters {0} and {1} should be {2}")
    @MethodSource("provideAddForLessThanZeroTest")
    public void with_less_than_zero_add_test(double add1, double add2, double sum) {
        assertEquals(sum, mathLibrary.add(add1, add2));
    }

    @ParameterizedTest(name = "Test with parameters {0} and {1} should be {2}")
    @MethodSource("provideMinusForTypicalTest")
    public void typical_minus_test(double minuend, double subtrahend, double different) {
        assertEquals(different, mathLibrary.minus(minuend, subtrahend));
    }

    @ParameterizedTest(name = "Test with parameters {0} and {1} should be {2}")
    @MethodSource("provideMinusForMinuendGreatThanSubtrahendTest")
    public void minuend_great_than_subtrahend_minus_test(double minuend, double subtrahend, double different) {
        assertEquals(different, mathLibrary.minus(minuend, subtrahend));
    }
}
