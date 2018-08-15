package com.xx.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSONObject;
import com.xx.application.dao.UserDao;
import com.xx.application.domain.CommonResponse;
import com.xx.application.domain.UserDomain;
import com.xx.application.utils.Utils;

@RestController
public class UserController {
	@Autowired
	private UserDao userDao;
	@RequestMapping(value = "/add/user",method = RequestMethod.POST)
	public CommonResponse addUser(@RequestBody(required = true) String params) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			if(params.isEmpty()) {
				commonResponse.setStatus("failed");
				commonResponse.setResult("add user failed " + "params is empty");
				return commonResponse;
			}else {
				JSONObject paramsObject = JSONObject.parseObject(params);
				String name = (String) paramsObject.get("name");
				String password = (String) paramsObject.get("password");
				
				UserDomain userDomain = new UserDomain();
				userDomain.setId(Utils.generalId());
				userDomain.setName(name);
				userDomain.setPassword(password);
				userDomain.setCreateTime(Utils.nowTime());
				userDomain.setModifyTime(Utils.nowTime());
				userDao.addUser(userDomain);
				
				commonResponse.setStatus("success");
				commonResponse.setResult("add user success");
				return commonResponse;
			}
			
		} catch (Exception e) {
			commonResponse.setStatus("failed");
			commonResponse.setResult("add user failed " + e);
			return commonResponse;
		}
	}
}
