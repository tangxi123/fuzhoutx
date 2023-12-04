package org.cechc.etl.test.testcase.util;

//import com.github.pagehelper.PageHelper;
import org.cechc.etl.test.domain.DateTimeRange;
import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.testcase.count.PatientCountTest;
import org.cechc.etl.test.testcase.count.allCount.PatientAllCountTest;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class TestUtil {


    public static <T> String getParamValue(Class<T> clazz, Method method, ITestContext context, String paraName){
        XmlSuite xmlSuite = context.getSuite().getXmlSuite();
        String param = xmlSuite.getParameter(clazz.getName()+"."+method.getName()+"."+paraName);
        return param;

    }

    public static <T> DateTimeRange getDatetimeRange(Class<T> clazz, Method method, ITestContext context){
        String createStartTime = getParamValue(clazz,method,context,"createStartTime");
        String createEndTime = getParamValue(clazz,method,context,"createEndTime");
        String modifyStartTime = getParamValue(clazz,method,context,"modifyStartTime");
        String modifyEndTime = getParamValue(clazz,method,context,"modifyEndTime");
        DateTimeRange dateTimeRange = new DateTimeRange(createStartTime, createEndTime, modifyStartTime, modifyEndTime);
        return dateTimeRange;
    }

    public static <T> Parameter getParamValue(Class<T> clazz, Method method, ITestContext context){
        String orgCode = getParamValue(clazz, method, context, "orgCode");
        String limit = getParamValue(clazz, method, context, "limit");
        String offset = getParamValue(clazz, method, context, "offset");
        String createStartTime = getParamValue(clazz,method,context,"createStartTime");
        String createEndTime = getParamValue(clazz,method,context,"createEndTime");
        String modifyStartTime = getParamValue(clazz,method,context,"modifyStartTime");
        String modifyEndTime = getParamValue(clazz,method,context,"modifyEndTime");
        Parameter parameter = new Parameter();
        parameter.setOrgCode(orgCode);
        parameter.setLimit(limit != null ? Integer.parseInt(limit) : null);
        parameter.setOffset(offset != null ? Integer.parseInt(offset) : null);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        parameter.setCreateStartTime(createStartTime != null ? LocalDateTime.parse(createStartTime.replace("T"," "),dateTimeFormatter) : null);
        parameter.setCreateEndTime(createEndTime != null ? LocalDateTime.parse(createEndTime.replace("T"," "),dateTimeFormatter) : null);
        parameter.setModifyStartTime(modifyStartTime != null ? LocalDateTime.parse(modifyStartTime.replace("T"," "),dateTimeFormatter) : null);
        parameter.setModifyEndTime(modifyEndTime != null ? LocalDateTime.parse(modifyEndTime.replace("T"," "),dateTimeFormatter) : null);
        return parameter;
    }



}
