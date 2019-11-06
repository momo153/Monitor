package com.petrochina.e7.monitor.commons.utils;

import com.alibaba.fastjson.JSON;
import com.petrochina.e7.monitor.pojo.MechanicalExcel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName com.petrochina.e7.monitor.commons.utils
 * @ClassName: ExcelUtils
 * @Description: TODO   excel 导入工具类
 * @Author: Administrator
 * @Date: 2019/10/31 0031$ 10:09$
 * @Version: 1.0
 */

public class ExcelUtils {

    /**
     * @Author (hgq)mzc
     * @Description //TODO 抽油机导入Excel 表格数据
     * @Date 10:11 2019/10/31 0031
     * @Param [file]
     * @return java.util.List<com.petrochina.e7.monitor.pojo.MechanicalExcel>
    **/
    public static List<MechanicalExcel> fetchMechanicalExcel (MultipartFile file){

        Boolean falg = true;
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        List<MechanicalExcel> resultList = new ArrayList<>();
        try {
            String str = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if (str.equals(".xls") || str.equals(".xlsx")) {///判断是不是表格
                POIFSFileSystem pfs = new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook wb = new HSSFWorkbook(pfs);
                FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                HSSFSheet sheet = wb.getSheetAt(0);
                //声明行对象
                HSSFRow row = null;
                //通过循环获取每一行
                for (int i = 4; sheet.getRow(i) != null; i++) {
                    MechanicalExcel mechanicalExcel = new MechanicalExcel();
                    row = sheet.getRow(i);
                    //循环获取一行的中列
                    //将表格中的一行数据看成一个对象，用List<MechanicalExcel>去接收

                  /*  for (int j = 0; row.getCell(j)!=null&&j < 55; j++) {

                        map.put(list.get(j),row.getCell(j).toString());
                    }*/
                   /*for (int j = 0; row.getCell(j)!=null&&j < 55; j++) {
                                if(row.getCell(j).toString().equals("")){

                                }

                    }*/
                    if (row.getCell(1) == null)
                        break;
                    System.out.println(row.getCell(3));
                    mechanicalExcel.setExcelName(file.getOriginalFilename());
                    mechanicalExcel.setOrgId(1);
                    mechanicalExcel.setOrgName("");
                    mechanicalExcel.setFactoryCatagory(row.getCell(0).toString());
                    mechanicalExcel.setMineCatagory(row.getCell(1).toString());
                    mechanicalExcel.setTeamCatagory(row.getCell(2).toString().replace(".0", ""));
                    mechanicalExcel.setSerialNumber(Integer.parseInt(row.getCell(3).toString().replace(".0", "")));
                    mechanicalExcel.setWellNumber(row.getCell(4).toString());
                    mechanicalExcel.setTestDate(row.getCell(5).toString());
                    mechanicalExcel.setPumpingModel(row.getCell(6).toString());
                    // mechanicalExcel.setPumpFactoryDate(row.getCell(7).toString());
                    mechanicalExcel.setPumpFactoryDate(evaluator.evaluate(row.getCell(7)).getStringValue());
                    //mechanicalExcel.setPumpManu(row.getCell(8).toString());
                    mechanicalExcel.setPumpManu(evaluator.evaluate(row.getCell(8)).getStringValue());
                    mechanicalExcel.setMotorModel(row.getCell(9).toString());
                    // mechanicalExcel.setMotorEliminationEquip(row.getCell(10).toString());
                    mechanicalExcel.setMotorEliminationEquip(evaluator.evaluate(row.getCell(10)).getStringValue());
                    mechanicalExcel.setMotorRatePow(row.getCell(11).toString());
                    // mechanicalExcel.setMotorRateCurrent(row.getCell(12).toString());
                    mechanicalExcel.setMotorRateCurrent(evaluator.evaluate(row.getCell(12)).getStringValue());
                    //mechanicalExcel.setMotorRateVoltage(row.getCell(13).toString());
                    mechanicalExcel.setMotorRateVoltage(evaluator.evaluate(row.getCell(13)).getStringValue());
                    mechanicalExcel.setMotorRateSpeed(evaluator.evaluate(row.getCell(14)).getStringValue());
                    mechanicalExcel.setMotorFactoryDate(evaluator.evaluate(row.getCell(15)).getStringValue());
                    mechanicalExcel.setMotorManu(evaluator.evaluate(row.getCell(16)).getStringValue());
                    mechanicalExcel.setDboxModel(row.getCell(17).toString());
                    mechanicalExcel.setDboxFactoryDate(evaluator.evaluate(row.getCell(18)).getStringValue());
                    mechanicalExcel.setDboxCompensation(evaluator.evaluate(row.getCell(19)).getStringValue());
                    mechanicalExcel.setDboxManu(evaluator.evaluate(row.getCell(20)).getStringValue());
                    mechanicalExcel.setDboxOperFrequency(evaluator.evaluate(row.getCell(21)).getStringValue());
                    mechanicalExcel.setDboxOperVltage(evaluator.evaluate(row.getCell(22)).getStringValue());
                /*    mechanicalExcel.setMotorRateSpeed(row.getCell(14).toString());
                    mechanicalExcel.setMotorFactoryDate(row.getCell(15).toString());
                    mechanicalExcel.setMotorManu(row.getCell(16).toString());

                    mechanicalExcel.setDboxFactoryDate(row.getCell(18).toString());
                    mechanicalExcel.setDboxCompensation(row.getCell(19).toString());
                    mechanicalExcel.setDboxManu(row.getCell(20).toString());
                    mechanicalExcel.setDboxOperFrequency(row.getCell(21).toString());
                    mechanicalExcel.setDboxOperVltage(row.getCell(22).toString());*/
                    mechanicalExcel.setOilType(row.getCell(32).toString());
                    mechanicalExcel.setBlockPermeability(row.getCell(33).toString());
                    mechanicalExcel.setWellTraj(row.getCell(34).toString());
                    mechanicalExcel.setOperFrequency(row.getCell(35).toString());
                    mechanicalExcel.setRuntimeDay(row.getCell(36).toString().replace(".0", ""));
                    mechanicalExcel.setActivePower(row.getCell(37).toString());
                    mechanicalExcel.setNegativeBattery(row.getCell(38).toString());
                    mechanicalExcel.setCumulativeTime(row.getCell(39).toString());
                    mechanicalExcel.setReactivePower(row.getCell(40).toString());
                    mechanicalExcel.setPowerFactor(row.getCell(41).toString());
                    mechanicalExcel.setOilPressure(row.getCell(42).toString());
                    mechanicalExcel.setCasiPressuer(row.getCell(43).toString());
                    mechanicalExcel.setDynaLiquLevDepth(row.getCell(44).toString());
                    mechanicalExcel.setPumpDepth(row.getCell(45).toString());
                    Cell cell = row.getCell(46);

                    CellValue cellValue = evaluator.evaluate(cell);
                    mechanicalExcel.setSinkDegr(String.valueOf(DoubleUtil.round(Double.parseDouble(String.valueOf(cellValue.getNumberValue())), 2)));
                    mechanicalExcel.setPumpDiam(row.getCell(47).toString());
                    mechanicalExcel.setMoisCont(row.getCell(48).toString());
                    mechanicalExcel.setProdLiquAmo(row.getCell(49).toString());
                    mechanicalExcel.setCrudOilDens(row.getCell(50).toString());
                    mechanicalExcel.setStroke(row.getCell(51).toString());
                    mechanicalExcel.setStrokeNum(row.getCell(52).toString());
                    mechanicalExcel.setUpstrokeCurrent(row.getCell(53).toString());
                    mechanicalExcel.setDownstrokeCurrent(row.getCell(54).toString());
                    //计算数据封装
                    // @Param [inputPow, RatePower] inputPow: 输入功率, RatePower:额定功率
                    String motorOwerTilization = String.valueOf(PumpUnitFormula.powFactorExcel(mechanicalExcel.getActivePower(), mechanicalExcel.getMotorRatePow()));
                    //液体密度
                    //@Param [moisCont, crudOilDens, WaterDens] moisCont:油井产出液含水率, crudOilDens:原油的密度, waterDens:水的密度
                    String liquidDensity = String.valueOf(PumpUnitFormula.dens(mechanicalExcel.getMoisCont(), mechanicalExcel.getCrudOilDens(), "1"));
                    //有效扬程
                    //* oilWellDynaLiquLevDepth(油井动液面深度)   测试参数       m
                    //     * wellhTubPressure(井口油管压力)            测试参数      Mpa
                    //     * wellhCasPressure(井口套管压力)            测试参数      Mpa
                    //     * dens(液体的密度)
                    String eficHead = String.valueOf(PumpUnitFormula.eficHead(mechanicalExcel.getDynaLiquLevDepth(), mechanicalExcel.getOilPressure(), mechanicalExcel.getCasiPressuer(), Double.parseDouble(liquidDensity), 9.81));
                    //机械采油系统输出功率(有效功率)
                    //  * ProdLiquAmo: 油井产液量                   测试参数      m3/d
                    //     * eficHead: 有效扬程                        计算参数      m
                    //     * dens: 液体的密度;                          计算参数      kg/m3
                    //     * g:重力加速度,g=9.81                        常量          m/s2
                    String eficipow = String.valueOf(PumpUnitFormula.outPower(mechanicalExcel.getProdLiquAmo(), Double.parseDouble(eficHead), Double.parseDouble(liquidDensity), 9.81));
                    //有功吨液百米单耗计算公式(有功吨液百米单耗)
                    /* * inputPow: 输入功率                        测试数据    kw
                     * runTime: 运行时间(是否间抽)                测试数据    h/d
                     * prodLiquAmo: 油井产液量                   测试参数    m3/d
                     * eficHead: 有效扬程                        计算参数    m
                     * dens: 液体的密度;                         计算参数    kg/m3
                     **/
                    String activeTonliquSconsu = String.valueOf(PumpUnitFormula.activeTonliquSconsuExcel(mechanicalExcel.getActivePower(), mechanicalExcel.getRuntimeDay(), mechanicalExcel.getProdLiquAmo(), Double.parseDouble(eficHead), Double.parseDouble(liquidDensity)));
                    //无功吨液百米单耗计算公式
                    /*  * reactivePower: 无功功率                   测试参数    kvar
                     * runTime: 运行时间(是否间抽)                测试参数    h/d
                     * prodLiquAmo: 油井产液量                   测试参数    m3/d
                     * eficHead: 有效扬程                        计算参数    m
                     * dens: 液体的密度;                         计算参数    kg/m3
                     **/
                    String reactiveTonliquSconsu = String.valueOf(PumpUnitFormula.reactiveTonliquSconsuExcel(mechanicalExcel.getReactivePower(), mechanicalExcel.getRuntimeDay(), mechanicalExcel.getProdLiquAmo(), Double.parseDouble(eficHead), Double.parseDouble(liquidDensity)));
                    //系统效率计算公式
                    /** * outPower :输出功率(有效功率)               计算参数        kw
                     *inputPow;有功功率                 计算参数        kw
                     **/
                    String systemPower = String.valueOf(PumpUnitFormula.sysefic(Double.parseDouble(eficipow), mechanicalExcel.getActivePower()));
                    //抽油机平衡度计算公式
                    /** * upstrokeCurrent: 上冲程电流       测试      A
                     * downstrokeCurrent:下冲程电流      测试      A
                     **/
                    String balanced = "";
                    if (!(mechanicalExcel.getUpstrokeCurrent().equals("") && mechanicalExcel.getDownstrokeCurrent().equals(""))) {
                        balanced = String.valueOf(PumpUnitFormula.balanceExcel(mechanicalExcel.getUpstrokeCurrent(), mechanicalExcel.getDownstrokeCurrent()));
                    }
                    //泵效率计算公式
                    /**  prodLiquAmo: 油井产液量     测试参数        m3/d
                     * pumpDiam: 泵径             测试参数        mm
                     *stroke: 冲程                测试参数        m
                     *rushtimes: 冲次             测试参数        min-1
                     **/
                    String pumpEfic = String.valueOf(PumpUnitFormula.PumpEffic(mechanicalExcel.getProdLiquAmo(), mechanicalExcel.getPumpDiam(), mechanicalExcel.getStroke(), mechanicalExcel.getStrokeNum()));
                    //封装计算数据
                    mechanicalExcel.setMotorOwerTilization(motorOwerTilization);
                    mechanicalExcel.setLiquidDensity(liquidDensity);
                    mechanicalExcel.setEficHead(eficHead);
                    mechanicalExcel.setEficipow(eficipow);
                    mechanicalExcel.setActiveTonliquSconsu(activeTonliquSconsu);
                    mechanicalExcel.setReactiveTonliquSconsu(reactiveTonliquSconsu);
                    mechanicalExcel.setSystemPower(systemPower);
                    mechanicalExcel.setBalanced(balanced);
                    mechanicalExcel.setPumpEfic(pumpEfic);
                    //计算数据评价封装
                    //* blockPermeability:   区块渗透率:中-1, 高-1, 低-1.4, 特低-1.6, 超低-1,7
                    String permeabilitK1 = String.valueOf(PumpUnitFormula.permeabilitK1(mechanicalExcel.getBlockPermeability()));
                    // pumpDepth ;泵挂 pumpDepth>1500-1, 1500 <= pumpDepth <=2500 -1.05,  pumpDepth>2500-1.1
                    String pumpHangK2 = String.valueOf(PumpUnitFormula.pumpHangK2(mechanicalExcel.getPumpDepth()));
                    // *wellTraj : 井眼轨迹:定向井-1.05,直井-1
                    String wellTrajK3 = String.valueOf(PumpUnitFormula.wellTrajK3(mechanicalExcel.getWellTraj()));
                    //功率因数评价指标
                    mechanicalExcel.setPowerFactorEvaIndex(row.getCell(67).toString().replace("≧", ""));
                    // * permeabilitK1:渗透率k1
                    //     * pumpHangK2:泵挂
                    //     * wellTrajK3:井眼轨迹
                    String thinOilLimitValue = String.valueOf(PumpUnitFormula.thinOilLimitValue(Double.parseDouble(permeabilitK1), Double.parseDouble(pumpHangK2), Double.parseDouble(wellTrajK3)));
                    //稀油井系统效率评价指标(节能评价值)
                    String thinOilEvaValue = String.valueOf(PumpUnitFormula.thinOilEvaValue(Double.parseDouble(permeabilitK1), Double.parseDouble(pumpHangK2), Double.parseDouble(wellTrajK3)));
                    ;

                    //稠油热采井系统效率评价指标(限定值)
                    mechanicalExcel.setThickenedOilLimitValue(row.getCell(70).toString());
                    //稠油热采井系统效率评价指标（节能评价值）
                    mechanicalExcel.setThickenedOilEvaValue(row.getCell(71).toString());
                    //TODO系统效率评价指标(限定值)
                    String systemPowerLimitValue = String.valueOf(PumpUnitFormula.systemPowerLimitValue(mechanicalExcel.getOilType(), Double.parseDouble(thinOilLimitValue), Double.parseDouble(mechanicalExcel.getThickenedOilLimitValue())));
                    //TODO系统效率评价指标（节能评价值）
                    String systemPowerEvaValue = String.valueOf(PumpUnitFormula.systemPowerEvaValue(mechanicalExcel.getOilType(), Double.parseDouble(thinOilEvaValue), Double.parseDouble(mechanicalExcel.getThickenedOilEvaValue())));

                    /*平衡度评价指标*/
                    mechanicalExcel.setBalancedEvaIndex(row.getCell(74).toString());
                    // 功率因数评价结果
                    String powerFactorEvaResult = String.valueOf(PumpUnitFormula.powerFactorEvaResult(mechanicalExcel.getPowerFactor(), Double.parseDouble(mechanicalExcel.getPowerFactorEvaIndex())));
                    // 系统效率评价结果
                    String systemPowerEvaResult = String.valueOf(PumpUnitFormula.systemPowerEvaResult(mechanicalExcel.getSystemPower(), Double.parseDouble(systemPowerLimitValue), Double.parseDouble(systemPowerEvaValue)));
                    //平衡度评价结果
                    String balancedEvaResult = PumpUnitFormula.balancedEvaResult(mechanicalExcel.getBalanced());
                    //  综合评价
                    String comprehensiveEva = String.valueOf(PumpUnitFormula.comprehensiveEva(powerFactorEvaResult, systemPowerEvaResult, balancedEvaResult));
                    // 封装计算数据评价
                    mechanicalExcel.setPermeabilitK1(permeabilitK1);
                    mechanicalExcel.setPumpHangK2(pumpHangK2);
                    mechanicalExcel.setWellTrajK3(wellTrajK3);
                    mechanicalExcel.setThinOilLimitValue(thinOilLimitValue);
                    mechanicalExcel.setThinOilEvaValue(thinOilEvaValue);
                    mechanicalExcel.setSystemPowerLimitValue(systemPowerLimitValue);
                    mechanicalExcel.setSystemPowerEvaValue(systemPowerEvaValue);
                    mechanicalExcel.setPowerFactorEvaResult(powerFactorEvaResult);
                    mechanicalExcel.setSystemPowerEvaResult(systemPowerEvaResult);
                    mechanicalExcel.setBalancedEvaResult(balancedEvaResult);
                    mechanicalExcel.setComprehensiveEva(comprehensiveEva);

                    resultList.add(mechanicalExcel);

                }
            } else {
                String jsonString = JSON.toJSONString("请上传表格");
                System.out.println(jsonString);
            }
        } catch (Exception e) {
            falg = false;
            e.printStackTrace();
        }
        return resultList;
    }

}
