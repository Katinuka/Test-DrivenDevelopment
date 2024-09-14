import org.junit.jupiter.api.Test;
import src.main.java.org.example.NumeralConverter;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static src.main.java.org.example.NumeralConverter.fromRoman;

public class NumeralConverterFromRomanTest {
    @Test
    public void testFromRoman() {
        testInvalidRoman();
        testWithoutSubtractiveForms();
        testSubtractiveFormsOnly();
        testMixed();
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

    @Test
    public void testWithoutSubtractiveForms() {
        assertEquals(3, NumeralConverter.fromRoman("III"));
        assertEquals(7, NumeralConverter.fromRoman("VII"));
        assertEquals(11, NumeralConverter.fromRoman("XI"));
        assertEquals(36, NumeralConverter.fromRoman("XXXVI"));
        assertEquals(73, NumeralConverter.fromRoman("LXXIII"));
        assertEquals(101, NumeralConverter.fromRoman("CI"));
        assertEquals(282, NumeralConverter.fromRoman("CCLXXXII"));
        assertEquals(786, NumeralConverter.fromRoman("DCCLXXXVI"));
        assertEquals(1055, NumeralConverter.fromRoman("MLV"));
        assertEquals(3887, NumeralConverter.fromRoman("MMMDCCCLXXXVII"));
    }

    @Test
    public void testSubtractiveFormsOnly() {
        assertEquals(4, NumeralConverter.fromRoman("IV"));
        assertEquals(9, NumeralConverter.fromRoman("IX"));
        assertEquals(49, NumeralConverter.fromRoman("XLIX"));
        assertEquals(90, NumeralConverter.fromRoman("XC"));
        assertEquals(94, NumeralConverter.fromRoman("XCIV"));
        assertEquals(494, NumeralConverter.fromRoman("CDXCIV"));
        assertEquals(900, NumeralConverter.fromRoman("CM"));
        assertEquals(949, NumeralConverter.fromRoman("CMXLIX"));
    }

    @Test
    public void testMixed() {
        assertEquals(7, NumeralConverter.fromRoman("VII"));
        assertEquals(19, NumeralConverter.fromRoman("XIX"));
        assertEquals(48, NumeralConverter.fromRoman("XLVIII"));
        assertEquals(459, NumeralConverter.fromRoman("CDLIX"));
        assertEquals(343, NumeralConverter.fromRoman("CCCXLIII"));
        assertEquals(505, NumeralConverter.fromRoman("DV"));
        assertEquals(602, NumeralConverter.fromRoman("DCII"));
        assertEquals(900, NumeralConverter.fromRoman("CM"));
        assertEquals(954, NumeralConverter.fromRoman("CMLIV"));
        assertEquals(1001, NumeralConverter.fromRoman("MI"));
        assertEquals(1340, NumeralConverter.fromRoman("MCCCXL"));
        assertEquals(1801, NumeralConverter.fromRoman("MDCCCI"));
        assertEquals(2040, NumeralConverter.fromRoman("MMXL"));
        assertEquals(2112, NumeralConverter.fromRoman("MMCXII"));
        assertEquals(2488, NumeralConverter.fromRoman("MMCDLXXXVIII"));
        assertEquals(3094, NumeralConverter.fromRoman("MMMXCIV"));
        assertEquals(3947, NumeralConverter.fromRoman("MMMCMXLVII"));
        assertEquals(3388, NumeralConverter.fromRoman("MMMCCCLXXXVIII"));
    }
}
