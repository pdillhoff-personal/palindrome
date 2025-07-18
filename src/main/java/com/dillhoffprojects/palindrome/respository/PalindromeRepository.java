package com.dillhoffprojects.palindrome.respository;

import com.dillhoffprojects.palindrome.model.Palindrome;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Component
public class PalindromeRepository {

    private ConcurrentHashMap<String, Palindrome> cache = new ConcurrentHashMap<>();

}
