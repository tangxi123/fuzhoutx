package org.cechc.etl.test.testcase.fields.schedule;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.fields.result.ResultProviderFieldTest;
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


public class ScheduleFieldTest extends Basic {
    @DataProvider(name = "scheduleListDataProvider")
    private Iterator<Object[]> scheduleListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(ScheduleFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsScheduleFieldMapper.getScheduleField(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "scheduleListDataProvider",description = "验证ods的outp_register_record表字段映射到obs的schedule表")
    public void compare_odsOutpSchedule_and_obsSchedule_field(HashMap<String, Object> odsPatientMap) throws Exception {
        Object record_id = odsPatientMap.get("record_id");
        HashMap<String, Object> obsPatientInfo = obsScheduleFieldMapper.getScheduleField((String) record_id);

        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("schedule/schedule.properties");
        TargetFieldComputerUtil.test((Map) properties, odsPatientMap, obsPatientInfo, softAssert);
    }
}
