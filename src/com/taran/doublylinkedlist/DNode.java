package com.taran.doublylinkedlist;

public class DNode {
    public int data;
    public DNode next;
    public DNode prev;

    public DNode(int data) {
        this.data = data;
    }

    public DNode getNext() {
        return next;
    }

    public void setNext(DNode next) {
        this.next = next;
    }

    public DNode getPrev() {
        return prev;
    }

    public void setPrev(DNode prev) {
        this.prev = prev;
    }
}
