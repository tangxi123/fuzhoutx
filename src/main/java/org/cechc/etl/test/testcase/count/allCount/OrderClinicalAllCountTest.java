package org.cechc.etl.test.testcase.count.allCount;



import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class OrderClinicalAllCountTest extends Basic {

    @Test(description = "验证ods映射到obs的order_clinical表的条数一致")
    public void compare_order_clinical_count(){
        Integer odsOrderCount = getOdsOrderClinicalCount();
        Integer obsOrderCount = getObsOrderClinicalCount();
        Assert.assertEquals(odsOrderCount,obsOrderCount);
    }

    @Test(description = "验证ods映射到obs的order_location表的条数一致")
    public void compare_order_location_count(){
        Integer odsOrderLocount = getOdsOrderClinicalLocationCount();
        Integer obsOrderLocCount = getObsOrderClinicalLocationCount();
        Assert.assertEquals(odsOrderLocount,obsOrderLocCount);
    }

    @Test(description = "验证ods映射到obs的order_provider表的条数一致")
    public void compare_order_provider_count(){
        Integer odsOrderProCount = getOdsOrderClinicalProviderCount();
        Integer obsOrderProCount = getObsOrderClinicalProviderCount();
        Assert.assertEquals(odsOrderProCount,obsOrderProCount);

    }

    private Integer getOdsOrderClinicalCount(){
        Integer inJoinPatientCount = odsOrderAllCountMapper.getInJoinPatientCount();
        Integer outJoinPatientCount = odsOrderAllCountMapper.getOutJoinPatientCount();
        Integer odsOrderClinicalCount = inJoinPatientCount + outJoinPatientCount;
        return odsOrderClinicalCount;
    }

    private Integer getObsOrderClinicalCount(){
        Integer orderCount = obsOrderAllCountMapper.getOrderCount();
        return orderCount;
    }

    private Integer getOdsOrderClinicalLocationCount(){
        Integer inJoinPatientLocationCount = odsOrderAllCountMapper.getInJoinPatientLocationCount();
        Integer outJoinPatientLocationCount = odsOrderAllCountMapper.getOutJoinPatientLocationCount();
        Integer odsOrderLocationCount = inJoinPatientLocationCount + outJoinPatientLocationCount;
        return odsOrderLocationCount;
    }

    private Integer getObsOrderClinicalLocationCount(){
        Integer orderLocCount = obsOrderAllCountMapper.getOrderLocCount();
        return orderLocCount;
    }

    private Integer getOdsOrderClinicalProviderCount(){
        Integer inJoinPatientProviderCount = odsOrderAllCountMapper.getInJoinPatientProviderCount();
        Integer outJoinPatientProviderCount = odsOrderAllCountMapper.getOutJoinPatientProviderCount();
        Integer odsOrderProvider = inJoinPatientProviderCount + outJoinPatientProviderCount;
        return odsOrderProvider;
    }

    private Integer getObsOrderClinicalProviderCount(){
        Integer orderProCount = obsOrderAllCountMapper.getOrderProCount();
        return orderProCount;
    }

}
