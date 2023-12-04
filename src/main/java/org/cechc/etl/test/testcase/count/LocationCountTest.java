package org.cechc.etl.test.testcase.count;

import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LocationCountTest extends Basic {
    private static Integer hospitalLocCount = 47;

    /**
     * 测试标题：对比ODS与OBS的location表条数
     * 测试步骤：对比ods的location表与OBS的location表的条数
     * 期望结果：条数为0
     */
    @Test(description = "验证obs的location的条数")
    public void compare_obsLocation_count(){
        Integer outpVisitDeptCount = odsLocationCountMapper.getOutpVisitDeptCount();
        Integer inpVisitDeptCount = odsLocationCountMapper.getInpVisitDeptCount();
        Integer inpVisitBedCount = odsLocationCountMapper.getInpVisitBedCount();
        Integer inpVisitRoomCount = odsLocationCountMapper.getInpVisitRoomCount();
        Integer inpVisitWardCount = odsLocationCountMapper.getInpVisitWardCount();
        Integer odsLocationCount = outpVisitDeptCount + inpVisitDeptCount + inpVisitBedCount + inpVisitRoomCount +inpVisitWardCount + hospitalLocCount;

        Integer obsLocationCount = obsLocationCountMapper.getLocationCount();

        Assert.assertEquals(odsLocationCount,obsLocationCount);

    }
}
