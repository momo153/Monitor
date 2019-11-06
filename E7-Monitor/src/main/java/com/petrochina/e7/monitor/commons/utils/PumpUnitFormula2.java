package com.petrochina.e7.monitor.commons.utils;

/**
 * @ProjectName com.petrochina.e7.monitor.commons.utils
 * @ClassName: PumpUnitFormula
 * @Description: TODO 抽油机计算公式工具类
 * @Author: Administrator
 * @Date: 2019/10/09 0009$ 17:30$
 * @Version: 1.0
 */

public class PumpUnitFormula2 {

    private static final Integer PARAM1 = 1;
    //private static final Double powerFactorEvaIndex = 0.4;
    //private static final Double thickenedOilLimitValue = 15.0; //稠油热采井系统效率评价指标(限定值)
    // private static final Double thickenedOilEvaValue = 22.0;//稠油热采井系统效率评价指标（节能评价值）
//    private static final Double g = 9.81;
    /**
     * @Author mzc
     * @Description //TODO 电机功率利用率计算公式 (依据计算表格公式)
     * @Date 17:36 2019/10/09 0009
     * @Param [inputPow, RatePower] inputPow: 输入功率, RatePower:额定功率
     * @return double   公式: 电机功率利用率=输入功率(有功功率)/额定功率*100;
     **/
    public static Double powFactorExcel(String inputPow, String ratePower){
        Double owFactor = null;
        if(inputPow != null && inputPow.length() != 0 && ratePower != null && ratePower.length() != 0 ) {
            Double inputPowDou = Double.parseDouble(inputPow);
            Double ratePowerDou = Double.parseDouble(ratePower);
            owFactor = inputPowDou / ratePowerDou * 100;
            return DoubleUtil.round(owFactor,2);
        }else {
            return null;
        }
    }

