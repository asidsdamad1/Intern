package com.company.datastructure;

public class HashTable<E, T> {
    private final HashEntry<E, T>[] data; // LinkedList
    private int INITIAL_SIZE = 16;  // NumBucket
    private int size;

    static class HashEntry<E, T> {
        E key;
        T value;
        HashEntry next;

        HashEntry(E key, T value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public void display() {
            System.out.print(key + ": " + value + ", ");
        }
    }

    HashTable() {
        size = 0;
        data = new HashEntry[INITIAL_SIZE];
    }

    HashTable(int capacity) {
        size = 0;
        INITIAL_SIZE = capacity;
        data = new HashEntry[INITIAL_SIZE];
    }

    public int getSize() {
        return size;
    }

    public void put(E key, T value) {

        // Get the index
        int index = getIndex(key);
        // Create the linked list entry
        HashEntry<E, T> entry = new HashEntry<>(key, value);

        // If no entry there - add it
        if (data[index] == null) {
            data[index] = entry;
        }
        // Else handle by adding to end of linked list
        else {
            HashEntry entries = data[index];
            // Walk to the end...
            while (entries.next != null) {
                entries = entries.next;
            }
            // Add our new entry there
            entries.next = entry;

        }
        size++;
    }

    public T remove(E key) {
        // find index for given key
        int index = getIndex(key);
        int hashcode = key.hashCode();

        // get head of chain
        HashEntry<E, T> head = data[index];

        // Search for key in its chain
        HashEntry<E, T> prev = null;
        while (head != null) {
            if (head.key.equals(key) && hashcode == head.key.hashCode())
                break;
            prev = head;
            head = head.next;
        }
        if (head == null)
            return null;

        size--;

        // remove key
        if (prev != null)
            prev.next = head.next;
        else
            data[index] = head.next;

        return head.value;
    }

    public T get(E key) {

        // Get the index
        int index = getIndex(key);

        // Get the current list of entries
        HashEntry<E, T> entries = data[index];

        // if we have existing entries against this key...
        if (entries != null) {
            while (!entries.key.equals(key) && entries.next != null) {
                entries = entries.next;
            }
            return entries.value;
        }

        return null;
    }

    private int getIndex(E key) {
        // Get the hash code
        int hashCode = key.hashCode();
        // Convert to index
        return (hashCode & 0x7fffffff) % INITIAL_SIZE;
    }

    public void display(HashTable<E, T> hashTable) {
        System.out.print("HashTable:  { ");
        for (HashEntry a :
                hashTable.data) {

            while (a != null) {
                a.display();
                a = a.next;
            }

        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("first", 333);
        hashTable.put("second", 12);
        hashTable.put("third", 44);

        hashTable.display(hashTable);

        System.out.println("size: " + hashTable.getSize());
        System.out.println("remove key first: " + hashTable.remove("first"));
//        System.out.println("remove key first again: " + hashTable.remove("first"));

        hashTable.display(hashTable);

        System.out.println("size: " + hashTable.getSize());

    }
}
