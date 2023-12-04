package org.cechc.etl.test.odsmapper.field;

//import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.poi.hssf.record.DVALRecord;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;
import org.testng.annotations.Optional;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface ODSPatientFieldMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifLimitStatement = "<if test='limit != null'>";
    String ifOffsetStatement = "<if test='offset != null'>";
    String ifOffsetNullStatement = "<if test='offset == null'>";


    String ifCreateTimeStatement = "<if test='createStartTime!=null and createEndTime!=null'>";
    String ifModifyTimeStatement = "<if test='modifyStartTime!=null and modifyEndTime!=null'>";
    String endIfStatement = "</if>";

    String selectConditions = ifCreateTimeStatement +
            "and pbi.create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            ifModifyTimeStatement +
            "and pbi.modify_datetime between #{modifyStartTime} and #{modifyEndTime}" +
            endIfStatement +
            ifLimitStatement +
            ifOffsetStatement +
            "order by pbi.patient_id limit #{limit} offset #{offset};" +
            endIfStatement +
            ifOffsetNullStatement +
            "order by pbi.patient_id limit #{limit};" +
            endIfStatement +

            endIfStatement;



    /**
     * 根据参数获取patient_basic_info的patient_id数据列表
     *
     * @return
     */
    @Select(beginScriptStatement +
            "select pbi.org_code      as src_id,\n" +
            "       pbi.patient_id    as record_id,\n" +
            "       0                 as delete_flag,\n" +
            "       0                 as test_record_flag,\n" +
            "       'N/A'             as pcp_prov_id,\n" +
            "       '病人ID'            as record_id_qualifier,\n" +
            "       id_card_no        as id_1,\n" +
            "       '身份证号码'           as id_1_qualifier,\n" +
            "       medical_safety_no as id_2,\n" +
            "       '医疗保险号'           as id_2_qualifier,\n" +
            "       0                 as pref_record_flag,\n" +
            "       null              as name_prefix,\n" +
            "       null              as first_name,\n" +
            "       null              as middle_name,\n" +
            "       null              as last_name,\n" +
            "       null              as name_suffix,\n" +
            "       patient_name      as full_name,\n" +
            "       birth_date        as birth_datetime,\n" +
            "       null              as ssn,\n" +
            "       sex_name          as gender,\n" +
            "       null              as birth_gender,\n" +
            "       null              as race,\n" +
            "       language_name     as language,\n" +
            "       null              as religion,\n" +
            "       nation_name       as ethnicity,\n" +
            "       null              as military_status,\n" +
            "       null              as activity_status_code,\n" +
            "       null              as activity_status,\n" +
            "       e_mail            as email_address,\n" +
            "       null              as home_phone,\n" +
            "       phone_no          as cell_phone,\n" +
            "       work_unit_phone   as work_phone,\n" +
            "       address           as home_address,\n" +
            "       null              as home_address_addtl,\n" +
            "       address_city      as home_city,\n" +
            "       address_province  as home_state,\n" +
            "       post_code         as home_zip,\n" +
            "       '中国'              as home_country\n" +
            "from patient_basic_info pbi\n" +
            "where org_code = '11'\n" +
            "  and patient_id not in\n" +
            "      (\n" +
            "          select patient_id\n" +
            "          from patient_basic_info\n" +
            "          where org_code = '11'\n" +
            "            and patient_id != ''\n" +
            "            and patient_id is not null\n" +
            "          group by patient_id\n" +
            "          having count(patient_id) > 1\n" +
            "      )\n" +
            "  and patient_id not in\n" +
            "      (\n" +
            "          select DISTINCT pbi.patient_id\n" +
            "          from patient_basic_info pbi\n" +
            "                   inner join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "          where pbi.org_code = '11'\n" +
            "            and pbi.patient_id != ''\n" +
            "      )\n" +
            "  and patient_id != ''\n" +
            "  and patient_id is not null\n" +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            ifModifyTimeStatement +
            "and modify_datetime between #{modifyStartTime} and #{modifyEndTime}" +
            endIfStatement +
            "order by patient_id\n" +
            "limit 20 offset 0;"+
            endScriptStatement
    )
    ArrayList<HashMap<String, Object>> getPatientBaseInfoList(Parameter parameter);


    /**
     * 根据参数获取case_basic_info的patient_id数据列表
     *
     * @return
     */
    @Select("select case_base_info.org_code   as src_id,\n" +
            "       case_base_info.patient_id as record_id,\n" +
            "       0                         as delete_flag,\n" +
            "       0                         as test_record_flag,\n" +
            "       'N/A'                     as pcp_prov_id,\n" +
            "       '病人ID'                    as record_id_qualifier,\n" +
            "       id_card_no                as id_1,\n" +
            "       '身份证号码'                   as id_1_qualifier,\n" +
            "       medical_safety_no         as id_2,\n" +
            "       '医疗保险号'                   as id_2_qualifier,\n" +
            "       0                         as pref_record_flag,\n" +
            "       null                      as name_prefix,\n" +
            "       null                      as first_name,\n" +
            "       null                      as middle_name,\n" +
            "       null                      as last_name,\n" +
            "       null                      as name_suffix,\n" +
            "       patient_name              as full_name,\n" +
            "       birth_date                as birth_datetime,\n" +
            "       null                      as ssn,\n" +
            "       sex_name                  as gender,\n" +
            "       null                      as birth_gender,\n" +
            "       null                      as race,\n" +
            "       language_name             as language,\n" +
            "       null                      as religion,\n" +
            "       nation_name               as ethnicity,\n" +
            "       null                      as military_status,\n" +
            "       null                      as activity_status_code,\n" +
            "       null                      as activity_status,\n" +
            "       e_mail                    as email_address,\n" +
            "       null                      as home_phone,\n" +
            "       phone_no                  as cell_phone,\n" +
            "       work_unit_phone           as work_phone,\n" +
            "       address                   as home_address,\n" +
            "       null                      as home_address_addtl,\n" +
            "       address_city              as home_city,\n" +
            "       address_province          as home_state,\n" +
            "       post_code                 as home_zip,\n" +
            "       '中国'                      as home_country\n" +
            "from case_base_info\n" +
            "where org_code = '11'\n" +
            "  and patient_id not in\n" +
            "      (\n" +
            "          select patient_id\n" +
            "          from case_base_info\n" +
            "          where org_code = '11'\n" +
            "            and patient_id != ''\n" +
            "            and patient_id is not null\n" +
            "          group by patient_id\n" +
            "          having count(patient_id) > 1\n" +
            "      )\n" +
            "  and patient_id not in\n" +
            "      (\n" +
            "          select DISTINCT pbi.patient_id\n" +
            "          from patient_basic_info pbi\n" +
            "                   inner join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "          where pbi.org_code = '11'\n" +
            "            and pbi.patient_id != ''\n" +
            "      )\n" +
            "  and patient_id != ''\n" +
            "  and patient_id is not null\n" +
            "order by patient_id\n" +
            "limit 20 offset 0;")
    ArrayList<HashMap<String, Object>> getPatientCaseBaseInfoList(Parameter parameter);


    @Select("select pbi.org_code          as src_id,\n" +
            "       pbi.patient_id        as record_id,\n" +
            "       0                     as delete_flag,\n" +
            "       0                     as test_record_flag,\n" +
            "       'N/A'                 as pcp_prov_id,\n" +
            "       '病人ID'                as record_id_qualifier,\n" +
            "       pbi.id_card_no        as id_1,\n" +
            "       '身份证号码'               as id_1_qualifier,\n" +
            "       pbi.medical_safety_no as id_2,\n" +
            "       '医疗保险号'               as id_2_qualifier,\n" +
            "       0                     as pref_record_flag,\n" +
            "       null                  as name_prefix,\n" +
            "       null                  as first_name,\n" +
            "       null                  as middle_name,\n" +
            "       null                  as last_name,\n" +
            "       null                  as name_suffix,\n" +
            "       pbi.patient_name      as full_name,\n" +
            "       pbi.birth_date        as birth_datetime,\n" +
            "       null                  as ssn,\n" +
            "       pbi.sex_name          as gender,\n" +
            "       null                  as birth_gender,\n" +
            "       null                  as race,\n" +
            "       pbi.language_name     as language,\n" +
            "       null                  as religion,\n" +
            "       pbi.nation_name       as ethnicity,\n" +
            "       null                  as military_status,\n" +
            "       null                  as activity_status_code,\n" +
            "       null                  as activity_status,\n" +
            "       pbi.e_mail            as email_address,\n" +
            "       null                  as home_phone,\n" +
            "       pbi.phone_no          as cell_phone,\n" +
            "       pbi.work_unit_phone   as work_phone,\n" +
            "       pbi.address           as home_address,\n" +
            "       null                  as home_address_addtl,\n" +
            "       pbi.address_city      as home_city,\n" +
            "       pbi.address_province  as home_state,\n" +
            "       pbi.post_code         as home_zip,\n" +
            "       '中国'                  as home_country\n" +
            "from patient_basic_info pbi\n" +
            "         inner join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "where pbi.org_code = '11'\n" +
            "  and pbi.patient_id != ''\n" +
            "  and pbi.patient_id not in\n" +
            "      (\n" +
            "          select patient_id\n" +
            "          from patient_basic_info\n" +
            "          where org_code = '11'\n" +
            "            and patient_id != ''\n" +
            "            and patient_id is not null\n" +
            "          group by patient_id\n" +
            "          having count(patient_id) > 1\n" +
            "      )\n" +
            "  and pbi.patient_id not in\n" +
            "      (\n" +
            "          select patient_id\n" +
            "          from case_base_info\n" +
            "          where org_code = '11'\n" +
            "            and patient_id != ''\n" +
            "            and patient_id is not null\n" +
            "          group by patient_id\n" +
            "          having count(patient_id) > 1\n" +
            "      );")
    ArrayList<HashMap<String, Object>> getPatientDuplicateInfoList(Parameter parameter);

    /**
     * 获取birth类型的address地址相关数据
     *
     * @param count
     * @return
     */
    @Select(beginScriptStatement +
            "SELECT org_code,\n" +
            "       patient_id,\n" +
            "       birth_address,\n" +
            "       birth_address_county,\n" +
            "       birth_address_town,\n" +
            "       birth_address_village,\n" +
            "       birth_address_door,\n" +
            "       birth_address_city,\n" +
            "       birth_address_province,\n" +
            "       birth_post_code,\n" +
            "       relate_address,\n" +
            "       relate_address_county,\n" +
            "       relate_address_town,\n" +
            "       relate_address_village,\n" +
            "       relate_address_door,\n" +
            "       relate_address_city,\n" +
            "       relate_address_province,\n" +
            "       relate_post_code,\n" +
            "       '中国' as country\n" +
            "FROM patient_basic_info\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String, Object>> getPatientAddrList(Integer count);

    /**
     * 获取relate类型的address地址相关数据
     *
     * @param count
     * @return
     */
//    @Select(
//            "SELECT org_code,\n" +
//            "       patient_id,\n" +
//            "       relate_address,\n" +
//            "       relate_address_county,\n" +
//            "       relate_address_town,\n" +
//            "       relate_address_village,\n" +
//            "       relate_address_door,\n" +
//            "       relate_address_city,\n" +
//            "       relate_address_province,\n" +
//            "       relate_post_code,\n" +
//            "       '中国' as country\n" +
//            "FROM patient_basic_info\n" +
//            "where relate_address is not null\n" +
//            "   or relate_address_county is not null\n" +
//            "   or relate_address_town is not null\n" +
//            "   or relate_address_village is not null\n" +
//            "   or relate_address_door is not null\n" +
//            "   or relate_address_city is not null\n" +
//            "   or relate_address_province is not null\n" +
//            "   or relate_post_code is not null\n" +
//            "limit #{count};")
//    ArrayList<HashMap<String, Object>> getPatientAddrTypeRelateList(Integer count);


    /**
     * 获取patient_basic_info的identifier数据
     *
     *
     * @return
     */
    @Select("select org_code as src_id,\n" +
            "       patient_id as record_id,\n" +
            "       insur_card_no,\n" +
            "       driver_license_no,\n" +
            "       hongkong_macao_pass_no,\n" +
            "       taiwan_pass_no,\n" +
            "       ic_card_no,\n" +
            "       residence_health_card_no,\n" +
            "       electronic_health_card_no,\n" +
            "       household_register_no,\n" +
            "       passport_no,\n" +
            "       officers_certificate_no,\n" +
            "       -999                                                                        as empi,\n" +
            "       null                                                                        as id_effective_datetime,\n" +
            "       count(case when insur_card_no != '' then 1 end) over (partition by patient_id) +\n" +
            "       count(case when driver_license_no != '' then 1 end) over (partition by patient_id) +\n" +
            "       count(case when hongkong_macao_pass_no != '' then 1 end) over (partition by patient_id) +\n" +
            "       count(case when taiwan_pass_no != '' then 1 end) over (partition by patient_id) +\n" +
            "       count(case when ic_card_no != '' then 1 end) over (partition by patient_id) +\n" +
            "       count(case when residence_health_card_no != '' then 1 end) over (partition by patient_id) +\n" +
            "       count(case when electronic_health_card_no != '' then 1 end) over (partition by patient_id) +\n" +
            "       count(case when household_register_no != '' then 1 end) over (partition by patient_id) +\n" +
            "       count(case when passport_no != '' then 1 end) over (partition by patient_id) +\n" +
            "       count(case when officers_certificate_no != '' then 1 end) over (partition by patient_id) +\n" +
            "       count(case when patient_id != '' then 1 end) over (partition by patient_id) as total\n" +
            "from patient_basic_info pbi\n" +
            "where org_code = '11'\n" +
            "  and patient_id not in\n" +
            "      (\n" +
            "          select patient_id\n" +
            "          from patient_basic_info\n" +
            "          where org_code = '11'\n" +
            "            and patient_id != ''\n" +
            "            and patient_id is not null\n" +
            "          group by patient_id\n" +
            "          having count(patient_id) > 1\n" +
            "      )\n" +
            "  and patient_id not in\n" +
            "      (\n" +
            "          select DISTINCT pbi.patient_id\n" +
            "          from patient_basic_info pbi\n" +
            "                   inner join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "          where pbi.org_code = '11'\n" +
            "            and pbi.patient_id != ''\n" +
            "      )\n" +
            "  and patient_id != ''\n" +
            "  and patient_id is not null\n" +
            "order by patient_id\n" +
            "limit 30 offset 0;")
    ArrayList<HashMap<String, Object>> getBaseInfoPatientIdentifierList(Parameter parameter);


    /**
     * 获取case_base_info的identifier数据
     *
     *
     * @return
     */

    @Select(beginScriptStatement +
            "SELECT org_code,\n" +
            "       patient_id,\n" +
            "       insur_card_no,\n" +
            "       id_card_no,\n" +
            "       residence_health_card_no,\n" +
            "       electronic_health_card_no,\n" +
            "       -999    as empi,\n" +
            "       ''      as normal_id_status,\n" +
            "       'VALID' as empi_id_status,\n" +
            "       null    as id_effective_datetime\n" +
            "FROM case_base_info\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String, Object>> getCaseBasePatientIdentifierList(Parameter parameter);


    @Select(beginScriptStatement +
            "select DISTINCT " +
            "                pbi.org_code,\n" +
            "                pbi.patient_id as record_id,\n" +
            "                pbi.birth_address,\n" +
            "                pbi.birth_address_door,\n" +
            "                pbi.birth_address_city,\n" +
            "                pbi.birth_address_province,\n" +
            "                pbi.birth_post_code,\n" +
            "                pbi.birth_address_county,\n" +
            "                pbi.birth_address_town,\n" +
            "                pbi.birth_address_village," +
            "                pbi.relate_address,\n" +
            "                pbi.relate_address_door,\n" +
            "                pbi.relate_address_city,\n" +
            "                pbi.relate_address_province,\n" +
            "                pbi.relate_post_code,\n" +
            "                count(case\n" +
            "                          when\n" +
            "                                  pbi.birth_address != '' or\n" +
            "                                  pbi.birth_address_door != '' or\n" +
            "                                  pbi.birth_address_city != '' or\n" +
            "                                  pbi.birth_address_province != '' or\n" +
            "                                  pbi.birth_post_code != ''\n" +
            "                              then 1\n" +
            "                          end) over (partition by pbi.patient_id) +\n" +
            "                count(case\n" +
            "                          when\n" +
            "                                  pbi.relate_address != '' or\n" +
            "                                  pbi.relate_address_door != '' or\n" +
            "                                  pbi.relate_address_city != '' or\n" +
            "                                  pbi.relate_address_province != '' or\n" +
            "                                  pbi.relate_post_code != ''\n" +
            "                              then 1\n" +
            "                          end\n" +
            "                    ) over (partition by pbi.patient_id) as total\n" +
            "from patient_basic_info pbi\n" +
            "where pbi.org_code = '11'\n" +
            "  and (\n" +
            "        pbi.birth_address != '' or\n" +
            "        pbi.birth_address_door != '' or\n" +
            "        pbi.birth_address_city != '' or\n" +
            "        pbi.birth_address_province != '' or\n" +
            "        pbi.birth_post_code != '' or\n" +
            "        pbi.relate_address != '' or\n" +
            "        pbi.relate_address_door != '' or\n" +
            "        pbi.relate_address_city != '' or\n" +
            "        pbi.relate_address_province != '' or\n" +
            "        pbi.relate_post_code != ''\n" +
            "    )\n" +
            "  and pbi.patient_id not in\n" +
            "      (\n" +
            "          select patient_id\n" +
            "          from patient_basic_info\n" +
            "          where org_code = '11'\n" +
            "            and patient_id != ''\n" +
            "            and patient_id is not null\n" +
            "          group by patient_id\n" +
            "          having count(patient_id) > 1\n" +
            "      )\n" +
            "  and pbi.patient_id != ''\n" +
            selectConditions +
            endScriptStatement

    )
    ArrayList<HashMap<String, Object>> getBaseInfoPatientAddreList(Parameter parameter);

    @Select(beginScriptStatement +
            "SELECT org_code,\n" +
            "       patient_id,\n" +
            "       outp_visit_no,\n" +
            "       0    as delete_flag,\n" +
            "       0    as test_record_flag,\n" +
            "       ''   as code,\n" +
            "       null as description,\n" +
            "       null as response,\n" +
            "       ''   as clinical_datetime,\n" +
            "       null as recorded_datetime,\n" +
            "       null as status\n" +
            "FROM outp_medical_record\n" +
            "where 1=1\n" +
            "and org_code = #{orgCode}" +
            selectConditions +
            endScriptStatement)
    ArrayList<HashMap<String, Object>> getPatientHxList(Parameter parameter);
}

