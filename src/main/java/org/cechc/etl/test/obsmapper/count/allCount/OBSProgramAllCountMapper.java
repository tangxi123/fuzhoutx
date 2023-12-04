package org.cechc.etl.test.obsmapper.count.allCount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSProgramAllCountMapper {
    /**
     * 获取program条数
     */
    @Select("SELECT COUNT(*) FROM program;")
    Integer getProgramCount();
}
