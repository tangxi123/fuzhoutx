package org.cechc.etl.test.demo;

import org.testng.*;
import org.testng.internal.ConstructorOrMethod;

public class IInvokedMethodListenerImp implements IInvokedMethodListener{

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        ConstructorOrMethod constructorOrMethod = method.getTestMethod().getConstructorOrMethod();
        DisableListener disable = constructorOrMethod.getMethod().getDeclaringClass().getAnnotation(DisableListener.class);
        if(disable != null){
            return;
        }

        System.out.println("HAHAHAHAHAHAHAHAHAHAHAHAHAH"+method.getTestMethod().getMethodName());

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }
}
