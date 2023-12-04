package org.cechc.etl.test.testcase.fields.diagnosis;


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


public class DiagnosisProviderTest extends Basic {
    @DataProvider(name = "caseDiagProvDataProvider")
    private Iterator<Object[]> caseDiagProvDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(DiagnosisProviderTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsDiagnosisFieldMapper.getCaseProvDiagnosis(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @DataProvider(name = "inpDiagProvDataProvider")
    private Iterator<Object[]> inpDiagProvDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(DiagnosisProviderTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsDiagnosisFieldMapper.getInpProvDiagnosis(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @DataProvider(name = "outpDiagProvDataProvider")
    private Iterator<Object[]> patientBaseInfoIdentifierDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(DiagnosisProviderTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsDiagnosisFieldMapper.getOutpProvDignosis(param);
        return DataProviderUtil.getDataProvider(patientList);
    }
    @Test(dataProvider = "caseDiagProvDataProvider")
    public void compare_odsCaseDiagnosisProvider_and_obsDiagnosisProvider_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsDiagnosisFieldMapper.getDiagnosisProv(record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("diagnosis/caseProvDiag.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
    @Test(dataProvider = "inpDiagProvDataProvider")
    public void compare_odsInpDiagnosisProvider_and_obsDiagnosisProvider_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos =(List)obsDiagnosisFieldMapper.getDiagnosisProv(record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("diagnosis/inpProvDiag.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
    @Test(dataProvider = "outpDiagProvDataProvider")
    public void compare_odsOutpDiagnosisProvider_and_obsDiagnosisProvider_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsDiagnosisFieldMapper.getDiagnosisProv(record_id);
        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("diagnosis/outpProvDiag.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
}
