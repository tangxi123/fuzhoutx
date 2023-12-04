package org.cechc.etl.test.obsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface OBSAdmissionFieldMapper {
    /**
     * 查询admission数据
     */
    @Select("SELECT * FROM admission where record_id = #{record_id} and src_id = '11'")
    HashMap<String,Object> getAdmissionField(String record_id);

    /**
     * 查询admission provider数据
     */
    @Select("SELECT * FROM admission_provider where adm_id = #{record_id};")
    ArrayList<HashMap<String,Object>> getAdmissionProv(String record_id);

    /**
     * 查询admission location数据
     */
    @Select("SELECT * FROM admission_location where adm_id = #{record_id};\n")
    ArrayList<HashMap<String,Object>> getAdmissionLoc(String record_id);
}
