package com.lombardinternational.technicaltest.algo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListUniqueFilterTest {


    @Test
    public void removeDuplicatesOK(){
        List<Integer> input = Arrays.asList(1,2,2,3,3,4,5,4,6,5,8,6,9);
        List<Integer> expected=Arrays.asList(1,2,3,4,5,6,8,9);

        List<Integer> result= ListUniqueFilter.unique(input);

        assertThat(result).containsExactly(1,2,3,4,5,6,8,9);
    }


    @Test
    public void removeDuplicatesAndKeepOrderOK(){
        List<Integer> input = Arrays.asList(1,2,1,2,7,3,3,4,5,4,6,5,8,9,6,9);

        List<Integer> result= ListUniqueFilter.unique(input);

        assertThat(result).containsExactly(1,2,7,3,4,5,6,8,9);
    }
}
