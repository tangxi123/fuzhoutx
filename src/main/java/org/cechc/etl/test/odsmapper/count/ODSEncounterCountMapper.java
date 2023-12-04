package org.cechc.etl.test.odsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.domain.DateTimeRange;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface ODSEncounterCountMapper {

    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifCreateTimeStatement = "<if test='createStartTime!=null and createEndTime != null '>";
    String endIfStatement = "</if>";

    /**
     * 查询OutpVisit条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select DISTINCT outp_visit_no\n" +
            "from outp_visit_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getOutpVisitCount(Parameter parameter);

    /**
     * 查询inpVisit条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select DISTINCT inp_visit_no\n" +
            "from inp_visit_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getInpVisitCount(Parameter parameter);


    /**
     * 查询caseVisit条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select DISTINCT inp_visit_no as total\n" +
            "from case_residence_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getCaseResidenceCount(Parameter parameter);

    /**
     * 查询OutpVisit的provider条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select outp_visit_no as record_id," +
            "count(case when cure_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when register_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id" +
            endScriptStatement)
    List<HashMap<String,Object>> getOutpVisitProCount(Parameter parameter);

    /**
     * 查询inpVisit的provider条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select inp_visit_no as record_id," +
            "count(case when outp_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when now_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when upper_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when now_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getInpVisitProCount(Parameter parameter);

    /**
     * 查询caseVisit的provider条数
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
            "       count(case when quality_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when quality_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when coder_code != '' then 1 else null end) as total\n" +
            "from case_residence_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getCaseResidenceProCount(Parameter parameter);


    /**
     * 查询OutpVisit的location条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select outp_visit_no as record_id, " +
            "count(case when cure_dept_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getOutpVisitLocCount(Parameter parameter);

    /**
     * 查询inpVisit的location条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select inp_visit_no as record_id," +
            "count(case when in_dept_code != '' then 1 else null end) +\n" +
            "       count(case when admission_dept_code != '' then 1 else null end) +\n" +
            "       count(case when nurse_dept_code != '' then 1 else null end) +\n" +
            "       count(case when ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) as total\n" +
            "from inp_visit_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getinpVisitLocCount(Parameter parameter);

    /**
     * 查询caseVisit的location条数
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
            "       count(case when discharge_ward_code != '' then 1 else null end) as total\n" +
            "from case_residence_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getCaseResidenceLocCount(Parameter parameter);


}
