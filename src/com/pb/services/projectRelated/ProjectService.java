package com.pb.services.projectRelated;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Project;
import com.pb.entity.Subproject;
import com.pb.entity.User;
import com.pb.entity.Subproject;
import com.pb.services.common.CommService;

@Service(value="projectService")
public class ProjectService extends CommService {
	
	public List<Project> findByName(String name){
		List<Project> list=baseDAO.findByHQL("from Project where name like ?",new Object[]{"%"+name+"%"});
		return list;
	}
	 	
	public List<Project> findAll(Integer userid){
		List<Subproject> list1=baseDAO.findByHQL("from Subproject where user.id =?",new Object[]{userid});
		List<Project> list=new ArrayList<Project>();
		for(int i=0;i<list1.size();i++)
		{
			Subproject sp=list1.get(i);
			Project p=sp.getProject();
			list.add(p);
		}
		return list;
	}
	
	public List<User> findRelated(Integer projectid){
		List<Subproject> list1=baseDAO.findByHQL("from Subproject where project.id =?",new Object[]{projectid});
		List<User> list=new ArrayList<User>();
		for(int i=0;i<list1.size();i++)
		{
			Subproject sp=list1.get(i);
			User u=sp.getUser();
			list.add(u);
		}
		return list;
	}
	
	public void addRelation(Integer projectid,Integer userid){
		Project p=new Project();
		p=baseDAO.findById(projectid,Project.class);
		User u=new User();
		u=baseDAO.findById(userid, User.class);
		Subproject sp=new Subproject(u,p, null, null);
		baseDAO.save(sp);
	}
	
	public void deleteRelation(Integer projectid,Integer userid){
		List<Subproject> spList=baseDAO.findByHQL("from Subproject where user.id=? and project.id=?",new Object[]{userid,projectid});
		baseDAO.delete(spList.get(0));
	}
}
