package org.cechc.etl.test.domain;

public enum ProviderRoleDomain {
    APPROVING_PROVIDER(0, "APPROVING_PROVIDER"),
    ENTERING_PROVIDER(1, "ENTERING_PROVIDER"),
    SIGNING_PROVIDER(2, "SIGNING_PROVIDER"),
    AUTHORIZING_PROVIDER(3, "AUTHORIZING_PROVIDER"),
    SUPERVISING_PROVIDER(4, "SUPERVISING_PROVIDER"),
    ADMITTING_PROVIDER(5, "ADMITTING_PROVIDER"),
    ATTENDING_PROVIDER(6, "ATTENDING_PROVIDER"),
    BILLING_PROVIDER(7, "BILLING_PROVIDER"),
    CONSULTING_PROVIDER(8, "CONSULTING_PROVIDER"),
    EMERGENCY_PROVIDER(9, "EMERGENCY_PROVIDER"),
    FAMILY_PROVIDER(10, "FAMILY_PROVIDER"),
    OBSERVING_PROVIDER(11, "OBSERVING_PROVIDER"),
    ORDERING_PROVIDER(12, "ORDERING_PROVIDER"),
    OTHER_PROVIDER(13, "OTHER_PROVIDER"),
    PERFORMING_PROVIDER(14, "PERFORMING_PROVIDER"),
    PRESCRIBING_PROVIDER(15, "PRESCRIBING_PROVIDER"),
    PRIMARYCARE_PROVIDER(16, "PRIMARYCARE_PROVIDER"),
    REFERRING_PROVIDER(17, "REFERRING_PROVIDER"),
    RENDERING_PROVIDER(18, "RENDERING_PROVIDER"),
    RESOURCE(19, "RESOURCE"),
    KESHI_ZHUREN(20, "科室主任"),
    ZHUREN_YISHENG(21, "主任医生"),
    ZHUZHI_YISHENG(22, "主治医生"),
    ZHUYUAN_YISHENG(23, "住院医生"),
    ZEREN_HUSHI(24, "责任护士"),
    JINXIU_YISHI(25, "进修医师"),
    SHIXI_YISHI(26, "实习医师"),
    BIANMA_YUAN(27, "编码员"),
    ZHIKONG_YISHI(28, "质控医师"),
    ZHIKONG_HUSHI(29, "质控护士"),
    RUZHEN_YISHENG(30, "门诊医生"),
    RUKE_YISHENG(31, "入科医生"),
    RUKE_HUSHI(32, "入科护士"),
    NOW_ZHUGUAN_YISHENG(33, "当前主管医生"),
    SHANGJI_YISHENG(34, "上级医生"),
    NOW_ZHUGUAN_HUSHI(35, "当前主管护士"),
    ZHENDUAN_YISHENG(36, "诊断医生"),
    XIAZHEN_YISHENG(37, "下诊医生"),
    GUAHAO_YISHENG(38, "挂号医生"),
    JIEZHEN_YISHENG(39, "接诊医生"),
    LURU_REN(40, "录入人"),
    JIAODUI_HUSHI(41, "校对护士"),
    JIAODUI_REN(42, "校对人"),
    XIAZHU_YISHENG(43, "下嘱医生"),
    TINGZHU_YISHENG(44, "停嘱医生"),
    SHENQING_REN(45, "申请人"),
    CAIJI_ZHE(46, "采集者"),
    JIANCHA_ZHE(47, "检查者"),
    BAOGAO_REN(48, "报告人"),
    HUISHOU_REN(49, "回收人");

    public Integer getDomain_id() {
        return domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    private Integer domain_id;
    private String domain_name;

    private ProviderRoleDomain(Integer domain_id, String domain_name) {
        this.domain_id = domain_id;

    }
}







