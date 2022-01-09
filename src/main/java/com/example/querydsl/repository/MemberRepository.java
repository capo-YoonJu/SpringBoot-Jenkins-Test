package com.example.querydsl.repository;

import java.util.List;

import com.example.querydsl.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositorySupport, MemberRepositoryFactory {
	
	// Query Method 방식
	List<Member> findByNameContainingOrNickNameContaining(String name, String nickName);
	
	// JPQL 방식
	@Query("SELECT m FROM Member m WHERE m.name LIKE %:name% OR m.nickName LIKE %:name%")
	List<Member> searchByNameJPQL(@Param("name") String name);
}
