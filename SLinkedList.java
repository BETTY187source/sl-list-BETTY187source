package SLIST;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLinkedList<T> implements Iterable<T>{
    class Node<T>{
        T data;
        Node<T> next;
        Node(T data){
            this.data=data;
            next=null;
        }

    }

    private Node<T> head;
    private Node<T> tail;
    private int size=0;
    public SLinkedList(){
        head=null;
        size=0;

}
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        tail=newNode;
        size++;
    }
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode; // ✅ move tail forward to the new node
        }
        size++;
    }
    
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public T deleteFirst(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        if (head == null) {
            tail = null; // If the list becomes empty, set tail to null
        }
        return data;
    }
    public T deleteLast(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (head.next == null) { // Only one element
            T data = head.data;
            head = null;
            tail = null;
            size--;
            return data;
        }
        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        T data = tail.data;
        current.next = null;
        tail = current;
        size--;
        return data;
    }
    public void display(){
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    public boolean contains(T data){
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public void clear(){
        head = null;
        tail=null;
        size=0;
    }
    public T getFirst(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }
    public T getLast(){
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }
    public void remove(T e) {
        if (head == null) {
            return; // list is empty, nothing to remove
        }
    
        // Case 1: element is at the head
        if (head.data.equals(e)) {
            head = head.next;
            size--;
            if (head == null) {
                tail = null; // list became empty
            }
            return;
        }
    
        // Case 2: element is somewhere else
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(e)) {
                // found the node to remove
                current.next = current.next.next;
                size--;
                if (current.next == null) {
                    tail = current; // removed the last node
                }
                return;
            }
            current = current.next;
        }
    }
    
    public void reverse() {
        if (head == null || head.next == null) {
            return; // nothing to reverse if list is empty or has one element
        }
    
        Node<T> prev = null;
        Node<T> current = head;
        tail = head; // after reversal, the old head becomes the tail
    
        while (current != null) {
            Node<T> nextNode = current.next; // store next node
            current.next = prev;             // reverse the link
            prev = current;                  // move prev forward
            current = nextNode;              // move current forward
        }
    
        head = prev; // new head is the last non-null node
        // TO-DO
    }
    
    public void deleteConsecutiveDuplicates(){
        if (head == null) return;

        Node<T> current = head;
        while (current.next != null) {
            if (current.data.equals(current.next.data)) {
                current.next = current.next.next;
                size--;
            } else {
                current = current.next;
            }
        }

        tail = current;
       //TODO
    }
    //two lists are equal if they have the same 
    // size and the same elements in the same order
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SLinkedList<?> other = (SLinkedList<?>) obj;

        if (this.size != other.size) return false;

        Node<T> current1 = this.head;
        SLinkedList<T>.Node<?> current2 = (SLinkedList<T>.Node<?>)other.head;

        while (current1 != null) {
            if (!current1.data.equals(current2.data)) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        return true;
    }

        //TODO

}