package com.dillhoffprojects.palindrome.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PalindromeResponse {

    private String original;
    private int length;
    private boolean palindrome;
}
