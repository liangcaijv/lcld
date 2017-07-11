package org.lanqiao.prj.feiq.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.lanqiao.prj.feiq.exception.RepetitionException;
import org.lanqiao.prj.feiq.util.IOUtils;
import org.lanqiao.prj.feiq.vo.Friends;
import org.lanqiao.prj.feiq.vo.Group;

public class GroupManager implements IGroupManager {
	private List<Group> gList;
	
	private File file;

	public GroupManager(File file) throws IOException{
		this.file = file;
		if(!this.file.exists()){
			file.createNewFile();			
		}
		//初始化列表
		try {
			gList = IOUtils.readObjFromFile(file);
		} catch (Exception e) {
			gList = new ArrayList<Group>();
			gList.add(new Group(0,"myFriends"));//添加默认组
		}
	}

	@Override
	public void add(Group g) throws RepetitionException {
		if(gList.contains(g))
			throw new RepetitionException();
		gList.add(g);
		save();
	}

	@Override
	public void del(Group g) {
		gList.remove(gList.indexOf(g));
		save();
	}


	@Override
	public void update(Group g) {
		int index = gList.indexOf(g);
		gList.set(index, g);
		save();
	}

	@Override
	public Group findById(int id) throws Exception {
		int index = gList.indexOf(new Group(id));
		if(index!=-1){
			return gList.get(index);			
		}else{
			throw new Exception("不正确的组id");
		}
	}

	@Override
	public String list() {

		return gList.toString();
	}

	@Override
	public Set<Friends> listFriendsByGroup(int id) throws Exception {

		Set<Friends> set =  findById(id).getFriends();
//		System.out.println("id=="+id+" size==="+set.size());
		return set;
	}

	@Override
	public void addFriendsToGroup(Friends f, int groupId) throws Exception {
		Group g = findById(groupId);		
		g.getFriends().add(f);
//		System.out.println("============"+gList.size());
//		System.out.println("=======id"+id+"==group===size=="+g.getFriends().size());
		save();
	}

	@Override
	public void removeFriendsFromGroup(Friends f, int id) throws Exception {
		Group g1 = findById(id);
		g1.getFriends().remove(f);
		save();
	}

	@Override
	public void save() {
		try {
			IOUtils.writeObjToFile(file, gList);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String listAll() throws Exception {
		StringBuilder sb = new StringBuilder();
		for(Group g:gList){
			int id = g.getId();
			sb.append(this.listFriendsByGroup(id).toString()+"\n");
		}
		return sb.toString();
	}
}
