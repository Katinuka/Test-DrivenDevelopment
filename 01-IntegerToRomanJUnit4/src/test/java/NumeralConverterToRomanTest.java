/**
 * This is code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
import org.junit.Assert;
import org.junit.Test;
import src.main.java.org.example.NumeralConverter;

public class NumeralConverterToRomanTest {

    @Test
    public void testToRoman() {
        testInvalidArabic();

        testWithoutSubtractiveForms();

        testSubtractiveFormsOnly();

        testMixed();
    }

    @Test
    public void testInvalidArabic() {
        AssertUtils.assertThrowsOrReturnsNull(
                () -> NumeralConverter.toRoman(Integer.MIN_VALUE), Exception.class);

        AssertUtils.assertThrowsOrReturnsNull(
                () -> NumeralConverter.toRoman(-1), Exception.class);

        AssertUtils.assertThrowsOrReturnsNull(
                () -> NumeralConverter.toRoman(0), Exception.class);

        AssertUtils.assertThrowsOrReturnsNull(
                () -> NumeralConverter.toRoman(4000), Exception.class);

        AssertUtils.assertThrowsOrReturnsNull(
                () -> NumeralConverter.toRoman(Integer.MAX_VALUE), Exception.class);
    }
    @Test
    public void testWithoutSubtractiveForms() {
        Assert.assertEquals("II", NumeralConverter.toRoman(2));
        Assert.assertEquals("VI", NumeralConverter.toRoman(6));
        Assert.assertEquals("XIII", NumeralConverter.toRoman(13));
        Assert.assertEquals("XXXVII", NumeralConverter.toRoman(37));
        Assert.assertEquals("LXXI", NumeralConverter.toRoman(71));
        Assert.assertEquals("C", NumeralConverter.toRoman(100));
        Assert.assertEquals("CCLXXXI", NumeralConverter.toRoman(281));
        Assert.assertEquals("DCCLXXXVII", NumeralConverter.toRoman(787));
        Assert.assertEquals("MLVI", NumeralConverter.toRoman(1056));
        Assert.assertEquals("MMMDCCCLXXXVIII", NumeralConverter.toRoman(3888));
    }

    @Test
    public void testSubtractiveFormsOnly() {
        Assert.assertEquals("IV", NumeralConverter.toRoman(4));
        Assert.assertEquals("IX", NumeralConverter.toRoman(9));
        Assert.assertEquals("XLIX", NumeralConverter.toRoman(49));
        Assert.assertEquals("XC", NumeralConverter.toRoman(90));
        Assert.assertEquals("XCIV", NumeralConverter.toRoman(94));
        Assert.assertEquals("CDXCIV", NumeralConverter.toRoman(494));
        Assert.assertEquals("CM", NumeralConverter.toRoman(900));
        Assert.assertEquals("CMXLIX", NumeralConverter.toRoman(949));
    }

    @Test
    public void testMixed() {
        Assert.assertEquals("VIII", NumeralConverter.toRoman(8));
        Assert.assertEquals("XIX", NumeralConverter.toRoman(19));
        Assert.assertEquals("XLVII", NumeralConverter.toRoman(47));
        Assert.assertEquals("CDLIX", NumeralConverter.toRoman(459));
        Assert.assertEquals("CCCXLII", NumeralConverter.toRoman(342));
        Assert.assertEquals("DV", NumeralConverter.toRoman(505));
        Assert.assertEquals("DCI", NumeralConverter.toRoman(601));
        Assert.assertEquals("CM", NumeralConverter.toRoman(900));
        Assert.assertEquals("CMLIV", NumeralConverter.toRoman(954));
        Assert.assertEquals("MI", NumeralConverter.toRoman(1001));
        Assert.assertEquals("MCCCXL", NumeralConverter.toRoman(1340));
        Assert.assertEquals("MDCCCI", NumeralConverter.toRoman(1801));
        Assert.assertEquals("MMXL", NumeralConverter.toRoman(2040));
        Assert.assertEquals("MMCXII", NumeralConverter.toRoman(2112));
        Assert.assertEquals("MMCDLXXXVIII", NumeralConverter.toRoman(2488));
        Assert.assertEquals("MMMXCIV", NumeralConverter.toRoman(3094));
        Assert.assertEquals("MMMCMXLVII", NumeralConverter.toRoman(3947));
        Assert.assertEquals("MMMCCCLXXXVIII", NumeralConverter.toRoman(3388));
    }
}