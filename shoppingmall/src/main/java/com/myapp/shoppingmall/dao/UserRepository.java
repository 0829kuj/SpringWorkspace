package com.myapp.shoppingmall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.shoppingmall.entites.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

}
