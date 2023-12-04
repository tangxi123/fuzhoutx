package org.cechc.etl.test.demo;


import net.minidev.json.JSONUtil;

import java.io.Serializable;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class RegexTest {
    public static void main(String[] args) {
        Integer[] ints = new Integer[]{1,2,3,4,5};
        Integer[] integers = Arrays.copyOf(ints, 20);
        Arrays.stream(integers).forEach(System.out::println);
        System.out.println("游标为："+indexOf(ints,4));

        String[] strings = new String[]{"1","2","3","a","b"};
        String[] stringss = Arrays.copyOf(strings, 20);
        System.out.println("游标为："+indexOf(stringss,"1"));
        System.out.println("最大值为："+max(strings));


    }

    public static <T> int indexOf(T[] arr, T elm){
        return IntStream.range(0, arr.length)
                .filter(i -> elm.equals(arr[i]))
                .findFirst()
                .orElse(-1);
    }

    public static <T extends Comparable<T>> T max(T[] arr){
        T t = Arrays.stream(arr).max(Comparable::compareTo).get();


        return t;


    }

    public static <T, U> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor,
            Comparator<? super U> keyComparator)
    {
        Objects.requireNonNull(keyExtractor);
        Objects.requireNonNull(keyComparator);
        return (Comparator<T> & Serializable)
                (c1, c2) -> keyComparator.compare
                        (keyExtractor.apply(c1), keyExtractor.apply(c2));
    }



    public   static   List<String>  removeDuplicate(List<String> list)  {
        for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {
            for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {
                if  (list.get(j).equals(list.get(i)))  {
                    list.remove(j);
                }
            }
        }
        return list;
    }
}

