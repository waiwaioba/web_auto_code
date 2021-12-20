package com.framework.listener;

import com.framework.common.BaseTest;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/15 21:18
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class TestResultListener implements IHookable {
    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        //run方法的作用：替换@Test注解标注的测试方法
        //System.out.println("替换@Test注解标注的测试方法");
        //让测试方法照常执行 testResult-->保存我们的测试结果
        callBack.runTestMethod(testResult);
        //获取测试结果的异常信息
        if(testResult.getThrowable() != null){
            //进行截图
            //driver从哪里来？？？
            //先获取当前测试类的实例（对象）
            //TestLogin02 testLogin02 = (TestLogin02) testResult.getInstance();
            //通过父类类型接收子类对象
            BaseTest baseTest = (BaseTest) testResult.getInstance();
            //takeScreenshot(baseTest.driver,"testLogin_"+System.currentTimeMillis());
            //生成字节数组的截图数据
            byte[] data = takeScreenshotAsByte(baseTest.driver);
            saveScreenshotToAllure(data);
        }
    }

    @Attachment(value = "screenshot",type = "image/png")
    public byte[] saveScreenshotToAllure(byte[] data){
        //返回的字节数组的数据 作为附件添加到Allure报表中--》@Attachment注解来实现的
        return data;
    }

    /**
     * 生成字节数组的截图数据
     * @param driver
     * @return
     */
    public byte[] takeScreenshotAsByte(WebDriver driver){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        byte[] data = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        return data;
    }

    /**
     * 生成截图以普通文件的形式，并且保存到本地
     * @param driver
     * @param fileName
     */
    public void takeScreenshot(WebDriver driver, String fileName){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir")+"\\screenshot\\"+fileName+".png");
        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
