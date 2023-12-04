package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSLocationCountMapper {
    /**
     * 获取location条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM location;")
    Integer getLocationCount();
}
