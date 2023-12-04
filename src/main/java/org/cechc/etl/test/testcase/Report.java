package org.cechc.etl.test.testcase;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public class Report extends TestListenerAdapter {
    Logger logger = LoggerFactory.getLogger(Report.class);
    private ITestContext m_testContext = null;


    String nowDateTime = LocalDateTime.now().toString() + UUID.randomUUID().toString();
    File outputDirectory = new File("test-output" + nowDateTime);
    String outputDirectoryName = outputDirectory.getAbsolutePath();
    File resultsDirectory = new File("/" + outputDirectoryName + "/results");
    String resultsDirectoryName = resultsDirectory.getAbsolutePath();

    ArrayList<MethodResult> methodResultLists = new ArrayList<>();

    public String getNowDateTime(){
        return nowDateTime;
    }

    @Override
    public void onStart(ITestContext context) {
        m_testContext = context;
        context.getCurrentXmlTest().addParameter("nowDateTime",nowDateTime);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
        if (!resultsDirectory.exists()) {
            resultsDirectory.mkdirs();
        }

    }




    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getName();
        String description = result.getMethod().getDescription();
        boolean find = false;
        for (MethodResult methodResult : methodResultLists) {
            if (methodResult.getMethodName().equals(methodName)) {
                methodResult.addFailCount(1);
                find = true;
                break;
            }
        }
        if (!find) {
            MethodResult methodResult = new MethodResult(methodName,description);
            methodResult.addFailCount(1);
            methodResultLists.add(methodResult);
        }
        logResult(result);
        writeResult(result, "fail");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getName();
        String description = result.getMethod().getDescription();

        boolean find = false;
        for (MethodResult methodResult : methodResultLists) {
            if (methodResult.getMethodName().equals(methodName)) {
                methodResult.addSkipCount(1);
                find = true;
                break;
            }
        }
        if (!find) {
            MethodResult methodResult = new MethodResult(methodName,description);
            methodResult.addSkipCount(1);
            methodResultLists.add(methodResult);
        }
        logResult(result);
        writeResult(result, "skip");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getName();
        String description = result.getMethod().getDescription();

        boolean find = false;
        for (MethodResult methodResult : methodResultLists) {
            if (methodResult.getMethodName().equals(methodName)) {
                methodResult.addPassCount(1);
                find = true;
                break;
            }
        }
        if (!find) {
            MethodResult methodResult = new MethodResult(methodName,description);
            methodResult.addPassCount(1);
            methodResultLists.add(methodResult);
        }
        logResult(result);
        writeResult(result, "pass");

    }

    @Override
    public void onFinish(ITestContext context){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@finish@@@@@@@@@@@@@@@@@@@@@");
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

    private void logResult(ITestResult result){
        int status = result.getStatus();
        String methodName = result.getName();
        Object[] parameters = result.getParameters();
        Throwable throwable = result.getThrowable();
        long testTime =  result.getEndMillis() - result.getStartMillis();
        String params = transferParamsToString(parameters);
        String throwMessage = transferThrowToString(throwable);
        String testResultStat = transferStatusToString(status);
        switch (testResultStat){
            case "失败":
                logger.error("******  测试用例: "+methodName+"执行失败！");
                logger.info("******  执行时间: "+testTime+"ms");
                logger.error("******  参数为: "+params);
                logger.error("******  throwException: "+throwMessage);
                break;
            case "通过":
                logger.info("******  测试用例: "+methodName+"执行成功！");
                logger.info("******  执行时间: "+testTime+"ms");
                logger.info("******  参数为: "+params);
            case "跳过":
                logger.error("******  测试用例: "+methodName+"执行跳过！");
                logger.info("******  执行时间: "+testTime+"ms");
                logger.error("******  参数为: "+params);
                logger.error("******  throwException: "+throwMessage);
                break;
        }
    }

    private void writeResult(ITestResult result, String resultType) {
        int index = 0;
        switch (resultType) {
            case "fail":
                for (MethodResult methodResult : methodResultLists) {
                    if (result.getName().equals(methodResult.getMethodName())) {
                        index = methodResult.getFailCount();
                    }
                }
                break;
            case "skip":
                for (MethodResult methodResult : methodResultLists) {
                    if (result.getName().equals(methodResult.getMethodName())) {
                        index = methodResult.getSkipCount();
                    }
                }
                break;
            case "pass":
                for (MethodResult methodResult : methodResultLists) {
                    if (result.getName().equals(methodResult.getMethodName())) {
                        index = methodResult.getPassCount();
                    }
                }
                break;
        }

        int status = result.getStatus();
        String methodName = result.getName();
        String description = result.getMethod().getDescription();
        Object[] parameters = result.getParameters();
        Throwable throwable = result.getThrowable();
        long testTime = result.getEndMillis()-result.getStartMillis();


        File methodDirecotry = new File(resultsDirectoryName + "/" + methodName);
        if (!methodDirecotry.exists()) {
            methodDirecotry.mkdirs();
        }

        File directory = new File(methodDirecotry + "/" + resultType);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        int prefix = (index - 1) / 100; //文件名前缀，每100条数据存一个excel
        String fileName = directory.getAbsolutePath() + "/" + prefix + "_" + methodName + "_" + resultType + "_" + nowDateTime + ".xls";

        boolean flag = fileExist(fileName);
        if (flag) {
            appendMessageToExcel(fileName, result);
        } else {
            writeColumnNameToExcel(fileName,result);
        }


    }




    private void writeMessageToFile(File file, String methodName, Object[] parameters, Throwable throwable, int status) {
        long count = 0;
        FileWriter fw = null;
        String params = transferParamsToString(parameters);
        String throwMessage = transferThrowToString(throwable);
        String stat = transferStatusToString(status);
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            count = bufferedReader.lines().filter(line -> line.equals("######################################################################################################################################################")).count();
            PrintWriter pw = new PrintWriter(fw);
            pw.append("测试用例名字：" + methodName + "\r");
            pw.append("测试结果：" + stat + "\r");
            pw.append("传入的参数为：" + params + "\r");
            pw.append("错误信息：" + throwMessage + "\r");
            pw.append("######################################################################################################################################################");
            pw.println();
            pw.flush();
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return count;


    }

    private void writeColumnNameToExcel(String fileName, ITestResult result) {

        int status = result.getStatus();
        String methodName = result.getName();
        String description = result.getMethod().getDescription();
        Object[] parameters = result.getParameters();
        Throwable throwable = result.getThrowable();
        long testTime = result.getEndMillis()-result.getStartMillis();

        HashMap<String, Object> paramMap;
        Object[] paramNames;


        if (parameters.length > 0 && parameters[0] instanceof HashMap) {

            paramMap = (HashMap) parameters[0];

            Set<String> strings = paramMap.keySet();
            paramNames = strings.toArray();
            System.out.println(strings);


            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("sheet");

            //添加列名
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("测试用例名字");
            row.createCell(1).setCellValue("测试用例描述");
            row.createCell(2).setCellValue("测试结果");
            row.createCell(3).setCellValue("错误信息");
            row.createCell(4).setCellValue("执行时间");
            for (int i = 0; i < paramNames.length; i++) {
                row.createCell(i + 5).setCellValue((String) paramNames[i]);
            }
            try (OutputStream fileOut = new FileOutputStream(fileName, true)) {
                wb.write(fileOut);
                fileOut.flush();
                fileOut.close();
                wb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //添加第一行数据
//            appendMessageToExcel(fileName,methodName,parameters,throwable,status,testTime);
            appendMessageToExcel(fileName,result);
        }else{
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("sheet1");

            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("测试用例名字");
            row.createCell(1).setCellValue("测试用例描述");
            row.createCell(2).setCellValue("测试结果");
            row.createCell(3).setCellValue("错误信息");
            row.createCell(4).setCellValue("参数信息");
            row.createCell(5).setCellValue("执行时间");

            try (OutputStream fileOut = new FileOutputStream(fileName, true)) {
                wb.write(fileOut);
                fileOut.flush();
                fileOut.close();
                wb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            appendMessageToExcel(fileName,result);
        }


    }


    private void appendMessageToExcel(String fileName,ITestResult result) {
        int status = result.getStatus();
        String methodName = result.getName();
        String description = result.getMethod().getDescription();
        Object[] parameters = result.getParameters();
        Throwable throwable = result.getThrowable();
        long testTime = result.getEndMillis()-result.getStartMillis();
        String throwMessage = transferThrowToString(throwable);
        String stat = transferStatusToString(status);
        String params = transferParamsToString(parameters);

        HashMap<String, Object> paramMap;
        Object[] paramValues;
        if (parameters.length > 0 && parameters[0] instanceof HashMap) {
            paramMap = (HashMap) parameters[0];
            Collection<Object> values = paramMap.values();
            paramValues = values.toArray();
            System.out.println(paramValues);
            try {
                FileInputStream myxls = new FileInputStream(fileName);
                HSSFWorkbook studentsSheet = new HSSFWorkbook(myxls);
                HSSFSheet worksheet = studentsSheet.getSheet("sheet");
                int lastRow = worksheet.getLastRowNum();
                Row row = worksheet.createRow(++lastRow);

                row.createCell(0).setCellValue(methodName);
                row.createCell(1).setCellValue(description);
                row.createCell(2).setCellValue(stat);
                row.createCell(3).setCellValue(throwMessage);
                row.createCell(4).setCellValue(testTime+" ms");
                for (int i = 0; i < paramValues.length; i++) {
                    row.createCell(i + 5).setCellValue(paramValues[i] + "");
                }

                myxls.close();
                FileOutputStream output_file = new FileOutputStream(new File(fileName));
                //write changes
                studentsSheet.write(output_file);
                output_file.flush();
                output_file.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                FileInputStream myxls = new FileInputStream(fileName);
                HSSFWorkbook studentsSheet = new HSSFWorkbook(myxls);
                HSSFSheet worksheet = studentsSheet.getSheet("sheet1");
                int lastRow = worksheet.getLastRowNum();
                Row row = worksheet.createRow(++lastRow);

                row.createCell(0).setCellValue(methodName);
                row.createCell(1).setCellValue(methodName);
                row.createCell(2).setCellValue(stat);
                row.createCell(3).setCellValue(throwMessage);
                row.createCell(4).setCellValue(params);
                row.createCell(5).setCellValue(testTime+" ms");


                myxls.close();
                FileOutputStream output_file = new FileOutputStream(new File(fileName));
                //write changes
                studentsSheet.write(output_file);
                output_file.flush();
                output_file.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //判断文件是否存在
    private static boolean fileExist(String fileName) {
        boolean flag;
        File file = new File(fileName);
        flag = file.exists();
        return flag;
    }


    private void writeTestResultToDoc(File file, String methodName, Object[] parameters, Throwable throwable, int status) {
        FileWriter fw = null;
        String params = transferParamsToString(parameters);
        String throwMessage = transferThrowToString(throwable);
        String stat = transferStatusToString(status);
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.append("测试用例名字：" + methodName + "\r");
        pw.append("测试结果：" + stat + "\r");
        pw.append("传入的参数为：" + params + "\r");
        pw.append("错误信息：" + throwMessage + "\r");
        pw.append("######################################################################################################################################################");
        pw.println();
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String transferThrowToString(Throwable throwable) {
        if (throwable == null) {
            return "没有错误信息";
        }
        return throwable.getMessage();
    }

    private String transferStatusToString(int status) {
        String result = null;
        switch (status) {
            case ITestResult.SUCCESS:
                result = "通过";
                break;
            case ITestResult.FAILURE:
                result = "失败";
                break;
            case ITestResult.SKIP:
                result = "跳过";
                break;
        }
        return result;
    }

    private String transferParamsToString(Object[] parameters) {
        StringBuilder sb = new StringBuilder();
        if (parameters.length == 0) {
            return "没有参数";
        }
        for (Object param : parameters) {
            sb.append(param);
        }
        return sb.toString();
    }

    private void writeTotalResultToSheet(Sheet sheet, ITestContext context) {

        int passedSize = context.getPassedTests().size();
        int failedSize = context.getFailedTests().size();
        int skippedSize = context.getSkippedTests().size();
        int totalSize = passedSize + failedSize + skippedSize;

        long testTime = 0;
        IResultMap passedTests = context.getPassedTests();
        IResultMap failedTests = context.getFailedTests();
        IResultMap skippedTests = context.getSkippedTests();
        Set<ITestResult> allPassedResults = passedTests.getAllResults();
        Set<ITestResult> allFailedResults = failedTests.getAllResults();
        Set<ITestResult> allSkipedResults = skippedTests.getAllResults();

        for(ITestResult result : allPassedResults){
            testTime = testTime + (result.getEndMillis() - result.getStartMillis());
        }

        for(ITestResult result : allFailedResults){
            testTime = testTime + (result.getEndMillis() - result.getStartMillis());
        }

        for(ITestResult result : allSkipedResults){
            testTime = testTime + (result.getEndMillis() - result.getStartMillis());
        }



        Row row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("测试用例总条数据");
        row1.createCell(1).setCellValue("通过条数");
        row1.createCell(2).setCellValue("失败条数");
        row1.createCell(3).setCellValue("跳过条数");
        row1.createCell(4).setCellValue("执行时间");

        Row row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue(totalSize);
        row2.createCell(1).setCellValue(passedSize);
        row2.createCell(2).setCellValue(failedSize);
        row2.createCell(3).setCellValue(skippedSize);
        row2.createCell(4).setCellValue(testTime+" ms");
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
            methodResults.add(new MethodResult(methodName,description));
        }

        for (MethodResult methodResult : methodResults) {
            String methodName = methodResult.getMethodName();
            for (ITestResult result : allPassedResults) {
                if (methodName.equals(result.getName())) {
                    methodResult.addPassCount(1);
                    methodResult.addTestTime(result.getEndMillis()-result.getStartMillis());
                }
            }

            for (ITestResult result : allFailedResults) {
                if (methodName.equals(result.getName())) {
                    methodResult.addFailCount(1);
                    methodResult.addTestTime(result.getEndMillis()-result.getStartMillis());
                }
            }

            for (ITestResult result : allSkipedResults) {
                if (methodName.equals(result.getName())) {
                    methodResult.addSkipCount(1);
                    methodResult.addTestTime(result.getEndMillis()-result.getStartMillis());
                }
            }
        }
        //创建标题列
        Row rowTile = sheet.createRow(0);
        rowTile.createCell(0).setCellValue("测试用例名字");
        rowTile.createCell(1).setCellValue("测试用例描述");
        rowTile.createCell(2).setCellValue("用例总条数");
        rowTile.createCell(3).setCellValue("通过条数");
        rowTile.createCell(4).setCellValue("失败条数");
        rowTile.createCell(5).setCellValue("跳过条数");
        rowTile.createCell(6).setCellValue("执行时间");
        int rowNum = 1;
        for (MethodResult methodResult : methodResults) {
            String methodName = methodResult.getMethodName();
            String description = methodResult.getDescription();
            Integer passCount1 = methodResult.getPassCount();
            Integer failCount1 = methodResult.getFailCount();
            Integer skipCount1 = methodResult.getSkipCount();
            Integer totalCount = passCount1 + failCount1 + skipCount1;
            long testTime = methodResult.getTestTime();


            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(methodName);
            row.createCell(1).setCellValue(description);
            row.createCell(2).setCellValue(totalCount);
            row.createCell(3).setCellValue(passCount1);
            row.createCell(4).setCellValue(failCount1);
            row.createCell(5).setCellValue(skipCount1);
            row.createCell(6).setCellValue(testTime+" ms");
            rowNum++;
        }
    }


    public static void main(String[] args) {
        XmlSuite suite = new XmlSuite();
        suite.setName("TmpSuite");
        suite.addListener("org.cechc.etl.test.testcase.Report");


        XmlTest test = new XmlTest(suite);
        test.setName("TmpTest");

        List<XmlClass> classes = new ArrayList<>();

        List<XmlInclude> includes = new ArrayList<>();


        Map<String,String> parameters = new HashMap<>();

        XmlInclude compare_patient_base_identifier_field = new XmlInclude("compare_odsPatientBasicInfoFields_mapTo_obsPatientFields");
        parameters.put("org.cechc.etl.test.testcase.fields.patient.PatientFieldTest.compare_odsPatientBasicInfoFields_mapTo_obsPatientFields.limit","3");
        includes.add(compare_patient_base_identifier_field);
        XmlClass xmlClass1 = new XmlClass("org.cechc.etl.test.testcase.fields.patient.PatientFieldTest");
        xmlClass1.setIncludedMethods(includes);
        classes.add(xmlClass1);

        XmlInclude compare_patient_base_info = new XmlInclude("compare_odsCaseBaseInfoFields_mapTo_obsPatientFields");
        parameters.put("org.cechc.etl.test.testcase.fields.patient.PatientFieldTest.compare_odsCaseBaseInfoFields_mapTo_obsPatientFields.limit","3");
        includes.add(compare_patient_base_info);
        XmlClass xmlClass2 = new XmlClass("org.cechc.etl.test.testcase.fields.patient.PatientFieldTest");
        xmlClass2.setIncludedMethods(includes);
        classes.add(xmlClass2);


        test.setXmlClasses(classes);
        suite.setParameters(parameters);
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }


}
