package org.cechc.etl.test.obsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OBSMedicationAllCountMapper {
    /**
     * Medication条数
     */
    @Select("SELECT COUNT(*) FROM medication;")
    Integer getMedicationCount();

    /**
     * Medication_location条数
     */
    @Select("SELECT COUNT(*) FROM medication_location;")
    Integer getMedicationLocCount();

    /**
     * Medication_provider
     * @return
     */
    @Select("SELECT COUNT(*) FROM medication_provider;")
    Integer getMedicationProCount();
}
