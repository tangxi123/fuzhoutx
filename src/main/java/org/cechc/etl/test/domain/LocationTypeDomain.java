package org.cechc.etl.test.domain;

public enum LocationTypeDomain {
    HOSPITAL(0, "HOSPITAL"),
    CLINIC(1, "CLINIC"),
    STANDALONE_ER(2, "STANDALONE_ER"),
    DEPARTMENT(3, "DEPARTMENT"),
    FLOOR(4, "FLOOR"),
    WING(5, "WING"),
    ROOM(6, "ROOM"),
    BED(7, "BED"),
    BEHAVIORAL_HEALTH_CLINIC(8, "BEHAVIORAL_HEALTH_CLINIC"),
    SCHOOL_CLINIC(9, "SCHOOL_CLINIC"),
    OTHER(11, "OTHER"),
    YIYUAN(11, "医院"),
    KESHI(12, "科室"),
    BINGQU(13, "病区"),
    FANGJIAN(14, "房间"),
    CHUANGWEI(15, "床位");


    public Integer getDomain_id() {
        return domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    private Integer domain_id;
    private String domain_name;

    private LocationTypeDomain(Integer domain_id, String domain_name) {
        this.domain_id = domain_id;

    }
}