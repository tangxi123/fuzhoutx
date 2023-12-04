package org.cechc.etl.test.obsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface OBSProviderFieldMapper {
    /**
     * 根据provider_id查询provider数据
     * @param providerId
     * @return
     */
    @Select(" SELECT * FROM provider WHERE record_id = #{providerId};")
    HashMap<String,Object> getProviderInfoByProviderId(String providerId);

    /**
     * 根据provider_id查询provider_identifier数据
     * @param providerId
     * @return
     */
    @Select(" SELECT * FROM provider_identifier WHERE prov_id = #{providerId};")
    ArrayList<HashMap<String,Object>> getProviderIdentiInfoByProviderId(String providerId);

    /**
     * 根据provider_id查询provider_specialty数据
     * @param providerId
     * @return
     */
    @Select(" SELECT * FROM provider_specialty WHERE prov_id = #{providerId};")
    HashMap<String,Object> getProviderspecialtyInfoByProviderId(String providerId);

    /**
     * 根据provider_id查询provider_address数据
     * @param providerId
     * @return
     */
    @Select(" SELECT * FROM provider_address WHERE prov_id = #{providerId};")
    HashMap<String,Object> getProviderAddressInfoByProviderId(String providerId);
}
