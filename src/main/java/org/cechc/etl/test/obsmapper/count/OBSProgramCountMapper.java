package org.cechc.etl.test.obsmapper.count;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSProgramCountMapper {
    /**
     * 获取program条数
     */
    @Select("SELECT COUNT(*) FROM program;")
    Integer getProgramCount();
}
