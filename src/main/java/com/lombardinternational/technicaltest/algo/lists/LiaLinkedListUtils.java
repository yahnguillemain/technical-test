package com.lombardinternational.technicaltest.algo.lists;

public class LiaLinkedListUtils {

    public static <T> LiaLinkedList<T> buildFromArray(T[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        LiaLinkedList<T> first = new LiaLinkedList<>(values[0]);
        LiaLinkedList<T> current = first;
        for (int i = 1; i < values.length; ++i) {
            LiaLinkedList<T> nextNode = new LiaLinkedList<>(values[i]);
            current.setNext(nextNode);
            current = nextNode;
        }
        return first;
    }

    public static <T> LiaLinkedList<T> reverse(LiaLinkedList<T> list) {
        //TODO
        return null;
    }

}
