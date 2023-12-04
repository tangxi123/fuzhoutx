package org.cechc.etl.test.testcase.count;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.util.DataProviderUtil;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class ResultCountTest extends Basic {
//    {
//        super.setCreateStartTime(null);
//        super.setCreateEndTime(null);
//        super.setModifyStartTime(null);
//        super.setModifyEndTime(null);
//    }

//    @DataProvider(name = "dictSrcAndDestCountsDataProvider")
//    private Iterator<Object[]> getDataProvider() {
//        ArrayList<ArrayList<HashMap<String, Object>>> dictSrcAndDestCounts = new ArrayList<>();
//
//        /*result*/
//        HashMap<String, Object> resultSrcAndDestCount = get1to1SrcAndDestCount(odsResultCountMapper::getResultCount,obsResultCountMapper::getResultCount);
//        ArrayList<HashMap<String, Object>> resultSrcAndDestCounts = toList(resultSrcAndDestCount);
//
//        /*result_provider*/
//        HashMap<String, Object> resultProvSrcAndDestCount = get1toManySrcAndDestCount(odsResultCountMapper::getResultProCount, obsResultCountMapper::getResultProviderCount);
//        ArrayList<HashMap<String, Object>> resultProvSrcAndDestCounts = toList(resultProvSrcAndDestCount);
//
//        /*result_location*/
//        HashMap<String, Object> resultLocSrcAndDestCount = get1toManySrcAndDestCount(odsResultCountMapper::getLisReportLocCount, obsResultCountMapper::getResultLocationCount);
//        ArrayList<HashMap<String, Object>> resultLocSrcAndDestCounts = toList(resultLocSrcAndDestCount);
//
//
//        dictSrcAndDestCounts.add(resultSrcAndDestCounts);
//        dictSrcAndDestCounts.add(resultProvSrcAndDestCounts);
//        dictSrcAndDestCounts.add(resultLocSrcAndDestCounts);
//
//        return DataProviderUtil.getDataProvider2(dictSrcAndDestCounts);
//    }

    @DataProvider(name = "getResultDataProvider")
    private Iterator<Object[]> getResultDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(ResultCountTest.class,method,context);
        HashMap<String, Object> resultSrcAndDestCount = get1to1SrcAndDestCount(odsResultCountMapper::getResultCount,obsResultCountMapper::getResultCount,parameter);
        ArrayList<HashMap<String, Object>> resultSrcAndDestCounts = toList(resultSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(resultSrcAndDestCounts);
    }

    @DataProvider(name = "getResultProviderDataProvider")
    private Iterator<Object[]> getResultProviderDataProvider(Method method,ITestContext context){
        Parameter parameter = TestUtil.getParamValue(ResultCountTest.class,method,context);
        HashMap<String, Object> resultProvSrcAndDestCount = get1toManySrcAndDestCount(odsResultCountMapper::getResultProCount, obsResultCountMapper::getResultProviderCount,parameter);
        ArrayList<HashMap<String, Object>> resultProvSrcAndDestCounts = toList(resultProvSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(resultProvSrcAndDestCounts);

    }

    @DataProvider(name = "getResultLocationDataProvider")
    private Iterator<Object[]> getResultLocationDataProvider(Method method,ITestContext context){
        Parameter parameter = TestUtil.getParamValue(ResultCountTest.class,method,context);
        HashMap<String, Object> resultLocSrcAndDestCount = get1toManySrcAndDestCount(odsResultCountMapper::getLisReportLocCount, obsResultCountMapper::getResultLocationCount,parameter);
        ArrayList<HashMap<String, Object>> resultLocSrcAndDestCounts = toList(resultLocSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(resultLocSrcAndDestCounts);

    }



    @Test(dataProvider = "getResultDataProvider",description = "根据时间范围，验证ods的lis_report_detail表条数与obs的result表条数一致")
    public void compare_odsResult_and_obsResult_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);

    }

    @Test(dataProvider = "getResultProviderDataProvider",description = "根据时间范围，验证ods的lis_report_detail表条数与obs的result_provider表条数一致")
    public void compare_odsResultProvider_and_obsResultProvider_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);

    }

    @Test(dataProvider = "getResultLocationDataProvider",description = "根据时间范围，验证ods的lis_report_detail表条数与obs的result_location表条数一致")
    public void compare_odsResultLocation_and_obsResultLocation_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);

    }
}
