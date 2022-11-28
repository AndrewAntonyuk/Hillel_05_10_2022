import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterRomanToInt {
    private final HashMap<Character, Integer> internalMap = new HashMap<>();
    private int sum = 0;

    public ConverterRomanToInt() {
        initialMap();
    }

    public int convert(String s) {
        int i = s.length() - 1;

        do {
            Integer current = internalMap.get(s.charAt(i));
            Integer next = (i >= 1 ? internalMap.get(s.charAt(i - 1)) : 0);

            if (current > next) {
                sum += (current - next);
            } else {
                sum += (current + next);
            }
            i -= 2;
        } while (i >= 0);

        return sum;
    }

    private void initialMap() {
        internalMap.put('I', 1);
        internalMap.put('V', 5);
        internalMap.put('X', 10);
        internalMap.put('L', 50);
        internalMap.put('C', 100);
        internalMap.put('D', 500);
        internalMap.put('M', 1000);
    }
}

class ConverterRomanToIntTest {
    @Test
    public void allRomanNumber() {
        ConverterRomanToInt converter = new ConverterRomanToInt();
        assertEquals(1666, converter.convert("MDCLXVI"));
    }

    @Test
    public void numbersInDescendingOrderTest1() {
        ConverterRomanToInt converter = new ConverterRomanToInt();
        assertEquals(8, converter.convert("VIII"));
    }

    @Test
    public void numbersInDescendingOrderTest2() {
        ConverterRomanToInt converter = new ConverterRomanToInt();
        assertEquals(2788, converter.convert("MMDCCLXXXVIII"));
    }

    @Test
    public void numbersInMixedOrderTest1() {
        ConverterRomanToInt converter = new ConverterRomanToInt();
        assertEquals(1444, converter.convert("MCDXLIV"));
    }

    @Test
    public void numbersInMixedOrderTest2() {
        ConverterRomanToInt converter = new ConverterRomanToInt();
        assertEquals(944, converter.convert("CMXLIV"));
    }

    @Test
    public void maxValue() {
        ConverterRomanToInt converter = new ConverterRomanToInt();
        assertEquals(3999, converter.convert("MMMCMXCIX"));
    }

    @Test
    public void minValue() {
        ConverterRomanToInt converter = new ConverterRomanToInt();
        assertEquals(1, converter.convert("I"));
    }
}
