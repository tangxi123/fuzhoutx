package org.cechc.etl.test.odsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface ODSAdmissionFieldMapper {
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
     * 住院admission
     */
    @Select("select ovr.org_code as src_id,\n" +
            "       ovr.patient_id as pat_id,\n" +
            "       ovr.inp_visit_no as record_id,\n" +
            "       ovr.inp_visit_no as enc_id,\n" +
            "       ovr.admission_purpose_name as adm_type,\n" +
            "       ovr.admission_datetime as adm_datetime,\n" +
            "       ovr.admission_mode_name as adm_src,\n" +
            "       ovr.status_name as status,\n" +
            "       ovr.discharge_datetime as disch_datetime,\n" +
            "       null         as disch_disp\n" +
            "from inp_visit_record ovr\n" +
            "where ovr.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (select pbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where pbi.org_code = '11'\n" +
            "                  and pbi.patient_id != ''\n" +
            "                union\n" +
            "                select cbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where cbi.org_code = '11'\n" +
            "                  and cbi.patient_id != ''\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ovr.org_code = '11'\n" +
            "  and ovr.create_datetime between '2020-06-09 16:35:29' and '2020-06-09 16:35:50'\n" +
            "order by ovr.inp_visit_no\n" +
            "limit 5 offset 0;"
    )
    ArrayList<HashMap<String,Object>> getInpAdmissionField(Parameter parameter);

    /**
     * 病案admission
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       patient_id,\n" +
            "       inp_visit_no as record_id,\n" +
            "       0            as delete_flag,\n" +
            "       0            as test_record_flag,\n" +
            "       inp_visit_no as enc_id,\n" +
            "       admission_purpose_name,\n" +
            "       admission_purpose_code,\n" +
            "       admission_datetime,\n" +
            "       admission_mode_name,\n" +
            "       ''           as status,\n" +
            "       discharge_datetime,\n" +
            "       disease_status_name\n" +
            "from case_residence_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement
    )
    ArrayList<HashMap<String,Object>> getCaseAdmissionField(Parameter parameter);

    /**
     * 住院admission provider
     */
    @Select("select org_code,\n" +
            "       inp_visit_no as record_id,\n" +
            "       patient_id,\n" +
            "       outp_doctor_code,\n" +
            "       admission_doctor_code,\n" +
            "       admission_nurse_code,\n" +
            "       now_doctor_code,\n" +
            "       upper_doctor_code,\n" +
            "       now_nurse_code\n" +
            "from inp_visit_record ovr\n" +
            "where ovr.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (select pbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where pbi.org_code = '11'\n" +
            "                  and pbi.patient_id != ''\n" +
            "                union\n" +
            "                select cbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where cbi.org_code = '11'\n" +
            "                  and cbi.patient_id != ''\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ovr.org_code = '11'\n" +
            " and ovr.create_datetime between '2020-06-09 16:35:29' and '2020-06-09 16:35:50'\n" +
            "order by ovr.inp_visit_no\n" +
            "limit 5 offset 0;"
    )
    ArrayList<HashMap<String,Object>> getInpAdmissionProvField(Parameter parameter);

    /**
     * 病案admission provider
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       inp_visit_no,\n" +
            "       dept_leader_code,\n" +
            "       chief_doctor_code,\n" +
            "       charge_doctor_code,\n" +
            "       residence_doctor_codecode,\n" +
            "       duty_nurse_code,\n" +
            "       study_doctor_code,\n" +
            "       practice_doctor_code,\n" +
            "       coder_code,\n" +
            "       quality_doctor_code,\n" +
            "       quality_nurse_code\n" +
            "from case_residence_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getCaseAdmissionProvField(Parameter parameter);

    /**
     * 住院admission location
     */
    @Select("select ovr.org_code                             as src_id,\n" +
            "       ovr.inp_visit_no                         as record_id,\n" +
            "       ovr.patient_id                           as pat_id,\n" +
            "       admission_dept_code,\n" +
            "       in_dept_code,\n" +
            "       nurse_dept_code,\n" +
            "       ward_code,\n" +
            "       room_no,\n" +
            "       bed_no,\n" +
            "       concat_ws('#',in_dept_code,ward_code) as bingqu_code,\n" +
            "       concat_ws('#',in_dept_code,ward_code,room_no) as fangjian_code,\n" +
            "       concat_ws('#',in_dept_code,ward_code,room_no,bed_no) as chuangwei_code,\n" +
            "       count(case when admission_dept_code != '' then 1 end) over(partition by ovr.inp_visit_no) +\n" +
            "       count(case when in_dept_code != '' then 1 end) over(partition by ovr.inp_visit_no) +\n" +
            "       count(case when nurse_dept_code != '' then 1 end) over(partition by ovr.inp_visit_no) +\n" +
            "       count(case when concat_ws('#',in_dept_code,ward_code)!=''  then 1 end) over(partition by ovr.inp_visit_no) +\n" +
            "       count(case when concat_ws('#',in_dept_code,ward_code,room_no)!=''  then 1 end) over(partition by ovr.inp_visit_no) +\n" +
            "       count(case when concat_ws('#',in_dept_code,ward_code,room_no,bed_no) !=''  then 1 end) over(partition by ovr.inp_visit_no) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "where ovr.org_code = '11'\n" +
            "  and ovr.patient_id in\n" +
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
            "  and (\n" +
            "        admission_dept_code != '' or\n" +
            "        in_dept_code != '' or\n" +
            "        nurse_dept_code != '' or\n" +
            "        ward_code != '' or\n" +
            "        room_no != '' or\n" +
            "        bed_no != ''\n" +
            "    )\n" +
            "  and ovr.inp_visit_no not in\n" +
            "      (\n" +
            "          select ivr.inp_visit_no\n" +
            "          from inp_visit_record ivr\n" +
            "          group by ivr.inp_visit_no\n" +
            "          having count(ivr.inp_visit_no) > 1\n" +
            "      )\n" +
            "  and ovr.inp_visit_no != ''\n" +
            "  and ovr.inp_visit_no is not null\n" +
            "order by ovr.inp_visit_no\n" +
            "limit 20 offset 0;")
    ArrayList<HashMap<String,Object>> getInpAdmissionLocField(Parameter parameter);

    /**
     * 病案admission location
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       inp_visit_no,\n" +
            "       patient_id,\n" +
            "       admission_dept_code,\n" +
            "       admission_ward_code,\n" +
            "       concat_ws('#',admission_dept_code,admission_ward_code) as admission_bingqu_code,\n" +
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
    ArrayList<HashMap<String,Object>> getCaseAdmissionLocField(Parameter parameter);
}
