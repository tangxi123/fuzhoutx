package org.cechc.etl.test.service;




import org.cechc.etl.test.mapper.TestCaseMapper;
import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.pojo.TestCase;
import org.cechc.etl.test.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestCaseService {

   @Autowired
   TestCaseMapper testCaseMapper;

    public List<TestCase> getTestCases(){
        List<TestCase> testCases = testCaseMapper.getTestCases();
        return testCaseMapper.getTestCases();
    }


    public HashMap<String,Object> runTestCases(List<TestCase> testCases) {
        try {
            //添加suite
            XmlSuite suite = new XmlSuite();
            suite.setName("FuzhouEtlTestSuite");
            suite.addListener("org.cechc.etl.test.testcase.Report");

            //添加test
            XmlTest test = new XmlTest(suite);
            test.setName("TmpTest");

            //添加测试类
            List<XmlClass> classes = new ArrayList<>();

            //向suite里添加parameters
            Map<String, String> parameters = new HashMap<>();


            //用于临时存储类名
            List<String> classNames = new ArrayList<>();

            for (TestCase testCase : testCases) {
                String packName = testCase.getPackName();
                String className = testCase.getClassName();
                String fullClassName = packName + "." + className;

                if (classNames.contains(fullClassName)) {
                    for (XmlClass xmlClass : classes) {
                        String name = xmlClass.getName();
                        if (fullClassName.equals(name)) {
                            setXmlParameter(testCase, parameters);
                            List<XmlInclude> includedMethods = xmlClass.getIncludedMethods();
                            includedMethods.add(new XmlInclude(testCase.getMethodName()));
                            xmlClass.setIncludedMethods(includedMethods);

                        }
                    }
                } else {
                    List<XmlInclude> methodIncludes = new ArrayList<>();
                    XmlInclude method = new XmlInclude(testCase.getMethodName());
                    setXmlParameter(testCase, parameters);
                    methodIncludes.add(method);
                    XmlClass xmlClass = new XmlClass(fullClassName);
                    xmlClass.setIncludedMethods(methodIncludes);
                    classes.add(xmlClass);
                    test.setXmlClasses(classes);
                    classNames.add(fullClassName);
                }
            }


            suite.setParameters(parameters);
            suite.setParallel(XmlSuite.ParallelMode.CLASSES);
            suite.setThreadCount(5);
            List<XmlSuite> suites = new ArrayList<XmlSuite>();
            suites.add(suite);
            TestNG tng = new TestNG();
            tng.setXmlSuites(suites);

            tng.run();
            HashMap<String,Object> response = new HashMap<>();

            response.put("code",200);
            response.put("status","执行测试用例完成");
            return response;
        }catch (Exception e){
            HashMap<String,Object> response = new HashMap<>();
            response.put("code",500);
            response.put("status","执行测试用例失败");
            response.put("message",e.getMessage());
            return response;
        }




//        runHaHa();



    }
    private void runHaHa(){



        XmlSuite suite1 = new XmlSuite();
        suite1.setName("TmpSuite1");
        suite1.addListener("org.cechc.etl.test.testcase.Report");
        Map<String,String> parameters1 = new HashMap<>();
        List<XmlClass> classes = new ArrayList<>();
        XmlTest test = new XmlTest(suite1);
        test.setName("TmpTest");

        XmlInclude compare_patient_base_identifier_field = new XmlInclude("compare_odsPatientBasicInfoFields_mapTo_obsPatientFields");
        XmlInclude compare_patient_base_info = new XmlInclude("compare_odsCaseBaseInfoFields_mapTo_obsPatientFields");

        parameters1.put("org.cechc.etl.test.testcase.fields.patient.PatientFieldTest.compare_odsPatientBasicInfoFields_mapTo_obsPatientFields.limit","3");
        parameters1.put("org.cechc.etl.test.testcase.fields.patient.PatientFieldTest.compare_odsCaseBaseInfoFields_mapTo_obsPatientFields.limit","3");

        List<XmlInclude> includes1 = new ArrayList<>();
        includes1.add(compare_patient_base_identifier_field);
        includes1.add(compare_patient_base_info);
        XmlClass xmlClass1 = new XmlClass("org.cechc.etl.test.testcase.fields.patient.PatientFieldTest");
        xmlClass1.setIncludedMethods(includes1);
        classes.add(xmlClass1);
        test.setXmlClasses(classes);

        XmlInclude compare_odsPatientCount_and_obsPatientCount = new XmlInclude("compare_odsPatientCount_and_obsPatientCount");
        List<XmlInclude> includes2 = new ArrayList<>();
        includes2.add(compare_odsPatientCount_and_obsPatientCount);
        XmlClass xmlClass2 = new XmlClass("org.cechc.etl.test.testcase.count.PatientCountTest");
        xmlClass2.setIncludedMethods(includes2);
        classes.add(xmlClass2);


        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suite1.setParameters(parameters1);
        suite1.setParallel(XmlSuite.ParallelMode.CLASSES);
        suite1.setThreadCount(5);
        suites.add(suite1);

        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();

    }

    private void setXmlParameter(TestCase testCase,Map<String,String> parameters){
        String packName = testCase.getPackName();
        String className = testCase.getClassName();
        String methodName = testCase.getMethodName();
        Parameter parameter = testCase.getParameter();


        if(parameter.getOrgCode() != null){
            parameters.put(packName+"."+className+"."+methodName+".orgCode",parameter.getOrgCode()+"");
        }
        if(parameter.getOffset() != null){
            parameters.put(packName+"."+className+"."+methodName+".offset",parameter.getOffset()+"");
        }
        if(parameter.getLimit() != null){
            parameters.put(packName+"."+className+"."+methodName+".limit",parameter.getLimit()+"");
        }
        if(parameter.getCreateStartTime() != null){
            parameters.put(packName+"."+className+"."+methodName+".createStartTime",parameter.getCreateStartTime()+"");
        }
        if(parameter.getCreateEndTime() != null){
            parameters.put(packName+"."+className+"."+methodName+".createEndTime",parameter.getCreateEndTime()+"");
        }
        if(parameter.getModifyStartTime() != null){
            parameters.put(packName+"."+className+"."+methodName+".modifyStartTime",parameter.getModifyStartTime()+"");
        }
        if(parameter.getModifyEndTime() != null){
            parameters.put(packName+"."+className+"."+methodName+".modifyEndTime",parameter.getModifyEndTime()+"");
        }

    }


}
