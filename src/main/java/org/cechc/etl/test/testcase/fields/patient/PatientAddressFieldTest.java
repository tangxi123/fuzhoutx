package org.cechc.etl.test.testcase.fields.patient;



import org.cechc.etl.test.obsmapper.field.OBSPatientFieldMapper;
import org.cechc.etl.test.odsmapper.field.ODSPatientFieldMapper;
import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.util.DataProviderUtil;
import org.cechc.etl.test.testcase.util.TargetFieldComputerUtil;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.cechc.etl.test.util.SpringUtil;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.*;

import static java.lang.Integer.parseInt;


public class PatientAddressFieldTest extends Basic {



    @DataProvider(name = "patientAddressDataProvider")
    private Iterator<Object[]> patientBaseInfoIdentifierDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(PatientAddressFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsPatientFieldMapper.getBaseInfoPatientAddreList(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "patientAddressDataProvider")
    public void compare_odsPatientAddr_and_obsPatientAddr_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsPatientFieldMapper.getPatientAddrInfoByPatientId(patient_id);
        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("patient/patientBasicAddrTransferMapConfig.properties");
        //TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
        TargetFieldComputerUtil.testTang((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }

}
