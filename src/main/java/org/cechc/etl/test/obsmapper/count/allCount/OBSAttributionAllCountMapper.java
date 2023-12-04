package org.cechc.etl.test.obsmapper.count.allCount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSAttributionAllCountMapper {

    @Select("SELECT COUNT(*) FROM attribution;")
    Integer getAttributionCount();
}
