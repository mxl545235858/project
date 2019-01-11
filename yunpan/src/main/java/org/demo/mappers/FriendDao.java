package org.demo.mappers;

import org.demo.po.Friend;
import org.demo.po.User;

public interface FriendDao {
	public User selectFriend(int uid);
	public User insertFriend(Friend f);
	public User removefriend(int uid,int fid);
}
