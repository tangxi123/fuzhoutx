package org.cechc.etl.test.testcase.fields.location;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.fields.encounter.EncounterFieldTest;
import org.cechc.etl.test.testcase.util.DataProviderUtil;
import org.cechc.etl.test.testcase.util.TargetFieldComputerUtil;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.*;


public class LocationFieldTest extends Basic {
    @DataProvider(name = "outpKeshiLocDataProvider")
    private Iterator<Object[]> outpKeshiLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(LocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsLocationFieldMapper.getOutpKeshiLoc(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "inpKeshiLocDataProvider")
    private Iterator<Object[]> inpKeshiLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(LocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsLocationFieldMapper.getInpKeshiLoc(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "inpBingquLocDataProvider")
    private Iterator<Object[]> inpBingquLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(LocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsLocationFieldMapper.getInpBingquLoc(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "inpBingfangLocDataProvider")
    private Iterator<Object[]> inpBingfangLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(LocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsLocationFieldMapper.getInpBingfangLoc(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "inpBedLocDataProvider")
    private Iterator<Object[]> inpBedLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(LocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsLocationFieldMapper.getInpBedLoc(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "outpKeshiLocDataProvider",description = "验证ods的outp_visit_record表科室数据映射到obs的Location")
    public void compare_odsOutpKeshiLoc_and_obsLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object record_id = odsPatientMap.get("record_id");
        Object src_id = odsPatientMap.get("org_code");
        HashMap<String, Object> obsPatientInfo = obsLocationFieldMapper.getLocationByRecordIdAndSrcId((String) record_id,(String) src_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("location/outpKeshiLoc.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }

    @Test(dataProvider = "inpKeshiLocDataProvider",description = "验证ods的inp_visit_record表科室数据映射到obs的Location")
    public void compare_odsInpKeshiLoc_and_obsLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object record_id = odsPatientMap.get("record_id");
        Object src_id = odsPatientMap.get("org_code");
        HashMap<String, Object> obsPatientInfo = obsLocationFieldMapper.getLocationByRecordIdAndSrcId((String) record_id,(String) src_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("location/inpKeshiLoc.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }

    @Test(dataProvider = "inpBingquLocDataProvider",description = "验证ods的inp_visit_record表病区数据映射到obs的Location")
    public void compare_odsInpBingquLoc_and_obsLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object record_id = odsPatientMap.get("record_id");
        Object src_id = odsPatientMap.get("org_code");
        HashMap<String, Object> obsPatientInfo = obsLocationFieldMapper.getLocationByRecordIdAndSrcId((String) record_id,(String) src_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("location/inpBingquLoc.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }

    @Test(dataProvider = "inpBingfangLocDataProvider",description = "验证ods的inp_visit_record表病房数据映射到obs的Location")
    public void compare_odsInpBingfangLoc_and_obsLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object record_id = odsPatientMap.get("record_id");
        Object src_id = odsPatientMap.get("org_code");
        HashMap<String, Object> obsPatientInfo = obsLocationFieldMapper.getLocationByRecordIdAndSrcId((String) record_id,(String) src_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("location/inpBingfangLoc.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }

    @Test(dataProvider = "inpBedLocDataProvider",description = "验证ods的inp_visit_record表病床数据映射到obs的Location")
    public void compare_odsInpBedLoc_and_obsLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object record_id = odsPatientMap.get("record_id");
        Object src_id = odsPatientMap.get("org_code");
        HashMap<String, Object> obsPatientInfo = obsLocationFieldMapper.getLocationByRecordIdAndSrcId((String) record_id,(String) src_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("location/inpBedLoc.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }
}
