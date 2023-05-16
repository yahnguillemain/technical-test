package com.lombardinternational.technicaltest.algo;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FibonacciNumberCalculatorTest {

    @Test
    @Order(1)
    public void testFibonacciNumber0() {
        assertThat(FibonacciNumberCalculator.getFibonacciNumber(0)).isEqualTo(0);
    }

    @Test
    @Order(2)
    public void testFibonacciNumber1() {
        assertThat(FibonacciNumberCalculator.getFibonacciNumber(1)).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void testFibonacciNumber2() {
        assertThat(FibonacciNumberCalculator.getFibonacciNumber(2)).isEqualTo(1);
    }

    @Test
    @Order(4)
    public void testFibonacciNumber3() {
        assertThat(FibonacciNumberCalculator.getFibonacciNumber(3)).isEqualTo(2);
    }

    @Test
    @Order(5)
    public void testFibonacciNumber5() {
        assertThat(FibonacciNumberCalculator.getFibonacciNumber(5)).isEqualTo(5);
    }

    @Test
    @Order(6)
    public void testFibonacciNumber13() {
        assertThat(FibonacciNumberCalculator.getFibonacciNumber(13)).isEqualTo(233);
    }

    @Test
    @Order(7)
    public void testFibonacciNumber21() {
        assertThat(FibonacciNumberCalculator.getFibonacciNumber(87)).isEqualTo(679891637638612258L);
    }
}

