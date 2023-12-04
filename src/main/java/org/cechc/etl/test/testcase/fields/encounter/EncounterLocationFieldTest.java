package org.cechc.etl.test.testcase.fields.encounter;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.fields.diagnosis.DiagnosisLocationTest;
import org.cechc.etl.test.testcase.util.DataProviderUtil;
import org.cechc.etl.test.testcase.util.TargetFieldComputerUtil;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.*;


public class EncounterLocationFieldTest extends Basic {
    @DataProvider(name = "outpEncLocDataProvider")
    private Iterator<Object[]> outpEncLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(EncounterLocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList =odsEncounterFieldMapper.getOutpEncLocFields(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "inpEncLocDataProvider")
    private Iterator<Object[]> inpEncLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(EncounterLocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList =odsEncounterFieldMapper.getInpEncLocFields(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "caseEncLocDataProvider")
    private Iterator<Object[]> caseEncLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(EncounterLocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList =odsEncounterFieldMapper.getCaseEncLocFields(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "outpEncLocDataProvider",description = "验证ods的outp_visit_record表字段是否正确映射到了obs的EncounterLocation表")
    public void compare_odsOutpEncounterLocation_and_obsEncounterLocation(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("outp_visit_no");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsEncounterFieldMapper.getEncLocFieldByRecordId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("encounter/outpEncLoc.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }

    @Test(dataProvider = "inpEncLocDataProvider",description = "验证ods的inp_visit_record表字段是否正确映射到了obs的EncounterLocation表")
    public void compare_odsInpEncounterLocation_and_obsEncounterLocation(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("inp_visit_no");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsEncounterFieldMapper.getEncLocFieldByRecordId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("encounter/inpEncLoc.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }

    @Test(dataProvider = "caseEncLocDataProvider",description = "验证ods的case_residence_record表字段是否正确映射到了obs的EncounterLocation表")
    public void compare_odsCaseEncounterLocation_and_obsEncounterLocation(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("inp_visit_no");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsEncounterFieldMapper.getEncLocFieldByRecordId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("encounter/caseEncLoc.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
}
