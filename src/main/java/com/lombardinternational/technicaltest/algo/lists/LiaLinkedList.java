package com.lombardinternational.technicaltest.algo.lists;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LiaLinkedList<T> {

    private T value;

    private LiaLinkedList<T> next;


    public LiaLinkedList(T value) {
        this.value = value;
    }

}
