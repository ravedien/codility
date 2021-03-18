package com.training.customMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Can be replace by 2 List or 1 Set 1 List Impl, but choose to divide it to 16, so the iteration will be lesser in big data sets
public class CustomMap implements Map{
    private List<Entry>[] partitions;
    private static final int PARTITION = 16;
    public CustomMap() {
        partitions = new ArrayList[PARTITION];
    }
    @Override
    public void put(String key, Object value) {
        int index = index(key);
        Entry entry = new Entry(key, value);
        List<Entry> entries = partitions[index] == null ? new ArrayList<>() : partitions[index];
        int listIndex = entries.indexOf(entry);
        if(listIndex > 0)
            entries.set(listIndex, entry);
        else
            entries.add(entry);
        partitions[index] = entries;
    }
    private int index(String key) {
        if(key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % PARTITION);
    }
    @Override
    public Object get(String key) {
        List<Entry> entries = partitions[index(key)];
        int listIndex = entries.indexOf(new Entry(key,null));
        if(listIndex >= 0)
            return entries.get(listIndex).getValue();
        else
            return null;
    }
    private static class Entry{
        private String key;
        private Object value;
        public Entry(String key, Object value) {
            this.key = key;
            this.value = value;
        }
        public String getKey() {
            return key;
        }
        public Object getValue() {
            return value;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(key, entry.key);
        }
        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }
    }
}
