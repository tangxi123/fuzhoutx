package org.cechc.etl.test.domain;

public enum IdentifierTypeDomain {
    SSN(0, "SSN"),
    EMPI(2, "MRN"),
    MRN(1, "EMPI"),
    DRIVERS_LICENSE(3, "DRIVERS_LICENSE"),
    PATIENT_ID_SECONDARY(4, "PATIENT_ID_SECONDARY"),
    OTHER(5, "OTHER"),
    NPI(6, "NPI"),
    STATE_LICENSE_NUM(7, "STATE_LICENSE_NUM"),
    TIN(8, "TIN"),
    CUSTOM(9, "CUSTOM"),
    UPIN(10, "UPIN"),
    MEDICARE_NUM(11, "MEDICARE_NUM"),
    ID_CARD(12, "身份证"),
    DRIVER_CARD(13, "驾驶证"),
    EMPI2(14, "EMPI"),
    JUNGUAN_CARD(15, "军官证"),
    YIBAO_CARD(16, "医保卡"),
    GANGAO_CARD(17, "港澳居民来往内地通行证"),
    TAIWAN_CARD(18, "台湾居民来往内地通行证"),
    IC_CARD(19, "IC卡"),
    JUMIN_CARD(20, "居民健康卡"),
    DIANZI_CARD(21, "电子健康卡"),
    HUKOU_CARD(22, "居民户口薄"),
    HUZHAO_CARD(23, "护照"),
    YILIAOBAOXIAN_CARD(24, "医疗保险"),
    YISHIZIGE_CARD(25, "医师资格证"),
    YUANGONG_CARD(26, "员工工作牌");

    public Integer getDomain_id() {
        return domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    private Integer domain_id;
    private String domain_name;

    private IdentifierTypeDomain(Integer domain_id, String domain_name) {
        this.domain_id = domain_id;
        this.domain_name = domain_name;
    }


}
