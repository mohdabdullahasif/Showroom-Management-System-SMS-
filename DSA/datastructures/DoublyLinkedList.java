package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {

    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean remove(T data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                removeNode(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private void removeNode(Node node) {
        if (node == head) {
            head = node.next;
            if (head != null) head.prev = null;
        } else if (node == tail) {  
            tail = node.prev;
            if (tail != null) tail.next = null;
        } else { 
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size--;
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public int size() {return size;}
    public T getFirst() {
        if (head == null) throw new NoSuchElementException("List is empty.");
        return head.data;
    }

    public T getLast() {
        if (tail == null) throw new NoSuchElementException("List is empty.");
        return tail.data;
    }

    public T searchById(int id) {
        Node current = head;
        while (current != null) {
            if (current.data.hashCode() == id) {return current.data;}
            current = current.next;
        }
        return null;
    }

    public boolean isEmpty() {return size == 0;}
    public ListIterator getIterator() {return new ListIterator();}

    @Override
    public Iterator<T> iterator() {return new ListIterator();}

    public class ListIterator implements Iterator<T> {
        private Node current;
        private Node lastReturned;

        public ListIterator() {
            this.current = head;
            this.lastReturned = null;
        }

        @Override
        public boolean hasNext() {return current != null;}

        @Override
        public T next() {
            if (!hasNext()) {throw new NoSuchElementException("No more elements in the list.");}
            lastReturned = current;
            T data = current.data;
            current = current.next;
            return data;
        }

        public boolean hasPrevious() {return lastReturned != null && lastReturned.prev != null;}
        public T previous() {
            if (!hasPrevious()) {throw new NoSuchElementException("No previous elements in the list.");}
            current = lastReturned.prev;
            lastReturned = current;
            return current.data;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {throw new IllegalStateException("No element to remove.");}
            Node nextNode = lastReturned.next;
            removeNode(lastReturned);

            if (current == lastReturned) {current = nextNode;}
            lastReturned = null;  
        }
    }
}
