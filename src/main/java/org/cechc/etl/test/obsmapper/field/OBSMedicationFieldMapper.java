package org.cechc.etl.test.obsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface OBSMedicationFieldMapper {
    /**
     * 根据record_id查询medication相关数据
     */
    @Select("SELECT * FROM medication where record_id = #{record_id};")
    HashMap<String,Object> getMedicByrecordId(String record_id);

    /**
     * 根据record_id查询medication_provider相关数据
     */
    @Select("SELECT * FROM medication_provider where med_id = #{record_id};")
    ArrayList<HashMap<String,Object>> getMedicProviderByrecordId(String record_id);

    /**
     * 根据record_id查询medication_location相关数据
     */
    @Select("SELECT * FROM medication_location where med_id = #{record_id};\n")
    ArrayList<HashMap<String,Object>> getMedicLocByrecordId(String record_id);
}
