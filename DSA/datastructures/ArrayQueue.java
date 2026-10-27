package datastructures;

public class ArrayQueue<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int front;
    private int rear;
    private int size;

    public ArrayQueue() {this(DEFAULT_CAPACITY);}
    public ArrayQueue(int capacity) {
        if (capacity <= 0) {throw new IllegalArgumentException("Capacity must be greater than 0.");}
        array = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(T item) {
        if (item == null) {throw new IllegalArgumentException("Item cannot be null.");}
        if (isFull()) {resizeArray();}
        rear = (rear + 1) % array.length; 
        array[rear] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {throw new IllegalStateException("Queue is empty");}
        T item = (T) array[front]; 
        array[front] = null; 
        front = (front + 1) % array.length; 
        size--;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {throw new IllegalStateException("Queue is empty");}
        return (T) array[front];
    }

    public boolean isEmpty() {return size == 0;}
    private boolean isFull() {return size == array.length;}
    public int size() {return size;}

    private void resizeArray() {
        int newCapacity = array.length * 2;
        Object[] newArray = new Object[newCapacity];
        int index = 0;

        for (int i = front; index < size; i++) {newArray[index++] = array[i % array.length];}

        array = newArray;
        front = 0;
        rear = size - 1;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("The queue is empty.");
            return;
        }
        System.out.println("Queue elements:");
        for (int i = 0; i < size; i++) {
            System.out.print(array[(front + i) % array.length] + " ");
        }
        System.out.println();
    }
}
