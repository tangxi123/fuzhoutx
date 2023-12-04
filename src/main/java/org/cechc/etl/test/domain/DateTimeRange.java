package org.cechc.etl.test.domain;

public class DateTimeRange {
    private String createStartTime;
    private String createEndTime;
    private String modifyStartTime;
    private String modifyEndTime;

    public DateTimeRange(String createStartTime, String createEndTime, String modifyStartTime, String modifyEndTime) {
        this.createStartTime = createStartTime;
        this.createEndTime = createEndTime;
        this.modifyStartTime = modifyStartTime;
        this.modifyEndTime = modifyEndTime;
    }

    public DateTimeRange(String createStartTime, String createEndTime) {
        this.createStartTime = createStartTime;
        this.createEndTime = createEndTime;
    }

    public String getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getModifyStartTime() {
        return modifyStartTime;
    }

    public void setModifyStartTime(String modifyStartTime) {
        this.modifyStartTime = modifyStartTime;
    }

    public String getModifyEndTime() {
        return modifyEndTime;
    }

    public void setModifyEndTime(String modifyEndTime) {
        this.modifyEndTime = modifyEndTime;
    }
}
