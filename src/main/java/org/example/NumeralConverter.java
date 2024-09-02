package src.main.java.org.example;

public class NumeralConverter {
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

    public static Integer fromRoman(String roman) {
        throw new RuntimeException();
    }
}
