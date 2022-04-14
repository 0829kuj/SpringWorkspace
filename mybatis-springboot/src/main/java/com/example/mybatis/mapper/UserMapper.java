package com.example.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.mybatis.model.User;

@Mapper	// jpa의 repository와 같은 역할을 함
public interface UserMapper {
	
	@Select("select * from user where id = #{id}")
	User getUser(String id);	// 유저객체 리턴 id로 DB의 user테이블에서 데이터를 찾아 유저객체로 리턴

	@Select("select * from user")
	List<User> getUserList();

	@Insert("insert into user values(#{id}, #{name}, #{phone}, #{address})")
	void insertUser(String id, String name, String phone, String address);

	@Update("update user set name = #{name}, phone = #{phone}, address = #{address} where id = #{id}")
	int updatetUser(String id, String name, String phone, String address);

	@Delete("delete from user where id = #{id}")
	void deleteUser(String id);
}
