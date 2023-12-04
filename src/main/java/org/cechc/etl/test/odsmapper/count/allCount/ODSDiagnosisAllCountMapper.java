package org.cechc.etl.test.odsmapper.count.allCount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ODSDiagnosisAllCountMapper {
    /**
     * 获取outp_diagnosis_record条数
     * @return
     */
    @Select("SELECT COUNT(*) as total\n" +
            "FROM outp_diagnosis_record;")
    Integer getOutpDiagnosisCount();

    @Select("select count(*) as total\n" +
            "from outp_diagnosis_record odr\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on odr.org_code = ovr.org_code\n" +
            "                        and odr.outp_visit_no = ovr.outp_visit_no\n" +
            "                        and odr.patient_id = ovr.patient_id\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.org_code = ovr.org_code\n" +
            "                        and pbi.patient_id = ovr.patient_id\n" +
            "where odr.org_code = '11';")
    Integer getOutDiagnosisJoinPatientBasicCount();

    @Select("select count(*) as total\n" +
            "from outp_diagnosis_record odr\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on odr.org_code = ovr.org_code\n" +
            "                        and odr.outp_visit_no = ovr.outp_visit_no\n" +
            "                        and odr.patient_id = ovr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.org_code = ovr.org_code\n" +
            "                        and cbi.patient_id = ovr.patient_id\n" +
            "where odr.org_code = '11';")
    Integer getOutDiagnosisJoinCaseBaseCount();

    @Select("select count(*) as total\n" +
            "from outp_diagnosis_record odr\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on odr.org_code = ovr.org_code\n" +
            "                        and odr.outp_visit_no = ovr.outp_visit_no\n" +
            "                        and odr.patient_id = ovr.patient_id\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.org_code = ovr.org_code\n" +
            "                        and pbi.patient_id = ovr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.org_code = ovr.org_code\n" +
            "                        and cbi.patient_id = ovr.patient_id\n" +
            "where odr.org_code = '11';")
    Integer getOutDiagnosisJoinPatientBasicJoinCaseBaseCount();


    /**
     * 获取inp_diagnosis_record条数
     * @return
     */
    @Select("SELECT COUNT(*)\n" +
            "FROM inp_diagnosis_record;")
    Integer getInpDiagnosisCount();

    /**
     * 获取case_diagnosis_record条数
     * @return
     */
    @Select("SELECT COUNT(*) as total\n" +
            "FROM case_diagnosis_record;\n")
    Integer getCaseDiagnosisCount();


    @Select("select count(*) as total\n" +
            "from case_diagnosis_record cdr\n" +
            "         left join case_operation_record cor\n" +
            "                   on cdr.org_code = cor.org_code\n" +
            "                       and cdr.case_no = cor.case_no\n" +
            "                       and cdr.inp_visit_no = cor.inp_visit_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on cdr.inp_visit_no = ivr.inp_visit_no\n" +
            "                        and cdr.org_code = ivr.org_code\n" +
            "                        and cdr.patient_id = ivr.patient_id\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.patient_id = cdr.patient_id\n" +
            "                        and pbi.org_code = ivr.org_code\n" +
            "where cdr.org_code = '11';\n")
    Integer getCaseDiagnosisJoinPatientBasicCount();


    @Select("select count(*) as total\n" +
            "from case_diagnosis_record cdr\n" +
            "         left join case_operation_record cor\n" +
            "                   on cdr.org_code = cor.org_code\n" +
            "                       and cdr.case_no = cor.case_no\n" +
            "                       and cdr.inp_visit_no = cor.inp_visit_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on cdr.inp_visit_no = ivr.inp_visit_no\n" +
            "                        and cdr.org_code = ivr.org_code\n" +
            "                        and cdr.patient_id = ivr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.patient_id = cdr.patient_id\n" +
            "                        and cbi.org_code = ivr.org_code\n" +
            "where cdr.org_code = '11';")
    Integer getCaseDiagnosisJoinCaseBaseCount();


    @Select("select count(*) as total\n" +
            "from case_diagnosis_record cdr\n" +
            "         left join case_operation_record cor\n" +
            "                   on cdr.org_code = cor.org_code\n" +
            "                       and cdr.case_no = cor.case_no\n" +
            "                       and cdr.inp_visit_no = cor.inp_visit_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on cdr.inp_visit_no = ivr.inp_visit_no\n" +
            "                        and cdr.org_code = ivr.org_code\n" +
            "                        and cdr.patient_id = ivr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.patient_id = cdr.patient_id\n" +
            "                        and cbi.org_code = ivr.org_code\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.patient_id = cdr.patient_id\n" +
            "                        and pbi.org_code = ivr.org_code\n" +
            "where cdr.org_code = '11';")
    Integer getCaseDiagnosisJoinPatientBasicJoinCaseBaseCount();

    /**
     * 获取outp_diagnosis_record与location关联条数
     * @return
     */
    @Select("select \n" +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from outp_diagnosis_record\n")
    Integer getOutpDiagnosisLocCount();

    @Select("select \n" +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from outp_diagnosis_record odr\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on odr.org_code = ovr.org_code\n" +
            "                        and odr.outp_visit_no = ovr.outp_visit_no\n" +
            "                        and odr.patient_id = ovr.patient_id\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.org_code = ovr.org_code\n" +
            "                        and pbi.patient_id = ovr.patient_id\n" +
            "where odr.org_code = '11';")
    Integer getOutDiagnosisJoinPatientBasicLocationCount();

    @Select("select " +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from outp_diagnosis_record odr\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on odr.org_code = ovr.org_code\n" +
            "                        and odr.outp_visit_no = ovr.outp_visit_no\n" +
            "                        and odr.patient_id = ovr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.org_code = ovr.org_code\n" +
            "                        and cbi.patient_id = ovr.patient_id\n" +
            "where odr.org_code = '11';")
    Integer getOutDiagnosisJoinCaseBaseLocationCount();

    @Select("select " +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from outp_diagnosis_record odr\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on odr.org_code = ovr.org_code\n" +
            "                        and odr.outp_visit_no = ovr.outp_visit_no\n" +
            "                        and odr.patient_id = ovr.patient_id\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.org_code = ovr.org_code\n" +
            "                        and pbi.patient_id = ovr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.org_code = ovr.org_code\n" +
            "                        and cbi.patient_id = ovr.patient_id\n" +
            "where odr.org_code = '11';")
    Integer getOutDiagnosisJoinPatientBasicJoinCaseBaseLocationCount();


    /**
     * 获取inp_diagnosis_record与location关联条数
     * @return
     */
    @Select("select \n" +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from inp_diagnosis_record\n")
    Integer getInpDiagnosisLocCount();
    /**
     * 获取case_diagnosis_record与location关联条数
     * @return
     */
    @Select("select \n" +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from case_diagnosis_record\n")
    Integer getCaseDiagnosisLocCount();

    @Select("select count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from case_diagnosis_record cdr\n" +
            "         left join case_operation_record cor\n" +
            "                   on cdr.org_code = cor.org_code\n" +
            "                       and cdr.case_no = cor.case_no\n" +
            "                       and cdr.inp_visit_no = cor.inp_visit_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on cdr.inp_visit_no = ivr.inp_visit_no\n" +
            "                        and cdr.org_code = ivr.org_code\n" +
            "                        and cdr.patient_id = ivr.patient_id\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.patient_id = cdr.patient_id\n" +
            "                        and pbi.org_code = ivr.org_code\n" +
            "where cdr.org_code = '11';")
    Integer getCaseDiagnosisJoinPatientBasicLocationCount();


    @Select("select " +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from case_diagnosis_record cdr\n" +
            "         left join case_operation_record cor\n" +
            "                   on cdr.org_code = cor.org_code\n" +
            "                       and cdr.case_no = cor.case_no\n" +
            "                       and cdr.inp_visit_no = cor.inp_visit_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on cdr.inp_visit_no = ivr.inp_visit_no\n" +
            "                        and cdr.org_code = ivr.org_code\n" +
            "                        and cdr.patient_id = ivr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.patient_id = cdr.patient_id\n" +
            "                        and cbi.org_code = ivr.org_code\n" +
            "where cdr.org_code = '11';")
    Integer getCaseDiagnosisJoinCaseBaseLocationCount();


    @Select("select " +
            "count(case when diagnosis_dept_code != '' then 1 else null end) as total\n" +
            "from case_diagnosis_record cdr\n" +
            "         left join case_operation_record cor\n" +
            "                   on cdr.org_code = cor.org_code\n" +
            "                       and cdr.case_no = cor.case_no\n" +
            "                       and cdr.inp_visit_no = cor.inp_visit_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on cdr.inp_visit_no = ivr.inp_visit_no\n" +
            "                        and cdr.org_code = ivr.org_code\n" +
            "                        and cdr.patient_id = ivr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.patient_id = cdr.patient_id\n" +
            "                        and cbi.org_code = ivr.org_code\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.patient_id = cdr.patient_id\n" +
            "                        and pbi.org_code = ivr.org_code\n" +
            "where cdr.org_code = '11';")
    Integer getCaseDiagnosisJoinPatientBasicJoinCaseBaseLocationCount();

    /**
     * 获取outp_diagnosis_record与provider关联条数
     * @return
     */
    @Select("select \n" +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_diagnosis_record\n")
    Integer getOutpDiagnosisProCount();

    @Select("select " +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_diagnosis_record odr\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on odr.org_code = ovr.org_code\n" +
            "                        and odr.outp_visit_no = ovr.outp_visit_no\n" +
            "                        and odr.patient_id = ovr.patient_id\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.org_code = ovr.org_code\n" +
            "                        and pbi.patient_id = ovr.patient_id\n" +
            "where odr.org_code = '11';")
    Integer getOutDiagnosisJoinPatientBasicProviderCount();

    @Select("select " +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_diagnosis_record odr\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on odr.org_code = ovr.org_code\n" +
            "                        and odr.outp_visit_no = ovr.outp_visit_no\n" +
            "                        and odr.patient_id = ovr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.org_code = ovr.org_code\n" +
            "                        and cbi.patient_id = ovr.patient_id\n" +
            "where odr.org_code = '11';")
    Integer getOutDiagnosisJoinCaseBaseProviderCount();

    @Select("select " +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_diagnosis_record odr\n" +
            "         inner join outp_visit_record ovr\n" +
            "                    on odr.org_code = ovr.org_code\n" +
            "                        and odr.outp_visit_no = ovr.outp_visit_no\n" +
            "                        and odr.patient_id = ovr.patient_id\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.org_code = ovr.org_code\n" +
            "                        and pbi.patient_id = ovr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.org_code = ovr.org_code\n" +
            "                        and cbi.patient_id = ovr.patient_id\n" +
            "where odr.org_code = '11';")
    Integer getOutDiagnosisJoinPatientBasicJoinCaseBaseProviderCount();

    /**
     * 获取inp_diagnosis_record与provider关联条数
     * @return
     */
    @Select("select \n" +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from inp_diagnosis_record\n" )
    Integer getInpDiagnosisProCount();
    /**
     * 获取case_diagnosis_record与provider关联条数
     * @return
     */
    @Select("select \n" +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from case_diagnosis_record\n")
    Integer getCaseDiagnosisProCount();

    @Select("select " +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from case_diagnosis_record cdr\n" +
            "         left join case_operation_record cor\n" +
            "                   on cdr.org_code = cor.org_code\n" +
            "                       and cdr.case_no = cor.case_no\n" +
            "                       and cdr.inp_visit_no = cor.inp_visit_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on cdr.inp_visit_no = ivr.inp_visit_no\n" +
            "                        and cdr.org_code = ivr.org_code\n" +
            "                        and cdr.patient_id = ivr.patient_id\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.patient_id = cdr.patient_id\n" +
            "                        and pbi.org_code = ivr.org_code\n" +
            "where cdr.org_code = '11';\n")
    Integer getCaseDiagnosisJoinPatientBasicProviderCount();


    @Select("select " +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from case_diagnosis_record cdr\n" +
            "         left join case_operation_record cor\n" +
            "                   on cdr.org_code = cor.org_code\n" +
            "                       and cdr.case_no = cor.case_no\n" +
            "                       and cdr.inp_visit_no = cor.inp_visit_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on cdr.inp_visit_no = ivr.inp_visit_no\n" +
            "                        and cdr.org_code = ivr.org_code\n" +
            "                        and cdr.patient_id = ivr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.patient_id = cdr.patient_id\n" +
            "                        and cbi.org_code = ivr.org_code\n" +
            "where cdr.org_code = '11';")
    Integer getCaseDiagnosisJoinCaseBaseProviderCount();


    @Select("select " +
            "count(case when diagnosis_doctor_code != '' then 1 else null end) as total\n" +
            "from case_diagnosis_record cdr\n" +
            "         left join case_operation_record cor\n" +
            "                   on cdr.org_code = cor.org_code\n" +
            "                       and cdr.case_no = cor.case_no\n" +
            "                       and cdr.inp_visit_no = cor.inp_visit_no\n" +
            "         inner join inp_visit_record ivr\n" +
            "                    on cdr.inp_visit_no = ivr.inp_visit_no\n" +
            "                        and cdr.org_code = ivr.org_code\n" +
            "                        and cdr.patient_id = ivr.patient_id\n" +
            "         inner join case_base_info cbi\n" +
            "                    on cbi.patient_id = cdr.patient_id\n" +
            "                        and cbi.org_code = ivr.org_code\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on pbi.patient_id = cdr.patient_id\n" +
            "                        and pbi.org_code = ivr.org_code\n" +
            "where cdr.org_code = '11';")
    Integer getCaseDiagnosisJoinPatientBasicJoinCaseBaseProviderCount();

}
