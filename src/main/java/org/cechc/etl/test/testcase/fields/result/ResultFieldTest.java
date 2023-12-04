package org.cechc.etl.test.testcase.fields.result;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.fields.provider.ProviderFieldTest;
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


public class ResultFieldTest extends Basic {
    @DataProvider(name = "resultListDataProvider")
    private Iterator<Object[]> resultListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(ResultFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsResultFieldMapper.getResultField(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "resultListDataProvider",description = "验证ods的lis_report_detail表字段映射到obs的result表")
    public void compare_odsLisResult_and_obsResult_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object patient_id = odsPatientMap.get("record_id");
        HashMap<String, Object> obsPatientInfo = obsResultFieldMapper.getResultField((String) patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("result/result.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }

}
