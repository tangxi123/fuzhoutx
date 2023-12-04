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

public class ScheduleProviderFieldTest extends Basic {
    @DataProvider(name = "scheduleProvDataProvider")
    private Iterator<Object[]> patientBaseInfoIdentifierDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(ScheduleProviderFieldTest.class, method, context);

        ArrayList<HashMap<String, Object>> patientList = odsScheduleFieldMapper.getScheduleProvField(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @Test(dataProvider = "scheduleProvDataProvider",description = "验证ods的outp_register_record表字段映射到obs的schedule_provider表")
    public void compare_odsOutpScheduleProvider_and_obsScheduleProvider_field(HashMap<String, Object> odsPatientMap) throws Exception {
        String record_id = (String) odsPatientMap.get("record_id");
        List<Map<String, Object>> obsPatientIdentifierInfos = (List)obsScheduleFieldMapper.getScheduleProvField(record_id);
        SoftAssert softAssert = new SoftAssert();
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("schedule/scheProv.properties");
        TargetFieldComputerUtil.test2((Map) properties, odsPatientMap, obsPatientIdentifierInfos, softAssert, (ConfigurableEnvironment) environment);
    }
}
