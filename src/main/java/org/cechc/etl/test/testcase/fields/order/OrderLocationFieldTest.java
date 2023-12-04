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


public class OrderLocationFieldTest extends Basic {
    @DataProvider(name = "oupOrderLocDataProvider")
    private Iterator<Object[]> oupOrderProviderDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(OrderLocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> list = odsOrderFieldMapper.getOutpOrderLocInfo(param);
        return DataProviderUtil.getDataProvider(list);
    }

    @DataProvider(name = "inpOrderLocDataProvider")
    private Iterator<Object[]> inpOrderProviderDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(OrderLocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> list = odsOrderFieldMapper.getInpOrderLocInfo(param);
        return DataProviderUtil.getDataProvider(list);
    }

    @Test(dataProvider = "oupOrderLocDataProvider",description = "验证ods的outp_order_master表的字段映射到obs的order_location表")
    public void compare_odsOutpOrderLoc_and_obsOrderLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String)odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsOrderFieldMapper.getOrderLocInfoByOrdertId(record_id);
        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("order/outpOrderLoc.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }

    @Test(dataProvider = "inpOrderLocDataProvider",description = "验证ods的inp_order_master表的字段映射到obs的order_location表")
    public void compare_odsInpOrderLoc_and_obsOrderLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String)odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsOrderFieldMapper.getOrderLocInfoByOrdertId(record_id);
        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("order/inpOrderLoc.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }


}

