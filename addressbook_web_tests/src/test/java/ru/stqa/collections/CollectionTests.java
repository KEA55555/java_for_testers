package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class CollectionTests {

    @Test
    void arrayTests() { //массив с фиксированным размером
        //var array = new String[]{"a", "b", "c"};
        var array = new String[3];
        Assertions.assertEquals(3, array.length);//Создание массива размером в 3 элеиента (с пустыми строками, все эл-ты=null)
        array[0] = "a";
        Assertions.assertEquals("a", array[0]);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listTests() { //список с переменным размером
        //var list = new ArrayList<String>();
        //var list = List.of("a", "b", "c"); //такой список нельзя изменить
        var list = new ArrayList<>(List.of("a", "b", "c")); //объединили
        Assertions.assertEquals(3, list.size());

//        list.add("a");
//        list.add("b");
//        list.add("c");
//        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));
    }
}