package org.cechc.etl.test.testcase.count.allCount;


import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EncounterAllCountTest extends Basic {


    @Test(description = "验证ods映射到obs的Encounter表条数一致")
    public void compare_encounter_count(){
        Integer odsEncounterCount = getOdsEncounterCount();
        Integer obsEncounterCount = getObsEncounterCount();
        Assert.assertEquals(odsEncounterCount,obsEncounterCount);
    }


    @Test(description = "验证ods映射到obs的Encounter_location表条数一致")
    public void compare_encounter_location_count(){
        Integer odsEncounterLocationCount = getOdsEncounterLocationCount();
        Integer obsEncounterLocationCount = getObsEncounterLocationCount();
        Assert.assertEquals(odsEncounterLocationCount,obsEncounterLocationCount);
    }

    @Test(description = "验证ods映射到obs的Encounter_provider表条数一致")
    public void compare_encounter_provider_count(){
        Integer odsEncounterProCount = getOdsEncounterProviderCount();
        Integer obsEncounterProCount = getObsEncounterProviderCount();
        Assert.assertEquals(odsEncounterProCount,obsEncounterProCount);
    }


    private Integer getOutVisitCount(){
        Integer outVisitJoinPatientBasicCount = odsEncounterAllCountMapper.getOutVisitJoinPatientBasicCount();
        Integer outVisitJoinCaseBaseCount = odsEncounterAllCountMapper.getOutVisitJoinCaseBaseCount();
        Integer outVisitJoinPatientBasicJoinCaseBaseCount = odsEncounterAllCountMapper.getOutVisitJoinPatientBasicJoinCaseBaseCount();
        Integer outVisitCount = outVisitJoinPatientBasicCount + outVisitJoinCaseBaseCount - outVisitJoinPatientBasicJoinCaseBaseCount;
        return outVisitCount;
    }

    private Integer getInpVisitCount(){
        Integer inVisitJoinPatientBasicCount = odsEncounterAllCountMapper.getInVisitJoinPatientBasicCount();
        Integer inVisitJoinCaseBaseCount = odsEncounterAllCountMapper.getInVisitJoinCaseBaseCount();
        Integer inVisitJoinPatientBasicJoinCaseBaseCount = odsEncounterAllCountMapper.getInVisitJoinPatientBasicJoinCaseBaseCount();
        Integer inVisitCount = inVisitJoinPatientBasicCount + inVisitJoinCaseBaseCount - inVisitJoinPatientBasicJoinCaseBaseCount;
        return inVisitCount;
    }

    private Integer getOdsEncounterCount(){
        Integer outVisitCount = getOutVisitCount();
        Integer inVisitCount = getInpVisitCount();
        Integer odsEncounterCount = outVisitCount + inVisitCount;
        return odsEncounterCount;
    }

    private Integer getObsEncounterCount(){
        Integer obsEncounterCount = obsEncounterAllCountMapper.getEncounterCount();
        return obsEncounterCount;
    }

    private Integer getOutVisitLocationCount(){
        Integer outVisitJoinPatientBasicLocationCount = odsEncounterAllCountMapper.getOutVisitJoinPatientBasicLocationCount();
        Integer outVisitJoinCaseBaseLocationCount = odsEncounterAllCountMapper.getOutVisitJoinCaseBaseLocationCount();
        Integer outVisitJoinPatientBasicJoinCaseBaseLocationCount = odsEncounterAllCountMapper.getOutVisitJoinPatientBasicJoinCaseBaseLocationCount();
        Integer outVisitLocationCount = outVisitJoinPatientBasicLocationCount + outVisitJoinCaseBaseLocationCount - outVisitJoinPatientBasicJoinCaseBaseLocationCount;
        return outVisitLocationCount;
    }

    private Integer getInpVisitLocationCount(){
        Integer inVisitJoinPatientBasicLocationCount = odsEncounterAllCountMapper.getInVisitJoinPatientBasicLocationCount();
        Integer inVisitJoinCaseBaseLocationCount = odsEncounterAllCountMapper.getInVisitJoinCaseBaseLocationCount();
        Integer inVisitJoinPatientBasicJoinCaseBaseLocationCount = odsEncounterAllCountMapper.getInVisitJoinPatientBasicJoinCaseBaseLocationCount();
        Integer inVisitLocationCount = inVisitJoinPatientBasicLocationCount + inVisitJoinCaseBaseLocationCount - inVisitJoinPatientBasicJoinCaseBaseLocationCount;
        return inVisitLocationCount;
    }

    private Integer getOdsEncounterLocationCount(){
        Integer outVisitLocationCount = getOutVisitLocationCount();
        Integer inVisitLocationCount = getInpVisitLocationCount();
        Integer odsEncounterLocationCount = outVisitLocationCount + inVisitLocationCount;
        return odsEncounterLocationCount;
    }

    private Integer getObsEncounterLocationCount(){
        Integer obsEncounterLocCount = obsEncounterAllCountMapper.getEncounterLocCount();
        return obsEncounterLocCount;
    }

    private Integer getOutVisitProviderCount(){
        Integer outVisitJoinPatientBasicProviderCount = odsEncounterAllCountMapper.getOutVisitJoinPatientBasicProviderCount();
        Integer outVisitJoinCaseBaseProviderCount = odsEncounterAllCountMapper.getOutVisitJoinCaseBaseProviderCount();
        Integer outVisitJoinPatientBasicJoinCaseBaseProviderCount = odsEncounterAllCountMapper.getOutVisitJoinPatientBasicJoinCaseBaseProviderCount();
        Integer outVisitProviderCount = outVisitJoinPatientBasicProviderCount + outVisitJoinCaseBaseProviderCount - outVisitJoinPatientBasicJoinCaseBaseProviderCount;
        return outVisitProviderCount;
    }

    private Integer getInpVisitProviderCount(){
        Integer inVisitJoinPatientBasicProviderCount = odsEncounterAllCountMapper.getInVisitJoinPatientBasicProviderCount();
        Integer inVisitJoinCaseBaseProviderCount = odsEncounterAllCountMapper.getInVisitJoinCaseBaseProviderCount();
        Integer inVisitJoinPatientBasicJoinCaseBaseProviderCount = odsEncounterAllCountMapper.getInVisitJoinPatientBasicJoinCaseBaseProviderCount();
        Integer inVisitProviderCount = inVisitJoinPatientBasicProviderCount + inVisitJoinCaseBaseProviderCount - inVisitJoinPatientBasicJoinCaseBaseProviderCount;
        return inVisitProviderCount;
    }

    private Integer getOdsEncounterProviderCount(){
        Integer outVisitProviderCount = getOutVisitProviderCount();
        Integer inVisitProviderCount = getInpVisitProviderCount();
        Integer odsEncounterProviderCount = outVisitProviderCount + inVisitProviderCount;
        return odsEncounterProviderCount;
    }

    private Integer getObsEncounterProviderCount(){
        Integer obsEncounterProviderCount = obsEncounterAllCountMapper.getEncounterProCount();
        return obsEncounterProviderCount;
    }



}
