package com.petrochina.e7.monitor.commons.utils;

/**
 * @author : YaoDong
 * @create : 2019-07-01 09:19
 * @description :   燃气加热炉计算公式工具类
 **/
public class GasHeatingFurnaceFormula {

    private static final Double PARAM1 = 4.1868;

    //TODO: 这里采用经验公式，标准6381里是查表所得

    /**
     * @param waterTemp 水温度   water temperature  ℃
     * @return java.lang.Double
     * @Date 9:32 2019/7/1
     * @description :  水焓值  Water enthalpy  kJ/kg   公式：(param + 0.1) * 4.1868
     **/
    public static Double waterEnthalpy(String waterTemp) {
        Double result = null;
        if (waterTemp.length() != 0 && waterTemp != null) {
            Double param = Double.parseDouble(waterTemp);
            result = (param + 0.1) * PARAM1;
            return DoubleUtil.round(result, 3);
        } else {
            return null;
        }
    }

    //TODO: 锅炉出力=标准里的被加热介质为水的情况下有效输出热量=运行负荷?????        问领导

    /**
     * @param mediaFlow        介质流量  Boiler output  m3/h
     * @param drainageEnthalpy 出水焓值  Drainage enthalpy   kJ/kg
     * @param influentEnthalpy 入水焓值  Influent enthalpy   kJ/kg
     * @return java.lang.Double
     * @Date 10:15 2019/7/1
     * @description :  锅炉出力  Boiler output   kJ/h    公式：param1*1000*(param2-param3)
     **/
    public static Double boilerOutput(String mediaFlow, Double drainageEnthalpy, Double influentEnthalpy) {
        if (mediaFlow.length() != 0 && mediaFlow != null && drainageEnthalpy.toString().length() != 0 && drainageEnthalpy != null && influentEnthalpy.toString().length() != 0 && influentEnthalpy != null) {
            Double param1 = Double.parseDouble(mediaFlow);
            Double result = param1 * (drainageEnthalpy - influentEnthalpy) * 1000;
            return DoubleUtil.round(result, 3);
        } else {
            return null;
        }
    }

    /**
     * @param mediaFlow    介质流量  Boiler output  m3/h
     * @param drainageTemp 出水温度  Drainage temperature   ℃
     * @param influentTemp 入水温度  Influent temperature   ℃
     * @return java.lang.Double
     * @Date 10:15 2019/7/1
     * @description :  锅炉出力  Boiler output   kJ/h    可能优化公式(未使用)：4186.8 * param1 * (param2 - param3)
     **/
    public static Double boilerOutput(String mediaFlow, String drainageTemp, String influentTemp) {
        if (mediaFlow != null && mediaFlow.length() != 0 && drainageTemp != null && drainageTemp.length() != 0 && influentTemp != null && influentTemp.length() != 0) {
            return boilerOutput(mediaFlow, waterEnthalpy(drainageTemp), waterEnthalpy(influentTemp));
        } else {
            return null;
        }
    }

    //TODO: 燃料比热容   标准里是计算参数，编号46   问领导

    /**
     * @param fuelTemp         燃料温度     Fuel temperature    测试参数    ℃
     * @param fuelSpecificHeat 燃料比热容    Fuel specific heat 化验参数    kJ/kg ·℃
     * @return java.lang.Double
     * @Date 14:16 2019/7/1
     * @description :  燃料物理热     Fuel physics heat   kJ/kg    公式：param1 * param2
     **/
    public static Double fuelPhysicsHeat(String fuelTemp, String fuelSpecificHeat) {
        if(fuelTemp != null && fuelTemp.length() != 0 && fuelSpecificHeat != null && fuelSpecificHeat.length() != 0 ) {
            Double param1 = Double.parseDouble(fuelTemp);
            Double param2 = Double.parseDouble(fuelSpecificHeat);
            Double result = param1 * param2;
            return DoubleUtil.round(result, 3);
        }else {
            return null;
        }
    }

    //TODO:少参数  标准：（燃料物理热 + 燃料收到基低位发热量 + 标准情况下每立方米燃料所给热量） * 燃料消耗量       问领导

