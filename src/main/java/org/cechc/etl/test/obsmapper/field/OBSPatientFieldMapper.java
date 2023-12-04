package org.cechc.etl.test.obsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OBSPatientFieldMapper {
    /**
     * 根据patient_id查询patient_info数据
     *
     * @param patientId
     * @return
     */
    @Select(" SELECT * FROM patient WHERE record_id = #{patientId};")
    HashMap<String, Object> getPatientInfoByPatientId(String patientId);



    /**
     * 根据patient_id查询patient_identifier数据
     *
     * @param patientId
     * @return
     */
    @Select(" SELECT * FROM patient_identifier WHERE pat_id = #{patientId};")
    List<Map<String, Object>> getPatientIdentifierInfoByPatientId(String patientId);

    /**
     * 根据patient_id查询patient_address数据
     *
     * @param patientId
     * @return
     */
    @Select(" SELECT addr_type as transfer_type,addr_line_1,addr_line_2,city,state,zip,src_id,pat_id FROM patient_address WHERE pat_id = #{patientId};")
    ArrayList<HashMap<String, Object>> getPatientAddrInfoByPatientId(String patientId);


    /**
     * 根据patient_id查询patient_hx数据
     *
     * @param patientId
     * @return
     */
    @Select(" SELECT * FROM patient_hx WHERE pat_id = #{patientId};")
    ArrayList<HashMap<String, Object>> getPatientHxByPatientId(String patientId);


}
