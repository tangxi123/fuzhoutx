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


public class MedicationCountTest extends Basic {

//    @DataProvider(name = "dictSrcAndDestCountsDataProvider")
//    private Iterator<Object[]> getDataProvider() {
//        ArrayList<ArrayList<HashMap<String, Object>>> dictSrcAndDestCounts = new ArrayList<>();
//
//        /*admission*/
//        HashMap<String, Object> inpMedSrcAndDestCount = get1to1SrcAndDestCount(odsMedicationCountMapper::getInpMedCount,obsMedicationCountMapper::getMedicationCount);
//        HashMap<String, Object> outpMedSrcAndDestCount = get1to1SrcAndDestCount(odsMedicationCountMapper::getOutpMedCount,obsMedicationCountMapper::getMedicationCount);
//        ArrayList<HashMap<String, Object>> medSrcAndDestCount = toList(inpMedSrcAndDestCount, outpMedSrcAndDestCount);
//
//        /*admission_provider*/
//        HashMap<String, Object> inpMedProvSrcAndDestCount = get1toManySrcAndDestCount(odsMedicationCountMapper::getInpMedProCount, obsMedicationCountMapper::getMedicationProCount);
//        HashMap<String, Object> outpMedProvSrcAndDestCount = get1toManySrcAndDestCount(odsMedicationCountMapper::getOutpMedProCount, obsMedicationCountMapper::getMedicationProCount);
//        ArrayList<HashMap<String, Object>> medProvSrcAndDestCount = toList(inpMedProvSrcAndDestCount, outpMedProvSrcAndDestCount);
//
//        /*admission_location*/
//        HashMap<String, Object> inpMedLocSrcAndDestCount = get1toManySrcAndDestCount(odsMedicationCountMapper::getInpMedLocCount, obsMedicationCountMapper::getMedicationLocCount);
//        HashMap<String, Object> outpMedLocSrcAndDestCount = get1toManySrcAndDestCount(odsMedicationCountMapper::getOutpMedLocCount, obsMedicationCountMapper::getMedicationLocCount);
//        ArrayList<HashMap<String, Object>> medLocrcAndDestCount = toList(inpMedLocSrcAndDestCount,outpMedLocSrcAndDestCount);
//
//
//
//        dictSrcAndDestCounts.add(medSrcAndDestCount);
//        dictSrcAndDestCounts.add(medProvSrcAndDestCount);
//        dictSrcAndDestCounts.add(medLocrcAndDestCount);
//
//        return DataProviderUtil.getDataProvider2(dictSrcAndDestCounts);
//    }

    @DataProvider(name = "getMedicationDataProvider")
    private Iterator<Object[]> getMedicationDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(MedicationCountTest.class,method,context);
        HashMap<String, Object> inpMedSrcAndDestCount = get1to1SrcAndDestCount(odsMedicationCountMapper::getInpMedCount,obsMedicationCountMapper::getMedicationCount,parameter);
        HashMap<String, Object> outpMedSrcAndDestCount = get1to1SrcAndDestCount(odsMedicationCountMapper::getOutpMedCount,obsMedicationCountMapper::getMedicationCount,parameter);
        ArrayList<HashMap<String, Object>> medSrcAndDestCount = toList(inpMedSrcAndDestCount, outpMedSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(medSrcAndDestCount);
    }

    @DataProvider(name = "getMedicationProviderDataProvider")
    private Iterator<Object[]> getMedicationProviderDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(MedicationCountTest.class,method,context);
        HashMap<String, Object> inpMedProvSrcAndDestCount = get1toManySrcAndDestCount(odsMedicationCountMapper::getInpMedProCount, obsMedicationCountMapper::getMedicationProCount,parameter);
        HashMap<String, Object> outpMedProvSrcAndDestCount = get1toManySrcAndDestCount(odsMedicationCountMapper::getOutpMedProCount, obsMedicationCountMapper::getMedicationProCount,parameter);
        ArrayList<HashMap<String, Object>> medProvSrcAndDestCount = toList(inpMedProvSrcAndDestCount, outpMedProvSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(medProvSrcAndDestCount);

    }

    @DataProvider(name = "getMedicationLocationDataProvider")
    private Iterator<Object[]> getMedicationLocationDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(MedicationCountTest.class,method,context);
        HashMap<String, Object> inpMedLocSrcAndDestCount = get1toManySrcAndDestCount(odsMedicationCountMapper::getInpMedLocCount, obsMedicationCountMapper::getMedicationLocCount,parameter);
        HashMap<String, Object> outpMedLocSrcAndDestCount = get1toManySrcAndDestCount(odsMedicationCountMapper::getOutpMedLocCount, obsMedicationCountMapper::getMedicationLocCount,parameter);
        ArrayList<HashMap<String, Object>> medLocrcAndDestCount = toList(inpMedLocSrcAndDestCount,outpMedLocSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(medLocrcAndDestCount);

    }

    @Test(dataProvider = "getMedicationDataProvider",description = "根据时间范围，验证ods的inp_order_record和outp_order_detail表条数与obs的medication表条数一致")
    public void compare_odsMedication_and_obsMedication_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

    @Test(dataProvider = "getMedicationProviderDataProvider",description = "根据时间范围，验证ods的inp_order_record和outp_order_detail表条数与obs的medication_provider表条数一致")
    public void compare_odsMedicationProvider_and_obsMedicationProvider_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

    @Test(dataProvider = "getMedicationLocationDataProvider",description = "根据时间范围，验证ods的inp_order_record和outp_order_detail表条数与obs的medication_location表条数一致")
    public void compare_odsMedicationLocation_and_obsMedicationLocation_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

}