    /**
     * @param fuelPhysicsHeat       燃料物理热     Fuel physics heat   计算参数   kJ/kg
     * @param lowFuelCalorificValue 燃料低位发热值   Low fuel calorific value    化验数据    kJ/m3
     * @param gasVolume             燃气量   Gas volume  测试数据  m3/h
     * @return java.lang.Double
     * @Date 15:30 2019/7/1
     * @description :  输入热量  Input heat  kJ/h    公式：(R5+S5)*M5
     **/
    public static Double inputHeat(Double fuelPhysicsHeat, String lowFuelCalorificValue, String gasVolume) {
        if(fuelPhysicsHeat != null && fuelPhysicsHeat.toString().length() != 0 && lowFuelCalorificValue != null && lowFuelCalorificValue.length() != 0 && gasVolume != null && gasVolume.length() != 0){
            Double param2 = Double.parseDouble(lowFuelCalorificValue);
            Double param3 = Double.parseDouble(gasVolume);
            Double result = (fuelPhysicsHeat + param2) * param3;
            return DoubleUtil.round(result, 3);
        }else {
            return null;
        }
    }

    /**
     * @param fuelTemp              燃料温度     Fuel temperature    测试参数    ℃
     * @param fuelSpecificHeat      燃料比热    Fuel specific heat 化验参数    kJ/kg ·℃
     * @param lowFuelCalorificValue 燃料低位发热值   Low fuel calorific value    化验数据    kJ/m3
     * @param gasVolume             燃气量   Gas volume  测试数据  m3/h
     * @return java.lang.Double
     * @Date 15:30 2019/7/1
     * @description :  输入热量  Input heat  kJ/h
     **/
    public static Double inputHeat(String fuelTemp, String fuelSpecificHeat, String lowFuelCalorificValue, String gasVolume) {
        if (fuelTemp != null && fuelTemp.length() > 0 && lowFuelCalorificValue != null && lowFuelCalorificValue.length() > 0 && gasVolume != null && gasVolume.length() > 0) {
            return inputHeat(fuelPhysicsHeat(fuelTemp, fuelSpecificHeat), lowFuelCalorificValue, gasVolume);
        } else {
            return null;
        }
    }

    /**
     * @param boilerOutput 锅炉出力   计算参数   Boiler output   kJ/h
     * @param inputHeat    输入热量   计算参数   Input heat  kJ/h
     * @return java.lang.Double
     * @Date 16:18 2019/7/1
     * @description :  正平衡效率  Positive balance efficiency    %   公式：param1 / param2 * 100
     **/
    public static Double positiveBalanceEffic(Double boilerOutput, Double inputHeat) {
        if (boilerOutput != null && boilerOutput.toString().length() > 0 && inputHeat != null && inputHeat.toString().length() > 0) {
            Double result = boilerOutput / inputHeat * 100;
            return DoubleUtil.round(result, 2);
        } else {
            return null;
        }
    }

    /**
     * @param mediaFlow             介质流量  Boiler output    测试参数    m3/h
     * @param drainageTemp          出水温度  Drainage temperature    测试参数    ℃
     * @param influentTemp          入水温度  Influent temperature    测试参数    ℃
     * @param fuelTemp              燃料温度     Fuel temperature    测试参数    ℃
     * @param fuelSpecificHeat      燃料比热容    Fuel specific heat 化验参数    kJ/kg ·℃
     * @param lowFuelCalorificValue 燃料低位发热值   Low fuel calorific value    化验数据    kJ/m3
     * @param gasVolume             燃气量   Gas volume  测试数据  m3/h
     * @return java.lang.Double
     * @Date 16:22 2019/7/1
     * @description :   正平衡效率  Positive balance efficiency    %
     **/
    public static Double positiveBalanceEffic(String mediaFlow, String drainageTemp, String influentTemp, String fuelTemp, String fuelSpecificHeat, String lowFuelCalorificValue, String gasVolume) {
        if (mediaFlow != null && mediaFlow.length() != 0 && drainageTemp != null && drainageTemp.length() != 0 && influentTemp != null && influentTemp.length() != 0 && fuelTemp != null && fuelTemp.length() != 0 && fuelSpecificHeat != null && fuelSpecificHeat.length() != 0 && lowFuelCalorificValue != null && lowFuelCalorificValue.length() != 0 && gasVolume != null && gasVolume.length() != 0) {
            return positiveBalanceEffic(boilerOutput(mediaFlow, drainageTemp, influentTemp), inputHeat(fuelTemp, fuelSpecificHeat, lowFuelCalorificValue, gasVolume));
        } else {
            return null;
        }
    }