    /**
     * @Author mzc
     * @Description //TODO 液体的密度计算公式,(依据:GBT 33653-2017)(单位有问题)
     * @Date 08:56 2019/10/10 0010
     * @Param [moisCont, crudOilDens, WaterDens] moisCont:油井产出液含水率, crudOilDens:原油的密度, waterDens:水的密度
     * @return java.lang.Double   公式: (1 - 油井产出液含水率) * 原油的密度 + 油井产出液含水率 * 水的密度
     * moisCont:油井产出液含水率            测试数据        %
     * crudOilDens:原油的密度              测试数据        kg/m3(单位转换)
     * waterDens:水的密度                  测试参数        kg/m3
     **/
    public static Double dens(String moisCont, String crudOilDens, String waterDens){
        Double dens = null;
        if(moisCont != null && moisCont.length() != 0 && crudOilDens != null && crudOilDens.length() != 0 && waterDens != null && waterDens.length() != 0) {
            Double moisContDou = Double.parseDouble(moisCont);
            Double crudOilDensDou = Double.parseDouble(crudOilDens);
            Double waterDensDou = Double.parseDouble(waterDens);
            dens = ((PARAM1 - (moisContDou / 100)) * crudOilDensDou + (moisContDou / 100) * waterDensDou);
            return DoubleUtil.round(dens,3);
        }else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO 液体的密度计算公式,(依据:依据计算表格公式)(单位有问题)
     * @Date 08:56 2019/10/10 0010
     * @Param [moisCont, crudOilDens, WaterDens] moisCont:油井产出液含水率, crudOilDens:原油的密度, waterDens:水的密度
     * @return java.lang.Double   公式: 液体的密度=1/((含水率/1/100)+(1-含水率/100)/原油密度)
     * moisCont:油井产出液含水率            测试数据        %
     * crudOilDens:原油的密度              测试数据        kg/m3(单位转换)
     * waterDens:水的密度                  测试参数        kg/m3
     **/
    public static Double densExcel(String moisCont, String crudOilDens){
        Double densExcel = null;
        if(moisCont != null && moisCont.length() != 0 && crudOilDens != null && crudOilDens.length() != 0 ) {
            Double moisContDou = Double.parseDouble(moisCont);
            Double crudOilDensDou = Double.parseDouble(crudOilDens);
//            densExcel = ((PARAM1 - (moisContDou / 100)) * crudOilDensDou + (moisContDou / 100) * waterDensDou);
            densExcel = 1/((moisContDou/1/100)+(1-(moisContDou/100))/crudOilDensDou);
            return DoubleUtil.round(densExcel,3);
        }else {
            return null;
        }
    }



    /**
     * @Author mzc
     * @Description //TODO       有效扬程计算公式(依据:GBT 33653-2017)(单位有问题)
     * @Date 09:44 2019/10/10 0010
     * @Param [oilWellDynaLiquLevDepth, wellhTubPressure, wellhCasPressure, dens, g]
     * @return java.lang.Double                 有效扬程=油井动液面深度+(井口油管压力-井口套管压力)*1000000/液体的密度*重力加速度
     * oilWellDynaLiquLevDepth(油井动液面深度)   测试参数       m
     * wellhTubPressure(井口油管压力)            测试参数      Mpa
     * wellhCasPressure(井口套管压力)            测试参数      Mpa
     * dens(液体的密度)                         计算参数      kg/m3(单位转换)
     *g:重力加速度,g=9.81                       常量          m/s2
     **/
    public static Double eficHead(String oilWellDynaLiquLevDepth, String wellhTubPressure, String wellhCasPressure, Double dens, Double g) {
        Double eficHead = null;
        if (oilWellDynaLiquLevDepth != null && oilWellDynaLiquLevDepth.length() != 0
                && wellhTubPressure != null && wellhTubPressure.length() != 0
                && wellhCasPressure != null && wellhCasPressure.length() != 0
                && dens != null && dens.toString().length() != 0
                && g != null && g.toString().length() != 0) {
            Double oilWellDynaLiquLevDepthDou = Double.parseDouble(oilWellDynaLiquLevDepth);
            Double wellhTubPressureDou = Double.parseDouble(wellhTubPressure);
            Double wellhCasPressureDou = Double.parseDouble(wellhCasPressure);
//        Double gDou = Double.parseDouble(g);
            eficHead = oilWellDynaLiquLevDepthDou + ((wellhTubPressureDou - wellhCasPressureDou) * 1000000) / (dens * 1000 * g);
            return DoubleUtil.round(eficHead, 2);
        } else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO  机械采油系统输出功率(有效功率)(依据:GBT 33653-2017)(立方米和吨单位??)
     * @Date 11:10 2019/10/10 0010
     * @Param [Prod_liqu_amo, Efic_head, dens, g]
     * @return java.lang.Double                   机械采油系统输出功率=油井产液量*有效扬程*液体的密度*重力加速度/86400000
     * ProdLiquAmo: 油井产液量                   测试参数      m3/d
     * eficHead: 有效扬程                        计算参数      m
     * dens: 液体的密度;                          计算参数      kg/m3
     * g:重力加速度,g=9.81                        常量          m/s2
     **/
    public static Double outPower(String ProdLiquAmo, Double eficHead, Double dens, Double g) {
        Double outPower = null;
        if (ProdLiquAmo != null && ProdLiquAmo.length() != 0 && eficHead != null && eficHead.toString().length() != 0 && dens != null && dens.toString().length() != 0 && g != null && g.toString().length() != 0) {
            Double ProdLiquAmoDou = Double.parseDouble(ProdLiquAmo);
//            Double eficHeadDou = Double.parseDouble(eficHead);
//        Double densDou = Double.parseDouble(dens);
//        Double gDou = Double.parseDouble(g);
            outPower = (ProdLiquAmoDou * eficHead * dens * 1000 * g) / 86400000;
            return DoubleUtil.round(outPower, 2);
        } else {
            return null;
        }
    }

    /**
     * @Author mzc
     * @Description //TODO  机械采油系统输出功率(有效功率)(依据:GBT 33653-2017)(立方米和吨单位??)
     * @Date 11:10 2019/10/10 0010
     * @Param [Prod_liqu_amo, Efic_head, dens, g]
     * @return java.lang.Double                   机械采油系统输出功率=油井产液量*有效扬程*液体的密度*重力加速度/86400000
     * ProdLiquAmo: 油井产液量                   测试参数      m3/d
     * eficHead: 有效扬程                        计算参数      m
     * dens: 液体的密度;                          计算参数      kg/m3(Excel计算表中没有此参数参与计算)
     * g:重力加速度,g=9.81                        常量          m/s2
     **/
    public static Double outPowerExcel(String ProdLiquAmo, Double eficHead, Double g) {
        Double outPowerExcel = null;
        if (ProdLiquAmo != null && ProdLiquAmo.length() != 0 && eficHead != null && eficHead.toString().length() != 0 && g != null && g.toString().length() != 0) {
            Double ProdLiquAmoDou = Double.parseDouble(ProdLiquAmo);
//            Double eficHeadDou = Double.parseDouble(eficHead);
//        Double densDou = Double.parseDouble(dens);
//        Double gDou = Double.parseDouble(g);
            outPowerExcel = (ProdLiquAmoDou * eficHead * g) / 86400;
            return DoubleUtil.round(outPowerExcel, 2);
        } else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO 吨液百米单耗计算(依据计算表格公式)
     * @Date 13:39 2019/10/10 0010
     * @Param [inputPow, runtime, prodLiquAmo, eficHead, dens]
     * @return java.lang.Double                 吨液百米单耗=输入功率*运行时间/(油井产液量*有效扬程*液体的密度)*100
     * inputPow: 输入功率                        测试数据    kw
     * runTime: 运行时间(是否间抽)                测试数据    h/d
     * prodLiquAmo: 油井产液量                   测试参数    m3/d
     * eficHead: 有效扬程                        计算参数    m
     * dens: 液体的密度;                         计算参数    kg/m3
     **/
    public static Double tonliquSconsuExcel(String inputPow, String runTime, String prodLiquAmo, Double eficHead, Double dens) {
        Double tonliquSconsu = null;
        if (inputPow != null && inputPow.length() != 0 && runTime != null && runTime.length() != 0 && prodLiquAmo != null && prodLiquAmo.length() != 0 && eficHead != null && eficHead.toString().length() != 0 && dens != null && dens.toString().length() != 0) {
            Double inputPowDou = Double.parseDouble(inputPow);
            Integer runTimeI = Integer.parseInt(runTime);
            Double prodLiquAmoDou = Double.parseDouble(prodLiquAmo);
            tonliquSconsu = (inputPowDou * runTimeI / (prodLiquAmoDou * eficHead * dens)) * 100;
            return DoubleUtil.round(tonliquSconsu, 2);
        } else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO 有功吨液百米单耗计算公式(依据:计算表格公式)
     * @Date 15:49 2019/10/11 0011
     * @Param [ActivePower, runTime, prodLiquAmo, eficHead, dens]
     * @return java.lang.Double                 有功吨液百米单耗=有功功率*运行时间/(油井产液量*有效扬程*液体的密度)*100
     * ActivePower: 有功功率                     测试参数    kw
     * runTime: 运行时间(是否间抽)                测试参数    h/d
     * prodLiquAmo: 油井产液量                   测试参数    m3/d
     * eficHead: 有效扬程                        计算参数    m
     * dens: 液体的密度;                         计算参数    kg/m3
     **/
    public static Double activeTonliquSconsuExcel(String activePower, String runTime, String prodLiquAmo, Double eficHead, Double dens) {
        Double activeTonliquSconsu = null;
        if (activePower != null && activePower.length() != 0 && runTime != null && runTime.length() != 0 && prodLiquAmo != null && prodLiquAmo.length() != 0 && eficHead != null && eficHead.toString().length() != 0 && dens != null && dens.toString().length() != 0) {
            Double activePowerDou = Double.parseDouble(activePower);
            Integer runTimeI = Integer.parseInt(runTime);
            Double prodLiquAmoDou = Double.parseDouble(prodLiquAmo);
            activeTonliquSconsu = (activePowerDou * runTimeI / (prodLiquAmoDou * eficHead * dens)) * 100;
            return DoubleUtil.round(activeTonliquSconsu, 3);
        } else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO 无功吨液百米单耗计算公式(依据:计算表格公式)
     * @Date 15:52 2019/10/11 0011
     * @Param [reactivePower, runTime, prodLiquAmo, eficHead, dens]
     * @return java.lang.Double                 无功吨液百米单耗=无功功率*运行时间/(油井产液量*有效扬程*液体的密度)*100
     * reactivePower: 无功功率                   测试参数    kvar
     * runTime: 运行时间(是否间抽)                测试参数    h/d
     * prodLiquAmo: 油井产液量                   测试参数    m3/d
     * eficHead: 有效扬程                        计算参数    m
     * dens: 液体的密度;                         计算参数    kg/m3
     **/
    public static Double reactiveTonliquSconsuExcel(String reactivePower, String runTime, String prodLiquAmo, Double eficHead, Double dens) {
        Double reactiveTonliquSconsu = null;
        if (reactivePower != null && reactivePower.length() != 0 && runTime != null && runTime.length() != 0 && prodLiquAmo != null && prodLiquAmo.length() != 0 && eficHead != null && eficHead.toString().length() != 0 && dens != null && dens.toString().length() != 0) {
            Double reactivePowerDou = Double.parseDouble(reactivePower);
            Integer runTimeI = Integer.parseInt(runTime);
            Double prodLiquAmoDou = Double.parseDouble(prodLiquAmo);
            reactiveTonliquSconsu = (reactivePowerDou * runTimeI / (prodLiquAmoDou * eficHead * dens)) * 100;
            return DoubleUtil.round(reactiveTonliquSconsu, 3);
        } else {
            return null;
        }
    }




    /**
     * @Author mzc
     * @Description //TODO 机械采油系统输入功率(依据:GBT 33653-2017)
     * @Date 16:31 2019/10/10 0010
     * @Param [inputActivePower, elecTestTime]
     * @return java.lang.Double
     * inputActivePower: 累计输入有功电量        kw.h
     * elecTestTime: 电量测试累计时间            s
     **/
    public static Double inputPow(String inputActivePower, String elecTestTime) {
        Double inputPow = null;
        if (inputActivePower.length() != 0 && inputActivePower != null && elecTestTime.length() != 0 && elecTestTime != null) {
            Double inputActivePowerDou = Double.parseDouble(inputActivePower);
            Double elecTestTimeDou = Double.parseDouble(elecTestTime);
            inputPow = 3600 * inputActivePowerDou / elecTestTimeDou;
            return DoubleUtil.round(inputPow, 3);
        } else {
            return null;
        }
    }



    /**
     * @Author mzc
     * @Description //TODO 机械采油系统输入功率(依据:计算公式表)
     * @Date 17:05 2019/10/10 0010
     * @Param [inputPow, negativePower, runTime]
     * @return java.lang.Double
     * inputPow: 输入功率               计算参数    kW
     * negativePower: 负功电量          测试参数    kW
     *runTime: 累计时间                 测试参数    s
     **/
    public static Double inputPowExcel(String inputPow, String negativePower, String runTime) {
        Double inputPowExcel = null;
        if (inputPow != null && inputPow.length() != 0 && negativePower != null && negativePower.length() != 0 && runTime != null && runTime.length() != 0) {
            Double inputPowDou = Double.parseDouble(inputPow);
            Double negativePowerDou = Double.parseDouble(negativePower);
            Double runTimeDou = Double.parseDouble(runTime);
            inputPowExcel = inputPowDou - negativePowerDou * 3600 / runTimeDou;
            return DoubleUtil.round(inputPowExcel, 3);
        } else {
            return null;
        }
    }



    /**
     * @Author mzc
     * @Description //TODO  系统效率计算公式(依据:GBT 33653-2017)
     * @Date 14:32 2019/10/10 0010
     * @Param [outPower, activePower]
     * @return java.lang.Double           系统效率=outPower/inputPow*100
     * outPower :输出功率               计算参数        kw
     *inputPow;有功功率                 计算参数        kw
     **/
    public static Double sysefic(Double outPower, String inputPow) {
        Double sysefic = null;
        if (outPower != null && outPower.toString().length() != 0 && inputPow != null && inputPow.length() != 0) {
//        double activePowerDou = Double.parseDouble(activePower);
            Double inputPowDou = Double.parseDouble(inputPow);
            sysefic = outPower / inputPowDou * 100;
            return DoubleUtil.round(sysefic, 2);
        } else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO 抽油机平衡度计算公式(依据:EXCEL计算表格)
     * @Date 14:36 2019/10/11 0011
     * @Param [upstrokeCurrent, downstrokeCurrent]
     * @return java.lang.Double         抽油机平衡度=downstrokeCurrent/upstrokeCurrent*100;
     * upstrokeCurrent: 上冲程电流       测试      A
     * downstrokeCurrent:下冲程电流      测试      A
     **/
    public static Double balanceExcel (String upstrokeCurrent, String downstrokeCurrent){
        Double balance = null;
        if(upstrokeCurrent != null && upstrokeCurrent.length() != 0 && downstrokeCurrent != null && downstrokeCurrent.length() != 0) {
            Double upstrokeCurrentDou = Double.parseDouble(upstrokeCurrent);
            Double downstrokeCurrentDou = Double.parseDouble(downstrokeCurrent);
            balance = downstrokeCurrentDou / upstrokeCurrentDou * 100;
            return DoubleUtil.round(balance,2);
        }else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO 无功功率计算公式(依据:计算公式Excel表)
     * @Date 14:46 2019/10/11 0011
     * @Param [Factor_power, inputPow]
     * @return java.lang.Double         sqrt((1-factorPower*factorPower)*inputPow/factorPower);
     * inputPow: 有功功率                 计算参数        kw
     * factorPower: 功率因数              测试参数        -
     **/
    public static Double eactivePowerExcel(String factorPower, String inputPow) {
        Double eactivePower = null;
        if (factorPower != null && factorPower.length() != 0 && inputPow != null && inputPow.length() != 0) {
            Double inputPowDou = Double.parseDouble(inputPow);
            Double factorPowerDou = Double.parseDouble(factorPower);
            eactivePower = (1 - factorPowerDou * factorPowerDou) * inputPowDou / factorPowerDou;
            return Math.sqrt(eactivePower);
        } else {
            return null;
        }
    }



    /**
     * @Author mzc
     * @Description //TODO 泵效率计算公式(依据:计算公式Excel表)
     * @Date 10:37 2019/10/18 0018
     * @Param []
     * @return java.lang.Double     泵效率=产液量/((泵径*泵径/4)*0.000001*冲程*冲次*3.1415*1440)*100
     * prodLiquAmo: 油井产液量     测试参数        m3/d
     *pumpDiam: 泵径             测试参数        mm
     *stroke: 冲程                测试参数        m
     *rushtimes: 冲次             测试参数        min-1
     **/
    public static Double PumpEffic(String prodLiquAmo, String pumpDiam, String stroke, String rushtimes) {
        Double PumpEffic = null;
        if (prodLiquAmo != null && prodLiquAmo.length() != 0 && pumpDiam != null && pumpDiam.length() != 0 && stroke != null && stroke.length() != 0 && rushtimes != null && rushtimes.length() != 0) {
            Double prodLiquAmoDou = Double.parseDouble(prodLiquAmo);
            Double pumpDiamDou = Double.parseDouble(pumpDiam);
            Double strokeDou = Double.parseDouble(stroke);
            Double rushtimesDou = Double.parseDouble(rushtimes);
            PumpEffic = prodLiquAmoDou / ((pumpDiamDou * pumpDiamDou / 4) * 0.000001 * strokeDou * 3.1415 * 1440 * rushtimesDou) * 100;
            return DoubleUtil.round(PumpEffic, 2);
        } else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO 沉没度计算(依据:计算公式Excel表)
     * @Date 18:04 2019/10/20 0020
     * @Param [pumpDepth, oilWellDynaLiquLevDepth]
     * @return java.lang.Double                沉没度=泵深-动液面深度
     * pumpDepth: 泵深                         测试参数      m
     * oilWellDynaLiquLevDepth: 动液面深度      测试参数      m
     **/
    public static Double sinkDegr(String pumpDepth, String oilWellDynaLiquLevDepth) {
        if (pumpDepth != null && pumpDepth.length() != 0 && oilWellDynaLiquLevDepth != null && oilWellDynaLiquLevDepth.length() != 0) {
            Double pumpDepthDou = Double.parseDouble(pumpDepth);
            Double oilWellDynaLiquLevDepthDou = Double.parseDouble(oilWellDynaLiquLevDepth);
            Double sinkDegr = pumpDepthDou - oilWellDynaLiquLevDepthDou;
            return DoubleUtil.round(sinkDegr, 3);
        } else {
            return null;
        }
    }

//----------评价指标及评价结果部分--------------------------------------------------------------------------------------------------------------------------------

    /**
     * @Author mzc
     * @Description //TODO 系统效率影响系数(渗透率k1)
     * @Date 09:58 2019/10/25 0025
     * @Param [blockPermeability]
     * @return java.lang.Double
     * blockPermeability:   区块渗透率:中-1, 高-1, 低-1.4, 特低-1.6, 超低-1,7
     **/
    public static Double permeabilitK1(String blockPermeability) {

        Double permeabilitK1 = null;
        if (blockPermeability != null && blockPermeability.length() != 0) {
            permeabilitK1 = 1.4;
            if ("低".equals(blockPermeability)) {
                permeabilitK1 = 1.4;
            } else if ("特低".equals(blockPermeability)) {
                permeabilitK1 = 1.6;
            } else if ("超低".equals(blockPermeability)) {
                permeabilitK1 = 1.7;
            } else if ("中".equals(blockPermeability) || "高".equals(blockPermeability)) {
                permeabilitK1 = 1.0;
            } else {
                permeabilitK1 = 1.0;
            }
        } else {
            permeabilitK1 = null;
        }
        return permeabilitK1;
    }


    /**
     * @Author mzc
     * @Description //TODO 系统效率影响系数K2泵挂
     * @Date 10:42 2019/10/25 0025
     * @Param [pumpDepth]
     * @return java.lang.Double
     * pumpDepth ;泵挂 pumpDepth>1500-1, 1500 <= pumpDepth <=2500 -1.05,  pumpDepth>2500-1.1
     **/
    public static Double pumpHangK2(String pumpDepth) {
        Double pumpHangK2 = null;
        if (pumpDepth != null && pumpDepth.length() != 0) {
            Double pumpDepthDou = Double.parseDouble(pumpDepth);
            if (pumpDepthDou < 1500) {
                pumpHangK2 = 1.0;
            } else if (pumpDepthDou <= 2500 && pumpDepthDou >= 1500) {
                pumpHangK2 = 1.05;
            } else {
                pumpHangK2 = 1.1;
            }
        }
        return pumpHangK2;
    }



    /**
     * @Author mzc
     * @Description //TODO 系统效率影响系数K3井眼轨迹
     * @Date 11:06 2019/10/25 0025
     * @Param []
     * @return java.lang.Double
     *wellTraj : 井眼轨迹:定向井-1.05,直井-1
     **/
    public static Double wellTrajK3(String wellTraj){
        Double wellTrajK3 = null;
        if (wellTraj != null && wellTraj.length() != 0) {
            if ("定向井".equals(wellTraj)) {
                wellTrajK3 = 1.05;
            } else if ("直井".equals(wellTraj)) {
                wellTrajK3 = 1.0;
            }
        } else {
            return null;
        }
        return wellTrajK3;
    }





    /**
     * @Author mzc
     * @Description //TODO 稀油井系统效率评价指标(限定值)
     * @Date 13:54 2019/10/25 0025
     * @Param [permeabilitK1, pumpHangK2, wellTrajK3]
     * @return java.lang.Double
     * permeabilitK1:渗透率k1
     * pumpHangK2:泵挂
     * wellTrajK3:井眼轨迹
     *
     **/

    public static Double thinOilLimitValue(Double permeabilitK1, Double pumpHangK2, Double wellTrajK3) {
        if (permeabilitK1 != null && permeabilitK1.toString().length() != 0 && pumpHangK2 != null && pumpHangK2.toString().length() != 0 && wellTrajK3 != null && wellTrajK3.toString().length() != 0) {
            Double thinOilLimitValueDou = 18 / (permeabilitK1 * pumpHangK2 * wellTrajK3);
            return DoubleUtil.round(thinOilLimitValueDou,2);
        } else {
            return null;
        }
    }
    /**
     * @Author mzc
     * @Description //TODO 稀油井系统效率评价指标(节能评价值)
     * @Date 13:54 2019/10/25 0025
     * @Param [permeabilitK1, pumpHangK2, wellTrajK3]
     * @return java.lang.Double
     * permeabilitK1:渗透率k1
     * pumpHangK2:泵挂
     * wellTrajK3:井眼轨迹
     *
     **/
    public static Double thinOilEvaValue(Double permeabilitK1, Double pumpHangK2, Double wellTrajK3) {
        if (permeabilitK1 != null && permeabilitK1.toString().length() != 0 && pumpHangK2 != null && pumpHangK2.toString().length() != 0 && wellTrajK3 != null && wellTrajK3.toString().length() != 0) {
            Double thinOilEvaValueDou = 31 / (permeabilitK1 * pumpHangK2 * wellTrajK3);
            return  DoubleUtil.round(thinOilEvaValueDou,2);
        } else {
            return null;
        }
    }

    /**
     * @Author mzc
     * @Description //TODO系统效率评价指标(限定值)
     * @Date 14:11 2019/10/25 0025
     * @Param [oilType]
     * @return java.lang.Double
     * oilType;油品性质
     * thinOilLimitValueDou:稀油井系统效率评价指标(限定值)
     **/
    public static Double systemPowerLimitValue(String oilType, Double thinOilLimitValueDou,Double thickenedOilLimitValue) {
        Double systemPowerLimitValue = null;
        if (oilType != null && oilType.length() != 0) {
            if ("稀油".equals(oilType)) {
                return systemPowerLimitValue =  DoubleUtil.round(thinOilLimitValueDou,2);
            } else {
                return systemPowerLimitValue =  DoubleUtil.round(thickenedOilLimitValue,2);
            }
        } else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO系统效率评价指标（节能评价值）
     * @Date 14:11 2019/10/25 0025
     * @Param [oilType]
     * @return java.lang.Double
     * oilType;油品性质
     * thinOilEvaValue:稀油井系统效率评价指标(评价值)
     **/
    public static Double systemPowerEvaValue(String oilType, Double thinOilEvaValue ,Double thickenedOilEvaValue) {
        Double systemPowerEvaValue = null;
        if (oilType != null && oilType.length() != 0) {
            if ("稀油".equals(oilType)) {
                return systemPowerEvaValue = DoubleUtil.round(thinOilEvaValue,2);
            } else {
                return systemPowerEvaValue = DoubleUtil.round(thickenedOilEvaValue,2);
            }
        } else {
            return null;
        }
    }


    /*平衡度评价指标*/


//---------评价结果-----------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @Author mzc
     * @Description //TODO 功率因数评价结果
     * @Date 11:20 2019/10/25 0025
     * @Param [powerFactor]
     * @return java.lang.String
     **/
    public static String powerFactorEvaResult(String powerFactor,Double powerFactorEvaIndex){
        String powerFactorEvaResult = null;
        if(powerFactor != null && powerFactor.length() != 0){
            Double powerFactorDou = Double.parseDouble(powerFactor);
            if(powerFactorDou >= powerFactorEvaIndex) {
                return powerFactorEvaResult = "合格";
            }else {
                return powerFactorEvaResult = "不合格";
            }
        }else {
            return null;
        }
    }



    /**
     * @Author mzc
     * @Description //TODO 系统效率评价结果
     * @Date 14:44 2019/10/25 0025
     * @Param [systemPowerm, systemPowerLimitValue, systemPowerEvaValue]
     * @return java.lang.String
     * systemPowerm:系统效率
     * systemPowerLimitValue:稀油井系统效率评价指标(限定值)
     * systemPowerEvaValue:稀油井系统效率评价指标(节能评价值)
     **/
    public static String systemPowerEvaResult(String systemPowerm, Double systemPowerLimitValue, Double systemPowerEvaValue) {
        String systemPowerEvaResult = null;
        if (systemPowerm != null && systemPowerm.length() != 0 && systemPowerLimitValue != null && systemPowerLimitValue.toString().length() != 0 && systemPowerEvaValue != null && systemPowerEvaValue.toString().length() != 0) {
            Double systemPowermDou = Double.parseDouble(systemPowerm);
            if (systemPowermDou < systemPowerLimitValue) {
//                 systemPowerEvaResult = "不合格";
                return systemPowerEvaResult = "不合格";
            } else {
                if (systemPowermDou < systemPowerEvaValue) {
                    return systemPowerEvaResult = "合格";
                } else {
                    return systemPowerEvaResult = "节能";
                }
            }
        } else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO 平衡度评价结果
     * @Date 15:10 2019/10/25 0025
     * @Param [balanced]
     * @return java.lang.String
     * balanced:平衡度
     **/
    public static String balancedEvaResult(String balanced) {
        String balancedEvaResult = null;
        if (balanced != null && balanced.length() != 0) {
            Double balancedDou = Double.parseDouble(balanced);
            if (balancedDou >= 80 && balancedDou <= 110) {
                return balancedEvaResult = "合格";
            } else {
                return balancedEvaResult = "不合格";
            }
        } else {
            return null;
        }
    }


    /**
     * @Author mzc
     * @Description //TODO 综合评价
     * @Date 15:36 2019/10/25 0025
     * @Param [powerFactorEvaResult, systemPowerEvaResult, balancedEvaResult]
     * @return java.lang.String
     * powerFactorEvaResult:功率因数评价结果
     * systemPowerEvaResul:系统效率评价结果
     * balancedEvaResult:平衡度评价结果
     **/
    public static String comprehensiveEva(String powerFactorEvaResult, String systemPowerEvaResult, String balancedEvaResult) {
        String comprehensiveEva = null;
        if (powerFactorEvaResult != null && powerFactorEvaResult.length() != 0 && systemPowerEvaResult != null && systemPowerEvaResult.length() != 0 && balancedEvaResult != null && balancedEvaResult.length() != 0) {
            if ("合格".equals(powerFactorEvaResult) && "合格".equals(systemPowerEvaResult) && "合格".equals(balancedEvaResult)) {
                return comprehensiveEva = "合格";
            } else if ("合格".equals(powerFactorEvaResult) && "节能".equals(systemPowerEvaResult) && "合格".equals(balancedEvaResult)) {
                return comprehensiveEva = "合格";
            } else {
                return comprehensiveEva = "不合格";
            }
        } else {
            return null;
        }
    }





}
