package com.littlefox.easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.littlefox.easypoi.model.*;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author rockychen
 * @version 1.0
 * @date 2020-06-17 16:00
 */
public class ImportTest {

    /**
     * 导入方式为实体类注解映射
     */
    @Test
    public void importTestExcelByPojo() {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(2);
        File file = new File(FileUtilTest.getWebRootPath("import/dynaColFinance.xls"));
        StopWatch stopWatch = new StopWatch("import/dynaColFinance.xls");
        stopWatch.start("import/dynaColFinance.xls");
        try {
            List<FinanceModel> list = ExcelImportUtil.importExcel(file,
                    FinanceModel.class, importParams);
            System.out.println(JSON.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.err.println(stopWatch.prettyPrint());
        System.err.println(stopWatch.getTotalTimeSeconds());
    }

    /**
     * 导入方式为自由map
     */
    @Test
    public void importTestExcelByMap() {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(2);
        File file = new File(FileUtilTest.getWebRootPath("import/dynaColFinance.xls"));
        StopWatch stopWatch = new StopWatch("import/dynaColFinance.xls");
        stopWatch.start("import/dynaColFinance.xls");
        try {
            List<LinkedHashMap<String, Object>> list = ExcelImportUtil.importExcel(file, Map.class, importParams);
            System.out.println(list.size());
            System.out.println(JSON.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.err.println(stopWatch.prettyPrint());
        System.err.println(stopWatch.getTotalTimeSeconds());
    }

    /**
     * 导入方式为转成实体（存在相同列名的情况）多sheet
     */
    @Test
    public void importTestExcelByFinanceBean() {
        List listAll = new ArrayList();
        List list = Collections.emptyList();
        for (int i = 0; i < 3; i++) {
            ImportParams importParams = new ImportParams();
            importParams.setTitleRows(3);
            importParams.setHeadRows(1);
            importParams.setStartSheetIndex(i);
            File file = new File(FileUtilTest.getWebRootPath("import/财务报表导入模版 (一般企业版)测试.xlsx"));
            try {
                list = ExcelImportUtil.importExcel(file, i == 0 ? AssetsLiabilities.class : i == 1 ? Profit.class : CashFlow.class, importParams);
                listAll.add(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.err.println(JSON.toJSONString(listAll));
    }

    /**
     * 导入方式为转成Map
     * 存在相同列名的情况会导致map数据丢失，原因key重复
     * 多sheet
     */
    @Test
    public void importTestExcelByFinanceMap() {
        List listAll = new ArrayList();
        List list = Collections.emptyList();
        for (int i = 0; i < 3; i++) {
            ImportParams importParams = new ImportParams();
            importParams.setTitleRows(3);
            importParams.setHeadRows(1);
            importParams.setStartSheetIndex(i);
            importParams.setKeyIndex(null);//设置读取空值

            File file = new File(FileUtilTest.getWebRootPath("import/财务报表导入模版 (一般企业版)测试.xlsx"));
            try {
                list = ExcelImportUtil.importExcel(file, Map.class, importParams);
                listAll.add(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.err.println(JSON.toJSONString(listAll));
    }


    /**
     * 导入方式为转成Map
     * 存在相同列名的情况会导致map数据丢失，原因key重复
     * 多sheet
     */
    @Test
    public void importTestExcelByFinanceKaoQinMap() {
        List<Map<String, Object>> list = Collections.emptyList();

        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(4);
        importParams.setHeadRows(2);
        importParams.setStartRows(1);
        importParams.setKeyIndex(null);//设置读取空值

        File file = new File(FileUtilTest.getWebRootPath("import/2020-8月-少量数据.xls"));
        try {
            list = ExcelImportUtil.importExcel(file, Map.class, importParams);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.err.println(JSON.toJSONString(list));
    }


    /**
     * 导入方式为实体类注解映射
     */
    @Test
    public void importTestExcelByKaoQinPojo() {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(4);
        importParams.setHeadRows(0);
        //importParams.setKeyIndex(null);//设置读取空值
        File file = new File(FileUtilTest.getWebRootPath("import/2020-8月-少量数据.xls"));
        try {
            List<KaoQin> list = ExcelImportUtil.importExcel(file,
                    KaoQin.class, importParams);

            List<MyKaoQin> lists = toData(list);
            //System.out.println(JSON.toJSONString(list));
            //System.out.println(JSON.toJSONString(lists));

            //实现行转列的算法
            List<Details> convertedTable = convert(lists.get(8).getList());
            //打印转换后的集合，查看结果

            //print(convertedTable);
            XirrDetails details = convertedTable.stream().collect(XirrDetails.collector());
            System.out.println(details.getStart());
            System.out.println(details.getEnd());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List toData(List<KaoQin> list) {
        List<MyKaoQin> lists = Lists.newArrayList();
        MyKaoQin myKaoQin = null;
        for (int i = 0; i < list.size(); i++) {
            KaoQin kaoQin = list.get(i);
            if (kaoQin != null) {
                if (kaoQin.getD1() != null) {
                    if (kaoQin.getD1().indexOf("工号") > -1) {
                        // myKaoQin.
                        myKaoQin = new MyKaoQin();
                        myKaoQin.setNumber(kaoQin.getD3());
                        myKaoQin.setName(kaoQin.getD11());
                        myKaoQin.setDept(kaoQin.getD18());
                        //i++;
                        lists.add(myKaoQin);
                        continue;
                    }
                }
                myKaoQin.add(kaoQin);
            }
        }
        return lists;
    }

    private static List<Details> convert(List<KaoQin> StudentGrandList)
            throws IllegalAccessException {
        //取得StudentGrand的属性，当然你也可以用list = {"id", "name", ...}
        Field[] declaredFields = KaoQin.class.getDeclaredFields();
        List<Details> details = new ArrayList<Details>();

        //多少个属性表示多少行，遍历行
        for (Field field : declaredFields) {
            field.setAccessible(true);
            //list<T>多少个StudentGrand实体类表示有多少列，遍历列
            String day = field.get(StudentGrandList.get(0)).toString();
            for (int i = 0, size = StudentGrandList.size(); i < size; i++) {
                //所以新table的第一列要设置为字段名
                if (i != 0) {
                    KaoQin StudentGrand = StudentGrandList.get(i);
                    String val = String.valueOf(field.get(StudentGrand));//grand为int会报错
                    if (StringUtils.equals("null", val) && i - 1 == 0) {
                        details.add(new Details("2020-08-" + (day.length() < 2 ? "0" + day : day) , "00:00"));
                        break;
                    }
                    String[] arr = val.split("\n");
                    Arrays.stream(arr).forEach(a -> {
                        if (StringUtils.isNotBlank(a) && !StringUtils.equals("null", a)) {
                            details.add(new Details("2020-08-" + (day.length() < 2 ? "0" + day : day) ,  a));
                        }
                    });
                }
            }
        }
        return details;
    }

    //打印查看结果
    private static void print(List<Details> convertedTable) {
        //String json = JSONArray.formObject(convertedTable).toString();
        convertedTable.forEach(d -> {
            System.out.println(d.getDate()+"=="+d.getTime());
        });
    }

}