    //

    /**
     * @param boilerOutput  锅炉出力  Boiler output   计算参数   kJ/h
     * @param ratedCapacity 额定容量  Rated Capacity    现场录入    MW
     * @return java.lang.Double
     * @Date 16:44 2019/7/1
     * @description :  负荷率  Load rate    %    公式：param1 / (param2 * 3600000) * 100  TODO:公式来源是别的标准
     **/
    public static Double loadRate(Double boilerOutput, String ratedCapacity) {
        if (boilerOutput != null && boilerOutput.toString().length() != 0 && ratedCapacity != null && ratedCapacity.length() != 0) {
            Double param2 = Double.parseDouble(ratedCapacity);
//        Double result = boilerOutput / (param2 * 3600000) * 100;
            Double result = boilerOutput / (param2 * 36000);
            return DoubleUtil.round(result, 2);
        } else {
            return null;
        }
    }

    /**
     * @param mediaFlow     介质流量  Boiler output    测试参数    m3/h
     * @param drainageTemp  出水温度  Drainage temperature    测试参数    ℃
     * @param influentTemp  入水温度  Influent temperature    测试参数    ℃
     * @param ratedCapacity 额定容量  Rated Capacity    现场录入    MW
     * @return java.lang.Double
     * @Date 16:46 2019/7/1
     * @description :  负荷率  Load rate    %
     **/
    public static Double loadRate(String mediaFlow, String drainageTemp, String influentTemp, String ratedCapacity) {
        if (mediaFlow != null && mediaFlow.length() != 0 && drainageTemp != null && drainageTemp.length() != 0 && influentTemp != null && influentTemp.length() != 0 && ratedCapacity != null && ratedCapacity.length() != 0) {
            return loadRate(boilerOutput(mediaFlow, drainageTemp, influentTemp), ratedCapacity);
        } else {
            return null;
        }
    }

    //TODO:收到基不饱和烃CmHn与C分子数m乘积总和 需不需要做成多个自由传输自动计算

