package org.cechc.etl.test.obsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface OBSResultFieldMapper {
    /**
     * 查询result数据
     */
    @Select("select * from result where record_id = #{record_id}")
    HashMap<String,Object> getResultField(String record_id);

    @Select("select * from result_provider where result_id = #{record_id}")
    ArrayList<HashMap<String,Object>> getResultProvField(String record_id);

    @Select("select * from result_location where result_id = #{record_id}")
    ArrayList<HashMap<String,Object>> getResultLocField(String record_id);
}
