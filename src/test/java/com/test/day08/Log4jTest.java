package com.test.day08;

import org.apache.log4j.Logger;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/13 20:26
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class Log4jTest {
    //初始化log4j日志对象
    private static Logger logger = Logger.getLogger(Log4jTest.class);
    public static void main(String[] args) {
        //测试使用log4j日志框架
        logger.debug("这是debug级别的日志");
        logger.info("这是info级别的日志");
        logger.warn("这是warn级别的日志");
        logger.error("这是error级别的日志");
    }
}
