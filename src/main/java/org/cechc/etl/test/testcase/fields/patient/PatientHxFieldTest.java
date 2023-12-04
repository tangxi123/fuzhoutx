package org.cechc.etl.test.testcase.fields.patient;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.util.DataProviderUtil;
import org.cechc.etl.test.testcase.util.TargetFieldComputerUtil;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.*;


@SpringBootTest
public class PatientHxFieldTest extends Basic {
    @DataProvider(name = "patientHxList")
    private Iterator<Object[]> patientListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(PatientHxFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsPatientFieldMapper.getPatientHxList(param);
        return DataProviderUtil.getDataProvider(patientList);

    }


    @Test(dataProvider = "patientHxList")
    public void compare_odsPatientHx_and_obsPatientHx_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("patient_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsPatientFieldMapper.getPatientHxByPatientId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("patient/patientHxMapConfig.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
}
