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
public interface ODSResultCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifCreateTimeStatement = "<if test='createStartTime!=null and createEndTime != null '>";
    String endIfStatement = "</if>";

    /**
     * lis_report_detail
     */
    @Select(beginScriptStatement +
            "SELECT distinct ld.cecmid_id as record_id\n" +
            "FROM lis_report_detail ld\n" +
            "         left join lis_report_master lm\n" +
            "                   on ld.org_code = lm.org_code\n" +
            "                       and ld.report_no = lm.report_no\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and lm.create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getResultCount(Parameter parameter);

    /**
     * lis_report_detail provider
     */
    @Select(beginScriptStatement +
            "SELECT ld.cecmid_id as record_id,\n" +
            "       count(case when apply_operator_code != '' then 1 else null end) +\n" +
            "       count(case when collect_operator_code != '' then 1 else null end) +\n" +
            "       count(case when check_operator_code != '' then 1 else null end) +\n" +
            "       count(case when report_operator_code != '' then 1 else null end) +\n" +
            "       count(case when recover_operator_code != '' then 1 else null end) as total\n" +
            "FROM lis_report_detail ld\n" +
            "         left join lis_report_master lm\n" +
            "                   on ld.org_code = lm.org_code\n" +
            "                       and ld.report_no = lm.report_no\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and lm.create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            "group by record_id" +
            endScriptStatement)
    List<HashMap<String, Object>> getResultProCount(Parameter parameter);

    /**
     * lis_report_detail location
     */
    @Select(beginScriptStatement +
            "SELECT ld.cecmid_id as record_id,\n" +
            "       count(case when apply_dept_code != '' then 1 else null end) +\n" +
            "       count(case when apply_ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) as total\n" +
            "FROM lis_report_detail ld\n" +
            "         left join lis_report_master lm\n" +
            "                   on ld.org_code = lm.org_code\n" +
            "                       and ld.report_no = lm.report_no\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and lm.create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            "group by record_id" +
            endScriptStatement)
    List<HashMap<String, Object>> getLisReportLocCount(Parameter parameter);
}
