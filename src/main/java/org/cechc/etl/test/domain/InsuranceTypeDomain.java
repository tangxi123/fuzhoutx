package org.cechc.etl.test.domain;

public enum InsuranceTypeDomain {
    CHENGZHEN_ZHIGONG(0,"城镇职工基本医疗保险"),
    CHENGZHEN_JUMIN(1,"城镇居民医疗保险"),
    XINNONGCUN(2,"新农村合作医疗");



    public Integer getDomain_id() {
        return domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    private Integer domain_id;
    private String domain_name;
    private InsuranceTypeDomain(Integer domain_id, String domain_name){
        this.domain_id = domain_id;
        this.domain_name = domain_name;
    }

}
