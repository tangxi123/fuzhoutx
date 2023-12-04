package org.cechc.etl.test.odsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface ODSDiagnosisFieldMapper {
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


    /*Case diagnosis*/
    @Select("select cdr.org_code                                     as src_id,\n" +
            "       cdr.patient_id                                   as pat_id,\n" +
            "       0                                                as delete_flag,\n" +
            "       0                                                as test_record_flag,\n" +
            "       cdr.inp_visit_no                                 as enc_id,\n" +
            "       concat_ws('#',cdr.case_no,cdr.inp_visit_no, diagnosis_method_code,diagnosis_type_code,diagnosis_sort_no) as record_id,\n" +
            "       diagnosis_code                                   as code,\n" +
            "       diagnosis_code                                   as icd10_code,\n" +
            "       null                                             as snomed_code,\n" +
            "       null                                             as drg_code,\n" +
            "       diagnosis_datetime                               as start_datetime,\n" +
            "       null                                             as end_datetime,\n" +
            "       diagnosis_name                                   as description,\n" +
            "       '完成'                                            as status,\n" +
            "       '1'                                              as poa_flag,\n" +
            "       main_diagnosis_flag                              as admitting_flag\n" +
            "from case_diagnosis_record cdr\n" +
            "         inner join case_operation_record cor\n" +
            "                   on cdr.org_code = cor.org_code\n" +
            "                       and cdr.case_no = cor.case_no\n" +
            "                       and cdr.inp_visit_no = cor.inp_visit_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on cdr.inp_visit_no = ivr.inp_visit_no\n" +
            "                        and cdr.org_code = ivr.org_code\n" +
            "                        and cdr.patient_id = ivr.patient_id\n" +
            "where cdr.patient_id in (\n" +
            "    select t1.patient_id\n" +
            "    from (select pbi.patient_id as patient_id\n" +
            "          from patient_basic_info pbi\n" +
            "                   left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "          where pbi.org_code = '11'\n" +
            "            and pbi.patient_id != ''\n" +
            "          union\n" +
            "          select cbi.patient_id as patient_id\n" +
            "          from patient_basic_info pbi\n" +
            "                   right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "          where cbi.org_code = '11'\n" +
            "            and cbi.patient_id != ''\n" +
            "         ) t1\n" +
            ")\n" +
            "  and cdr.org_code = '11'\n" +
            "and cdr.storage_datetime between '2020-06-17 16:37:10' and '2020-06-17 16:37:50'\n" +
            "order by cdr.inp_visit_no\n" +
            "limit 5 offset 0;")
    ArrayList<HashMap<String,Object>> getCaseDiagnosis(Parameter parameter);

    /*Inp diagnosis*/
    @Select(beginScriptStatement +
            "select inp_diagnosis_record.org_code     as org_code,\n" +
            "       inp_diagnosis_record.patient_id   as patient_id,\n" +
            "       inp_diagnosis_record.inp_visit_no as record_id,\n" +
            "       0                                 as delete_flag,\n" +
            "       0                                 as test_record_flag,\n" +
            "       inp_diagnosis_record.inp_visit_no as enc_id,\n" +
            "       diagnosis_code,\n" +
            "       null                              as snomed_code,\n" +
            "       null                              as drg_code,\n" +
            "       diagnosis_datetime,\n" +
            "       null                              as end_datetime,\n" +
            "       diagnosis_remark,\n" +
            "       ''                                as status,\n" +
            "       null                              as problem_flag,\n" +
            "       main_diagnosis_flag,\n" +
            "       null                              as chronicity,\n" +
            "       null                              as rfe_flag,\n" +
            "       diagnosis_type_code,\n" +
            "       null                              as admitting_flag\n" +
            "from inp_diagnosis_record\n" +
            "         left join case_operation_record\n" +
            "                   on inp_diagnosis_record.org_code = case_operation_record.org_code and\n" +
            "                      inp_diagnosis_record.patient_id = case_operation_record.patient_id and\n" +
            "                      inp_diagnosis_record.inp_visit_no = case_operation_record.inp_visit_no\n" +
            "where 1=1\n" +
            "and inp_diagnosis_record.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpDiagnosis(Parameter parameter);

    /*OutP diagnosis*/
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       patient_id,\n" +
            "       outp_visit_no,\n" +
            "       diagnosis_sort_no,\n" +
            "       concat_ws('#',outp_visit_no,diagnosis_sort_no) as record_id,\n" +
            "       0             as delete_flag,\n" +
            "       0             as test_record_flag,\n" +
            "       outp_visit_no as enc_id,\n" +
            "       null          as icd9_code,\n" +
            "       diagnosis_code,\n" +
            "       null          as snomed_code,\n" +
            "       null          as drg_code,\n" +
            "       diagnosis_datetime,\n" +
            "       null          as end_datetime,\n" +
            "       diagnosis_remark,\n" +
            "       ''            as status,\n" +
            "       null          as problem_flag,\n" +
            "       main_diagnosis_flag,\n" +
            "       null          as chronicity,\n" +
            "       null          as rfe_flag,\n" +
            "       diagnosis_type_code,\n" +
            "       null          as admitting_flag\n" +
            "from outp_diagnosis_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpDignosis(Parameter parameter);


    /**
     * 查询case Diagnosis provider
     * @return
     */
    @Select(beginScriptStatement +
            "select case_diagnosis_record.org_code   as org_code,\n" +
            "       concat_ws('#',case_diagnosis_record.case_no,diagnosis_serial_no) as record_id,\n" +
            "       case_diagnosis_record.patient_id as patient_id,\n" +
            "       case_diagnosis_record.diagnosis_doctor_code\n" +
            "from case_diagnosis_record\n" +
            "         left join case_operation_record\n" +
            "                   on case_diagnosis_record.org_code = case_operation_record.org_code and\n" +
            "                      case_diagnosis_record.case_no = case_operation_record.case_no\n" +
            "         left join inp_visit_record\n" +
            "                   on case_diagnosis_record.org_code = inp_visit_record.org_code and\n" +
            "                      case_diagnosis_record.inp_visit_no = inp_visit_record.inp_visit_no\n" +
            "where 1=1\n" +
            "and case_diagnosis_record.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getCaseProvDiagnosis(Parameter parameter);

    /**
     * 查询inp Diagnosis provider
     * @return
     */
    @Select(beginScriptStatement +
            "select inp_diagnosis_record.org_code     as org_code,\n" +
            "       inp_diagnosis_record.inp_visit_no as record_id,\n" +
            "       inp_diagnosis_record.patient_id   as patient_id,\n" +
            "       diagnosis_doctor_code\n" +
            "from inp_diagnosis_record\n" +
            "         left join case_operation_record\n" +
            "                   on inp_diagnosis_record.org_code = case_operation_record.org_code and\n" +
            "                      inp_diagnosis_record.patient_id = case_operation_record.patient_id and\n" +
            "                      inp_diagnosis_record.inp_visit_no = case_operation_record.inp_visit_no\n" +
            "where 1=1\n" +
            "and inp_diagnosis_record.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpProvDiagnosis(Parameter parameter);

    /**
     * 查询outp Diagnosis provider
     * @return
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       concat_ws('#',outp_visit_no,diagnosis_sort_no) as record_id,\n" +
            "       patient_id,\n" +
            "       diagnosis_doctor_code\n" +
            "from outp_diagnosis_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpProvDignosis(Parameter parameter);

    /**
     * 查询case Diagnosis location
     * @return
     */
    @Select(beginScriptStatement +
            "select case_diagnosis_record.org_code   as org_code,\n" +
            "       concat_ws('#',case_diagnosis_record.case_no,diagnosis_serial_no) as record_id,\n" +
            "       case_diagnosis_record.patient_id as patient_id,\n" +
            "       case_diagnosis_record.diagnosis_dept_code\n" +
            "from case_diagnosis_record\n" +
            "         left join case_operation_record\n" +
            "                   on case_diagnosis_record.org_code = case_operation_record.org_code and\n" +
            "                      case_diagnosis_record.case_no = case_operation_record.case_no\n" +
            "         left join inp_visit_record\n" +
            "                   on case_diagnosis_record.org_code = inp_visit_record.org_code and\n" +
            "                      case_diagnosis_record.inp_visit_no = inp_visit_record.inp_visit_no\n" +
            "where 1=1\n" +
            "and case_diagnosis_record.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getCaseLocDiagnosis(Parameter parameter);
    /**
     * 查询inp Diagnosis location
     * @param count
     * @return
     */
    @Select(beginScriptStatement +
            "select inp_diagnosis_record.org_code     as org_code,\n" +
            "       inp_diagnosis_record.inp_visit_no as record_id,\n" +
            "       inp_diagnosis_record.patient_id as patient_id,\n" +
            "       diagnosis_dept_code\n" +
            "from inp_diagnosis_record\n" +
            "         left join case_operation_record\n" +
            "                   on inp_diagnosis_record.org_code = case_operation_record.org_code and\n" +
            "                      inp_diagnosis_record.patient_id = case_operation_record.patient_id and\n" +
            "                      inp_diagnosis_record.inp_visit_no = case_operation_record.inp_visit_no\n" +
            "where 1=1\n" +
            "and inp_diagnosis_record.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpLocDiagnosis(Parameter parameter);
    /**
     * 查询outp Diagnosis location
     * @return
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       concat_ws('#',outp_visit_no,diagnosis_sort_no) as record_id,\n" +
            "       patient_id,\n" +
            "       diagnosis_dept_code\n" +
            "from outp_diagnosis_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpLocDignosis(Parameter parameter);




}
