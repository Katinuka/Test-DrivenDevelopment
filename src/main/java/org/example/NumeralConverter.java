package src.main.java.org.example;

import java.util.HashMap;
import java.util.Map;

public class NumeralConverter {

    /**
     * @author <a href="https://leetcode.com/problems/integer-to-roman/solutions/5495753/simple-code-easy-to-understand-beginner-friendly-o-n-time-complexity/">LeetCode</a>
     */
    public static String toRoman(int num) {
        if (num < 1 || num > 3999) {
            return null;
        }

        int[] n = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] s = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int i =0;
        String str = new String();
        while (num>0){
            if (num>=n[i]){
                str=str+s[i];
                num-=n[i];
            } else{
                i++;
            }
        }
        return str;
    }


    /**
     * @author ChatGPT
     */
    public static boolean isValidRomanNumeral(String roman) {
        // Roman numeral regular expression pattern
        String romanPattern = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

        // Check if the input string matches the Roman numeral pattern
        return roman != null && !roman.isEmpty() && roman.matches(romanPattern);
    }

    /**
     * @author <a href="https://leetcode.com/problems/roman-to-integer/solutions/3651672/best-method-c-java-python-beginner-friendly/">LeetCode</a>
     */
    public static Integer fromRoman(String roman) {
        if (!isValidRomanNumeral(roman)) {
            throw new IllegalArgumentException();
        }

        Map<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int ans = 0;

        for (int i = 0; i < roman.length(); i++) {
            if (i < roman.length() - 1 && map.get(roman.charAt(i)) < map.get(roman.charAt(i + 1))) {
                ans -= map.get(roman.charAt(i));
            } else {
                ans += map.get(roman.charAt(i));
            }
        }

        return ans;
    }
}
