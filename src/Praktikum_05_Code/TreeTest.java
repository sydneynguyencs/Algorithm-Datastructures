package Praktikum_05_Code;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TreeTest {
    Tree<String> tree;

    @Before
    public void setUp() throws Exception {
        tree = new SortedBinaryTree<String>();
        tree.add("B");
        tree.add("A");
        tree.add("C");
        tree.add("D");
    }

    @Test
    public void testInorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().inorder(v);
        assertEquals("inorder", "ABCD", v.toString());
    }

    @Test
    public void testPreorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().preorder(v);
        assertEquals("preorder", "BACD", v.toString());
    }

    @Test
    public void testPostorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().postorder(v);
        assertEquals("postorder", "ADCB", v.toString());
    }

    @Test
    public void testLevelorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().levelorder(v);
        assertEquals("levelorder", "BACD", v.toString());
    }

    @Test
    public void testInterval() {
        SortedBinaryTree<String> tree = new SortedBinaryTree<>();
        tree.add("5");
        tree.add("3");
        tree.add("7");
        tree.add("2");
        tree.add("4");
        tree.add("6");
        tree.add("8");
        Visitor<String> v = new MyVisitor<String>();

        tree.traversal().interval("3","6",v);
        assertEquals("interval", "4365", v.toString());
    }
}


class MyVisitor<T> implements Visitor<T> {
    StringBuilder output;

    MyVisitor() {
        output = new StringBuilder();
    }

    public void visit(T s) {
        output.append(s);
    }

    public String toString() {
        return output.toString();
    }
}