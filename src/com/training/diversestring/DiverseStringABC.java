package com.training.diversestring;

import java.util.*;

public class DiverseStringABC {

    public static void main(String[] args) {
        System.out.println(solution(6,1,1));
    }


    public static String solution(int a, int b, int c){
        String str = "";

        List<Entry> entryList = new ArrayList<>();
        entryList.add(new Entry("a",a));
        entryList.add(new Entry("b",b));
        entryList.add(new Entry("c",c));

        while(true){
            Collections.sort(entryList,Comparator.comparing(Entry::getValue));
            Collections.reverse(entryList);

            Entry entry = entryList.get(0);
            if(entry.getValue() == 0)
                break;
            String lastTwoLetters = str.length() > 2 ? str.substring(str.length() - 2) : str;
            if((entry.getKey()+entry.getKey()).equals(lastTwoLetters)){
                entry = entryList.get(1);
                if(entry.getValue() == 0)
                    break;
            }
            entry.value--;
            str+=entry.getKey();
        }
        return str;
    }

    public static class Entry{
        private String key;
        private int value;

        public Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

}
