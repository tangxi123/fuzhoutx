package org.cechc.etl.test.testcase.fields.result;


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


public class ResultLocationFieldTest extends Basic {
    @DataProvider(name = "resultLocDataProvider")
    private Iterator<Object[]> resultLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(ResultLocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsResultFieldMapper.getResultLocation(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "resultLocDataProvider",description = "验证ods的lis_report_detail表字段映射到obs的result_location表")
    public void compare_odsLisResultLocation_and_obsResultLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsResultFieldMapper.getResultLocField(patient_id);
        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("result/resultLoc.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
}
