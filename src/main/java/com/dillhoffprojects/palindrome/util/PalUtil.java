package com.dillhoffprojects.palindrome.util;

import java.util.Arrays;
import java.util.List;

public class PalUtil {

    public static List<String> parseIncomingString(String incString) {
        return Arrays.asList(incString.split(","));
    }

    public static boolean checkForPalindrome(String original) {

        boolean isPalindrome = true;
        String reverse = new StringBuilder(original).reverse().toString();

        for (int i = 0; i < reverse.length(); i++) {
            if (reverse.charAt(i) != original.charAt(i)) {
                isPalindrome = false;
                break;
            }
        }

        return isPalindrome;
    }

}
