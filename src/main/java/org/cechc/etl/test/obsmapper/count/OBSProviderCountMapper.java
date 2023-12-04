package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OBSProviderCountMapper {
    String beginScriptStatement = "<script>";
    String endScriptStatement = "</script>";
    String ifListSizeTestNotZero = "<if test='list.size() !=0'>";
    String iflistSizeTestIsZero = "<if test='list.size() ==0'>";
    String endIfStatement = "</if>";

    /**
     * 获取provider条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM provider WHERE 1=1 " +
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
    Long getProviderCount(@Param("list") List<String> list);

    /**
     * 获取provider_address条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM provider_address WHERE 1=1 " +
                    iflistSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and prov_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getProviderAddrCount(@Param("list") List<String> list);

    /**
     * 获取provider_group条数
     */

    @Select("SELECT COUNT(*) FROM provider_group")
    Long getProviderGroupCount();

    /**
     * 获取provider_identifier条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM provider_identifier WHERE 1=1 " +
                    iflistSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and prov_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getProviderIdentifierCount(@Param("list") List<String> list);

    /**
     * 获取provider_specialty条数
     */
    @Select(
            beginScriptStatement +
                    "SELECT COUNT(*) FROM provider_specialty WHERE 1=1 " +
                    iflistSizeTestIsZero +
                    "and 1=0" +
                    endIfStatement +
                    ifListSizeTestNotZero +
                    "and prov_id in " +
                    "<foreach item = 'item' collection = 'list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    endIfStatement +
                    endScriptStatement)
    Long getProviderSpecCount(@Param("list") List<String> list);
}
