package Praktikum_02_Code;

public class SortedList extends MyList {

    //insert at its ordered place
    @Override
    public boolean add(Object o) {
        if(isEmpty() || tail.value.toString().charAt(0) <= o.toString().charAt(0)) {
            super.add(o);
        } else {
            Node newNode = new Node(o);
            if(o.toString().charAt(0) <= head.value.toString().charAt(0)) {
                head.prev = newNode;
                newNode.next = head;
                head = newNode;
                size++;
            } else {
                Node current = head;
                while (o.toString().charAt(0) > current.value.toString().charAt(0)) {
                    current = current.next;
                    if(current==null) {
                        return false;
                    }
                }
                if(current==tail) {
                    current.prev.next = newNode;
                    newNode.prev = current.prev;
                    newNode.next = current;
                    current.prev = newNode;
                    size++;
                }
            }
        }
        return true;
    }

}
