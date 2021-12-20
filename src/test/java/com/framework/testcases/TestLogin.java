package com.framework.testcases;

import com.framework.businesslogic.LoginFlow;
import com.framework.common.BaseTest;
import com.framework.config.GlobalDatas;
import com.framework.listener.RetryListener;
import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import io.qameta.allure.Description;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.*;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/11 20:24
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class TestLogin extends BaseTest {

    @BeforeMethod
    @Parameters({"browserType"})
    public void setup(String browserType){
        //用例前置
        //1、打开浏览器
        openBrowser(browserType);
        maxBrowser();
        //2、进入登录页面
        toUrl(GlobalDatas.INDEX_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterLoginPage();
    }

    @Test(retryAnalyzer = RetryListener.class)
    public void test_login_success(){
        //用例步骤
        /*driver.findElement(By.xpath("//input[@placeholder='请输入手机号/用户名']")).sendKeys(GlobalDatas.USER_NAME);
        driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).sendKeys(GlobalDatas.USER_PASSWORD);
        driver.findElement(By.xpath("//a[@class='login-button']")).click();*/
        //通过调用页面层已经封装好的操作组成业务逻辑-登录
        //两层po
        /*LoginPage loginPage = new LoginPage(driver);
        loginPage.inputPhone(GlobalDatas.USER_NAME);
        loginPage.inputPassword(GlobalDatas.USER_PASSWORD);
        HomePage homePage = loginPage.clickLogin();*/
        //三层po
        LoginFlow loginFlow = new LoginFlow(driver);
        HomePage homePage = loginFlow.login(GlobalDatas.USER_NAME,GlobalDatas.USER_PASSWORD);
        //用例断言,测试结果是否符合预期
        //1、根据主页的提示【欢迎来到柠檬班】,根据它是否有显示？？
        myAssertTrue(!homePage.isTipsExist(),"主页的提示->欢迎来到柠檬班");
        //2、根据主页的用户名
        myAssertTrue(homePage.isNicknameExist(),"主页的用户名");
        //退出登录
        homePage.quitLogin();
    }

    @Test(dataProvider = "getLoginFailureDatas")
    @Description("手机号码10位/手机号码12位/手机号码未注册")
    public void test_login_failure(String phone,String pwd){
        LoginFlow loginFlow = new LoginFlow(driver);
        HomePage homePage = loginFlow.login(phone,pwd);
        //断言？？
        LoginPage loginPage = new LoginPage(driver);
        String actual = loginPage.getErrorAccountPwdText();
        myAssertEquals(actual,"账号或密码不正确XX","账号密码不正确提示信息");
    }

    @DataProvider
    public Object[][] getLoginFailureDatas(){
        Object[][] datas={
            {"1332323454","123456"},
            {"133232345451","123456"},
            {"13323234544","123456"},
        };
        return datas;
    }

    @AfterMethod
    public void teardown(){
        //关闭浏览器
        closeBrowser();
    }

}
