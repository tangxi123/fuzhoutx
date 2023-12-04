package org.cechc.etl.test.odsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ODSProviderAllCountMapper {
    /**
     * 获取dict_staff_dict条数
     */
    @Select("SELECT COUNT(DISTINCT(staff_no)) FROM dict_staff_dict where org_code='11';")
    Integer getDictStaffCount();

    /**
     * 获取dict_staff_dict address条数
     */
    @Select("SELECT COUNT(DISTINCT(staff_no)) FROM dict_staff_dict\n" +
            "where org_code='11';")
    Integer getDictStaffAddrCount();

    /**
     * 获取dict_staff_dict identifier条数
     */
    @Select("select \n" +
            "count(case when id_card_no != '' then 1 else null end) +\n" +
            "       count(case when employe_no != '' then 1 else null end) +\n" +
            "       count(case when doctor_certificate_no != '' then 1 else null end) as total\n" +
            "from dict_staff_dict\n" +
            "where org_code='11';" )
    Integer getDictStaffIdentiCount();

    /**
     * 获取dict_staff_dict specialty条数
     */
    @Select("SELECT COUNT(DISTINCT(staff_no)) FROM dict_staff_dict\n" +
            "where org_code='11';")
    Integer getDictStaffSpecCount();


    @Select("SELECT COUNT(DISTINCT(staff_no)) FROM dict_staff_dict\n" +
            "where org_code='11';")
    Integer getDictStaffGroupCount();

}
