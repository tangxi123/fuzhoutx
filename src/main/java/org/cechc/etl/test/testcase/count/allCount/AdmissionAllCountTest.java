package org.cechc.etl.test.testcase.count.allCount;


import org.cechc.etl.test.obsmapper.count.OBSAdmissionCountMapper;
import org.cechc.etl.test.odsmapper.count.ODSAdmissionCountMapper;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AdmissionAllCountTest extends Basic {

    @Test(description = "验证ods映射到obs的admission表的条数一致")
    public void compare_admission_count() {
        Integer odsAdmissionCount = getOdsAdmissionCount();
        Integer obsAdmissionCount = getObsAdmissionCount();
        Assert.assertEquals(odsAdmissionCount, obsAdmissionCount);
    }


    @Test(description = "验证ods映射admission_location表的条数")
    public void compare_admission_location_count() {
        Integer odsAdmissionLocationCount = getOdsAdmissionLocationCount();
        Integer obsAdmissionLocationCount = getObsAdmissionLocationCount();
        Assert.assertEquals(odsAdmissionLocationCount,obsAdmissionLocationCount);

    }

    @Test(description = "验证ods映射admission_provider表的条数")
    public void compare_admission_provider_count() {
        Integer odsAdmissionProviderCount = getOdsAdmissionProviderCount();
        Integer obsAdmissionProviderCount = getObsAdmissionProviderCount();
        Assert.assertEquals(odsAdmissionProviderCount, obsAdmissionProviderCount);
    }

    private Integer getOdsAdmissionCount(){
        Integer inVisitJoinPatientBasicCount = odsAdmissionAllCountMapper.getInVisitJoinPatientBasicCount();
        Integer inVisitJoinCaseBaseCount = odsAdmissionAllCountMapper.getInVisitJoinCaseBaseCount();
        Integer inVisitJoinPatientBasicJoinCaseBaseCount = odsAdmissionAllCountMapper.getInVisitJoinPatientBasicJoinCaseBaseCount();
        Integer odsAdmissionCount = inVisitJoinPatientBasicCount + inVisitJoinCaseBaseCount - inVisitJoinPatientBasicJoinCaseBaseCount;
        return odsAdmissionCount;
    }

    private Integer getObsAdmissionCount(){
        Integer obsAdmissionCount = obsAdmissionAllCountMapper.getAdmissionCount();
        return obsAdmissionCount;
    }

    private Integer getOdsAdmissionLocationCount(){
        Integer inVisitJoinPatientBasicLocationCount = odsAdmissionAllCountMapper.getInVisitJoinPatientBasicLocationCount();
        Integer inVisitJoinCaseBaseLocationCount = odsAdmissionAllCountMapper.getInVisitJoinCaseBaseLocationCount();
        Integer inVisitJoinPatientBasicJoinCaseBaseLocationCount = odsAdmissionAllCountMapper.getInVisitJoinPatientBasicJoinCaseBaseLocationCount();
        Integer odsAdmissionLocationCount = inVisitJoinPatientBasicLocationCount + inVisitJoinCaseBaseLocationCount - inVisitJoinPatientBasicJoinCaseBaseLocationCount;
        return odsAdmissionLocationCount;
    }

    private Integer getObsAdmissionLocationCount(){
        Integer admissionLocationCount = obsAdmissionAllCountMapper.getAdmissionLocationCount();
        return admissionLocationCount;
    }

    private Integer getOdsAdmissionProviderCount(){
        Integer inVisitJoinPatientBasicProviderCount = odsAdmissionAllCountMapper.getInVisitJoinPatientBasicProviderCount();
        Integer inVisitJoinCaseBaseProviderCount = odsAdmissionAllCountMapper.getInVisitJoinCaseBaseProviderCount();
        Integer inVisitJoinPatientBasicJoinCaseBaseProviderCount = odsAdmissionAllCountMapper.getInVisitJoinPatientBasicJoinCaseBaseProviderCount();
        Integer odsAdmissionProviderCount = inVisitJoinPatientBasicProviderCount + inVisitJoinCaseBaseProviderCount - inVisitJoinPatientBasicJoinCaseBaseProviderCount;
        return odsAdmissionProviderCount;
    }

    private Integer getObsAdmissionProviderCount(){
        Integer admissionProviderCount = obsAdmissionAllCountMapper.getAdmissionProviderCount();
        return admissionProviderCount;
    }
}
