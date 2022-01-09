package com.example.querydsl.api;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.querydsl.domain.Member;
import com.example.querydsl.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberApi {
	
	private final MemberRepository memberRepository;
	
	@GetMapping
	public List<Member> getAll() {
		return memberRepository.findAll();
	}
	
	@GetMapping("/method")
	public List<Member> searchUsingQueryMethod(@RequestParam String search) {
		return memberRepository.findByNameContainingOrNickNameContaining(search, search);
	}
	
	@GetMapping("/jpql")
	public List<Member> searchUsingJPQL(@RequestParam String search) {
		return memberRepository.searchByNameJPQL(search);
	}

	@GetMapping("/qdsl")
	public List<Member> searchUsingQqueryDSL(@RequestParam String search) {
	    return memberRepository.searchByNameQueryDsl(search);
	}
	
	@GetMapping("/factory")
	public List<Member> searchUsingQueryDslWithJPAQueryFactory(@RequestParam String search) {
	    return memberRepository.searchByNameQueryDslWithJPAQueryFactory(search);
	}
	
	@PostConstruct
	private void generateSample() {
		if (memberRepository.count() > 0) {
			return;
		}
		
		memberRepository.save(Member.of("김싸피", "제이지"));
		memberRepository.save(Member.of("이싸피", "라이언"));
		memberRepository.save(Member.of("최싸피", "어피치"));
	}
}
