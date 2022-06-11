package org.unicat.servicefitness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SteamTest {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(7);
        integers.add(8);
        integers.add(8);
        integers.add(8);
        integers.add(9);
        integers.add(9);
        integers.add(9);
        integers.add(9);
        integers.add(9);
        integers.add(10);
        integers.add(11231231);

        System.out.println(
                integers.stream()
                .filter(s -> s.equals(5))
                .collect(Collectors.toList())
        );
    }
}
