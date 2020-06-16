package com.littlefox.easypoi.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.littlefox.easypoi.dto.Finance;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
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
     * @param exportPath
     * @param templateFile
     * @param exportFileType
     */
    public static void exportFinanceByTemplate(String exportPath, String templateFile, String exportFileType, int years) {
        if (years < 1) {
            throw new IllegalArgumentException("非法参数");
        }
        TemplateExportParams templateExportParams = new TemplateExportParams(  "/" + templateFile, true);
        //横向遍历
        templateExportParams.setColForEach(true);
        templateFile = templateFile.substring(0, templateFile.lastIndexOf("."));

        String exportName = templateFile + TEMPLATE_EXPORT_NAME + exportFileType;
        File exportFile = new File(exportPath + "/" + exportName);
        try (
                FileOutputStream fos = new FileOutputStream(exportFile);
        ) {
            Map<String, Object> map = Finance.financeModel(years);
            //System.out.println(JSON.toJSONString(map));
            Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, map);
            //ExcelExportUtil.exportBigExcel(templateExportParams, listMap)
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 无模板导出，自动生成列和表头
     *
     * @param years 多少期数据
     */
    public static void exportDynaColFinance(int years) {
        try {
            if (years < 1) {
                throw new IllegalArgumentException("非法参数");
            }
            //创建表头
            List<ExcelExportEntity> colList = new ArrayList<ExcelExportEntity>();
            ExcelExportEntity colEntity = new ExcelExportEntity("科目", "subject",50);
            colEntity.setNeedMerge(true);
            colList.add(colEntity);

            IntStream.range(0, years).forEach(k -> {
                int year = LocalDate.now().getYear() + k;
                ExcelExportEntity yearGroup = new ExcelExportEntity(String.valueOf(year), String.valueOf(year),60);
                List<ExcelExportEntity> yearColList = new ArrayList<ExcelExportEntity>();
                yearColList.add(new ExcelExportEntity("期初余额", "begin",30));
                yearColList.add(new ExcelExportEntity("期末余额", "end",30));
                yearGroup.setList(yearColList);
                colList.add(yearGroup);
            });

            // 获取数据
            List<Map<String, Object>> list = Finance.financeDynaColModel(years);
            //System.out.println(JSON.toJSONString(list));
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("资产负债表", "财务"), colList,
                    list);
            FileOutputStream fos = new FileOutputStream("/Users/datadriver/easypoi/tt.xls");
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
