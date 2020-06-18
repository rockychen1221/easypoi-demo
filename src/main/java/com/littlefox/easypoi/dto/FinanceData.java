package com.littlefox.easypoi.dto;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author rockychen
 * @version 1.0
 * @date 2020-06-15 10:11
 */
public class FinanceData {
    /**
     * 资产负债科目
     */
    public static String assetSubject = "流动资产：" +
            ",        货币资金" +
            ",        结算备付金" +
            ",        拆出资金" +
            ",        交易性金融资产" +
            ",        应收票据" +
            ",        应收账款" +
            ",        预付款项" +
            ",        应收保费" +
            ",        应收分保账款" +
            ",        应收分保合同准备金" +
            ",        应收利息" +
            ",        其他应收款" +
            ",        应收股利" +
            ",        买入返售金融资产" +
            ",        存货" +
            ",        其中：消耗性生物资产" +
            ",        一年内到期非流动资产" +
            ",        待摊费用" +
            ",        其他流动资产" +
            ",        流动资产差额(特殊报表科目)" +
            ",        流动资产差额(合计平衡项目)" +
            ",                流动资产合计" +
            ",非流动资产：" +
            ",        发放贷款及垫款" +
            ",        可供出售金融资产" +
            ",        持有到期投资" +
            ",        长期应收账款" +
            ",        长期股权投资" +
            ",        投资性房地产" +
            ",        固定资产" +
            ",        在建工程" +
            ",        工程物资" +
            ",        固定资产清理" +
            ",        生产性生物资产" +
            ",        油气资产" +
            ",        无形资产" +
            ",        开发支出" +
            ",        商誉" +
            ",        长期待摊费用" +
            ",        递延所得税资产" +
            ",        其他非流动资产" +
            ",        非流动资产差额(特殊报表科目)" +
            ",        非流动资产差额(合计平衡项目)" +
            ",                非流动资产合计" +
            ",        资产差额(特殊报表科目)" +
            ",        资产差额(合计平衡项目)" +
            ",                        资产总计" +
            ",流动负债：" +
            ",        短期借款" +
            ",        向中央银行借款" +
            ",        吸收存款及同业存放" +
            ",        拆入资金" +
            ",        交易性金融负债" +
            ",        应付票据" +
            ",        应付账款" +
            ",        预收款项" +
            ",        卖出回购金融资产款" +
            ",        应付手续费及佣金" +
            ",        应付职工薪酬" +
            ",        应交税费" +
            ",        应付利息" +
            ",        其他应付款" +
            ",        应付分保账款" +
            ",        保险合同准备金" +
            ",        代理买卖证券款" +
            ",        代理承销证券款" +
            ",        一年内到期的非流动负债" +
            ",        应付股利" +
            ",        预提费用" +
            ",        递延收益-流动负债" +
            ",        应付短期债券" +
            ",        流动负债差额(特殊报表科目)" +
            ",        流动负债差额(合计平衡项目)" +
            ",        其他流动负债" +
            ",                流动负债合计" +
            ",非流动负债：" +
            ",        长期借款" +
            ",        应付债券" +
            ",        长期应付款" +
            ",        专项应付款" +
            ",        预计负债" +
            ",        递延所得税负债" +
            ",        递延收益-非流动负债" +
            ",        其他非流动负债" +
            ",        非流动负债差额(特殊报表科目)" +
            ",        非流动负债差额(合计平衡项目)" +
            ",                非流动负债合计" +
            ",        负债差额(特殊报表科目)" +
            ",        负债差额(合计平衡项目)" +
            ",                        负债合计" +
            ",所有者权益（或股东权益）：" +
            ",        实收资本（或股本）" +
            ",        资本公积" +
            ",        减：库存股" +
            ",        盈余公积" +
            ",        专项储备" +
            ",        一般风险准备" +
            ",        未分配利润" +
            ",        外币报表折算差额" +
            ",        未确认的投资损失" +
            ",                归属于母公司所有者权益合计" +
            ",        少数股东权益" +
            ",        股东权益差额(特殊报表科目)" +
            ",        股权权益差额(合计平衡项目)" +
            ",                        所有者权益合计" +
            ",        负债及股东权益差额(特殊报表项目)" +
            ",        负债及股东权益差额(合计平衡项目)" +
            ",                                负债和所有者权益总计";

    /**
     * 财务科目
     *
     * @return
     */
    public static Map<String, Object> financeModel(int years) {

        HashMap<String, Object> map = new LinkedHashMap<String, Object>();

        List<Map<String, Object>> subjectList = new ArrayList();
        AtomicInteger index = new AtomicInteger(1);
        Arrays.asList(FinanceData.assetSubject.split(",")).stream().forEach(i -> {
            List yearList = new ArrayList();

//            Map<String, Object> subjectMap = new LinkedHashMap<String, Object>();
//            subjectMap.put("subject", i);
//            subjectList.add(subjectMap);

            IntStream.range(0, years).forEach(k -> {
                HashMap<String, Object> dataMap = new LinkedHashMap<String, Object>(2);
                dataMap.put("begin", String.valueOf((Math.random() * 9 + 1) * 100000));
                dataMap.put("end", String.valueOf((Math.random() * 9 + 1) * 100000));
                yearList.add(dataMap);
            });
            map.put(String.valueOf(index.getAndIncrement()), yearList);
        });

        List yearLists = new ArrayList();
        List titleLists = new ArrayList();

        IntStream.range(0, years).forEach(k -> {
            HashMap<String, Object> yearMap = new LinkedHashMap<String, Object>();
            int year = LocalDate.now().getYear() + k;
            yearMap.put("year", year);
            yearLists.add(yearMap);

            HashMap<String, Object> title = new LinkedHashMap<String, Object>(2);
            title.put("beginName", "期初");
            title.put("endName", "期末");
            titleLists.add(title);

        });
        map.put("yearList", yearLists);
        map.put("titleList", titleLists);
        //map.put("subject", subjectList);
        return map;
    }

    /**
     * 动态创建表头无模板导出数据
     *
     * @param years
     * @return
     */
    public static List<Map<String, Object>> financeDynaColModel(int years) {
        if (years < 1) {
            throw new IllegalArgumentException("非法参数");
        }
        return Arrays.asList(FinanceData.assetSubject.split(",")).stream().map(i -> {
            //单个对象
            HashMap<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("subject", i);
            IntStream.range(0, years).forEach(k -> {
                List yearList = new ArrayList();
                int year = LocalDate.now().getYear() + k;
                HashMap<String, Object> yearMap = new LinkedHashMap<String, Object>(2);
                yearMap.put("begin", String.valueOf((Math.random() * 9 + 1) * 100000));
                yearMap.put("end", String.valueOf((Math.random() * 9 + 1) * 100000));
                yearList.add(yearMap);
                map.put(String.valueOf(year), yearList);
            });
            return map;
        }).collect(Collectors.toList());
    }
}
