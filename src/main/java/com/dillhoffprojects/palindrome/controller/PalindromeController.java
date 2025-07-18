package com.dillhoffprojects.palindrome.controller;

import com.dillhoffprojects.palindrome.model.PalindromeContext;
import com.dillhoffprojects.palindrome.model.PalindromeResponse;
import com.dillhoffprojects.palindrome.service.PalindromeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/api/v1/palindrome")
@RestController
public class PalindromeController {

    private final PalindromeService palindromeService;

    public PalindromeController(PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CompletableFuture<ResponseEntity<List<PalindromeResponse>>> getPalindrome(@RequestBody PalindromeContext palContext) {

        log.info("Received PalindromeContext = " + palContext.getPalindromeList().toString());
        List<CompletableFuture<PalindromeResponse>> futures = palContext.getPalindromeList().stream()
                .map(palindromeService::processPalindrome)
                .collect(Collectors.toList());

        return sequence(futures)
                .thenApply(ResponseEntity::ok);
    }

    private CompletableFuture<List<PalindromeResponse>> sequence(List<CompletableFuture<PalindromeResponse>> futures) {
        // Turn List<CF<String>> → CF<Void> that completes when _all_ are done
        CompletableFuture<Void> allDone =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        log.info("STARTING ALL DONE LOGIC!");

        // When they’re all done, join each and collect into a List<String>
        return allDone.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join)      // safe because allDone signaled completion
                        .collect(Collectors.toList())
        );
    }

}
