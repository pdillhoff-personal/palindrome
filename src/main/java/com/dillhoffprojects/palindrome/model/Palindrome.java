package com.dillhoffprojects.palindrome.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Palindrome {
    private String original;
    private int length;
    private boolean palindrome;
}
