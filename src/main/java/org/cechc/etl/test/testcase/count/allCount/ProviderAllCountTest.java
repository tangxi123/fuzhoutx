package org.cechc.etl.test.testcase.count.allCount;


import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProviderAllCountTest extends Basic {

    @Test(description = "验证ods映射到obd的provider表的条数一致")
    public void compare_provider_count(){
        Integer odsProviderCount = odsProviderAllCountMapper.getDictStaffCount();
        Integer obsProviderCount = obsProviderAllCountMapper.getProviderCount();
        Assert.assertEquals(odsProviderCount,obsProviderCount);


    }

    @Test(description = "验证ods映射到obd的provider_address表的条数一致")
    public void compare_provider_address_count(){
        Integer odsDictStaffAddrCount = odsProviderAllCountMapper.getDictStaffAddrCount();
        Integer obsProviderAddrCount = obsProviderAllCountMapper.getProviderAddrCount();

        Assert.assertEquals(odsDictStaffAddrCount,obsProviderAddrCount);

    }


    @Test(description = "验证ods映射到obd的provider_identifier表的条数一致")
    public void compare_provider_identifier_count(){
        Integer odsDictStaffIdentiCount = odsProviderAllCountMapper.getDictStaffIdentiCount();
        Integer obsProviderIdentifierCount = obsProviderAllCountMapper.getProviderIdentifierCount();

        Assert.assertEquals(odsDictStaffIdentiCount,obsProviderIdentifierCount);

    }

    @Test(description = "验证ods映射到obd的provider_specialty表的条数一致")
    public void compare_provider_specialty_count(){
        Integer odsDictStaffSpecCount = odsProviderAllCountMapper.getDictStaffSpecCount();
        Integer obsProviderSpecCount = obsProviderAllCountMapper.getProviderSpecCount();

        Assert.assertEquals(odsDictStaffSpecCount,obsProviderSpecCount);
    }

    @Test(description = "验证ods映射到obd的provider_group表的条数一致")
    public void compare_provider_group_count(){
        Integer odsStaffGroupCount = odsProviderAllCountMapper.getDictStaffGroupCount();
        Integer obsProviderGroupCount = obsProviderAllCountMapper.getProviderGroupCount();
        Assert.assertEquals(odsStaffGroupCount,obsProviderGroupCount);
    }

}
