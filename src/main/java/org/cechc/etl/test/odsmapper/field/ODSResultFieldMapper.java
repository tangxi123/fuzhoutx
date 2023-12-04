package org.cechc.etl.test.odsmapper.field;

//import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface ODSResultFieldMapper {
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
     * 查询result数据
     */
    @Select(beginScriptStatement +
            "select lis_report_detail.org_code       as org_code,\n" +
            "       lis_report_master.his_patient_id as patient_id,\n" +
            "       lis_report_detail.cecmid_id      as record_id,\n" +
            "       0                                as delete_flag,\n" +
            "       0                                as test_record_flag,\n" +
            "       null                             as loinc_code,\n" +
            "       lis_report_detail.report_datetime,\n" +
            "       quality_result,\n" +
            "       result_status_name,\n" +
            "       result_status_code,\n" +
            "       null                             as value_status,\n" +
            "       quantity_resul_unit              as value_uom,\n" +
            "       lis_report_detail.result_flag    as record_status,\n" +
            "       upper_limit                      as reference_high,\n" +
            "       lower_limit                      as reference_low,\n" +
            "       reference_range,\n" +
            "       order_no                         as order_id\n" +
            "from lis_report_detail\n" +
            "         left join lis_report_master\n" +
            "                   on lis_report_master.org_code = lis_report_detail.org_code and\n" +
            "                      lis_report_master.report_no = lis_report_detail.report_no\n" +
            "where 1=1\n" +
            "and lis_report_detail.org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getResultField(Parameter parameter);



    /**
     * result provider
     */
    @Select(beginScriptStatement +
            "select lis_report_detail.org_code as org_code,\n" +
            "       lis_report_detail.cecmid_id as record_id,\n" +
            "       apply_operator_code,\n" +
            "       collect_operator_code,\n" +
            "       check_operator_code,\n" +
            "       report_operator_code,\n" +
            "       recover_operator_code\n" +
            "from lis_report_detail\n" +
            "left join lis_report_master\n" +
            "on lis_report_master.org_code=lis_report_detail.org_code and lis_report_master.report_no=lis_report_detail.report_no\n" +
            "where 1=1\n" +
            "and lis_report_detail.org_code = #{orgCode}"+
            selectConditions +
            endScriptStatement
    )
    ArrayList<HashMap<String,Object>> getResultProvider(Parameter parameter);

    /**
     * result location
     */
    @Select(beginScriptStatement +
            "select lis_report_detail.org_code  as org_code,\n" +
            "       lis_report_detail.cecmid_id as record_id,\n" +
            "       apply_dept_code,\n" +
            "       apply_ward_code,\n" +
            "       concat_ws('#',apply_dept_code,apply_ward_code) as bingqu_code,\n" +
            "       room_no,\n" +
            "       concat_ws('#',apply_dept_code,apply_ward_code,room_no) as fangjian_code,\n" +
            "       bed_no,\n" +
            "       concat_ws('#',apply_dept_code,apply_ward_code,room_no,bed_no) as chuangwei_code\n" +
            "from lis_report_detail\n" +
            "         left join lis_report_master\n" +
            "                   on lis_report_master.org_code = lis_report_detail.org_code and\n" +
            "                      lis_report_master.report_no = lis_report_detail.report_no\n" +
            "where 1=1\n" +
            "and lis_report_detail.org_code = #{orgCode}"+
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String ,Object>> getResultLocation(Parameter parameter);


}
