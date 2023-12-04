package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OBSAdmissionCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifListSizeTestNotZero = "<if test='list.size() !=0'>";
    String iflistSizeTestIsZero = "<if test='list.size() ==0'>";
    String endIfStatement = "</if>";

    /**
     * 获取admission的条数
     * @return
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM admission WHERE 1=1 " +
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
    Long getAdmissionCount(@Param("list") List<String> list);

    /**
     * 获取admission_location的条数
     * @return
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM admission_location WHERE 1=1 " +
                    iflistSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and adm_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getAdmissionLocationCount(@Param("list") List<String> list);

    /**
     * 获取admission_provider的条数
     * @return
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM admission_provider WHERE 1=1 " +
                    iflistSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and adm_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getAdmissionProviderCount(@Param("list") List<String> list);


}
