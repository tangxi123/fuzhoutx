package org.cechc.etl.test.testcase.count.allCount;


import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DiagnosisAllCountTest extends Basic {


    /**
     * 测试标题：对比ODS与OBS的diagnosis表条数
     * 测试步骤：对比ODS的case_diagnosis_record加上inp_diagnosis_record加上outp_diagnosis_record的条数
     *          与OBS的diagnosis条数一致
     * 期望结果：条数一致
     */
    @Test(description = "验证ods映射到obs的diagnosis表条数一致")
    public void compare_diagnosis_count(){
        Integer odsDiagnosisCount = getOdsDiagnosisCount();
        Integer obsDiagnosisCount = getObsDiagnosisCount();
        Assert.assertEquals(odsDiagnosisCount,obsDiagnosisCount);
    }

    @Test(description = "验证ods映射到obs的diagnosis_location表条数一致")
    public void compare_diagnosis_location_count(){
        Integer odsDiagnosisLocCount = getOdsDiagnosisLocationCount();
        Integer obsDiagnosisLocCount = getObsDiagnosisLocationCount();
        Assert.assertEquals(odsDiagnosisLocCount,obsDiagnosisLocCount);

    }

    @Test(description = "验证ods映射到obs的diagnosis_provider表条数一致")
    public void compare_diagnosis_provider_count(){
        Integer odsDiagnosisProCount = getOdsDiagnosisProviderCount();
        Integer obsDiagnosisProCount = getObsDiagnosisProviderCount();
        Assert.assertEquals(odsDiagnosisProCount,obsDiagnosisProCount);
    }

    private Integer getOdsDiagnosisCount(){
        Integer caseDiagnosisCount = getCaseDiagnosisCount();
        Integer outDiagnosisCount = getOutDiagnosisCount();
        Integer odsDiagnosisCount = caseDiagnosisCount + outDiagnosisCount;
        return odsDiagnosisCount;
    }

    private Integer getCaseDiagnosisCount(){
        Integer caseDiagnosisJoinPatientBasicCount = odsDiagnosisAllCountMapper.getCaseDiagnosisJoinPatientBasicCount();
        Integer caseDiagnosisJoinCaseBaseCount = odsDiagnosisAllCountMapper.getCaseDiagnosisJoinCaseBaseCount();
        Integer caseDiagnosisJoinPatientBasicJoinCaseBaseCount = odsDiagnosisAllCountMapper.getCaseDiagnosisJoinPatientBasicJoinCaseBaseCount();
        Integer caseDiagnosisCount = caseDiagnosisJoinPatientBasicCount + caseDiagnosisJoinCaseBaseCount - caseDiagnosisJoinPatientBasicJoinCaseBaseCount;
        return caseDiagnosisCount;
    }

    private Integer getOutDiagnosisCount(){
        Integer outDiagnosisJoinPatientBasicCount = odsDiagnosisAllCountMapper.getOutDiagnosisJoinPatientBasicCount();
        Integer outDiagnosisJoinCaseBaseCount = odsDiagnosisAllCountMapper.getOutDiagnosisJoinCaseBaseCount();
        Integer caseDiagnosisJoinPatientBasicJoinCaseBaseCount = odsDiagnosisAllCountMapper.getCaseDiagnosisJoinPatientBasicJoinCaseBaseCount();
        Integer outDiagnosisCount = outDiagnosisJoinPatientBasicCount + outDiagnosisJoinCaseBaseCount - caseDiagnosisJoinPatientBasicJoinCaseBaseCount;
        return outDiagnosisCount;
    }

    private Integer getObsDiagnosisCount(){
        Integer diagnosisCount = obsDiagnosisAllCountMapper.getDiagnosisCount();
        return diagnosisCount;
    }

    private Integer getOdsDiagnosisLocationCount(){
        Integer caseDiagnosisLocationCount = getCaseDiagnosisLocationCount();
        Integer outDiagnosisLocationCount = getOutDiagnosisLocationCount();
        Integer odsDiagnosisLocationCount = caseDiagnosisLocationCount + outDiagnosisLocationCount;
        return odsDiagnosisLocationCount;
    }

    private Integer getOutDiagnosisLocationCount(){
        Integer outDiagnosisJoinPatientBasicLocationCount = odsDiagnosisAllCountMapper.getOutDiagnosisJoinPatientBasicLocationCount();
        Integer outDiagnosisJoinCaseBaseLocationCount = odsDiagnosisAllCountMapper.getOutDiagnosisJoinCaseBaseLocationCount();
        Integer outDiagnosisJoinPatientBasicJoinCaseBaseLocationCount = odsDiagnosisAllCountMapper.getOutDiagnosisJoinPatientBasicJoinCaseBaseLocationCount();
        Integer outDiagnosisLocationCount = outDiagnosisJoinPatientBasicLocationCount + outDiagnosisJoinCaseBaseLocationCount - outDiagnosisJoinPatientBasicJoinCaseBaseLocationCount;
        return outDiagnosisLocationCount;
    }

    private Integer getCaseDiagnosisLocationCount(){
        Integer caseDiagnosisJoinPatientBasicLocationCount = odsDiagnosisAllCountMapper.getCaseDiagnosisJoinPatientBasicLocationCount();
        Integer caseDiagnosisJoinCaseBaseLocationCount = odsDiagnosisAllCountMapper.getCaseDiagnosisJoinCaseBaseLocationCount();
        Integer caseDiagnosisJoinPatientBasicJoinCaseBaseLocationCount = odsDiagnosisAllCountMapper.getCaseDiagnosisJoinPatientBasicJoinCaseBaseLocationCount();
        Integer caseDiagnosisLocationCount =  caseDiagnosisJoinPatientBasicLocationCount + caseDiagnosisJoinCaseBaseLocationCount - caseDiagnosisJoinPatientBasicJoinCaseBaseLocationCount;
        return caseDiagnosisLocationCount;
    }

    private Integer getObsDiagnosisLocationCount(){
        Integer diagnosisLocCount = obsDiagnosisAllCountMapper.getDiagnosisLocCount();
        return diagnosisLocCount;
    }

    private Integer getOdsDiagnosisProviderCount(){
        Integer outDiagnosisProviderCount = getOutDiagnosisProviderCount();
        Integer caseDiagnosisProviderCount = getCaseDiagnosisProviderCount();
        Integer odsDiagnosisPrividerCount = outDiagnosisProviderCount + caseDiagnosisProviderCount;
        return odsDiagnosisPrividerCount;
    }

    private Integer getOutDiagnosisProviderCount(){
        Integer outDiagnosisJoinPatientBasicProviderCount = odsDiagnosisAllCountMapper.getOutDiagnosisJoinPatientBasicProviderCount();
        Integer outDiagnosisJoinCaseBaseProviderCount = odsDiagnosisAllCountMapper.getOutDiagnosisJoinCaseBaseProviderCount();
        Integer outDiagnosisJoinPatientBasicJoinCaseBaseProviderCount = odsDiagnosisAllCountMapper.getOutDiagnosisJoinPatientBasicJoinCaseBaseProviderCount();
        Integer outDiagnosisProviderCount = outDiagnosisJoinPatientBasicProviderCount + outDiagnosisJoinCaseBaseProviderCount - outDiagnosisJoinPatientBasicJoinCaseBaseProviderCount;
        return outDiagnosisProviderCount;
    }

    private Integer getCaseDiagnosisProviderCount(){
        Integer caseDiagnosisJoinPatientBasicProviderCount = odsDiagnosisAllCountMapper.getCaseDiagnosisJoinPatientBasicProviderCount();
        Integer caseDiagnosisJoinCaseBaseProviderCount = odsDiagnosisAllCountMapper.getCaseDiagnosisJoinCaseBaseProviderCount();
        Integer caseDiagnosisJoinPatientBasicJoinCaseBaseProviderCount = odsDiagnosisAllCountMapper.getCaseDiagnosisJoinPatientBasicJoinCaseBaseProviderCount();
        Integer caseDiagnosisProviderCount = caseDiagnosisJoinPatientBasicProviderCount + caseDiagnosisJoinCaseBaseProviderCount - caseDiagnosisJoinPatientBasicJoinCaseBaseProviderCount;
        return caseDiagnosisProviderCount;
    }

    private Integer getObsDiagnosisProviderCount(){
        Integer diagnosisProCount = obsDiagnosisAllCountMapper.getDiagnosisProCount();
        return diagnosisProCount;
    }

}
