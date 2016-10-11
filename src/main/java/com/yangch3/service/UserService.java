package com.yangch3.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yangch3.data.User;

@Service
public class UserService {

	private static Map<String, User> users = new HashMap<String, User>();
	static {
		users.put("0001", new User("0001","ZhangSan","111","zhang@gmail.com"));
		users.put("0002", new User("0002","LiSi","222","Li@gmail.com"));
		users.put("0003", new User("0003","WangWu","333","Wang@gmail.com"));
		users.put("0004", new User("0004","ChenBa","444","Chen@gmail.com"));
		users.put("0005", new User("0005","ZhaoLiu","555","Zhao@gmail.com"));
	}
	
	public User login(String id, String password) {
		User user = users.get(id);
		if (user != null) {
			String userpwd = user.getPassword();
			if (userpwd.equals(password)) {
				return user;
			}
		}
		return null;
		
	}
	
	public Map getAllUsers() {
		return users;
		
	}
	
	public User getUserById(String id) {
		return users.get(id);
		
	}
	
	public void deleteUserById(String id) {
		users.remove(id);
	}
}