    /**
     * @param exhaustO2Content     排烟处O2   测试数据    %
     * @param exhaustCOContent     排烟处CO   测试数据    %
     * @param exhaustH2Content     排烟处H2   测试数据    %
     * @param exhaustCH4Content    排烟处CH4  测试数据    %
     * @param exhaustN2Content     排烟处N2   测试数据    %
     * @param receivedBaseN2       收到基N2   测试数据    %
     * @param exhaustRO2Content    排烟处RO2  测试数据    %
     * @param receivedBaseCO2      收到基CO2  测试数据    %
     * @param receivedBaseCO       收到基CO   测试数据    %
     * @param sumOfReceivedCmHnBym 所有收到基不饱和烃CmHn与其所对应单个分子所含C原子个数m乘积的总和     计算参数
     * @param receivedBaseH2S      收到基H2S  测试数据    %
     * @return java.lang.Double
     * @Date 16:15 2019/7/4
     * @description :  空气系数  Air coefficient     α   公式：21/(21-79*(排烟处O2-0.5*排烟处CO-0.5*排烟处H2-2*排烟处CH4)/(排烟处N2-((收到基N2*(排烟处RO2+排烟处CO+排烟处CH4))/(收到基CO2+收到基CO+收到基不饱和烃CmHn与C分子数m乘积总和+收到基H2S))))
     **/
    public static Double airCoefficient(String exhaustO2Content, String exhaustCOContent, String exhaustH2Content, String exhaustCH4Content, String exhaustN2Content, String receivedBaseN2, String exhaustRO2Content, String receivedBaseCO2, String receivedBaseCO, Long sumOfReceivedCmHnBym, String receivedBaseH2S) {
        if (exhaustO2Content != null && exhaustO2Content.length() != 0 && exhaustCOContent != null && exhaustCOContent.length() != 0 && exhaustCH4Content != null && exhaustCH4Content.length() != 0 && exhaustN2Content != null && exhaustN2Content.length() != 0 && receivedBaseN2 != null && receivedBaseN2.length() != 0 &&
                exhaustRO2Content != null && exhaustRO2Content.length() != 0 && receivedBaseCO2 != null && receivedBaseCO2.length() != 0 && receivedBaseCO != null && receivedBaseCO.length() != 0 && sumOfReceivedCmHnBym != null && sumOfReceivedCmHnBym.toString().length() != 0 && receivedBaseH2S != null && receivedBaseH2S.length() != 0) {
            Double outO2 = Double.parseDouble(exhaustO2Content);
            Double outCO = Double.parseDouble(exhaustCOContent);
            Double outH2 = Double.parseDouble(exhaustH2Content);
            Double outCH4 = Double.parseDouble(exhaustCH4Content);
            Double outN2 = Double.parseDouble(exhaustN2Content);
            Double outRO2 = Double.parseDouble(exhaustRO2Content);
            Double inN2 = Double.parseDouble(receivedBaseN2);
            Double inCO2 = Double.parseDouble(receivedBaseCO2);
            Double inCO = Double.parseDouble(receivedBaseCO);
            Double inH2S = Double.parseDouble(receivedBaseH2S);
            Double result = 21 / (21 - 79 * (outO2 - 0.5 * outCO - 0.5 * outH2 - 2 * outCH4) / (outN2 - ((inN2 * (outRO2 + outCO + outCH4)) / (inCO2 + inCO + sumOfReceivedCmHnBym + inH2S))));
            return DoubleUtil.round(result, 3);
        } else {
            return null;
        }
    }

    /**
     * @param exhaustO2Content  排烟处（O2)  Smoke exhaust O2 content   测试参数    %
     * @param exhaustCOContent  排烟处（CO)  Smoke exhaust CO content   测试参数    %
     * @param exhaustRO2Content 排烟处（RO2) Smoke exhaust RO2 content   测试参数    %
     * @return java.lang.Double
     * @Date 10:13 2019/7/4
     * @description :  空气系数  Air coefficient     α   公式：21/(21-79*((param1-0.5*param2)/(100-param1-param3-param2)))
     **/
    public static Double airCoefficient(String exhaustO2Content, String exhaustCOContent, String exhaustRO2Content) {
        if (exhaustO2Content != null && exhaustO2Content.length() != 0 && exhaustCOContent != null && exhaustCOContent.length() != 0 && exhaustRO2Content != null && exhaustRO2Content.length() != 0) {
            Double param1 = Double.parseDouble(exhaustO2Content);
            Double param2 = Double.parseDouble(exhaustCOContent);
            Double param3 = Double.parseDouble(exhaustRO2Content);
            Double result = 21 / (21 - 79 * (param1 - 0.5 * param2) / (100 - param1 - param2 - param3));
            return DoubleUtil.round(result, 3);
        } else {
            return null;
        }
    }

    /**
     * @param airCoefficient 空气系数  Air coefficient   计算参数    α
     * @param exhaustTemp    排烟温度   exhaust temperature  测试参数   ℃
     * @param airTemp        空气温度    Air temperature     测试参数   ℃
     * @return java.lang.Double
     * @Date 10:27 2019/7/4
     * @description :  排烟热损失    Exhaust heat loss    %   公式：(3.5*param1+0.5)*((param2-param3)/100)*(1-0/100)
     **/
    public static Double exhaustHeatLoss(Double airCoefficient, String exhaustTemp, String airTemp) {
        if (airCoefficient != null && airCoefficient.toString().length() != 0 && exhaustTemp != null && exhaustTemp.length() != 0 && airTemp != null && airTemp.length() != 0) {
            Double param2 = Double.parseDouble(exhaustTemp);
            Double param3 = Double.parseDouble(airTemp);
            Double result = (3.5 * airCoefficient + 0.5) * (param2 - param3) / 100;
            return DoubleUtil.round(result, 2);
        } else {
            return null;
        }
    }

