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
public interface ODSOrderCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifCreateTimeStatement = "<if test='createStartTime!=null and createEndTime != null '>";
    String endIfStatement = "</if>";
    /**
     * outp_order_detail条数
     */
    @Select(beginScriptStatement +
            "select Distinct concat_ws('#', oom.order_no,  ood.order_serial_no) as record_id\n" +
            "from outp_order_master oom\n" +
            "         left join outp_order_detail ood on\n" +
            "    oom.org_code = ood.org_code and oom.order_no = ood.order_no\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and oom.create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getOutpOrderCount(Parameter parameter);

    /**
     * outp_order_detail provider条数
     */
    @Select(beginScriptStatement +
            "select concat_ws('#', oom.order_no,  ood.order_serial_no) as record_id," +
            "count(case when check_operator_code != '' then 1 else null end) +\n" +
            "       count(case when execute_operator_code != '' then 1 else null end) +\n" +
            "       count(case when order_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_order_master oom\n" +
            "         left join outp_order_detail ood on\n" +
            "    oom.org_code = ood.org_code and oom.order_no = ood.order_no\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and oom.create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getOutpOrderProCount(Parameter parameter);

    /**
     * outp_order_detail location条数
     */
    @Select(beginScriptStatement +
            "select concat_ws('#', oom.order_no,  ood.order_serial_no) as record_id," +
            "count(case when order_dept_code != '' then 1 else null end) +\n" +
            "       count(case when execute_dept_code != '' then 1 else null end) as total\n" +
            "from outp_order_master oom\n" +
            "         left join outp_order_detail ood on\n" +
            "    oom.org_code = ood.org_code and oom.order_no = ood.order_no\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and oom.create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getOutpOrderLocCount(Parameter parameter);

    /**
     * inp_order_record条数
     */
    @Select(beginScriptStatement +
            "select Distinct concat_ws( '#', order_no, order_serial_no) as record_id\n" +
            "from inp_order_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<String> getInpOrderCount(Parameter parameter);

    /**
     * inp_order_record provider条数
     */
    @Select(beginScriptStatement +
            "select Distinct concat_ws( '#', order_no, order_serial_no) as record_id," +
            "count(case when input_operator_code != '' then 1 else null end) +\n" +
            "       count(case when order_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when stop_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when check_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_order_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getInpOrderProCount(Parameter parameter);

    /**
     * inp_order_record location条数
     */
    @Select(beginScriptStatement +
            "select Distinct concat_ws( '#', order_no, order_serial_no) as record_id," +
            "count(case when order_dept_code != '' then 1 else null end) +\n" +
            "       count(case when execute_dept_code != '' then 1 else null end) as total\n" +
            "from inp_order_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id"+
            endScriptStatement)
    List<HashMap<String,Object>> getInpOrderLocCount(Parameter parameter);

}
