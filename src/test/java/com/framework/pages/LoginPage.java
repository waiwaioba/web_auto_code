package com.framework.pages;

import com.framework.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/11 20:15
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class LoginPage extends BasePage {
    //元素定位信息-->Java对象里面的属性
    //1、手机号码输入框
    private By phoneBy = By.xpath("//input[@placeholder='请输入手机号/用户名']");
    //2、密码输入框
    private By passwordBy = By.xpath("//input[@placeholder='请输入密码']");
    //3、登录按钮
    private By loginButtonBy = By.xpath("//a[@class='login-button']");
    //4、账号或密码不正确的提示信息
    private By errorAccountPwdBy =By.xpath("//p[@class='el-message__content']");

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    public String getErrorAccountPwdText(){
        return getText(driver,errorAccountPwdBy,"账号或密码不正确的提示信息");
    }

    //元素操作-->Java对象里面的行为
    public void inputPhone(String phone){
        //driver？？？
        //waitElementVisible(driver,phoneBy).sendKeys(phone);
        type(driver,phoneBy,phone,"用户名输入框");
    }

    public void inputPassword(String password){
        //waitElementVisible(driver,passwordBy).sendKeys(password);
        type(driver,passwordBy,password,"密码输入框");
    }

    public HomePage clickLogin(){
        //waitElementClickable(driver,loginButtonBy).click();
        click(driver,loginButtonBy,"登录按钮");
        //进入到首页--新的页面的诞生
        return new HomePage(driver);
    }
}
