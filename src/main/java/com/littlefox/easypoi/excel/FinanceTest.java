package com.littlefox.easypoi.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.alibaba.fastjson.JSON;
import com.littlefox.easypoi.dto.FinanceData;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author rockychen
 * @version 1.0
 * @date 2020-06-15 10:40
 */
public class FinanceTest {

    public final static String TEMPLATE_EXPORT_NAME = "_export_result";

    /**
     * 根据指定模板导出
     *
     * @param exportPath     导出目录
     * @param templateFile   模板文件
     * @param exportFileType 后缀
     * @param years          年度数据
     */
    public static void exportFinanceByTemplate(String exportPath, String templateFile, String exportFileType, int years) {
        if (years < 1) {
            throw new IllegalArgumentException("非法参数");
        }

        Map<String, Object> map = FinanceData.financeModel(years);

        System.err.println(JSON.toJSONString(map));

        TemplateExportParams templateExportParams = new TemplateExportParams("/" + templateFile, true);
        //横向遍历
        templateExportParams.setColForEach(true);
        String tempFile = templateFile.substring(0, templateFile.lastIndexOf("."));
        String exportName = tempFile + TEMPLATE_EXPORT_NAME + exportFileType;
        File exportFile = new File(exportPath + "/" + exportName.split("/")[0]);
        if (!exportFile.exists()) {
            exportFile.mkdirs();
        }
        StopWatch stopWatch = new StopWatch(templateFile);
        stopWatch.start(templateFile);
        try (
                FileOutputStream fos = new FileOutputStream(exportPath + "/" + exportName);
        ) {
            Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, map);
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.err.println(stopWatch.prettyPrint());
        System.err.println(stopWatch.getTotalTimeSeconds());
    }

    /**
     * 无模板导出，自动生成列和表头
     *
     * @param exportPath 导出目录
     * @param years      多少期数据
     */
    public static void exportDynaColFinance(String exportPath, int years) {
        if (years < 1) {
            throw new IllegalArgumentException("非法参数");
        }
        //创建表头
        List<ExcelExportEntity> colList = new ArrayList<ExcelExportEntity>();
        ExcelExportEntity colEntity = new ExcelExportEntity("科目", "subject", 50);
        colEntity.setNeedMerge(true);
        colList.add(colEntity);

        IntStream.range(0, years).forEach(k -> {
            int year = LocalDate.now().getYear() + k;
            ExcelExportEntity yearGroup = new ExcelExportEntity(String.valueOf(year), String.valueOf(year), 60);
            List<ExcelExportEntity> yearColList = new ArrayList<ExcelExportEntity>();
            yearColList.add(new ExcelExportEntity("期初余额", "begin", 30));
            yearColList.add(new ExcelExportEntity("期末余额", "end", 30));
            yearGroup.setList(yearColList);
            colList.add(yearGroup);
        });

        // 获取数据
        List<Map<String, Object>> list = FinanceData.financeDynaColModel(years);
        //System.err.println(JSON.toJSONString(list));

        File exportFile = new File(exportPath);
        if (!exportFile.exists()) {
            exportFile.mkdirs();
        }

        StopWatch stopWatch = new StopWatch("dynaColFinance.xls");
        stopWatch.start("dynaColFinance.xls");
        try (
                FileOutputStream fos = new FileOutputStream(exportPath + "/dynaColFinance.xls");
        ) {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("资产负债表", "财务"), colList, list);
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.err.println(stopWatch.prettyPrint());
        System.err.println(stopWatch.getTotalTimeSeconds());
    }
}
