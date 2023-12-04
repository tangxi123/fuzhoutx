package org.cechc.etl.test.testcase.fields.provider;



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

public class ProviderIdentifierFieldTest extends Basic {
    @DataProvider(name = "providerIdentifierDataProvider")
    private Iterator<Object[]> patientBaseInfoIdentifierDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(ProviderIdentifierFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsProviderFieldMapper.getProviderIdentiInfo(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "providerIdentifierDataProvider",description = "验证ods的dict_staff_dict表的字段映射到obs的provider_identifier表")
    public void compare_odsDictProviderIdentifier_and_obsProviderIdentifier_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String provider_id = (String) odsPatientMap.get("staff_no");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsProviderFieldMapper.getProviderIdentiInfoByProviderId(provider_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("provider/providerIdentifier.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }

}
