package org.cechc.etl.test.odsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface ODSOrderFieldMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifLimitStatement = "<if test='limit != null'>";
    String ifOffsetStatement = "<if test='offset != null'>";
    String ifOffsetNullStatement = "<if test='offset == null'>";
    String endIfStatement = "</if>";

    String selectConditions =
            ifLimitStatement +
            ifOffsetStatement +
            "limit #{offset},#{limit};" +
            endIfStatement +
            ifOffsetNullStatement +
            "limit #{limit};" +
            endIfStatement +
            endIfStatement;
    /**
     * 查询门诊order
     * @param count
     * @return
     */
    @Select(beginScriptStatement +
            "select om.org_code as org_code,\n" +
            "       op.order_no as order_no,\n" +
            "       order_serial_no,\n" +
            "       concat_ws('#',op.order_no,order_serial_no) as record_id,\n" +
            "       patient_id,\n" +
            "       outp_visit_no,\n" +
            "       order_item_code,\n" +
            "       order_item_name,\n" +
            "       null        as cpt_code,\n" +
            "       null        as icd9_code,\n" +
            "       null        as icd10_code,\n" +
            "       null        as snomed_code,\n" +
            "       om.apply_datetime,\n" +
            "       op.execute_datetime,\n" +
            "       order_status_code,\n" +
            "       check_part_name\n" +
            "from outp_order_master om\n" +
            "         left join outp_order_detail op on om.org_code = op.org_code and om.order_no = op.order_no\n" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpOrderInfo(Parameter parameter);


    /**
     * 查询住院order
     * @param count
     * @return
     */
    @Select(beginScriptStatement +
            "select org_code as org_code,\n" +
            "       order_no as order_no,\n" +
            "       order_serial_no,\n" +
            "       concat_ws('#',order_no,order_serial_no) as record_id,\n" +
            "       patient_id,\n" +
            "       inp_visit_no,\n" +
            "       item_class_code,\n" +
            "       item_class_name,\n" +
            "       null     as cpt_code,\n" +
            "       null     as icd9_code,\n" +
            "       null     as icd10_code,\n" +
            "       null     as snomed_code,\n" +
            "       order_datetime,\n" +
            "       begin_execute_datetime,\n" +
            "       order_status_code,\n" +
            "       check_part_name\n" +
            "from inp_order_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpOrderInfo(Parameter parameter);

    /**
     * 查询门诊order provider
     */

    @Select(beginScriptStatement +
            "select om.org_code as org_code,\n" +
            "       om.order_no as order_no,\n" +
            "       order_serial_no,\n" +
            "       concat_ws('#',om.order_no,order_serial_no) as record_id,\n" +
            "       order_doctor_code,\n" +
            "       check_operator_code,\n" +
            "       execute_operator_code\n" +
            "from outp_order_master om\n" +
            "         left join outp_order_detail op on om.org_code = op.org_code and om.order_no = op.order_no\n" +
            "where 1=1\n" +
            "and om.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpOrderProviderInfo(Parameter parameter);

    /**
     * 查询住院order provider
     */

    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       order_no,\n" +
            "       order_serial_no,\n" +
            "       concat_ws('#',order_no,order_serial_no) as record_id,\n" +
            "       check_nurse_code,\n" +
            "       input_operator_code,\n" +
            "       order_doctor_code,\n" +
            "       stop_doctor_code\n" +
            "from inp_order_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getIntpOrderProviderInfo(Parameter parameter);

    /*
    * 查询门诊order location*/
    @Select(beginScriptStatement +
            "select om.org_code as org_code,\n" +
            "       om.order_no as order_no,\n" +
            "       order_serial_no,\n" +
            "       concat_ws('#',om.order_no,order_serial_no) as record_id,\n" +
            "       patient_id,\n" +
            "       order_dept_code,\n" +
            "       execute_dept_code\n" +
            "from outp_order_master om\n" +
            "         left join outp_order_detail op on om.org_code = op.org_code and om.order_no = op.order_no\n" +
            "where 1=1\n" +
            "and om.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpOrderLocInfo(Parameter parameter);

    /**
     * 查询住院order location
     */
    @Select("select ior.org_code                                                                as src_id,\n" +
            "       concat_ws('#', ior.order_no, ior.order_serial_no)                           as ord_id,\n" +
            "       ior.patient_id                                                              as pat_id,\n" +
            "       order_dept_code,\n" +
            "       execute_dept_code,\n" +
            "       count(case when order_dept_code != '' then 1 end)\n" +
            "             over (partition by concat_ws('#', ior.order_no, ior.order_serial_no)) +\n" +
            "       count(case when execute_dept_code != '' then 1 end)\n" +
            "             over (partition by concat_ws('#', ior.order_no, ior.order_serial_no)) as total\n" +
            "from inp_order_record ior\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on ior.org_code = ivr.org_code\n" +
            "                        and ior.patient_id = ivr.patient_id\n" +
            "                        and ior.inp_visit_no = ivr.inp_visit_no\n" +
            "where ior.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (select pbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         left join case_base_info cbi\n" +
            "                             on pbi.patient_id = cbi.patient_id\n" +
            "                                and pbi.org_code = cbi.org_code\n" +
            "                where pbi.org_code = '11'\n" +
            "                  and pbi.patient_id != ''\n" +
            "                union\n" +
            "                select cbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         right join case_base_info cbi\n" +
            "                             on pbi.patient_id = cbi.patient_id\n" +
            "                                and pbi.org_code = cbi.org_code\n" +
            "                where cbi.org_code = '11'\n" +
            "                  and cbi.patient_id != ''\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ior.org_code = '11'\n" +
            "  and ior.order_no != ''#疑问：是否要判断这两个字段不为空？\n" +
            "  and ior.order_serial_no != ''\n" +
            "  and concat_ws('#', ior.order_no, ior.order_serial_no) not in\n" +
            "      (\n" +
            "          select concat_ws('#', ior.order_no, ior.order_serial_no)\n" +
            "          from inp_order_record ior\n" +
            "                   inner join inp_visit_record ivr\n" +
            "                              on ior.org_code = ivr.org_code\n" +
            "                                  and ior.patient_id = ivr.patient_id\n" +
            "                                  and ior.inp_visit_no = ivr.inp_visit_no\n" +
            "          where ior.org_code = '11'\n" +
            "          group by ior.order_no, ior.order_serial_no\n" +
            "          having count(concat_ws('#', ior.order_no, ior.order_serial_no)) > 1\n" +
            "      )\n" +
            "  and (\n" +
            "        order_dept_code != '' or\n" +
            "        execute_dept_code != ''\n" +
            "    )\n" +
            "order by concat_ws('#', order_no, order_serial_no)\n" +
            "limit 20 offset 0;")
    ArrayList<HashMap<String,Object>> getInpOrderLocInfo(Parameter parameter);
}
