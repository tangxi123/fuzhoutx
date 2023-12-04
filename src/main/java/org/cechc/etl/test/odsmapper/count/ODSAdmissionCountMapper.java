package org.cechc.etl.test.odsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.domain.DateTimeRange;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface ODSAdmissionCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifCreateTimeStatement = "<if test='createStartTime!=null and createEndTime != null '>";
    String endIfStatement = "</if>";


    @Select("select count(DISTINCT ovr.inp_visit_no) as total\n" +
            "from inp_visit_record ovr\n" +
            "where ovr.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (\n" +
            "          select pbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         left join case_base_info cbi\n" +
            "                             on pbi.patient_id = cbi.patient_id\n" +
            "                                and pbi.org_code = cbi.org_code\n" +
            "                where pbi.org_code = '11'\n" +
            "                union\n" +
            "                select cbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         right join case_base_info cbi\n" +
            "                             on pbi.patient_id = cbi.patient_id\n" +
            "                                and pbi.org_code = cbi.org_code\n" +
            "                where cbi.org_code = '11'\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ovr.org_code = '11'\n" +
            "and ovr.create_datetime between '2020-06-09 16:35:29' and '2020-06-09 16:35:50';")
    Long getInVisitCodesByTimeRangeCount(Parameter parameter);

    @Select("select DISTINCT ovr.inp_visit_no\n" +
            "from inp_visit_record ovr\n" +
            "where ovr.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (\n" +
            "          select pbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         left join case_base_info cbi\n" +
            "                             on pbi.patient_id = cbi.patient_id\n" +
            "                                and pbi.org_code = cbi.org_code\n" +
            "                where pbi.org_code = '11'\n" +
            "                union\n" +
            "                select cbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         right join case_base_info cbi\n" +
            "                             on pbi.patient_id = cbi.patient_id\n" +
            "                                and pbi.org_code = cbi.org_code\n" +
            "                where cbi.org_code = '11'\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ovr.org_code = '11'\n" +
            "and ovr.create_datetime between '2020-06-09 16:35:29' and '2020-06-09 16:35:50'\n" +
            "order by ovr.inp_visit_no limit #{limit} offset #{offset} ;")
    List<String> getInVisitCodesByTimeRangeLimitOffset(Parameter parameter);



    @Select("select DISTINCT ovr.inp_visit_no\n" +
            "from inp_visit_record ovr\n" +
            "where ovr.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (\n" +
            "          select pbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         left join case_base_info cbi\n" +
            "                             on pbi.patient_id = cbi.patient_id\n" +
            "                                and pbi.org_code = cbi.org_code\n" +
            "                where pbi.org_code = '11'\n" +
            "                union\n" +
            "                select cbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         right join case_base_info cbi\n" +
            "                             on pbi.patient_id = cbi.patient_id\n" +
            "                                and pbi.org_code = cbi.org_code\n" +
            "                where cbi.org_code = '11'\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ovr.org_code = '11'\n" +
            "and ovr.create_datetime between '2020-06-09 16:35:29' and '2020-06-09 16:35:50';")
    List<String> getInVisitCodesByTimeRange(Parameter parameter);

    /**
     * 根据inp_visit_record查询admission的条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select Distinct inp_visit_no\n" +
            "from inp_visit_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getInpVisitCount(Parameter parameter);

    /**
     * 根据case_residence_record查询admission的条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select Distinct inp_visit_no\n" +
            "from case_residence_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getCaseResidenceCount(Parameter parameter);

    /**
     * 根据inp_visit_record查询admission与provider相关的总条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select inp_visit_no as record_id," +
            "count(case when admission_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when now_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when upper_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when now_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    List<HashMap<String,Object>>  getInpVisitProCount(Parameter parameter);

    /**
     * 根据case_residence_record查询admission与provider相关的总条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select inp_visit_no as record_id," +
            "count(case when dept_leader_code != '' then 1 else null end) +\n" +
            "       count(case when chief_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when charge_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when residence_doctor_codecode != '' then 1 else null end) +\n" +
            "       count(case when duty_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when study_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when practice_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when coder_code != '' then 1 else null end) +\n" +
            "       count(case when quality_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when quality_nurse_code != '' then 1 else null end) as total\n" +
            "from case_residence_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    List<HashMap<String,Object>>  getCaseResidenceProCount(Parameter parameter);

    /**
     * 根据inp_visit_record查询admission与location相关的总条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select inp_visit_no as record_id," +
            "count(case when admission_dept_code != '' then 1 else null end) +\n" +
            "       count(case when in_dept_code != '' then 1 else null end) +\n" +
            "       count(case when nurse_dept_code != '' then 1 else null end) +\n" +
            "       count(case when ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) as total\n" +
            "from inp_visit_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    List<HashMap<String,Object>>  getInpVisitLocCount(Parameter parameter);

    /**
     * 根据case_residence_record查询admission与location相关的总条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select inp_visit_no as record_id," +
            "count(case when admission_dept_code != '' then 1 else null end) +\n" +
            "       count(case when admission_ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) +\n" +
            "       count(case when transfer_dept_code != '' then 1 else null end) +\n" +
            "       count(case when discharge_dept_code != '' then 1 else null end) +\n" +
            "       count(case when discharge_ward_code != '' then 1 else null end)\n" +
            "           as total\n" +
            "from case_residence_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    List<HashMap<String,Object>>  getCaseResidenceLocCount(Parameter parameter);

}
