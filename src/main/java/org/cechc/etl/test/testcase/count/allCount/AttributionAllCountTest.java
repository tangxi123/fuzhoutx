package org.cechc.etl.test.testcase.count.allCount;


import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AttributionAllCountTest extends Basic {
    private static Integer count = 0;


    /**
     * 测试标题：对比ODS与OBS的attribution表条数
     * 测试步骤：因为没有attribution表的映射，所以attribution表的条数为0
     * 期望结果：条数为0
     */
    @Test(description = "验证obs的attribution表的条数为0")
    public void compare_attribution_count(){
        Integer odsAttributionCount = count;
        Integer obsAttributionCount = obsAttributionAllCountMapper.getAttributionCount();

        Assert.assertEquals(odsAttributionCount,obsAttributionCount);
    }

}

