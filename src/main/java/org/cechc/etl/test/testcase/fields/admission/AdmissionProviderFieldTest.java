package org.cechc.etl.test.testcase.fields.admission;


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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class AdmissionProviderFieldTest extends Basic {
    @DataProvider(name = "inpAdmProvDataProvider")
    private Iterator<Object[]> patientBaseInfoIdentifierDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(AdmissionProviderFieldTest.class, method, context);
        ArrayList<HashMap<String, Object>> patientList = odsAdmissionFieldMapper.getInpAdmissionProvField(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "inpAdmProvDataProvider",description = "验证ods映射到obs的admission_provider的字段值一致")
    public void compare_odsInpAdmissionProvider_and_obsAdmissionProvider_field(HashMap<String, Object> odsPatientMap) throws Exception {
        List<Map<String, Object>> obsAdmissionProviderFields = getObsAdmissionProviderFields(odsPatientMap);
        final Properties mapRule = PropertiesLoaderUtils.loadAllProperties("admission/inpAdmissionProv.properties");
        compareOneToManyFields(odsPatientMap,obsAdmissionProviderFields,mapRule);
    }

    private List<Map<String,Object>> getObsAdmissionProviderFields(HashMap<String, Object> srcMap){
        String record_id = (String) srcMap.get("record_id");
        List<Map<String, Object>> obsAdmissionProvider = (List)obsAdmissionFieldMapper.getAdmissionProv(record_id);
        return obsAdmissionProvider;
    }




}
