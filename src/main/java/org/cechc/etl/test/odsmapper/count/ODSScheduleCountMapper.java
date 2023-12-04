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
public interface ODSScheduleCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifCreateTimeStatement = "<if test='createStartTime!=null and createEndTime != null '>";
    String endIfStatement = "</if>";

    /**
     * outp_register_record条数
     */
    @Select(beginScriptStatement +
            "SELECT orr.cecmid_id as record_id FROM\n" +
            "    outp_register_record orr left join outp_visit_record ovr\n" +
            "on orr.org_code = ovr.org_code\n" +
            "and orr.register_no = ovr.register_no\n" +
            "and orr.patient_id = ovr.patient_id\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and orr.create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getScheduleCount(Parameter parameter);

    /**
     * outp_register_record provider条数
     */
    @Select(beginScriptStatement +
            "SELECT orr.cecmid_id as record_id,\n" +
            "       count(case when orr.register_doctor_code != '' then 1 else null end) as total\n" +
            "FROM\n" +
            "    outp_register_record orr left join outp_visit_record ovr\n" +
            "on orr.org_code = ovr.org_code\n" +
            "and orr.register_no = ovr.register_no\n" +
            "and orr.patient_id = ovr.patient_id\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and orr.create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            "group by record_id" +
            endScriptStatement)
    List<HashMap<String,Object>> getScheduleProCount(Parameter parameter);


    /**
     * outp_register_record location条数
     */
    @Select(beginScriptStatement +
            "SELECT orr.cecmid_id as record_id,\n" +
            "       count(case when ovr.cure_dept_code != '' then 1 else null end) as total\n" +
            "FROM\n" +
            "    outp_register_record orr left join outp_visit_record ovr\n" +
            "on orr.org_code = ovr.org_code\n" +
            "and orr.register_no = ovr.register_no\n" +
            "and orr.patient_id = ovr.patient_id\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and orr.create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            "group by record_id" +
            endScriptStatement)
    List<HashMap<String,Object>> getScheduleLocCount(Parameter parameter);
}
