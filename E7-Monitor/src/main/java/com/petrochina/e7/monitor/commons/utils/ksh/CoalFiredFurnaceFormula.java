package com.petrochina.e7.monitor.commons.utils.ksh;

import com.petrochina.e7.monitor.commons.utils.DoubleUtil;

/**
 * @author : YaoDong
 * @create : 2019-06-21 17:16
 * @description : 燃煤加热炉计算公式工具类
 * 注释行是当里面参数代表特殊意义时，方便将参数抽取出来，注释行只是相对于原公式把百分比进行了简化
 **/
public class CoalFiredFurnaceFormula {

    //TODO: 1.这里燃料干燥基比热容被写死0.25，而标准里只有无烟煤贫煤0.92，烟煤0.96，褐煤 1.09
    //TODO: 2. (煤燃料干燥基比热容*(100-param)+4.1868*param)/100

    /**
     * @param fuelABM 燃料应用基水分  Fuel application base moisture 测试参数  单位：%
     * @return java.lang.Double
     * @Date 10:59 2019/6/21
     * @description :   燃料比热容    Fuel specific heat     单位：kJ/kg ·℃      原公式：((100-param)/100*0.25+param/100)*4.1868
     **/
    public static Double fuelSpecificHeat(String fuelABM) {
        Double param = Double.parseDouble(fuelABM);
//        Double result = (25 + 0.75 * param) / 100 * 4.1868;
        Double result = 0.031401 * param + 1.0467;
        return DoubleUtil.round(result, 2);
    }


