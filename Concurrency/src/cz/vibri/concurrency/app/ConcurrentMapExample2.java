package cz.vibri.concurrency.app;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapExample2 {
    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

        //slipping condition
        synchronized (map) {
            if(!map.containsKey("key")) {
                map.put("key", "value");
            }

        } // not efficient solution

        // solution
        map.putIfAbsent("key", "value");


    }
}
