package org.cechc.etl.test.obsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSProviderAllCountMapper {
    /**
     * 获取provider条数
     */
    @Select("SELECT COUNT(*) FROM provider;")
    Integer getProviderCount();

    /**
     * 获取provider_address条数
     */
    @Select("SELECT COUNT(*) FROM provider_address;")
    Integer getProviderAddrCount();

    /**
     * 获取provider_group条数
     */
    @Select("SELECT COUNT(*) FROM provider_group;")
    Integer getProviderGroupCount();

    /**
     * 获取provider_identifier条数
     */
    @Select("SELECT COUNT(*) FROM provider_identifier;")
    Integer getProviderIdentifierCount();

    /**
     * 获取provider_specialty条数
     */
    @Select("SELECT COUNT(*) FROM provider_specialty;")
    Integer getProviderSpecCount();
}
