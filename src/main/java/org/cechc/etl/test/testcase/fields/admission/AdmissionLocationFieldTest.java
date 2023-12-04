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

import java.lang.reflect.Method;
import java.util.*;

public class AdmissionLocationFieldTest extends Basic {
    @DataProvider(name = "inpAdmLocDataProvider")
    private Iterator<Object[]> patientBaseInfoIdentifierDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(AdmissionLocationFieldTest.class, method, context);
        ArrayList<HashMap<String, Object>> patientList = odsAdmissionFieldMapper.getInpAdmissionLocField(param);
        return DataProviderUtil.getDataProvider(patientList);
    }



    @Test(dataProvider = "inpAdmLocDataProvider",description = "验证ods映射到obs的admission_location字段值一致")
    public void compare_odsInpAdmissionLoc_and_obsAdmissionLoc_field(HashMap<String, Object> odsPatientMap) throws Exception {
        List<Map<String, Object>> obsAdmissionLocationFields = getObsAdmissionLocationFields(odsPatientMap);
        final Properties mapRule = PropertiesLoaderUtils.loadAllProperties("admission/inpAdmissionLoc.properties");
        compareOneToManyFields(odsPatientMap,obsAdmissionLocationFields,mapRule);
    }

    private List<Map<String,Object>> getObsAdmissionLocationFields(HashMap<String, Object> srcMap){
        String record_id = (String) srcMap.get("record_id");
        List<Map<String, Object>> obsAdmissionProvider = (List)obsAdmissionFieldMapper.getAdmissionLoc(record_id);
        return obsAdmissionProvider;
    }

}
