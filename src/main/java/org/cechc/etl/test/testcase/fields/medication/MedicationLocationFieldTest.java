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

public class MedicationLocationFieldTest extends Basic {

    @DataProvider(name = "outpMedicaLocDataProvider")
    private Iterator<Object[]> patientBaseInfoIdentifierDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(MedicationLocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsMedicationFieldMapper.getOutpMedicaLoc(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "inptpMedicaLocDataProvider")
    private Iterator<Object[]> inpMedicaProDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(MedicationLocationFieldTest.class, method, context);


        ArrayList<HashMap<String, Object>> patientList = odsMedicationFieldMapper.getInpMedicaLoc(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "outpMedicaLocDataProvider",description = "验证ods的outp_order_master表的字段映射到obs的medicationLocation表")
    public void compare_odsOutpMedicationLoc_and_obsMedicationLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsMedicationFieldMapper.getMedicLocByrecordId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("medication/outpMedicationLoc.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }

    @Test(dataProvider = "inptpMedicaLocDataProvider",description = "验证ods的inp_order_record表的字段映射到obs的medicationLocation表")
    public void compare_odsInpMedicationLoc_and_obsMedicationLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsMedicationFieldMapper.getMedicLocByrecordId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("medication/inpMedicationLoc.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
}