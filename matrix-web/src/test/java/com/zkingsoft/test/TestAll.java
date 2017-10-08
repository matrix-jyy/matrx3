package com.zkingsoft.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.zkingsoft.test.web.WebArticleControllerTest;
//指定运行器
@RunWith(Suite.class)
//指定要测试的类
@Suite.SuiteClasses({ WebArticleControllerTest.class })
/**
 * 套件测试
 * 
 * @author jiangyouyao
 *
 */
public class TestAll {

	public class TestSuite1 {

	}
}