    /**
     * @param airCoefficient   空气系数  Air coefficient   计算参数    α
     * @param exhaustCOContent 排烟处（CO)  Smoke exhaust CO content   测试参数    %
     * @return java.lang.Double
     * @Date 10:38 2019/7/4
     * @description :  未完全燃烧热损失    incomplete combustion heat loss  %    公式：3.2*param1*param2*(1-0/100)
     **/
    public static Double incompleteCombustionHeatLoss(Double airCoefficient, String exhaustCOContent) {
        if (airCoefficient != null && airCoefficient.toString().length() != 0 && exhaustCOContent != null && exhaustCOContent.length() != 0) {
            Double param2 = Double.parseDouble(exhaustCOContent);
            Double result = 3.2 * airCoefficient * param2;
            return DoubleUtil.round(result, 2);
        } else {
            return null;
        }
    }

    /**
     * @param loadRate             负荷率  Load rate   计算参数    %
     * @param ratedHeatDissipation 额定散热   Rated heat dissipation  查表参数    %
     * @return java.lang.Double
     * @Date 10:56 2019/7/4
     * @description :  散热损失  Heat dissipation   %   公式：1/param1*100*param2
     **/
    public static Double heatDissipation(Double loadRate, String ratedHeatDissipation) {
        if(loadRate != null && loadRate.toString().length() != 0 && ratedHeatDissipation != null && ratedHeatDissipation.length() != 0) {
            Double param2 = Double.parseDouble(ratedHeatDissipation);
            Double result = 100 * param2 / loadRate;
            return DoubleUtil.round(result, 2);
        }else {
            return null;
        }
    }

    /**
     * @param mediaFlow            介质流量  Boiler output    测试参数    m3/h
     * @param drainageTemp         出水温度  Drainage temperature    测试参数    ℃
     * @param influentTemp         入水温度  Influent temperature    测试参数    ℃
     * @param ratedCapacity        额定容量  Rated Capacity    现场录入    MW
     * @param ratedHeatDissipation 额定散热   Rated heat dissipation  现场录入    %
     * @return java.lang.Double
     * @Date 10:59 2019/7/4
     * @description :  散热损失  Heat dissipation   %
     **/
    public static Double heatDissipation(String mediaFlow, String drainageTemp, String influentTemp, String ratedCapacity, String ratedHeatDissipation) {
        if (mediaFlow != null && mediaFlow.length() != 0 && drainageTemp != null && drainageTemp.length() != 0 && ratedCapacity != null && ratedCapacity.length() != 0 && ratedHeatDissipation != null && ratedHeatDissipation.length() != 0) {
            return heatDissipation(loadRate(mediaFlow, drainageTemp, influentTemp, ratedCapacity), ratedHeatDissipation);
        } else {
            return null;
        }
    }

    /**
     * @param heatDissipation              散热损失  Heat dissipation   计算参数   %
     * @param incompleteCombustionHeatLoss 未完全燃烧热损失    incomplete combustion heat loss   计算参数  %
     * @param exhaustHeatLoss              排烟热损失    Exhaust heat loss   计算参数    %
     * @return java.lang.Double
     * @Date 11:05 2019/7/4
     * @description :  各项热损失之和   The sum of heat loss    %   原公式：param1+param2+param3
     **/
    public static Double heatLossSum(Double heatDissipation, Double incompleteCombustionHeatLoss, Double exhaustHeatLoss) {
        if (heatDissipation != null && heatDissipation.toString().length() != 0 && incompleteCombustionHeatLoss != null && incompleteCombustionHeatLoss.toString().length() != 0 && exhaustHeatLoss != null && exhaustHeatLoss.toString().length() != 0) {
            Double result = heatDissipation + incompleteCombustionHeatLoss + exhaustHeatLoss;
            return DoubleUtil.round(result, 2);
        } else {
            return null;
        }
    }

