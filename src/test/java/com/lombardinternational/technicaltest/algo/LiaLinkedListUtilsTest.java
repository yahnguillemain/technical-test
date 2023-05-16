package com.lombardinternational.technicaltest.algo;

import com.lombardinternational.technicaltest.algo.lists.LiaLinkedList;
import com.lombardinternational.technicaltest.algo.lists.LiaLinkedListUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LiaLinkedListUtilsTest {

    @Test
    public void checkBuild() {
        Integer[] items = new Integer[] { 1, 2, 3 };
        LiaLinkedList<Integer> result = LiaLinkedListUtils.buildFromArray(items);
        LiaLinkedList<Integer> current = result;
        for (int i = 0; i < items.length; ++i) {
            assertThat(current.getValue()).isEqualTo(items[i]);
            current = current.getNext();
        }

    }

    @Test
    public void reverseList() {
        Integer[] items = new Integer[] { 1, 2, 3, 4, 7, 9, 10 };
        LiaLinkedList<Integer> initial = LiaLinkedListUtils.buildFromArray(items);

        LiaLinkedList<Integer> result = LiaLinkedListUtils.reverse(initial);
        LiaLinkedList<Integer> current = result;
        for (int i = items.length - 1; i > 0; --i) {
            assertThat(current.getValue()).isEqualTo(items[i]);
            current = current.getNext();
        }
    }

}
