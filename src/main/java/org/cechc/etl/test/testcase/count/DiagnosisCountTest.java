package org.cechc.etl.test.testcase.count;




import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.util.DataProviderUtil;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class DiagnosisCountTest extends Basic {
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
//        /*diagnosis*/
//        HashMap<String, Object> inpDiagSrcAndDestCount = get1to1SrcAndDestCount(odsDiagnosisCountMapper::getInpDiagnosisCount,obsDiagnosisCountMapper::getDiagnosisCount);
//        HashMap<String, Object> outpDiagSrcAndDestCount = get1to1SrcAndDestCount(odsDiagnosisCountMapper::getOutpDiagnosisCount,obsDiagnosisCountMapper::getDiagnosisCount);
//        HashMap<String, Object> caseDiagSrcAndDestCount = get1to1SrcAndDestCount(odsDiagnosisCountMapper::getCaseDiagnosisCount,obsDiagnosisCountMapper::getDiagnosisCount);
//        ArrayList<HashMap<String, Object>> DiagSrcAndDestCounts = toList(inpDiagSrcAndDestCount, outpDiagSrcAndDestCount,caseDiagSrcAndDestCount);
//
//        /*diagnosis_provider*/
//        HashMap<String, Object> inpDiagProvSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getInpDiagnosisProCount, obsDiagnosisCountMapper::getDiagnosisProCount);
//        HashMap<String, Object> outpDiagProvSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getOutpDiagnosisProCount, obsDiagnosisCountMapper::getDiagnosisProCount);
//        HashMap<String, Object> caseDiagProvSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getCaseDiagnosisProCount, obsDiagnosisCountMapper::getDiagnosisProCount);
//        ArrayList<HashMap<String, Object>> diagProvSrcAndDestCounts = toList(inpDiagProvSrcAndDestCount, outpDiagProvSrcAndDestCount,caseDiagProvSrcAndDestCount);
//
//        /*diagnosis_location*/
//        HashMap<String, Object> inpDiagLocSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getInpDiagnosisLocCount, obsDiagnosisCountMapper::getDiagnosisLocCount);
//        HashMap<String, Object> outpDiagLocSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getOutpDiagnosisLocCount, obsDiagnosisCountMapper::getDiagnosisLocCount);
//        HashMap<String, Object> caseDiagLocSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getCaseDiagnosisLocCount, obsDiagnosisCountMapper::getDiagnosisLocCount);
//        ArrayList<HashMap<String, Object>> diagLocSrcAndDestCounts = toList(inpDiagLocSrcAndDestCount,outpDiagLocSrcAndDestCount,caseDiagLocSrcAndDestCount);
//
//
//
//        dictSrcAndDestCounts.add(DiagSrcAndDestCounts);
//        dictSrcAndDestCounts.add(diagProvSrcAndDestCounts);
//        dictSrcAndDestCounts.add(diagLocSrcAndDestCounts);
//
//        return DataProviderUtil.getDataProvider2(dictSrcAndDestCounts);
//    }

    @DataProvider(name = "getDiagnosisDataProvider")
    private Iterator<Object[]> getDiagnosisDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(DiagnosisCountTest.class,method,context);
        HashMap<String, Object> inpDiagSrcAndDestCount = get1to1SrcAndDestCount(odsDiagnosisCountMapper::getInpDiagnosisCount,obsDiagnosisCountMapper::getDiagnosisCount,parameter);
        HashMap<String, Object> outpDiagSrcAndDestCount = get1to1SrcAndDestCount(odsDiagnosisCountMapper::getOutpDiagnosisCount,obsDiagnosisCountMapper::getDiagnosisCount,parameter);
        HashMap<String, Object> caseDiagSrcAndDestCount = get1to1SrcAndDestCount(odsDiagnosisCountMapper::getCaseDiagnosisCount,obsDiagnosisCountMapper::getDiagnosisCount,parameter);
        ArrayList<HashMap<String, Object>> DiagSrcAndDestCounts = toList(inpDiagSrcAndDestCount, outpDiagSrcAndDestCount,caseDiagSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(DiagSrcAndDestCounts);

    }

    @DataProvider(name = "getDiagnosisProviderDataProvider")
    private Iterator<Object[]> getDiagnosisProviderDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(DiagnosisCountTest.class,method,context);
        HashMap<String, Object> inpDiagProvSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getInpDiagnosisProCount, obsDiagnosisCountMapper::getDiagnosisProCount,parameter);
        HashMap<String, Object> outpDiagProvSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getOutpDiagnosisProCount, obsDiagnosisCountMapper::getDiagnosisProCount,parameter);
        HashMap<String, Object> caseDiagProvSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getCaseDiagnosisProCount, obsDiagnosisCountMapper::getDiagnosisProCount,parameter);
        ArrayList<HashMap<String, Object>> diagProvSrcAndDestCounts = toList(inpDiagProvSrcAndDestCount, outpDiagProvSrcAndDestCount,caseDiagProvSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(diagProvSrcAndDestCounts);

    }

    @DataProvider(name = "getDiagnosisLocationDataProvider")
    private Iterator<Object[]> getDiagnosisLocationDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(DiagnosisCountTest.class,method,context);

        HashMap<String, Object> inpDiagLocSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getInpDiagnosisLocCount, obsDiagnosisCountMapper::getDiagnosisLocCount,parameter);
        HashMap<String, Object> outpDiagLocSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getOutpDiagnosisLocCount, obsDiagnosisCountMapper::getDiagnosisLocCount,parameter);
        HashMap<String, Object> caseDiagLocSrcAndDestCount = get1toManySrcAndDestCount(odsDiagnosisCountMapper::getCaseDiagnosisLocCount, obsDiagnosisCountMapper::getDiagnosisLocCount,parameter);
        ArrayList<HashMap<String, Object>> diagLocSrcAndDestCounts = toList(inpDiagLocSrcAndDestCount,outpDiagLocSrcAndDestCount,caseDiagLocSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(diagLocSrcAndDestCounts);


    }


    @Test(dataProvider = "getDiagnosisDataProvider",description = "根据时间范围，验证ods的inp_diagnosis_record表outp_diagnosis_record表case_diagnosis_record表条数与obs的diagnosis表条数一致")
    public void compare_odsDiagnosis_and_obsDiagnosis_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);

    }

    @Test(dataProvider = "getDiagnosisProviderDataProvider",description = "根据时间范围，验证ods的inp_diagnosis_record表outp_diagnosis_record表case_diagnosis_record表条数与obs的diagnosis_provider表条数一致")
    public void compare_odsDiagnosisProvider_and_obsDiagnosisProvider_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

    @Test(dataProvider = "getDiagnosisLocationDataProvider",description = "根据时间范围，验证ods的inp_diagnosis_record表outp_diagnosis_record表case_diagnosis_record表条数与obs的diagnosis_location表条数一致")
    public void compare_odsDiagnosisLocation_and_obsDiagnosisLocation_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

}
