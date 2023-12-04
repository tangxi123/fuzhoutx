package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OBSMedicationCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifListSizeTestNotZero = "<if test='list.size() !=0'>";
    String ifListSizeTestIsZero = "<if test='list.size() ==0'>";
    String endIfStatement = "</if>";
    /**
     * Medication条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM medication WHERE 1=1 " +
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
    Long getMedicationCount(@Param("list") List<String> list);

    /**
     * Medication_location条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM medication_location WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and med_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getMedicationLocCount(@Param("list") List<String> list);

    /**
     * Medication_provider
     * @return
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM medication_provider WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and med_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getMedicationProCount(@Param("list") List<String> list);
}
