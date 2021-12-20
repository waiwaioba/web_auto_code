package com.framework.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/17 20:40
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class RetryListener implements IRetryAnalyzer {
    //最大重试次数
    private int maxRetryCount=3;
    //当前的重试次数
    private int currentRetryCount=0;

    @Override
    public boolean retry(ITestResult result) {
        //Returns true if the test method has to be retried, false otherwise.
        //如果你的测试方法要重新运行的话，那么当前retry方法返回true即可
        System.out.println("执行到了retry方法这里");
        //限制重试的最大次数，否则会进入死循环
        if(currentRetryCount < maxRetryCount) {
            //如果当前的重试次数没有达到限制，就去执行重试机制
            currentRetryCount++;
            return true;
        }else {
            return false;
        }
    }
}
