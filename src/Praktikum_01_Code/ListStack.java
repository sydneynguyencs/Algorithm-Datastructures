package Praktikum_01_Code;

import java.util.ArrayList;
import java.util.List;

public class ListStack {
    private List<String> stack;

    public ListStack() {
        stack = new ArrayList<>();
    }

    public void push(String a) {
        stack.add(a);
    }

    public String pop() {
        String val;
        if(!stack.isEmpty()) {
            val = peek();
            stack.remove(getSize()-1);
        } else {
            val = null;
        }
        return val;
    }

    public String peek() {
        return stack.get(stack.size()-1);
    }

    public boolean isEmpty() {
        return stack.size() == 0;
    }

    public boolean isFull() {
        return false;
    }

    public int getSize() {
        return stack.size();
    }

    public List<String> getList() {
        return stack;
    }

    public void removeAll() {
        while(!isEmpty()) {
            pop();
        }
    }
}
