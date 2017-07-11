package org.lanqiao.prj.feiq.service;

import java.util.Set;

import org.lanqiao.prj.feiq.exception.RepetitionException;
import org.lanqiao.prj.feiq.vo.Friends;
import org.lanqiao.prj.feiq.vo.Group;

public interface IGroupManager {
	void add(Group g) throws RepetitionException;
	void del(Group g);
	void update(Group g);
	Group findById(int id) throws Exception;
	String list();
	
	
	Set<Friends> listFriendsByGroup(int id) throws Exception;
	void addFriendsToGroup(Friends f, int id) throws Exception;
	void removeFriendsFromGroup(Friends f, int id) throws Exception;
	void save();
	String listAll() throws Exception;
}
