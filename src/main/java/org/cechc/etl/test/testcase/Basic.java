package org.cechc.etl.test.testcase;

//import com.github.pagehelper.PageHelper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.cechc.etl.test.domain.DateTimeRange;
import org.cechc.etl.test.obsmapper.count.*;
import org.cechc.etl.test.obsmapper.count.allCount.*;
import org.cechc.etl.test.obsmapper.field.*;
import org.cechc.etl.test.odsmapper.count.*;
import org.cechc.etl.test.odsmapper.count.allCount.*;
import org.cechc.etl.test.odsmapper.field.*;
import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.util.TargetFieldComputerUtil;
import org.cechc.etl.test.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.*;
import org.testng.annotations.AfterSuite;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;


public class Basic {

    private static String srcCountStr = "srcCount";
    private static String destCountStr = "destCount";
    public Environment environment = SpringUtil.getBean(Environment.class);
    public OBSAdmissionCountMapper obsAdmissionCountMapper = SpringUtil.getBean(OBSAdmissionCountMapper.class);
    public ODSAdmissionCountMapper odsAdmissionCountMapper = SpringUtil.getBean(ODSAdmissionCountMapper.class);
    public OBSAllergyCountMapper obsAllergyCountMapper = SpringUtil.getBean(OBSAllergyCountMapper.class);
    public OBSAttributionCountMapper obsAttributionCountMapper = SpringUtil.getBean(OBSAttributionCountMapper.class);
    public OBSDiagnosisCountMapper obsDiagnosisCountMapper = SpringUtil.getBean(OBSDiagnosisCountMapper.class);
    public ODSDiagnosisCountMapper odsDiagnosisCountMapper = SpringUtil.getBean(ODSDiagnosisCountMapper.class);
    public OBSDomainCountMapper obsDomainCountMapper = SpringUtil.getBean(OBSDomainCountMapper.class);
    public ODSEncounterCountMapper odsEncounterCountMapper = SpringUtil.getBean(ODSEncounterCountMapper.class);
    public OBSEncounterCountMapper obsEncounterCountMapper = SpringUtil.getBean(OBSEncounterCountMapper.class);
    public OBSImmunizationCountMapper obsImmunizationCountMapper = SpringUtil.getBean(OBSImmunizationCountMapper.class);
    public OBSLocationCountMapper obsLocationCountMapper = SpringUtil.getBean(OBSLocationCountMapper.class);
    public ODSLocationCountMapper odsLocationCountMapper = SpringUtil.getBean(ODSLocationCountMapper.class);
    public ODSMedicationCountMapper odsMedicationCountMapper = SpringUtil.getBean(ODSMedicationCountMapper.class);
    public OBSMedicationCountMapper obsMedicationCountMapper = SpringUtil.getBean(OBSMedicationCountMapper.class);
    public ODSOrderCountMapper odsOrderCountMapper = SpringUtil.getBean(ODSOrderCountMapper.class);
    public OBSOrderCountMapper obsOrderCountMapper = SpringUtil.getBean(OBSOrderCountMapper.class);
    public ODSPatientCountMapper odsPatientCountMapper = SpringUtil.getBean(ODSPatientCountMapper.class);
    public OBSPatientCountMapper obsPatientCountMapper = SpringUtil.getBean(OBSPatientCountMapper.class);
    public OBSProgramCountMapper obsProgramCountMapper = SpringUtil.getBean(OBSProgramCountMapper.class);
    public ODSProviderCountMapper odsProviderCountMapper = SpringUtil.getBean(ODSProviderCountMapper.class);
    public OBSProviderCountMapper obsProviderCountMapper = SpringUtil.getBean(OBSProviderCountMapper.class);
    public ODSResultCountMapper odsResultCountMapper = SpringUtil.getBean(ODSResultCountMapper.class);
    public OBSResultCountMapper obsResultCountMapper = SpringUtil.getBean(OBSResultCountMapper.class);
    public ODSScheduleCountMapper odsScheduleCountMapper = SpringUtil.getBean(ODSScheduleCountMapper.class);
    public OBSScheduleCountMapper obsScheduleCountMapper = SpringUtil.getBean(OBSScheduleCountMapper.class);
    public OBSAdmissionAllCountMapper obsAdmissionAllCountMapper = SpringUtil.getBean(OBSAdmissionAllCountMapper.class);
    public ODSAdmissionAllCountMapper odsAdmissionAllCountMapper = SpringUtil.getBean(ODSAdmissionAllCountMapper.class);
    public OBSAllergyAllCountMapper obsAllergyAllCountMapper = SpringUtil.getBean(OBSAllergyAllCountMapper.class);
    public OBSAttributionAllCountMapper obsAttributionAllCountMapper = SpringUtil.getBean(OBSAttributionAllCountMapper.class);
    public OBSDiagnosisAllCountMapper obsDiagnosisAllCountMapper = SpringUtil.getBean(OBSDiagnosisAllCountMapper.class);
    public ODSDiagnosisAllCountMapper odsDiagnosisAllCountMapper = SpringUtil.getBean(ODSDiagnosisAllCountMapper.class);
    public OBSDomainAllCountMapper obsDomainAllCountMapper = SpringUtil.getBean(OBSDomainAllCountMapper.class);
    public ODSEncounterAllCountMapper odsEncounterAllCountMapper = SpringUtil.getBean(ODSEncounterAllCountMapper.class);
    public OBSEncounterAllCountMapper obsEncounterAllCountMapper = SpringUtil.getBean(OBSEncounterAllCountMapper.class);
    public OBSImmunizationAllCountMapper obsImmunizationAllCountMapper = SpringUtil.getBean(OBSImmunizationAllCountMapper.class);
    public OBSLocationAllCountMapper obsLocationAllCountMapper = SpringUtil.getBean(OBSLocationAllCountMapper.class);
    public ODSLocationAllCountMapper odsLocationAllCountMapper = SpringUtil.getBean(ODSLocationAllCountMapper.class);
    public ODSMedicationAllCountMapper odsMedicationAllCountMapper = SpringUtil.getBean(ODSMedicationAllCountMapper.class);
    public OBSMedicationAllCountMapper obsMedicationAllCountMapper = SpringUtil.getBean(OBSMedicationAllCountMapper.class);
    public ODSOrderAllCountMapper odsOrderAllCountMapper = SpringUtil.getBean(ODSOrderAllCountMapper.class);
    public OBSOrderAllCountMapper obsOrderAllCountMapper = SpringUtil.getBean(OBSOrderAllCountMapper.class);
    public ODSPatientAllCountMapper odsPatientAllCountMapper = SpringUtil.getBean(ODSPatientAllCountMapper.class);
    public OBSPatientAllCountMapper obsPatientAllCountMapper = SpringUtil.getBean(OBSPatientAllCountMapper.class);
    public OBSProgramAllCountMapper obsProgramAllCountMapper = SpringUtil.getBean(OBSProgramAllCountMapper.class);
    public ODSProviderAllCountMapper odsProviderAllCountMapper = SpringUtil.getBean(ODSProviderAllCountMapper.class);
    public OBSProviderAllCountMapper obsProviderAllCountMapper = SpringUtil.getBean(OBSProviderAllCountMapper.class);
    public ODSResultAllCountMapper odsResultAllCountMapper = SpringUtil.getBean(ODSResultAllCountMapper.class);
    public OBSResultAllCountMapper obsResultAllCountMapper = SpringUtil.getBean(OBSResultAllCountMapper.class);
    public ODSScheduleAllCountMapper odsScheduleAllCountMapper = SpringUtil.getBean(ODSScheduleAllCountMapper.class);
    public OBSScheduleAllCountMapper obsScheduleAllCountMapper = SpringUtil.getBean(OBSScheduleAllCountMapper.class);
    public ODSAdmissionFieldMapper odsAdmissionFieldMapper = SpringUtil.getBean(ODSAdmissionFieldMapper.class);
    public OBSAdmissionFieldMapper obsAdmissionFieldMapper = SpringUtil.getBean(OBSAdmissionFieldMapper.class);
    public ODSDiagnosisFieldMapper odsDiagnosisFieldMapper = SpringUtil.getBean(ODSDiagnosisFieldMapper.class);
    public OBSDiagnosisFieldMapper obsDiagnosisFieldMapper = SpringUtil.getBean(OBSDiagnosisFieldMapper.class);
    public OBSEncounterFieldMapper obsEncounterFieldMapper = SpringUtil.getBean(OBSEncounterFieldMapper.class);
    public ODSEncounterFieldMapper odsEncounterFieldMapper = SpringUtil.getBean(ODSEncounterFieldMapper.class);
    public ODSLocationFieldMapper odsLocationFieldMapper = SpringUtil.getBean(ODSLocationFieldMapper.class);
    public OBSLocationFieldMapper obsLocationFieldMapper = SpringUtil.getBean(OBSLocationFieldMapper.class);
    public ODSMedicationFieldMapper odsMedicationFieldMapper = SpringUtil.getBean(ODSMedicationFieldMapper.class);
    public OBSMedicationFieldMapper obsMedicationFieldMapper = SpringUtil.getBean(OBSMedicationFieldMapper.class);
    public ODSOrderFieldMapper odsOrderFieldMapper = SpringUtil.getBean(ODSOrderFieldMapper.class);
    public OBSOrderFieldMapper obsOrderFieldMapper = SpringUtil.getBean(OBSOrderFieldMapper.class);
    public OBSPatientFieldMapper obsPatientFieldMapper = SpringUtil.getBean(OBSPatientFieldMapper.class);
    public ODSPatientFieldMapper odsPatientFieldMapper = SpringUtil.getBean(ODSPatientFieldMapper.class);
    public ODSProviderFieldMapper odsProviderFieldMapper = SpringUtil.getBean(ODSProviderFieldMapper.class);
    public OBSProviderFieldMapper obsProviderFieldMapper = SpringUtil.getBean(OBSProviderFieldMapper.class);
    public ODSResultFieldMapper odsResultFieldMapper = SpringUtil.getBean(ODSResultFieldMapper.class);
    public OBSResultFieldMapper obsResultFieldMapper = SpringUtil.getBean(OBSResultFieldMapper.class);
//    public ODSPatientFieldMapper odsPatientFieldMapper = SpringUtil.getBean(ODSPatientFieldMapper.class);
//    public OBSPatientFieldMapper obsPatientFieldMapper = SpringUtil.getBean(OBSPatientFieldMapper.class);
//
//    public ODSPatientCountMapper odsPatientCountMapper = SpringUtil.getBean(ODSPatientCountMapper.class);
//    public OBSPatientCountMapper obsPatientCountMapper = SpringUtil.getBean(OBSPatientCountMapper.class);


//    public Environment environment  = SpringUtil.getBean(Environment.class);
    public ODSScheduleFieldMapper odsScheduleFieldMapper = SpringUtil.getBean(ODSScheduleFieldMapper.class);
    public OBSScheduleFieldMapper obsScheduleFieldMapper = SpringUtil.getBean(OBSScheduleFieldMapper.class);
    public String nowDateTime = new Report().getNowDateTime();
    private String createStartTime = null;
    private String createEndTime = null;
    private String modifyStartTime = null;
    private String modifyEndTime = null;

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

