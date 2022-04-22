package com.myapp.miniBlog.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.repository.query.Param;

import lombok.Data;

@Data
@Entity
@Table(name="guestbook")
public class Guest {
	
	@Id
	private int gno;
	
	private int mno;
	
	private String content;
	
	private String writer;
	
	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
}
