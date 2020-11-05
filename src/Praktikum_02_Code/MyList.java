package Praktikum_02_Code;

import java.util.AbstractList;

public class MyList extends AbstractList {

    protected Node head;
    protected Node tail;
    protected int size;

    public MyList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean add(Object o) {
        Node newNode = new Node(o);
        //list is empty
        if(isEmpty()) {
            head = newNode;
            head.prev = null;
            tail = newNode;
            tail.next = null;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = null;
        }
        size++;
        return true;
    }

    public boolean remove(Object o) {
        int count = size;
        if(!isEmpty()) {
            Node removing = head;
            while(removing.value!=o) {
                removing = removing.next;
                if(removing==null) {
                    return false;
                }
            }
            if (removing == head && size == 1) {
                head = null;
                tail = null;
                size--;
            } else if (removing == head) {
                removing.next.prev = null;
                head = removing.next;
                size--;
            } else if (removing == tail) {
                removing.prev.next = null;
                tail = removing.prev;
                size--;
            } else {
                removing.prev.next = removing.next;
                removing.next.prev = removing.prev;
                size--;
            }
        }
        return size != count;
    }

    @Override
    public Object get(int index)  {
        if(!isEmpty()) {
            Node current = head;
            if (index >= size) {
                return null;
            }
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public void clear() {
        if(!isEmpty()) {
            Node current = head;
            while(current.next != null) {
                current = current.next;
                remove(current.prev.value);
            }
            remove(current);
            head = null;
            tail = null;
            size = 0;
        }
    }

    public class Node {
        Object value;
        Node prev;
        Node next;

        public Node(Object value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
}
