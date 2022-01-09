package com.example.querydsl.repository;

import java.util.List;

import com.example.querydsl.domain.Member;

// QueryDSL 방식
public interface MemberRepositorySupport {
	List<Member> searchByNameQueryDsl(String name);
}