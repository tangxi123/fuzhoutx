package org.cechc.etl.test.odsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface ODSScheduleFieldMapper {
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
    /*查询Schedule*/
    @Select(beginScriptStatement +
            "select outp_register_record.org_code           as org_code,\n" +
            "       outp_register_record.patient_id         as patient_id,\n" +
            "       outp_register_record.register_no        as record_id,\n" +
            "       0                                       as delete_flag,\n" +
            "       0                                       as test_record_flag,\n" +
            "       outp_register_record.register_type_code as sched_type,\n" +
            "       outp_register_record.register_type_name as sched_type_desc,\n" +
            "       outp_register_record.register_datetime  as sched_event_datetime,\n" +
            "       null                                    as scheduled_duration,\n" +
            "       null                                    as scheduled_duration_unit,\n" +
            "       null                                    as registration_status,\n" +
            "       null                                    as reason\n" +
            "from outp_register_record\n" +
            "         left join outp_visit_record\n" +
            "                   on outp_register_record.org_code = outp_visit_record.org_code and\n" +
            "                      outp_register_record.register_no = outp_visit_record.register_no\n" +
            "where 1=1\n" +
            "and outp_register_record.org_code = #{orgCode}"+
            selectConditions +
            endScriptStatement
    )
    ArrayList<HashMap<String,Object>> getScheduleField(Parameter parameter);

    /*查询Schedule_provider*/
    @Select(beginScriptStatement +
            "select outp_register_record.org_code             as org_code,\n" +
            "       outp_register_record.register_no          as record_id,\n" +
            "       outp_register_record.register_doctor_code as register_doctor_code\n" +
            "from outp_register_record\n" +
            "         left join outp_visit_record\n" +
            "                   on outp_register_record.org_code = outp_visit_record.org_code and\n" +
            "                      outp_register_record.register_no = outp_visit_record.register_no\n" +
            "where 1=1\n" +
            "and outp_register_record.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getScheduleProvField(Parameter parameter);

    /*查询Schedule_Location*/
    @Select(beginScriptStatement +
            "select outp_register_record.org_code    as org_code,\n" +
            "       outp_register_record.register_no as record_id,\n" +
            "       cure_dept_code\n" +
            "from outp_register_record\n" +
            "         left join outp_visit_record\n" +
            "                   on outp_register_record.org_code = outp_visit_record.org_code and\n" +
            "                      outp_register_record.register_no = outp_visit_record.register_no\n" +
            "where 1=1\n" +
            "and outp_register_record.org_code = #{orgCode}"+
            selectConditions +
            endScriptStatement
    )
    ArrayList<HashMap<String,Object>> getScheduleLocField(Parameter parameter);
}
