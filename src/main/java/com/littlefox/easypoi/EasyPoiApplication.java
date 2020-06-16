package com.littlefox.easypoi;


import com.littlefox.easypoi.excel.ExcelTest;
import com.littlefox.easypoi.excel.FinanceTest;
import com.littlefox.easypoi.word.WordTest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.Instant;

@SpringBootApplication
public class EasyPoiApplication implements CommandLineRunner {

    @Value("${p:/Users/datadriver/easypoi}")
    private String exportPath;//导出根目录
    @Value("${d:100}")
    private int fakseDataSize;//假数据大小
    @Value("${type:10}")
    private String type;//导出类型

    private final static String E_XLS = ".xls";
    private final static String E_XLSX = ".xlsx";
    private final static String W_DOCX = ".docx";

    public static void main(String[] args) {
        SpringApplication.run(EasyPoiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Instant start = Instant.now();
        printlnParam();
        String templateFile;
        if (StringUtils.isBlank(type)) {
            System.err.println("export type is null");
        } else {
            switch (type) {
                case "10":
                    templateFile = "excel/singlesheet.xls";
                    ExcelTest.exportSingleSheet(exportPath, templateFile, fakseDataSize, E_XLS);
                    break;
                case "11":
                    templateFile = "excel/singlesheet.xlsx";
                    ExcelTest.exportSingleSheet(exportPath, templateFile, fakseDataSize, E_XLSX);
                    break;
                case "12":
                    templateFile = "excel/multisheet.xls";
                    ExcelTest.exportMultiSheet(exportPath, templateFile, fakseDataSize, E_XLS);
                    break;
                case "13":
                    templateFile = "excel/multisheet.xlsx";
                    ExcelTest.exportMultiSheet(exportPath, templateFile, fakseDataSize, E_XLSX);
                    break;
                case "14":
                    templateFile = "excel/horizontalmultisheet.xlsx";
                    ExcelTest.exportMultiSheet(exportPath, templateFile, fakseDataSize, E_XLSX);
                    break;
                case "20":
                    templateFile = "word/template.docx";
                    WordTest.export(exportPath, templateFile, fakseDataSize, W_DOCX);
                    break;
                case "30":
                    templateFile = "excel/finance.xlsx";
                    FinanceTest.exportFinanceByTemplate(exportPath, templateFile, E_XLSX,3);
                    break;
                case "40":
                    FinanceTest.exportDynaColFinance(3);
                    break;
                default:
                    break;
            }
        }
        Instant end = Instant.now();
        System.err.println("共耗时: " + Duration.between(start, end).toMillis() / 1000 + " 秒");
    }

    private void printlnParam() {
        System.err.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.err.println("==Parameter");
        System.err.println("exportPath=" + exportPath);
        System.err.println("type=" + type);
        System.err.println("fakseDataSize=" + fakseDataSize);
        System.err.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
