package org.cechc.etl.test.domain;

public enum CodesetDomain {
    ICD9(0,"ICD-9"),
    ICD10(1,"ICD-10");

    public Integer getDomain_id() {
        return domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    private Integer domain_id;
    private String domain_name;
    private CodesetDomain(Integer domain_id, String domain_name){
        this.domain_id = domain_id;
        this.domain_name = domain_name;
    }


}
