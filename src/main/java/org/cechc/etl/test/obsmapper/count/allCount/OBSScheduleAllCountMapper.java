package org.cechc.etl.test.obsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSScheduleAllCountMapper {
    /**
     * 获取Schedule条数
     */
    @Select("SELECT COUNT(*) FROM schedule;\n")
    Integer getScheduleCount();

    /**
     * 获取Schedule provider条数
     */
    @Select("SELECT COUNT(*) FROM schedule_provider;\n")
    Integer getScheduleProCount();

    /**
     * 获取Schedule location条数
     */
    @Select("SELECT COUNT(*) FROM schedule_location;\n")
    Integer getScheduleLocCount();

}
