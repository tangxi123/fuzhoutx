package org.cechc.etl.test.testcase.count;

import org.cechc.etl.test.domain.*;
import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DomainCountTest extends Basic {


    /**
     * 测试标题：对比OBS的address_type_domain表条数
     * 测试步骤：对比excel的address_type_domain条数与OBS的address_type_domain表条数
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的address_type_domain表条数")
    public void compare_obsAddressTypeDomain_count(){
        Integer odsAddressTypeDomainCount = AddressTypeDomain.values().length;
        Integer obsAddressTypeDomainCount = obsDomainCountMapper.getAddressTypeDomainCount();

        Assert.assertEquals(odsAddressTypeDomainCount,obsAddressTypeDomainCount);

    }

    /**
     * 测试标题：对比OBS的codeset_domain表条数
     * 测试步骤：对比excel的codeset_domain条数与OBS的codeset_domain表条数
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的codeset_domain表条数")
    public void compare_obsCodesetDomain_count(){
        Integer odsCodesetDomainCount = CodesetDomain.values().length;
        Integer obsCodesetDomainCount = obsDomainCountMapper.getCodesetDomainCount();

        Assert.assertEquals(odsCodesetDomainCount,obsCodesetDomainCount);

    }

    /**
     * 测试标题：对比OBS的identifier_type_domain表条数
     * 测试步骤：对比excel的identifier_type_domain条数与OBS的identifier_type_domain表条数
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的identifier_type_domain表条数")
    public void compare_obsIdentifierTypeDomain_count(){
        Integer odsIdentifierTypeDomainCount = IdentifierTypeDomain.values().length;
        Integer obsIdentifierTypeDomainCount = obsDomainCountMapper.getIdentifierTypeDomainCount();

        Assert.assertEquals(odsIdentifierTypeDomainCount,obsIdentifierTypeDomainCount);

    }

    /**
     * 测试标题：对比OBS的insurance_type_domain表条数
     * 测试步骤：对比excel的insurance_type_domain条数与OBS的insurance_type_domain表条数
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的insurance_type_domain表条数")
    public void compare_obsInsuranceTypeDomain_count(){
        Integer odsInsuranceTypeDomainCount = InsuranceTypeDomain.values().length;
        Integer obsIdentifierTypeDomainCount = obsDomainCountMapper.getIdentifierTypeDomainCount();

        Assert.assertEquals(odsInsuranceTypeDomainCount,obsIdentifierTypeDomainCount);

    }

    /**
     * 测试标题：对比OBS的location_type_domain表条数
     * 测试步骤：对比excel的location_type_domain条数与OBS的location_type_domain表条数
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的location_type_domain表条数")
    public void compare_obsLocationTypeDomain_count(){
        Integer odsLocationTypeDomain = LocationTypeDomain.values().length;
        Integer obsLocationTypeDomain = obsDomainCountMapper.getLocationTypeDomainCount();

        Assert.assertEquals(odsLocationTypeDomain,obsLocationTypeDomain);

    }

    /**
     * 测试标题：对比OBS的patient_contact_method_domain表条数
     * 测试步骤：对比excel的patient_contact_method_domain条数与OBS的patient_contact_method_domain表条数
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的patient_contact_method_domain表条数")
    public void compare_obsPatientContactMethodDomain_count(){
        Integer odsPatientContactMethodDomain = PatientContactMethodDomain.values().length;
        Integer obsPatientContactMethodDomain = obsDomainCountMapper.getPatientContactMethodDomainCount();

        Assert.assertEquals(odsPatientContactMethodDomain,obsPatientContactMethodDomain);

    }

    /**
     * 测试标题：对比OBS的patient_hx_type_domain表条数
     * 测试步骤：对比excel的patient_hx_type_domain条数与OBS的patient_hx_type_domain表条数
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的patient_hx_type_domain表条数")
    public void compare_obsPatientHxTypeDomain_count(){
        Integer odsPatientHxTypeDomain = PatientHxTypeDomain.values().length;
        Integer obsPatientHxTypeDomain = obsDomainCountMapper.getPatientHxTypeDomain();

        Assert.assertEquals(odsPatientHxTypeDomain,obsPatientHxTypeDomain);

    }

    /**
     * 测试标题：对比OBS的provider_role_domain表条数
     * 测试步骤：对比excel的provider_role_domain条数与OBS的provider_role_domain表条数
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的provider_role_domain表条数")
    public void compare_obsProviderRoleDomain_count(){
        Integer odsProviderRoleDomain = ProviderRoleDomain.values().length;
        Integer obsProviderRoleDomain = obsDomainCountMapper.getProviderRoleDomain();

        Assert.assertEquals(odsProviderRoleDomain,obsProviderRoleDomain);

    }

    /**
     * 测试标题：对比OBS的source_type_domain表条数
     * 测试步骤：对比excel的provider_role_domain条数与OBS的source_type_domain表条数
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的source_type_domain表条数")
    public void compare_obsSourceTypeDomain_count(){
        Integer odsSourceTypeDomain = SourceTypeDomain.values().length;
        Integer obsSourceTypeDomain = obsDomainCountMapper.getSourceTypeDomain();

        Assert.assertEquals(odsSourceTypeDomain,obsSourceTypeDomain);
    }

}
