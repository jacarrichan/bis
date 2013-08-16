package cn.javass.ssonline.spider.service.impl;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class BaseServiceTest extends AbstractJUnit4SpringContextTests {
	@Test
	public void init() {
		System.out.println("===========================");
	}
}
