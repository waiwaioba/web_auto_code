package com.framework.testcases;

import com.framework.businesslogic.AddToCartFlow;
import com.framework.businesslogic.LoginFlow;
import com.framework.common.BaseTest;
import com.framework.config.GlobalDatas;
import com.framework.pages.CartPage;
import com.framework.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/8 21:26
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class TestCart extends BaseTest {

    @BeforeTest
    @Parameters({"browserType"})
    public void setup(String browserType){
        //用例前置
        //打开浏览器-->登录
        openBrowser(browserType);
        maxBrowser();
        toUrl(GlobalDatas.INDEX_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterLoginPage();
        LoginFlow loginFlow = new LoginFlow(driver);
        loginFlow.login(GlobalDatas.USER_NAME,GlobalDatas.USER_PASSWORD);
    }

    @Test
    public void test_addtocart_success() {
        //测试步骤
        //添加商品到购物车
        AddToCartFlow addToCart = new AddToCartFlow(driver);
        HashMap<String,Object> datas = addToCart.doAction();
        //断言
        //进入到购物车
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = homePage.enterCartPage();
        //1、商品的名称
        myAssertEquals(cartPage.getGoodsName(), (String) datas.get("goodsTitleX"),"根据商品的名称断言");
        //2、商品的价格
        //3、商品的数量
    }

    @AfterTest
    public void teardown() {
        //测试后置
        //删除购物车商品
        CartPage cartPage = new CartPage(driver);
        cartPage.deleteCart();
        //1、退出登录
        HomePage homePage = new HomePage(driver);
        homePage.quitLogin();
        //2、关闭浏览器
        closeBrowser();
    }
}
