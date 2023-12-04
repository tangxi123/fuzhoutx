package org.cechc.etl.test.odsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ODSScheduleAllCountMapper {
    /**
     outp_register_record条数
     */
    @Select("SELECT COUNT(orr.cecmid_id) FROM\n" +
            "    outp_register_record orr inner join outp_visit_record ovr\n" +
            "on orr.org_code = ovr.org_code\n" +
            "and orr.register_no = ovr.register_no\n" +
            "and orr.patient_id = ovr.patient_id;")
    Integer getOutpRegisterCount();

    /**
     outp_register_record provider条数
     */
    @Select("SELECT COUNT(orr.cecmid_id) FROM\n" +
            "    outp_register_record orr inner join outp_visit_record ovr\n" +
            "on orr.org_code = ovr.org_code\n" +
            "and orr.register_no = ovr.register_no\n" +
            "and orr.patient_id = ovr.patient_id\n" +
            "where ovr.cure_dept_code is not null;")
    Integer getOutpRegisterProCount();


    /**
     outp_register_record location条数
     */
    @Select("SELECT COUNT(orr.cecmid_id) FROM\n" +
            "    outp_register_record orr inner join outp_visit_record ovr\n" +
            "on orr.org_code = ovr.org_code\n" +
            "and orr.register_no = ovr.register_no\n" +
            "and orr.patient_id = ovr.patient_id\n" +
            "where ovr.cure_dept_code is not null;")
    Integer getOutpRegisterLocCount();


    @Select("select count(*) as total\n" +
            "from outp_visit_record ovr\n" +
            "where ovr.patient_id in (\n" +
            "    select t1.patient_id\n" +
            "    from (\n" +
            "             select pbi.patient_id as patient_id\n" +
            "             from patient_basic_info pbi\n" +
            "                      left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "             where pbi.org_code = '11'\n" +
            "             union\n" +
            "             select cbi.patient_id as patient_id\n" +
            "             from patient_basic_info pbi\n" +
            "                      right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "             where cbi.org_code = '11'\n" +
            "         ) t1\n" +
            ")\n" +
            "  and ovr.org_code = '11';")
    Integer getOutVisitJoinPatientCount();

    @Select("select count(*)\n" +
            "from inp_visit_record ivr\n" +
            "where ivr.patient_id in (\n" +
            "    select t1.patient_id\n" +
            "    from (\n" +
            "             select pbi.patient_id as patient_id\n" +
            "             from patient_basic_info pbi\n" +
            "                      left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "             where pbi.org_code = '11'\n" +
            "             union\n" +
            "             select cbi.patient_id as patient_id\n" +
            "             from patient_basic_info pbi\n" +
            "                      right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "             where cbi.org_code = '11'\n" +
            "         ) t1\n" +
            ")\n" +
            "  and ivr.org_code = '11';")
    Integer getInVisitJoinPatientCount();

    @Select("select count(case when register_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when cure_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record ovr\n" +
            "where ovr.patient_id in (\n" +
            "    select t1.patient_id\n" +
            "    from (\n" +
            "             select pbi.patient_id as patient_id\n" +
            "             from patient_basic_info pbi\n" +
            "                      left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "             where pbi.org_code = '11'\n" +
            "             union\n" +
            "             select cbi.patient_id as patient_id\n" +
            "             from patient_basic_info pbi\n" +
            "                      right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "             where cbi.org_code = '11'\n" +
            "         ) t1\n" +
            ")\n" +
            "  and ovr.org_code = '11';")
    Integer getOutVisitJoinPatientProviderCount();

    @Select("select count(case when outp_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_doctor_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record ivr\n" +
            "where ivr.patient_id in (\n" +
            "    select t1.patient_id from\n" +
            "    (\n" +
            "        select pbi.patient_id as patient_id\n" +
            "        from patient_basic_info pbi\n" +
            "                 left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "        where pbi.org_code = '11'\n" +
            "        union\n" +
            "        select cbi.patient_id as patient_id\n" +
            "        from patient_basic_info pbi\n" +
            "                 right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "        where cbi.org_code = '11'\n" +
            "    )t1\n" +
            ")\n" +
            "  and ivr.org_code = '11';\n")
    Integer getInVisitJoinPatientProviderCount();

    @Select("select count(case when cure_dept_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record ovr\n" +
            "where ovr.patient_id in (\n" +
            "    select t1.patient_id\n" +
            "    from (\n" +
            "             select pbi.patient_id as patient_id\n" +
            "             from patient_basic_info pbi\n" +
            "                      left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "             where pbi.org_code = '11'\n" +
            "             union\n" +
            "             select cbi.patient_id as patient_id\n" +
            "             from patient_basic_info pbi\n" +
            "                      right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "             where cbi.org_code = '11'\n" +
            "         ) t1\n" +
            ")\n" +
            "  and ovr.org_code = '11';")
    Integer getOutVisitJoinPatientLocationCount();

    @Select("select count(case when admission_dept_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record ivr\n" +
            "where ivr.patient_id in (\n" +
            "    select t1.patient_id\n" +
            "    from (\n" +
            "             select pbi.patient_id as patient_id\n" +
            "             from patient_basic_info pbi\n" +
            "                      left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "             where pbi.org_code = '11'\n" +
            "             union\n" +
            "             select cbi.patient_id as patient_id\n" +
            "             from patient_basic_info pbi\n" +
            "                      right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "             where cbi.org_code = '11'\n" +
            "         ) t1\n" +
            ")\n" +
            "  and ivr.org_code = '11';")
    Integer getInVisitJoinPatientLocationCount();
}
