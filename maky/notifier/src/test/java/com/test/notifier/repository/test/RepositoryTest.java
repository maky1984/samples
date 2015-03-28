package com.test.notifier.repository.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.test.notifier.dao.TokenRepository;
import com.test.notifier.domain.Token;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testApplicationContext.xml" })
public class RepositoryTest {

	@Autowired
	private TokenRepository repository;

	@Test
	public void testInsert() {
		Token token = new Token();
		token = repository.save(token);
		Assert.notNull(token.getId());
		Token newToken = repository.findOne(token.getId());
		repository.delete(token);
		Assert.isTrue(token.equals(newToken));
	}
}
