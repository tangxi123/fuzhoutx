package org.cechc.etl.test.testcase.fields.provider;


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


public class ProviderFieldTest extends Basic {

    @DataProvider(name = "providerInfo")
    private Iterator<Object[]> providerInfoListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(ProviderFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> providerList = odsProviderFieldMapper.getProviderInfo(param);
        return DataProviderUtil.getDataProvider(providerList);

    }

    @Test(dataProvider = "providerInfo",description = "验证ods的dict_staff_dict表的字段映射到obs的provider")
    public void compare_obsDictProvider_and_obsProvider_field(HashMap<String, Object> odsProviderMap) throws Exception {
        Object patient_id = odsProviderMap.get("staff_no");
        HashMap<String, Object> obsPatientInfo = obsProviderFieldMapper.getProviderInfoByProviderId((String) patient_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("provider/provider.properties");
        TargetFieldComputerUtil.test((Map) properties, obsPatientInfo, obsPatientInfo, softAssert);
    }


}
