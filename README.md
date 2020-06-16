# easypoi-demo

POI提供了HSSF、XSSF以及SXSSF三种方式操作Excel。

HSSF：Excel97-2003版本，扩展名为.xls。一个sheet最大行数65536，最大列数256。

XSSF：Excel2007版本开始，扩展名为.xlsx。一个sheet最大行数1048576，最大列数16384。

SXSSF：是在XSSF基础上，POI3.8版本开始提供的支持低内存占用的操作方式，扩展名为.xlsx。

Excel版本兼容性是向下兼容。


## Excel
1. Excel导出单个sheet页
2. Excel导出多个Sheet页
3. Excel导出单个对象
4. Excel导出List Map对象

> com.littlefox.example.excel.ExcelTest

## Word
1. 导出单个对象
2. 导出List Map对象
3. 导出图片文件

> com.littlefox.example.excel.WordTest

目前Word支持2007及其以上的版本，不支持内嵌表格操作

## 实例（财务报表-资产负债）

## 性能测试（财务科目）

| 数据量   |   耗时   |  easypoi    |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |



## 工程运行
	1. 打包`mvn clean package`,`target`目录下生成`easypoi-example.jar`
    2. 使用`java -jar`命令来执行
    3. 参数说明：
    --p      导出后的文件存放的根目录，例如：--p=D:/
    --d      程序自动生成的数据数量，例如: --d=500，测试程序单个list将生成500条数据
    --type   10(单sheet,xls格式);11(单sheet,xlsx格式);12(多sheet,xlsx格式);13(多sheet,xlsx格式);20(word,docx格式);

样例: `java -jar easypoi-example.jar --p=D:/ --d=500 --type=10`

## 其他
1. 建议运行时加上JVM内存设置: 例如 `-Xmx2048m -Xms2048m`
2. 所有的模板文件在`src/main/resources/`中
