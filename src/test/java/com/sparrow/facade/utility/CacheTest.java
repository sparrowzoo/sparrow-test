package com.sparrow.facade.utility;

import com.sparrow.core.cache.Cache;

import java.util.HashMap;
import java.util.Map;

public class CacheTest {
    public static void main(String[] args) {
        Cache cache=Cache.getInstance();
        Map<String,Object> map=new HashMap<>();
        map.put("k1","v1");
        cache.put("K1",map);
        cache.put("K1",map,1);

        System.out.println(cache.getExpirable("K1").getData()+"");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache.getExpirable("K1")+"");
        System.out.println(cache.get("K1")+"");
    }
}
