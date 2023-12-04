package org.cechc.etl.test.domain;

public enum AddressTypeDomain {
    HOME      (0,"HOME"),
    WORK      (1,"WORK"),
    ALTERNATE (2,"ALTERNATE"),
    OTHER     (3,"OTHER" ),
    BIRTH_Address(4,"出生地址"),
    HUJI_Address(5,"户籍地址"),
    NOW_Address(6,"现住址"),
    WORK_Address(7,"工作地址"),
    CONTACT_Address(8,"联系人地址"),
    Home_Address(9,"家庭地址");

    private Integer domain_id;
    private String domain_name;
    private AddressTypeDomain(Integer domain_id,String domain_name){
        this.domain_id = domain_id;
        this.domain_name = domain_name;
    }

    public Integer getDomain_id(){
        return domain_id;
    }

    public String getDomain_name(){
        return domain_name;
    }








}
