package datastructures;

public class HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private ArrayList<Entry<K, V>> table;
    private int size;

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public HashMap() {
        table = new ArrayList<>(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {table.add(null); }
        size = 0;
    }

    private int hash(K key) {return Math.abs(key.hashCode()) % table.size();}
    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (table.get(index) == null) {table.set(index, newEntry);} 
        else {
            Entry<K, V> current = table.get(index);
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = newEntry;
        }
        size++;
        if ((double) size / table.size() > LOAD_FACTOR) {resize();}
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = table.get(index);

        while (current != null) {
            if (current.key.equals(key)) {return current.value;}
            current = current.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        Entry<K, V> current = table.get(index);
        Entry<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {table.set(index, current.next);} 
                else {prev.next = current.next;}
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public boolean containsKey(K key) {return get(key) != null;}
    private void resize() {
        ArrayList<Entry<K, V>> oldTable = table;
        table = new ArrayList<>(oldTable.size() * 2);
        size = 0;

        for (int i = 0; i < oldTable.size(); i++) {table.add(null);}
        for (int i = 0; i < oldTable.size(); i++) {
            Entry<K, V> entry = oldTable.get(i);
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }
    public int size() {return size;}
}
