package org.cechc.etl.test.odsmapper.count.allCount;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ODSEncounterAllCountMapper {

    /**
     * 查询OutpVisit条数
     * @return
     */
    @Select("SELECT COUNT(*) as total\n" +
            "FROM outp_visit_record;")
    Integer getOutpVisitCount();

    @Select("select count(*) as total\n" +
            "from outp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getOutVisitJoinPatientBasicCount();

    @Select("select count(*) as total\n" +
            "from outp_visit_record ovr\n" +
            "         inner join case_base_info cbi\n" +
            "             on ovr.patient_id = cbi.patient_id\n" +
            "                    and ovr.org_code = cbi.org_code\n" +
            "    where ovr.org_code = '11';")
    Integer getOutVisitJoinCaseBaseCount();

    @Select("select count(*) as total\n" +
            "from outp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "             on ovr.patient_id = pbi.patient_id\n" +
            "                and ovr.org_code = pbi.org_code\n" +
            "         inner join case_base_info cbi\n" +
            "             on ovr.patient_id = cbi.patient_id\n" +
            "                    and ovr.org_code = cbi.org_code\n" +
            "    where ovr.org_code = '11';")
    Integer getOutVisitJoinPatientBasicJoinCaseBaseCount();

    /**
     * 查询inpVisit条数
     * @return
     */
    @Select("SELECT COUNT(*) as total\n" +
            "FROM inp_visit_record;")
    Integer getinpVisitCount();

    @Select("select count(*) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinPatientBasicCount();

    @Select("select count(*) as toatl\n" +
            "from inp_visit_record ovr\n" +
            "         inner join case_base_info cbi\n" +
            "                    on ovr.patient_id = cbi.patient_id\n" +
            "                        and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinCaseBaseCount();

    @Select("select count(*) as total\n" +
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
     * 查询caseVisit条数
     * @return
     */
    @Select("SELECT COUNT(*) as total\n" +
            "FROM case_residence_record;")
    Integer getCaseResidenceCount();

    /**
     * 查询OutpVisit的provider条数
     * @return
     */
    @Select("select \n" +
            " count(case when cure_doctor_code != '' then 1 else null) +\n" +
            " count(case when register_doctor_code != '' then 1 else null) as total\n" +
            "from outp_visit_recor\n")
    Integer getOutpVisitProCount();

    @Select("select count(case when cure_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when register_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "             on ovr.patient_id = pbi.patient_id\n" +
            "                    and ovr.org_code = pbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getOutVisitJoinPatientBasicProviderCount();

    @Select("select count(case when cure_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when register_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record ovr\n" +
            "         inner join case_base_info cbi\n" +
            "             on ovr.patient_id = cbi.patient_id\n" +
            "                    and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getOutVisitJoinCaseBaseProviderCount();

    @Select("select count(case when cure_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when register_doctor_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "         inner join case_base_info cbi\n" +
            "                    on ovr.patient_id = cbi.patient_id\n" +
            "                        and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getOutVisitJoinPatientBasicJoinCaseBaseProviderCount();

    /**
     * 查询inpVisit的provider条数
     * @return
     */
    @Select("select \n" +
            "count(case when outp_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when now_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when upper_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when now_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record\n")
    Integer getinpVisitProCount();

    @Select("select count(case when outp_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when now_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when upper_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when now_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinPatientBasicProviderCount();

    @Select("select count(case when outp_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when now_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when upper_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when now_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join case_base_info cbi\n" +
            "             on ovr.patient_id = cbi.patient_id\n" +
            "                    and ovr.org_code = cbi.org_code\n" +
            "    where ovr.org_code = '11';")
    Integer getInVisitJoinCaseBaseProviderCount();

    @Select("select count(case when outp_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when admission_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when now_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when upper_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when now_nurse_code != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "         inner join case_base_info cbi\n" +
            "                    on ovr.patient_id = cbi.patient_id\n" +
            "                        and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinPatientBasicJoinCaseBaseProviderCount();

    /**
     * 查询caseVisit的provider条数
     * @return
     */
    @Select("select \n" +
            "count(case when dept_leader_code != '' then 1 else null end) +\n" +
            "       count(case when chief_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when charge_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when residence_doctor_codecode != '' then 1 else null end) +\n" +
            "       count(case when duty_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when study_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when practice_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when quality_doctor_code != '' then 1 else null end) +\n" +
            "       count(case when quality_nurse_code != '' then 1 else null end) +\n" +
            "       count(case when coder_code != '' then 1 else null end) as total\n" +
            "from case_residence_record\n")
    Integer getCaseResidenceProCount();


    /**
     * 查询OutpVisit的location条数
     * @return
     */
    @Select("select \n" +
            "count(case when cure_dept_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record\n")
    Integer getOutpVisitLocCount();

    @Select("select count(case when cure_dept_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getOutVisitJoinPatientBasicLocationCount();

    @Select("select count(case when cure_dept_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record ovr\n" +
            "         inner join case_base_info cbi\n" +
            "                    on ovr.patient_id = cbi.patient_id\n" +
            "                        and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getOutVisitJoinCaseBaseLocationCount();

    @Select("select count(case when cure_dept_code != '' then 1 else null end) as total\n" +
            "from outp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "                    on ovr.patient_id = pbi.patient_id\n" +
            "                        and ovr.org_code = pbi.org_code\n" +
            "         inner join case_base_info cbi\n" +
            "                    on ovr.patient_id = cbi.patient_id\n" +
            "                        and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getOutVisitJoinPatientBasicJoinCaseBaseLocationCount();



    /**
     * 查询inpVisit的location条数
     * @return
     */
    @Select("select \n" +
            "count(case when in_dept_code != '' then 1 else null end) +\n" +
            "       count(case when admission_dept_code != '' then 1 else null end) +\n" +
            "       count(case when nurse_dept_code != '' then 1 else null end) +\n" +
            "       count(case when ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) as total\n" +
            "from inp_visit_record\n")
    Integer getinpVisitLocCount();

    @Select("select count(case when in_dept_code != '' then 1 else null end) +\n" +
            "       count(case when admission_dept_code != '' then 1 else null end) +\n" +
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

    @Select("select\n" +
            "count(case when in_dept_code != '' then 1 else null end)+\n" +
            "       count(case when admission_dept_code != '' then 1 else null end) +\n" +
            "       count(case when nurse_dept_code != '' then 1 else null end) +\n" +
            "       count(case when ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join case_base_info cbi\n" +
            "             on ovr.patient_id = cbi.patient_id\n" +
            "                    and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code='11';")
    Integer getInVisitJoinCaseBaseLocationCount();

    @Select("select count(case when in_dept_code != '' then 1 else null end) +\n" +
            "       count(case when admission_dept_code != '' then 1 else null end) +\n" +
            "       count(case when nurse_dept_code != '' then 1 else null end) +\n" +
            "       count(case when ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) as total\n" +
            "from inp_visit_record ovr\n" +
            "         inner join patient_basic_info pbi\n" +
            "             on ovr.patient_id = pbi.patient_id\n" +
            "                and ovr.org_code = pbi.org_code\n" +
            "         inner join case_base_info cbi\n" +
            "             on ovr.patient_id = cbi.patient_id\n" +
            "                    and ovr.org_code = cbi.org_code\n" +
            "where ovr.org_code = '11';")
    Integer getInVisitJoinPatientBasicJoinCaseBaseLocationCount();

    /**
     * 查询caseVisit的location条数
     * @return
     */
    @Select("select \n" +
            "count(case when admission_dept_code != '' then 1 else null end) +\n" +
            "       count(case when admission_ward_code != '' then 1 else null end) +\n" +
            "       count(case when room_no != '' then 1 else null end) +\n" +
            "       count(case when bed_no != '' then 1 else null end) +\n" +
            "       count(case when transfer_dept_code != '' then 1 else null end) +\n" +
            "       count(case when discharge_dept_code != '' then 1 else null end) +\n" +
            "       count(case when discharge_ward_code != '' then 1 else null end) as total\n" +
            "from case_residence_record\n")
    Integer getCaseResidenceLocCount();


}
