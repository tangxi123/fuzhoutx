package org.cechc.etl.test.testcase.fields.encounter;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
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

public class EncounterProviderFieldTest extends Basic {
    @DataProvider(name = "outpEncProvDataProvider")
    private Iterator<Object[]> outpEncProvDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(EncounterProviderFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList =odsEncounterFieldMapper.getOutpEncProvFields(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @DataProvider(name = "inpEncProvDataProvider")
    private Iterator<Object[]> inpEncProvDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(EncounterProviderFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsEncounterFieldMapper.getInpEncProvFields(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @DataProvider(name = "caseEncProvDataProvider")
    private Iterator<Object[]> caseEncProvDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(EncounterProviderFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsEncounterFieldMapper.getCaseEncProvFields(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @Test(dataProvider = "outpEncProvDataProvider",description = "验证ods的outp_visit_record表字段是否正确映射到了obs的EncounterProvider表")
    public void compare_odsOutpEncounterProvider_and_obsEncounterProvider(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("outp_visit_no");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsEncounterFieldMapper.getEncProFieldByRecordId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("encounter/outpEncProv.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
    @Test(dataProvider = "inpEncProvDataProvider",description = "验证ods的inp_visit_record表字段是否正确映射到了obs的EncounterProvider表")
    public void compare_odsInpEncounterProvider_and_obsEncounterProvider(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("inp_visit_no");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsEncounterFieldMapper.getEncProFieldByRecordId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("encounter/inpEncProv.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
    @Test(dataProvider = "caseEncProvDataProvider",description = "验证ods的case_visit_record表字段是否正确映射到了obs的EncounterProvider表")
    public void compare_odsCaseEncounterProvider_and_obsEncounterProvider(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("inp_visit_no");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsEncounterFieldMapper.getEncProFieldByRecordId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("encounter/caseEncProv.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }


}
