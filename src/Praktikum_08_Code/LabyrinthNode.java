package Praktikum_08_Code;

public class LabyrinthNode<E> extends Node<E> {

    boolean mark;
    LabyrinthNode<E> prev;

    public void setMark(boolean m) {
        mark = m;
    }

    public boolean getMark() {
        return mark;
    }

    public void setPrev(LabyrinthNode<E> p) {
        prev = p;
    }

    public LabyrinthNode<E> getPrev() {
        return prev;
    }

}