    public HashMap<String, Object> get1to1SrcAndDestCount(Function<DateTimeRange, List<String>> sourceMapper, Function<List<String>, Long> destMapper) {
        long srcCount = 0;
        long destCount = 0;
        int offset = 1;
        int limit = 10000;
        DateTimeRange dateTimeRange = new DateTimeRange(createStartTime, createEndTime, modifyStartTime, modifyEndTime);
        HashMap<String, Object> srcAndDestCountMap = new HashMap<>();
        List<String> srcIdList;
        do {
//            PageHelper.startPage(offset, limit, false);
            srcIdList = getSrcList(dateTimeRange, sourceMapper);
            srcCount = srcCount + srcIdList.size();

            Long destExistedIdCount = getDestExistedIdCount(srcIdList, destMapper);
            destCount = destCount + destExistedIdCount;
            offset++;
        } while (srcIdList.size() > 0);
        srcAndDestCountMap.put(srcCountStr, srcCount);
        srcAndDestCountMap.put(destCountStr, destCount);
        System.out.println("#################################################");
        System.out.println(srcCountStr + ": " + srcCount);
        System.out.println(destCountStr + ": " + destCount);
        return srcAndDestCountMap;
    }

    public HashMap<String, Object> get1to1SrcAndDestCount(Function<Parameter, List<String>> sourceMapper, Function<List<String>, Long> destMapper, Parameter parameter) {
        long srcCount = 0;
        long destCount = 0;
        HashMap<String, Object> srcAndDestCountMap = new HashMap<>();
        List<String> srcIdList;
        srcIdList = getSrcList(parameter, sourceMapper);
        if (srcIdList.size() > 0) {
            srcCount = srcCount + srcIdList.size();

            Long destExistedIdCount = getDestExistedIdCount(srcIdList, destMapper);
            destCount = destCount + destExistedIdCount;

        }
        srcAndDestCountMap.put(srcCountStr, srcCount);
        srcAndDestCountMap.put(destCountStr, destCount);
        System.out.println("#################################################");
        System.out.println(srcCountStr + ": " + srcCount);
        System.out.println(destCountStr + ": " + destCount);
        return srcAndDestCountMap;
    }