    /**
     * @param mediaFlow            介质流量  Boiler output    测试参数    m3/h
     * @param drainageTemp         出水温度  Drainage temperature    测试参数    ℃
     * @param influentTemp         入水温度  Influent temperature    测试参数    ℃
     * @param ratedCapacity        额定容量  Rated Capacity    现场录入    MW
     * @param ratedHeatDissipation 额定散热   Rated heat dissipation  查表参数    %
     * @param airCoefficient       空气系数  Air coefficient   计算参数    α
     * @param exhaustCOContent     排烟处（CO)  Smoke exhaust CO content   测试参数    %
     * @param exhaustTemp          排烟温度    exhaust temperature     测试参数   ℃
     * @param airTemp              空气温度    Air temperature     测试参数   ℃
     * @return java.lang.Double
     * @Date 11:12 2019/7/4
     * @description :   各项热损失之和   The sum of heat loss    %(各项热损失之和公式有问题???)
     **/
    public static Double heatLossSum(String mediaFlow, String drainageTemp, String influentTemp, String ratedCapacity, String ratedHeatDissipation, Double airCoefficient, String exhaustCOContent, String exhaustTemp, String airTemp) {
        if (mediaFlow != null && mediaFlow.length() != 0 && drainageTemp != null && drainageTemp.length() != 0 && influentTemp != null && influentTemp.length() != 0 && ratedCapacity != null && ratedCapacity.length() != 0 &&
                ratedHeatDissipation != null && ratedHeatDissipation.length() != 0 && airCoefficient != null && airCoefficient.toString().length() != 0 && exhaustCOContent != null && exhaustCOContent.length() != 0 && exhaustTemp != null && exhaustTemp.length() != 0 && airTemp != null && airTemp.length() != 0) {
            return heatLossSum(heatDissipation(mediaFlow, drainageTemp, influentTemp, ratedCapacity, ratedHeatDissipation), incompleteCombustionHeatLoss(airCoefficient, exhaustCOContent), exhaustHeatLoss(airCoefficient, exhaustTemp, airTemp));
        } else {
            return null;
        }
    }

    /**
     * @param heatLossSum 各项热损失之和   The sum of heat loss    计算参数   %
     * @return java.lang.Double
     * @Date 17:30 2019/6/26
     * @description :   反平衡效率 Reverse balance thermal efficiency    %     公式：100-param
     **/
    public static Double reverseBalanceEffic(Double heatLossSum) {
        if(heatLossSum != null && heatLossSum.toString().length() != 0) {
            return 100 - heatLossSum;
        }else {
            return null;
        }
    }

    /**
     * @param mediaFlow            介质流量  Boiler output    测试参数    m3/h
     * @param drainageTemp         出水温度  Drainage temperature    测试参数    ℃
     * @param influentTemp         入水温度  Influent temperature    测试参数    ℃
     * @param ratedCapacity        额定容量  Rated Capacity    现场录入    MW
     * @param ratedHeatDissipation 额定散热   Rated heat dissipation  查表参数    %
     * @param airCoefficient       空气系数  Air coefficient   计算参数    α
     * @param exhaustCOContent     排烟处（CO)  Smoke exhaust CO content   测试参数    %
     * @param exhaustTemp          排烟温度    exhaust temperature     测试参数   ℃
     * @param airTemp              空气温度    Air temperature     测试参数   ℃
     * @return java.lang.Double
     * @Date 11:12 2019/7/4
     * @description :   反平衡效率 Reverse balance thermal efficiency    %(公式有问题???)
     **/
    public static Double reverseBalanceEffic(String mediaFlow, String drainageTemp, String influentTemp, String ratedCapacity, String ratedHeatDissipation, Double airCoefficient, String exhaustCOContent, String exhaustTemp, String airTemp) {
        if (mediaFlow != null && mediaFlow.length() != 0 && drainageTemp != null && drainageTemp.length() != 0 && influentTemp != null && influentTemp.length() != 0 && ratedCapacity != null && ratedCapacity.length() != 0 && ratedHeatDissipation != null && ratedHeatDissipation.length() != 0 &&
                airCoefficient != null && airCoefficient.toString().length() != 0 && exhaustCOContent != null && exhaustCOContent.length() != 0 && exhaustTemp != null && exhaustTemp.length() != 0 && airTemp != null && airTemp.length() != 0) {
            return reverseBalanceEffic(heatLossSum(mediaFlow, drainageTemp, influentTemp, ratedCapacity, ratedHeatDissipation, airCoefficient, exhaustCOContent, exhaustTemp, airTemp));
        } else {
            return null;
        }
    }

