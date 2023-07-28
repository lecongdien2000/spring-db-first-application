package com.example.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


@Configuration
public class MySqlSessionFactory {

	@Bean
	public SqlSessionFactory startSqlSession() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory =
		  new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	
}
