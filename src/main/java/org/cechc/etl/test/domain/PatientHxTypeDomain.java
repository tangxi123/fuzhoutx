package org.cechc.etl.test.domain;

public enum PatientHxTypeDomain {
    MEDICAL   (0,"MEDICAL"),

    SURGICAL  (1,"SURGICAL"),

    SOCIAL    (2,"SOCIAL"),

    DRUG      (3,"DRUG"),

    FAMILY    (4,"FAMILY") ,
    BINGSHI   (5,"病史");

    public Integer getDomain_id() {
        return domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    private Integer domain_id;
    private String domain_name;

    private PatientHxTypeDomain(Integer domain_id, String domain_name) {
        this.domain_id = domain_id;

    }


}
