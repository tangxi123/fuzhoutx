package org.cechc.etl.test.obsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OBSAdmissionAllCountMapper {
    /**
     * 获取admission的条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM admission;")
    Integer getAdmissionCount();

    /**
     * 获取admission_location的条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM admission_location;")
    Integer getAdmissionLocationCount();

    /**
     * 获取admission_provider的条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM admission_provider;")
    Integer getAdmissionProviderCount();


}
