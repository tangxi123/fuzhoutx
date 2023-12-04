package org.cechc.etl.test.testcase.count.allCount;



import org.cechc.etl.test.testcase.Basic;
import org.cechc.etl.test.testcase.BasicTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProgramAllCountTest extends Basic {
    private static Integer count = 0;



    /**
     * 测试标题：对比ODS与OBS的program表条数
     * 测试步骤：因为没有做program表的映射，所以program表的条数应为0
     * 期望结果：条数一致
     */
    @Test(description = "验证obs的program条数为0")
    public void compare_program_count(){
        Integer odsProgramCount = 0;
        Integer obsProgramCount = obsProgramAllCountMapper.getProgramCount();
        Assert.assertEquals(odsProgramCount,obsProgramCount);


    }
}