    public HashMap<String, Object> get1to1SrcAndDestCount(Function<Parameter, List<String>> sourceMapper, Function<List<String>, Long> destMapper, Long srcCount, Parameter parameter) {
        long destCount = 0;
        long limit = 1000;
        long offsetCount = srcCount / limit;
        HashMap<String, Object> srcAndDestCountMap = new HashMap<>();
        List<String> srcIdList;
        for (long i = 0; i <= offsetCount; i++) {
            long offset = i * limit;
            parameter.setLimit((int) limit);
            parameter.setOffset((int) offset);
            srcIdList = getSrcList(parameter, sourceMapper);
            Long destExistedIdCount = getDestExistedIdCount(srcIdList, destMapper);
            destCount = destCount + destExistedIdCount;
        }

        srcAndDestCountMap.put(srcCountStr, srcCount);
        srcAndDestCountMap.put(destCountStr, destCount);
        System.out.println("#################################################");
        System.out.println(srcCountStr + ": " + srcCount);
        System.out.println(destCountStr + ": " + destCount);
        return srcAndDestCountMap;
    }

    public HashMap<String, Object> get1toManySrcAndDestCount(Function<DateTimeRange, List<HashMap<String, Object>>> sourceMapper, Function<List<String>, Long> destMapper) {
        long srcCount = 0;
        long destCount = 0;
        int offset = 1;
        int limit = 10000;
        DateTimeRange dateTimeRange = new DateTimeRange(createStartTime, createEndTime, modifyStartTime, modifyEndTime);
        HashMap<String, Object> srcAndDestCountMap = new HashMap<>();

        List<HashMap<String, Object>> srcIdMapCountList;
        do {
//            PageHelper.startPage(offset, limit, false);
            srcIdMapCountList = getSrcList(dateTimeRange, sourceMapper);
            ArrayList<String> srcIdList = new ArrayList<>();
            for (HashMap<String, Object> IdCountMap : srcIdMapCountList) {
                srcCount = srcCount + (long) IdCountMap.get("total");
                String recorId = (String) IdCountMap.get("record_id");
                srcIdList.add(recorId);
            }
            Long destExistedIdCount = getDestExistedIdCount(srcIdList, destMapper);
            destCount = destCount + destExistedIdCount;
            offset++;
        } while (srcIdMapCountList.size() > 0);
        srcAndDestCountMap.put(srcCountStr, srcCount);
        srcAndDestCountMap.put(destCountStr, destCount);
        System.out.println("#################################################");
        System.out.println(srcCountStr + ": " + srcCount);
        System.out.println(destCountStr + ": " + destCount);
        return srcAndDestCountMap;
    }

