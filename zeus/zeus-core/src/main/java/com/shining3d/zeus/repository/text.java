package com.shining3d.zeus.repository;

import java.util.*;

/**
 * Created by renyg on 2016/12/27.
 */
    public class text {

        private HashMap map = new HashMap();
        private Set keySet = map.keySet();

        public Object get(String key) {
            return map.get(key);
        }

        public void put(String key, Object value) {
            map.put(key, value);
        }

        public void sort() {
            List list = new ArrayList(map.keySet());

            Collections.sort(list, new Comparator() {
                public int compare(Object a, Object b) {
                    return a.toString().toLowerCase().compareTo(b.toString()
                            .toLowerCase());
                }
            });

            this.keySet = new TreeSet(list);
        }

        public Set keySet() {
            return this.keySet;
        }

//        public static void main(String[] args) {
//            text map = new text();
//            map.put("1", "yi");
//            map.put("8", "ba");
//            map.put("9", "jiu");
//            map.put("7", "qi");
//            map.put("5", "wu");
//            map.put("6", "liu");
//            map.put("4", "si");
//            map.put("3", "san");
//            map.put("2", "er");
//
//            map.sort();
//            for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
//                String key = (String) it.next();
//                System.out.println("key[ " + key + "],   value[ " + map.get(key) + "] ");
//
//            }
//        }
    }
