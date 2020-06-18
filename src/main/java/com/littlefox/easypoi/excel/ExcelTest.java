
package com.littlefox.easypoi.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.littlefox.easypoi.dto.FakeData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StopWatch;

import java.io.*;
import java.util.*;

/**
 * Excel测试类
 *
 * @author delei
 */
public class ExcelTest {
    public final static String TEMPLATE_EXPORT_NAME = "_export_result";

    /**
     * 导出单个Sheet页（包含单个对象、列表、图片）
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
        List<Map<String, Object>> listMap = FakeData.fakeListModel(fakeDataSize);
        map.put("listMap", listMap);
        exportExcel(exportPath, templateFile, exportFileType, map);
    }

    /**
     * 导出多个Sheet页（包含单个对象、列表、图片）
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
        List<Map<String, Object>> listMap = FakeData.fakeListModel(fakeDataSize);
        map.put("listMap", listMap);
        listMap = FakeData.fakeListModel(fakeDataSize);
        map.put("listMap1", listMap);
        exportExcel(exportPath, templateFile, exportFileType, map);
    }

    private static void exportExcel(String exportPath, String templateFile, String exportFileType, Map listMap) {
        TemplateExportParams templateExportParams = new TemplateExportParams("/" + templateFile, true);
        //横向遍历
        templateExportParams.setColForEach(true);

        String tempFile = templateFile.substring(0, templateFile.lastIndexOf("."));
        String exportName = tempFile + TEMPLATE_EXPORT_NAME + exportFileType;
        File exportFile = new File(exportPath + "/" + exportName.split("/")[0]);
        if (!exportFile.exists()) {
            exportFile.mkdirs();
        }

        //System.err.println(JSON.toJSONString(listMap));

        StopWatch stopWatch= new StopWatch(templateFile);
        stopWatch.start(templateFile);
        try (
                FileOutputStream fos = new FileOutputStream(exportPath + "/" + exportName);
        ) {
            Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, listMap);
            workbook.write(fos);
            workbook.close();
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
