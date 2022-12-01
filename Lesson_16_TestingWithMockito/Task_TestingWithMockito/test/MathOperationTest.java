import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MathOperationTest {
    @Test
    public void testSumDirectOrder() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(Arrays.asList(3, 5, 7));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(15, mathOperation.sum());
    }

    @Test
    public void testSumReverseOrder() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(Arrays.asList(7, 5, 3));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(15, mathOperation.sum());
    }

    @Test
    public void testSumOne() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(List.of(7));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(7, mathOperation.sum());
    }

    @Test
    public void testSumLessZero() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(Arrays.asList(-7, -3, -5));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(-15, mathOperation.sum());
    }

    @Test
    public void testSumEmpty() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(List.of());

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);

        IllegalStateException e = assertThrows(IllegalStateException.class, mathOperation::sum);
        assertEquals(e.getMessage(), "List can't be empty");
    }

    @Test
    public void testAvg() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(Arrays.asList(7, 5, 3));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(5, mathOperation.avg());
    }

    @Test
    public void testAvgLessZero() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(Arrays.asList(-7, -5, -3));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(-5, mathOperation.avg());
    }

    @Test
    public void testAvgSameNumber() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(Arrays.asList(7, 7, 7));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(7, mathOperation.avg());
    }

    @Test
    public void testAvgEmpty() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(List.of());

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        IllegalStateException e = assertThrows(IllegalStateException.class, mathOperation::sum);
        assertEquals(e.getMessage(), "List can't be empty");
    }

    @Test
    public void testMaxDirectOrder() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(Arrays.asList(3, 5, 7));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(7, mathOperation.max());
    }

    @Test
    public void testMaxReverseOrder() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(Arrays.asList(7, 5, 3));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(7, mathOperation.max());
    }

    @Test
    public void testMaxSameNumbers() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(Arrays.asList(7, 7, 7));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(7, mathOperation.max());
    }

    @Test
    public void testMaxLessZero() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(Arrays.asList(-7, -4, -1));

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        assertEquals(-1, mathOperation.max());
    }

    @Test
    public void testMaxEmpty() {
        ItemSource mockAWSItemSource = mock(ItemSource.class);
        when(mockAWSItemSource.getItems()).thenReturn(List.of());

        MathOperation mathOperation = new MathOperation(mockAWSItemSource);
        IllegalStateException e = assertThrows(IllegalStateException.class, mathOperation::sum);
        assertEquals(e.getMessage(), "List can't be empty");
    }
}
