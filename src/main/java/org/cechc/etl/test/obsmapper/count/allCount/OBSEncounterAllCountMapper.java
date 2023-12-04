package org.cechc.etl.test.obsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSEncounterAllCountMapper {
    /**
     * 获取encounter条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM encounter;")
    Integer getEncounterCount();

    /**
     * 获取encounter_provider条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM encounter_provider;")
    Integer getEncounterProCount();

    /**
     * 获取encounter_location条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM encounter_location;")
    Integer getEncounterLocCount();
}
