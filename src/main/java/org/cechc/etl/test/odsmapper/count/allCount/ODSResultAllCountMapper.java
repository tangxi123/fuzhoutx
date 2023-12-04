package org.cechc.etl.test.odsmapper.count.allCount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ODSResultAllCountMapper {
    /**
     lis_report_detail
     */
    @Select("SELECT COUNT(*)\n" +
            "FROM lis_report_detail ld\n" +
            "         inner join lis_report_master lm\n" +
            "             on ld.org_code = lm.org_code\n" +
            "             and ld.report_no = lm.report_no;\n")
    Integer getLisReportCount();

    @Select("select count(*) as total\n" +
            "from lis_report_detail lrd\n" +
            "         inner join lis_report_master lrm\n" +
            "                    on lrd.org_code = lrm.org_code\n" +
            "                        and lrd.report_no = lrm.report_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on ivr.inp_visit_no = lrm.his_visit_no\n" +
            "                        and ivr.patient_id = lrm.his_patient_id\n" +
            "                        and ivr.org_code = lrm.org_code\n" +
            "where lrm.his_patient_id in (\n" +
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
    Integer getReportJoinInVisitJoinPatientCount();

    @Select("select count(*) as total\n" +
            "from lis_report_detail lrd\n" +
            "         inner join lis_report_master lrm\n" +
            "                    on lrd.org_code = lrm.org_code\n" +
            "                        and lrd.report_no = lrm.report_no\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on ovr.outp_visit_no = lrm.his_visit_no\n" +
            "                        and ovr.patient_id = lrm.his_patient_id\n" +
            "                        and ovr.org_code = lrm.org_code\n" +
            "where lrm.his_patient_id in (\n" +
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
    Integer getReportJoinOutVisitJoinPatientCount();

    @Select("select count(*) as total\n" +
            "from lis_report_detail lrd\n" +
            "         inner join lis_report_master lrm\n" +
            "                    on lrd.org_code = lrm.org_code\n" +
            "                        and lrd.report_no = lrm.report_no\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on ovr.outp_visit_no = lrm.his_visit_no\n" +
            "                        and ovr.patient_id = lrm.his_patient_id\n" +
            "                        and ovr.org_code = lrm.org_code\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on ivr.inp_visit_no = lrm.his_visit_no\n" +
            "                        and ivr.patient_id = lrm.his_patient_id\n" +
            "                        and ivr.org_code = lrm.org_code\n" +
            "where lrm.his_patient_id in (\n" +
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
            "  and lrd.org_code = '11';")
    Integer getReportJoinInVisitJoinOutVisitJoinPatientCount();

    /**
     lis_report_detail provider
     */
    @Select("SELECT \n" +
            "       count(case when apply_operator_code != '' then 1 else null end) +\n" +
            "       count(case when collect_operator_code != '' then 1 else null end) +\n" +
            "       count(case when check_operator_code != '' then 1 else null end) +\n" +
            "       count(case when report_operator_code != '' then 1 else null end) +\n" +
            "       count(case when recover_operator_code != '' then 1 else null end) as total\n" +
            "FROM lis_report_detail ld\n" +
            "         left join lis_report_master lm\n" +
            "                   on ld.org_code = lm.org_code\n" +
            "                       and ld.report_no = lm.report_no\n")
    Integer getLisReportProCount();

    @Select("select count(case when lrm.apply_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.collect_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.check_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.report_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.recover_operator_code != '' then 1 else null end) as total\n" +
            "from lis_report_detail lrd\n" +
            "         inner join lis_report_master lrm\n" +
            "                    on lrd.org_code = lrm.org_code\n" +
            "                        and lrd.report_no = lrm.report_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on ivr.inp_visit_no = lrm.his_visit_no\n" +
            "                        and ivr.patient_id = lrm.his_patient_id\n" +
            "                        and ivr.org_code = lrm.org_code\n" +
            "where lrm.his_patient_id in (\n" +
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
            "  and ivr.org_code = '11';")
    Integer getReportJoinInVisitJoinPatientProviderCount();

    @Select("select count(case when lrm.apply_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.collect_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.check_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.report_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.recover_operator_code != '' then 1 else null end) as total\n" +
            "from lis_report_detail lrd\n" +
            "         inner join lis_report_master lrm\n" +
            "                    on lrd.org_code = lrm.org_code\n" +
            "                        and lrd.report_no = lrm.report_no\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on ovr.outp_visit_no = lrm.his_visit_no\n" +
            "                        and ovr.patient_id = lrm.his_patient_id\n" +
            "                        and ovr.org_code = lrm.org_code\n" +
            "where lrm.his_patient_id in (\n" +
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
            "  and ovr.org_code = '11';")
    Integer getReportJoinOutVisitJoinPatientProviderCount();

    @Select("select count(case when lrm.apply_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.collect_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.check_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.report_operator_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.recover_operator_code != '' then 1 else null end) as total\n" +
            "from lis_report_detail lrd\n" +
            "         inner join lis_report_master lrm\n" +
            "                    on lrd.org_code = lrm.org_code\n" +
            "                        and lrd.report_no = lrm.report_no\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on ovr.outp_visit_no = lrm.his_visit_no\n" +
            "                        and ovr.patient_id = lrm.his_patient_id\n" +
            "                        and ovr.org_code = lrm.org_code\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on ivr.inp_visit_no = lrm.his_visit_no\n" +
            "                        and ivr.patient_id = lrm.his_patient_id\n" +
            "                        and ivr.org_code = lrm.org_code\n" +
            "where lrm.his_patient_id in (\n" +
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
            "  and lrd.org_code = '11';")
    Integer getReportJoinInVisitJoinOutVisitJoinPatientProviderCount();


    /**
     * lis_report_detail location
     */
    @Select( "SELECT \n" +
            "       count(case when apply_dept_code != '' then 1 else null end) +\n" +
            "       count(case when apply_ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) as total\n" +
            "FROM lis_report_detail ld\n" +
            "         left join lis_report_master lm\n" +
            "                   on ld.org_code = lm.org_code\n" +
            "                       and ld.report_no = lm.report_no\n")
    Integer getLisReportLocCount();

    @Select("select count(case when lrm.apply_dept_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.apply_ward_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.room_no != '' then 1 else null end) +\n" +
            "       count(case when lrm.bed_no != '' then 1 else null end) as total\n" +
            "from lis_report_detail lrd\n" +
            "         inner join lis_report_master lrm\n" +
            "                    on lrd.org_code = lrm.org_code\n" +
            "                        and lrd.report_no = lrm.report_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on ivr.inp_visit_no = lrm.his_visit_no\n" +
            "                        and ivr.patient_id = lrm.his_patient_id\n" +
            "                        and ivr.org_code = lrm.org_code\n" +
            "where lrm.his_patient_id in (\n" +
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
    Integer getReportJoinInVisitJoinPatientLocationCount();

    @Select("select count(case when lrm.apply_dept_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.apply_ward_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.room_no != '' then 1 else null end) +\n" +
            "       count(case when lrm.bed_no != '' then 1 else null end) as total\n" +
            "from lis_report_detail lrd\n" +
            "         inner join lis_report_master lrm\n" +
            "                    on lrd.org_code = lrm.org_code\n" +
            "                        and lrd.report_no = lrm.report_no\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on ovr.outp_visit_no = lrm.his_visit_no\n" +
            "                        and ovr.patient_id = lrm.his_patient_id\n" +
            "                        and ovr.org_code = lrm.org_code\n" +
            "where lrm.his_patient_id in (\n" +
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
    Integer getReportJoinOutVisitJoinPatientLocationCount();

    @Select("select count(case when lrm.apply_dept_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.apply_ward_code != '' then 1 else null end) +\n" +
            "       count(case when lrm.room_no != '' then 1 else null end) +\n" +
            "       count(case when lrm.bed_no != '' then 1 else null end) as total\n" +
            "from lis_report_detail lrd\n" +
            "         inner join lis_report_master lrm\n" +
            "                    on lrd.org_code = lrm.org_code\n" +
            "                        and lrd.report_no = lrm.report_no\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on ovr.outp_visit_no = lrm.his_visit_no\n" +
            "                        and ovr.patient_id = lrm.his_patient_id\n" +
            "                        and ovr.org_code = lrm.org_code\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on ivr.inp_visit_no = lrm.his_visit_no\n" +
            "                        and ivr.patient_id = lrm.his_patient_id\n" +
            "                        and ivr.org_code = lrm.org_code\n" +
            "where lrm.his_patient_id in (\n" +
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
            "  and lrd.org_code = '11';")
    Integer getReportJoinInVisitJoinOutVisitJoinPatientLocationCount();
}
