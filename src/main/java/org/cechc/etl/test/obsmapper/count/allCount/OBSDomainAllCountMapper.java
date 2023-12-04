package org.cechc.etl.test.obsmapper.count.allCount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OBSDomainAllCountMapper {
    /**
     * 查询Address_type_domain的条数
     */
    @Select("SELECT COUNT(*) FROM address_type_domain;")
    Integer getAddressTypeDomainCount();

    /**
     * 查询codeset_domain条数
     */
    @Select("SELECT COUNT(*) FROM codeset_domain;")
    Integer getCodesetDomainCount();

    /**
     * 查询identifier_type_domain条数
     */
    @Select("SELECT COUNT(*) FROM identifier_type_domain;")
    Integer getIdentifierTypeDomainCount();

    /**
     * 查询location_type_domain条数
     */
    @Select("SELECT COUNT(*) FROM location_type_domain;")
    Integer getLocationTypeDomainCount();

    /**
     * 查询patient_contact_method_domain条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM patient_contact_method_domain;")
    Integer getPatientContactMethodDomainCount();

    /**
     *查询patient_hx_type_domain条数
     */
    @Select("SELECT COUNT(*) FROM patient_hx_type_domain;")
    Integer getPatientHxTypeDomain();

    /**
     * 查询provider_role_domain条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM provider_role_domain;")
    Integer getProviderRoleDomain();

    /**
     * 查询source_type_domain条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM source_type_domain;")
    Integer getSourceTypeDomain();
}
