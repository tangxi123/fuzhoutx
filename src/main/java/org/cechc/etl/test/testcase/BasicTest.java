package org.cechc.etl.test.testcase;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.*;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@SpringBootTest
@Component
public class BasicTest extends AbstractTestNGSpringContextTests {
    public static String nowDateTime = LocalDateTime.now().toString();

    @Autowired
    public Environment environment;

    @Autowired
    public OBSAdmissionCountMapper obsAdmissionCountMapper;

    @Autowired
    public ODSAdmissionCountMapper odsAdmissionCountMapper;

    @Autowired
    public OBSAllergyCountMapper obsAllergyCountMapper;

    @Autowired
    public OBSAttributionCountMapper obsAttributionCountMapper;

    @Autowired
    public OBSDiagnosisCountMapper obsDiagnosisCountMapper;

    @Autowired
    public ODSDiagnosisCountMapper odsDiagnosisCountMapper;

    @Autowired
    public OBSDomainCountMapper obsDomainCountMapper;

    @Autowired
    public ODSEncounterCountMapper odsEncounterCountMapper;

    @Autowired
    public OBSEncounterCountMapper obsEncounterCountMapper;

    @Autowired
    public OBSImmunizationCountMapper obsImmunizationCountMapper;

    @Autowired
    public OBSLocationCountMapper obsLocationCountMapper;

    @Autowired
    public ODSLocationCountMapper odsLocationCountMapper;

    @Autowired
    public ODSMedicationCountMapper odsMedicationCountMapper;

    @Autowired
    public OBSMedicationCountMapper obsMedicationCountMapper;

    @Autowired
    public ODSOrderCountMapper odsOrderCountMapper;

    @Autowired
    public OBSOrderCountMapper obsOrderCountMapper;

    @Autowired
    public ODSPatientCountMapper odsPatientCountMapper;

    @Autowired
    public OBSPatientCountMapper obsPatientCountMapper;


    @Autowired
    public OBSProgramCountMapper obsProgramCountMapper;

    @Autowired
    public ODSProviderCountMapper odsProviderCountMapper;

    @Autowired
    public OBSProviderCountMapper obsProviderCountMapper;

    @Autowired
    public ODSResultCountMapper odsResultCountMapper;

    @Autowired
    public OBSResultCountMapper obsResultCountMapper;

    @Autowired
    public ODSScheduleCountMapper odsScheduleCountMapper;

    @Autowired
    public OBSScheduleCountMapper obsScheduleCountMapper;

    @Autowired
    public OBSAdmissionAllCountMapper obsAdmissionAllCountMapper;

    @Autowired
    public ODSAdmissionAllCountMapper odsAdmissionAllCountMapper;

    @Autowired
    public OBSAllergyAllCountMapper obsAllergyAllCountMapper;

    @Autowired
    public OBSAttributionAllCountMapper obsAttributionAllCountMapper;

    @Autowired
    public OBSDiagnosisAllCountMapper obsDiagnosisAllCountMapper;

    @Autowired
    public ODSDiagnosisAllCountMapper odsDiagnosisAllCountMapper;

    @Autowired
    public OBSDomainAllCountMapper obsDomainAllCountMapper;

    @Autowired
    public ODSEncounterAllCountMapper odsEncounterAllCountMapper;

    @Autowired
    public OBSEncounterAllCountMapper obsEncounterAllCountMapper;

    @Autowired
    public OBSImmunizationAllCountMapper obsImmunizationAllCountMapper;

    @Autowired
    public OBSLocationAllCountMapper obsLocationAllCountMapper;

    @Autowired
    public ODSLocationAllCountMapper odsLocationAllCountMapper;

    @Autowired
    public ODSMedicationAllCountMapper odsMedicationAllCountMapper;

    @Autowired
    public OBSMedicationAllCountMapper obsMedicationAllCountMapper;

    @Autowired
    public ODSOrderAllCountMapper odsOrderAllCountMapper;

    @Autowired
    public OBSOrderAllCountMapper obsOrderAllCountMapper;

    @Autowired
    public ODSPatientAllCountMapper odsPatientAllCountMapper;

    @Autowired
    public OBSPatientAllCountMapper obsPatientAllCountMapper;


    @Autowired
    public OBSProgramAllCountMapper obsProgramAllCountMapper;

    @Autowired
    public ODSProviderAllCountMapper odsProviderAllCountMapper;

