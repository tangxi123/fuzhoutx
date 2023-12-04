package org.cechc.etl.test.odsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ODSOrderAllCountMapper {
    /**
     outp_order_detail条数
     */
    @Select("SELECT COUNT(od.cecmid_id)\n" +
            "FROM outp_order_detail od\n" +
            "         inner join outp_order_master om\n" +
            "                    on od.order_no = om.order_no\n" +
            "                        and od.org_code = om.org_code;")
    Integer getOutpOrderCount();

    @Select("select count(*) as total\n" +
            "from outp_order_detail ood\n" +
            "         inner join outp_order_master oom\n" +
            "                    on ood.org_code = oom.org_code\n" +
            "                        and ood.order_no = oom.order_no\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on oom.org_code = ovr.org_code\n" +
            "                        and oom.patient_id = ovr.patient_id\n" +
            "                        and oom.outp_visit_no = ovr.outp_visit_no\n" +
            "where oom.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (select pbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where pbi.org_code = '11'\n" +
            "                union\n" +
            "                select cbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where cbi.org_code = '11'\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ood.org_code = '11';")
    Integer getOutJoinPatientCount();

    @Select("select count(*) as total\n" +
            "from inp_order_record ior\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on ior.org_code = ivr.org_code\n" +
            "                        and ior.patient_id = ivr.patient_id\n" +
            "                        and ior.inp_visit_no = ivr.inp_visit_no\n" +
            "where ior.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (select pbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where pbi.org_code = '11'\n" +
            "                union\n" +
            "                select cbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where cbi.org_code = '11'\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ior.org_code = '11';")
    Integer getInJoinPatientCount();

    @Select("select count(case when order_dept_code != '' then 1 else null end) +\n" +
            "       count(case when execute_dept_code != '' then 1 else null end) as total\n" +
            "from outp_order_detail ood\n" +
            "         inner join outp_order_master oom\n" +
            "                    on ood.org_code = oom.org_code\n" +
            "                        and ood.order_no = oom.order_no\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on oom.org_code = ovr.org_code\n" +
            "                        and oom.patient_id = ovr.patient_id\n" +
            "                        and oom.outp_visit_no = ovr.outp_visit_no\n" +
            "where oom.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (\n" +
            "                   select pbi.patient_id as patient_id\n" +
            "                   from patient_basic_info pbi\n" +
            "                            left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                   where pbi.org_code = '11'\n" +
            "                   union\n" +
            "                   select cbi.patient_id as patient_id\n" +
            "                   from patient_basic_info pbi\n" +
            "                            right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                   where cbi.org_code = '11'\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ood.org_code = '11';")
    Integer getOutJoinPatientLocationCount();

    @Select("select count(case when order_dept_code != '' then 1 else null end) +\n" +
            "       count(case when execute_dept_code != '' then 1 else null end) as total\n" +
            "from inp_order_record ior\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on ior.org_code = ivr.org_code\n" +
            "                        and ior.patient_id = ivr.patient_id\n" +
            "                        and ior.inp_visit_no = ivr.inp_visit_no\n" +
            "where ivr.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (select pbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where pbi.org_code = '11'\n" +
            "                union\n" +
            "                select cbi.patient_id as patient_id\n" +
            "                from patient_basic_info pbi\n" +
            "                         right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                where cbi.org_code = '11'\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ior.org_code = '11';")
    Integer getInJoinPatientLocationCount();

    @Select("select count(case when check_operator_code != '' then 1 else null end) +\n" +
            "       count(case when execute_operator_code != '' then 1 else null end) +\n" +
            "       count(case when order_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_order_detail ood\n" +
            "         inner join outp_order_master oom\n" +
            "                    on ood.org_code = oom.org_code\n" +
            "                        and ood.order_no = oom.order_no\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on oom.org_code = ovr.org_code\n" +
            "                        and oom.patient_id = ovr.patient_id\n" +
            "                        and oom.outp_visit_no = ovr.outp_visit_no\n" +
            "where oom.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (\n" +
            "                   select pbi.patient_id as patient_id\n" +
            "                   from patient_basic_info pbi\n" +
            "                            left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                   where pbi.org_code = '11'\n" +
            "                   union\n" +
            "                   select cbi.patient_id as patient_id\n" +
            "                   from patient_basic_info pbi\n" +
            "                            right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                   where cbi.org_code = '11'\n" +
            "               ) t1\n" +
            "      )\n" +
            "  and ood.org_code = '11';")
    Integer getOutJoinPatientProviderCount();

    @Select("select count(case when input_operator_code != '' then 1 else null end) +\n" +
            "       count(case when order_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when stop_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when check_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_order_record ior\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on ior.org_code = ivr.org_code\n" +
            "                        and ior.patient_id = ivr.patient_id\n" +
            "                        and ior.inp_visit_no = ivr.inp_visit_no\n" +
            "where ivr.patient_id in\n" +
            "      (\n" +
            "          select t1.patient_id\n" +
            "          from (\n" +
            "                   select pbi.patient_id as patient_id\n" +
            "                   from patient_basic_info pbi\n" +
            "                            left join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                   where pbi.org_code = '11'\n" +
            "                   union\n" +
            "                   select cbi.patient_id as patient_id\n" +
            "                   from patient_basic_info pbi\n" +
            "                            right join case_base_info cbi on pbi.patient_id = cbi.patient_id\n" +
            "                   where cbi.org_code = '11'\n" +
            "               ) t1\n" +
            "      )\n" +
            "\n" +
            "  and ior.org_code = '11';")
    Integer getInJoinPatientProviderCount();

    /**
     outp_order_detail provider条数
     */
    @Select("select \n" +
            "count(case when check_operator_code != '' then 1 else null end) +\n" +
            "       count(case when execute_operator_code != '' then 1 else null end) +\n" +
            "       count(case when order_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_order_master oom\n" +
            "         left join outp_order_detail ood on\n" +
            "    oom.org_code = ood.org_code and oom.order_no = ood.order_no\n")
    Integer getOutpOrderProCount();

    /**
     outp_order_detail location条数
     */
    @Select("select \n" +
            "count(case when order_dept_code != '' then 1 else null end) +\n" +
            "       count(case when execute_dept_code != '' then 1 else null end) as total\n" +
            "from outp_order_master oom\n" +
            "         left join outp_order_detail ood on\n" +
            "    oom.org_code = ood.org_code and oom.order_no = ood.order_no\n")
    Integer getOutpOrderLocCount();

    /**
     inp_order_record条数
     */
    @Select("SELECT COUNT(*)\n" +
            "FROM inp_order_record;")
    Integer getInpOrderCount();

    /**
     inp_order_record provider条数
     */
    @Select("select \n" +
            "count(case when input_operator_code != '' then 1 else null end) +\n" +
            "       count(case when order_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when stop_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when check_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_order_record\n")
    Integer getInpOrderProCount();

    /**
     inp_order_record location条数
     */
    @Select("select \n" +
            "count(case when order_dept_code != '' then 1 else null end) +\n" +
            "       count(case when execute_dept_code != '' then 1 else null end) as total\n" +
            "from inp_order_record\n")
    Integer getInpOrderLocCount();
}

