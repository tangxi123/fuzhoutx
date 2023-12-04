package org.cechc.etl.test.testcase;

class MethodResult {
    String methodName;
    String description;
    Integer passCount = 0;
    Integer failCount = 0;
    Integer skipCount = 0;
    long testTime = 0;

    public MethodResult(String methodName,String description) {
        this.methodName = methodName;
        this.description = description;
    }

    public void addPassCount(Integer passCount) {
        this.passCount = this.passCount + passCount;
    }

    public void addFailCount(Integer failCount) {
        this.failCount = this.failCount + failCount;
    }

    public void addSkipCount(Integer skipCount) {
        this.skipCount = this.skipCount + skipCount;
    }

    public void addTestTime(long testTime) {this.testTime = this.testTime + testTime;}


    public String getMethodName() {
        return methodName;
    }

    public String getDescription() {return description;}

    public Integer getPassCount() {
        return passCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public Integer getSkipCount() {
        return skipCount;
    }

    public long getTestTime() {return testTime;}
}