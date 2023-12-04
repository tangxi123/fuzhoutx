package org.cechc.etl.test.testcase.count.allCount;



import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ImmunizationAllCountTest extends Basic {

    private static Integer count = 0;
    /**
     * 测试标题：对比ODS与OBS的immunization表条数
     * 测试步骤：因为没有immunization表的映射，所以immunization表的条数为0
     * 期望结果：条数为0
     */
    @Test(description = "验证obs的immunization表条数是否为0")
    public void compare_immunization_count(){
        Integer odsImmunizationCount = count;
        Integer obsImmunizationCount = obsImmunizationAllCountMapper.getImmunizationCount();
        Assert.assertEquals(odsImmunizationCount,obsImmunizationCount);

    }

    /**
     * 测试标题：对比ODS与OBS的immunization_location表条数
     * 测试步骤：因为没有immunization_location表的映射，所以immunization_location表的条数为0
     * 期望结果：条数为0
     */
    @Test(description = "验证obs的immunization_location表条数是否为0")
    public void compare_immunization_location_count(){
        Integer odsImmunizationLocCount = count;
        Integer obsImmunizationLocCount = obsImmunizationAllCountMapper.getImmunizationLocCount();
        Assert.assertEquals(odsImmunizationLocCount,obsImmunizationLocCount);
    }

    /**
     * 测试标题：对比ODS与OBS的immunization_provider表条数
     * 测试步骤：因为没有immunization_provider表的映射，所以immunization_provider表的条数为0
     * 期望结果：条数为0
     */
    @Test(description = "验证obs的immunization_provider表条数是否为0")
    public void compare_immunization_provider_count(){
        Integer odsImmunizationProCount = count;
        Integer obsImmunizationProCount = obsImmunizationAllCountMapper.getImmunizationProCount();
        Assert.assertEquals(odsImmunizationProCount,obsImmunizationProCount);
    }

}
