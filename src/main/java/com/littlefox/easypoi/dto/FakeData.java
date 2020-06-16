package com.littlefox.easypoi.dto;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 制作假数据
 *
 * @author Delei
 */
public class FakeData {

    /**
     * 单个对象
     *
     * @return
     */
    public static Map<String, Object> fakeModel() {
        HashMap<String, Object> map = new HashMap<String, Object>(4);
        //单个对象
        map.put("id", "1231313");
        map.put("name", "A某某");
        map.put("age", "34");
        map.put("phone", "18787674333");
        return map;
    }

    /**
     * 指定数据量List
     *
     * @param fakeDataSize
     * @return
     */
    public static List<Map<String, String>> fakeListModel(int fakeDataSize) {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        //List数据
        HashMap<String, String> tempMap;
        for (int i = 0; i < fakeDataSize; i++) {
            tempMap = new HashMap<String, String>(6);
            tempMap.put("id", (121 + i * 10) + "");
            tempMap.put("user",  "张三"+i);
            tempMap.put("year", (1200 + i * 10) + "");
            tempMap.put("income", "1000" + i);
            tempMap.put("lastYearIncome", "2000" + i);
            tempMap.put("cost", "3000" + i);
            tempMap.put("lastYearCost", "4000" + i);
            data.add(tempMap);
        }
        return data;
    }
}
