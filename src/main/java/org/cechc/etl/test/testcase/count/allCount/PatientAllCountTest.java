package org.cechc.etl.test.testcase.count.allCount;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

public class PatientAllCountTest extends Basic {

    @Test(description = "验证ods映射到obs的patient表条数一致")
    public void compare_patient_count(){
        Integer odsPatientCount = getOdsPatientCount();
        Integer obsPatientCount = getObsPatientCount();
        Assert.assertEquals(odsPatientCount, obsPatientCount,"ods条数："+odsPatientCount+" obs条数："+obsPatientCount+"。");
    }

//    @Test(description = "验证ods映射到obs的patient_identifier表条数一致")
//    public void compare_patient_identifier_count(){
//        Integer odsPatientIdentifierCount = getOdsPatientIdentifierCount();
//        Integer obsPatientIdentifierCount = getObsPatientIdentifierCount();
//
//        Assert.assertEquals(odsPatientIdentifierCount, obsPatientIdentifierCount);
//
//    }
//
//    @Test(description = "验证ods映射到obs的patient_address表条数一致")
//    public void compare_patient_address_count() {
//        Integer odsPatientAddressCount = getOdsPatientAddressCount();
//        Integer obsPtientIdentifierCount = getObsPtientIdentifierCount();
//        Assert.assertEquals(odsPatientAddressCount, obsPtientIdentifierCount);
//
//    }
//
//    @Test(description = "验证obs的patient_attribution表条数为0")
//    public void compare_patient_attribution_count() {
//        Integer odsPatientAttributionCount = 0;
//        Integer obsPatientAttributionCount = obsPatientAllCountMapper.getPatientAttributionCount();
//
//        Assert.assertEquals(odsPatientAttributionCount,obsPatientAttributionCount);
//    }
//
//
//    @Test(description = "验证ods映射到obs的patient_hx表条数一致")
//    public void compare_patient_hx_count() {
//        Integer odsPatientBasicInfoHxCount = getOdsPatientHxCount();
//        Integer obsPatientHxCount = getObsPatientHxCount();
//        Assert.assertEquals(odsPatientBasicInfoHxCount,obsPatientHxCount);
//
//    }
//
//    @Test(description = "验证obs的patient_program表条数为0")
//    public void compare_patient_program_count() {
//        Integer odsPatientProgramCount = 0;
//        Integer obsPatientProgramCount = obsPatientAllCountMapper.getPatientProgramCount();
//
//        Assert.assertEquals(odsPatientProgramCount,obsPatientProgramCount);
//
//    }

    private Integer getOdsPatientHxCount(){
        Integer outJoinPatient = odsPatientAllCountMapper.getOutJoinPatient();
        return outJoinPatient;
    }

    private Integer getObsPatientHxCount(){
        Integer patientHxCount = obsPatientAllCountMapper.getPatientHxCount();
        return patientHxCount;
    }

    private Integer getOdsPatientCount(){
        Integer odsPatientBasicInfoCount = odsPatientAllCountMapper.getPatientBasicInfoCount();
        Integer odsCaseBaseInfoPatientCount = odsPatientAllCountMapper.getCaseBaseInfoPatientCount();
        Integer duplicatePatientCount = odsPatientAllCountMapper.getDuplicatePatientCount();
        Integer odsPatientCount = odsPatientBasicInfoCount + odsCaseBaseInfoPatientCount-duplicatePatientCount;
        return odsPatientCount;
    }

    private Integer getObsPatientCount(){
        Integer obsPatientCount = obsPatientAllCountMapper.getPatientCount();
        return obsPatientCount;
    }

    private Integer getOdsPatientIdentifierCount(){
        Integer odsPatientBasicInfoIdentifierCount = odsPatientAllCountMapper.getPatientBasicInfoIdentifierCount();
        Integer odsCaseBaseInfoPatientIdentifierCount = odsPatientAllCountMapper.getCaseBaseInfoPatientIdentifierCount();
        Integer caseBaseDuplicatePatIdCount = odsPatientAllCountMapper.getCaseBaseDuplicatePatIdCount();
        Integer odsPatientIdentifierCount = odsPatientBasicInfoIdentifierCount + odsCaseBaseInfoPatientIdentifierCount-caseBaseDuplicatePatIdCount;
        return odsPatientIdentifierCount;
    }

    private Integer getObsPatientIdentifierCount(){
        Integer obsPatientIdentifierCount = obsPatientAllCountMapper.getPatientIdentifierCount();
        return obsPatientIdentifierCount;
    }

    private Integer getOdsPatientAddressCount(){
        Integer odsPatientBasicInfoCount = odsPatientAllCountMapper.getPatientBasicInfoAddressCount();
        Integer odsCaseBaseInfoPatientAddressCount = odsPatientAllCountMapper.getCaseBaseInfoPatientAddressCount();
        Integer duplicatePatientAddressCount = odsPatientAllCountMapper.getDuplicatePatientAddressCount();

        Integer odsPatientAddressCount = odsPatientBasicInfoCount+odsCaseBaseInfoPatientAddressCount-duplicatePatientAddressCount;
        return odsPatientAddressCount;
    }

    private Integer getObsPtientIdentifierCount(){
        Integer obsPtientIdentifierCount = obsPatientAllCountMapper.getPatientAddressCount();
        return obsPtientIdentifierCount;
    }

}
