package org.cechc.etl.test.obsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSResultAllCountMapper {
    /**
     * 获取Result条数
     */
    @Select("SELECT COUNT(*) FROM result;\n")
    Integer getResultCount();

    /**
     * 获取Result_provider条数
     */
    @Select("SELECT COUNT(*) FROM result_provider;\n")
    Integer getResultProviderCount();

    /**
     * 获取Result_location条数
     */
    @Select("SELECT COUNT(*) FROM result_location;\n")
    Integer getResultLocationCount();
}
