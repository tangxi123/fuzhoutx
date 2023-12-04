package org.cechc.etl.test.domain;

public enum PatientContactMethodDomain {
    PHONE(0, "PHONE"),
    EMAIL(1, "EMAIL"),
    MAIL(2, "MAIL"),
    NONE(3, "NONE"),
    OTHER(4, "OTHE"),
    WORK_PHONE(5, "工作单位电话"),
    PATIENT_PHONE(6, "患者电话"),
    CONTACT_PHONE(7, "联系人电话");


    public Integer getDomain_id() {
        return domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    private Integer domain_id;
    private String domain_name;

    private PatientContactMethodDomain(Integer domain_id, String domain_name) {
        this.domain_id = domain_id;

    }
}