    /**
     * @param fuelSpecificHeat 燃料比热容    Fuel specific heat  计算参数     单位：kJ/kg ·℃
     * @param coalBurnTemp     燃煤温度 Coal burning temperature 测试参数 单位：℃
     * @return java.lang.Double
     * @Date 9:49 2019/6/25
     * @description :   燃料物理热    Fuel physics heat   单位：kJ/kg   原公式：param1*param2
     **/
    public static Double fuelPhysicsHeat(Double fuelSpecificHeat, String coalBurnTemp) {
        Double param2 = Double.parseDouble(coalBurnTemp);
        Double result = fuelSpecificHeat * param2;
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param fuelABM      燃料应用基水分  Fuel application base moisture 测试参数 单位：%
     * @param coalBurnTemp 燃煤温度 Coal burning temperature 测试参数 单位：℃
     * @return java.lang.Double
     * @Date 15:22 2019/6/21
     * @description :   燃料物理热    Fuel physics heat   单位：kJ/kg
     **/
    public static Double fuelPhysicsHeat(String fuelABM, String coalBurnTemp) {
        return fuelPhysicsHeat(fuelSpecificHeat(fuelABM), coalBurnTemp);
    }


    //TODO: 公式有问题，标准里面没有,是介质-测试/气体-化验参数
    /**
     * @param crudeOilTemp 流量测定处原油温度  Crude oil temperature at flow measurement 测试参数    单位：℃
     * @return java.lang.Double
     * @Date 16:55 2019/6/21
     * @description :  流量测定处原油密度    Crude oil density at flow measurement     单位：kg/m3   原公式：860-(param-20)*0.00068
     **/
    public static Double crudeOilDensity(String crudeOilTemp) {
        Double param = Double.parseDouble(crudeOilTemp);
//        Double result = 860 - (param - 20) * 0.00068;
        Double result = 860.0136 - 0.00068 * param;
        return DoubleUtil.round(result, 2);
    }

    //TODO:比标准多     含水率 * 4.1868 * 1000
    /**
     * @param outletTemp   出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp    入口温度 Inlet temperature   测试参数    单位：℃
     * @param mediaFlow    介质流量 Media flow  测试参数    单位：m3/h
     * @param waterContent 含水率  Water content ratio 测试参数    单位：%
     * @return java.lang.Double
     * @Date 9:37 2019/6/24
     * @description :  水有效输出热量   Water effectively outputs heat    单位：kJ/h  原公式: (param1-param2)*param3*(param4/100)*4.1868*1000
     **/
    public static Double waterEffectOutputsHeat(String outletTemp, String inletTemp, String mediaFlow, String waterContent) {
        Double param1 = Double.parseDouble(outletTemp);
        Double param2 = Double.parseDouble(inletTemp);
        Double param3 = Double.parseDouble(mediaFlow);
        Double param4 = Double.parseDouble(waterContent);
//        Double result = (param1 - param2) * param3 * param4 * 4.1868 * 1000/100;
        Double result = (param1 - param2) * param3 * param4 * 41.868;
        return DoubleUtil.round(result, 2);
    }


    //TODO: 1. 比标准多 (1-含水率/100)
    //TODO: 2. 20℃原有密度是否是860
    /**
     * @param outletTemp   出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp    入口温度 Inlet temperature   测试参数    单位：℃
     * @param waterContent 含水率  Water content ratio 测试参数    单位：%
     * @param mediaFlow    介质流量 Media flow  测试参数    单位：m3/h
     * @param oilDensity   流量测定处原油密度    Crude oil density at flow measurement   计算参数    单位：kg/m3
     * @return java.lang.Double
     * @Date 11:06 2019/6/24
     * @description :  原油有效输出热量  Effective heat output of crude oil  单位：kJ/h
     * 原公式: 4.1868*(param1*(0.403+0.00081*param1)/SQRT(860/1000)-param2*(0.403+0.00081*param2)/SQRT(860/1000))(1-param3/100)*param4*param5
     **/
    public static Double oilEffectOutputsHeat(String outletTemp, String inletTemp, String waterContent, String mediaFlow, Double oilDensity) {
        Double param1 = Double.parseDouble(outletTemp);
        Double param2 = Double.parseDouble(inletTemp);
        Double param3 = Double.parseDouble(waterContent);
        Double param4 = Double.parseDouble(mediaFlow);
        Double result = 4.1868 * (param1 - param2) * (0.403 + 0.00081 * (param1 + param2)) * (100 - param3) * param4 * oilDensity / Math.sqrt(8600);
        return DoubleUtil.round(result, 3);
    }

    /**
     * @param outletTemp   出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp    入口温度 Inlet temperature   测试参数    单位：℃
     * @param waterContent 含水率  Water content ratio 测试参数    单位：%
     * @param mediaFlow    介质流量 Media flow  测试参数    单位：m3/h
     * @param crudeOilTemp 流量测定处原油温度  Crude oil temperature at flow measurement 测试参数    单位：℃
     * @return java.lang.Double
     * @Date 14:32 2019/6/24
     * @description :   原油有效输出热量  Effective heat output of crude oil  单位：kJ/h
     **/
    public static Double oilEffectOutputsHeat(String outletTemp, String inletTemp, String waterContent, String mediaFlow, String crudeOilTemp) {
        return oilEffectOutputsHeat(outletTemp, inletTemp, waterContent, mediaFlow, crudeOilDensity(crudeOilTemp));
    }

    //TODO: 燃煤热值= 收到基低位发热量Q(net.v.ar)+ 外来燃料加热空气时标准情况下每千克或每立方米燃料所给热量Q(wl)？
    //TODO: 表格为：燃煤热值；   标准为：收到基低位发热量Q(net.v.ar)+ 外来燃料加热空气时标准情况下每千克或每立方米燃料所给热量Q(wl)
    /**
     * @param fuelConsu       燃料消耗量 Fuel consumption    测试参数    单位：kg/h
     * @param coalCalorific   燃煤热值  Coal calorific value    化验参数    单位：kJ/kg
     * @param fuelPhysicsHeat 燃料物理热 Fuel physics heat   计算参数    单位：kJ/kg
     * @return java.lang.Double
     * @Date 15:16 2019/6/24
     * @description :  输给热量  Provide heat  单位：kJ/h  原公式：param1 * (param2 + param3)
     **/
    public static Double provideHeat(String fuelConsu, String coalCalorific, Double fuelPhysicsHeat) {
        Double param1 = Double.parseDouble(fuelConsu);
        Double param2 = Double.parseDouble(coalCalorific);
        Double result = param1 * (param2 + fuelPhysicsHeat);
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param fuelConsu     燃料消耗量 Fuel consumption  测试参数  单位：kg/h
     * @param coalCalorific 燃煤热值  Coal calorific value 化验参数   单位：kJ/kg
     * @param fuelABM       燃料应用基水分   Fuel application base moisture  测试参数    单位：%
     * @param coalBurnTemp  燃煤温度  Coal burning temperature 测试参数   单位：℃
     * @return java.lang.Double
     * @Date 15:27 2019/6/24
     * @description :  输给热量  Provide heat  单位：kJ/h
     **/
    public static Double provideHeat(String fuelConsu, String coalCalorific, String fuelABM, String coalBurnTemp) {
        return provideHeat(fuelConsu, coalCalorific, fuelPhysicsHeat(fuelABM, coalBurnTemp));
    }

    /**
     * @param waterEffectOutputsHeat 水有效输出热量   Water effectively outputs heat  计算参数    单位：kJ/h
     * @param oilEffectOutputsHeat   原油有效输出热量  Effective heat output of crude oil  计算参数    单位：kJ/h
     * @return java.lang.Double
     * @Date 15:42 2019/6/24
     * @description :  有效输出热量    Effective heat output  单位：kJ/h  原公式：param1 + param2
     **/
    public static Double effectOutHeat(Double waterEffectOutputsHeat, Double oilEffectOutputsHeat) {
        Double result = waterEffectOutputsHeat + oilEffectOutputsHeat;
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param outletTemp   出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp    入口温度 Inlet temperature   测试参数    单位：℃
     * @param waterContent 含水率  Water content ratio 测试参数    单位：%
     * @param mediaFlow    介质流量 Media flow  测试参数    单位：m3/h
     * @param crudeOilTemp 流量测定处原油温度  Crude oil temperature at flow measurement 测试参数    单位：℃
     * @return java.lang.Double
     * @Date 15:54 2019/6/24
     * @description :  有效输出热量    Effective heat output  单位：kJ/h
     **/
    public static Double effectOutHeat(String outletTemp, String inletTemp, String waterContent, String mediaFlow, String crudeOilTemp) {
        return effectOutHeat(waterEffectOutputsHeat(outletTemp, inletTemp, mediaFlow, waterContent), oilEffectOutputsHeat(outletTemp, inletTemp, waterContent, mediaFlow, crudeOilTemp));
    }

    //FIXME:
    /**
     * @param effectOutHeat   有效输出热量    Effective heat output   计算参数  单位：kJ/h
     * @param furnaceCapacity 加热炉容量 Furnace capacity   现场录入  单位：MW
     * @return java.lang.Double
     * @Date 17:10 2019/6/24
     * @description :  热负荷率  Heat load rate    单位：%  原公式：param1 / (param2 * 3600000) * 100
     **/
    public static Double heatLoadRate(Double effectOutHeat, String furnaceCapacity) {
        Double param = Double.parseDouble(furnaceCapacity);
        Double result = effectOutHeat / (param * 36000);
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param outletTemp      出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp       入口温度 Inlet temperature   测试参数    单位：℃
     * @param waterContent    含水率  Water content ratio 测试参数    单位：%
     * @param mediaFlow       介质流量 Media flow  测试参数    单位：m3/h
     * @param crudeOilTemp    流量测定处原油温度  Crude oil temperature at flow measurement 测试参数    单位：℃
     * @param furnaceCapacity 加热炉容量 Furnace capacity   现场录入  单位：MW
     * @return java.lang.Double
     * @Date 17:14 2019/6/24
     * @description :  热负荷率  Heat load rate    单位：%
     **/
    public static Double heatLoadRate(String outletTemp, String inletTemp, String waterContent, String mediaFlow, String crudeOilTemp, String furnaceCapacity) {
        return heatLoadRate(effectOutHeat(outletTemp, inletTemp, waterContent, mediaFlow, crudeOilTemp), furnaceCapacity);
    }

    //TODO:标准：有效输出热量Q / 总供给能量Q(T)
    /**
     * @param mediaFlow              介质流量 Media flow  测试参数    单位：m3/h
     * @param outletTemp             出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp              入口温度 Inlet temperature   测试参数    单位：℃
     * @param waterEffectOutputsHeat 水有效输出热量   Water effectively outputs heat  计算参数    单位：kJ/h
     * @param oilEffectOutputsHeat   原油有效输出热量  Effective heat output of crude oil  计算参数    单位：kJ/h
     * @param coalCalorific          燃煤热值  Coal calorific value 化验参数   单位：kJ/kg
     * @param fuelPhysicsHeat        燃料物理热    Fuel physics heat  计算参数   单位：kJ/kg
     * @param fuelConsu              燃料消耗量 Fuel consumption    测试参数    单位：kg/h
     * @return java.lang.Double
     * @Date 8:58 2019/6/25
     * @description :  正平衡效率  Positive balance thermal efficiency    字段名：Pbalance efic    单位：%  原公式：(param1*1000*(param2-param3)*param4*param5)/((param6+param7)*param8)/1000*100
     **/
    public static Double positPalanceEffic(String mediaFlow, String outletTemp, String inletTemp, Double waterEffectOutputsHeat, Double oilEffectOutputsHeat, String coalCalorific, Double fuelPhysicsHeat, String fuelConsu) {
        Double param1 = Double.parseDouble(mediaFlow);
        Double param2 = Double.parseDouble(outletTemp);
        Double param3 = Double.parseDouble(inletTemp);
        Double param6 = Double.parseDouble(coalCalorific);
        Double param8 = Double.parseDouble(fuelConsu);
//        Double result = (param1*1000*(param2-param3)*param4*param5)/((param6+param7)*param8)/1000*100;
        Double result = 100 * param1 * waterEffectOutputsHeat * oilEffectOutputsHeat * (param2 - param3) / (param6 + fuelPhysicsHeat) / param8;
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param mediaFlow     介质流量 Media flow  测试参数    单位：m3/h
     * @param outletTemp    出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp     入口温度 Inlet temperature   测试参数    单位：℃
     * @param coalCalorific 燃煤热值  Coal calorific value 化验参数   单位：kJ/kg
     * @param fuelConsu     燃料消耗量 Fuel consumption    测试参数    单位：kg/h
     * @param waterContent  含水率  Water content ratio 测试参数    单位：%
     * @param crudeOilTemp  流量测定处原油温度  Crude oil temperature at flow measurement 测试参数    单位：℃
     * @param fuelABM       燃料应用基水分  Fuel application base moisture 测试参数 单位：%
     * @param coalBurnTemp  燃煤温度 Coal burning temperature 测试参数 单位：℃
     * @return java.lang.Double
     * @Date 9:41 2019/6/25
     * @description :  正平衡效率  Positive balance thermal efficiency    字段名：Pbalance efic    单位：%
     **/
    public static Double positPalanceEffic(String mediaFlow, String outletTemp, String inletTemp, String coalCalorific, String fuelConsu, String waterContent, String crudeOilTemp, String fuelABM, String coalBurnTemp) {
        Double param4 = waterEffectOutputsHeat(outletTemp, inletTemp, mediaFlow, waterContent);
        Double param5 = oilEffectOutputsHeat(outletTemp, inletTemp, waterContent, mediaFlow, crudeOilTemp);
        return positPalanceEffic(mediaFlow, outletTemp, inletTemp, param4, param5, coalCalorific, fuelPhysicsHeat(fuelABM, coalBurnTemp), fuelConsu);
    }

    /**
     * @param exhaustO2Content   排烟处（O2)  Smoke exhaust O2 content   测试参数    单位：%
     * @param exhaustCOContent   排烟处（CO)  Smoke exhaust CO content   测试参数    单位：%
     * @param exhaustRO2Content  排烟处（RO2) Smoke exhaust RO2 content   测试参数    单位：%
     * @param exhaustH2Content   排烟处（H2)  Smoke exhaust H2 content   测试参数    单位：%
     * @param exhaustCmHnContent 排烟处（CmHn)  Smoke exhaust CmHn content   测试参数    单位：%
     * @param fuelCmHnContent    燃料收到基不饱和烃  Fuel CmHn content   化验参数    单位：%
     * @return java.lang.Double
     * @Date 14:02 2019/6/25
     * @description :  过剩空气系数    Excess air factor   单位：α   原公式：21/(21-79*((param1-0.5*param2)/(100-param3-param1-param2)))
     **/
    public static Double excessAirFactor(String exhaustO2Content, String exhaustCOContent, String exhaustRO2Content, String exhaustH2Content, String exhaustCmHnContent, String fuelCmHnContent) {
        Double param1 = Double.parseDouble(exhaustO2Content);
        Double param2 = Double.parseDouble(exhaustCOContent);
        Double param3 = Double.parseDouble(exhaustRO2Content);
        Double param4 = Double.parseDouble(exhaustH2Content);
        Double param5 = Double.parseDouble(exhaustCmHnContent);
        Double param6 = Double.parseDouble(fuelCmHnContent);
//        表格内公式：
//        Double result = 21/(21-79*((param1-0.5*param2)/(100-param3-param1-param2)));
//        标准SYT 6381-2016 石油工业用加热炉热工测定内公式：
        Double result = 21 / (21 - 79 * (param1 - 0.5 * param2 - 0.5 * param4 - 2 * param5) / (100 - param3 - param1 - param2 - param4 - param6));
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param slagWeight       炉渣重量    Slag weight   测试参数    单位：kg/h
     * @param productOfSHAndST 炉渣比热与温度乘积    The product of specific heat and slag temperature   查表参数    单位：kJ/kg
     * @param provideHeat      输给热量  Provide heat  计算参数  单位：kJ/h
     * @return java.lang.Double
     * @Date 9:22 2019/6/26
     * @description :  炉渣物理热损失   Slag physical heat loss    单位：%   原公式：param1*param2/param3
     **/
    public static Double slagPhysicalHeatLoss(String slagWeight, String productOfSHAndST, Double provideHeat) {
        Double param1 = Double.parseDouble(slagWeight);
        Double param2 = Double.parseDouble(productOfSHAndST);
        Double result = param1 * param2 / provideHeat;
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param slagWeight       炉渣重量    Slag weight   测试参数    单位：kg/h
     * @param productOfSHAndST 炉渣比热与温度乘积    The product of specific heat and slag temperature   查表参数    单位：kJ/kg
     * @param fuelConsu        燃料消耗量 Fuel consumption  测试参数  单位：kg/h
     * @param coalCalorific    燃煤热值  Coal calorific value 化验参数   单位：kJ/kg
     * @param fuelABM          燃料应用基水分   Fuel application base moisture  测试参数    单位：%
     * @param coalBurnTemp     燃煤温度  Coal burning temperature   测试参数   单位：℃
     * @return java.lang.Double
     * @Date 9:22 2019/6/26
     * @description :  炉渣物理热损失   Slag physical heat loss    单位：%
     **/
    public static Double slagPhysicalHeatLoss(String slagWeight, String productOfSHAndST, String fuelConsu, String coalCalorific, String fuelABM, String coalBurnTemp) {
        return slagPhysicalHeatLoss(slagWeight, productOfSHAndST, provideHeat(fuelConsu, coalCalorific, fuelABM, coalBurnTemp));
    }

    /**
     * @param slagWeight   炉渣重量    Slag weight   测试参数    单位：kg/h
     * @param slagCContent 炉渣含碳量  Slag carbon content  化验参数   单位：%
     * @param provideHeat  输给热量  Provide heat   计算参数  单位：kJ/h
     * @return java.lang.Double
     * @Date 10:46 2019/6/26
     * @description :  固体未完全燃烧热损失 Solid incomplete combustion heat loss  单位：%   原公式：32866 * param1 * param2 / param3
     **/
    public static Double solidICHeatLoss(String slagWeight, String slagCContent, Double provideHeat) {
        Double param1 = Double.parseDouble(slagWeight);
        Double param2 = Double.parseDouble(slagCContent);
        Double result = 32866 * param1 * param2 / provideHeat;
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param slagWeight    炉渣重量    Slag weight   测试参数    单位：kg/h
     * @param slagCContent  炉渣含碳量  Slag carbon content  化验参数   单位：%
     * @param fuelConsu     燃料消耗量 Fuel consumption  测试参数  单位：kg/h
     * @param coalCalorific 燃煤热值  Coal calorific value 化验参数   单位：kJ/kg
     * @param fuelABM       燃料应用基水分   Fuel application base moisture  测试参数    单位：%
     * @param coalBurnTemp  燃煤温度  Coal burning temperature   测试参数   单位：℃
     * @return java.lang.Double
     * @Date 10:46 2019/6/26
     * @description :  固体未完全燃烧热损失 Solid incomplete combustion heat loss  单位：%
     **/
    public static Double solidICHeatLoss(String slagWeight, String slagCContent, String fuelConsu, String coalCalorific, String fuelABM, String coalBurnTemp) {
        return solidICHeatLoss(slagWeight, slagCContent, provideHeat(fuelConsu, coalCalorific, fuelABM, coalBurnTemp));
    }

    /**
     * @param excessAirFactor  过剩空气系数    Excess air factor    计算参数   单位：α
     * @param exhaustCOContent 排烟处（CO)  Smoke exhaust CO content   测试参数    单位：%
     * @param solidICHeatLoss  固体未完全燃烧热损失 Solid incomplete combustion heat loss    计算参数  单位：%
     * @return java.lang.Double
     * @Date 14:41 2019/6/26
     * @description :    气体未完全燃烧热损失    Gas incomplete combustion heat loss  单位：%   原公式：3.2*param1*param2*(1-param3/100)
     **/
    public static Double gasICHeatLoss(Double excessAirFactor, String exhaustCOContent, Double solidICHeatLoss) {
        Double param2 = Double.parseDouble(exhaustCOContent);
//        Double result = 3.2*excessAirFactor*param2*(1-solidICHeatLoss/100);
        Double result = 3.2 * excessAirFactor * param2 * (100 - solidICHeatLoss) / 100;
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param excessAirFactor  过剩空气系数    Excess air factor    计算参数   单位：α
     * @param exhaustCOContent 排烟处（CO)  Smoke exhaust CO content   测试参数    单位：%
     * @param slagWeight       炉渣重量    Slag weight   测试参数    单位：kg/h
     * @param slagCContent     炉渣含碳量  Slag carbon content  化验参数   单位：%
     * @param fuelConsu        燃料消耗量 Fuel consumption  测试参数  单位：kg/h
     * @param coalCalorific    燃煤热值  Coal calorific value 化验参数   单位：kJ/kg
     * @param fuelABM          燃料应用基水分   Fuel application base moisture  测试参数    单位：%
     * @param coalBurnTemp     燃煤温度  Coal burning temperature   测试参数   单位：℃
     * @return java.lang.Double
     * @Date 14:41 2019/6/26
     * @description :    气体未完全燃烧热损失    Gas incomplete combustion heat loss  单位：%
     **/
    public static Double gasICHeatLoss(Double excessAirFactor, String exhaustCOContent, String slagWeight, String slagCContent, String fuelConsu, String coalCalorific, String fuelABM, String coalBurnTemp) {
        return gasICHeatLoss(excessAirFactor, exhaustCOContent, solidICHeatLoss(slagWeight, slagCContent, provideHeat(fuelConsu, coalCalorific, fuelABM, coalBurnTemp)));
    }

    /**
     * @param excessAirFactor 过剩空气系数    Excess air factor    计算参数   单位：α
     * @param exhaustTemp     排烟温度   exhaust temperature   测试参数   单位：℃
     * @param coldAirTemp     冷空气温度  Cold air temperature   测试参数   单位：℃
     * @param solidICHeatLoss 固体未完全燃烧热损失 Solid incomplete combustion heat loss   计算参数  单位：%
     * @return java.lang.Double
     * @Date 11:04 2019/6/26
     * @description :  排烟热损失 Exhaust heat loss    单位：%   原公式：(3.5*param1+0.5)*(param2-param3)/100*(1-param4/100)
     **/
    public static Double exhaustHeatLoss(Double excessAirFactor, String exhaustTemp, String coldAirTemp, Double solidICHeatLoss) {
        Double param2 = Double.parseDouble(exhaustTemp);
        Double param3 = Double.parseDouble(coldAirTemp);
//        Double result = (3.5*excessAirFactor+0.5)*(param2-param3)/100*(1-solidICHeatLoss/100);
        Double result = (3.5 * excessAirFactor + 0.5) * (param2 - param3) * (100 - solidICHeatLoss) / 10000;
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param excessAirFactor 过剩空气系数    Excess air factor    计算参数   单位：α
     * @param exhaustTemp     排烟温度   exhaust temperature   测试参数   单位：℃
     * @param coldAirTemp     冷空气温度  Cold air temperature   测试参数   单位：℃
     * @param slagWeight      炉渣重量    Slag weight   测试参数    单位：kg/h
     * @param slagCContent    炉渣含碳量  Slag carbon content  化验参数   单位：%
     * @param fuelConsu       燃料消耗量 Fuel consumption  测试参数  单位：kg/h
     * @param coalCalorific   燃煤热值  Coal calorific value 化验参数   单位：kJ/kg
     * @param fuelABM         燃料应用基水分   Fuel application base moisture  测试参数    单位：%
     * @param coalBurnTemp    燃煤温度  Coal burning temperature   测试参数   单位：℃
     * @return java.lang.Double
     * @Date 11:04 2019/6/26
     * @description :  排烟热损失 Exhaust heat loss    单位：%
     **/
    public static Double exhaustHeatLoss(Double excessAirFactor, String exhaustTemp, String coldAirTemp, String slagWeight, String slagCContent, String fuelConsu, String coalCalorific, String fuelABM, String coalBurnTemp) {
        return exhaustHeatLoss(excessAirFactor, exhaustTemp, coldAirTemp, solidICHeatLoss(slagWeight, slagCContent, provideHeat(fuelConsu, coalCalorific, fuelABM, coalBurnTemp)));
    }

    /**
     * @param heatLoadRate         热负荷率  Heat load rate    计算参数   单位：%
     * @param fuelConsu            燃料消耗量 Fuel consumption  测试参数  单位：kg/h
     * @param ratedHeatDissipation 额定散热   Rated heat dissipation  查表参数    单位：%
     * @return java.lang.Double
     * @Date 15:33 2019/6/26
     * @description :  散热损失  Heat dissipation   单位：%   原公式：IF(param1>=75,param2,IF(param1>=30,param3/param1*100,param3/0.3))
     **/
    public static Double heatDissipation(Double heatLoadRate, String fuelConsu, String ratedHeatDissipation) {
        Double param2 = Double.parseDouble(fuelConsu);
        Double param3 = Double.parseDouble(ratedHeatDissipation);
        Double result = 0.0;
        if (heatLoadRate >= 75) {
            result = param2;
        } else if (heatLoadRate >= 30) {
            result = param3 / heatLoadRate * 100;
        } else {
            result = param3 / 3 * 10;
        }
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param fuelConsu            燃料消耗量 Fuel consumption  测试参数  单位：kg/h
     * @param ratedHeatDissipation 额定散热   Rated heat dissipation  查表参数    单位：%
     * @param outletTemp           出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp            入口温度 Inlet temperature   测试参数    单位：℃
     * @param waterContent         含水率  Water content ratio 测试参数    单位：%
     * @param mediaFlow            介质流量 Media flow  测试参数    单位：m3/h
     * @param crudeOilTemp         流量测定处原油温度  Crude oil temperature at flow measurement 测试参数    单位：℃
     * @param furnaceCapacity      加热炉容量 Furnace capacity   现场录入  单位：MW
     * @return java.lang.Double
     * @Date 15:33 2019/6/26
     * @description :  散热损失  Heat dissipation   单位：%
     **/
    public static Double heatDissipation(String fuelConsu, String ratedHeatDissipation, String outletTemp, String inletTemp, String waterContent, String mediaFlow, String crudeOilTemp, String furnaceCapacity) {
        return heatDissipation(heatLoadRate(effectOutHeat(outletTemp, inletTemp, waterContent, mediaFlow, crudeOilTemp), furnaceCapacity), fuelConsu, ratedHeatDissipation);
    }

    /**
     * @param slagPhysicalHeatLoss 炉渣物理热损失   Slag physical heat loss    计算参数   单位：%
     * @param exhaustHeatLoss      排烟热损失 Exhaust heat loss    计算参数   单位：%
     * @param solidICHeatLoss      固体未完全燃烧热损失 Solid incomplete combustion heat loss    计算参数   单位：%
     * @param gasICHeatLoss        气体未完全燃烧热损失    Gas incomplete combustion heat loss    计算参数   单位：%
     * @param heatDissipation      散热损失  Heat dissipation    计算参数   单位：%
     * @return java.lang.Double
     * @Date 16:01 2019/6/26
     * @description :  各项热损失之和   The sum of heat loss    单位：%   原公式：param1+param2+param3+param4+param5
     **/
    public static Double heatLossSum(Double slagPhysicalHeatLoss, Double exhaustHeatLoss, Double solidICHeatLoss, Double gasICHeatLoss, Double heatDissipation) {
        return slagPhysicalHeatLoss + exhaustHeatLoss + solidICHeatLoss + gasICHeatLoss + heatDissipation;
    }

    /**
     * @param ratedHeatDissipation 额定散热   Rated heat dissipation  查表参数    单位：%
     * @param furnaceCapacity      加热炉容量 Furnace capacity   现场录入  单位：MW
     * @param slagWeight           炉渣重量    Slag weight   测试参数    单位：kg/h
     * @param productOfSHAndST     炉渣比热与温度乘积    The product of specific heat and slag temperature   查表参数    单位：kJ/kg
     * @param fuelConsu            燃料消耗量 Fuel consumption  测试参数  单位：kg/h
     * @param coalCalorific        燃煤热值  Coal calorific value 化验参数   单位：kJ/kg
     * @param slagCContent         炉渣含碳量  Slag carbon content  化验参数   单位：%
     * @param fuelABM              燃料应用基水分   Fuel application base moisture  测试参数    单位：%
     * @param waterContent         含水率  Water content ratio 测试参数    单位：%
     * @param mediaFlow            介质流量 Media flow  测试参数    单位：m3/h
     * @param coalBurnTemp         燃煤温度  Coal burning temperature   测试参数   单位：℃
     * @param outletTemp           出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp            入口温度 Inlet temperature   测试参数    单位：℃
     * @param crudeOilTemp         流量测定处原油温度  Crude oil temperature at flow measurement 测试参数    单位：℃
     * @param coldAirTemp          冷空气温度  Cold air temperature   测试参数   单位：℃
     * @param exhaustTemp          排烟温度   exhaust temperature   测试参数   单位：℃
     * @param exhaustCOContent     排烟处（CO)  Smoke exhaust CO content   测试参数    单位：%
     * @param excessAirFactor      过剩空气系数    Excess air factor    计算参数   单位：α
     * @return java.lang.Double
     * @Date 16:22 2019/6/26
     * @description :  各项热损失之和   The sum of heat loss    单位：%
     **/
    public static Double heatLossSum(String ratedHeatDissipation, String furnaceCapacity, String slagWeight, String productOfSHAndST,
                                     String fuelConsu, String coalCalorific, String slagCContent, String fuelABM, String waterContent,
                                     String mediaFlow, String coalBurnTemp, String outletTemp, String inletTemp, String crudeOilTemp,
                                     String coldAirTemp, String exhaustTemp, String exhaustCOContent, Double excessAirFactor) {
        Double provideHeat = provideHeat(fuelConsu, coalCalorific, fuelABM, coalBurnTemp);
        Double effectOutHeat = effectOutHeat(outletTemp, inletTemp, waterContent, mediaFlow, crudeOilTemp);
        Double slagPhysicalHeatLoss = slagPhysicalHeatLoss(slagWeight, productOfSHAndST, provideHeat);
        Double solidICHeatLoss = solidICHeatLoss(slagWeight, slagCContent, provideHeat);
        Double gasICHeatLoss = gasICHeatLoss(excessAirFactor, exhaustCOContent, solidICHeatLoss);
        Double exhaustHeatLoss = exhaustHeatLoss(excessAirFactor, exhaustTemp, coldAirTemp, solidICHeatLoss);
        Double heatDissipation = heatDissipation(heatLoadRate(effectOutHeat, furnaceCapacity), fuelConsu, ratedHeatDissipation);
        return heatLossSum(slagPhysicalHeatLoss, exhaustHeatLoss, solidICHeatLoss, gasICHeatLoss, heatDissipation);
    }

    /**
     * @param heatLossSum 各项热损失之和   The sum of heat loss    计算参数   单位：%
     * @return java.lang.Double
     * @Date 17:30 2019/6/26
     * @description :     反平衡效率 Reverse balance thermal efficiency    字段名：Cbalance_efic    单位：%     原公式：100-param
     **/
    public static Double reversePalanceEffic(Double heatLossSum) {
        return 100 - heatLossSum;
    }

    /**
     * @param ratedHeatDissipation 额定散热   Rated heat dissipation  查表参数    单位：%
     * @param furnaceCapacity      加热炉容量 Furnace capacity   现场录入  单位：MW
     * @param slagWeight           炉渣重量    Slag weight   测试参数    单位：kg/h
     * @param productOfSHAndST     炉渣比热与温度乘积    The product of specific heat and slag temperature   查表参数    单位：kJ/kg
     * @param fuelConsu            燃料消耗量 Fuel consumption  测试参数  单位：kg/h
     * @param coalCalorific        燃煤热值  Coal calorific value 化验参数   单位：kJ/kg
     * @param slagCContent         炉渣含碳量  Slag carbon content  化验参数   单位：%
     * @param fuelABM              燃料应用基水分   Fuel application base moisture  测试参数    单位：%
     * @param waterContent         含水率  Water content ratio 测试参数    单位：%
     * @param mediaFlow            介质流量 Media flow  测试参数    单位：m3/h
     * @param coalBurnTemp         燃煤温度  Coal burning temperature   测试参数   单位：℃
     * @param outletTemp           出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp            入口温度 Inlet temperature   测试参数    单位：℃
     * @param crudeOilTemp         流量测定处原油温度  Crude oil temperature at flow measurement 测试参数    单位：℃
     * @param coldAirTemp          冷空气温度  Cold air temperature   测试参数   单位：℃
     * @param exhaustTemp          排烟温度   exhaust temperature   测试参数   单位：℃
     * @param exhaustCOContent     排烟处（CO)  Smoke exhaust CO content   测试参数    单位：%
     * @param excessAirFactor      过剩空气系数    Excess air factor    计算参数   单位：α
     * @return java.lang.Double
     * @Date 17:30 2019/6/26
     * @description :     反平衡效率 Reverse balance thermal efficiency    字段名：Cbalance_efic    单位：%
     **/
    public static Double reversePalanceEffic(String ratedHeatDissipation, String furnaceCapacity, String slagWeight, String productOfSHAndST,
                                             String fuelConsu, String coalCalorific, String slagCContent, String fuelABM, String waterContent,
                                             String mediaFlow, String coalBurnTemp, String outletTemp, String inletTemp, String crudeOilTemp,
                                             String coldAirTemp, String exhaustTemp, String exhaustCOContent, Double excessAirFactor) {
        Double heatLossSum = heatLossSum(ratedHeatDissipation, furnaceCapacity, slagWeight, productOfSHAndST, fuelConsu, coalCalorific, slagCContent, fuelABM,
                waterContent, mediaFlow, coalBurnTemp, outletTemp, inletTemp, crudeOilTemp, coldAirTemp, exhaustTemp,exhaustCOContent, excessAirFactor);
        return reversePalanceEffic(heatLossSum);
    }

    /**
     * @param positPalanceEffic   正平衡效率 Positive balance thermal efficiency    单位：%
     * @param reversePalanceEffic 反平衡效率 Reverse balance thermal efficiency  单位：%
     * @return java.lang.Double
     * @Date 9:36 2019/6/27
     * @description :  加热炉热效率    Furnace thermal efficiency      单位：%    原公式：(param1+param2)/2
     **/
    public static Double furnaceEfficiency(Double positPalanceEffic, Double reversePalanceEffic) {
        Double result = (positPalanceEffic + reversePalanceEffic) / 2;
        return DoubleUtil.round(result, 2);
    }

    /**
     * @param ratedHeatDissipation 额定散热   Rated heat dissipation  查表参数    单位：%
     * @param furnaceCapacity      加热炉容量 Furnace capacity   现场录入  单位：MW
     * @param slagWeight           炉渣重量    Slag weight   测试参数    单位：kg/h
     * @param productOfSHAndST     炉渣比热与温度乘积    The product of specific heat and slag temperature   查表参数    单位：kJ/kg
     * @param fuelConsu            燃料消耗量 Fuel consumption  测试参数  单位：kg/h
     * @param coalCalorific        燃煤热值  Coal calorific value 化验参数   单位：kJ/kg
     * @param slagCContent         炉渣含碳量  Slag carbon content  化验参数   单位：%
     * @param fuelABM              燃料应用基水分   Fuel application base moisture  测试参数    单位：%
     * @param waterContent         含水率  Water content ratio 测试参数    单位：%
     * @param mediaFlow            介质流量 Media flow  测试参数    单位：m3/h
     * @param coalBurnTemp         燃煤温度  Coal burning temperature   测试参数   单位：℃
     * @param outletTemp           出口温度 Outlet temperature  测试参数    单位：℃
     * @param inletTemp            入口温度 Inlet temperature   测试参数    单位：℃
     * @param crudeOilTemp         流量测定处原油温度  Crude oil temperature at flow measurement 测试参数    单位：℃
     * @param coldAirTemp          冷空气温度  Cold air temperature   测试参数   单位：℃
     * @param exhaustTemp          排烟温度   exhaust temperature   测试参数   单位：℃
     * @param exhaustCOContent     排烟处（CO)  Smoke exhaust CO content   测试参数    单位：%
     * @param excessAirFactor      过剩空气系数    Excess air factor    计算参数   单位：α
     * @return java.lang.Double
     * @Date 9:36 2019/6/27
     * @description :  加热炉热效率    Furnace thermal efficiency      单位：%    原公式：(param1+param2)/2
     **/
    public static Double furnaceEfficiency(String ratedHeatDissipation, String furnaceCapacity, String slagWeight, String productOfSHAndST,
                                           String fuelConsu, String coalCalorific, String slagCContent, String fuelABM, String waterContent,
                                           String mediaFlow, String coalBurnTemp, String outletTemp, String inletTemp, String crudeOilTemp,
                                           String coldAirTemp, String exhaustTemp, String exhaustCOContent, Double excessAirFactor) {
        Double waterEffectOutputsHeat = waterEffectOutputsHeat(outletTemp, inletTemp, mediaFlow, waterContent);
        Double oilEffectOutputsHeat = oilEffectOutputsHeat(outletTemp, inletTemp, waterContent, mediaFlow, crudeOilTemp);
        Double positPalanceEffic = positPalanceEffic(mediaFlow, outletTemp, inletTemp, waterEffectOutputsHeat, oilEffectOutputsHeat, coalCalorific, fuelPhysicsHeat(fuelABM, coalBurnTemp), fuelConsu);

        Double reversePalanceEffic = reversePalanceEffic(ratedHeatDissipation, furnaceCapacity, slagWeight, productOfSHAndST, fuelConsu, coalCalorific, slagCContent, fuelABM,
                waterContent, mediaFlow, coalBurnTemp, outletTemp, inletTemp, crudeOilTemp, coldAirTemp, exhaustTemp, exhaustCOContent, excessAirFactor);
        return furnaceEfficiency(positPalanceEffic,reversePalanceEffic);
    }
}

