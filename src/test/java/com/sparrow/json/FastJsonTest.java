package com.sparrow.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhanglizhi01@meicai.cn
 * @date: 2019/6/12 10:54
 * @description:
 */
public class FastJsonTest {
    public static void main(String[] args) {
        List<Long> ids=new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        System.out.println(JSON.toJSONString(ids));
        ids=JSON.parseObject(JSON.toJSONString(ids),new TypeReference<List<Long>>(){});
        for(Long id:ids){
            System.out.println(id);
        }
    }
}
