package org.cechc.etl.test.testcase.count;


import org.cechc.etl.test.demo.DisableListener;
import org.cechc.etl.test.domain.DateTimeRange;
import org.cechc.etl.test.obsmapper.count.OBSPatientCountMapper;
import org.cechc.etl.test.odsmapper.count.ODSPatientCountMapper;
import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.util.DataProviderUtil;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.cechc.etl.test.util.SpringUtil;
import org.springframework.boot.test.context.TestConfiguration;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;


public class PatientCountTest extends Basic {


    @DataProvider(name = "patientInfoDataProvider")
    public Iterator<Object[]> patientInfoDataProvider(Method method, ITestContext context){
        Parameter parameter = TestUtil.getParamValue(PatientCountTest.class,method,context);

        HashMap<String, Object> basicPatSrcAndDestCount = get1to1SrcAndDestCount(odsPatientCountMapper::getBasicPatientIdList, obsPatientCountMapper::getPatientIdCount,parameter);
        HashMap<String, Object> casePatSrcAndDestCount = get1to1SrcAndDestCount(odsPatientCountMapper::getCasePatientList, obsPatientCountMapper::getPatientIdCount,parameter);

        ArrayList<HashMap<String, Object>> patSrcAndDestCount = toList(basicPatSrcAndDestCount, casePatSrcAndDestCount);

        return DataProviderUtil.getDataProvider4(patSrcAndDestCount);
    }

    @DataProvider(name = "patientIdentifierDataProvider")
    public Iterator<Object[]> patientIdentifierDataProvider(Method method, ITestContext context){
        Parameter parameter = TestUtil.getParamValue(PatientCountTest.class,method,context);

        HashMap<String, Object> basicPatIdSrcAndDestCount = get1toManySrcAndDestCount(odsPatientCountMapper::getPatientBasicInfoIdentifierList, obsPatientCountMapper::getPatientIdentifierCountByList,parameter);
        HashMap<String, Object> casePatIdSrcAndDestCount = get1toManySrcAndDestCount(odsPatientCountMapper::getCaseBasePatientIdentifierList, obsPatientCountMapper::getPatientIdentifierCountByList,parameter);

        ArrayList<HashMap<String, Object>> PatIdSrcAndDestCount = toList(basicPatIdSrcAndDestCount, casePatIdSrcAndDestCount);

        return DataProviderUtil.getDataProvider4(PatIdSrcAndDestCount);
    }


    @DataProvider(name = "patientAddressDataProvider")
    public Iterator<Object[]> patientAddressDataProvider(Method method, ITestContext context){
        Parameter parameter = TestUtil.getParamValue(PatientCountTest.class,method,context);


        HashMap<String, Object> basicPatAddrSrcAndDestCount = get1toManySrcAndDestCount(odsPatientCountMapper::getPatientBasicInfoAddressCountList, obsPatientCountMapper::getPatientAddressCount,parameter);
        HashMap<String, Object> casePatAddrSrcAndDestCount = get1toManySrcAndDestCount(odsPatientCountMapper::getCaseBaseInfoPatientAddressCountList, obsPatientCountMapper::getPatientAddressCount,parameter);
        ArrayList<HashMap<String, Object>> patAddrSrcAndDestCount = toList(basicPatAddrSrcAndDestCount,casePatAddrSrcAndDestCount);

        return DataProviderUtil.getDataProvider4(patAddrSrcAndDestCount);
    }

