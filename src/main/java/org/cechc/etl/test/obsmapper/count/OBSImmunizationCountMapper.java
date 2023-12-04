package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSImmunizationCountMapper {

    /**
     * 获取immunization条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM immunization;")
    Integer getImmunizationCount();

    /**
     * 获取immunization_provider条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM immunization_provider;")
    Integer getImmunizationProCount();

    /**
     * 获取immunization_location条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM immunization_location;")
    Integer getImmunizationLocCount();




}
