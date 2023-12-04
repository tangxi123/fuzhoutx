package org.cechc.etl.test.mapper;


//import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.ibatis.annotations.*;
import org.cechc.etl.test.pojo.Parameter;
import org.cechc.etl.test.pojo.TestCase;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface TestCaseMapper {
    @Select("select * from test_case")
    @Results(id = "testcaseMapToParam",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "packName",column = "pack_name"),
            @Result(property = "className",column = "class_name"),
            @Result(property = "methodName",column = "method_name"),
            @Result(property = "packDesc",column = "pack_desc"),
            @Result(property = "classDesc",column = "class_desc"),
            @Result(property = "methodDesc",column = "method_desc"),
            @Result(property = "parameter",column = "id",javaType = Parameter.class,one = @One(select = "getParamByTestCaseId"))
    })
    List<TestCase> getTestCases();

    @Select("select * from param where test_case_id = #{testCaseId}")
    @Results(value = {
            @Result(property = "createStartTime",column = "create_start_time",javaType = LocalDateTime.class),
            @Result(property = "createEndTime",column = "create_end_time",javaType = LocalDateTime.class),
            @Result(property = "modifyStartTime",column = "modify_start_time",javaType = LocalDateTime.class),
            @Result(property = "modifyEndTime",column = "modify_end_time",javaType = LocalDateTime.class),
            @Result(property = "testCaseId",column = "test_case_id",javaType = Integer.class),
            @Result(property = "orgCode",column = "org_code",javaType = String.class)
    })
    Parameter getParamByTestCaseId(String testCaseId);

}
