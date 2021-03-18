package com.training.customMap;

public class MapRunner {
    public static void main(String[] args) {
        Map map = new CustomMap();
        map.put("string", "STRING");
        map.put("int", 1);
        map.put("long", 1L);
        System.out.println("Map init - done");
        System.out.println(map.get("string"));
        System.out.println(map.get("int"));
        System.out.println(map.get("long"));
    }
}
