package org.cechc.etl.test.obsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Mapper
public interface OBSLocationFieldMapper {
    /**
     * 根据record_id查询location数据
     *
     */
    @Select("select * from location where record_id = #{record_id} and src_id = #{src_id}")
    HashMap<String,Object> getLocationByRecordIdAndSrcId(String record_id,String src_id);
}
