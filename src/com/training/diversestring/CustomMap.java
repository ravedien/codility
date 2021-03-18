package com.training.customMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class CustomMap implements Map{
    private List<Entry>[] partitions;
    private static final int PARTITION = 16;
    public MapImpl() {
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
public class MapRunner {
    public static void main(String[] args){
        Map map = new MapImpl();
        map.put("string","STRING");
        map.put("int",1);
        map.put("long",1L);
        System.out.println("Map init - done");
        System.out.println(map.get("string"));
        System.out.println(map.get("int"));
        System.out.println(map.get("long"));
    }
}