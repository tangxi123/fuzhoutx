package org.cechc.etl.test.odsmapper.count.allCount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cechc.etl.test.pojo.Parameter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ODSPatientAllCountMapper {
    /**
     * 查询patient_basic_info总条数
     */
    @Select("SELECT Count(DISTINCT (patient_id)) as total\n" +
            "FROM patient_basic_info\n" +
            "WHERE org_code = '11'\n" +
            "  and patient_id != '';")
    Integer getPatientBasicInfoCount();

    /**
     * 根据address条件查询patient_basic_info的address条数
     */
    @Select("select count(case\n" +
            "                 when\n" +
            "                         birth_address != '' or\n" +
            "                         birth_address_province != '' or\n" +
            "                         birth_address_county != '' or\n" +
            "                         birth_address_door != '' or\n" +
            "                         birth_address_town != '' or\n" +
            "                         birth_address_village != '' or\n" +
            "                         birth_address_city != '' or\n" +
            "                         birth_post_code != ''\n" +
            "                     then 1\n" +
            "                 else null end) +\n" +
            "       count(case\n" +
            "                 when\n" +
            "                         relate_address != '' or\n" +
            "                         relate_address_province != '' or\n" +
            "                         relate_address_county != '' or\n" +
            "                         relate_address_door != '' or\n" +
            "                         relate_address_town != '' or\n" +
            "                         relate_address_village != '' or\n" +
            "                         relate_address_city != '' or\n" +
            "                         relate_post_code != ''\n" +
            "                     then 1\n" +
            "                 else null end\n" +
            "           ) as total\n" +
            "from patient_basic_info\n" +
            "where org_code = '11';")
    Integer getPatientBasicInfoAddressCount();

    /**
     * 获取patient_basic_info与identifier相关的条数，因为patient_identier与patient表应该是一一对应的关系，
     * 所以直接获取patient_basic_info表的条数就行了.
     */
    @Select("select count(case when insur_card_no != '' then 1 else null end) +\n" +
            "       count(case when driver_license_no != '' then 1 else null end) +\n" +
            "       count(case when hongkong_macao_pass_no != '' then 1 else null end) +\n" +
            "       count(case when taiwan_pass_no != '' then 1 else null end) +\n" +
            "       count(case when ic_card_no != '' then 1 else null end) +\n" +
            "       count(case when residence_health_card_no != '' then 1 else null end) +\n" +
            "       count(case when electronic_health_card_no != '' then 1 else null end) +\n" +
            "       count(case when household_register_no != '' then 1 else null end) +\n" +
            "       count(case when passport_no != '' then 1 else null end) +\n" +
            "       count(case when officers_certificate_no != '' then 1 else null end) +\n" +
            "       count(case when patient_id != '' then 1 else null end) as total\n" +
            "from patient_basic_info\n" +
            "where org_code = '11';")
    Integer getPatientBasicInfoIdentifierCount();

    /**
     * 根据insurance条件查询patient_basic_info的insurance条数
     */
//    @Select("SELECT COUNT(*)\n" +
//            "FROM patient_basic_info\n" +
//            "WHERE medical_safety_class_code is not null\n" +
//            "   or medical_safety_class_name is not null;")
//    Integer getPatientBasicInfoInsuranceCount();


    /**
     * 根据insurance条件查询outp_medical_record的patient_hx关联的条数
     */
    /**
     * 查询patient hx count条数
     */
    @Select(
            "select patient_id as record_id,\n" +
            "count(DISTINCT concat(patient_id, outp_visit_no)) as total\n" +
            "from outp_medical_record\n"
           )
    Integer getPatientHxCount();


    /**
     * 查询case_base_info的patient条数
     */
    @Select("SELECT COUNT(DISTINCT (patient_id)) as total\n" +
            "FROM case_base_info\n" +
            "WHERE org_code = '11'\n" +
            "  and patient_id != '';")
    Integer getCaseBaseInfoPatientCount();

    /**
     * 根据address条件查询case_base_info的address条数
     */
    @Select("select\n" +
            "                   count(case when\n" +
            "                       birth_address != '' or\n" +
            "                       birth_address_province != '' or\n" +
            "                       birth_address_county != '' or\n" +
            "                       birth_address_door != '' or\n" +
            "                       birth_address_town != '' or\n" +
            "                       birth_address_village != '' or\n" +
            "                       birth_address_city != '' or\n" +
            "                       birth_post_code != ''\n" +
            "                   then 1 else null end)+\n" +
            "                   count(case when\n" +
            "                       account_address != '' or\n" +
            "                       account_address_province != '' or\n" +
            "                       account_address_county != '' or\n" +
            "                       account_address_door != '' or\n" +
            "                       account_address_town != '' or\n" +
            "                       account_address_village != '' or\n" +
            "                       account_address_city != '' or\n" +
            "                       account_post_code != ''\n" +
            "                   then 1 else null end\n" +
            "                       )  as total\n" +
            "            from case_base_info\n" +
            "where org_code = '11';")
    Integer getCaseBaseInfoPatientAddressCount();

    @Select("select count(case\n" +
            "                 when\n" +
            "                         cbi.birth_address != '' or\n" +
            "                         cbi.birth_address_province != '' or\n" +
            "                         cbi.birth_address_county != '' or\n" +
            "                         cbi.birth_address_door != '' or\n" +
            "                         cbi.birth_address_town != '' or\n" +
            "                         cbi.birth_address_village != '' or\n" +
            "                         cbi.birth_address_city != '' or\n" +
            "                         cbi.birth_post_code != ''\n" +
            "                     then 1\n" +
            "                 else null end) +\n" +
            "       count(case\n" +
            "                 when\n" +
            "                         cbi.account_address != '' or\n" +
            "                         cbi.account_address_province != '' or\n" +
            "                         cbi.account_address_county != '' or\n" +
            "                         cbi.account_address_door != '' or\n" +
            "                         cbi.account_address_town != '' or\n" +
            "                         cbi.account_address_village != '' or\n" +
            "                         cbi.account_address_city != '' or\n" +
            "                         cbi.account_post_code != ''\n" +
            "                     then 1\n" +
            "                 else null end\n" +
            "           ) as total\n" +
            "from patient_basic_info pbi\n" +
            "         inner join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "where pbi.org_code = '11';")
    Integer getDuplicatePatientAddressCount();

    /**
     * 获取case_base_info与identifier相关的条数，因为patient_identier与patient表应该是一一对应的关系，
     * 所以直接获取case_base_info表的条数就行了.
     */
    @Select("select count(case when id_card_no != '' then 1 else null end) +\n" +
            "       count(case when insur_card_no != '' then 1 else null end) +\n" +
            "       count(case when electronic_health_card_no != '' then 1 else null end) +\n" +
            "       count(case when residence_health_card_no != '' then 1 else null end) +\n" +
            "       count(case when patient_id != '' then 1 else null end)\n" +
            "           as total\n" +
            "from case_base_info\n" +
            "where org_code = '11';")
    Integer getCaseBaseInfoPatientIdentifierCount();

    /**
     * 根据insurance条件查询patient_basic_info的insurance条数
     */
//    @Select("SELECT COUNT(*)\n" +
//            "FROM case_base_info\n" +
//            "WHERE medical_safety_class_code is not null\n" +
//            "   or medical_safety_no is not null;")
//    Integer getCaseBaseInfoPatientInsuranceCount();

    /**
     * 查询patient_basic_info和case_base_info共有的patient条数
     */
    @Select("select count(DISTINCT (pbi.patient_id)) as total\n" +
            "from patient_basic_info pbi\n" +
            "         inner join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "where pbi.org_code = '11'\n" +
            "  and pbi.patient_id != '';")
    Integer getDuplicatePatientCount();

    /**
     * 查询patient_basic_info和case_base_info共有的patient_id
     */
    @Select("select pbi.patient_id\n" +
            "from patient_basic_info pbi\n" +
            "         inner join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "where pbi.org_code = '11';")
    List<String> getDuplicatePatientIds();

    @Select("select count(case when cbi.id_card_no != '' then 1 else null end) +\n" +
            "       count(case when cbi.insur_card_no != '' then 1 else null end) +\n" +
            "       count(case when cbi.electronic_health_card_no != '' then 1 else null end) +\n" +
            "       count(case when cbi.residence_health_card_no != '' then 1 else null end) +\n" +
            "       count(case when cbi.patient_id != '' then 1 else null end) as total\n" +
            "from patient_basic_info pbi\n" +
            "         inner join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "where pbi.org_code = '11';")
    Integer getCaseBaseDuplicatePatIdCount();

    /**********patient_hx*********************/
    @Select("select count(*) as total\n" +
            "from outp_medical_record omr\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on omr.org_code = ovr.org_code\n" +
            "                        and omr.outp_visit_no = ovr.outp_visit_no\n" +
            "                        and omr.patient_id = ovr.patient_id\n" +
            "where omr.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (select pbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where pbi.org_code = '11'\n" +
            "                union\n" +
            "                select cbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where cbi.org_code = '11'\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and omr.org_code = '11';")
    Integer getOutJoinPatient();

}
