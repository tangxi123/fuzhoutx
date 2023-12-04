package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OBSEncounterCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifListSizeTestNotZero = "<if test='list.size() !=0'>";
    String ifListSizeTestIsZero = "<if test='list.size() ==0'>";
    String endIfStatement = "</if>";
    /**
     * 获取encounter条数
     * @return
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM encounter WHERE 1=1 " +
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
    Long getEncounterCount(@Param("list") List<String> list);

    /**
     * 获取encounter_provider条数
     * @return
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM encounter_provider WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and enc_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getEncounterProCount(@Param("list") List<String> list);

    /**
     * 获取encounter_location条数
     * @return
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM encounter_location WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and enc_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getEncounterLocCount(@Param("list") List<String> list);
}
