package org.cechc.etl.test.testcase.count.allCount;


import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ResultAllCountTest extends Basic {

    @Test(description = "验证ods映射到obs的result表条数一致")
    public void compare_result_count(){
        Integer odsLisReportCount = getOdsReportCount();
        Integer obsResultCount = getObsResultCount();
        Assert.assertEquals(odsLisReportCount,obsResultCount);
    }

    @Test(description = "验证ods映射到obs的result_location表的条数一致")
    public void compare_result_location_count(){
        Integer odsLisReportLocCount = getOdsReportLocationCount();
        Integer obsResultLocationCount = getObsReportLocationCount();
        Assert.assertEquals(odsLisReportLocCount,obsResultLocationCount);

    }

    @Test(description = "验证ods映射到obs的result_provider表条数一致")
    public void compare_result_provider_count(){
        Integer odsLisReportProCount = getOdsReportProviderCount();
        Integer obsResultProviderCount = getObsReportProviderCount();
        Assert.assertEquals(odsLisReportProCount,obsResultProviderCount);

    }

    private Integer getOdsReportCount(){
        Integer reportJoinInVisitJoinPatientCount = odsResultAllCountMapper.getReportJoinInVisitJoinPatientCount();
        Integer reportJoinOutVisitJoinPatientCount = odsResultAllCountMapper.getReportJoinOutVisitJoinPatientCount();
        Integer reportJoinInVisitJoinOutVisitJoinPatientCount = odsResultAllCountMapper.getReportJoinInVisitJoinOutVisitJoinPatientCount();
        Integer odsReportCount = reportJoinInVisitJoinPatientCount + reportJoinOutVisitJoinPatientCount - reportJoinInVisitJoinOutVisitJoinPatientCount;
        return odsReportCount;

    }

    private Integer getObsResultCount(){
        Integer resultCount = obsResultAllCountMapper.getResultCount();
        return resultCount;
    }

    private Integer getOdsReportProviderCount(){
        Integer reportJoinInVisitJoinPatientProviderCount = odsResultAllCountMapper.getReportJoinInVisitJoinPatientProviderCount();
        Integer reportJoinOutVisitJoinPatientProviderCount = odsResultAllCountMapper.getReportJoinOutVisitJoinPatientProviderCount();
        Integer reportJoinInVisitJoinOutVisitJoinPatientProviderCount = odsResultAllCountMapper.getReportJoinInVisitJoinOutVisitJoinPatientProviderCount();
        Integer odsReportProvider = reportJoinInVisitJoinPatientProviderCount + reportJoinOutVisitJoinPatientProviderCount - reportJoinInVisitJoinOutVisitJoinPatientProviderCount;
        return odsReportProvider;
    }

    private Integer getObsReportProviderCount(){
        Integer resultProviderCount = obsResultAllCountMapper.getResultProviderCount();
        return resultProviderCount;
    }

    private Integer getOdsReportLocationCount(){
        Integer reportJoinInVisitJoinPatientLocationCount = odsResultAllCountMapper.getReportJoinInVisitJoinPatientLocationCount();
        Integer reportJoinOutVisitJoinPatientLocationCount = odsResultAllCountMapper.getReportJoinOutVisitJoinPatientLocationCount();
        Integer reportJoinInVisitJoinOutVisitJoinPatientLocationCount = odsResultAllCountMapper.getReportJoinInVisitJoinOutVisitJoinPatientLocationCount();
        Integer odsReportLocationCount = reportJoinInVisitJoinPatientLocationCount + reportJoinOutVisitJoinPatientLocationCount - reportJoinInVisitJoinOutVisitJoinPatientLocationCount;
        return odsReportLocationCount;
    }

    private Integer getObsReportLocationCount(){
        Integer resultLocationCount = obsResultAllCountMapper.getResultLocationCount();
        return resultLocationCount;
    }
}
