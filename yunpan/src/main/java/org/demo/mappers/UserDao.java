package org.demo.mappers;

import org.demo.po.User;

public interface UserDao {
	public User selectUser(int id);
	public User getUserByAccount(String account);
	public User insertUser(User newUser);
}
