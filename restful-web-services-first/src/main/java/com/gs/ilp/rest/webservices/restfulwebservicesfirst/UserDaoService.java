package com.gs.ilp.rest.webservices.restfulwebservicesfirst;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static ArrayList<User> users = new ArrayList<>(10);

	static {
		User user1 = new User(1, "Mohit", new Date());
		User user2 = new User(2, "Rohit", new Date());
		User user3 = new User(3, "Test", new Date());
		users.add(user1);
		users.add(user2);
		users.add(user3);

	}
	
	private static int size=3;

	public ArrayList<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		user.setId(++size);
		users.add(user);
		return user;
	}
	
	public User getSpecUser(int id) {
		User userToReturn=null;
		for(User user: users) {
			if(user.getId()==id)
			{
				userToReturn=user;
				break;
			}
		}
		return userToReturn;
	}

}
