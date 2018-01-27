package com.sparrow.redis;

import com.sparrow.cache.CacheClient;
import com.sparrow.cache.CacheDataNotFound;
import com.sparrow.constant.cache.KEY;
import com.sparrow.container.Container;
import com.sparrow.container.impl.SparrowContainerImpl;
import com.sparrow.exception.CacheConnectionException;
import com.sparrow.support.ModuleSupport;

/**
 * @author by harry
 */
public class RedisStringTest {
    private static CacheClient client;

    public static void main(String[] args) throws CacheConnectionException {
        Container container = new SparrowContainerImpl("/redis_config.xml");
        //定义模块，一个业务会存在多个模块
        ModuleSupport OD = new ModuleSupport() {
            @Override
            public String code() {
                return "01";
            }

            @Override
            public String name() {
                return "OD";
            }
        };

        //相同模块下会存在多个业务
        KEY.Business od = new KEY.Business(OD, "POOL");
        container.init();
        client = container.getBean("cacheClient");
        //相同业务下存在多个KEY
        KEY key = new KEY.Builder().business(od).businessId("BJS", "CHI", "HU").build();

        client.string().set(key, "test");
        System.out.println(client.string().get(key));
        client.key().delete(key);
        String value = client.string().get(key, new CacheDataNotFound<String>() {
            @Override
            public String read(KEY key) {
                return "from db";
            }
        });
        System.out.println(value);
        client.string().append(key, "append value");
        System.out.println(client.string().get(key));
        client.string().set(key, 0);
        client.string().increase(key);
        System.out.println(client.string().get(key));

        client.string().increase(key, 10L);
        System.out.println(client.string().get(key));

        client.string().decrease(key, 10L);
        System.out.println(client.string().get(key));

        client.string().decrease(key);
        System.out.println(client.string().get(key));

        client.string().setExpire(key, 10, "11111");
        System.out.println(client.string().get(key));

        client.key().delete(key);
        client.string().setIfNotExist(key, "not exist");
        System.out.println(client.string().get(key));
        client.key().delete(key);
        System.out.println(client.string().get(key));

        client.key().delete(key);
        client.string().set(key, new RedisEntity(1, "ZHANGSAN"));
        System.out.println(client.string().get(key));

        KEY k2 = KEY.parse("OD.POOL:BJS.CHI.HU");
        System.out.println("key:" + k2.key() + ",module:" + k2.getModule() + " business:" + k2.getBusiness());
    }
}
