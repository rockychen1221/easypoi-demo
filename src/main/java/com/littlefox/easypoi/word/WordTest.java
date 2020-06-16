package com.littlefox.easypoi.word;


import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.littlefox.easypoi.dto.FakeData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.ClassUtils;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Word测试类
 *
 * @author delei
 */
public class WordTest {
    public final static String TEMPLATE_EXPORT_NAME = "export_result";
    public final static String ROOT_PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    /**
     * 导出单个Sheet页
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
        templateFile = ROOT_PATH + "/" + templateFile;
        Map<String, Object> map = FakeData.fakeModel();
        List<Map<String, String>> listMap = FakeData.fakeListModel(fakeDataSize);
        map.put("listMap", listMap);
        //图片
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setHeight(128);//图片高度
        imageEntity.setWidth(128);//图片宽度
        imageEntity.setUrl(ROOT_PATH + "/" + "imgs/test.png");//图片路径
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
        FileOutputStream fos = null;
        try {
            String exportName = TEMPLATE_EXPORT_NAME + exportFileType;
            File exportFile = new File(exportPath + "/" + exportName);
            XWPFDocument s = WordExportUtil.exportWord07(templateFile, listMap);
            fos = new FileOutputStream(exportFile);
            s.write(fos);
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
