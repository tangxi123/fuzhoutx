package org.cechc.etl.test.obsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface OBSScheduleFieldMapper {
    /**
     * 查询schedule
     */
    @Select("SELECT * FROM schedule where record_id = #{record_id};\n")
    HashMap<String, Object> getScheduleField(String record_id);

    /**
     * 查询schedule provider
     */
    @Select("SELECT * FROM schedule_provider where sched_id = #{record_id};\n")
    ArrayList<HashMap<String, Object>> getScheduleProvField(String record_id);

    /**
     * 查询schedule location
     */
    @Select("SELECT * FROM schedule_location where sched_id = #{record_id};\n")
    ArrayList<HashMap<String, Object>> getScheduleLocField(String record_id);
}
