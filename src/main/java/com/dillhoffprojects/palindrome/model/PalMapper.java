package com.dillhoffprojects.palindrome.model;

import org.springframework.stereotype.Component;

@Component
public class PalMapper {

    public Palindrome toEntity(String original) {

        return new Palindrome(original, 0, false);
    }

    public PalindromeResponse toRespone(Palindrome palindrome) {
        return new PalindromeResponse(palindrome.getOriginal(), palindrome.getLength(), palindrome.isPalindrome());
    }

}
