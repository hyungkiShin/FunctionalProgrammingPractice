package com.example.streampt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Collectors {

    List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);

    @Test
    @DisplayName("Collectors To List")
    void Collectors_To_List() {
        final List<Integer> collect = Stream.of(numbers).flatMap(arr -> arr.stream()).collect(toList());
        System.out.println("collect = " + collect);
    }

}
