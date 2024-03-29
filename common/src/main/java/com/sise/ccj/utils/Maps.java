package com.sise.ccj.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Maps {

    public static JSONObject of(String key1, Object value1) {
        JSONObject map = new JSONObject(1);
        map.put(key1, value1);
        return map;
    }

    public static JSONObject of(String key1, Object value1, String key2, Object value2) {
        JSONObject map = new JSONObject(2);
        map.put(key1, value1);
        map.put(key2, value2);
        return map;
    }

    public static JSONObject of(Object... objects) {
        JSONObject map = new JSONObject(objects.length);
        String key = null;
        Object value;
        for (int i = 1; i <= objects.length; i++){
            if (i % 2 == 1) {
                key = objects[i-1]+"";
            }else {
                value = objects[i-1];
                map.put(key, value);
            }
        }
        return map;
    }

    public static <K, V extends Comparable<? super V>> List<V> sortByValue(Map<K, V> map, int trim) {
        List<V> list = new ArrayList<>(map.size());
        Stream<Entry<K, V>> st = map.entrySet().stream();
        st.sorted(Comparator.comparing(Entry::getValue)).forEachOrdered(e -> list.add(e.getValue()));
        return list.size() < trim ? list : list.subList(0, trim);
    }
}