    @Autowired
    public OBSProviderAllCountMapper obsProviderAllCountMapper;

    @Autowired
    public ODSResultAllCountMapper odsResultAllCountMapper;

    @Autowired
    public OBSResultAllCountMapper obsResultAllCountMapper;

    @Autowired
    public ODSScheduleAllCountMapper odsScheduleAllCountMapper;

    @Autowired
    public OBSScheduleAllCountMapper obsScheduleAllCountMapper;

    @Autowired
    public ODSAdmissionFieldMapper odsAdmissionFieldMapper;

    @Autowired
    public OBSAdmissionFieldMapper obsAdmissionFieldMapper;

    @Autowired
    public ODSDiagnosisFieldMapper odsDiagnosisFieldMapper;

    @Autowired
    public OBSDiagnosisFieldMapper obsDiagnosisFieldMapper;

    @Autowired
    public OBSEncounterFieldMapper obsEncounterFieldMapper;

    @Autowired
    public ODSEncounterFieldMapper odsEncounterFieldMapper;

    @Autowired
    public ODSLocationFieldMapper odsLocationFieldMapper;

    @Autowired
    public OBSLocationFieldMapper obsLocationFieldMapper;

    @Autowired
    public ODSMedicationFieldMapper odsMedicationFieldMapper;

    @Autowired
    public OBSMedicationFieldMapper obsMedicationFieldMapper;

    @Autowired
    public ODSOrderFieldMapper odsOrderFieldMapper;

    @Autowired
    public OBSOrderFieldMapper obsOrderFieldMapper;

    @Autowired
    public OBSPatientFieldMapper obsPatientFieldMapper;

    @Autowired
    public ODSPatientFieldMapper odsPatientFieldMapper;

    @Autowired
    public ODSProviderFieldMapper odsProviderFieldMapper;

    @Autowired
    public OBSProviderFieldMapper obsProviderFieldMapper;

    @Autowired
    public ODSResultFieldMapper odsResultFieldMapper;

    @Autowired
    public OBSResultFieldMapper obsResultFieldMapper;

    @Autowired
    public ODSScheduleFieldMapper odsScheduleFieldMapper;

    @Autowired
    public OBSScheduleFieldMapper obsScheduleFieldMapper;

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

    private static String srcCountStr = "srcCount";
    private static String destCountStr = "destCount";

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

    public <T, R> R getSrcList(T dateTimeRange, Function<T, R> mapper) {
        R srcIdList = mapper.apply(dateTimeRange);
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

    File outputDirectory = new File("test-output" + nowDateTime);
    String outputDirectoryName = outputDirectory.getAbsolutePath();

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
            methodResults.add(new MethodResult(methodName,description));
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

    @AfterSuite
    public void afterSuite_wirteResult_toExcel(ITestContext context) {
        Workbook wb = new HSSFWorkbook();

        Sheet totalResultSheet = wb.createSheet("total_result");
        Sheet caseResultSheet = wb.createSheet("case_result");
        writeTotalResultToSheet(totalResultSheet,context);
        writeCaseResultToSheet(caseResultSheet,context);

        try (OutputStream fileOut = new FileOutputStream(outputDirectoryName + "/total_result.xls")) {
            wb.write(fileOut);
            wb.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void writeTotalResultToSheet(Sheet sheet,ITestContext context){
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

    private void writeCaseResultToSheet(Sheet sheet,ITestContext context){
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
            methodResults.add(new MethodResult(methodName,description));
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

    private HashMap<String,Long> getTotalResult(ITestContext context){
        IResultMap passedTests = context.getPassedTests();
        IResultMap failedTests = context.getFailedTests();
        IResultMap skippedTests = context.getSkippedTests();

        long passTestsSize = passedTests.size() + context.getFailedButWithinSuccessPercentageTests().size();
        long failTestsSize = failedTests.size();
        long skipTestsSize = skippedTests.size();
        long totalTestsSize = passTestsSize + failTestsSize + skipTestsSize;

        HashMap<String,Long> totalResult = new HashMap<>();
        totalResult.put("passTestsSize",passTestsSize);
        totalResult.put("failTestsSize",failTestsSize);
        totalResult.put("skipTestsSize",skipTestsSize);
        totalResult.put("totalTestsSize",totalTestsSize);

        return totalResult;
    }



}
