package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSAttributionCountMapper {

    @Select("SELECT COUNT(*) FROM attribution;")
    Integer getAttributionCount();
}
