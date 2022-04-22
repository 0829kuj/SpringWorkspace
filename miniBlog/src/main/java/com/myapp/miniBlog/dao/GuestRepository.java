package com.myapp.miniBlog.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myapp.miniBlog.entities.Guest;

public interface GuestRepository extends CrudRepository<Guest, Long> {
	
	@Override
	List<Guest> findAll();

}
