package org.cechc.etl.test.odsmapper.count.allCount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ODSLocationAllCountMapper {

    @Select("select count(*) from dict_org where org_code='11'")
    Integer getOrgLocCount();

    @Select("select " +
            "count(case when admission_dept_code != '' then 1 else null end)+\n"+
            "count(case when in_dept_code != '' then 1 else null end)) +\n" +
            "count(case when nurse_dept_code != ''then 1 else null end)) +\n" +
            "count(case when ward_code != ''then 1 else null end)) +\n" +
            "count(case when room_no != '' then 1 else null end) +\n" +
            "count(case when bed_no != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi on ovr.patient_id = pbi.patient_id\n" +
            "         inner join case_base_info cbi on ovr.patient_id = cbi.patient_id\n" +
            "and ovr.org_code='11';")
    Integer getAdmissionKeshiLocCount();

    /**
     outp_visit_record科室
     */
    @Select("SELECT COUNT(*) FROM outp_visit_record\n" +
            "WHERE cure_dept_code is not null;")
    Integer getOutpVisitDeptCount();

    /**
     inp_visit_record床位
     */
    @Select("SELECT COUNT(*) FROM inp_visit_record\n" +
            "WHERE in_dept_code is not null\n" +
            "or ward_code is not null\n" +
            "or room_no is not null\n" +
            "or bed_no is not null;")
    Integer getInpVisitBedCount();

    /**
     inp_visit_record病房
     */
    @Select("SELECT COUNT(*) FROM inp_visit_record\n" +
            "WHERE in_dept_code is not null\n" +
            "or ward_code is not null\n" +
            "or room_no is not null;")
    Integer getInpVisitRoomCount();

    /**
     inp_visit_record病区
     */
    @Select("SELECT COUNT(*) FROM inp_visit_record\n" +
            "WHERE in_dept_code is not null\n" +
            "or ward_code is not null;")
    Integer getInpVisitWardCount();

    /**
     inp_visit_record科室
     */
    @Select("SELECT COUNT(*) FROM inp_visit_record\n" +
            "WHERE in_dept_code is not null\n" +
            "or nurse_dept_code is not null\n" +
            "or discharge_dept_code is not null\n" +
            "or admission_dept_code is not null;")
    Integer getInpVisitDeptCount();
}
