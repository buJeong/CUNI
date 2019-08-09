package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Member {
	private long id;
	private String regDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String email;
	
	private long delStatus;
	
	private String authKey;
	private boolean emailAuthStatus;
}
