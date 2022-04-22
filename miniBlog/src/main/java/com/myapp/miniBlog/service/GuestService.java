package com.myapp.miniBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.miniBlog.dao.GuestRepository;
import com.myapp.miniBlog.entities.Guest;

@Service
public class GuestService {
	
	@Autowired
	public GuestRepository GuestRepo;
	
	public List<Guest> findAll(){
		return GuestRepo.findAll();
	}

}
