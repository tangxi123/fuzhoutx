package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OBSDiagnosisCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifListSizeTestNotZero = "<if test='list.size() !=0'>";
    String ifListSizeTestIsZero = "<if test='list.size() ==0'>";
    String endIfStatement = "</if>";
    /**
     * 获取diagnosis条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM diagnosis WHERE 1=1 " +
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
    Long getDiagnosisCount(@Param("list") List<String> list);

    /**
     * 获取diagnosis_location条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM diagnosis_location WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and diag_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getDiagnosisLocCount(@Param("list") List<String> list);

    /**
     * 获取diagnosis_provider条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM diagnosis_provider WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and diag_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getDiagnosisProCount(@Param("list") List<String> list);

}
