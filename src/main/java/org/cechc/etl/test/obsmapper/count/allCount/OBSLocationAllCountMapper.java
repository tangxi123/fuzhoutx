package org.cechc.etl.test.obsmapper.count.allCount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSLocationAllCountMapper {
    /**
     * 获取location条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM location;")
    Integer getLocationCount();
}
