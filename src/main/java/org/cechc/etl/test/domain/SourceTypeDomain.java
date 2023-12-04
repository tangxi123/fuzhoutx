package org.cechc.etl.test.domain;

public enum SourceTypeDomain {
    EMR_DATABASE(0, "EMR_DATABASE"),
    EDW(1, "EDW"),
    HL7(2, "HL7"),
    S837(3, "837"),
    CCD(4, "CCD"),
    HIE(5, "HIE"),
    CLAIMS_DATABASE(6, "CLAIMS_DATABASE"),
    OTHER(7, "OTHE"),
    HIS(8, "医院His系统"),
    LIS(9, "医院LIS系统"),
    PACS(10, "医院PACS系统");

    public Integer getDomain_id() {
        return domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    private Integer domain_id;
    private String domain_name;

    private SourceTypeDomain(Integer domain_id, String domain_name) {
        this.domain_id = domain_id;

    }
}





