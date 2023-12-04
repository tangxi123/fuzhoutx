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


public class EncounterCountTest extends Basic {


//    @DataProvider(name = "dictSrcAndDestCountsDataProvider")
//    private Iterator<Object[]> getDataProvider() {
//        ArrayList<ArrayList<HashMap<String, Object>>> dictSrcAndDestCounts = new ArrayList<>();
//
//        /*encounter*/
//        HashMap<String, Object> inpEncSrcAndDestCount = get1to1SrcAndDestCount(odsEncounterCountMapper::getInpVisitCount,obsEncounterCountMapper::getEncounterCount);
//        HashMap<String, Object> outpEncSrcAndDestCount = get1to1SrcAndDestCount(odsEncounterCountMapper::getOutpVisitCount,obsEncounterCountMapper::getEncounterCount);
//        HashMap<String, Object> caseEncSrcAndDestCount = get1to1SrcAndDestCount(odsEncounterCountMapper::getCaseResidenceCount,obsEncounterCountMapper::getEncounterCount);
//        ArrayList<HashMap<String, Object>> encSrcAndDestCounts = toList(inpEncSrcAndDestCount, outpEncSrcAndDestCount,caseEncSrcAndDestCount);
//
//        /*encounter_provider*/
//        HashMap<String, Object> inpEncProvSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getInpVisitProCount, obsEncounterCountMapper::getEncounterProCount);
//        HashMap<String, Object> outpEncProvSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getOutpVisitProCount, obsEncounterCountMapper::getEncounterProCount);
//        HashMap<String, Object> caseEncProvSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getCaseResidenceProCount, obsEncounterCountMapper::getEncounterProCount);
//        ArrayList<HashMap<String, Object>> encProvSrcAndDestCounts = toList(inpEncProvSrcAndDestCount, outpEncProvSrcAndDestCount,caseEncProvSrcAndDestCount);
//
//        /*encounter_location*/
//        HashMap<String, Object> inpEncLocSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getinpVisitLocCount, obsEncounterCountMapper::getEncounterLocCount);
//        HashMap<String, Object> outpEncLocSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getOutpVisitLocCount, obsEncounterCountMapper::getEncounterLocCount);
//        HashMap<String, Object> caseEncLocSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getCaseResidenceLocCount, obsEncounterCountMapper::getEncounterLocCount);
//        ArrayList<HashMap<String, Object>> diagLocSrcAndDestCounts = toList(inpEncLocSrcAndDestCount,outpEncLocSrcAndDestCount,caseEncLocSrcAndDestCount);
//
//
//
//        dictSrcAndDestCounts.add(encSrcAndDestCounts);
//        dictSrcAndDestCounts.add(encProvSrcAndDestCounts);
//        dictSrcAndDestCounts.add(diagLocSrcAndDestCounts);
//
//        return DataProviderUtil.getDataProvider2(dictSrcAndDestCounts);
//    }

    @DataProvider(name = "getEncounterDataProvider")
    private Iterator<Object[]> getEncounterDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(EncounterCountTest.class,method,context);
        HashMap<String, Object> inpEncSrcAndDestCount = get1to1SrcAndDestCount(odsEncounterCountMapper::getInpVisitCount,obsEncounterCountMapper::getEncounterCount,parameter);
        HashMap<String, Object> outpEncSrcAndDestCount = get1to1SrcAndDestCount(odsEncounterCountMapper::getOutpVisitCount,obsEncounterCountMapper::getEncounterCount,parameter);
        HashMap<String, Object> caseEncSrcAndDestCount = get1to1SrcAndDestCount(odsEncounterCountMapper::getCaseResidenceCount,obsEncounterCountMapper::getEncounterCount,parameter);
        ArrayList<HashMap<String, Object>> encSrcAndDestCounts = toList(inpEncSrcAndDestCount, outpEncSrcAndDestCount,caseEncSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(encSrcAndDestCounts);

    }

    @DataProvider(name = "getEncounterProviderDataProvider")
    private Iterator<Object[]> getEncounterProviderDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(EncounterCountTest.class,method,context);

        HashMap<String, Object> inpEncProvSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getInpVisitProCount, obsEncounterCountMapper::getEncounterProCount,parameter);
        HashMap<String, Object> outpEncProvSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getOutpVisitProCount, obsEncounterCountMapper::getEncounterProCount,parameter);
        HashMap<String, Object> caseEncProvSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getCaseResidenceProCount, obsEncounterCountMapper::getEncounterProCount,parameter);
        ArrayList<HashMap<String, Object>> encProvSrcAndDestCounts = toList(inpEncProvSrcAndDestCount, outpEncProvSrcAndDestCount,caseEncProvSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(encProvSrcAndDestCounts);

    }

    @DataProvider(name = "getEncounterLocationDataProvider")
    private Iterator<Object[]> getEncounterLocationDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(EncounterCountTest.class,method,context);

        HashMap<String, Object> inpEncLocSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getinpVisitLocCount, obsEncounterCountMapper::getEncounterLocCount,parameter);
        HashMap<String, Object> outpEncLocSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getOutpVisitLocCount, obsEncounterCountMapper::getEncounterLocCount,parameter);
        HashMap<String, Object> caseEncLocSrcAndDestCount = get1toManySrcAndDestCount(odsEncounterCountMapper::getCaseResidenceLocCount, obsEncounterCountMapper::getEncounterLocCount,parameter);
        ArrayList<HashMap<String, Object>> diagLocSrcAndDestCounts = toList(inpEncLocSrcAndDestCount,outpEncLocSrcAndDestCount,caseEncLocSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(diagLocSrcAndDestCounts);

    }


    @Test(dataProvider = "getEncounterDataProvider",description = "根据时间范围，验证ods的inp_visit_record表outp_visit_record表case_residence_record表条数与obs的diagnosis表条数一致")
    public void compare_odsEncounter_and_obsEncounter_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

    @Test(dataProvider = "getEncounterProviderDataProvider",description = "根据时间范围，验证ods的inp_visit_record表outp_visit_record表case_residence_record表条数与obs的diagnosis_provider表条数一致")
    public void compare_odsEncounterProvider_and_obsEncounterProvider_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

    @Test(dataProvider = "getEncounterLocationDataProvider",description = "根据时间范围，验证ods的inp_visit_record表outp_visit_record表case_residence_record表条数与obs的diagnosis_location表条数一致")
    public void compare_odsEncounterLocation_and_obsEncounterLocation_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }
}
