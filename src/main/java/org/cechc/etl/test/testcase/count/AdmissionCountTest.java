package org.cechc.etl.test.testcase.count;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.cechc.etl.test.testcase.util.DataProviderUtil;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class AdmissionCountTest extends Basic {


    @DataProvider(name = "getAdmissionDataProvider")
    private Iterator<Object[]> getAdmission(Method method, ITestContext context){
        Parameter parameter = TestUtil.getParamValue(AdmissionCountTest.class,method,context);
        Long odsAdmissionCount = odsAdmissionCountMapper.getInVisitCodesByTimeRangeCount(parameter);

        HashMap<String, Object> AdmSrcAndDestCount = get1to1SrcAndDestCount(odsAdmissionCountMapper::getInVisitCodesByTimeRangeLimitOffset,obsAdmissionCountMapper::getAdmissionCount,odsAdmissionCount,parameter);
        ArrayList<HashMap<String, Object>> admSrcAndDestCount = toList(AdmSrcAndDestCount);

        return DataProviderUtil.getDataProvider4(admSrcAndDestCount);
    }

    @DataProvider(name = "getAdmissionProviderDataProvider")
    private Iterator<Object[]> getAdmissionProvider(Method method, ITestContext context){
        Parameter parameter = TestUtil.getParamValue(AdmissionCountTest.class,method,context);
        HashMap<String, Object> inpAdmProvSrcAndDestCount = get1toManySrcAndDestCount(odsAdmissionCountMapper::getInpVisitProCount, obsAdmissionCountMapper::getAdmissionProviderCount,parameter);
        HashMap<String, Object> caseAdmProvSrcAndDestCount = get1toManySrcAndDestCount(odsAdmissionCountMapper::getCaseResidenceProCount, obsAdmissionCountMapper::getAdmissionProviderCount,parameter);
        ArrayList<HashMap<String, Object>> admProviderSrcAndDestCount = toList(inpAdmProvSrcAndDestCount, caseAdmProvSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(admProviderSrcAndDestCount);

    }

    @DataProvider(name = "getAdmissionLocationDataProvider")
    private Iterator<Object[]> getAdmissionLocationDataProvider(Method method, ITestContext context){
        Parameter parameter = TestUtil.getParamValue(AdmissionCountTest.class,method,context);
        HashMap<String, Object> inpAdmLocSrcAndDestCount = get1toManySrcAndDestCount(odsAdmissionCountMapper::getInpVisitLocCount, obsAdmissionCountMapper::getAdmissionLocationCount,parameter);
        HashMap<String, Object> caseAdmLocSrcAndDestCount = get1toManySrcAndDestCount(odsAdmissionCountMapper::getCaseResidenceLocCount, obsAdmissionCountMapper::getAdmissionLocationCount,parameter);
        ArrayList<HashMap<String, Object>> admLocationSrcAndDestCount = toList(inpAdmLocSrcAndDestCount,caseAdmLocSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(admLocationSrcAndDestCount);

    }


    @Test(dataProvider = "getAdmissionDataProvider",description = "根据时间范围，验证ods映射到obs的admission表条数一致")
    public void compare_odsAdmission_and_obsAdmission_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);

    }

    @Test(dataProvider = "getAdmissionProviderDataProvider",description = "根据时间范围，验证ods映射到obs的admission_provider表条数一致")
    public void compare_odsAdmissionProvider_and_obsAdmissionProvider_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);

    }

    @Test(dataProvider = "getAdmissionLocationDataProvider",description = "根据时间范围，验证ods映射到obs的admission_location表条数一致")
    public void compare_odsAdmissionLocation_and_obsAdmissionLocation_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);

    }
}
