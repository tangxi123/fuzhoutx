package org.cechc.etl.test.testcase.fields.diagnosis;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.fields.admission.AdmissionFieldTest;
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


public class DiagnosisLocationTest extends Basic {
    @DataProvider(name = "caseDiagLocDataProvider")
    private Iterator<Object[]> caseDiagLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(DiagnosisLocationTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsDiagnosisFieldMapper.getCaseLocDiagnosis(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @DataProvider(name = "inpDiagLocDataProvider")
    private Iterator<Object[]> inpDiagLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(DiagnosisLocationTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsDiagnosisFieldMapper.getInpLocDiagnosis(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @DataProvider(name = "outpDiagLocDataProvider")
    private Iterator<Object[]> outpDiagLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(DiagnosisLocationTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsDiagnosisFieldMapper.getOutpLocDignosis(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @Test(dataProvider = "caseDiagLocDataProvider")
    public void compare_odsCaseDiagnosisLoc_and_obsDiagnosisLocation(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsDiagnosisFieldMapper.getDiagnosisLoc(record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("diagnosis/caseLocDiag.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
    @Test(dataProvider = "inpDiagLocDataProvider")
    public void compare_odsInpDiagnosisLoc_and_obsDiagnosisLocation(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos =(List)obsDiagnosisFieldMapper.getDiagnosisLoc(record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("diagnosis/inpLocDiag.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
    @Test(dataProvider = "outpDiagLocDataProvider")
    public void compare_odsOutpDiagnosisLoc_and_obsDiagnosisLocation(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsDiagnosisFieldMapper.getDiagnosisLoc(record_id);
        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("diagnosis/outpLocDiag.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
}
