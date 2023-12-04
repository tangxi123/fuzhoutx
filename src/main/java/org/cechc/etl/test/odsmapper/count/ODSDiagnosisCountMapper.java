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
public interface ODSDiagnosisCountMapper {

    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifCreateTimeStatement = "<if test='createStartTime!=null and createEndTime != null '>";
    String endIfStatement = "</if>";

    /**
     * 获取outp_diagnosis_record条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select Distinct concat_ws('#', outp_visit_no,diagnosis_sort_no) as record_id\n" +
            "from outp_diagnosis_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getOutpDiagnosisCount(Parameter parameter);

    /**
     * 获取inp_diagnosis_record条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select Distinct inp_visit_no as record_id\n" +
            "from inp_diagnosis_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getInpDiagnosisCount(Parameter parameter);

    /**
     * 获取case_diagnosis_record条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select DISTINCT concat_ws('#', case_no, diagnosis_serial_no) as record_id\n" +
            "from case_diagnosis_record\n" +
            "where 1=1" +
            ifCreateTimeStatement +
            "create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getCaseDiagnosisCount(Parameter parameter);

    /**
     * 获取outp_diagnosis_record与location关联条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select Distinct concat_ws('#', outp_visit_no,diagnosis_sort_no) as record_id," +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from outp_diagnosis_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getOutpDiagnosisLocCount(Parameter parameter);

    /**
     * 获取inp_diagnosis_record与location关联条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select inp_visit_no as record_id," +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from inp_diagnosis_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getInpDiagnosisLocCount(Parameter parameter);

    /**
     * 获取case_diagnosis_record与location关联条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select concat_ws('#', case_no, diagnosis_serial_no) as record_id," +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from case_diagnosis_record\n" +
            "where 1 = 1\n" +
            ifCreateTimeStatement +
            "  and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getCaseDiagnosisLocCount(Parameter parameter);

    /**
     * 获取outp_diagnosis_record与provider关联条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select Distinct concat_ws('#', outp_visit_no,diagnosis_sort_no) as record_id, " +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_diagnosis_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getOutpDiagnosisProCount(Parameter parameter);

    /**
     * 获取inp_diagnosis_record与provider关联条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select inp_visit_no as record_id," +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from inp_diagnosis_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getInpDiagnosisProCount(Parameter parameter);

    /**
     * 获取case_diagnosis_record与provider关联条数
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select concat_ws('#', case_no, diagnosis_serial_no) as record_id," +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from case_diagnosis_record\n" +
            "where 1 = 1\n" +
            ifCreateTimeStatement +
            "  and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getCaseDiagnosisProCount(Parameter parameter);

}
