package org.cechc.etl.test.testcase.fields.schedule;


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


public class ScheduleLocationFieldTest extends Basic {
    @DataProvider(name = "scheduleLocDataProvider")
    private Iterator<Object[]> scheduleLocDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(ScheduleLocationFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsScheduleFieldMapper.getScheduleLocField(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "scheduleLocDataProvider",description = "验证ods的outp_register_record表字段映射到obs的schedule_location表")
    public void compare_odsOutpScheduleLocation_and_obsScheduleLocation_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String patient_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsScheduleFieldMapper.getScheduleLocField(patient_id);
        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("schedule/scheduleLoc.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
}