    public HashMap<String, Object> get1toManySrcAndDestCount(Function<Parameter, List<HashMap<String, Object>>> sourceMapper, Function<List<String>, Long> destMapper, Parameter parameter) {
        long srcCount = 0;
        long destCount = 0;

        HashMap<String, Object> srcAndDestCountMap = new HashMap<>();

        List<HashMap<String, Object>> srcIdMapCountList;


        srcIdMapCountList = getSrcList(parameter, sourceMapper);
        if (srcIdMapCountList.size() > 0) {
            ArrayList<String> srcIdList = new ArrayList<>();
            for (HashMap<String, Object> IdCountMap : srcIdMapCountList) {
                srcCount = srcCount + (long) IdCountMap.get("total");
                String recorId = (String) IdCountMap.get("record_id");
                srcIdList.add(recorId);
            }
            Long destExistedIdCount = getDestExistedIdCount(srcIdList, destMapper);
            destCount = destCount + destExistedIdCount;
        }

        srcAndDestCountMap.put(srcCountStr, srcCount);
        srcAndDestCountMap.put(destCountStr, destCount);
        System.out.println("#################################################");
        System.out.println(srcCountStr + ": " + srcCount);
        System.out.println(destCountStr + ": " + destCount);
        return srcAndDestCountMap;
    }

