
package com.littlefox.easypoi.csv;

import cn.afterturn.easypoi.csv.CsvExportUtil;
import cn.afterturn.easypoi.csv.entity.CsvExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.handler.inter.IWriter;
import com.littlefox.easypoi.dto.FinanceData;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Excel测试类
 *
 * @author rockychen
 */
public class CsvTest {

    /**
     * 导出动态表头百万级数据量至csv
     * @param exportPath
     * @param years
     */
    public static void exportCsv(String exportPath, int years) {

        CsvExportParams csvExportParams = new CsvExportParams(CsvExportParams.UTF8);
        csvExportParams.setHeadRows(1);
        csvExportParams.setTitleRows(2);

        //创建表头
        List<ExcelExportEntity> colList = new ArrayList<ExcelExportEntity>();
        ExcelExportEntity colEntity = new ExcelExportEntity("科目", "subject", 50);

        colEntity.setGroupName("科目");
        colEntity.setMergeVertical(true);
        colList.add(colEntity);

        IntStream.range(0, years).forEach(k -> {
            int year = LocalDate.now().getYear() + k;
            ExcelExportEntity yearGroup = new ExcelExportEntity(String.valueOf(year), String.valueOf(year), 60);
            yearGroup.setGroupName(String.valueOf(year));
            yearGroup.setOrderNum(k+1);
            List<ExcelExportEntity> yearColList = new ArrayList<ExcelExportEntity>();
            yearColList.add(new ExcelExportEntity("期初余额", "begin", 30));
            yearColList.add(new ExcelExportEntity("期末余额", "end", 30));
            yearGroup.setList(yearColList);
            colList.add(yearGroup);
        });

        File exportFile = new File(exportPath);
        if (!exportFile.exists()) {
            exportFile.mkdirs();
        }

        List<Map<String, Object>> list = FinanceData.financeDynaColModel(years);

        StopWatch stopWatch = new StopWatch("dynaColFinance.csv");
        stopWatch.start("dynaColFinance.csv");
        try (
                FileOutputStream fos = new FileOutputStream(exportPath + "/dynaColFinance.csv");
        ) {
            IWriter iWriter = CsvExportUtil.exportCsv(csvExportParams, colList, fos);
            for (int i = 0; i < 10000; i++) {
                iWriter.write(list);
            }
            iWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.err.println(stopWatch.prettyPrint());
        System.err.println(stopWatch.getTotalTimeSeconds());
    }

}
