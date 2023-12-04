package org.cechc.etl.test.odsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.domain.DateTimeRange;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface ODSProviderCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifCreateTimeStatement = "<if test='createStartTime!=null and createEndTime != null '>";
    String ifModifyTimeStatement = "<if test='modifyStartTime!=null and modifyEndTime != null '>";
    String endIfStatement = "</if>";

    /**
     * 获取dict_staff_dict条数
     */
    @Select(beginScriptStatement +
            "select distinct staff_no\n" +
            "from dict_staff_dict\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            ifModifyTimeStatement +
            "  and modify_datetime between #{modifyStartTime} and #{modifyEndTime}" +
            endIfStatement +
            endScriptStatement)
    ArrayList<String> getDictStaffIdList(Parameter parameter);

    /**
     * 获取dict_staff_dict address条数
     */
    @Select(beginScriptStatement +
            "select staff_no as record_id,\n" +
            "count(case when family_address != '' then 1 else null end) as total\n" +
            "from dict_staff_dict\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            ifModifyTimeStatement +
            "  and modify_datetime between #{modifyStartTime} and #{modifyEndTime}\n" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getDictStaffAddrCountList(Parameter parameter);

    /**
     * 获取dict_staff_dict identifier条数
     */
    @Select(beginScriptStatement +
            "select staff_no as record_id,\n" +
            "count(case when id_card_no != '' then 1 else null end) +\n" +
            "       count(case when employe_no != '' then 1 else null end) +\n" +
            "       count(case when doctor_certificate_no != '' then 1 else null end) +\n" +
            "       count(case when staff_no != '' then 1 else null end) as total\n" +
            "from dict_staff_dict\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            ifModifyTimeStatement +
            "  and modify_datetime between #{modifyStartTime} and #{modifyEndTime}" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getDictStaffIdentiCountList(Parameter parameter);

    /**
     * 获取dict_staff_dict specialty条数
     */
    @Select(beginScriptStatement +
            "select staff_no as record_id,\n" +
            "count(case\n" +
            "                 when specially_name != '' or\n" +
            "                      specially_code != ''\n" +
            "                     then 1\n" +
            "                 else null end\n" +
            "           ) as total\n" +
            "from dict_staff_dict\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            ifModifyTimeStatement +
            "  and modify_datetime between #{modifyStartTime} and #{modifyEndTime}" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    Integer getDictStaffSpecCount(DateTimeRange dateTimeRange);


}
