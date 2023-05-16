package com.lombardinternational.technicaltest.algo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class AnagramCheckerTest {
    @ParameterizedTest
    @ValueSource(strings = { "Mary/Army", "Azerty/yzaret","economic/ComicOne" })
    public void checkStringsAreAnagram(String input){
        String[] s = input.split("/");
        assertThat(AnagramChecker.areAnagram(s[0],s[1])).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = { "Marym/Army", "Azerty/yzareta", "zfhzqsdc/qsldh" })
    public void checkStringsAreNotAnagram(String input){
        String[] s = input.split("/");
        assertThat(AnagramChecker.areAnagram(s[0],s[1])).isFalse();
    }
}
