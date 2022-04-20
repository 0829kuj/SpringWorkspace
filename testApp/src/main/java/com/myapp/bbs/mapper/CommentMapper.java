package com.myapp.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.myapp.bbs.model.Comment;

@Mapper
public interface CommentMapper {
	
	@Select("select * from comment where target = #{idNum}")
	List<Comment> getComment(int num);
}
