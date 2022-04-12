package com.myapp.shoppingmall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.shoppingmall.entites.Admin;
import com.myapp.shoppingmall.entites.User;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByUsername(String username);

}
