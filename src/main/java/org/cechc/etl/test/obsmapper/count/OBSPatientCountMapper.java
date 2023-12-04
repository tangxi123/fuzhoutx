package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OBSPatientCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifListSizeTestNotZero = "<if test='list.size() !=0'>";
    String ifListSizeTestIsZero = "<if test='list.size() ==0'>";
    String endIfStatement = "</if>";

    /**
     * 查询patient总条数
     */
    @Select("SELECT COUNT(*) FROM patient")
    Integer getPatientCount();

    /**
     * 根据patient_id list查询patient_info count数据
     *
     * @param list
     * @return
     */
    @Select(
            beginScriptStatement +
                    "SELECT count(*) FROM patient WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and record_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getPatientIdCount(@Param("list") List<String> list);

    /**
     * 查询patient_identifier总条数
     */
    @Select("SELECT COUNT(*) FROM patient_identifier")
    Integer getPatientIdentifierCount();

    /**
     * 根据patient_id list查询patient_identifier count数据
     *
     * @param list
     * @return
     */
    @Select(
            beginScriptStatement +
                    "SELECT count(*) FROM patient_identifier WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and pat_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getPatientIdentifierCountByList(@Param("list") List<String> list);

    /**
     * 查询patient_address总条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM patient_address WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and pat_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getPatientAddressCount(@Param("list") List<String> list);

    /**
     * 查询patient_attribution总条数
     */
    @Select("SELECT COUNT(*) FROM patient_attribution")
    Long getPatientAttributionCount();


    /**
     * 查询patient_insurance总条数
     */
    @Select("SELECT COUNT(*) FROM patient_insurance")
    Long getPatientInsuranceCount();

    /**
     * 查询patient_hx总条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM patient_hx WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and pat_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getPatientHxCount(@Param("list") List<String> list);

    /**
     * 查询patient_program总条数
     */
    @Select("SELECT COUNT(*) FROM patient_program")
    Long getPatientProgramCount();


}
