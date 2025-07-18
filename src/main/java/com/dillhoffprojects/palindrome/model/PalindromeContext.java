package com.dillhoffprojects.palindrome.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PalindromeContext {

    private List<String> palindromeList;
}
