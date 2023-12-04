package org.cechc.etl.test.odsmapper.count;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.domain.DateTimeRange;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface ODSPatientCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifCreateTimeStatement = "<if test='createStartTime!=null and createEndTime != null '>";
    String ifModifyTimeStatement = "<if test='modifyStartTime!=null and modifyEndTime != null '>";
    String endIfStatement = "</if>";

    @Select("select * from patient_basic_info")
    ArrayList<String> getCountTest();

    /**
     * 根据创建时间和更新时间分页查询查询patient_basic_info的病人的patient_id条数
     */
    @Select(beginScriptStatement +
            "SELECT patient_id \n" +
            "FROM patient_basic_info\n" +
            "where 1=1" +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            ifModifyTimeStatement +
            "and modify_datetime between #{modifyStartTime} and #{modifyEndTime}" +
            endIfStatement +
            endScriptStatement
            )
    List<String> getBasicPatientIdList(Parameter parameter);


    /**
     * 根据address条件查询patient_basic_info的address条数
     */
    @Select(beginScriptStatement +
            "select patient_id as record_id,\n" +
            "       count(case when\n" +
            "           birth_address != '' or\n" +
            "           birth_address_province != '' or\n" +
            "           birth_address_county != '' or\n" +
            "           birth_address_door != '' or\n" +
            "           birth_address_town != '' or\n" +
            "           birth_address_village != '' or\n" +
            "           birth_address_city != '' or\n" +
            "           birth_post_code != ''\n" +
            "       then 1 else null end)+\n" +
            "       count(case when\n" +
            "           relate_address != '' or\n" +
            "           relate_address_province != '' or\n" +
            "           relate_address_county != '' or\n" +
            "           relate_address_door != '' or\n" +
            "           relate_address_town != '' or\n" +
            "           relate_address_village != '' or\n" +
            "           relate_address_city != '' or\n" +
            "           relate_post_code != ''\n" +
            "       then 1 else null end\n" +
            "           )  as total\n" +
            "from patient_basic_info\n" +
            "where 1=1" +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            ifModifyTimeStatement +
            "and modify_datetime between #{modifyStartTime} and #{modifyEndTime}" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    List<HashMap<String,Object>> getPatientBasicInfoAddressCountList(Parameter parameter);

    /**
     * 获取patient_basic_info与identifier相关的条数是一对多的，所以需要判断各个no是否有值，有值的话identifier里就有一条数据
     */
    @Select(beginScriptStatement +
            "select patient_id as record_id,\n" +
            "if(insur_card_no != '', 1, 0) +\n" +
            "       if(driver_license_no != '', 1, 0) +\n" +
            "       if(hongkong_macao_pass_no != '', 1, 0) +\n" +
            "       if(taiwan_pass_no != '', 1, 0) +\n" +
            "       if(ic_card_no != '', 1, 0) +\n" +
            "       if(residence_health_card_no != '', 1, 0) +\n" +
            "       if(electronic_health_card_no != '', 1, 0) +\n" +
            "       if(household_register_no != '', 1, 0) +\n" +
            "       if(passport_no != '', 1, 0) +\n" +
            "       if(officers_certificate_no != '', 1, 0) +\n" +
            "       if(patient_id != '', 1, 0)" +
            "as total\n" +
            "from patient_basic_info\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            ifModifyTimeStatement +
            "and modify_datetime between #{modifyStartTime} and #{modifyEndTime}" +
            endIfStatement +
            endScriptStatement)
    List<HashMap<String,Object>> getPatientBasicInfoIdentifierList(Parameter parameter);


    /**
     * 查询case_base_info的patient条数
     */
    @Select(beginScriptStatement +
            "SELECT Count(DISTINCT (patient_id)) as total\n" +
            "FROM case_base_info\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            endScriptStatement)
    Integer getCaseBaseInfoPatientCount(String createStartTime, String createEndTime);

    /**
     * 根据创建时间和分页查询case_base_info病人的patient_id list条数
     */
    @Select(beginScriptStatement +
            "SELECT patient_id\n" +
            "FROM case_base_info\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}\n" +
            endIfStatement +
            endScriptStatement)
    List<String> getCasePatientList(Parameter parameter);

    /**
     * 根据address条件查询case_base_info的address条数
     */
    @Select(beginScriptStatement +
            "select patient_id as record_id,\n" +
            "       count(case when\n" +
            "           birth_address != '' or\n" +
            "           birth_address_province != '' or\n" +
            "           birth_address_county != '' or\n" +
            "           birth_address_door != '' or\n" +
            "           birth_address_town != '' or\n" +
            "           birth_address_village != '' or\n" +
            "           birth_address_city != '' or\n" +
            "           birth_post_code != ''\n" +
            "       then 1 else null end)+\n" +
            "       count(case when\n" +
            "           account_address != '' or\n" +
            "           account_address_province != '' or\n" +
            "           account_address_county != '' or\n" +
            "           account_address_door != '' or\n" +
            "           account_address_town != '' or\n" +
            "           account_address_village != '' or\n" +
            "           account_address_city != '' or\n" +
            "           account_post_code != ''\n" +
            "       then 1 else null end\n" +
            "           )  as total\n" +
            "from case_base_info\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    List<HashMap<String,Object>> getCaseBaseInfoPatientAddressCountList(Parameter parameter);

    /**
     * 根据创建时间分页获取case_base_info与identifier相关的条数
     */
    @Select(beginScriptStatement +
            "select patient_id as record_id,\n" +
            "       count(case when id_card_no != '' then 1 else null end) +\n" +
            "       count(case when insur_card_no != '' then 1 else null end) +\n" +
            "       count(case when electronic_health_card_no != '' then 1 else null end) +\n" +
            "       count(case when residence_health_card_no != '' then 1 else null end) +\n" +
            "       count(case when patient_id != '' then 1 else null end)\n" +
            "           as total\n" +
            "from case_base_info\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    List<HashMap<String,Object>> getCaseBasePatientIdentifierList(Parameter parameter);


    /**
     * 查询patient hx count条数
     */
    @Select(beginScriptStatement +
            "select patient_id as record_id,\n" +
            "count(DISTINCT concat(patient_id, outp_visit_no)) as total\n" +
            "from outp_medical_record\n" +
            "where 1=1 " +
            ifCreateTimeStatement +
            "and create_datetime between #{createStartTime} and #{createEndTime}" +
            endIfStatement +
            "group by record_id\n" +
            endScriptStatement)
    List<HashMap<String,Object>>  getPatientHxCount(Parameter parameter);

    /**
     * 往case base info里插入数据
     */
    @Insert("INSERT INTO case_base_info (cecmid_id, create_datetime,\n" +
            "                                              patient_id,\n" +
            "                                              patient_name, electronic_health_card_no, medical_safety_class_code,\n" +
            "                                              sex_code, sex_name, id_card_no, residence_health_card_no,\n" +
            "                                              medical_safety_no, birth_date, insur_card_no)\n" +
            "VALUES (\n" +
            "#{cecmid_id}, #{create_datetime}, #{patient_id},\n" +
            "#{patient_name}, #{electronic_health_card_no}, #{medical_safety_class_code}, '0', 'F', #{id_card_no}, #{residence_health_card_no}, #{medical_safety_no}, '2012-10-14 00:00:00',\n" +
            "#{insur_card_no});\n")
    Integer insertDataToCasePatient(String cecmid_id, String create_datetime, String patient_id, String patient_name,
                                    String electronic_health_card_no, String medical_safety_class_code, String id_card_no,
                                    String residence_health_card_no, String medical_safety_no, String insur_card_no);

    /**
     * 往 patient basic info里插入数据
     */
    @Insert("INSERT INTO patient_basic_info (cecmid_id, work_unit_phone, blood_type,\n" +
            "                                country_name, language_name, modify_datetime, modify_operator_code,\n" +
            "                                modify_operator_name,\n" +
            "                                delete_flag, register_datetime, storage_datetime, org_code, org_name,\n" +
            "                                create_datetime,\n" +
            "                                patient_id, register_type_code, register_type_name, outp_case_no,\n" +
            "                                inp_case_no,\n" +
            "                                outp_medical_no, birth_address_village, birth_address_town,\n" +
            "                                birth_address_county,\n" +
            "                                birth_address_city, birth_address_province, birth_address,\n" +
            "                                relate_type_name, relate_type_code,\n" +
            "                                relate_name, e_mail, post_code, address_door, address_village,\n" +
            "                                medical_safety_class_name,\n" +
            "                                address_town, address_county, address_city, address_province, address,\n" +
            "                                phone_no,\n" +
            "                                profession_name, work_unit_name, birth_date, sex_name, sex_code,\n" +
            "                                insur_card_no,\n" +
            "                                medical_safety_no, driver_license_no, hongkong_macao_pass_no,\n" +
            "                                taiwan_pass_no,\n" +
            "                                other_legal_valid_no, marital_code, marital_name, nation_code,\n" +
            "                                nation_name,\n" +
            "                                country_code, language_code, org_empi, patient_name,\n" +
            "                                community_residence_flag,\n" +
            "                                ic_card_no, birth_post_code, relate_phone_no, relate_address,\n" +
            "                                relate_address_province,\n" +
            "                                relate_address_city, relate_address_county, relate_address_town,\n" +
            "                                relate_address_village,\n" +
            "                                relate_address_door, residence_health_card_no,\n" +
            "                                electronic_health_card_no,\n" +
            "                                medical_safety_class_code, birth_address_door, relate_post_code,\n" +
            "                                native_place_code,\n" +
            "                                native_place_name, id_card_no, household_register_no, passport_no,\n" +
            "                                officers_certificate_no)\n" +
            "VALUES (#{cecmid_id}, '工作电话', '1', '中国', 'chinese', #{modify_datetime}, '1', '汤茜', '0', '2020-03-26 23:27:10',\n" +
            "           '2020-03-26 23:27:10', '11', 'TEST SOURCE', #{create_datetime},#{patient_id}, '2',\n" +
            "           '汤茜2', #{outp_case_no}, #{inp_case_no}, #{outp_medical_no}, '易州广场', '宝兴县', '雅安市', '雅安市', '四川省',\n" +
            "           '四川省雅安市宝兴县易州广场181号', '母女', '123', null, 'gpinaqu@photobucket.com', '625700', '181号', '易州广场', null, '宝兴县',\n" +
            "           '雅安市',\n" +
            "           '雅安市', '四川省', '四川省雅安市宝兴县易州广场181号', '1647', null, null, '2012-10-14 00:00:00', 'F', '0', '111111',\n" +
            "           '511827201210149000', '', '1111111111', null, null, null, null, null, null, null, '86',\n" +
            "           null, #{patient_name}, null,\n" +
            "           null, '625700', null, '四川省雅安市宝兴县易州广场181号', '四川省', '雅安市', '雅安市', '宝兴县', '易州广场', '181号', null, null, null,\n" +
            "           '181号',\n" +
            "           '6257001', null, null, '511827201210149000', null, null, null);\n")
    Integer insertDataToBasicPatient(String cecmid_id,String modify_datetime,String create_datetime,String patient_id,
                                     String outp_case_no,String inp_case_no,String outp_medical_no,String patient_name);

}
