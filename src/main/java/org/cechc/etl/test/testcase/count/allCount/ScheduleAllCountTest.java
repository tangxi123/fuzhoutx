package org.cechc.etl.test.testcase.count.allCount;


import org.cechc.etl.test.testcase.Basic;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ScheduleAllCountTest extends Basic {

    @Test(description = "验证ods的映射到obs的schedule表的条数一致")
    public void compare_schedule_count(){
        Integer odsOutpRegisterCount = getOdsScheduleCount();
        Integer obsScheduleCount = getObsScheduleCount();
        Assert.assertEquals(odsOutpRegisterCount,obsScheduleCount);

    }

    @Test(description = "验证ods的outp_register_record表条数与obs的schedule_provider表的条数")
    public void compare_schedule_provider_count(){
        Integer odsOutpRegisterLocCount = getOdsScheduleProviderCount();
        Integer obsScheduleLocCount = getObsScheduleProviderCount();
        Assert.assertEquals(odsOutpRegisterLocCount,obsScheduleLocCount);


    }

    @Test(description = "验证ods的outp_register_record表条数与obs的schedule_location表的条数")
    public void compare_schedule_location_count(){
        Integer odsOutpRegisterProCount = getOdsScheduleLocationCount();
        Integer obsScheduleProCount = getObsScheduleLocationCount();
        Assert.assertEquals(odsOutpRegisterProCount,obsScheduleProCount);

    }

    private Integer getOdsScheduleCount(){
        Integer outVisitJoinPatient = odsScheduleAllCountMapper.getOutVisitJoinPatientCount();
        Integer inVisitJoinPatient = odsScheduleAllCountMapper.getInVisitJoinPatientCount();
        Integer odsScheduleCount = inVisitJoinPatient + outVisitJoinPatient;
        return odsScheduleCount;
    }

    private Integer getObsScheduleCount(){
        Integer scheduleCount = obsScheduleAllCountMapper.getScheduleCount();
        return scheduleCount;
    }

    private Integer getOdsScheduleProviderCount(){
        Integer outVisitJoinPatientProviderCount = odsScheduleAllCountMapper.getOutVisitJoinPatientProviderCount();
        Integer inVisitJoinPatientProviderCount = odsScheduleAllCountMapper.getInVisitJoinPatientProviderCount();
        Integer odsScheduleProviderCount = outVisitJoinPatientProviderCount + inVisitJoinPatientProviderCount;
        return odsScheduleProviderCount;
    }

    private Integer getObsScheduleProviderCount(){
        Integer scheduleProCount = obsScheduleAllCountMapper.getScheduleProCount();
        return scheduleProCount;
    }

    private Integer getOdsScheduleLocationCount(){
        Integer inVisitJoinPatientLocationCount = odsScheduleAllCountMapper.getInVisitJoinPatientLocationCount();
        Integer outVisitJoinPatientLocationCount = odsScheduleAllCountMapper.getOutVisitJoinPatientLocationCount();
        Integer odsScheduleLocationCount = inVisitJoinPatientLocationCount + outVisitJoinPatientLocationCount;
        return odsScheduleLocationCount;
    }
    private Integer getObsScheduleLocationCount(){
        Integer scheduleLocCount = obsScheduleAllCountMapper.getScheduleLocCount();
        return scheduleLocCount;
    }
}
