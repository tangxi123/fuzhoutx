package org.cechc.etl.test.odsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ODSAdmissionAllCountMapper {
    /**
     * 根据inp_visit_record查询admission的条数
     * @return
     */
    @Select("SELECT COUNT(*) as total\n" +
            "FROM inp_visit_record;")
    Integer getInpVisitCount();

    @Select("select count(ovr.inp_visit_no) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "where ovr.org_code = '11';\n")
    Integer getInVisitJoinPatientBasicCount();

    @Select("select count(ovr.inp_visit_no) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join case_base_info cbi\n" +
            "             on ovr.patient_id = cbi.patient_id\n" +
            "                    and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinCaseBaseCount();

    @Select("select count(ovr.inp_visit_no) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "         inner join case_base_info cbi\n" +
            "                    on ovr.patient_id = cbi.patient_id\n" +
            "                        and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinPatientBasicJoinCaseBaseCount();

    /**
     * 根据case_residence_record查询admission的条数
     * @return
     */
    @Select("SELECT COUNT(*) as total\n" +
            "FROM case_residence_record;")
    Integer getCaseResidenceCount();

    /**
     * 根据inp_visit_record查询admission与provider相关的总条数
     * @return
     */
    @Select("select \n" +
            "count(case when admission_doctor_code != '' then 1 else null end)+\n"+
            "count(case when outp_doctor_code != '' then 1 else null end) +\n" +
            "count(case when admission_nurse_code != '' then 1 else null end) +\n" +
            "count(case when now_doctor_code != '' then 1 else null end) +\n" +
            "count(case when upper_doctor_code != '' then 1 else null end) +\n" +
            "count(case when now_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record\n")
    Integer getInpVisitProCount();

    @Select("select count(case when admission_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when outp_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when now_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when upper_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when now_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "             on ovr.patient_id = pbi.patient_id\n" +
            "                    and ovr.org_code = pbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinPatientBasicProviderCount();

    @Select("select count(case when admission_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when outp_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when now_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when upper_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when now_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join case_base_info cbi\n" +
            "                    on ovr.patient_id = cbi.patient_id\n" +
            "                        and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinCaseBaseProviderCount();

    @Select("select count(case when admission_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when outp_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when now_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when upper_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when now_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "             on ovr.patient_id = pbi.patient_id\n" +
            "                and ovr.org_code = pbi.org_code\n" +
            "         inner join case_base_info cbi\n" +
            "             on ovr.patient_id = cbi.patient_id\n" +
            "                    and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinPatientBasicJoinCaseBaseProviderCount();


    /**
     * 根据case_residence_record查询admission与provider相关的总条数
     * @return
     */
    @Select( "select \n" +
            "count(case when dept_leader_code != '' then 1 else null end) +\n" +
            "       count(case when chief_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when charge_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when residence_doctor_codecode != '' then 1 else null end) +\n" +
            "       count(case when duty_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when study_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when practice_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when coder_code != '' then 1 else null end) +\n" +
            "       count(case when quality_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when quality_nurse_code != '' then 1 else null end) as total\n" +
            "from case_residence_record\n")
    Integer getCaseResidenceProCount();

    /**
     * 根据inp_visit_record查询admission与location相关的总条数
     * @return
     */
    @Select( "select \n" +
            "count(case when admission_dept_code != '' then 1 else null end)+\n"+
            "count(case when in_dept_code != '' then 1 else null end)) +\n" +
            "count(case when nurse_dept_code != ''then 1 else null end)) +\n" +
            "count(case when ward_code != ''then 1 else null end)) +\n" +
            "count(case when room_no != '' then 1 else null end) +\n" +
            "count(case when bed_no != '' then 1 else null end) as total\n" +
            "from inp_visit_record\n")
    Integer getInpVisitLocCount();

    @Select("select count(case when admission_dept_code != '' then 1 else null end) +\n" +
            "       count(case when in_dept_code != '' then 1 else null end) +\n" +
            "       count(case when nurse_dept_code != '' then 1 else null end) +\n" +
            "       count(case when ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinPatientBasicLocationCount();

    @Select("select " +
            "count(case when admission_dept_code != '' then 1 else null end)+\n"+
            "count(case when in_dept_code != '' then 1 else null end)) +\n" +
            "count(case when nurse_dept_code != ''then 1 else null end)) +\n" +
            "count(case when ward_code != ''then 1 else null end)) +\n" +
            "count(case when room_no != '' then 1 else null end) +\n" +
            "count(case when bed_no != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join case_base_info cbi on ovr.patient_id = cbi.patient_id\n" +
            "and ovr.org_code='11';")
    Integer getInVisitJoinCaseBaseLocationCount();

    @Select("select count(case when admission_dept_code != '' then 1 else null end) +\n" +
            "       count(case when in_dept_code != '' then 1 else null end) +\n" +
            "       count(case when nurse_dept_code != '' then 1 else null end) +\n" +
            "       count(case when ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "         inner join case_base_info cbi\n" +
            "                    on ovr.patient_id = cbi.patient_id\n" +
            "                        and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinPatientBasicJoinCaseBaseLocationCount();

    /**
     * 根据case_residence_record查询admission与location相关的总条数
     * @return
     */
    @Select("select \n" +
            "count(case when admission_dept_code != '' then 1 else null end) +\n" +
            "       count(case when admission_ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) +\n" +
            "       count(case when transfer_dept_code != '' then 1 else null end) +\n" +
            "       count(case when discharge_dept_code != '' then 1 else null end) +\n" +
            "       count(case when discharge_ward_code != '' then 1 else null end)\n" +
            "           as total\n" +
            "from case_residence_record")
    Integer getCaseResidenceLocCount();

}
