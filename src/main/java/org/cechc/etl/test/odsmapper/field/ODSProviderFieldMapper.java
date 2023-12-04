package org.cechc.etl.test.odsmapper.field;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Mapper
public interface ODSProviderFieldMapper {
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
     * 查询provider相关基本数据
     */
    @Select(beginScriptStatement +
            "SELECT org_code,\n" +
            "       staff_no,\n" +
            "       0    as delete_flag,\n" +
            "       0    as test_record_flag,\n" +
            "       doctor_certificate_no,\n" +
            "       null as name_prefix,\n" +
            "       null as first_name,\n" +
            "       null as middle_name,\n" +
            "       null as last_name,\n" +
            "       null as name_suffix,\n" +
            "       name,\n" +
            "       specially_code,\n" +
            "       specially_name,\n" +
            "       sex_name,\n" +
            "       sex_code,\n" +
            "       birth_date,\n" +
            "       null as fax,\n" +
            "       phone_no,\n" +
            "       null as email_address,\n" +
            "       employe_no,\n" +
            "       null as language,\n" +
            "       null as race,\n" +
            "       null as religion,\n" +
            "       work_kind_name,\n" +
            "       work_kind_code\n" +
            "FROM dict_staff_dict\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement
    )
    ArrayList<HashMap<String,Object>> getProviderInfo(Parameter parameter);

    /**
     * 查询provider_identifier相关数据
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       staff_no,\n" +
            "       id_card_no,\n" +
            "       employe_no,\n" +
            "       doctor_certificate_no,\n" +
            "       -999 as empi\n" +
            "from dict_staff_dict\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getProviderIdentiInfo(Parameter parameter);

    /**
     * 查询provider_specialty相关数据
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       staff_no,\n" +
            "       specially_name,\n" +
            "       specially_name,\n" +
            "       'Primary' as spec_type,\n" +
            "       ''        as spec_status,\n" +
            "       null      as effective_datetime\n" +
            "from dict_staff_dict\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getProviderSpecInfo(Parameter parameter);


    /**
     * 查询provider_address相关数据
     * @param count
     * @return
     */
    @Select(beginScriptStatement +
            "select org_code,\n" +
            "       staff_no,\n" +
            "       family_address,\n" +
            "       null as addr_line_2,\n" +
            "       null as city,\n" +
            "       null as state,\n" +
            "       null as zip,\n" +
            "       '中国' as country\n" +
            "from dict_staff_dict\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String,Object>> getProviderAddrInfo(Parameter parameter);
}
