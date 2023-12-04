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


public class SchedultCountTest extends Basic {

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
//        /*schedule*/
//        HashMap<String, Object> scheduleSrcAndDestCount = get1to1SrcAndDestCount(odsScheduleCountMapper::getScheduleCount,obsScheduleCountMapper::getScheduleCount);
//        ArrayList<HashMap<String, Object>> scheduleSrcAndDestCounts = toList(scheduleSrcAndDestCount);
//
//        /*schedule_provider*/
//        HashMap<String, Object> resultProvSrcAndDestCount = get1toManySrcAndDestCount(odsScheduleCountMapper::getScheduleProCount, obsScheduleCountMapper::getScheduleProCount);
//        ArrayList<HashMap<String, Object>> resultProvSrcAndDestCounts = toList(resultProvSrcAndDestCount);
//
//        /*schedule_location*/
//        HashMap<String, Object> resultLocSrcAndDestCount = get1toManySrcAndDestCount(odsScheduleCountMapper::getScheduleLocCount, obsScheduleCountMapper::getScheduleLocCount);
//        ArrayList<HashMap<String, Object>> resultLocSrcAndDestCounts = toList(resultLocSrcAndDestCount);
//
//
//        dictSrcAndDestCounts.add(scheduleSrcAndDestCounts);
//        dictSrcAndDestCounts.add(resultProvSrcAndDestCounts);
//        dictSrcAndDestCounts.add(resultLocSrcAndDestCounts);
//
//        return DataProviderUtil.getDataProvider2(dictSrcAndDestCounts);
//    }

    @DataProvider(name = "getScheduleDataProvider")
    private Iterator<Object[]> getScheduleDataProvider(Method method, ITestContext context){
        Parameter parameter = TestUtil.getParamValue(SchedultCountTest.class, method, context);
        HashMap<String, Object> scheduleSrcAndDestCount = get1to1SrcAndDestCount(odsScheduleCountMapper::getScheduleCount,obsScheduleCountMapper::getScheduleCount,parameter);
        ArrayList<HashMap<String, Object>> scheduleSrcAndDestCounts = toList(scheduleSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(scheduleSrcAndDestCounts);

    }

    @DataProvider(name = "getScheduleProviderDataProvider")
    private Iterator<Object[]> getScheduleProviderDataProvider(Method method,ITestContext context){
        Parameter parameter = TestUtil.getParamValue(SchedultCountTest.class, method, context);
        HashMap<String, Object> resultProvSrcAndDestCount = get1toManySrcAndDestCount(odsScheduleCountMapper::getScheduleProCount, obsScheduleCountMapper::getScheduleProCount,parameter);
        ArrayList<HashMap<String, Object>> resultProvSrcAndDestCounts = toList(resultProvSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(resultProvSrcAndDestCounts);

    }

    @DataProvider(name = "getScheduleLocationDataProvider")
    private Iterator<Object[]> getScheduleLocationDataProvider(Method method,ITestContext context){
        Parameter parameter = TestUtil.getParamValue(SchedultCountTest.class, method, context);
        HashMap<String, Object> resultLocSrcAndDestCount = get1toManySrcAndDestCount(odsScheduleCountMapper::getScheduleLocCount, obsScheduleCountMapper::getScheduleLocCount,parameter);
        ArrayList<HashMap<String, Object>> resultLocSrcAndDestCounts = toList(resultLocSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(resultLocSrcAndDestCounts);

    }


    @Test(dataProvider = "getScheduleDataProvider",description = "根据时间范围，验证ods的outp_register_record表条数与obs的schedule表条数一致")
    public void compare_odsSchedule_and_obsSchedule_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);

    }

    @Test(dataProvider = "getScheduleProviderDataProvider",description = "根据时间范围，验证ods的outp_register_record表条数与obs的schedule_provider表条数一致")
    public void compare_odsScheduleProvider_and_obsScheduleProvider_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);

    }

    @Test(dataProvider = "getScheduleLocationDataProvider",description = "根据时间范围，验证ods的outp_register_record表条数与obs的schedule_location表条数一致")
    public void compare_odsScheduleLocation_and_obsScheduleLocation_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);

    }
}
