package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OBSOrderCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifListSizeTestNotZero = "<if test='list.size() !=0'>";
    String ifListSizeTestIsZero = "<if test='list.size() ==0'>";
    String endIfStatement = "</if>";
    /**
     *获取order_clinical条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM order_clinical WHERE 1=1 " +
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
    Long getOrderCount(@Param("list") List<String> list);

    /**
     * order_provider条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM order_provider WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and ord_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getOrderProCount(@Param("list") List<String> list);

    /**
     * order_location条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM order_location WHERE 1=1 " +
                    ifListSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and ord_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getOrderLocCount(@Param("list") List<String> list);

}
