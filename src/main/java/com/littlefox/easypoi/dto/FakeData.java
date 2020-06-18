package com.littlefox.easypoi.dto;

import cn.afterturn.easypoi.entity.ImageEntity;

import java.time.LocalDate;
import java.util.*;

/**
 * 制作假数据
 *
 * @author Delei
 */
public class FakeData {

    private static String[] imgs = new String[]{"ali.png", "baidu.png", "lemur.png", "one.png"};

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
    public static List<Map<String, Object>> fakeListModel(int fakeDataSize) {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        //List数据
        for (int i = 0; i < fakeDataSize; i++) {
            HashMap<String, Object> tempMap = new HashMap<String, Object>(7);
            tempMap.put("id", (121 + i * 10) + "");
            tempMap.put("user", "张三" + i);
            tempMap.put("year", LocalDate.now());
            tempMap.put("income", "1000" + i);
            tempMap.put("lastYearIncome", "2000" + i);
            tempMap.put("cost", "3000" + i);
            tempMap.put("lastYearCost", "4000" + i);

            int index = new Random().nextInt(4);
            ImageEntity image = new ImageEntity();
            image.setHeight(200);
            image.setWidth(500);
            image.setUrl("imgs/"+imgs[index]);
            image.setType(ImageEntity.URL);
            tempMap.put("imgs", image);
            data.add(tempMap);
        }
        return data;
    }
}
