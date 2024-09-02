import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static src.main.java.org.example.NumeralConverter.fromRoman;

public class NumeralConverterFromRomanTest {
    @Test
    public void testFromRoman() {
        testInvalidRoman();
    }

    @Test
    public void testInvalidRoman() {
        String[] invalidParams = {
                null,
                "",
                "532",
                " _   @rty$  5%%",
                "XII_a15",
                "IIX",
                "XXXXV",
                "ivv",
                "XLVIIII"};

        Arrays.stream(invalidParams).forEach(
                str -> assertThrows(Exception.class, () -> fromRoman(str))
        );
    }
}