    public <T, R> R getSrcList(T parameter, Function<T, R> mapper) {
        R srcIdList = mapper.apply(parameter);
        return srcIdList;

    }

    public <T, R> R getDestExistedIdCount(T t, Function<T, R> mapper) {
        R r = mapper.apply(t);
        return r;
    }

    public HashMap<String, Object> getTotalSrcAndDestCount(ArrayList<HashMap<String, Object>> srcAndDestCounts) {
        long srcCount = 0;
        long destCount = 0;
        HashMap<String, Object> totalSrcAndDestCount = new HashMap<>();
        for (HashMap<String, Object> srcAndDestCount : srcAndDestCounts) {
            srcCount = srcCount + (Long) srcAndDestCount.get(srcCountStr);
            destCount = destCount + (Long) srcAndDestCount.get(destCountStr);
        }
        totalSrcAndDestCount.put(srcCountStr, srcCount);
        totalSrcAndDestCount.put(destCountStr, destCount);
        return totalSrcAndDestCount;
    }

    public void compare(ArrayList<HashMap<String, Object>> srcAndDestCounts) {
        HashMap<String, Object> totalSrcAndDestCount = getTotalSrcAndDestCount(srcAndDestCounts);
        Long srcCount = (Long) totalSrcAndDestCount.get(srcCountStr);
        Long destCount = (Long) totalSrcAndDestCount.get(destCountStr);

        Assert.assertEquals(srcCount, destCount, "源数据条数为：" + srcCount + ",目标数据条数为：" + destCount);

    }


    public ArrayList<HashMap<String, Object>> toList(HashMap<String, Object>... srcAndDestCounts) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (HashMap<String, Object> count : srcAndDestCounts) {
            list.add(count);
        }

        return list;

    }

    public HashMap<String, Object> getObsInfoByRecordId(HashMap<String, Object> odsMap, Function<String, HashMap<String, Object>> obsMapper) {
        String record_id = (String) odsMap.get("record_id");
        HashMap<String, Object> obsPatientInfo = applyObsMapper(record_id, obsMapper);
        return obsPatientInfo;

    }

    public <T, R> R applyObsMapper(T t, Function<T, R> mapper) {
        R r = mapper.apply(t);
        return r;
    }


    public void compareOneToManyFields(HashMap<String, Object> srcMap, List<Map<String, Object>> destLists, Properties mapRule) throws Exception {
        SoftAssert softAssert = new SoftAssert();
        TargetFieldComputerUtil.test2((Map) mapRule, srcMap, destLists, softAssert, (ConfigurableEnvironment) environment);
    }

    public void compareOneToOneField(HashMap<String, Object> srcFields, HashMap<String, Object> destFields, Properties mapRule) throws Exception {
        SoftAssert softAssert = new SoftAssert();
        TargetFieldComputerUtil.test((Map) mapRule, srcFields, destFields, softAssert);
    }

//    File outputDirectory = new File("test-output" + nowDateTime);
//    String outputDirectoryName = outputDirectory.getAbsolutePath();

