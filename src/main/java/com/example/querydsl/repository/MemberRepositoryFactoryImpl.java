package com.example.querydsl.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.querydsl.domain.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.example.querydsl.domain.QMember.member;

@Transactional(readOnly = true)
@Repository
@RequiredArgsConstructor
public class MemberRepositoryFactoryImpl implements MemberRepositoryFactory {

	private final JPAQueryFactory jpaQueryFactory;
	
	// 심화과제 : JPAQueryFactory와 BooleanExpression 방식 작성
	@Override
	public List<Member> searchByNameQueryDslWithJPAQueryFactory(String search) {
		List<Member> memberList = jpaQueryFactory.select(member)
					   .from(member)
					   .where(nameContain(search)
					   .or(nickNameContain(search)))
					   .fetch();
		return memberList;
	}
	
	
	private BooleanExpression nameContain(String search) {
		if (StringUtils.isNullOrEmpty(search)) return null;
		return member.name.contains(search);
	}
	
	private BooleanExpression nickNameContain(String search) {
		if (StringUtils.isNullOrEmpty(search)) return null;
		return member.nickName.contains(search);
	}
}
