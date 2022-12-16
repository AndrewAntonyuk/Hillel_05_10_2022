import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Tests for tasks 14 and 15 from HW17")
public class MainTest {
    private Main testMain;

    private static Stream<Arguments> afterLast4TypicalTest() {
        return Stream.of(
                Arguments.of(new int[]{4, 0, 0, 7, 0, 8, 5}, new int[]{0, 0, 7, 0, 8, 5}),
                Arguments.of(new int[]{22, 0, 0, 4, 0, 8, 5}, new int[]{0, 8, 5}),
                Arguments.of(new int[]{4, 0, 0, 7, 0, 4, 5}, new int[]{5})
        );
    }

    private static Stream<Arguments> afterLast4When4IsLastTest() {
        return Stream.of(
                Arguments.of(new int[]{4, 0, 0, 7, 0, 8, 4}, new int[]{}),
                Arguments.of(new int[]{22, 0, 0, 4, 0, 8, 4}, new int[]{}),
                Arguments.of(new int[]{4, 0, 0, 7, 0, 4, 4}, new int[]{})
        );
    }

    private static Stream<Arguments> only1And4TypicalTest() {
        return Stream.of(
                Arguments.of(new int[]{1, 4, 4, 4, 4}, true),
                Arguments.of(new int[]{1, 1, 1, 1, 4}, true),
                Arguments.of(new int[]{1, 1, 1, 4, 4}, true),
                Arguments.of(new int[]{4, 1, 4, 1, 4, 1}, true)
        );
    }

    private static Stream<Arguments> only1And4WithOnly1Test() {
        return Stream.of(
                Arguments.of(new int[]{1, 1, 1, 1}, false),
                Arguments.of(new int[]{1, 1, 1, 1, 1, 1, 1}, false),
                Arguments.of(new int[]{1}, false)
        );
    }

    private static Stream<Arguments> only1And4WithOnly4Test() {
        return Stream.of(
                Arguments.of(new int[]{4}, false),
                Arguments.of(new int[]{4, 4, 4, 4, 4, 4, 4}, false),
                Arguments.of(new int[]{4, 4, 4}, false)
        );
    }

    private static Stream<Arguments> only1And4WithOthersNumbersTest() {
        return Stream.of(
                Arguments.of(new int[]{4, 1, 1, 1, 4, 2, 1, 4}, false),
                Arguments.of(new int[]{4, 1, 55, 1, 4}, false),
                Arguments.of(new int[]{-365}, false)
        );
    }

    @BeforeEach
    void init() {
        testMain = new Main();
    }

    @ParameterizedTest(name = "Result with parameters {0} should be {1}")
    @MethodSource("afterLast4TypicalTest")
    public void typical_get_all_elements_after_last_4_test(final int[] inputArray, int[] resultArray) {
        assertEquals(Arrays.toString(resultArray), Arrays.toString(testMain.getAllAfterLast4(inputArray)));
    }

    @ParameterizedTest(name = "Result with parameters {0} should be {1}")
    @MethodSource("afterLast4When4IsLastTest")
    public void when_4_is_last_get_all_elements_after_last_4_test(final int[] inputArray, int[] resultArray) {
        assertEquals(Arrays.toString(resultArray), Arrays.toString(testMain.getAllAfterLast4(inputArray)));
    }

    @ParameterizedTest(name = "Result with parameters {0} should be {1}")
    @MethodSource("only1And4TypicalTest")
    public void typical_only_1_and_4_test(final int[] inputArray, boolean result) {
        assertEquals(result, testMain.isContainOnly1And4(inputArray));
    }

    @ParameterizedTest(name = "Result with parameters {0} should be {1}")
    @MethodSource("only1And4WithOnly1Test")
    public void with_only_1_only_1_and_4_test(final int[] inputArray, boolean result) {
        assertEquals(result, testMain.isContainOnly1And4(inputArray));
    }

    @ParameterizedTest(name = "Result with parameters {0} should be {1}")
    @MethodSource("only1And4WithOnly4Test")
    public void with_only_4_only_1_and_4_test(final int[] inputArray, boolean result) {
        assertEquals(result, testMain.isContainOnly1And4(inputArray));
    }

    @ParameterizedTest(name = "Result with parameters {0} should be {1}")
    @MethodSource("only1And4WithOthersNumbersTest")
    public void with_others_numbers_only_1_and_4_test(final int[] inputArray, boolean result) {
        assertEquals(result, testMain.isContainOnly1And4(inputArray));
    }
}
