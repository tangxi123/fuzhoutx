package org.cechc.etl.test.pojo;

//import com.fasterxml.jackson.annotation.JsonFormat;

//import com.fasterxml.jackson.annotation.JsonFormat;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Parameter {
    private Integer id;

    private String orgCode;

    private Integer offset;

    private Integer limit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createEndTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyEndTime;

    private Integer testCaseId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public LocalDateTime getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(LocalDateTime createStartTime) {
        this.createStartTime = createStartTime;
    }

    public LocalDateTime getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(LocalDateTime createEndTime) {
        this.createEndTime = createEndTime;
    }

    public LocalDateTime getModifyStartTime() {
        return modifyStartTime;
    }

    public void setModifyStartTime(LocalDateTime modifyStartTime) {
        this.modifyStartTime = modifyStartTime;
    }

    public LocalDateTime getModifyEndTime() {
        return modifyEndTime;
    }

    public void setModifyEndTime(LocalDateTime modifyEndTime) {
        this.modifyEndTime = modifyEndTime;
    }

    public Integer getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }
}
