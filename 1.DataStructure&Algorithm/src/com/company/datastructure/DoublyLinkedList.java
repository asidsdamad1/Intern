package com.company.datastructure;

public class DoublyLinkedList<E> {
    Node head;
    Node tail;
    int size = 0;

    class Node<E> {
        E data;
        Node<E> next, prev;

        public Node(E data) {
            this.data = data;
        }

        public void display() {
            System.out.print(data + " ");
        }
    }

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Adding a node at the front of the list
    public void insertAtFirst(E data) {
        Node newNode = new Node(data);

        if (isEmpty())
            tail = newNode;
        else
            head.prev = newNode;

        newNode.next = head;
        newNode.prev = null;
        head = newNode;

        size++;
    }

    // Adding a node at the front of the list
    public void insertAtLast(E data) {
        Node newNode = new Node(data);

        if (isEmpty())
            head = newNode;
        else {
            tail.next = newNode;
            newNode.prev = tail;
        }

        tail = newNode;
        size++;
    }

    // Given a node as prev_node, insert a new node after the given node
    public void insertAfter(E data, int index) {
        if (index < 0 || index > size) {
            throw new StackOverflowError("Index at " + index + " is not valid");
        }

        Node<E> newNode = new Node<E>(data);
        Node current = head;
        if (index == 0)
            insertAtFirst(data);
        else if (index == size)
            insertAtLast(data);
        else {
            for (int j = 0; j < index && current.next != null; j++) {
                current = current.next;
            }
            newNode.next = current;
            current.prev.next = newNode;
            newNode.prev = current.prev;
            current.prev = newNode;
            size++;
        }
    }

    // delete a node in a Doubly Linked List.
    public void deleteFirstNode() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        if (head.next == null)
            tail = null;
        else
            head.next.prev = null;
        head = head.next;
        size--;
    }

    public void deleteLastNode() {
        if (tail == null) {
            throw new RuntimeException("List is empty");
        }
        if (head.next == null)
            head = null;
        else
            tail.prev.next = null;

        tail = tail.prev;
        size--;
    }


    public void printForward() {
        Node current = head;

        while (current != null) {
            current.display();
            current = current.next;
        }

        System.out.println();
    }

    public void printBackward() {
        Node current = tail;

        while (current != null) {
            current.display();
            current = current.prev;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> ddl = new DoublyLinkedList<>();
        ddl.insertAtFirst("a");
        ddl.insertAtFirst("b");
        ddl.insertAtFirst("d");

        ddl.insertAtLast("c");

        ddl.printForward();
        System.out.println("size: " + ddl.size);

        System.out.println("==============");
        ddl.insertAfter("e ", 3);
        ddl.printForward();
        System.out.println("size: " + ddl.size);

        System.out.println("==============");
        System.out.println("Backward after delete d");
        ddl.deleteFirstNode();
        ddl.printBackward();
        System.out.println("size: " + ddl.size);

        System.out.println("==============");
        ddl.deleteLastNode();
        ddl.printBackward();
        System.out.println(ddl.size);

    }
}
