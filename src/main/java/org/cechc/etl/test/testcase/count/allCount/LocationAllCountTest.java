package org.cechc.etl.test.testcase.count.allCount;


import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LocationAllCountTest extends Basic {

    private static Integer hospitalLocCount = 47;

    /**
     * 测试标题：对比ODS与OBS的location表条数
     * 测试步骤：对比ods的location表与OBS的location表的条数
     * 期望结果：条数为0
     */
//    @Test(description = "验证obs的location条数")
//    public void compare_location_count(){
//        Integer odsLocationCount = getOdsLocationCount();
//        Integer obsLocationCount = getObsLocationCount();
//        Assert.assertEquals(odsLocationCount,obsLocationCount);
//
//    }
//
//    private Integer getOdsLocationCount(){
//        Integer orgLocCount = odsLocationAllCountMapper.getOrgLocCount();
//        Integer inKeShiLocCount = getInKeShiLocCount();
//        Integer inBingQuLocCount = getInBingQuLocCount();
//        Integer inBingFangLocCount = getInBingFangLocCount();
//        Integer inChuangWeiLocCount = getInChuangWeiLocCount();
//        Integer outKeShiLocCount = getOutKeShiLocCount();
//        Integer odsLocationCount = orgLocCount + inKeShiLocCount + inBingQuLocCount + inBingFangLocCount + inChuangWeiLocCount + outKeShiLocCount;
//        return odsLocationCount;
//    }
//
//    private Integer getInKeShiLocCount(){
//        Integer admissionKeshiLocCount =
//    }
//
//    private Integer getObsLocationCount(){
//        Integer obsLocationCount = obsLocationAllCountMapper.getLocationCount();
//        return obsLocationCount;
//
//    }

}
