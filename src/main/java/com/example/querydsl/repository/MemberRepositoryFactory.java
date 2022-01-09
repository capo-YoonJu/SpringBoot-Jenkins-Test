package com.example.querydsl.repository;

import java.util.List;

import com.example.querydsl.domain.Member;

// JPAQueryFactory 방식
public interface MemberRepositoryFactory {
	List<Member> searchByNameQueryDslWithJPAQueryFactory(String name);
}