    /**
     * @param positiveBalanceEffic 正平衡效率 Positive balance thermal efficiency    单位：%
     * @param reverseBalanceEffic  反平衡效率 Reverse balance thermal efficiency  单位：%
     * @return java.lang.Double
     * @Date 9:20 2019/7/12
     * @description :    加热炉热效率    Furnace thermal efficiency      %    公式：(param1+param2)/2
     **/
    public static Double furnaceEfficiency(Double positiveBalanceEffic, Double reverseBalanceEffic) {
        if (positiveBalanceEffic != null && positiveBalanceEffic.toString().length() != 0 && reverseBalanceEffic != null && reverseBalanceEffic.toString().length() != 0) {
            Double result = (positiveBalanceEffic + reverseBalanceEffic) / 2;
            return DoubleUtil.round(result, 2);
        } else {
            return null;
        }
    }

    /**
     * @param mediaFlow             介质流量  Boiler output    测试参数    m3/h
     * @param drainageTemp          出水温度  Drainage temperature    测试参数    ℃
     * @param influentTemp          入水温度  Influent temperature    测试参数    ℃
     * @param fuelTemp              燃料温度     Fuel temperature    测试参数    ℃
     * @param fuelSpecificHeat      燃料比热容    Fuel specific heat 化验参数    kJ/kg ·℃
     * @param lowFuelCalorificValue 燃料低位发热值   Low fuel calorific value    化验数据    kJ/m3
     * @param gasVolume             燃气量   Gas volume  测试数据  m3/h
     * @param ratedCapacity         额定容量  Rated Capacity    现场录入    MW
     * @param ratedHeatDissipation  额定散热   Rated heat dissipation  查表参数    %
     * @param airCoefficient        空气系数  Air coefficient   计算参数    α
     * @param exhaustCOContent      排烟处（CO)  Smoke exhaust CO content   测试参数    %
     * @param exhaustTemp           排烟温度    exhaust temperature     测试参数   ℃
     * @param airTemp               空气温度    Air temperature     测试参数   ℃
     * @return java.lang.Double
     * @Date 9:23 2019/7/12
     * @description :
     **/
    public static Double furnaceEfficiency(String mediaFlow, String drainageTemp, String influentTemp, String fuelTemp, String fuelSpecificHeat, String lowFuelCalorificValue, String gasVolume, String ratedCapacity, String ratedHeatDissipation, Double airCoefficient, String exhaustCOContent, String exhaustTemp, String airTemp) {
        if (mediaFlow != null && mediaFlow.length() != 0 &&
                drainageTemp != null && drainageTemp.length() != 0 &&
                influentTemp != null && influentTemp.length() != 0 &&
                fuelTemp != null && fuelTemp.length() != 0 &&
                fuelSpecificHeat != null && fuelSpecificHeat.length() != 0 &&
                lowFuelCalorificValue != null && lowFuelCalorificValue.length() != 0 &&
                gasVolume != null && gasVolume.length() != 0 &&
                ratedCapacity != null && ratedCapacity.length() != 0 &&
                ratedHeatDissipation != null && ratedHeatDissipation.length() != 0 &&
                airCoefficient != null && airCoefficient.toString().length() != 0 &&
                exhaustCOContent != null && exhaustCOContent.length() != 0 &&
                exhaustTemp != null && exhaustTemp.length() != 0 &&
                airTemp != null && airTemp.length() != 0) {
            return furnaceEfficiency(positiveBalanceEffic(mediaFlow, drainageTemp, influentTemp, fuelTemp, fuelSpecificHeat, lowFuelCalorificValue, gasVolume), reverseBalanceEffic(mediaFlow, drainageTemp, influentTemp, ratedCapacity, ratedHeatDissipation, airCoefficient, exhaustCOContent, exhaustTemp, airTemp));
        } else {
            return null;
        }
    }

}
