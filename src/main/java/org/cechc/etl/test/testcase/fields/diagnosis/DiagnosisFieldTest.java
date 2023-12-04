package org.cechc.etl.test.testcase.fields.diagnosis;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.fields.admission.AdmissionFieldTest;
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


public class DiagnosisFieldTest extends Basic {
    @DataProvider(name = "caseDiagDataProvider")
    private Iterator<Object[]> caseDiagDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(DiagnosisFieldTest.class, method, context);
        ArrayList<HashMap<String, Object>> patientList = odsDiagnosisFieldMapper.getCaseDiagnosis(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "outpDiagDataProvider")
    private Iterator<Object[]> outpDiagDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(DiagnosisFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsDiagnosisFieldMapper.getOutpDignosis(param);
        return DataProviderUtil.getDataProvider(patientList);
    }


    @Test(dataProvider = "caseDiagDataProvider",description = "验证ods映射到obs的diagnosis字段值一致")
    public void compare_odsCaseDiagnosis_and_obsDiagnosis_field(HashMap<String, Object> odsPatientMap) throws Exception {
        HashMap<String, Object> obsCaseDiagnosisField = getObsCaseDiagnosisField(odsPatientMap);
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("diagnosis/caseDiag.properties");
        compareOneToOneField(odsPatientMap,obsCaseDiagnosisField,properties);
    }

    @Test(dataProvider = "outpDiagDataProvider")
    public void compare_odsOutpDiagnosis_and_obsDiagnosis_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object patient_id = odsPatientMap.get("record_id");
        HashMap<String, Object> obsPatientInfo = obsDiagnosisFieldMapper.getDiagnosisField((String) patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("diagnosis/outpDiag.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }

    private HashMap<String, Object> getObsCaseDiagnosisField(HashMap<String, Object> odsPatientMap){
        Object patient_id = odsPatientMap.get("record_id");
        HashMap<String, Object> obsCaseDiagnosisField = obsDiagnosisFieldMapper.getDiagnosisField((String) patient_id);
        return obsCaseDiagnosisField;

    }


}
