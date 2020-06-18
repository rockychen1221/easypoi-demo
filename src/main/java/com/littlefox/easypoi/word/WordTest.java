
package com.littlefox.easypoi.word;

import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.littlefox.easypoi.dto.FakeData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.StopWatch;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Word测试类
 *
 * @author delei
 */
public class WordTest {

    public final static String TEMPLATE_EXPORT_NAME = "export_result";

    /**
     * 导出word (包含单个对象、列表、图片)
     *
     * @param exportPath
     * @param templateFile
     * @param fakeDataSize
     */
    public static void export(String exportPath, String templateFile, int fakeDataSize, String exportFileType) {
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
        //图片
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setHeight(128);//图片高度
        imageEntity.setWidth(128);//图片宽度
        imageEntity.setUrl("/imgs/test.png");//图片路径
        imageEntity.setType(ImageEntity.URL);
        map.put("imageEntity", imageEntity);
        exportWord(exportPath, templateFile, exportFileType, map);
    }

    /**
     * 导出Word(07以上的版本)
     *
     * @param exportPath
     * @param templateFile
     * @param exportFileType
     * @param listMap
     */
    private static void exportWord(String exportPath, String templateFile, String exportFileType, Map listMap) {
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
            XWPFDocument s = WordExportUtil.exportWord07("/" + templateFile, listMap);
            s.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.err.println(stopWatch.prettyPrint());
        System.err.println(stopWatch.getTotalTimeSeconds());
    }

}
