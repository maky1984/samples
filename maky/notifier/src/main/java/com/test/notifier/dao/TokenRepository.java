package com.test.notifier.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.notifier.domain.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

}
