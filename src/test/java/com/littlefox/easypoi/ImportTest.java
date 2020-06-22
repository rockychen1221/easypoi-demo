package com.littlefox.easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.littlefox.easypoi.model.AssetsLiabilities;
import com.littlefox.easypoi.model.CashFlow;
import com.littlefox.easypoi.model.FinanceModel;
import com.littlefox.easypoi.model.Profit;
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
    public void importTestExcelByFinance() {
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

}
