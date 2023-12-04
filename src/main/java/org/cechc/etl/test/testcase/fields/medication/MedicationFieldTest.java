package org.cechc.etl.test.testcase.fields.medication;


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


public class MedicationFieldTest extends Basic {
    @DataProvider(name = "outpMedicationListDataProvider")
    private Iterator<Object[]> outpMedicationListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(MedicationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsMedicationFieldMapper.getOutpMedication(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "inpMedicationListDataProvider")
    private Iterator<Object[]> patientBaseInfoListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(MedicationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsMedicationFieldMapper.getInpMedication(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "outpMedicationListDataProvider",description = "验证ods的outp_order_master的字段是否映射到obs的Medication")
    public void compare_odsOutpMedication_and_obsMedication(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String)odsPatientMap.get("record_id");

        HashMap<String, Object> obsPatientInfo = obsMedicationFieldMapper.getMedicByrecordId((String)patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("medication/outpMedication.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }

    @Test(dataProvider = "inpMedicationListDataProvider",description = "验证ods的inp_order_record的字段是否映射到obs的Medication")
    public void compare_odsInpMedication_and_obsMedication(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String)odsPatientMap.get("record_id");

        HashMap<String, Object> obsPatientInfo = obsMedicationFieldMapper.getMedicByrecordId((String)patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("medication/inpMedication.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }
}
