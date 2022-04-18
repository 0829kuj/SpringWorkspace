package com.myapp.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.myapp.bbs.model.Human;

@Mapper
public interface HumanMapper {

	@Select("select * from myuser where num = #{num}")
	Human getHuman(int num);
	
	@Select("select * from myuser")
	List<Human> getHumanList();
}
