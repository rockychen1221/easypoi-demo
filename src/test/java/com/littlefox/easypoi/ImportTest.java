package com.littlefox.easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.littlefox.easypoi.model.FinanceModel;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        File file =  new File(FileUtilTest.getWebRootPath("import/dynaColFinance.xls"));
        StopWatch stopWatch= new StopWatch("import/dynaColFinance.xls");
        stopWatch.start("import/dynaColFinance.xls");
        try {
            List<FinanceModel> list = ExcelImportUtil.importExcel(file,
                    FinanceModel.class, importParams);
            System.out.println(JSON.toJSONString(list));
        }  catch (Exception e) {
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
        File file =  new File(FileUtilTest.getWebRootPath("import/dynaColFinance.xls"));
        StopWatch stopWatch= new StopWatch("import/dynaColFinance.xls");
        stopWatch.start("import/dynaColFinance.xls");
        try {
            List<LinkedHashMap<String, Object>> list = ExcelImportUtil.importExcel(file, Map.class, importParams);
            System.out.println(list.size());
            System.out.println(JSON.toJSONString(list));
        }  catch (Exception e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.err.println(stopWatch.prettyPrint());
        System.err.println(stopWatch.getTotalTimeSeconds());
    }

}
