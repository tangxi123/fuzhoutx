package org.cechc.etl.test.testcase.fields.patient;



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
import java.util.function.Function;


public class PatientIdentifierFieldTest extends Basic {

    @DataProvider(name = "patientBaseIdentifierDataProvider")
    private Iterator<Object[]> patientBaseInfoIdentifierDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(PatientIdentifierFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsPatientFieldMapper.getBaseInfoPatientIdentifierList(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "patientCaseIdentifierDataProvider")
    private Iterator<Object[]> patientIdentifierDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(PatientIdentifierFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsPatientFieldMapper.getCaseBasePatientIdentifierList(param);
        return DataProviderUtil.getDataProvider(patientList);
    }



    @Test(dataProvider = "patientBaseIdentifierDataProvider")
    public void compare_obsBaseInfoPatientIdentifier_and_odsBaseInfoPatientIdentifier_field(HashMap<String, Object> odsPatientMap) throws Exception {
        //1.对比条数
        //2.对比字段
        List<Map<String, Object>> obsPatientIdentifierInfos = getObsInfosByRecordId(odsPatientMap,obsPatientFieldMapper::getPatientIdentifierInfoByPatientId);
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("patient/patientBasicIdentifierTransferMapConfig.properties");
        compareOneToManyFields(odsPatientMap,obsPatientIdentifierInfos,properties);
    }

    @Test(dataProvider = "patientCaseIdentifierDataProvider")
    public void compare_obsCaseInfoPatientIdentifier_and_odsCaseInfoPatientIdentifier_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("patient_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsPatientFieldMapper.getPatientIdentifierInfoByPatientId(patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("patient/patientCaseIdentifierTransferMapConfig.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos,softAssert,(ConfigurableEnvironment) environment);
    }

    private List<Map<String, Object>> getObsInfosByRecordId(HashMap<String, Object> odsMap, Function<String, List<Map<String, Object>>> obsMapper) {
        String record_id = (String) odsMap.get("record_id");
        List<Map<String, Object>> obsPatientInfo = applyObsMapper(record_id, obsMapper);
        return obsPatientInfo;

    }

}
