package org.cechc.etl.test.odsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ODSLocationCountMapper {
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
