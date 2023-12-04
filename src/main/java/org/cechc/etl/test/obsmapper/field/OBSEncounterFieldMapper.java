package org.cechc.etl.test.obsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface OBSEncounterFieldMapper {

    /**
     * 根据record_id查询encounter
     */
    @Select("SELECT * FROM encounter where record_id = #{record_id};\n")
    HashMap<String,Object> getEncFieldByRecordId(String record_id);

    /**
     * 根据record_id查询encounter_provider
     */
    @Select("SELECT * FROM encounter_provider where enc_id = #{record_id};\n")
    ArrayList<HashMap<String,Object>> getEncProFieldByRecordId(String record_id);

    /**
     * 根据record_id查询encounter_loc
     */
    @Select("SELECT * FROM encounter_location where enc_id = #{record_id};\n")
    ArrayList<HashMap<String,Object>> getEncLocFieldByRecordId(String record_id);
}
