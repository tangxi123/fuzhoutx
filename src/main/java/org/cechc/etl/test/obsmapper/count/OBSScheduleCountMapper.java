package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OBSScheduleCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifListSizeTestNotZero = "<if test='list.size() !=0'>";
    String iflistSizeTestIsZero = "<if test='list.size() ==0'>";
    String endIfStatement = "</if>";
    /**
     * 获取Schedule条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM schedule WHERE 1=1 " +
                    iflistSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and record_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getScheduleCount(@Param("list") List<String> list);

    /**
     * 获取Schedule provider条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM schedule_provider WHERE 1=1 " +
                    iflistSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and sched_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getScheduleProCount(@Param("list") List<String> list);

    /**
     * 获取Schedule location条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM schedule_location WHERE 1=1 " +
                    iflistSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and sched_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getScheduleLocCount(@Param("list") List<String> list);

}
