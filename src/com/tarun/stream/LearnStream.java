package com.tarun.stream;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class LearnStream {
    public static void calAvg1() {
        LongStream ls = LongStream.of(6L, 8L, 10L);
        Double a = ls.mapToInt(e -> (int)e).
                boxed().
                collect(Collectors.groupingBy(x -> x)).
                keySet().
                stream().
                collect(Collectors.averagingInt(x ->x));
        System.out.println(a);

    }

    private static int intMap(int a) {
        if (a < 0) {
            return -1;
        } else if (a > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    public static Integer intMyMap(int value) {
        // Your logic here
        return value; // Placeholder return
    }

    public static Map<Integer, Long> getMap(int []arr) {
        Map<Integer, Long> map = Arrays.
                stream(arr).mapToObj(LearnStream::intMap).
                collect(Collectors.groupingBy(LearnStream::intMap, Collectors.counting()));
        return map;
    }
    public static void main(String[] args) {
        Person[] persons = {new Person(12, "Tarun", "Bangalore", "India"),
                new Person(13, "Anil", "Bangalore", "India"),
                new Person(15, "Kumar", "Patna", "Hindustan"),
                new Person(13, "Sharma", "Patna", "Hindustan")};

        Map<String, Map<String, List<String>>> map = Arrays.stream(persons).collect(Collectors.groupingBy(e->e.getCountry(), Collectors.groupingBy(e->e.getAddress(), Collectors.mapping(e->e.getName(), Collectors.toList()))));
        System.out.println(map);
        //Map<Integer, String> map = stream.collect(Collectors.toMap(Person::getId, Person::getName, (old, newVal) -> old));
        //Set<Integer> keys = map.keySet();
        //for (int key : keys) {
        //    System.out.println("Key: " + + key + " Value: " + map.get(key));
        //}

        //Map<String, Map<String, List<String>>> groupByCountry = Arrays.stream(persons).collect(Collectors.groupingBy(Person::getCountry,
               // Collectors.groupingBy(Person::getAddress, Collectors.mapping(Person::getName, Collectors.toList()))));
        //System.out.println(groupByCountry);
        Stream<Integer> s = Stream.of(1, 2, 3, 4, 5);
        List<Integer> list = s.filter(e-> e % 2 == 1).map(e -> e*e).collect(Collectors.toList());
        //list.forEach(System.out::println);
        //Stream<Double> gen = Stream.generate(Math::random);
        //gen.forEach(System.out::println);
        //Stream<Integer> intstream = Stream.iterate(1, n -> n + 1);
        //intstream.forEach(System.out::println);
//        Stream<String> str = Stream.of("T", "a", "r", "u", "n");
//        //String ss = str.reduce("=> ", (a, b) -> a + b);
//        String ss2 = str.reduce("** ", String::concat);
//        System.out.println(ss2);
//        Stream<String> s2 = Stream.iterate("", e -> e + "1");
//        System.out.println(s2.limit(2).map(e -> e + "2"));
//        Predicate<String> predicate = e -> e.startsWith("g");
//        var stream1 = Stream.generate(() -> "growl!");
//        var stream2 = Stream.generate(() -> "growl!");
//        var b1 = stream1.noneMatch(predicate);
//        var b2 = stream2.allMatch(predicate);
//        System.out.println(b1 + " " + b2);
        calAvg1();
        int []arr = {-1, -1, -2, -2, -3, -3, -4, 1, 1, 2, 2, 3, 3, 0};

        Map<Integer, Long> map1 = getMap(arr);
        System.out.println(map);

    }
}
