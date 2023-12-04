package org.cechc.etl.test.odsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface ODSMedicationFieldMapper {
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
     * 查询门诊medication
     */
    @Select(beginScriptStatement +
            "select om.org_code         as org_code,\n" +
            "       op.order_no         as order_no,\n" +
            "       order_serial_no,\n" +
            "       concat_ws('#',op.order_no,order_serial_no) as record_id,\n" +
            "       patient_id,\n" +
            "       outp_visit_no,\n" +
            "       order_item_code,\n" +
            "       order_item_name,\n" +
            "       null                as ndc_code,\n" +
            "       null                as snomed_code,\n" +
            "       null                as multum_code,\n" +
            "       null                as rxnorm_cui,\n" +
            "       null                as medispan_code,\n" +
            "       null                as admin_datetime,\n" +
            "       end_datetime,\n" +
            "       om.apply_datetime   as apply_datetime,\n" +
            "       op.execute_datetime as execute_datetime,\n" +
            "       '门诊'                as ordering_mode,\n" +
            "       null                as sigs,\n" +
            "       dosage,\n" +
            "       dosage_unit,\n" +
            "       quantity,\n" +
            "       drug_method_code,\n" +
            "       drug_method_name,\n" +
            "       op.apply_datetime   as apply_datetime,\n" +
            "       order_remark,\n" +
            "       order_status_code,\n" +
            "       check_part_name\n" +
            "from outp_order_master om\n" +
            "         left join outp_order_detail op on om.org_code = op.org_code and om.order_no = op.order_no\n" +
            "where 1=1\n" +
            "and om.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpMedication(Parameter parameter);


    /**
     * 查询住院medication
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       order_no,\n" +
            "       order_serial_no,\n" +
            "       concat_ws('#',order_no,order_serial_no) as record_id,\n" +
            "       patient_id,\n" +
            "       inp_visit_no,\n" +
            "       order_item_code,\n" +
            "       order_item_name,\n" +
            "       null as code,\n" +
            "       null as ndc_code,\n" +
            "       null as description,\n" +
            "       null as snomed_code,\n" +
            "       null as multum_code,\n" +
            "       null as rxnorm_cui,\n" +
            "       null as medispan_code,\n" +
            "       null as admin_datetime,\n" +
            "       null as start_datetime,\n" +
            "       null as end_datetime,\n" +
            "       null as prescribe_datetime,\n" +
            "       '住院' as ordering_mode,\n" +
            "       null as sigs,\n" +
            "       dosage,\n" +
            "       dosage_unit,\n" +
            "       total_drug,\n" +
            "       drug_method_code,\n" +
            "       drug_method_name,\n" +
            "       order_remark\n" +
            "from inp_order_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpMedication(Parameter parameter);


    /**
     * 获取门诊的medication provider
     * @param count
     * @return
     */
    @Select(beginScriptStatement +
            "select om.org_code        as org_code,\n" +
            "       om.order_no        as order_no,\n" +
            "       op.order_serial_no as order_serial_no,\n" +
            "       concat_ws('#',op.order_no,order_serial_no) as record_id,\n" +
            "       check_operator_code,\n" +
            "       execute_operator_code,\n" +
            "       order_doctor_code\n" +
            "from outp_order_master om\n" +
            "         left join outp_order_detail op on om.org_code = op.org_code and om.order_no = op.order_no\n" +
            "where 1=1\n" +
            "and om.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpMedicaProv(Parameter parameter);


    /**
     * 获取住院的medication provider
     * @param count
     * @return
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       order_no,\n" +
            "       order_serial_no,\n" +
            "       concat_ws('#',order_no,order_serial_no) as record_id,\n" +
            "       input_operator_code,\n" +
            "       order_doctor_code,\n" +
            "       stop_doctor_code,\n" +
            "       check_nurse_code\n" +
            "from inp_order_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpMedicaProv(Parameter parameter);

    /**
     * 获取门诊medication location
     * @param count
     * @return
     */
    @Select(beginScriptStatement +
            "select om.org_code        as org_code,\n" +
            "       op.order_serial_no as order_serial_no,\n" +
            "       concat_ws('#',om.order_no,op.order_serial_no) as record_id,\n" +
            "       om.order_no,\n" +
            "       execute_dept_code,\n" +
            "       order_dept_code\n" +
            "from outp_order_master om\n" +
            "         left join outp_order_detail op on om.org_code = op.org_code and om.order_no = op.order_no\n" +
            "where 1=1\n" +
            "and om.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpMedicaLoc(Parameter parameter);

    /**
     * 获取住院medication location
     * @param count
     * @return
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       order_no,\n" +
            "       order_serial_no,\n" +
            "       concat_ws('#',order_no,order_serial_no) as record_id,\n" +
            "       order_dept_code,\n" +
            "       execute_dept_code\n" +
            "from inp_order_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpMedicaLoc(Parameter parameter);



}