//    @AfterMethod
//    public void afterMethod(ITestResult testResult){
//        String methodName = testResult.getName();
//        Object[] parameters = testResult.getParameters();
//        int status = testResult.getStatus();
//        System.out.println("***********************************************************");
//        System.out.println(methodName);
//        System.out.println(parameters);
//        System.out.println(status);
//
//
//    }

//    @AfterSuite
//    public void afterSuite(ITestContext context) {
//        File totalResultFile = new File(outputDirectoryName + "/total-result");
//        FileWriter fw = null;
//        try {
//            //如果文件存在，则追加内容；如果文件不存在，则创建文件
//            fw = new FileWriter(totalResultFile, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        PrintWriter pw = new PrintWriter(fw);
//        pw.append("************************************************************************************************************************\r");
//
//        writeTotalResult(context, pw);
//        writeMethodResult(context, pw);
//
//        try {
//            fw.flush();
//            pw.close();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    private void writeTotalResult(ITestContext context, PrintWriter pw) {
        IResultMap passedTests = context.getPassedTests();
        IResultMap failedTests = context.getFailedTests();
        IResultMap skippedTests = context.getSkippedTests();

        long passTestsSize = passedTests.size() + context.getFailedButWithinSuccessPercentageTests().size();
        long failTestsSize = failedTests.size();
        long skipTestsSize = skippedTests.size();
        long totalTestsSize = passTestsSize + failTestsSize + skipTestsSize;

        pw.append("测试用例总条数据：" + totalTestsSize + "\t通过条数：" + passTestsSize + "\t失败条数：" + failTestsSize + "\t跳过条数：" + skipTestsSize + "\r");
        pw.append("************************************************************************************************************************\r");
    }

    private void writeMethodResult(ITestContext context, PrintWriter pw) {
        IResultMap passedTests = context.getPassedTests();
        IResultMap failedTests = context.getFailedTests();
        IResultMap skippedTests = context.getSkippedTests();

        Set<ITestResult> allPassedResults = passedTests.getAllResults();
        Set<ITestResult> allFailedResults = failedTests.getAllResults();
        Set<ITestResult> allSkipedResults = skippedTests.getAllResults();


        ITestNGMethod[] allTestMethods = context.getAllTestMethods();
        ArrayList<MethodResult> methodResults = new ArrayList<>();
        for (ITestNGMethod method : allTestMethods) {
            String methodName = method.getMethodName();
            String description = method.getDescription();
            methodResults.add(new MethodResult(methodName, description));
        }

        for (MethodResult methodResult : methodResults) {
            String methodName = methodResult.getMethodName();
            for (ITestResult result : allPassedResults) {
                if (methodName.equals(result.getName())) {
                    methodResult.addPassCount(1);
                }
            }

            for (ITestResult result : allFailedResults) {
                if (methodName.equals(result.getName())) {
                    methodResult.addFailCount(1);
                }
            }

            for (ITestResult result : allSkipedResults) {
                if (methodName.equals(result.getName())) {
                    methodResult.addSkipCount(1);
                }
            }
        }
        for (MethodResult methodResult : methodResults) {
            String methodName = methodResult.getMethodName();
            Integer passCount1 = methodResult.getPassCount();
            Integer failCount1 = methodResult.getFailCount();
            Integer skipCount1 = methodResult.getSkipCount();
            Integer totalCount = passCount1 + failCount1 + skipCount1;
            pw.append("测试方法名字：" + methodName + "\t用例总条数：" + totalCount + "\t通过条数：" + passCount1 + "\t失败条数：" + failCount1 + "\t跳过条数：" + skipCount1 + "\r");
        }
    }

    //    @AfterSuite(alwaysRun = true)
    public void afterSuite_wirteResult_toExcel(ITestContext context) {
        String nowDateTime = context.getCurrentXmlTest().getParameter("nowDateTime");
        File outputDirectory = new File("test-output" + nowDateTime);
        String outputDirectoryName = outputDirectory.getAbsolutePath();

        System.out.println("*****************************hahahahahahahahaha*******************************************");
        Workbook wb = new HSSFWorkbook();

        Sheet totalResultSheet = wb.createSheet("总测试用例结果");
        Sheet caseResultSheet = wb.createSheet("测试用例明细");
        writeTotalResultToSheet(totalResultSheet, context);
        writeCaseResultToSheet(caseResultSheet, context);
        String fileName = outputDirectoryName + "/total_result.xls";
        try (OutputStream fileOut = new FileOutputStream(fileName)) {
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
            wb.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void writeTotalResultToSheet(Sheet sheet, ITestContext context) {
        HashMap<String, Long> totalResult = getTotalResult(context);

        Row row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("测试用例总条数据");
        row1.createCell(1).setCellValue("通过条数");
        row1.createCell(2).setCellValue("失败条数");
        row1.createCell(3).setCellValue("跳过条数");

        Row row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue(totalResult.get("totalTestsSize"));
        row2.createCell(1).setCellValue(totalResult.get("passTestsSize"));
        row2.createCell(2).setCellValue(totalResult.get("failTestsSize"));
        row2.createCell(3).setCellValue(totalResult.get("skipTestsSize"));
    }

    private void writeCaseResultToSheet(Sheet sheet, ITestContext context) {
        IResultMap passedTests = context.getPassedTests();
        IResultMap failedTests = context.getFailedTests();
        IResultMap skippedTests = context.getSkippedTests();

        Set<ITestResult> allPassedResults = passedTests.getAllResults();
        Set<ITestResult> allFailedResults = failedTests.getAllResults();
        Set<ITestResult> allSkipedResults = skippedTests.getAllResults();


        ITestNGMethod[] allTestMethods = context.getAllTestMethods();
        ArrayList<MethodResult> methodResults = new ArrayList<>();
        for (ITestNGMethod method : allTestMethods) {
            String methodName = method.getMethodName();
            String description = method.getDescription();
            methodResults.add(new MethodResult(methodName, description));
        }

        for (MethodResult methodResult : methodResults) {
            String methodName = methodResult.getMethodName();
            for (ITestResult result : allPassedResults) {
                if (methodName.equals(result.getName())) {
                    methodResult.addPassCount(1);
                }
            }

            for (ITestResult result : allFailedResults) {
                if (methodName.equals(result.getName())) {
                    methodResult.addFailCount(1);
                }
            }

            for (ITestResult result : allSkipedResults) {
                if (methodName.equals(result.getName())) {
                    methodResult.addSkipCount(1);
                }
            }
        }
        //创建标题列
        Row rowTile = sheet.createRow(0);
        rowTile.createCell(0).setCellValue("测试方法名字");
        rowTile.createCell(1).setCellValue("用例总条数");
        rowTile.createCell(2).setCellValue("通过条数");
        rowTile.createCell(3).setCellValue("失败条数");
        rowTile.createCell(4).setCellValue("跳过条数");
        int rowNum = 1;
        for (MethodResult methodResult : methodResults) {
            String methodName = methodResult.getMethodName();
            Integer passCount1 = methodResult.getPassCount();
            Integer failCount1 = methodResult.getFailCount();
            Integer skipCount1 = methodResult.getSkipCount();
            Integer totalCount = passCount1 + failCount1 + skipCount1;

            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(methodName);
            row.createCell(1).setCellValue(totalCount);
            row.createCell(2).setCellValue(passCount1);
            row.createCell(3).setCellValue(failCount1);
            row.createCell(4).setCellValue(skipCount1);
            rowNum++;
        }
    }

    private HashMap<String, Long> getTotalResult(ITestContext context) {
        IResultMap passedTests = context.getPassedTests();
        IResultMap failedTests = context.getFailedTests();
        IResultMap skippedTests = context.getSkippedTests();

        long passTestsSize = passedTests.size() + context.getFailedButWithinSuccessPercentageTests().size();
        long failTestsSize = failedTests.size();
        long skipTestsSize = skippedTests.size();
        long totalTestsSize = passTestsSize + failTestsSize + skipTestsSize;

        HashMap<String, Long> totalResult = new HashMap<>();
        totalResult.put("passTestsSize", passTestsSize);
        totalResult.put("failTestsSize", failTestsSize);
        totalResult.put("skipTestsSize", skipTestsSize);
        totalResult.put("totalTestsSize", totalTestsSize);

        return totalResult;
    }


}
