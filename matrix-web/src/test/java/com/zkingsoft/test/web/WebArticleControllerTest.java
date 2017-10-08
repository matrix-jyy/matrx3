package com.zkingsoft.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.zkingsoft.actions.admin.web.WebArticleController;
import com.zkingsoft.model.web.WebArticle;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.services.web.WebArticleService;
import com.zkingsoft.test.BaseJunit4Test;

public class WebArticleControllerTest extends BaseJunit4Test {


	static Logger log = Logger.getLogger(WebArticleControllerTest.class);

	@Resource // 自动注入,默认按名称
	private WebArticleController webArticleController;

	@BeforeClass
	public static void beforeClass() throws Exception {
		log.info("测试类执行前调用");
	}

	@AfterClass
	public static void afterClass() throws Exception {
		log.info("测试类执行之后调用");
	}

	@Before
	public void before() throws Exception {
		log.info("测试方法前调用");
	}

	@After
	public void afterTest() throws Exception {
		log.info("测试方法执行后调用");
	}

	@Test
	public void testShowListWebArticlePaginationVO() {

		// 判断期待结果和实际结果是否相等
		assertEquals(5, 6);
	}

	@Test
	@Transactional // 标明此方法需使用事务
	@Rollback(true) // 标明使用完此方法后事务不回滚,true时为回滚
	public void testAddOrModify() {
		WebArticle webArticle = new WebArticle();
		webArticle.setArtTitle("好好好");
		AjaxResult ajaxResult = webArticleController.addOrModify(webArticle);
		assertEquals(AjaxResult.STATUS_OK, ajaxResult.getStatus());
	}

	@Test
	public void testEditForm() {
		fail("Not yet implemented");
	}

	@Test
	public void testDel() {
		fail("Not yet implemented");
	}

	/**
	 * 超时单元测试 单位毫秒
	 */
	@Test(timeout = 100)
	public void testTime() {
		for (int i = 0;; i++) {
			log.info("超时测试" + i);
			if (i > 200) {
				assertEquals(5, 5);
				break;
			}
		}
	}

	/**
	 * 异常单元测试
	 */
	@Test(expected = ArithmeticException.class)
	public void testException() {
		@SuppressWarnings("unused")
		int r = 1 / 0;
	}

	/**
	 * 多参数测试方案
	 */
	@Test(timeout = 100)
	public void testData() {
		Object param[][] = { { 11, 11 }, { 21, 22 } };
		for (int i = 0; i < param.length; i++) {
			log.info(i);
			assertEquals(param[i][0], param[i][1]);
		}
	}
}
