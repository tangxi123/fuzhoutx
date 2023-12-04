package org.cechc.etl.test.testcase.count;


import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.cechc.etl.test.testcase.util.DataProviderUtil;
import org.cechc.etl.test.testcase.util.TestUtil;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ProviderCountTest extends Basic {
//    {
//        super.setCreateStartTime("2020-04-13 11:10:31");
//        super.setCreateEndTime("2020-04-13 11:10:31");
//        super.setModifyStartTime("2020-04-13 11:10:31");
//        super.setModifyEndTime("2020-04-13 11:10:31");
//    }

//    @DataProvider(name = "dictSrcAndDestCountsDataProvider")
//    private Iterator<Object[]> patientBaseInfoListDataProvider() {
//        ArrayList<ArrayList<HashMap<String, Object>>> dictSrcAndDestCounts = new ArrayList<>();
//
//        /*provider*/
//        HashMap<String, Object> provSrcAndDestCount = get1to1SrcAndDestCount(odsProviderCountMapper::getDictStaffIdList, obsProviderCountMapper::getProviderCount);
//        ArrayList<HashMap<String, Object>> provSrcAndDestCounts = toList(provSrcAndDestCount);
//
//        /*provider address*/
//        HashMap<String, Object> provAddrSrcAndDestCount = get1toManySrcAndDestCount(odsProviderCountMapper::getDictStaffAddrCountList, obsProviderCountMapper::getProviderAddrCount);
//        ArrayList<HashMap<String, Object>> provAddrSrcAndDestCounts = toList(provAddrSrcAndDestCount);
//
//        /*provider identifier*/
//        HashMap<String, Object> provIdenSrcAndDestCount = get1toManySrcAndDestCount(odsProviderCountMapper::getDictStaffIdentiCountList, obsProviderCountMapper::getProviderIdentifierCount);
//        ArrayList<HashMap<String, Object>> provIdenSrcAndDestCounts = toList(provIdenSrcAndDestCount);
//
//
//        dictSrcAndDestCounts.add(provSrcAndDestCounts);
//        dictSrcAndDestCounts.add(provAddrSrcAndDestCounts);
//        dictSrcAndDestCounts.add(provIdenSrcAndDestCounts);
//
//        return DataProviderUtil.getDataProvider2(dictSrcAndDestCounts);
//    }

    @DataProvider(name = "getProviderDataProvider")
    private Iterator<Object[]> getProviderDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(ProviderCountTest.class,method,context);
        HashMap<String, Object> provSrcAndDestCount = get1to1SrcAndDestCount(odsProviderCountMapper::getDictStaffIdList, obsProviderCountMapper::getProviderCount,parameter);
        ArrayList<HashMap<String, Object>> provSrcAndDestCounts = toList(provSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(provSrcAndDestCounts);

    }

    @DataProvider(name = "getProviderAddressDataProvider")
    private Iterator<Object[]> getProviderAddressDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(ProviderCountTest.class,method,context);
        HashMap<String, Object> provAddrSrcAndDestCount = get1toManySrcAndDestCount(odsProviderCountMapper::getDictStaffAddrCountList, obsProviderCountMapper::getProviderAddrCount,parameter);
        ArrayList<HashMap<String, Object>> provAddrSrcAndDestCounts = toList(provAddrSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(provAddrSrcAndDestCounts);
    }

    @DataProvider(name = "getProviderIdentifierDataProvider")
    private Iterator<Object[]> getProviderIdentifierDataProvider(Method method, ITestContext context) {
        Parameter parameter = TestUtil.getParamValue(ProviderCountTest.class,method,context);
        HashMap<String, Object> provIdenSrcAndDestCount = get1toManySrcAndDestCount(odsProviderCountMapper::getDictStaffIdentiCountList, obsProviderCountMapper::getProviderIdentifierCount,parameter);
        ArrayList<HashMap<String, Object>> provIdenSrcAndDestCounts = toList(provIdenSrcAndDestCount);
        return DataProviderUtil.getDataProvider4(provIdenSrcAndDestCounts);


    }

    /**
     * 测试标题：对比ODS与OBS的provider表条数
     * 测试步骤：对比ODS的dict_staff_dict与OBS的provider表的条数
     * 期望结果：条数一致
     */
    @Test(dataProvider = "getProviderDataProvider",description = "根据时间范围，验证ods的dict_staff_dict表条数与obs的provider表一致")
    public void compare_odsProvider_and_obsProvider_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

    @Test(dataProvider = "getProviderAddressDataProvider",description = "根据时间范围，验证ods的dict_staff_dict表条数与obs的provider_address表一致")
    public void compare_odsProviderAddress_and_obsProviderAddress_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

    @Test(dataProvider = "getProviderIdentifierDataProvider",description = "根据时间范围，验证ods的dict_staff_dict表条数与obs的provider_identifier表一致")
    public void compare_odsProviderIdentifier_and_obsProviderIdentifier_count(ArrayList<HashMap<String, Object>> srcAndDestCount) {
        compare(srcAndDestCount);
    }

    /**
     * 测试标题：对比ODS与OBS的provider_group表条数
     * 测试步骤：对比ODS的dict_staff_dict与group相关联的条数与OBS的provider_groups表的条数
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的provider_group条数为0")
    public void compare_odsProviderGroup_and_obsProviderGroup_count() {
        long odsGroupCount = 0;
        long obsProviderGroupCount = obsProviderCountMapper.getProviderGroupCount();
        Assert.assertEquals(odsGroupCount, obsProviderGroupCount);

    }



}
