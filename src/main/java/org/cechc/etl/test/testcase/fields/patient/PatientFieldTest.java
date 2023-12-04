package org.cechc.etl.test.testcase.fields.patient;

import org.cechc.etl.test.demo.IHookableDemo;
import org.cechc.etl.test.demo.IInvokedMethodListenerImp;
import org.cechc.etl.test.demo.MyRetry;
import org.cechc.etl.test.obsmapper.field.*;
import org.cechc.etl.test.odsmapper.field.*;
import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.util.DataProviderUtil;
import org.cechc.etl.test.testcase.util.TargetFieldComputerUtil;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.cechc.etl.test.util.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.xml.XmlSuite;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

import static java.lang.Integer.*;


public class PatientFieldTest extends Basic {
    private Class<PatientFieldTest> testClass = PatientFieldTest.class;

    @DataProvider(name = "patientBaseInfoListDataProvider")
    private Iterator<Object[]> patientBaseInfoListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(testClass, method, context);
        ArrayList<HashMap<String, Object>> patientList = odsPatientFieldMapper.getPatientBaseInfoList(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "patientCaseBaseInfoList")
    private Iterator<Object[]> patientCaseBaseInfoListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(testClass, method, context);
        ArrayList<HashMap<String, Object>> patientList = odsPatientFieldMapper.getPatientCaseBaseInfoList(param);
        return DataProviderUtil.getDataProvider(patientList);
    }

    @DataProvider(name = "patientDuplicateInfoListDataProvider")
    private Iterator<Object[]> patientDuplicateInfoListDataProvider(Method method, ITestContext context) {
        Parameter param = TestUtil.getParamValue(testClass, method, context);
        ArrayList<HashMap<String, Object>> patientList = odsPatientFieldMapper.getPatientDuplicateInfoList(param);
        return DataProviderUtil.getDataProvider(patientList);
    }


    @Test(dataProvider = "patientBaseInfoListDataProvider",
            description = "验证ods patient_basic_info映射到obs的patient表的字段的正确性（排除重复的patient_id,排除与case_base_info能关联上的patient_id）")
    public void compare_odsPatientBasicInfoFields_mapTo_obsPatientFields(HashMap<String, Object> odsPatientMap) throws Exception {
        HashMap<String, Object> obsPatientInfo = getObsInfoByRecordId(odsPatientMap, obsPatientFieldMapper::getPatientInfoByPatientId);
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("patient/patientBasicInfoMapConfig.properties");
        compareOneToOneField(odsPatientMap, obsPatientInfo, properties);
    }

    @Test(dataProvider = "patientCaseBaseInfoList",
            description = "验证ods case_base_info映射到obs的patient表的字段（排除重复的patient_id,排除与排除与patient_basic_info能关联上的patient_id")
    public void compare_odsCaseBaseInfoFields_mapTo_obsPatientFields(HashMap<String, Object> odsPatientMap) throws Exception {
        HashMap<String, Object> obsPatientInfo = getObsInfoByRecordId(odsPatientMap, obsPatientFieldMapper::getPatientInfoByPatientId);
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("patient/patientCaseBasicInfoMapConfig.properties");
        compareOneToOneField(odsPatientMap, obsPatientInfo, properties);
    }

    @Test(dataProvider = "patientDuplicateInfoListDataProvider",
    description = "验证ods patient_basic_info与case_base_info关联的映射到obs的patient字段(排除了patient_basic_info重复的patient_id，排除了case_base_info重复的patient_id）")
    public void compare_odsDuplicateInfoFields_mapTo_obsPatientFields(HashMap<String, Object> odsPatientMap) throws Exception {
        HashMap<String, Object> obsPatientInfo = getObsInfoByRecordId(odsPatientMap, obsPatientFieldMapper::getPatientInfoByPatientId);
        final Properties properties = PropertiesLoaderUtils.loadAllProperties("patient/patientDuplicateInfoMapConfig.properties");
        compareOneToOneField(odsPatientMap, obsPatientInfo, properties);
    }


}
