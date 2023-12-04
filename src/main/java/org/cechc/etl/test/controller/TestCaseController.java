package org.cechc.etl.test.controller;

import org.cechc.etl.test.odsimpalamapper.FirstTableMapper;
import org.cechc.etl.test.pojo.TestCase;
import org.cechc.etl.test.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/testcase")
public class TestCaseController {
    @Autowired
    TestCaseService testCaseService;


    @GetMapping("/query")
    public List<TestCase> getTestCases(){
        return testCaseService.getTestCases();
    }

    @PostMapping("/run")
    public HashMap<String,Object> runTestCases(@RequestBody List<TestCase> testCases){
        return  testCaseService.runTestCases(testCases);
    }
}
