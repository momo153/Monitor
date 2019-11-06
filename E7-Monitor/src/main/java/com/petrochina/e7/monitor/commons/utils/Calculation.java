package com.petrochina.e7.monitor.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.petrochina.e7.monitor.pojo.IndexValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName com.petrochina.e7.monitor.commons.utils.ksh
 * @ClassName: Furnace
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/10/20 0020$ 16:01$
 * @Version: 1.0
 */

public class Calculation {
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 加热炉计算
     * @Date 20:55 2019/10/16 0016
     * @Param []
     **/
    public static String cauculIdexEchoByFurnace(Map<String, Object> paramMap) {
        String indexResultStr = null;
        try {
            Map<String, Object> indexResultMap = new LinkedHashMap<String, Object>();

//            Object dataParam = paramMap.get("dataParam");
//            Map<String, Object> dataParamMap = JSONObject.parseObject(JSON.toJSONString(dataParam));

            String fuelTemp = (String) paramMap.get("燃煤温度");
//            String volume = (String) paramMap.get("燃料消耗量");
            String mediaFlow = (String) paramMap.get("介质流量");
            String influentTemp = (String) paramMap.get("进水温度");
            String drainageTemp = (String) paramMap.get("出水温度");

            String moisCont = (String) paramMap.get("含水率");
            String 原油在流量测定处温度 = (String) paramMap.get("原油在流量测定处温度");
            String exhaustRO2Content = (String) paramMap.get("排烟处(RO2)");
            String exhaustO2Content = (String) paramMap.get("排烟处(O2)");
            String exhaustCOContent = (String) paramMap.get("排烟处(CO)");

//            String 炉渣温度 = (String) paramMap.get("炉渣温度");
//            String 炉渣重量 = (String) paramMap.get("炉渣重量");
            String ratedCapacity = (String) paramMap.get("加热炉容量");
            String 加热炉类型 = (String) paramMap.get("加热炉类型");
            String 加热炉用途 = (String) paramMap.get("加热炉用途");

            String 使用年限 = (String) paramMap.get("使用年限");
            String 是否自动燃烧器 = (String) paramMap.get("是否自动燃烧器");
            String 燃料应用基水分 = (String) paramMap.get("燃料应用基水分");
//            String 炉渣比热与温度乘积 = (String) paramMap.get("炉渣比热与温度乘积");
            String ratedHeatDissipation = (String) paramMap.get("额定散热");

            String gasVolume = (String) paramMap.get("燃气量");
            String airTemp = (String) paramMap.get("空气温度");
            String 燃气压力 = (String) paramMap.get("燃气压力");
            String fuelSpecificHeat = (String) paramMap.get("燃料比热");
            String FurnaceSurfaceTemp = (String) paramMap.get("炉体外表面温度");

            String exhaustTemp = (String) paramMap.get("排烟温度");
            String lowFuelCalorificValue = (String) paramMap.get("燃料低位发热值");
            String 被加热介质密度 = (String) paramMap.get("被加热介质密度");
            String 被加热介质含水量 = (String) paramMap.get("被加热介质含水量");
            //公式逻辑计算
            //指标1:排烟温度
            //指标2:炉体外表面平均温度
            //指标3:空气系数 标准公式：21/(21-79*(排烟处O2-0.5*排烟处CO-0.5*排烟处H2-2*排烟处CH4)/(排烟处N2-((收到基N2*(排烟处RO2+排烟处CO+排烟处CH4))/(收到基CO2+收到基CO+收到基不饱和烃CmHn与C分子数m乘积总和+收到基H2S))))
//                          表格公式:21/(21-79*((排烟处O2-0.5*排烟处CO)/(100-排烟处RO2-排烟处O2-排烟处O2)))
            Double airCoefficient = GasHeatingFurnaceFormula.airCoefficient(exhaustO2Content, exhaustCOContent, exhaustRO2Content);
//            System.out.println("空气系数:" + airCoefficient);
            //指标4:热效率 a:3
            //指标5:锅炉出力 b:2
            /*计算锅炉出力,参数1:介质流量
                          参数2:出水温度
                          参数3:入水温度
                          */
            Double boilerOutput = GasHeatingFurnaceFormula.boilerOutput(mediaFlow, drainageTemp, influentTemp);
//            System.out.println("锅炉出力:" + boilerOutput);
            /*输入热量,参数1:燃料温度
                      参数2:燃料比热
                      参数3:燃料低位发热值
                      参数4:燃气量
            * */
            Double inputHeat = GasHeatingFurnaceFormula.inputHeat(fuelTemp, fuelSpecificHeat, lowFuelCalorificValue, gasVolume);
//            System.out.println("输入热量:" + inputHeat);
            //指标6:正平衡效率
            /*正平衡效率
                参数1:计算锅炉出力
                参数2:输入热量
            * */
            Double positiveBalanceEffic = GasHeatingFurnaceFormula.positiveBalanceEffic(boilerOutput, inputHeat);
//            System.out.println("正平衡效率:" + positiveBalanceEffic);

            /*散热损失(对)
             * 参数1:介质流量
             * 参数2:出水温度
             * 参数3:入水温度
             * 参数4:额定容量
             * 参数5:额定散热
             * */
            Double heatDissipation = GasHeatingFurnaceFormula.heatDissipation(mediaFlow, drainageTemp, influentTemp, ratedCapacity, ratedHeatDissipation);
//            System.out.println("散热损失:" + heatDissipation);
            /*未完全燃烧热损失(对)
             * 参数1:空气系数
             * 参数2:排烟处（CO)
             * */
            Double incompleteCombustionHeatLoss = GasHeatingFurnaceFormula.incompleteCombustionHeatLoss(airCoefficient, exhaustCOContent);
//            System.out.println("未完全燃烧热损失:" + incompleteCombustionHeatLoss);
            /*排烟热损失
             * 参数1:空气系数
             * 参数2:排烟温度
             * 参数3:空气温度
             * */
            Double exhaustHeatLoss = GasHeatingFurnaceFormula.exhaustHeatLoss(airCoefficient, exhaustTemp, airTemp);
//            System.out.println("排烟热损失:" + exhaustHeatLoss);
            /*各项热损失之和
             * 参数1:介质流量
             * 参数2:出水温度
             * 参数3;入水温度
             * 参数4:额定容量
             * 参数5:额定散热
             * 参数6:空气系数
             * 参数7:排烟处（CO)
             * 参数8:排烟温度
             * 参数9:空气温度
             * */
//            Double heatLossSum = GasHeatingFurnaceFormula.heatLossSum(mediaFlow, drainageTemp, influentTemp, ratedCapacity, ratedHeatDissipation, airCoefficient, exhaustO2Content, exhaustTemp, airTemp);
            Double heatLossSum = GasHeatingFurnaceFormula.heatLossSum(heatDissipation, incompleteCombustionHeatLoss, exhaustHeatLoss);
//            System.out.println("各项热损失之和:" + heatLossSum);
            //指标7:反平衡效率
            /*反平衡效率
             * 参数1:各项热损失之和
             * */
            Double reverseBalanceEffic = GasHeatingFurnaceFormula.reverseBalanceEffic(heatLossSum);
//            System.out.println("反平衡效率:" + reverseBalanceEffic);
            /*加热炉效率
             * 参数1:正平衡效率
             * 参数2:反平衡效率
             * */
               Double furnaceEfficiency = GasHeatingFurnaceFormula.furnaceEfficiency(positiveBalanceEffic, reverseBalanceEffic);
//            System.out.println("加热炉效率:" + furnaceEfficiency);

            indexResultMap.put("空气系数", airCoefficient);
            indexResultMap.put("炉体外表面温度", FurnaceSurfaceTemp);
            indexResultMap.put("炉渣含碳量", "null");
            indexResultMap.put("排烟温度", exhaustTemp);
            indexResultMap.put("加热炉热效率", furnaceEfficiency);
//            indexResultMap.put("锅炉出力", boilerOutput);
//            indexResultMap.put("正平衡效率", positiveBalanceEffic);
//            indexResultMap.put("反平衡效率", reverseBalanceEffic);
            //获取指标值回显到新增页面
            List<IndexValue> indexValuesList = indexEcho(indexResultMap);

            Result success2 = ResultUtil.success(indexValuesList, indexValuesList.size());
            indexResultStr = JSON.toJSONStringWithDateFormat(success2, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
//            logger.info(indexResultStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indexResultStr.toString();
    }

    /**
     * @return java.util.List<com.petrochina.e7.monitor.pojo.IndexValue>
     * @Author mzc
     * @Description //TODO 指标回显
     * @Date 14:35 2019/10/20 0020
     * @Param [indexResultMap]
     **/
    public static List<IndexValue> indexEcho(Map<String, Object> indexResultMap) {
        List<IndexValue> indexValueList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : indexResultMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            IndexValue iv = new IndexValue();
            iv.setRemark(key);
            if(value != null) {
                iv.setCategoryIndexValue(value.toString());
                indexValueList.add(iv);
            }else{
                iv.setCategoryIndexValue("null");
                indexValueList.add(iv);
            }
        }
        return indexValueList;
    }

    /**
     * @return java.lang.String
     * @Author mzc
     * @Description //TODO 机采系统抽油机计算
     * @Date 16:16 2019/10/20 0020
     * @Param [paramMap]
     **/
    public static String cauculIdexEchoByPump(Map<String, Object> paramMap) {
        System.out.println(paramMap);
        String indexResultStr = null;
        try {
            Map<String, Object> indexResultMap = new LinkedHashMap<String, Object>();

            String 抽油机型号 = (String) paramMap.get("抽油机型号");
            String 抽油机出厂日期 = (String) paramMap.get("抽油机出厂日期");
            String 抽油机生产厂家 = (String) paramMap.get("抽油机生产厂家");
            String 电机型号 = (String) paramMap.get("电机型号");
            String 电机是否淘汰 = (String) paramMap.get("电机是否淘汰");

            String ratePower = (String) paramMap.get("电机额定功率");
            String 额定电流 = (String) paramMap.get("额定电流");
            String 额定电压 = (String) paramMap.get("额定电压");
            String 额定转数 = (String) paramMap.get("额定转数");
            String 电机出厂日期 = (String) paramMap.get("电机出厂日期");

            String 电机生产厂家 = (String) paramMap.get("电机生产厂家");
            String 配电箱出厂日期 = (String) paramMap.get("配电箱出厂日期");
            String 配电箱电容补偿 = (String) paramMap.get("配电箱电容补偿");
            String 配电箱生产厂家 = (String) paramMap.get("配电箱生产厂家");
            String 配电箱运行电压 = (String) paramMap.get("配电箱运行电压");

            String 油品性质 = (String) paramMap.get("油品性质");
            String 区块渗透率 = (String) paramMap.get("区块渗透率");
            String 井眼轨迹 = (String) paramMap.get("井眼轨迹");
            String 运行频率 = (String) paramMap.get("运行频率");
            String runTime = (String) paramMap.get("运行时间");

            String inputPow = (String) paramMap.get("有功功率");
            String 负功电量 = (String) paramMap.get("负功电量");
            String 累计时间 = (String) paramMap.get("累计时间");
//             String 无功功率 = (String) paramMap.get("无功功率");//计算
            String factorPower = (String) paramMap.get("功率因数");

            String wellhTubPressure = (String) paramMap.get("油压");
            String wellhCasPressure = (String) paramMap.get("套压");
            String oilWellDynaLiquLevDepth = (String) paramMap.get("动液面深度");
            String pumpDepth = (String) paramMap.get("泵深");
//             String 沉没度 = (String) paramMap.get("沉没度");//计算

            String pumpDiam = (String) paramMap.get("泵径");
            String moisCont = (String) paramMap.get("含水率");
            String prodLiquAmo = (String) paramMap.get("产液量");
            String crudOilDens = (String) paramMap.get("原油密度");
            String stroke = (String) paramMap.get("冲程");

            String rushtimes = (String) paramMap.get("冲次");
            String upstrokeCurrent = (String) paramMap.get("上冲程电流");
            String downstrokeCurrent = (String) paramMap.get("下冲程电流");

            String 电机功率利用率 = (String) paramMap.get("电机功率利用率");
            String 液体密度 = (String) paramMap.get("液体密度");

            String 有效扬程 = (String) paramMap.get("有效扬程");
            String 有效功率 = (String) paramMap.get("有效功率");
            String 吨液百米单耗 = (String) paramMap.get("吨液百米单耗");
            String 泵效率 = (String) paramMap.get("泵效率");

            //沉没度
            Double sinkDegr = PumpUnitFormula2.sinkDegr(pumpDepth, oilWellDynaLiquLevDepth);
            System.out.println("sinkDegr:" + sinkDegr);
            //电机功率利用率
            double powFactor = PumpUnitFormula2.powFactorExcel(inputPow, ratePower);
            System.out.println("powFactor:" + powFactor);
            //液体密度计算 (2个公式有出入,待查)
            String waterDens = "1";//水的密度
//            Double dens = PumpUnitFormula2.dens(moisCont, crudOilDens, waterDens);
            Double dens = PumpUnitFormula2.densExcel(moisCont, crudOilDens);
            System.out.println("dens:" + dens);
            //有效扬程(计算结果不对,待查)
            double g = 9.81;
            Double eficHead = PumpUnitFormula2.eficHead(oilWellDynaLiquLevDepth, wellhTubPressure, wellhCasPressure, dens, g);
            System.out.println("eficHead:" + eficHead);
            //输出功率(有效功率)
//            Double outPower = PumpUnitFormula2.outPower(prodLiquAmo, eficHead, dens, g);
            Double outPowerExcel = PumpUnitFormula2.outPowerExcel(prodLiquAmo, eficHead, g);
            System.out.println("outPower:" + outPowerExcel);
            //吨液百米单耗计算
            Double tonliquSconsu = PumpUnitFormula2.tonliquSconsuExcel(inputPow, runTime, prodLiquAmo, eficHead, dens);
            System.out.println("tonliquSconsu:" + tonliquSconsu);
            //系统效率
            Double sysefic = PumpUnitFormula2.sysefic(outPowerExcel, inputPow);
            System.out.println("sysefic:" + sysefic);
            //平衡度
            Double balance = PumpUnitFormula2.balanceExcel(upstrokeCurrent, downstrokeCurrent);
            System.out.println("balance:" + balance);

            //泵效率
            Double pumpEffic = PumpUnitFormula2.PumpEffic(prodLiquAmo, pumpDiam, stroke, rushtimes);
            System.out.println("pumpEffic:"+pumpEffic);

            indexResultMap.put("电动机功率因数", factorPower);
            indexResultMap.put("抽油机平衡度", balance);
            indexResultMap.put("系统效率", sysefic.toString());
            List<IndexValue> indexValuesList = indexEcho(indexResultMap);

            Result success2 = ResultUtil.success(indexValuesList, indexValuesList.size());
            indexResultStr = JSON.toJSONStringWithDateFormat(success2, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return indexResultStr;
    }

}
