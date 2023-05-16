package com.lombardinternational.technicaltest.algo;

import ch.qos.logback.core.testUtil.RandomUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SwapNumbersTest {

    @Test
    public void swapNumbers() {
        final int INITAL_X_VALUE = RandomUtil.getPositiveInt();
        final int INITAL_Y_VALUE = RandomUtil.getPositiveInt();
        SwapNumbers swapNumbers = SwapNumbers.builder()
                                             .x(INITAL_X_VALUE)
                                             .y(INITAL_Y_VALUE)
                                             .build();
        swapNumbers.swap();
        assertThat(swapNumbers).hasFieldOrPropertyWithValue("x", INITAL_Y_VALUE)
                               .hasFieldOrPropertyWithValue("y", INITAL_X_VALUE);
    }
}
