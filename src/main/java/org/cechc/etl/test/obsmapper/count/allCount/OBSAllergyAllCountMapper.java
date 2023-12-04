package org.cechc.etl.test.obsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OBSAllergyAllCountMapper {
    /**
     * 查询allery的总条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM allergy;")
    Integer getAllergyCount();

    /**
     * 查询allery_provider的总条数
     * @return
     */
    @Select("SELECT COUNT(*) FROM allergy_provider;")
    Integer getAllergyProCount();

    /**
     * 查询allery_loc的总条数
     * @return
     */
    @Select(" SELECT COUNT(*) FROM allergy_location;")
    Integer getAllergyLocCount();
}
