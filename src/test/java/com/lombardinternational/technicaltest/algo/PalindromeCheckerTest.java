package com.lombardinternational.technicaltest.algo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeCheckerTest {


    @ParameterizedTest
    @ValueSource(strings = {
        "senones",
        "malayalam",
        "detartrated",
        "rotavator",
        "tattarrattat"
    })
    public void checkPalindromeMatched(String value){
        assertThat(PalindromeChecker.isPalindrome(value)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "satire veritas",
        "dammit i m mad",
        "olson in oslo",
        "dr awkward"
    })
    public void checkPalindromeMatchedIgnoringSpaces(String value){
        assertThat(PalindromeChecker.isPalindromeIgnoringSpaces(value)).isTrue();
    }


    @ParameterizedTest
    @ValueSource(strings = {
        "thisisnotapalindrome",
        "not a palindrome neither"
    })
    public void checkPalindromeNotMatched(String value){
        assertThat(PalindromeChecker.isPalindromeIgnoringSpaces(value)).isFalse();
    }
}
