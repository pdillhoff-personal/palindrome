package com.dillhoffprojects.palindrome.service;

import com.dillhoffprojects.palindrome.model.PalMapper;
import com.dillhoffprojects.palindrome.model.Palindrome;
import com.dillhoffprojects.palindrome.model.PalindromeResponse;
import com.dillhoffprojects.palindrome.respository.PalindromeRepository;
import com.dillhoffprojects.palindrome.util.PalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class PalindromeService {

    private PalMapper palMapper;
    private PalindromeRepository cache;

    public PalindromeService(PalMapper palMapper, PalindromeRepository cache) {
        this.palMapper = palMapper;
        this.cache = cache;
    }

    @Async
    public CompletableFuture<PalindromeResponse> processPalindrome(String curPalindrome) {

        Palindrome palindrome = palMapper.toEntity(curPalindrome);

        palindrome.setLength(curPalindrome.length());
        palindrome.setPalindrome(PalUtil.checkForPalindrome(curPalindrome));
        cache.getCache().put(curPalindrome, palindrome);

        log.info("Palindrome processed : Original = {}, Length = {}, Palindrome = {}", palindrome.getOriginal(), palindrome.getLength(), palindrome.isPalindrome());

        return CompletableFuture.completedFuture(palMapper.toRespone(palindrome));

    }
}
