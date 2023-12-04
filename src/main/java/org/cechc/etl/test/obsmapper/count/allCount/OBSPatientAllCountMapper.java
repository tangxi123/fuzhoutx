package org.cechc.etl.test.obsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OBSPatientAllCountMapper {

    /**
     * 查询patient总条数
     */
    @Select("SELECT COUNT(*) FROM patient")
    Integer getPatientCount();

    /**
     * 查询patient_identifier总条数
     */
    @Select("SELECT COUNT(*) FROM patient_identifier")
    Integer getPatientIdentifierCount();

    /**
     * 查询patient_address总条数
     */
    @Select("SELECT COUNT(*) FROM patient_address")
    Integer getPatientAddressCount();

    /**
     * 查询patient_attribution总条数
     */
    @Select("SELECT COUNT(*) FROM patient_attribution")
    Integer getPatientAttributionCount();


    /**
     * 查询patient_insurance总条数
     */
    @Select("SELECT COUNT(*) FROM patient_insurance")
    Integer getPatientInsuranceCount();

    /**
     * 查询patient_hx总条数
     */
    @Select("SELECT COUNT(*) FROM patient_hx")
    Integer getPatientHxCount();

    /**
     * 查询patient_program总条数
     */
    @Select("SELECT COUNT(*) FROM patient_program")
    Integer getPatientProgramCount();


}
