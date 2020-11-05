package Praktikum_02_Code;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;

public class SortedListTest {

    List list;

    @Before
    public void setUp() throws Exception {
        list = new SortedList();
    }

    @Test
    public void testAdd() {
        list.clear();
        list.add("A");
        list.add("C");
        list.add("B");
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        Object o = list.get(0);
        assertEquals(o, "A");
        o = list.get(1);
        assertEquals(o, "B");
        o = list.get(2);
        assertEquals(o, "C");
    }

    @Test
    public void testAdd2() {
        list.clear();
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("F");
        list.add("E");


        Object o = list.get(0);

        assertEquals(o, "A");
        o = list.get(1);
        assertEquals(o, "A");
        o = list.get(2);
        assertEquals(o, "B");
        o = list.get(3);
        assertEquals(o, "E");
        o = list.get(4);
        assertEquals(o, "F");
    }

    @Test
    public void testAdd3() {
        list.clear();
        list.add("C");
        list.add("B");
        list.add("A");


        Object o = list.get(0);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        assertEquals(o, "A");
        o = list.get(1);
        assertEquals(o, "B");
        o = list.get(2);
        assertEquals(o, "C");

    }



    @Test
    public void testSize() {
        list.clear();
        assertEquals(0, list.size());
        testAdd2();
        assertEquals(5, list.size());
    }

    @Test
    public void testRemove() {
        list.clear();
        list.add("A");
        list.remove("A");
        assertEquals(0, list.size());
        list.add("A");
        list.remove("B");
        assertEquals(1, list.size());
        list.remove("A");
        assertEquals(0, list.size());
    }

}