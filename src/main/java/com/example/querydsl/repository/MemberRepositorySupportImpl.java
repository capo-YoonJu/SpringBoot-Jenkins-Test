package com.example.querydsl.repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.example.querydsl.domain.QMember;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.querydsl.domain.Member;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Transactional(readOnly = true)
@Repository
public class MemberRepositorySupportImpl extends QuerydslRepositorySupport implements MemberRepositorySupport {
	
	public MemberRepositorySupportImpl(JPAQueryFactory queryFactory) {
		super(Member.class);
	}
	
	// 기본과제 : QueryDSL 방식 작성
	@Override
	public List<Member> searchByNameQueryDsl(String search) {
		QMember member = QMember.member;
		JPQLQuery<Member> jpqlQuery = from(member);

		List<Member> memberList = jpqlQuery.where(member.name.contains(search).or(member.nickName.contains(search)))
				.fetch();

		return memberList;
	}

}