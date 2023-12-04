package org.cechc.etl.test.testcase.fields.order;


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

public class OrderProviderFieldTest extends Basic {
    @DataProvider(name = "oupOrderProviderDataProvider")
    private Iterator<Object[]> oupOrderProviderDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(OrderProviderFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> list = odsOrderFieldMapper.getOutpOrderProviderInfo(param);
        return DataProviderUtil.getDataProvider(list);
    }
    @DataProvider(name = "inpOrderProviderDataProvider")
    private Iterator<Object[]> inpOrderProviderDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(OrderProviderFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> list = odsOrderFieldMapper.getIntpOrderProviderInfo(param);
        return DataProviderUtil.getDataProvider(list);
    }

    @Test(dataProvider = "oupOrderProviderDataProvider",description = "验证ods的outp_order_master表的字段映射到obs的order_provider表")
    public void compare_odsOutpOrderProvider_and_obsOrderProvider_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String)odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsOrderFieldMapper.getOrderProviderInfoByOrdertId(record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("order/outpOrderProvider.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }

    @Test(dataProvider = "inpOrderProviderDataProvider",description = "验证ods的inp_order_master表的字段映射到obs的order_provider表")
    public void compare_odsInpOrderProvider_and_obsOrderProvider_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String)odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsOrderFieldMapper.getOrderProviderInfoByOrdertId(record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("order/inpOrderProvider.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos,softAssert, (ConfigurableEnvironment) environment);
    }


}
