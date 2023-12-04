package org.cechc.etl.test.testcase.count;


import org.cechc.etl.test.testcase.Basic;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllergyCountTest extends Basic {
    private static Integer count = 0;

    /**
     * 测试标题：对比ODS与OBS的allergy表条数
     * 测试步骤：因为没有allergy表的映射，所以allergy表的条数为0
     * 期望结果：条数为0
     */
    @Test(description = "验证obs的allergy条数是否为0")
    public void compare_odsAllergy_and_obsAllergy_count(){
        Integer odsAllergyCount = count;
        Integer obsAllergyCount = obsAllergyCountMapper.getAllergyCount();

        Assert.assertEquals(odsAllergyCount,obsAllergyCount);
    }

    /**
     * 测试标题：对比ODS与OBS的allergy_location表条数
     * 测试步骤：因为没有allergy_location表的映射，所以allergy_location表的条数为0
     * 期望结果：条数为0
     */
    @Test(description = "验证obs的allergy_location条数是否为0")
    public void compare_odsAllergyLocation_and_obsAllergyLocation_count(){
        Integer odsAllergyLocCount = count;
        Integer obsAllergyLocCount = obsAllergyCountMapper.getAllergyLocCount();

        Assert.assertEquals(odsAllergyLocCount,obsAllergyLocCount);


    }

    /**
     * 测试标题：对比ODS与OBS的allergy_provider表条数
     * 测试步骤：因为没有allergy_provider表的映射，所以allergy_provider表的条数为0
     * 期望结果：条数为0
     */
    @Test(description = "验证obs的allergy_provider条数是否为0")
    public void compare_odsAllergyProvider_and_obsAllergyProvider_count(){
        Integer odsAllergyProCount = count;
        Integer obsAllergyLocCount = obsAllergyCountMapper.getAllergyLocCount();

        Assert.assertEquals(odsAllergyProCount,obsAllergyLocCount);
    }

}