    @DataProvider(name = "patientHxDataProvider")
    public Iterator<Object[]> patientHxDataProvider(Method method, ITestContext context){
        Parameter parameter = TestUtil.getParamValue(PatientCountTest.class,method,context);


        HashMap<String, Object> PatHxSrcAndDestCount = get1toManySrcAndDestCount(odsPatientCountMapper::getPatientHxCount, obsPatientCountMapper::getPatientHxCount,parameter);
        ArrayList<HashMap<String, Object>> PatHxSrcAndDestCounts = toList(PatHxSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(PatHxSrcAndDestCounts);


    }
    @Test(dataProvider = "patientInfoDataProvider",description = "根据时间范围，验证ods的patient_basic_info表和case_base_info表的条数与obs的patient表条数一致")
    public void compare_odsPatientCount_and_obsPatientCount(ArrayList<HashMap<String, Object>> patSrcAndDestCount){
        compare(patSrcAndDestCount);

    }


    @Test(dataProvider = "patientIdentifierDataProvider",description = "根据时间范围，验证ods的patient_basic_info表和case_base_info表的条数与obs的patient_identifier表条数一致")
    public void compare_odsPatientIdentifierCount_and_obsPatientIdentifierCount(ArrayList<HashMap<String, Object>> patSrcAndDestCount){
        compare(patSrcAndDestCount);

    }

    @Test(dataProvider = "patientAddressDataProvider",description = "根据时间范围，验证ods的patient_basic_info表和case_base_info表的条数与obs的patient_address表条数一致")
    public void compare_odsPatientAddressCount_and_obsPatientAddressCount(ArrayList<HashMap<String, Object>> patSrcAndDestCount){
        compare(patSrcAndDestCount);

    }

    @Test(dataProvider = "patientHxDataProvider",description = "根据时间范围，验证ods的patient_basic_info表和case_base_info表的条数与obs的patient_Hx表条数一致")
    public void compare_odsPatientHxCount_and_obsPatientHxCount(ArrayList<HashMap<String, Object>> patSrcAndDestCount){
        compare(patSrcAndDestCount);

    }

//
//    /**
//     * 测试标题：对比ODS与OBS的patient表条数
//     * 测试步骤：对比ODS的
//     * patient_basic_info表的条数
//     * 加上case_base_info表的条数
//     * 与OBS的patient表条数
//     * 期望结果：条数一致
//     */
//    @Test(dataProvider = "dictSrcAndDestCountsDataProvider")
//    public void compare_patient_count(HashMap<String,ArrayList<HashMap<String, Object>>> srcAndDestCount) {
//        Collection<ArrayList<HashMap<String, Object>>> values = srcAndDestCount.values();
//        Set<String> keys = srcAndDestCount.keySet();
//        for(String key:keys){
//            ArrayList<HashMap<String, Object>> hashMaps = srcAndDestCount.get(key);
//            compare(hashMaps);
//        }
////        System.out.println("hhello");
//
////        Collection<ArrayList<HashMap<String, Object>>> values = srcAndDestCount.values();
////        compare(values.);
////        for(ArrayList<HashMap<String, Object>> value : values){
////            compare(value);
////        }
////        compare(srcAndDestCount);
////        HashMap<String, Object> basicPatSrcAndDestCount = get1to1SrcAndDestCount(odsPatientCountMapper::getBasicPatientIdList, obsPatientCountMapper::getPatientIdCount);
////        HashMap<String, Object> casePatSrcAndDestCount = get1to1SrcAndDestCount(odsPatientCountMapper::getCasePatientList, obsPatientCountMapper::getPatientIdCount);
////        compare(basicPatSrcAndDestCount, casePatSrcAndDestCount);
//
//    }
//
//
//
//
//
//
//
//    /**
//     * 测试标题：对比ODS与OBS的patient_attribution表条数
//     * 测试步骤：没有做patient_attribution表的映射，所以patient_attribution表的条数应为0
//     * 期望结果：条数为0
//     */
//    @Test
//    public void compare_patient_attribution_count() {
//        long odsPatientAttributionCount = 0;
//        long obsPatientAttributionCount = obsPatientCountMapper.getPatientAttributionCount();
//
//        Assert.assertEquals(odsPatientAttributionCount, obsPatientAttributionCount);
//    }
//
//
//    /**
//     * 测试标题：对比ODS与OBS的patient_insurance表条数
//     * 测试步骤：对比ODS的
//     * patient_basic_info表与insurance关联的条数
//     * 加上case_base_info表与insurance关联的条数
//     * 与OBS的patient_insurance表条数
//     * 期望结果：条数一致
//     */
//    @Test
//    public void compare_patient_insurance_count() {
//        long odsInsuranceCount = 0;
//        long obsPatientInsuranceCount = obsPatientCountMapper.getPatientInsuranceCount();
//
//        Assert.assertEquals(odsInsuranceCount, obsPatientInsuranceCount);
//
//
//    }
//
//
//
//    /**
//     * 测试标题：对比ODS与OBS的patient_program表条数
//     * 测试步骤：因为没有做patient_program表的映射，所以patient_program表的条数应为0
//     * 期望结果：条数为0
//     */
//    @Test
//    public void compare_patient_program_count() {
//        long odsPatientProgramCount = 0;
//        long obsPatientProgramCount = obsPatientCountMapper.getPatientProgramCount();
//
//        Assert.assertEquals(odsPatientProgramCount, obsPatientProgramCount);
//
//    }
//
//    /**
//     * 插入数据
//     */
////    @Test
////    public void insertData() {
//        /*向case patient 里插入数据*/
////        Random rand = new Random();
////        for(int i=999999; i<100000001; i++){
////            String cecmid_id = Integer.toString(i);
////            String create_datetime = LocalDateTime.now().toString();
////            String patient_id = UUID.randomUUID().toString();
////            String patient_name = "测试人员"+i;
////            String electronic_health_card_no = Integer.toString(rand.nextInt(100000)+ 1);
////            String medical_safety_class_code= Integer.toString(rand.nextInt(100000)+ 1);
////            String id_card_no= Integer.toString(rand.nextInt(100000)+ 1);
////            String residence_health_card_no= Integer.toString(rand.nextInt(100000)+ 1);
////            String insur_card_no= Integer.toString(rand.nextInt(100000)+ 1);
////            String medical_safety_no = Integer.toString(rand.nextInt(100000)+ 1);
////            odsPatientCountMapper.insertDataToCasePatient(cecmid_id,create_datetime,patient_id,patient_name,
////                    electronic_health_card_no,medical_safety_class_code,id_card_no,residence_health_card_no,medical_safety_no,insur_card_no);
////        }
//
////        /*向basic patient info里插入数据*/
////        Random rand = new Random();
////        for (int i = 6; i < 100001; i++) {
////            String cecmid_id = Integer.toString(i);
////            String modify_datetime = LocalDateTime.now().toString();
////            String create_datetime = LocalDateTime.now().toString();
////            String patient_id = UUID.randomUUID().toString();
////            String outp_case_no = Integer.toString(rand.nextInt(100000) + 1);
////            String inp_case_no = Integer.toString(rand.nextInt(100000) + 1);
////            String outp_medical_no = Integer.toString(rand.nextInt(100000) + 1);
////            String patient_name = "汤茜" + i;
////            odsPatientCountMapper.insertDataToBasicPatient(cecmid_id, modify_datetime, create_datetime, patient_id, outp_case_no,
////                    inp_case_no, outp_medical_no, patient_name);
////        }
////    }
//
//
////    @Test
////    public void test1() {
////        get1toManySrcAndDestCount(odsPatientCountMapper::getPatientBasicInfoIdentifierList, obsPatientCountMapper::getPatientIdentifierCountByList);
////    }


}
