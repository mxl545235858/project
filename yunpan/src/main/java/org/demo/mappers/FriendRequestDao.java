package org.demo.mappers;

import org.demo.po.FriendRequest;
import org.demo.po.User;

public interface FriendRequestDao {
	public User selectFriendRequest(int rqid);
	public User insertFriendRequest(FriendRequest fq);
	public User deleteFriendRequest(int id);
}
