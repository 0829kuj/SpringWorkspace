package com.myapp.mobile.ui.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.mobile.model.request.UpdateUserRequest;
import com.myapp.mobile.model.request.UserRequest;
import com.myapp.mobile.model.response.UserRest;

@RestController
@RequestMapping("/users")	// http://localhost:8080/users
public class UserController {
	
	Map<String, UserRest> users;
	
	// 효율적으로 DB를 사용하기 위해 페이지 수와 한 페이지 당 가져올 데이터수를 지정
	@GetMapping
	public String getUserList(@RequestParam(value= "page", defaultValue = "1") int page, 
							@RequestParam(value = "limit", defaultValue = "50") int limit,
							@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "유저 리스트 리턴 페이지: "+ page + ", limit : " + limit + ", 정렬 : " + sort;
	}
	
	@GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE,
												MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserRest> getUser(@PathVariable("userId") String id) {
		// java 객체 유저를 리턴
		if (users.containsKey(id)) {
			return new ResponseEntity<UserRest>(users.get(id), HttpStatus.OK);	
		} else {	// userId가 없음을 알림
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/bad")
	public ResponseEntity<String> badRequest() {
		return new ResponseEntity<String>("잘못된 요청", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping
	public ResponseEntity<UserRest> getCreaterUser(@Valid @RequestBody UserRequest user) {
		UserRest returnUser = new UserRest();
		returnUser.setName(user.getName());
		returnUser.setEmail(user.getEmail());
		String userId = UUID.randomUUID().toString();	// 랜덤한 유니크 id를 만듦
		returnUser.setUserId(userId);
		
		if (users == null) users = new HashMap<>();	// 싱클톤 패턴. 선언된 map <문자열, UserRest>가 없으면 새로생성
		users.put(userId, returnUser);		// map이므로 (유저id, 유저객체) 를 한쌍으로 입력
		
		return new ResponseEntity<UserRest>(returnUser, HttpStatus.CREATED);
	}
	
	// 업데이트 시 id를 입력 후 body에 업데이트 할 이름을 json으로 입력함
	@PutMapping("/{userId}")
	public UserRest getUpdateUser(@PathVariable("userId") String id,
								@Valid @RequestBody UpdateUserRequest user) {
		UserRest savedUser = users.get(id);
		savedUser.setName(user.getName());		// 이름 수정됨
		
		return savedUser;
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> geDeletetUser(@PathVariable("userId") String id) {
		users.remove(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
