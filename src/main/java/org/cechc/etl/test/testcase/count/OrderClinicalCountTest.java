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


public class OrderClinicalCountTest extends Basic {

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
//        /*admission*/
//        HashMap<String, Object> inpOrderSrcAndDestCount = get1to1SrcAndDestCount(odsOrderCountMapper::getInpOrderCount,obsOrderCountMapper::getOrderCount);
//        HashMap<String, Object> outpOrderSrcAndDestCount = get1to1SrcAndDestCount(odsOrderCountMapper::getOutpOrderCount,obsOrderCountMapper::getOrderCount);
//        ArrayList<HashMap<String, Object>> orderSrcAndDestCount = toList(inpOrderSrcAndDestCount, outpOrderSrcAndDestCount);
//
//        /*admission_provider*/
//        HashMap<String, Object> inpOrderProvSrcAndDestCount = get1toManySrcAndDestCount(odsOrderCountMapper::getInpOrderProCount, obsOrderCountMapper::getOrderProCount);
//        HashMap<String, Object> outpOrderProvSrcAndDestCount = get1toManySrcAndDestCount(odsOrderCountMapper::getOutpOrderProCount, obsOrderCountMapper::getOrderProCount);
//        ArrayList<HashMap<String, Object>> orderProvSrcAndDestCount = toList(inpOrderProvSrcAndDestCount, outpOrderProvSrcAndDestCount);
//
//        /*admission_location*/
//        HashMap<String, Object> inpOrderLocSrcAndDestCount = get1toManySrcAndDestCount(odsOrderCountMapper::getInpOrderLocCount, obsOrderCountMapper::getOrderLocCount);
//        HashMap<String, Object> outpOrderLocSrcAndDestCount = get1toManySrcAndDestCount(odsOrderCountMapper::getOutpOrderLocCount, obsOrderCountMapper::getOrderLocCount);
//        ArrayList<HashMap<String, Object>> orderLocrcAndDestCount = toList(inpOrderLocSrcAndDestCount,outpOrderLocSrcAndDestCount);
//
//
//
//        dictSrcAndDestCounts.add(orderSrcAndDestCount);
//        dictSrcAndDestCounts.add(orderProvSrcAndDestCount);
//        dictSrcAndDestCounts.add(orderLocrcAndDestCount);
//
//        return DataProviderUtil.getDataProvider2(dictSrcAndDestCounts);
//    }

    @DataProvider(name = "getOrderDataProvider")
    private Iterator<Object[]> getOrderDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(OrderClinicalCountTest.class,method,context);

        HashMap<String, Object> inpOrderSrcAndDestCount = get1to1SrcAndDestCount(odsOrderCountMapper::getInpOrderCount,obsOrderCountMapper::getOrderCount,parameter);
        HashMap<String, Object> outpOrderSrcAndDestCount = get1to1SrcAndDestCount(odsOrderCountMapper::getOutpOrderCount,obsOrderCountMapper::getOrderCount,parameter);
        ArrayList<HashMap<String, Object>> orderSrcAndDestCount = toList(inpOrderSrcAndDestCount, outpOrderSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(orderSrcAndDestCount);

    }

    @DataProvider(name = "getOrderProviderDataProvider")
    private Iterator<Object[]> getOrderProviderDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(OrderClinicalCountTest.class, method, context);
        HashMap<String, Object> inpOrderProvSrcAndDestCount = get1toManySrcAndDestCount(odsOrderCountMapper::getInpOrderProCount, obsOrderCountMapper::getOrderProCount,parameter);
        HashMap<String, Object> outpOrderProvSrcAndDestCount = get1toManySrcAndDestCount(odsOrderCountMapper::getOutpOrderProCount, obsOrderCountMapper::getOrderProCount,parameter);
        ArrayList<HashMap<String, Object>> orderProvSrcAndDestCount = toList(inpOrderProvSrcAndDestCount, outpOrderProvSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(orderProvSrcAndDestCount);

    }

    @DataProvider(name = "getOrderLocationDataProvider")
    private Iterator<Object[]> getOrderLocationDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(OrderClinicalCountTest.class, method, context);
        HashMap<String, Object> inpOrderLocSrcAndDestCount = get1toManySrcAndDestCount(odsOrderCountMapper::getInpOrderLocCount, obsOrderCountMapper::getOrderLocCount,parameter);
        HashMap<String, Object> outpOrderLocSrcAndDestCount = get1toManySrcAndDestCount(odsOrderCountMapper::getOutpOrderLocCount, obsOrderCountMapper::getOrderLocCount,parameter);
        ArrayList<HashMap<String, Object>> orderLocrcAndDestCount = toList(inpOrderLocSrcAndDestCount,outpOrderLocSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(orderLocrcAndDestCount);

    }


    @Test(dataProvider = "getOrderDataProvider",description = "根据时间范围，验证ods的inp_order_record和outp_order_master表条数与obs的order表条数一致")
    public void compare_odsOrder_and_obsOrder_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

    @Test(dataProvider = "getOrderProviderDataProvider",description = "根据时间范围，验证ods的inp_order_record和outp_order_master表条数与obs的order_provider表条数一致")
    public void compare_odsOrderProvider_and_obsOrderProvider_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

    @Test(dataProvider = "getOrderLocationDataProvider",description = "根据时间范围，验证ods的inp_order_record和outp_order_master表条数与obs的order_location表条数一致")
    public void compare_odsOrderLocation_and_obsOrderLocation_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }
}
