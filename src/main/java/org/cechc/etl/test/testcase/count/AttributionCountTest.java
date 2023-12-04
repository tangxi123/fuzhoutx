package org.cechc.etl.test.testcase.count;


import org.cechc.etl.test.testcase.Basic;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AttributionCountTest extends Basic {
    private static Integer count = 0;


    /**
     * 测试标题：对比ODS与OBS的attribution表条数
     * 测试步骤：因为没有attribution表的映射，所以attribution表的条数为0
     * 期望结果：条数为0
     */
    @Test(description = "验证obs的attribution条数是否为0")
    public void compare_odsAttribution_and_obsAttribution_count(){
        Integer odsAttributionCount = count;
        Integer obsAttributionCount = obsAttributionCountMapper.getAttributionCount();

        Assert.assertEquals(odsAttributionCount,obsAttributionCount);
    }

}
