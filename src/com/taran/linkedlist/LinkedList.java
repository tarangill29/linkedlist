package com.taran.linkedlist;

import java.util.Stack;

public class LinkedList {
    private Node head;
    private int size;

    public int getSize() {
        return size;
    }

   public boolean isEmpty() {
        return head == null;
   }

   public void addToFront(Node node) {
        node.next = head;
        head = node;
        size++;
   }

   public Node removeFromFront() {
        if (isEmpty()) return null;
        Node removeNode = head;
        head = head.next;
        size--;
        return removeNode;
    }

    public void addToEnd(int data) {
        Node node = new Node(data);
        if(isEmpty()) {
            addToFront(node);
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
        node.next = null;
        size++;
    }

    public Node findNode(int data) {
        Node current = head;
        while (current != null) {
            if(current.data == data) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void printList() {
        System.out.print("HEAD---> ");
        Node current = head;
         while (current != null) {
             System.out.print(current.data + " -> ");
             current = current.next;
         }
        System.out.println("NULL");
    }

    public Node getNthToLast(int k) {
        Node p1 = head;
        Node p2 = head;
        for (int i=1; i<k; i++) {
            if(p2 == null) return null;
            p2 = p2.next;
        }

        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public void reverse() {
        Node current = head;
        Node prev = null;
        Node next = null;

        while(current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public void partitionPivot(int pivot) {
        LinkedList small = new LinkedList();
        LinkedList large = new LinkedList();
        Node smallTail = null;
        Node largeTail = null;
        Node current = head;
        while (current != null) {
            if(current.data < pivot) {
                if(small.head == null) {
                    Node toAdd = new Node(current.data);
                    small.head = toAdd;
                    smallTail = small.head;
                } else {
                    Node toAdd = new Node(current.data);
                    smallTail.next = toAdd;
                    smallTail = toAdd;
                }
            } else {
                if(large.head == null) {
                    Node toAdd = new Node(current.data);
                    large.head =toAdd;
                    largeTail = large.head;
                } else {
                    Node toAdd = new Node(current.data);
                    largeTail.next = toAdd;
                    largeTail = toAdd;
                }
            }
            current = current.next;
        }
        smallTail.next = large.head;
        this.head = small.head;
    }

    private static LinkedList sum(LinkedList list1, LinkedList list2) {
        LinkedList sumList = new LinkedList();
        Node current1 = list1.head;
        Node current2 = list2.head;
        int sum = 0;
        int carry = 0;

        while (current1 != null || current2 != null) {
            sum = (current1==null? 0: current1.data) + (current2==null? 0: current2.data) + carry;

            sumList.addToEnd(sum%10);
            carry = sum/10;

            current1 = current1==null? null : current1.next;
            current2 = current2==null? null : current2.next;
        }
        if(carry != 0) {
            sumList.addToEnd(carry);
        }
        return sumList;
    }
    private static LinkedList sumRecursive(LinkedList list1, LinkedList list2) {
        SumList sList = sumRecursive(list1.head, list2.head);
        if (sList.carry != 0) {
            sList.list.addToFront(new Node(sList.carry));
        }
        return sList.list;
    }

    private static SumList sumRecursive(Node node1, Node node2) {
        if (node1 == null && node2== null) return new SumList();
        SumList sumList = sumRecursive(node1.next, node2.next);
        int sum = node1.data + node2.data + sumList.carry;
        sumList.list.addToFront(new Node(sum%10));
        sumList.carry = sum/10;
        return sumList;
    }
    static class SumList {
        public LinkedList list;
        public int carry;

        public SumList() {
            this.list = new LinkedList();
        }
    }

    public boolean isPalindrome() {
        Node slow = head;
        Node fast = head;
        boolean isOdd = false;
        Stack<Integer> stack = new Stack<>();

        while (fast.next !=null && fast.next.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }
        stack.push(slow.data);
        if(fast.next == null) {
            isOdd =true;
        }

        if(!isOdd) {
            slow = slow.next;
        }


        while (slow != null) {
            if(stack.pop() != slow.data) {
                return false;
            }
            slow = slow.next;
        }

        return true;
    }

    public static Node findIntersection(LinkedList list1, LinkedList list2) {
        int size1 = 0;
        int size2 = 0;
        Node runner1 = list1.head;
        Node runner2 = list2.head;
        while (runner1.next != null) {
            size1++;
            runner1 = runner1.next;
        }
        size1++;
        while (runner2.next != null) {
            size2++;
            runner2 = runner2.next;
        }
        size2++;
        System.out.println(size1+" " + size2);
        if (runner1 != runner2) {
            return null;
        }

        runner1 = list1.head;
        runner2 = list2.head;

        int sizeDiff = size1 > size2 ? size1-size2 : size2-size1;

        if(size1 > size2) {
            for (int i=0; i < sizeDiff; i++) {
                runner1 = runner1.next;
            }
        } else {
            for (int i=0; i < sizeDiff; i++) {
                runner2 = runner2.next;
            }
        }

        while (runner1.next != null && runner2.next != null) {
            if(runner1 == runner2) {
                return runner1;
            }
            runner1 = runner1.next;
            runner2 = runner2.next;
        }
        return null;
    }

    public static Node detectLoop(LinkedList list) {
        Node slow = list.head;
        Node fast = list.head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if(slow == null || fast == null) {
            return null;
        }

        slow = list.head;

        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        Node node = new Node(25);

        LinkedList list = new LinkedList();
        list.printList();
        list.removeFromFront();
        list.addToEnd(12);
        list.printList();
        list.addToFront(node);
        list.addToFront(new Node(345));
        list.addToFront(new Node(56));
        list.addToEnd(112);
        list.printList();
        list.addToFront(new Node(2));
        Node find = list.findNode(88);
        System.out.println(find);
        list.printList();
        Node r = list.removeFromFront();
        System.out.println(r.data);
        list.printList();
        list.addToFront(new Node(88));
        find = list.findNode(88);
        System.out.println(find.data);
        list.printList();
        Node k = list.getNthToLast(3);
        System.out.println(k.data);
        list.partitionPivot(55);
        list.printList();

        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();
        list1.addToFront(new Node(4));
        list1.addToFront(new Node(2));
        list1.addToFront(new Node(5));
        list1.addToFront(new Node(2));
        list1.addToFront(new Node(0));
        Node node1 = new Node(344);
        Node node3 = new Node(33);
        Node node4 = new Node(54);
        Node node5 = new Node(22);
        Node node6 = new Node(93);
        Node node7 = new Node(87);
        Node node8 = new Node(212);
        Node node9 = new Node(45);

       // Node node2 = list1.findNode(4);
       // node2.next = node1;
        LinkedList list3 = new LinkedList();
        list3.head = node1;
        node1.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node5;
        Node loop = detectLoop(list3);
        System.out.println(loop.data);

        list2.addToFront(new Node(9));
        list2.addToFront(new Node(8));
        list2.addToFront(new Node(8));
        list2.addToFront(new Node(8));
        list2.addToFront(new Node(0));
        list2.addToFront(new Node(19));
      //  Node node6 = list2.findNode(9);
       // node6.next = node1;

        //Node intersec = findIntersection(list1, list2);
       // System.out.println(intersec.data);
       /* LinkedList sum = sumRecursive(list1, list2);
        sum.printList();*/
    }


}
