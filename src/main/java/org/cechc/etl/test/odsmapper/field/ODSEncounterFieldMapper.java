package org.cechc.etl.test.odsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface ODSEncounterFieldMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifLimitStatement = "<if test='limit != null'>";
    String ifOffsetStatement = "<if test='offset != null'>";
    String ifOffsetNullStatement = "<if test='offset == null'>";
    String endIfStatement = "</if>";

    String selectConditions = ifLimitStatement +
            ifOffsetStatement +
            "limit #{offset},#{limit};" +
            endIfStatement +
            ifOffsetNullStatement +
            "limit #{limit};" +
            endIfStatement +
            endIfStatement;
    /**
     * 查询门诊Encounter
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       outp_visit_no,\n" +
            "       patient_id,\n" +
            "       0    as delete_flag,\n" +
            "       0    as test_record_flag,\n" +
            "       register_type_name,\n" +
            "       register_type_code,\n" +
            "       register_datetime,\n" +
            "       register_status_name,\n" +
            "       cast(height as decimal(5,2)) as height,\n" +
            "       cast(weight as decimal(5,2)) as weight,\n" +
            "       null as systolic_bp,\n" +
            "       null as diastolic_bp,\n" +
            "       null as pulse,\n" +
            "       null as registration_status,\n" +
            "       null as reason\n" +
            "from outp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpEncFields(Parameter parameter);

    /**
     * 查询住院Encounter
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       inp_visit_no,\n" +
            "       patient_id,\n" +
            "       0    as delete_flag,\n" +
            "       0    as test_record_flag,\n" +
            "       '住院' as enc_type,\n" +
            "       admission_datetime,\n" +
            "       status_name,\n" +
            "       cast(height as decimal(5,2)) as height,\n" +
            "       cast(weight as decimal(5,2)) as weight,\n" +
            "       null as systolic_bp,\n" +
            "       null as diastolic_bp,\n" +
            "       null as pulse,\n" +
            "       null as registration_status,\n" +
            "       null as reason\n" +
            "from inp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpEncFields(Parameter parameter);

    /**
     * 查询病案Encounter
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       inp_visit_no,\n" +
            "       patient_id,\n" +
            "       0    as delete_flag,\n" +
            "       0    as test_record_flag,\n" +
            "       '住院' as enc_type,\n" +
            "       admission_datetime,\n" +
            "       discharge_datetime,\n" +
            "       null as height,\n" +
            "       null as weight,\n" +
            "       null as systolic_bp,\n" +
            "       null as diastolic_bp,\n" +
            "       null as pulse,\n" +
            "       null as registration_status,\n" +
            "       null as reason\n" +
            "from case_residence_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getCaseEncFields(Parameter parameter);

    /**
     * 查询门诊Encounter provider
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       outp_visit_no,\n" +
            "       cure_doctor_code,\n" +
            "       register_doctor_code\n" +
            "from outp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpEncProvFields(Parameter parameter);

    /**
     * 查询住院Encounter provider
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       inp_visit_no,\n" +
            "       outp_doctor_code,\n" +
            "       admission_doctor_code,\n" +
            "       admission_nurse_code,\n" +
            "       now_doctor_code,\n" +
            "       upper_doctor_code,\n" +
            "       now_nurse_code\n" +
            "from inp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpEncProvFields(Parameter parameter);

    /**
     * 查询病案Encounter provider
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       inp_visit_no,\n" +
            "       admission_dept_code,\n" +
            "       admission_ward_code,\n" +
            "        concat_ws('#',admission_dept_code,admission_ward_code) as admission_bingqu_code,\n" +
            "       room_no,\n" +
            "       concat_ws('#',admission_dept_code,admission_ward_code,room_no) as fangjian_code,\n" +
            "       bed_no,\n" +
            "       concat_ws('#',admission_dept_code,admission_ward_code,room_no,bed_no)as chuangwei_code,\n" +
            "       transfer_dept_code,\n" +
            "       discharge_dept_code,\n" +
            "       discharge_ward_code,\n" +
            "       concat_ws('#',discharge_dept_code,discharge_ward_code) as discharge_bingqu_code\n" +
            "from case_residence_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getCaseEncProvFields(Parameter parameter);

    /**
     * 查询门诊Encounter location
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       outp_visit_no,\n" +
            "       cure_dept_code\n" +
            "from outp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpEncLocFields(Parameter parameter);

    /**
     * 查询住院Encounter location
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       inp_visit_no,\n" +
            "       in_dept_code,\n" +
            "       admission_dept_code,\n" +
            "       nurse_dept_code,\n" +
            "       ward_code,\n" +
            "        concat_ws('#',in_dept_code,ward_code) as bingqu_code,\n" +
            "       room_no,\n" +
            "       concat_ws('#',in_dept_code,ward_code,room_no) as fangjian_code,\n" +
            "       bed_no,\n" +
            "       concat_ws('#',in_dept_code,ward_code,room_no,bed_no) as chuangwei_code\n" +
            "from inp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpEncLocFields(Parameter parameter);

    /**
     * 查询病案Encounter location
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       inp_visit_no,\n" +
            "       admission_dept_code,\n" +
            "       admission_ward_code,\n" +
            "       room_no,\n" +
            "       bed_no,\n" +
            "       transfer_dept_code,\n" +
            "       discharge_dept_code,\n" +
            "       discharge_ward_code\n" +
            "from case_residence_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getCaseEncLocFields(Parameter parameter);


}
