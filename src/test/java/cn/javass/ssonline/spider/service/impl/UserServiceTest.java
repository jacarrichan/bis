package cn.javass.ssonline.spider.service.impl;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.avicit.bis.hr.user.model.UserModel;
import com.avicit.bis.hr.user.service.UserService;

/**
 * 
 * 该测试为集成测试，非单元测试
 * 
 * User: Zhang Kaitao Date: 11-12-26 下午4:33 Version: 1.0
 */
// @Ignore

@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

	AtomicInteger counter = new AtomicInteger();

	@Autowired
	private UserService userService;

	@Test
	public void testCreate() {

		int beforeDbCount = userService.countAll();

		userService.save(genRandomUser());

		int afterDbCount = userService.countAll();

		assertEquals(beforeDbCount + 1, afterDbCount);
	}

	@Test
	public void testUpdate() {

		UserModel user = userService.save(genRandomUser());
		String expectedPassword = "123234";
		user.setPassword(expectedPassword);
		userService.update(user);

		String actualPassword = userService.get(user.getId()).getPassword();

		assertEquals(expectedPassword, actualPassword);

	}

	@Test
	public void testDelete() {

		int beforeDbCount = userService.countAll();

		UserModel user = userService.save(genRandomUser());

		userService.delete(user.getId());

		int afterDbCount = userService.countAll();

		assertEquals(beforeDbCount, afterDbCount);
	}

	@Test
	public void testList() {

		UserModel user = userService.save(genRandomUser());

		List<UserModel> userList = userService.listAll();

		assertThat(userList, hasItem(user));
	}

	public UserModel genRandomUser() {
		long randomKey = System.nanoTime() + counter.addAndGet(1);
		UserModel user = new UserModel();
		user.setUsername("zhang" + randomKey);
		user.setEmail("zhang" + randomKey + "@sishuok.com");
		user.setPassword("123456");
		user.setRegisterDate(new Date());
		return user;
	}

}
