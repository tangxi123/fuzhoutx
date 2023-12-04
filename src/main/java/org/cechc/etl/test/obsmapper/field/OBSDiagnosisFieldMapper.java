package org.cechc.etl.test.obsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface OBSDiagnosisFieldMapper {
    /**
     * 查询diagnosis
     */
    @Select("SELECT * FROM diagnosis where record_id = #{record_id};\n")
    HashMap<String,Object> getDiagnosisField(String record_id);

    /**
     * 查询diagnosis provider
     */
    @Select("SELECT * FROM diagnosis_provider where diag_id = #{record_id};\n")
    ArrayList<HashMap<String,Object>> getDiagnosisProv(String record_id);

    /**
     * 查询diagnosis location
     */
    @Select("SELECT * FROM diagnosis_location where diag_id = #{record_id};\n")
    ArrayList<HashMap<String,Object>> getDiagnosisLoc(String record_id);
}
