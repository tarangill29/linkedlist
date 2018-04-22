package com.taran.doublylinkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DoublyLinkedList {
    public DNode head;
    public DNode tail;
    public int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public DNode addToFront(int data) {
        DNode node = new DNode(data);
        if (isEmpty()) {
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
        }
        head = node;
        size++;
        return node;
    }

    public boolean addToEnd(int data) {
        DNode node = new DNode(data);
        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        size++;
        return true;
    }

   /* public boolean addBefore(int toAdd, int before) {
        DNode node = new DNode(toAdd);
        DNode current = head;
        while (current != null) {
            if (current.data == before) {
                break;
            }
            current = current.next;
        }

        if (current != null) {
            if (current.data == head.data) {
                return addToFront(toAdd);
            }
            node.next = current;
            node.prev = current.prev;
            current.prev.next = node;
            current.prev = node;
            size++;
            return true;
        } else {
            return false;
        }
    }*/

    public DNode remove(int data) {
        if (isEmpty()) {
            return null;
        }
        if(head.data == data) {
            return removeFromFront();
        } else if(tail.data == data) {
            return removeFromEnd();
        } else {
            DNode current = head;
            while (current !=null) {
                if(current.data == data) {
                    break;
                }
                current = current.next;
            }
            if(current != null) {
                current.prev.next = current.next;
                current.next.prev = current.prev;

                size--;
                return current;
            } else {
                return null;
            }
        }
    }

    public DNode removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        DNode removed = head;
        if(head.next == null) {
            tail = null;
            head = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return removed;
    }

    public DNode removeFromEnd() {
        if (isEmpty()) {
            return null;
        }
        DNode removed = tail;
        if(tail.prev == null) {
            tail = null;
            head = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return removed;
    }

    public void printList() {
        System.out.print("HEAD---> ");
        DNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("<---TAIL");
    }

    public void removeDups() {
        Set<Integer> unique = new HashSet<>();
        DNode current  = head;
        while (current != null) {
            if (unique.contains(current.data)) {
                if(current.next == null) {
                    current.prev.next = null;
                    tail = current.prev;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
            } else {
                unique.add(current.data);
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.printList();
        DNode dd = list.addToFront(32);
        HashMap<Integer, DNode>  map = new HashMap<>();
        map.put(4, dd);
        System.out.println(dd==map.get(4));
        list.addToFront(45);
        list.addToEnd(67);
        list.addToFront(122);
        list.addToEnd(98);
    /*    System.out.println(list.addBefore(567,122));
        DNode d1 = list.removeFromFront();
        DNode d2 = list.removeFromEnd();
        DNode d3 = list.remove(45);
        System.out.println(d1.data+" " + d2.data + " "  + d3.data);
        list.addBefore(223, 122);
        list.addBefore(1, 122);
        list.addToEnd(122);
        list.addBefore(223, 32);
*/
        list.printList();

        list.removeDups();


        list.printList();
        System.out.println(list.head.data + " " + list.tail.data + " " + list.getSize());
        DoublyLinkedList l = new DoublyLinkedList();
        System.out.println(l==list);


    }
}
