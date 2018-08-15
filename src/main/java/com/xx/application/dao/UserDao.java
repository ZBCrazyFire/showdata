package com.xx.application.dao;


import org.apache.ibatis.annotations.Mapper;

import com.xx.application.domain.UserDomain;

@Mapper
public interface UserDao {
	boolean addUser(UserDomain userDomain);
}
