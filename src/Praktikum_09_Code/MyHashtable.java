package Praktikum_09_Code;

import java.util.*;

/**
 * Initially I wanted to adapt the algorithm by making the table size M = 4*k+3, k is a natural number
 * and M is prime to fulfill the permutation rule.
 * Also I wanted to mark removed entries as deleted(here:flagDelete) such that I can still find an
 * entry even though the one before it was deleted (=null).
 * It did not work as expected so I commented it out and sticked to the lecture notes.
 */

public class MyHashtable<K,V> implements Map<K,V> {
    private K[] keys;
    private V[] values;
    private int capacityK = 2;
    private int capacity = 4*capacityK+3;
    //private final Object flagDelete = Integer.MIN_VALUE;


    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    public MyHashtable(int size) {
        clear();
        capacity = size;
    }

    //  Removes all mappings from this map (optional operation).
    public void clear() {
        capacityK = 2;
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        // to be done
        if(size()>=keys.length) {
            extendTable();
        }
        int pos;
        //if(keys[hash(key)] == null || keys[hash(key)] == flagDelete) {
        if(keys[hash(key)] == null) {
            pos = hash(key);
        }else {
            pos = findPosSquare(key);
        }
        keys[pos] = key;
        values[pos] = value;
        return value;
    }

    private void extendTable() {
        capacityK++;
        K[] keysClone = keys.clone();
        V[] valuesClone = values.clone();
        keys = (K[]) new Object[getCapacity(capacity)];
        values = (V[]) new Object[getCapacity(capacity)];
        for(int i = 0; i < keysClone.length; i++) {
            keys[i] = keysClone[i];
            values[i] = valuesClone[i];
        }
    }

    private int getCapacity(int n) {
        while(!isPrime(n)) {
            capacityK++;
            n = capacity;
        }
        return n;
    }

    private boolean isPrime(int capacity) {
        boolean isPrime = true;
        for(int i = 2; i < capacity; i++) {
            if(capacity%i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    private int findPosLinear(K key) {
        int currentPos = hash(key);
        while(keys[currentPos] != null && !keys[currentPos].equals(key)) {
            currentPos  = (currentPos+1) % keys.length;
        }
        return currentPos;
    }

    private int findPosSquare(K key) {
        int collisionNum = 0;
        int currentPos = hash(key);
        //while(keys[currentPos] != null && !keys[currentPos].equals(key) && keys[currentPos] != flagDelete) {
        while(keys[currentPos] != null && !keys[currentPos].equals(key)) {
            currentPos += 2 * ++collisionNum -1;
            currentPos = currentPos % keys.length;
        }
        return currentPos;
    }

    //  Returns the value to which this map maps the specified key.
    public V get(Object key) {
        V value = null;
        int hash = hash(key);
        if(keys[hash] == key) {
            value = values[hash];
        }
        return value;
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        return size() == 0;
    }

    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        // to be done (Aufgabe 3)
        int hash = hash(key);
        V value = null;
        if(keys[hash] == key) {
            value = values[hash];
            keys[hash] = null;
            values[hash] = null;
            //keys[hash] = (K) flagDelete;
        }
        return value;
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        int size = 0;
        for(K k : keys) {
            //if(k != null && k != flagDeleted)
            if(k != null) {
                size++;
            }
        }
        return size;
    }

    // =======================================================================
    //  Returns a set view of the keys contained in this map.
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    //  Copies all of the mappings from the specified map to this map (optional operation).
    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    //  Returns a collection view of the values contained in this map.
    public Collection values() {
        throw new UnsupportedOperationException();
    }
    //  Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }
    //  Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value)  {
        throw new UnsupportedOperationException();
    }
    //  Returns a set view of the mappings contained in this map.
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }
    //  Compares the specified object with this map for equality.
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }
    //  Returns the hash code value for this map.
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}