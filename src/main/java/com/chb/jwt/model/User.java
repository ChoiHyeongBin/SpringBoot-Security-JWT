package com.chb.jwt.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data		// Getter, Setter 생성
@Entity	// DB 를 만듬
public class User {

	@Id		// 기본키(PK) 자동생성
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// MySQL 을 사용한다면, AUTO_INCREMENT(값 자동증가) 가 됨
	private long id;
	private String username;
	private String password;
	private String roles;	// USER, ADMIN
	
	public List<String> getRoleList() {		// role 이 여러개라 만듬
		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
	
}
