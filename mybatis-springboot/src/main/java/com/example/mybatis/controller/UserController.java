package com.example.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") String id) {
		User user = userMapper.getUser(id);
		
		return user;
	}
	
	@GetMapping
	public List<User> getUsersList(){
		List<User> userList = userMapper.getUserList();
		
		return userList;
	}
	
	@PostMapping
	public void createUser( @RequestParam("id") String id,
							@RequestParam("name") String name,
							@RequestParam("phone") String phone,
							@RequestParam("address") String address) {
		userMapper.insertUser(id, name, phone, address);
	}
	
	@PutMapping("/{id}")
	public void editUser(@PathVariable("id") String id,
						@RequestParam("name") String name,
						@RequestParam("phone") String phone,
						@RequestParam("address") String address) {
		int i = userMapper.updatetUser(id, name, phone, address);
		if (i > 0) System.out.println("성공!");	
	}

	// user객체를 입력받아 업데이트도 가능
//	@PutMapping("/{id}")
//	public void editUser(User user) {		
//		int i = userMapper.updatetUser(user.getId(), user.getName(), user.getPhone(), user.getAddress());
//		if (i > 0) System.out.println("성공!");
//	}
	
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") String id) {
		userMapper.deleteUser(id);
	}
	
}
