package org.cechc.etl.test.obsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface OBSOrderFieldMapper {
    /**
     * 查询order_clinical数据
     */
    @Select(" SELECT * FROM order_clinical where record_id = #{order_id};")
    HashMap<String,Object> getOrderInfoByOrderId(String order_id);

    /**
     * 查询order_provider数据
     */
    @Select(" SELECT * FROM order_provider where ord_id = #{order_id};")
    ArrayList<HashMap<String,Object>> getOrderProviderInfoByOrdertId(String order_id);

    /**
     * 查询order_location数据
     */
    @Select(" SELECT * FROM order_location where ord_id = #{order_id};")
    ArrayList<HashMap<String,Object>> getOrderLocInfoByOrdertId(String order_id);
}
