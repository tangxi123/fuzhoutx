package org.cechc.etl.test.testcase.fields.encounter;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.fields.diagnosis.DiagnosisLocationTest;
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


public class EncounterFieldTest extends Basic {

    @DataProvider(name = "outpEncDataProvider")
    private Iterator<Object[]> patientBaseInfoListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(EncounterFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsEncounterFieldMapper.getOutpEncFields(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @DataProvider(name = "inpEncDataProvider")
    private Iterator<Object[]> inpEncDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(EncounterFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsEncounterFieldMapper.getInpEncFields(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @DataProvider(name = "caseEncDataProvider")
    private Iterator<Object[]> caseEncDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(EncounterFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsEncounterFieldMapper.getCaseEncFields(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "outpEncDataProvider",description = "验证ods的outp_visit_record表字段是否正确映射到了obs的Encounter表")
    public void compare_odsOutpEncounter_and_obsOutpEncounter_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object record_id = odsPatientMap.get("outp_visit_no");
        HashMap<String, Object> obsPatientInfo = obsEncounterFieldMapper.getEncFieldByRecordId((String)record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("encounter/outpEnc.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }


    @Test(dataProvider = "inpEncDataProvider",description = "验证ods的inp_visit_record表字段是否正确映射到了obs的Encounter表")
    public void compare_odsInpEncounter_and_obsInpEncounter_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object record_id = odsPatientMap.get("inp_visit_no");
        HashMap<String, Object> obsPatientInfo = obsEncounterFieldMapper.getEncFieldByRecordId((String)record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("encounter/inpEnc.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }


    @Test(dataProvider = "caseEncDataProvider",description = "验证ods的case_visit_record表字段是否正确映射到了obs的Encounter表")
    public void compare_odsCaseEncounter_and_obsCaseEncounter_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object record_id = odsPatientMap.get("inp_visit_no");
        HashMap<String, Object> obsPatientInfo = obsEncounterFieldMapper.getEncFieldByRecordId((String)record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("encounter/caseEnc.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }

}
