package org.cechc.etl.test.odsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface ODSLocationFieldMapper {
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

    /*门诊科室location*/
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       cure_dept_code as record_id,\n" +
            "       0 as delete_flag,\n" +
            "       0 as test_record_flag,\n" +
            "       cure_dept_name as name,\n" +
            "       null as description,\n" +
            "       null as phone,\n" +
            "       null as addr_line_1,\n" +
            "       null as addr_line_2,\n" +
            "       null as city,\n" +
            "       null as state,\n" +
            "       null as zip,\n" +
            "       null as country\n" +
            "from outp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getOutpKeshiLoc(Parameter parameter);

    /*住院科室location*/
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       in_dept_code as record_id,\n" +
            "       0 as delete_flag,\n" +
            "       0 as test_record_flag,\n" +
            "       in_dept_name as name,\n" +
            "       null as description,\n" +
            "       null as phone,\n" +
            "       null as addr_line_1,\n" +
            "       null as addr_line_2,\n" +
            "       null as city,\n" +
            "       null as state,\n" +
            "       null as zip,\n" +
            "       null as country\n" +
            "from inp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpKeshiLoc(Parameter parameter);

    /*住院病区location*/
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       in_dept_code,\n" +
            "       ward_code,\n" +
            "       concat_ws('#',in_dept_code,ward_code) as record_id,\n" +
            "       0 as delete_flag,\n" +
            "       0 as test_record_flag,\n" +
            "       ward_name as name,\n" +
            "       null as description,\n" +
            "       null as phone,\n" +
            "       null as addr_line_1,\n" +
            "       null as addr_line_2,\n" +
            "       null as city,\n" +
            "       null as state,\n" +
            "       null as zip,\n" +
            "       null as country\n" +
            "from inp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpBingquLoc(Parameter parameter);

    /**
     * 住院病房location
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       in_dept_code,\n" +
            "       ward_code,\n" +
            "       room_no,\n" +
            "       concat_ws('#',in_dept_code,ward_code,room_no) as record_id,\n" +
            "       0 as delete_flag,\n" +
            "       0 as test_record_flag,\n" +
            "       in_dept_code,\n" +
            "       ward_code,\n" +
            "       room_no,\n" +
            "       null as description,\n" +
            "       null as phone,\n" +
            "       null as addr_line_1,\n" +
            "       null as addr_line_2,\n" +
            "       null as city,\n" +
            "       null as state,\n" +
            "       null as zip,\n" +
            "       null as country\n" +
            "from inp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpBingfangLoc(Parameter parameter);

    /**
     * 住院床位
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       in_dept_code,\n" +
            "       ward_code,\n" +
            "       room_no,\n" +
            "       bed_no,\n" +
            "       concat_ws('#',in_dept_code,ward_code,room_no,bed_no) as record_id,\n" +
            "       0 as delete_flag,\n" +
            "       0 as test_record_flag,\n" +
            "       in_dept_code,\n" +
            "       ward_code,\n" +
            "       room_no,\n" +
            "       bed_no,\n" +
            "       null as description,\n" +
            "       null as phone,\n" +
            "       null as addr_line_1,\n" +
            "       null as addr_line_2,\n" +
            "       null as city,\n" +
            "       null as state,\n" +
            "       null as zip,\n" +
            "       null as country\n" +
            "from inp_visit_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getInpBedLoc(Parameter parameter);
}
