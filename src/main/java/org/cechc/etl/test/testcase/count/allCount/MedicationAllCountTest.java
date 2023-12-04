package org.cechc.etl.test.testcase.count.allCount;


import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MedicationAllCountTest extends Basic {

    @Test(description = "验证od映射到obs的medication表的条数一致")
    public void compare_medication_count(){
        Integer odsMedicationCount = getOdsMedicationCount();
        Integer obsMedicationCount = getObsMedicationCount();
        Assert.assertEquals(odsMedicationCount,obsMedicationCount);

    }

    @Test(description = "验证od映射到obs的medication_location表的条数一致")
    public void compare_medication_location_count(){
        Integer odsMedicationLocCount = getOdsMedicationLocationCount();
        Integer obsMedicationLocCount = getObsMedicationLocationCount();
        Assert.assertEquals(odsMedicationLocCount,obsMedicationLocCount);


    }

    @Test(description = "验证od映射到obs的medication_provider表的条数一致")
    public void compare_medication_provider_count(){
        Integer odsMedicationProCount = getOdsMedicationProviderCount();
        Integer obsMedicationProCount = getObsMedicationProviderCount();
        Assert.assertEquals(odsMedicationProCount,obsMedicationProCount);

    }

    private Integer getOdsMedicationCount(){
        Integer inJoinPatientCount = odsMedicationAllCountMapper.getInJoinPatientCount();
        Integer outJoinPatientCount = odsMedicationAllCountMapper.getOutJoinPatientCount();
        Integer odsPatientCount = inJoinPatientCount + outJoinPatientCount;
        return odsPatientCount;
    }

    private Integer getObsMedicationCount(){
        Integer medicationCount = obsMedicationAllCountMapper.getMedicationCount();
        return medicationCount;
    }

    private Integer getOdsMedicationLocationCount(){
        Integer inJoinPatientLocationCount = odsMedicationAllCountMapper.getInJoinPatientLocationCount();
        Integer outJoinPatientLocationCount = odsMedicationAllCountMapper.getOutJoinPatientLocationCount();
        Integer odsMedicationLocationCount = inJoinPatientLocationCount + outJoinPatientLocationCount;
        return odsMedicationLocationCount;
    }

    private Integer getObsMedicationLocationCount(){
        Integer medicationLocCount = obsMedicationAllCountMapper.getMedicationLocCount();
        return medicationLocCount;
    }

    private Integer getOdsMedicationProviderCount(){
        Integer inJoinPatientProviderCount = odsMedicationAllCountMapper.getInJoinPatientProviderCount();
        Integer outJoinPatientProviderCount = odsMedicationAllCountMapper.getOutJoinPatientProviderCount();
        Integer odsMedicationProviderCount = inJoinPatientProviderCount + outJoinPatientProviderCount;
        return odsMedicationProviderCount;
    }

    private Integer getObsMedicationProviderCount(){
        Integer medicationProCount = obsMedicationAllCountMapper.getMedicationProCount();
        return medicationProCount;
    }

}
