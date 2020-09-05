package com.littlefox.easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.littlefox.easypoi.model.*;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.File;
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
        importParams.setKeyIndex(null);//设置读取空值
        File file = new File(FileUtilTest.getWebRootPath("import/2020-8月-少量数据.xls"));
        try {
            List<KaoQin> list = ExcelImportUtil.importExcel(file,
                    KaoQin.class, importParams);
            List<MyKaoQin> lists = toData(list);
            System.out.println(JSON.toJSONString(list));
            System.out.println(JSON.toJSONString(lists));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private List toData(List<KaoQin> list) {
        List<MyKaoQin> lists= Lists.newArrayList();
        MyKaoQin myKaoQin = null;
        for (int i = 0; i < list.size(); i++) {
            KaoQin kaoQin = list.get(i);
            if (kaoQin != null) {
                if (kaoQin.getD1() != null) {
                    if (kaoQin.getD1().indexOf("工号") > -1) {
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


}
