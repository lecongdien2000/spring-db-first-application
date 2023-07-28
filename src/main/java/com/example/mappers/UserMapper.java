package com.example.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.example.models.User;
@Component
@Mapper
public interface UserMapper {
	public User getUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}



	

