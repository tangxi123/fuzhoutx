package org.cechc.etl.test.testcase.fields.admission;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
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

public class AdmissionFieldTest extends Basic {

    @DataProvider(name = "inpAdmissionDataProvider")
    private Iterator<Object[]> inpAdmissionDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(AdmissionFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsAdmissionFieldMapper.getInpAdmissionField(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "inpAdmissionDataProvider",description = "验证ods映射到admission的字段值一致")
    public void compare_odsInpAdmission_and_obsAdmission_filed(HashMap<String, Object> odsPatientMap) throws Exception {
        HashMap<String, Object> obsAdmissionFields = getObsAdmissionFields(odsPatientMap);
        final Properties mapRule = PropertiesLoaderUtils.loadAllProperties("admission/inpAdmission.properties");
        compareOneToOneField(odsPatientMap,obsAdmissionFields,mapRule);
    }

    private  HashMap<String, Object> getObsAdmissionFields(HashMap<String, Object> odsPatientMap){
        Object record_id = odsPatientMap.get("record_id");
        HashMap<String, Object> obsPatientInfo = obsAdmissionFieldMapper.getAdmissionField((String) record_id);
        return obsPatientInfo;
    }



}
