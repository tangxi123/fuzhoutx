package org.cechc.etl.test.obsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSOrderAllCountMapper {
    /**
     *获取order_clinical条数
     */
    @Select("SELECT COUNT(*) FROM order_clinical;\n")
    Integer getOrderCount();

    /**
     * order_provider条数
     */
    @Select("SELECT COUNT(*) FROM order_provider;\n")
    Integer getOrderProCount();

    /**
     * order_location条数
     */
    @Select("SELECT COUNT(*) FROM order_location;\n")
    Integer getOrderLocCount();

}
