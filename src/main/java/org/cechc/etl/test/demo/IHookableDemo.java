package org.cechc.etl.test.demo;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class IHookableDemo implements IHookable {
    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        System.out.println("hhhhhhhhhhhhhhhhhelllllllllllllllll");
        callBack.runTestMethod(testResult);
    }
}
