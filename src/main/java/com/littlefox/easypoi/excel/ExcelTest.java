package com.littlefox.easypoi.excel;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.littlefox.easypoi.dto.FakeData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Excel测试类
 *
 * @author delei
 */
public class ExcelTest {
    public final static String TEMPLATE_EXPORT_NAME = "_export_result";

    /**
     * 导出单个Sheet页
     *
     * @param exportPath
     * @param templateFile
     * @param fakeDataSize
     */
    public static void exportSingleSheet(String exportPath, String templateFile, int fakeDataSize, String exportFileType) {
        if (StringUtils.isBlank(exportPath)) {
            System.err.println("exportPath is null");
            return;
        }
        if (StringUtils.isBlank(templateFile)) {
            System.err.println("templateFile is null");
            return;
        }
        Map<String, Object> map = FakeData.fakeModel();
        List<Map<String, String>> listMap = FakeData.fakeListModel(fakeDataSize);
        map.put("listMap", listMap);
        exportExcel(exportPath, templateFile, exportFileType, map);
    }

    /**
     * 导出多个Sheet页
     *
     * @param exportPath
     * @param templateFile
     * @param fakeDataSize
     */
    public static void exportMultiSheet(String exportPath, String templateFile, int fakeDataSize, String exportFileType) {
        if (StringUtils.isBlank(exportPath)) {
            System.err.println("exportPath is null");
            return;
        }
        if (StringUtils.isBlank(templateFile)) {
            System.err.println("templateFile is null");
            return;
        }
        Map<String, Object> map = FakeData.fakeModel();
        List<Map<String, String>> listMap = FakeData.fakeListModel(fakeDataSize);
        map.put("listMap", listMap);
        listMap = FakeData.fakeListModel(fakeDataSize);
        map.put("listMap1", listMap);
        exportExcel(exportPath, templateFile, exportFileType, map);
    }

    private static void exportExcel(String exportPath, String templateFile, String exportFileType, Map listMap) {
        FileOutputStream fos = null;
        try {
            TemplateExportParams templateExportParams = new TemplateExportParams(  "/" + templateFile, true);
            //横向遍历
            templateExportParams.setColForEach(true);
            templateFile = templateFile.substring(0,templateFile.lastIndexOf("."));
            String exportName = templateFile+ TEMPLATE_EXPORT_NAME + exportFileType;
            File exportFile = new File(exportPath + "/" + exportName);
            Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, listMap);
            //ExcelExportUtil.exportBigExcel(templateExportParams, listMap)

            fos = new FileOutputStream(exportFile);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭输出流
            closeQuietly(fos);
        }
    }

    /**
     * 关闭输出流
     *
     * @param closeable
     */
    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
