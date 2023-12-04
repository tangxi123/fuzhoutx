package org.cechc.etl.test.obsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSDiagnosisAllCountMapper {
    /**
     * 获取diagnosis条数
     */
    @Select("SELECT COUNT(*) FROM diagnosis;")
    Integer getDiagnosisCount();

    /**
     * 获取diagnosis_location条数
     */
    @Select("SELECT COUNT(*) FROM diagnosis_location;")
    Integer getDiagnosisLocCount();

    /**
     * 获取diagnosis_provider条数
     */
    @Select("SELECT COUNT(*) FROM diagnosis_provider;")
    Integer getDiagnosisProCount();

}
