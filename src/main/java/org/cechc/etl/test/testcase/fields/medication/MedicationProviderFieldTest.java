package org.cechc.etl.test.testcase.fields.medication;


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

public class MedicationProviderFieldTest extends Basic {
    @DataProvider(name = "outpMedicaProDataProvider")
    private Iterator<Object[]> patientBaseInfoIdentifierDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(MedicationProviderFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsMedicationFieldMapper.getOutpMedicaProv(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "inptpMedicaProDataProvider")
    private Iterator<Object[]> inpMedicaProDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(MedicationProviderFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsMedicationFieldMapper.getInpMedicaProv(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "outpMedicaProDataProvider",description = "验证ods的outp_order_master表字段映射到obs的medication_provider")
    public void compare_outpMedicationProvider_and_obsMedicationProvider_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsMedicationFieldMapper.getMedicProviderByrecordId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("medication/outpMedicationPro.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }

    @Test(dataProvider = "inptpMedicaProDataProvider",description = "验证ods的outp_order_master表字段映射到obs的medication_provider")
    public void compare_inpMedicationProvider_and_obsMedicationProvider_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsMedicationFieldMapper.getMedicProviderByrecordId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("medication/inpMedicationPro.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
}
