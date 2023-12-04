package org.cechc.etl.test.testcase.fields.order;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.fields.medication.MedicationProviderFieldTest;
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

public class OrderClinicalFieldTest extends Basic {
    @DataProvider(name = "outpOrderInfoListDataProvider")
    private Iterator<Object[]> patientBaseInfoListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(OrderClinicalFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsOrderFieldMapper.getOutpOrderInfo(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "inpOrderInfoListDataProvider")
    private Iterator<Object[]> inpOrderInfoListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(OrderClinicalFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsOrderFieldMapper.getInpOrderInfo(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "outpOrderInfoListDataProvider",description = "验证ods的outp_order_master表的字段映射到obs的order_clinical表")
    public void compare_odsOutpOrder_and_obsOrder_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String)odsPatientMap.get("record_id");
        HashMap<String, Object> obsPatientInfo = obsOrderFieldMapper.getOrderInfoByOrderId(record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("order/oupOrder.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }


    @Test(dataProvider = "inpOrderInfoListDataProvider",description = "验证ods的inp_order_record表的字段映射到obs的order_clinical表")
    public void compare_odsInpOrder_and_obsOrder_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String)odsPatientMap.get("record_id");
        HashMap<String, Object> obsPatientInfo = obsOrderFieldMapper.getOrderInfoByOrderId(record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("order/inpOrder.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }

}
