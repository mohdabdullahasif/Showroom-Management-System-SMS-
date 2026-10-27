package datastructures;

public class ArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10; 
    private Object[] elements; 
    private int size; 

    public ArrayList() {this(DEFAULT_CAPACITY);}
    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {throw new IllegalArgumentException("Capacity must be greater than 0.");}
        elements = new Object[initialCapacity];
        size = 0;
    }

    public void add(T element) {
        if (size == elements.length) {resizeArray(); }
        elements[size++] = element;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);}
        if (size == elements.length) {resizeArray();}
        for (int i = size; i > index; i--) {elements[i] = elements[i - 1];}
        elements[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);}
        return (T) elements[index];
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);}
        T removedElement = (T) elements[index];
        for (int i = index; i < size - 1; i++) {elements[i] = elements[i + 1];}
        elements[--size] = null; 
        return removedElement;
    }

    public boolean contains(T element) {return indexOf(element) >= 0;}
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {return i;}
        }
        return -1;
    }

    public int size() {return size;}
    public boolean isEmpty() {return size == 0;}

    public void clear() {
        for (int i = 0; i < size; i++) {elements[i] = null;}
        size = 0;
    }

    public void display() {
        System.out.print("ArrayList: [");
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i]);
            if (i < size - 1) {System.out.print(", ");}
        }
        System.out.println("]");
    }

    private void resizeArray() {
        int newCapacity = elements.length * 2;
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {newArray[i] = elements[i];}
        elements = newArray;
    }

    public Object[] toArray() {
        Object[] newArray = new Object[size];
        for (int i = 0; i < size; i++) {newArray[i] = elements[i];}
        return newArray;
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);}
        elements[index] = element;
    }
    
}
