package com.framework.testcases;

import com.framework.listener.RetryListener;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/17 20:47
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class TestRegister {
    @Test()
    public void register(){
        int a = 1;
        int b = 2;
        Assert.assertEquals(a,b);
    }
}
